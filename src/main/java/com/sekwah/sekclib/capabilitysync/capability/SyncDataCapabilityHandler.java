package com.sekwah.sekclib.capabilitysync.capability;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.sekclib.SekCLib;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID)
public class SyncDataCapabilityHandler {

    @CapabilityInject(ISyncData.class)
    public static final Capability<ISyncData> SYNC_DATA = null;


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(SekCLib.MOD_ID, "sync_data"), new SyncData());
            // TODO handle creating the capability trackers.
        }
    }
}
