package com.sekwah.narutomod.abilities.utility;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;

/**
 * More of a slight speed boost than an actual dash
 */
public class WaterWalkAbility extends Ability implements Ability.Toggled {

    @Override
    public ActivationType activationType() {
        return ActivationType.TOGGLE;
    }

    @Override
    public long defaultCombo() {
        return 3;
    }
    private final int CHARKA_COOLDOWN = 15;

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        WaterChecks checks = this.checkSteadyNormalFastPush(player);

        if(player.getVehicle() != null) {
            player.displayClientMessage(Component.translatable("jutsu.fail.riding", Component.translatable("jutsu.waterwalk").withStyle(ChatFormatting.YELLOW)), true);
            return false;
        }

        float fullCost = 0;
        if (checks.pushUpFast) {
            fullCost += 1f;
        } else if (checks.steadyCheck) {
            fullCost += 0.12F;
        }
        if(ninjaData.getChakra() < fullCost) {
            player.displayClientMessage(Component.translatable("jutsu.fail.notenoughchakra", Component.translatable("jutsu.waterwalk").withStyle(ChatFormatting.YELLOW)), true);
            return false;
        }
        ninjaData.useChakra(fullCost, CHARKA_COOLDOWN);
        return true;
    }


    public SoundEvent castingSound() {
        return null;
    }

    public record WaterChecks(boolean steadyCheck, boolean pushUpFast, boolean pushUpNormal) {}

    public WaterChecks checkSteadyNormalFastPush(Player player) {


        int blockX = (int)Math.floor(player.getX());
        int blockZ = (int)Math.floor(player.getZ());
        final int block1 = (int) Math.round(player.getY() - 0.56f);
        boolean steadyCheck = triggerWaterWalk(player.level(), new BlockPos(blockX, block1, blockZ));

        int block2 = (int) Math.round(player.getY());
        int beforeBlock2 = (int) Math.round(player.yo);
        boolean pushUpFast = triggerWaterWalk(player.level(), new BlockPos(blockX, block2, blockZ));
        if(player.level().isClientSide() && player.yo > player.getY()) {
            boolean beforeYCheck = triggerWaterWalk(player.level(), new BlockPos(blockX, beforeBlock2, blockZ));
            if(!beforeYCheck && steadyCheck && player.yo - player.getY() < 0.9f) {
                Vec3 vec = player.getDeltaMovement();
                player.lerpMotion(vec.x(), 0, vec.z());
                player.setPos(player.getX(), block2 + 0.05f, player.getZ());
            } else {
                steadyCheck = false;
            }
        }

        int block3 = (int) Math.round(player.getY() - 0.47f);
        boolean pushUpNormal = triggerWaterWalk(player.level(), new BlockPos(blockX, block3, blockZ));

        return new WaterChecks(steadyCheck, pushUpFast, pushUpNormal);
    }

    private void updatePlayerMovement(Player player, INinjaData ninjaData) {

        // TODO rewrite as this is the old way of doing it ported over
        // TODO also check if the block is waterlogged and non solid
        WaterChecks checks = this.checkSteadyNormalFastPush(player);


        // TODO sort offset
        Vec3 vec = player.getDeltaMovement();
        double resultingYSpeed = vec.y();
        if (checks.pushUpFast) {
            resultingYSpeed += 0.2D;
            if (resultingYSpeed > 0.6D) {
                resultingYSpeed = 0.6D;
            }
        }
        else if (checks.pushUpNormal) {
            resultingYSpeed += 0.1D;
            if (resultingYSpeed > 0.2D) {
                resultingYSpeed = 0.2D;
            }
        } else if (checks.steadyCheck && resultingYSpeed < 0.0D) {
            resultingYSpeed = 0.0D;
            player.resetFallDistance();
            player.setOnGround(true);
            ninjaData.getDoubleJumpData().canDoubleJumpServer = true;
            if(player.isFallFlying()) {
                player.stopFallFlying();
            }
            // This adds the hand bobbing back to the player
            float f = (float)Math.min(0.1D, player.getDeltaMovement().horizontalDistance());
            player.bob += (f - player.bob) * 0.4F;
        }
        player.lerpMotion(vec.x(), resultingYSpeed, vec.z());

    }

    public boolean triggerWaterWalk(Level level, BlockPos blockPos) {
        FluidState fluidState = level.getFluidState(blockPos);
        BlockState blockState = level.getBlockState(blockPos);
        return (fluidState.is(Fluids.WATER) || fluidState.is(Fluids.FLOWING_WATER)) && !blockState.blocksMotion();
    }

    @Override
    public void performServer(Player player, INinjaData ninjaData, int ticksActive) {
        updatePlayerMovement(player, ninjaData);
    }

    @Override
    public void performToggleClient(Player player, INinjaData ninjaData) {
        updatePlayerMovement(player, ninjaData);
    }
}
