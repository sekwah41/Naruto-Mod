package com.sekwah.narutomod.capabilities;

import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public interface INinjaData extends INBTSerializable<Tag> {
    float getChakra();
    float getStamina();
    void setChakra(float chakra);
    void setStamina(float stamina);
}
