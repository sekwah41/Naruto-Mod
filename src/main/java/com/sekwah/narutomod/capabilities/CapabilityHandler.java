package com.sekwah.narutomod.capabilities;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHandler {

    public static void init() {
        CapabilityManager.INSTANCE.register(INinjaData.class, new NinjaDataStorage(), NinjaData::new);
    }

}
