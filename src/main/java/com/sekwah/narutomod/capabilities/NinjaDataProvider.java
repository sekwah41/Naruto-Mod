package com.sekwah.narutomod.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NinjaDataProvider implements ICapabilitySerializable<INBT> {

    @CapabilityInject(INinjaData.class)
    public static final Capability<INinjaData> NINJA_DATA = null;

    private LazyOptional<INinjaData> instance = LazyOptional.of(NINJA_DATA::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == NINJA_DATA ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return NINJA_DATA.getStorage().writeNBT(NINJA_DATA, this.instance.orElse(null), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        NINJA_DATA.getStorage().readNBT(NINJA_DATA, this.instance.orElse(null), null, nbt);
    }
}
