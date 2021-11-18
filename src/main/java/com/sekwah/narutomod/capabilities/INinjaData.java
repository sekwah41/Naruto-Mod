package com.sekwah.narutomod.capabilities;

import com.sekwah.narutomod.abilities.Ability;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
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

    ResourceLocation currentlyChanneledAbility();
    void setCurrentlyChanneledAbility(ResourceLocation ability);

    /**
     * Handle basic regen and updates for a tick.
     */
    void updateChakra();
}
