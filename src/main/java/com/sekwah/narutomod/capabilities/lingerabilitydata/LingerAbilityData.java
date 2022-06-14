package com.sekwah.narutomod.capabilities.lingerabilitydata;

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
public class LingerAbilityData {

    private final HashSet<ResourceLocation> abilities;

    public LingerAbilityData(int size) {
        this.abilities = new HashSet<>(size);
    }

    public LingerAbilityData() {
        this.abilities = new HashSet<>();
    }

    public boolean addAbility(Ability ability) {
        var abilityKey = NarutoRegistries.ABILITIES.getResourceKey(ability);
        return abilityKey.filter(abilityResourceKey -> this.abilities.add(abilityResourceKey.location())).isPresent();
    }

    public boolean addAbilityStarted(Player player, INinjaData ninjaData, Ability ability) {
        if (ability.activationType() == Ability.ActivationType.TOGGLE && ability.logInChat()) {
            player.displayClientMessage(Component.translatable("jutsu.toggle.enabled", Component.translatable(ability.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GREEN), false);
        }
        return this.addAbility(ability);
    }

    public boolean removeAbilityEnded(Player player, INinjaData ninjaData, Ability ability) {
        if(ability instanceof Ability.HandleEnded endedAbility) endedAbility.handleAbilityEnded(player, ninjaData, 0);
        if (ability.activationType() == Ability.ActivationType.TOGGLE && ability.logInChat()) {
            player.displayClientMessage(Component.translatable("jutsu.toggle.disabled", Component.translatable(ability.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.RED), false);
        }
        return this.removeAbility(ability);
    }

    public boolean removeAbility(Ability ability) {
        var resourceKey = NarutoRegistries.ABILITIES.getResourceKey(ability);
        return resourceKey.filter(abilityResourceKey -> this.abilities.remove(abilityResourceKey.location())).isPresent();
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
        if (!(o instanceof LingerAbilityData)) return false;
        LingerAbilityData that = (LingerAbilityData) o;
        return abilities.equals(that.abilities);
    }
}
