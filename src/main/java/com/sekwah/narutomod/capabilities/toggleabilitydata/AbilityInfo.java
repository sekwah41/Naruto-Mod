package com.sekwah.narutomod.capabilities.toggleabilitydata;

import com.sekwah.narutomod.abilities.Ability;

import java.util.Objects;

/**
 * Tracks the ability and how long it has been active for (server side)
 */
public class AbilityInfo {

    public Ability ability;

    public int activeTime;

    /**
     * This stops two abilities with the same name being able to be toggled on.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(ability.getRegistryName());
    }
}
