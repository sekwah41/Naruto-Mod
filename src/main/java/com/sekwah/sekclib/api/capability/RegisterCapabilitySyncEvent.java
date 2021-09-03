package com.sekwah.sekclib.api.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.IModBusEvent;

public class RegisterCapabilitySyncEvent extends Event implements IModBusEvent {

    public void registerPlayerCap(Capability<?> capability, Class<?> clazz) {
        CapabilitySyncRegistry.registerPlayerCap(capability, clazz);
    }

}
