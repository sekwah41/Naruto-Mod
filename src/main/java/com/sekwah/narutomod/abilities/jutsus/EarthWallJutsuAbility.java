package com.sekwah.narutomod.abilities.jutsus;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.INinjaData;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EarthWallJutsuAbility extends Ability implements Ability.Cooldown {
    @Override
    public ActivationType activationType() {
        return ActivationType.INSTANT;
    }

    @Override
    public long defaultCombo() {
        return 312;
    }

    @Override
    public boolean handleCost(Player player, INinjaData ninjaData, int chargeAmount) {
        var blocksToPlace = calculateWall(player);
        float chakraCost = blocksToPlace.size() * 0.7f;
        if(ninjaData.getChakra() < chakraCost) {
            player.displayClientMessage(Component.translatable("jutsu.fail.notenoughchakra", Component.translatable(this.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)), true);
            return false;
        }
        if(blockHit(player).getType() == HitResult.Type.MISS) {
            player.displayClientMessage(Component.translatable("jutsu.fail.noblocktarget", Component.translatable(this.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)), true);
            return false;
        }
        if(blocksToPlace.size() == 0) {
            player.displayClientMessage(Component.translatable("jutsu.fail.noblocks", Component.translatable(this.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)), true);
            return false;
        }
        // TODO check if any entities would actually be placed!
        ninjaData.useChakra(chakraCost, 30);
        return true;
    }

    public ArrayList<BlockPlacement> calculateWall(Player player) {
        ArrayList<BlockPlacement> placeBlocks = new ArrayList<>();
        var blockHit = blockHit(player);
        if(blockHit(player).getType() == HitResult.Type.MISS) {
            return placeBlocks;
        }

        WallDir walldir = getWallDirection(player.getYHeadRot());

        int wallWidth = 5;
        int maxHeight = 5;
        int maxOffset = 5;


        BlockPos targetLocation = blockHit.getBlockPos();

        int yOffset = getYOffset(player.level, targetLocation, maxOffset);

        for(int offset = -(wallWidth-1)/2; offset <= (wallWidth-1)/2; offset++) {
            BlockPos offsetPos = blockHit.getBlockPos().offset(walldir.xDir * offset, yOffset, walldir.zDir * offset);
            int posOffset = getYOffset(player.level, offsetPos, maxOffset);
            if(posOffset == 0) {
                posOffset = -getYOffsetNegative(player.level, offsetPos, maxOffset);
            }
            if(posOffset == -maxOffset) {
                continue;
            }
            var blockToPlace = pillar(player.level, offsetPos.above(posOffset), maxHeight - (int) Math.ceil(posOffset * 0.40));
            placeBlocks.addAll(blockToPlace);
        }
        return placeBlocks;
    }

    @Override
    public void performServer(Player player, INinjaData ninjaData, int ticksActive) {
        var placeBlocks = calculateWall(player);
        for (BlockPlacement blockPlacement : placeBlocks) {
            player.level.setBlockAndUpdate(blockPlacement.pos, blockPlacement.state);
        }
    }

    private final List<Material> blockList = Arrays.asList(Material.DIRT, Material.STONE, Material.GRASS, Material.SAND);

    private int getYOffset(Level level, BlockPos pos, int maxOffset) {

        for(int i = 0; i < maxOffset; i++) {
            if(canPlaceEarthBlock(level, pos.above(i))) {
                return i;
            }
        }

        return 0;
    }

    /**
     * This is for checking in case the current block already can be placed at.
     * @param level
     * @param pos
     * @param maxOffset
     * @return if it is the same as max offset then it is not a valid position
     */
    private int getYOffsetNegative(Level level, BlockPos pos, int maxOffset) {
        int lastMatched = maxOffset;
        boolean alreadyMatched = false;
        for(int i = 0; i <= maxOffset; i++) {
            if(canPlaceEarthBlock(level, pos.below(i))) {
                lastMatched = i;
                alreadyMatched = true;
            }
            else if(alreadyMatched) {
                return lastMatched;
            }
        }

        return lastMatched;
    }

    private boolean canPlaceEarthBlock(Level level, BlockPos pos) {
        var blockAtPos = level.getBlockState(pos);
        return blockAtPos.getMaterial().isReplaceable();
    }

    public record BlockPlacement(BlockPos pos, BlockState state) {

    }

    public ArrayList<BlockPlacement> pillar(Level level, BlockPos pos, int height) {
        ArrayList<BlockPlacement> placeBlocks = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            var newBlockPos = pos.above(i);
            if(canPlaceEarthBlock(level, newBlockPos)) {
                var block = level.getBlockState(newBlockPos.below(height));
                if(block.is(Blocks.GRASS_BLOCK) && i != height - 1) {
                    block = Blocks.DIRT.defaultBlockState();
                }
                if(!blockList.contains(block.getMaterial())) {
                    height--;
                    i--;
                    continue;
                }
                /*if(!blockList.contains(block.getMaterial())) {
                    block = Blocks.DIRT.defaultBlockState();
                }*/
                placeBlocks.add(new BlockPlacement(newBlockPos, block));
            }
        }
        return placeBlocks;
    }

    @Override
    public int getCooldown() {
        return 3 * 20;
    }

    public record WallDir(int xDir, int zDir) {

    }

    public WallDir getWallDirection(float playerLookAngle) {
        short xDir = 0;
        short zDir = 0;
        int dir = (int) (Math.floor((double) (((playerLookAngle + 180) * 8F) / 360F) + 0.5D)) % 4;
        switch (dir) {
            case 0: // Z facing north/south
                xDir = 1;
                break;
            case 1: // Diagonal south-east/north-west
                xDir = 1;
                zDir = 1;
                break;
            case 2: // X facing east/west
                zDir = 1;
                break;
            case 3: // Diagonal south-west/north-east
                xDir = 1;
                zDir = -1;
                break;
        }

        return new WallDir(xDir, zDir);
    }




    public BlockHitResult blockHit(Player player) {
        var eyePos = player.getEyePosition();
        var lookVec = player.getLookAngle();
        int range = 40;

        return player.level.clip(new ClipContext(eyePos, eyePos.add(lookVec.multiply(range, range, range)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));
    }
}
