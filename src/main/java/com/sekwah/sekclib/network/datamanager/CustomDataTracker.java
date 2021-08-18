package com.sekwah.sekclib.network.datamanager;

import com.google.common.collect.Maps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.SynchedEntityData;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Similar data and info to {@link SynchedEntityData} however repurposed so it can work for a lot more than just entities.
 *
 * @param <T>
 */
public abstract class CustomDataTracker<T> {

    private final Map<Integer, SynchedEntityData.DataItem<?>> entries = Maps.newHashMap();
    private boolean dirty;

    private T target;
    private CustomDataManager<T> customDataManager;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * Not sure how to cleanly call a Callback constructor with a single param.
     * @param target
     */
    protected void setTarget(T target) {
        this.target = target;
    }

    protected T getTarget() {
        return this.target;
    }

    public void setDataManager(CustomDataManager<T> customDataManager) {
        this.customDataManager = customDataManager;
    }

    /**
     * How often to update at minimum the data (in sync triggers)
      */
    public int updateRate = 5;

    private int ticksToNextUpdate = 0;

    public CustomDataTracker() {
    }

    public CustomDataTracker<T> setUpdateRate(int updateRate) {
        this.updateRate = updateRate;
        return this;
    }

    /**
     * Call on methods such as entity updates to signify when it should update.
     */
    public void sync() {
        if(--ticksToNextUpdate > 0 && !dirty) {
            return;
        }
        ticksToNextUpdate = updateRate;

        // TODO code to collect all of the dirty data and send it off.
        // TODO add data to register handlers for when updates need to be sent off.

    }

    /**
     * Write entries to a packet buffer
     * @param entriesIn
     * @param buf
     * @throws IOException
     */
    public static void writeEntries(List<SynchedEntityData.DataItem<?>> entriesIn, FriendlyByteBuf buf) throws IOException {

    }

    /**
     * Read entries from a packet buffer
     * @param buf
     * @return
     * @throws IOException
     */
    @Nullable
    public static List<SynchedEntityData.DataItem<?>> readEntries(FriendlyByteBuf buf) throws IOException {
        return Collections.emptyList();
    }
}
