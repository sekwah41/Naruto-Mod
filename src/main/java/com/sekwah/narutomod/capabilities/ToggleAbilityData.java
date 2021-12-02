package com.sekwah.narutomod.capabilities;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class ToggleAbilityData {

    public HashSet<ResourceLocation> abilities;

    public ToggleAbilityData(int size) {
        this.abilities = new HashSet<>(size);
    }

    public ToggleAbilityData() {
        this.abilities = new HashSet<>();
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
