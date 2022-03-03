package com.sekwah.sekclib.network.s2c;

import com.sekwah.sekclib.SekCLib;
import com.sekwah.sekclib.capabilitysync.CapabilityEntry;
import com.sekwah.sekclib.capabilitysync.SyncEntry;
import com.sekwah.sekclib.capabilitysync.capabilitysync.broadcaster.CapabilityInfo;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.ISyncTrackerData;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerData;
import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerUpdater;
import com.sekwah.sekclib.registries.SekCLibRegistries;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Structure
 * int what player it targets
 * int how many capabilities
 * <p>
 * int capability sync id
 * int how many fields to sync
 * <p>
 * int id for which field on the capability to sync. (this stops types needing to by synced with a registry)
 * boolean has data (not null) (if its null skip encoding & decoding)
 * (push to the tracker to handle the encoding and decoding)
 */
public class ClientCapabilitySyncPacket {

    private final int entityId;
    public List<CapabilityInfo> capabilityData;
    private boolean includePrivate;

    /**
     * To construct the data for serialisation
     *
     * @param entity
     * @param capabilityData
     * @param includePrivate - should include non global tags
     */
    public ClientCapabilitySyncPacket(LivingEntity entity, List<CapabilityInfo> capabilityData, boolean includePrivate) {
        this.entityId = entity.getId();
        this.capabilityData = new ArrayList<>(capabilityData);
        this.includePrivate = includePrivate;
        if (!includePrivate) {
            this.capabilityData.removeIf(data -> data.changedEntries.isEmpty());
        }
    }

    private ClientCapabilitySyncPacket(int player, List<CapabilityInfo> capabilityData) {
        this.entityId = player;
        this.capabilityData = capabilityData;
    }

    public static void encodeSyncTrackers(List<ISyncTrackerData> trackers, FriendlyByteBuf outBuffer) {
        for (ISyncTrackerData tracker : trackers) {
            outBuffer.writeByte(tracker.getSyncEntry().getTrackerId());
            outBuffer.writeBoolean(tracker.getSendValue() != null);
            if(tracker.getSendValue() != null) {
                tracker.getSyncEntry().getSerializer().encode(tracker.getSendValue(), outBuffer);
            }
        }
    }

    /**
     * TODO replace return type with a temporary data only object ready for processing back into the list.
     *
     * @param capability for context on how to de-code the capabilities
     * @param inBuffer
     * @return
     */
    public static List<ISyncTrackerData> decodeSyncTrackers(CapabilityEntry capability, FriendlyByteBuf inBuffer) {
        int trackerCount = inBuffer.readInt();
        List<ISyncTrackerData> syncTrackerDataList = new ArrayList<>();
        for (int i = 0; i < trackerCount; i++) {
            int trackerId = inBuffer.readByte();
            boolean hasData = inBuffer.readBoolean();
            SyncEntry tracker = capability.getSyncEntries().get(trackerId);
            Object data = hasData ? tracker.getSerializer().decode(inBuffer) : null;
            syncTrackerDataList.add(new SyncTrackerData(tracker, data));

        }
        return syncTrackerDataList;
    }

    public static void encode(ClientCapabilitySyncPacket msg, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt(msg.entityId);
        outBuffer.writeInt(msg.capabilityData.size());
        for (CapabilityInfo capInfo : msg.capabilityData) {
            outBuffer.writeInt(capInfo.capabilityId);
            int count = capInfo.changedEntries.size();
            if (msg.includePrivate) count += capInfo.changedPrivateEntries.size();
            outBuffer.writeInt(count);

            encodeSyncTrackers(capInfo.changedEntries, outBuffer);
            if (msg.includePrivate) encodeSyncTrackers(capInfo.changedPrivateEntries, outBuffer);
        }
    }

    public static ClientCapabilitySyncPacket decode(FriendlyByteBuf inBuffer) {

        int playerId = inBuffer.readInt();
        int capCount = inBuffer.readInt();
        List<CapabilityInfo> capabilityInfoList = new ArrayList<>();
        for (int i = 0; i < capCount; i++) {
            int capId = inBuffer.readInt();
            CapabilityEntry capability = SekCLibRegistries.CAPABILITY_REGISTRY.getValue(capId);
            List<ISyncTrackerData> syncTrackers = decodeSyncTrackers(capability, inBuffer);
            if (!syncTrackers.isEmpty()) {
                CapabilityInfo capInfo = new CapabilityInfo(capId, capability);
                capInfo.changedEntries.addAll(syncTrackers);
                capabilityInfoList.add(capInfo);
            }
        }

        return new ClientCapabilitySyncPacket(playerId, capabilityInfoList);
    }

    public static class Handler {
        public static void handle(ClientCapabilitySyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
            NetworkEvent.Context context = ctx.get();
            context.enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                    ClientLevel level = Minecraft.getInstance().level;
                    Entity entity = level.getEntity(msg.entityId);
                    if (entity instanceof LivingEntity livingEntity) {
                        for (CapabilityInfo capInfo : msg.capabilityData) {
                            livingEntity.getCapability(capInfo.capability.getCapability()).ifPresent(targetCap -> {
                                List<SyncEntry> syncEntries = capInfo.capability.getSyncEntries();
                                for (ISyncTrackerData syncTrackerData : capInfo.changedEntries) {
                                    SyncEntry syncEntry = syncEntries.get(syncTrackerData.getSyncEntry().getTrackerId());
                                    try {
                                        Object syncValue = syncTrackerData.getSendValue();
                                        if(syncTrackerData.getSyncEntry().getSerializer() instanceof SyncTrackerUpdater updater)   {
                                            updater.updateTracker(syncValue, syncEntry.getGetter().invoke(targetCap));
                                        }
                                        syncEntry.getSetter().invoke(targetCap, syncValue);
                                    } catch (Throwable e) {
                                        SekCLib.LOGGER.error("There was a problem setting a value", e);
                                    }
                                }
                            });
                        }
                    }
                }));
            context.setPacketHandled(true);
        }
    }
}
