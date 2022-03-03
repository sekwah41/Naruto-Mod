package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker;

import net.minecraft.network.FriendlyByteBuf;

public interface SyncTrackerSerializer<T> {
    /**
     * Needs to also be able to encode and decode null objects
     * @param objectToSend
     * @param outBuffer
     */
    void encode(T objectToSend, FriendlyByteBuf outBuffer);

    T decode(FriendlyByteBuf inBuffer);
}
