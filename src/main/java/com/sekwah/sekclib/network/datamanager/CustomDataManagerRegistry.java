package com.sekwah.sekclib.network.datamanager;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Where to register the behavior handlers for the custom data managers.
 *
 * You can handle them manually yourself however this will handle the packets and other basic stuff just so its one
 * less thing to worry about.
 *
 *
 *
 */
public class CustomDataManagerRegistry {

    /**
     * This will by synced on server joining using a packet.
     */
    private final Int2ObjectMap<CustomDataManager<?>> dataManagerIDs = new Int2ObjectOpenHashMap<>();

    /**
     * Used server side to determine the correct packet channels.
     */
    private static final AtomicInteger NEXT_MANAGER_ID = new AtomicInteger();

    /**
     * Clean up all data objects when a player is disconnected.
     */
    public void handlePlayerDisconnect() {

    }


    public enum TrackingTypes {
        /**
         * Use start and stop tracking events as well as {@link net.minecraftforge.fmllegacy.network.PacketDistributor#TRACKING_ENTITY_AND_SELF}
         */
        ENTITY,
        /**
         * Look for events to bind to {@link net.minecraftforge.fml.network.PacketDistributor#DIMENSION}
         *
         * May need to look a bit more at the capability handlers for that.
         */
        //DIMENSION,
        /**
         *
         */
        //CHUNK,
        //CUSTOM,
    }

}
