package com.sekwah.sekclib.capabilitysync.capabilitysync;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

public class RegisterCapabilitySyncEvent extends Event implements IModBusEvent {

    /**
     *
     * @param resourceSyncName - This is needed to help sync with the client.
     * @param capability - The capability to pull the data from.
     * @param clazz - The class to pull the annotation data from.
     */
    public void registerPlayerCap(ResourceLocation resourceSyncName, Capability<?> capability, Class<?> clazz) {
        CapabilitySyncRegistry.registerPlayerCap(resourceSyncName, capability, clazz);
    }

}
