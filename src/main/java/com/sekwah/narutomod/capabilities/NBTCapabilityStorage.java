package com.sekwah.narutomod.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;

public class NBTCapabilityStorage<T extends INBT, U extends INBTSerializable<T>> implements Capability.IStorage<U> {
    private final Class<T> nbtClass;

    private NBTCapabilityStorage(Class<T> nbtClass) {
        this.nbtClass = nbtClass;
    }

    public static <V extends INBT, W extends INBTSerializable<V>> NBTCapabilityStorage<V, W> create(Class<V> tClass) {
        return new NBTCapabilityStorage<>(tClass);
    }

    public static <V extends INBTSerializable<CompoundNBT>> NBTCapabilityStorage<CompoundNBT, V> create() {
        return new NBTCapabilityStorage<>(CompoundNBT.class);
    }

    @Nullable
    @Override
    public INBT writeNBT(Capability<U> capability, U instance, Direction side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<U> capability, U instance, Direction side, INBT nbt) {
        if (nbtClass.isInstance(nbt)) {
            instance.deserializeNBT(nbtClass.cast(nbt));
        }
    }
}
