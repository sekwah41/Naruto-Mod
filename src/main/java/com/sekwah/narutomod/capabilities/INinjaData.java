package com.sekwah.narutomod.capabilities;

import com.sekwah.narutomod.abilities.Ability;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public interface INinjaData extends INBTSerializable<Tag> {
    float getChakra();
    float getMaxChakra();
    float getStamina();
    float getMaxStamina();
    void setChakra(float amount);
    void setStamina(float amount);
    void useChakra(float amount, int cooldown);
    void useStamina(float amount, int cooldown);

    Ability currentlyChanneledAbility();
    void setCurrentlyChanneledAbility(Ability ability);

    /**
     * Handle basic regen and updates for a tick.
     */
    void updateChakra();
}
