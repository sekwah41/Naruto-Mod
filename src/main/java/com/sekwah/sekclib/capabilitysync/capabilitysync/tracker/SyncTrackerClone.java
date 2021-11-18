package com.sekwah.sekclib.capabilitysync.capabilitysync.tracker;

/**
 * If this is not implemented for the tracker it will just straight up replace the value.
 */

/**
 * This is for where the updates may keep references to old versions and will always be equal unless this exists.
 * @param <T>
 */
public interface SyncTrackerClone<T> {
    T clone(T data);
}
