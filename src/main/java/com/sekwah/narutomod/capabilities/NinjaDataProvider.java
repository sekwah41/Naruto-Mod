package com.sekwah.narutomod.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NinjaDataProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundNBT> {

    private final INinjaData ninjaData = NarutoCapabilities.NINJA_DATA.getDefaultInstance();

    private final LazyOptional<INinjaData> optional = LazyOptional.of(() -> ninjaData);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == NarutoCapabilities.NINJA_DATA ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return ninjaData.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        ninjaData.deserializeNBT(nbt);
    }
}
