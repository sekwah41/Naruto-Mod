package com.sekwah.narutomod.capabilities;

import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class NinjaDataStorage implements Capability.IStorage<INinjaData> {

    @Nullable
    @Override
    public INBT writeNBT(Capability<INinjaData> capability, INinjaData instance, Direction side) {
        return null;
       /* return StringNBT.valueOf(instance.getChakra());*/
    }

    @Override
    public void readNBT(Capability<INinjaData> capability, INinjaData instance, Direction side, INBT nbt) {
        /*if(nbt instanceof StringNBT) {
            instance.setSkin(nbt.getString());
        }*/
    }
}
