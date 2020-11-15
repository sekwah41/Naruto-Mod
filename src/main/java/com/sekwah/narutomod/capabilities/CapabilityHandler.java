package com.sekwah.narutomod.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHandler {

    public static void register() {
        CapabilityManager.INSTANCE.register(INinjaData.class, NBTCapabilityStorage.create(CompoundNBT.class), NinjaData::new);
    }

}
