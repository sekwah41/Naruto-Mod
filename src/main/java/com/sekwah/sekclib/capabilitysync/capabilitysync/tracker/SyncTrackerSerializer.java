package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker;

import net.minecraft.network.FriendlyByteBuf;

public interface SyncTrackerSerializer {
    void encode(Object objectToSend, FriendlyByteBuf outBuffer);

    Object decode(FriendlyByteBuf inBuffer);
}
