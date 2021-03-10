package com.sekwah.narutomod.capabilities;

import net.minecraft.nbt.CompoundNBT;

public class NinjaData implements INinjaData {

    private float chakra;
    private float stamina;

    private static final String CHAKRA_NBT = "CHAKRA";
    private static final String STAMINA_NBT = "STAMINA";

    public NinjaData() {
        System.out.println("HELLO");
    }

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
    public CompoundNBT serializeNBT() {
        final CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat(CHAKRA_NBT, this.chakra);
        nbt.putFloat(STAMINA_NBT, this.stamina);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.chakra = nbt.getFloat(CHAKRA_NBT);
        this.stamina = nbt.getFloat(STAMINA_NBT);
    }
}
