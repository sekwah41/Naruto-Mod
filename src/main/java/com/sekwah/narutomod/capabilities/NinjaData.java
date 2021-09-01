package com.sekwah.narutomod.capabilities;

import com.sekwah.sekclib.capabilitysync.annotation.Sync;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NinjaData implements INinjaData, ICapabilityProvider {

    @Sync
    private float chakra;

    @Sync
    private float stamina;

    private static final String CHAKRA_TAG = "chakra";
    private static final String STAMINA_TAG = "stamina";

    private final LazyOptional<INinjaData> holder = LazyOptional.of(() -> this);

    @Override
    public float getChakra() {
        return this.chakra;
    }

    @Override
    public float getStamina() {
        return this.stamina;
    }

    @Override
    public void setChakra(float chakra) {
        this.chakra = chakra;
    }

    @Override
    public void setStamina(float stamina) {
        this.stamina = stamina;
    }

    @Override
    public Tag serializeNBT() {
        final CompoundTag nbt = new CompoundTag();
        nbt.putFloat(CHAKRA_TAG, this.chakra);
        nbt.putFloat(STAMINA_TAG, this.stamina);
        return nbt;
    }

    @Override
    public void deserializeNBT(Tag tag) {
        if(tag instanceof CompoundTag compoundTag) {
            this.chakra = compoundTag.getFloat(CHAKRA_TAG);
            this.stamina = compoundTag.getFloat(STAMINA_TAG);
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return NinjaCapabilityHandler.NINJA_DATA.orEmpty(cap, holder);
    }
}
