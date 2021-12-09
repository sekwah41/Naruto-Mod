package com.sekwah.narutomod.capabilities.toggleabilitydata;

import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Objects;

/**
 * Tracks ability references and other information for the server side.
 *
 * On client side the ticks tracked will be since the client knows the ability is active.
 * Do not use it for reliable triggering for effects or behavior when the user is not the player itssself.
 *
 * Try to keep as much behavior solely to server side as you can.
 */
public class ToggleAbilityData {

    private HashSet<ResourceLocation> abilities;

    public ToggleAbilityData(int size) {
        this.abilities = new HashSet<>(size);
    }

    public ToggleAbilityData() {
        this.abilities = new HashSet<>();
    }

    public boolean addAbility(ResourceLocation ability) {
        return this.abilities.add(ability);
    }

    public boolean removeAbility(ResourceLocation ability) {
        return this.abilities.remove(ability);
    }

    public HashSet<ResourceLocation> getAbilitiesHashSet() {
        return abilities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abilities);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToggleAbilityData)) return false;
        ToggleAbilityData that = (ToggleAbilityData) o;
        return abilities.equals(that.abilities);
    }
}
