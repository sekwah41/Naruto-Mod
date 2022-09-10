package com.sekwah.narutomod.capabilities.toggleabilitydata;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import com.sekwah.narutomod.registries.NarutoRegistries;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

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

    private final HashSet<ResourceLocation> abilities;

    public ToggleAbilityData(int size) {
        this.abilities = new HashSet<>(size);
    }

    public ToggleAbilityData() {
        this.abilities = new HashSet<>();
    }

    public boolean addAbility(ResourceLocation ability) {
        return this.abilities.add(ability);
    }

    public boolean addAbilityStarted(Player player, INinjaData ninjaData, Ability ability) {
        if (ability.activationType() == Ability.ActivationType.TOGGLE && ability.logInChat()) {
            player.displayClientMessage(Component.translatable("jutsu.toggle.enabled", Component.translatable(ability.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GREEN), true);
        }
        var registryName = NarutoRegistries.ABILITIES.getResourceKey(ability).orElse(null);
        if (registryName != null) {
            return this.addAbility(registryName.location());
        }
        return false;
    }

    public boolean removeAbilityEnded(Player player, INinjaData ninjaData, Ability ability) {
        if(ability instanceof Ability.HandleEnded endedAbility) endedAbility.handleAbilityEnded(player, ninjaData, 0);
        if (ability.activationType() == Ability.ActivationType.TOGGLE && ability.logInChat()) {
            player.displayClientMessage(Component.translatable("jutsu.toggle.disabled", Component.translatable(ability.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.RED), true);
        }
        var registryName = NarutoRegistries.ABILITIES.getResourceKey(ability).orElse(null);
        if (registryName != null) {
            return this.removeAbility(registryName.location());
        }
        return false;
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
        if (!(o instanceof ToggleAbilityData that)) return false;
        return abilities.equals(that.abilities);
    }
}
