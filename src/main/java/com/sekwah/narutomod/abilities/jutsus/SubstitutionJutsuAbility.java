package com.sekwah.narutomod.abilities.jutsus;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import com.sekwah.narutomod.entity.SubstitutionLogEntity;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class SubstitutionJutsuAbility extends Ability implements Ability.Channeled {

    public static int MAX_MARKER_DISTANCE = 70;

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
        if (ninjaData.getSubstitutionCount() < 1 && chargeAmount == -1) {
            player.displayClientMessage(new TranslatableComponent("jutsu.fail.notenoughcharges", new TranslatableComponent(this.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)), true);
            player.playNotifySound( NarutoSounds.JUTSU_FAIL.get(), SoundSource.PLAYERS, 0.5f, 1.0f);
            return false;
        }
        return true;
    }

    @Override
    public String getTranslationKey(INinjaData ninjaData, int ticksActive) {
        if(ticksActive <= 1) {
            return this.getRegistryName().toString();
        } else {
            return this.getRegistryName().toString() + ".mark";
        }
    }

    /**
     * Due to the nature of this ability all costs and other things will be handled here.
     */
    @Override
    public void performServer(Player player, INinjaData ninjaData, int ticksActive) {
        if(ticksActive == -1) {
            // Activate
            ninjaData.useSubstitution(1);
            Vec3 loc = ninjaData.getSubstitutionLoc();
            double distance = loc != null ? player.position().distanceTo(loc) : 0;
            if(loc != null && distance < 40 && player.level.dimension().location().equals(ninjaData.getSubstitutionDimension())) {
                spawnLogAt(player, player.position(), ninjaData);
                player.teleportTo(loc.x, loc.y - 2, loc.z);
                ninjaData.setSubstitutionLoc(null, null);
            } else {
                Vec3 originalPosition = player.position();
                if(this.randomTeleportPlayer(player, ninjaData)) {
                    spawnLogAt(player, originalPosition, ninjaData);
                } else {
                    // TODO add a failed teleport message or handle the tp logic in the calculation.
                }
            }
        } else {
            ninjaData.setSubstitutionLoc(player.position().add(0, 2, 0), player.level.dimension().location());
            // Mark
        }
    }

    public void spawnLogAt(Player player, Vec3 pos, INinjaData ninjaData) {
        ninjaData.setInvisibleTicks(5);
        player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 5, 0, false, false));
        SubstitutionLogEntity log = new SubstitutionLogEntity(player.level);
        log.setPos(pos.add(0, 1, 0));
        player.level.addFreshEntity(log);
        if(player.level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.CLOUD,
                    pos.x,
                    pos.y + (player.getBbHeight() / 2),
                    pos.z,
                    100,
                    0.5, 0.7, 0.5, 0);
        }
    }

    public boolean randomTeleportPlayer(Player player, INinjaData ninjaData) {
        for(int i = 0; i < 16; i++) {
            double x = player.getX() + (player.getRandom().nextDouble() - 0.5D) * 25.0D;
            double y = Mth.clamp(player.getY() + (double)(player.getRandom().nextInt(16) - 8), (double)player.level.getMinBuildHeight(), (double)(player.level.getMinBuildHeight() + ((ServerLevel)player.level).getLogicalHeight() - 1));
            double z = player.getZ() + (player.getRandom().nextDouble() - 0.5D) * 25.0D;
            if(Math.sqrt(player.distanceToSqr(x, y, z)) >= 10 && player.randomTeleport(x, y, z, false)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hideChannelMessages() {
        return false;
    }

    @Override
    public boolean useChargedMessages() {
        return true;
    }
}
