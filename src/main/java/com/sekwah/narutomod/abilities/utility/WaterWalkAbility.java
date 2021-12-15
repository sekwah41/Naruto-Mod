package com.sekwah.narutomod.abilities.utility;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

/**
 * More of a slight speed boost than an actual dash
 */
public class WaterWalkAbility extends Ability {

    @Override
    public ActivationType activationType() {
        return ActivationType.TOGGLE;
    }

    @Override
    public long defaultCombo() {
        return 3;
    }

    private final float CHAKRA_COST = 0.1F;
    private final int CHARKA_COOLDOWN = 15;

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        System.out.println("Cost Walk???");
        if(ninjaData.getChakra() < CHAKRA_COST) {
            player.displayClientMessage(new TranslatableComponent("jutsu.fail.notenoughchakra", new TranslatableComponent("jutsu.waterwalk").withStyle(ChatFormatting.YELLOW)), true);
            return false;
        }
        ninjaData.useChakra(CHAKRA_COST, CHARKA_COOLDOWN);
        return true;
    }

    @Override
    public void handleAbilityEnded(Player player, INinjaData ninjaData) {
    }

    @Override
    public void perform(Player player, INinjaData ninjaData, int chargeAmount) {
        // TODO validation that the player is above water
        player.fallDistance = 0.0F;
    }

    @Override
    public void performToggleClient(Player player, INinjaData ninjaData) {

        // TODO rewrite as this is the old way of doing it ported over
        // TODO also check if the block is waterlogged and non solid
        final int block1 = (int) Math.round(player.getY() - 0.96f);
        Block feetBlock = player.level.getBlockState(new BlockPos((int) player.getX(), block1, (int) player.getZ())).getBlock();

        int block2 = (int) Math.round(player.getY() - 0.4f);
        Block midBlock = player.level.getBlockState(new BlockPos((int) player.getX(), block2, (int) player.getZ())).getBlock();

        int block3 = (int) Math.round(player.getY() - 0.87f);
        Block headBlock = player.level.getBlockState(new BlockPos((int) player.getX(), block3, (int) player.getZ())).getBlock();


        // TODO sort offset
        Vec3 vec = player.getDeltaMovement();
        double resultingYSpeed = vec.y();
        if (midBlock == Blocks.WATER) {
            resultingYSpeed += 0.2D;
            if (resultingYSpeed > 0.6D) {
                resultingYSpeed = 0.6D;
            }
        }
        else if (headBlock == Blocks.WATER) {
            resultingYSpeed += 0.1D;
            if (resultingYSpeed > 0.2D) {
                resultingYSpeed = 0.2D;
            }
        } else if ((feetBlock == Blocks.WATER) && resultingYSpeed < 0.0D) {
            resultingYSpeed = 0.0D;
            player.setOnGround(true);
        }
        player.lerpMotion(vec.x(), resultingYSpeed, vec.z());
    }
}
