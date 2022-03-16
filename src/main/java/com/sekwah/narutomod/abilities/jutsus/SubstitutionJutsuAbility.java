package com.sekwah.narutomod.abilities.jutsus;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class SubstitutionJutsuAbility extends Ability implements Ability.Channeled {
    @Override
    public ActivationType activationType() {
        return ActivationType.CHANNELED;
    }

    @Override
    public long defaultCombo() {
        return 12;
    }

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        return true;
    }

    /**
     * Due to the nature of this ability all costs and other things will be handled here.
     */
    @Override
    public void performServer(Player player, INinjaData ninjaData, int ticksActive) {
        if(ticksActive == 0) {
            player.displayClientMessage(new TranslatableComponent("jutsu.cast.substitution"), false);
            // Activate
            ninjaData.useSubstitution(1);
            Vec3 loc = ninjaData.getSubstitutionLoc();
            if(loc != null) {
                double distance = player.position().distanceTo(loc);
                if(distance < 40 && player.level.dimension().location().equals(ninjaData.getSubstitutionDimension())) {
                    player.teleportTo(loc.x, loc.y, loc.z);
                    ninjaData.setSubstitutionLoc(null, null);
                }
            }
        } else {
            player.displayClientMessage(new TranslatableComponent("jutsu.cast.substitution_mark"), false);
            ninjaData.setSubstitutionLoc(player.position(), player.level.dimension().location());
            // Mark
        }
    }

    @Override
    public boolean hideChannelMessages() {
        return true;
    }
}
