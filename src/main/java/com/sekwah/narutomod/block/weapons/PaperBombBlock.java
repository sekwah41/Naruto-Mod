package com.sekwah.narutomod.block.weapons;

import com.sekwah.narutomod.block.NarutoBlockStates;
import com.sekwah.narutomod.entity.item.PaperBombEntity;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFaceBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class PaperBombBlock extends HorizontalFaceBlock {

    public static final BooleanProperty HIDDEN = NarutoBlockStates.HIDDEN;

    protected static final double HEIGHT = 2;
    protected static final double LENGTH = 8;
    protected static final double WIDTH = 6;

    protected static final int TRANSPARENT_DELAY = 5 * 20;

    protected static final VoxelShape AABB_CEILING_X = Block.makeCuboidShape(8.0D - (LENGTH / 2D), 16, 8.0D - (WIDTH / 2D), 8.0D + (LENGTH / 2D), 16D - HEIGHT, 8.0D + (WIDTH / 2D));
    protected static final VoxelShape AABB_CEILING_Z = Block.makeCuboidShape(8.0D - (WIDTH / 2D), 16, 8.0D - (LENGTH / 2D), 8.0D + (WIDTH / 2D), 16D - HEIGHT, 8.0D + (LENGTH / 2D));
    protected static final VoxelShape AABB_FLOOR_X = Block.makeCuboidShape(8.0D - (LENGTH / 2D), 0, 8.0D - (WIDTH / 2D), 8.0D + (LENGTH / 2D), HEIGHT, 8.0D + (WIDTH / 2D));
    protected static final VoxelShape AABB_FLOOR_Z = Block.makeCuboidShape(8.0D - (WIDTH / 2D), 0, 8.0D - (LENGTH / 2D), 8.0D + (WIDTH / 2D), HEIGHT, 8.0D + (LENGTH / 2D));
    protected static final VoxelShape AABB_NORTH = Block.makeCuboidShape(8.0D - (WIDTH / 2D), 8.0D - (LENGTH / 2D), 16.0D - HEIGHT, 8.0D + (WIDTH / 2D), 8.0D + (LENGTH / 2D), 16.0D);
    protected static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(8.0D - (WIDTH / 2D), 8.0D - (LENGTH / 2D), HEIGHT, 8.0D + (WIDTH / 2D), 8.0D + (LENGTH / 2D), 0.0D);
    protected static final VoxelShape AABB_WEST = Block.makeCuboidShape(16.0D - HEIGHT, 8.0D - (LENGTH / 2D), 8.0D - (WIDTH / 2D), 16.0D, 8.0D + (LENGTH / 2D), 8.0D + (WIDTH / 2D));
    protected static final VoxelShape AABB_EAST = Block.makeCuboidShape(HEIGHT, 8.0D - (LENGTH / 2D), 8.0D - (WIDTH / 2D), 0D, 8.0D + (LENGTH / 2D), 8.0D + (WIDTH / 2D));

    // TODO interaction when exploded due to another explosion
    public PaperBombBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(HIDDEN, Boolean.valueOf(false)).with(FACE, AttachFace.FLOOR));
    }

    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        spawnPaperbomb(null, worldIn, pos, explosionIn.getExplosivePlacedBy(), true);
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction direction = state.get(HORIZONTAL_FACING);
        switch(state.get(FACE)) {
            case FLOOR:
                if (direction.getAxis() == Direction.Axis.X) {
                    return AABB_FLOOR_X;
                }
                return AABB_FLOOR_Z;
            case WALL:
                switch(direction) {
                    case EAST:
                        return AABB_EAST;
                    case WEST:
                        return AABB_WEST;
                    case SOUTH:
                        return AABB_SOUTH;
                    case NORTH:
                    default:
                        return AABB_NORTH;
                }
            case CEILING:
            default:
                if (direction.getAxis() == Direction.Axis.X) {
                    return AABB_CEILING_X;
                } else {
                    return AABB_CEILING_Z;
                }
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!state.get(HIDDEN)) {
            worldIn.setBlockState(pos, state.with(HIDDEN, Boolean.valueOf(true)), 3);
        }
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (worldIn.isBlockPowered(pos)) {
            catchFire(state, worldIn, pos, null, null);
            worldIn.removeBlock(pos, false);
        }

    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
        worldIn.getPendingBlockTicks().scheduleTick(new BlockPos(pos), this, TRANSPARENT_DELAY);
    }

    public void catchFire(BlockState state, World worldIn, BlockPos pos, @Nullable net.minecraft.util.Direction face, @Nullable LivingEntity igniter) {
        spawnPaperbomb(state, worldIn, pos, igniter);
    }


    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote) {
            spawnPaperbomb(state, worldIn, pos, player);;
            worldIn.removeBlock(pos, false);
        }
        return ActionResultType.func_233537_a_(worldIn.isRemote);
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if(!worldIn.isRemote) {
            spawnPaperbomb(state, worldIn, pos, entityIn instanceof LivingEntity ? (LivingEntity) entityIn : null, true);
            worldIn.removeBlock(pos, false);
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, HIDDEN, FACE);
    }

    public void spawnPaperbomb(@Nullable BlockState state, World worldIn, BlockPos pos, @Nullable LivingEntity igniter) {
        spawnPaperbomb(state, worldIn, pos, igniter, false);
    }

    public void spawnPaperbomb(@Nullable BlockState state, World worldIn, BlockPos pos, @Nullable LivingEntity igniter, boolean shortFuse) {
        if (!worldIn.isRemote) {
            Direction dir = state != null ? state.get(HORIZONTAL_FACING) : Direction.NORTH;
            Vector3i dirVec = dir.getDirectionVec();
            AttachFace face = state != null ? state.get(FACE) : AttachFace.FLOOR;

            BlockPos attachBlock;

            double xOffset = 0.5D;
            double yOffset = 0;
            double zOffset = 0.5D;

            if(face.equals(AttachFace.CEILING)) {
                yOffset += 0.5;

                attachBlock = pos.up();
            }
            else if(face.equals(AttachFace.WALL)) {
                attachBlock = pos.subtract(dirVec);
                yOffset += 0.25;
                xOffset -= 0.25D * dirVec.getX();
                zOffset -= 0.25D * dirVec.getZ();
            }
            else {
                attachBlock = pos.down();
            }


            PaperBombEntity paperBombEntity = new PaperBombEntity(worldIn,
                    (double)pos.getX() + xOffset, (double)pos.getY() + yOffset,(double)pos.getZ() + zOffset,
                    igniter, dir, face, attachBlock);
            worldIn.addEntity(paperBombEntity);

            if(shortFuse) {
                paperBombEntity.setFuse((short)(paperBombEntity.getFuse() / 32));
            }
            else {
                worldIn.playSound(null, paperBombEntity.getPosX(), paperBombEntity.getPosY(), paperBombEntity.getPosZ(), NarutoSounds.SIZZLE.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

}
