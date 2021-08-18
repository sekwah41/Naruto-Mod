package com.sekwah.sekclib.network.datamanager;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.server.level.ServerPlayer;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Sort of similar to EntityDataManager but designed to handle any custom data.
 *
 * One thing to note is on top of any events used that they may not fire after the tracker is made.
 * E.g. if player is in view distance and a new tracker is added.

 * Further Dev Notes:
 * For now you will have to implement the tracking and stop tracking events but there may be pre-done versions you can
 * extend in the future e.g. ones that will track entities, dimensions or chunks.
 *
 * May want to make behavior to handle all of the following types of packet distribution handled in
 * {@link net.minecraftforge.fmllegacy.network.PacketDistributor}.
 *
 * E.g. TRACKING_ENTITY_AND_SELF may use {@link net.minecraftforge.event.entity.player.PlayerEvent.StartTracking}
 * and {@link net.minecraftforge.event.entity.player.PlayerEvent.StopTracking} to automatically know when to start stop.
 * Then the playersTracking would be kinda useless.
 *
 * This may be a bit overkill though I just wanted to make this for fun. Feel free to recommend any better ways
 * of doing it :)
 *
 *
 * NEW dont use tracking ID's use the capabilities and then provide data based on which capability is being used possibly.
 *
 * Check what is allowed on both. You may need to still add it for custom ones e.g. dimension or client side based world ones.
 * Though Entity, TileEntity and Chunk should probably be fine.
 *
 * @param <T>
 */
public abstract class CustomDataManager<T> {

    private static final Map<Class, Integer> NEXT_ID_MAP = Maps.newHashMap();

    private Callable<? extends CustomDataTracker<T>> factory;


    /**
     * Server side this will be used to sync the channel id's to the client.
     */
    private final Int2ObjectMap<CustomDataTracker> dataTrackerID = new Int2ObjectOpenHashMap<>();

    public CustomDataManager(Callable<? extends CustomDataTracker<T>> factory) {
        this.factory = factory;
    }

    /**
     * Create a new tracker object.
     * @param target
     * @return returns null if there was a problem creating the tracker.
     */
    public CustomDataTracker<T> makeCustomDataTracker(T target) {
        try {
            CustomDataTracker<T> tracker = factory.call();
            tracker.setTarget(target);
            tracker.setDataManager(this);
            return tracker;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract CustomDataTracker<T> getFromIdentifier(int id);

    /**
     * Stop the current player from tracking the specified id.
     * @param playerEntity
     */
    public void playerStopTracking(ServerPlayer playerEntity, CustomDataTracker tracker) {
        this.playerStopTracking(playerEntity, tracker);
    }

    /**
     * Start the current player tracking the tracker and send a full thing of data.
     * @param playerEntity
     * @param tracker the targeted tracker
     */
    public void playerStartTracking(ServerPlayer playerEntity, CustomDataTracker tracker) {
        this.playerStartTracking(playerEntity, tracker);
    }

//    /**
//     * Stop the current player from tracking the specified id.
//     * @param playerEntity
//     */
//    public void playerStopTracking(ServerPlayerEntity playerEntity, int tracker) {
//
//    }
//
//    /**
//     * Start the current player tracking the tracker and send a full thing of data.
//     * @param playerEntity
//     * @param tracker the targeted tracker
//     */
//    public void playerStartTracking(ServerPlayerEntity playerEntity, int tracker) {
//
//    }

    /**
     * To be called from server side
     * @param tracker
     */
    public void addTracker(CustomDataTracker tracker) {

    }

    /**
     * To be called from server side
     * @param tracker
     */
    public void removeTracker(CustomDataTracker tracker) {
        // TODO send data to destroy the tracker to the tracking players.
    }
}
