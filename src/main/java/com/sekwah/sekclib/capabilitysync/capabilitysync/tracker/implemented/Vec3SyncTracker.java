package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.implemented;

import com.sekwah.sekclib.capabilitysync.capabilitysync.tracker.SyncTrackerSerializer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;

/**
 * Sync structure
 * boolean isNull
 * double posX
 * double posX
 * double posX
 */
public class Vec3SyncTracker implements SyncTrackerSerializer<Vec3> {

    @Override
    public void encode(Vec3 objectToSend, FriendlyByteBuf outBuffer) {
        outBuffer.writeDouble(objectToSend.x);
        outBuffer.writeDouble(objectToSend.y);
        outBuffer.writeDouble(objectToSend.z);
    }

    @Override
    public Vec3 decode(FriendlyByteBuf inBuffer) {
        return new Vec3(inBuffer.readDouble(), inBuffer.readDouble(), inBuffer.readDouble());
    }
}
