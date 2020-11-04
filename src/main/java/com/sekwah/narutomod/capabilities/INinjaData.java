package com.sekwah.narutomod.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface INinjaData extends INBTSerializable<CompoundNBT> {
    float getChakra();
    float getStamina();
    void setChakra(float chakra);
    void setStamina(float stamina);

}
