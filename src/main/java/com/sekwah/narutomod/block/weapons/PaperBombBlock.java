package com.sekwah.narutomod.block.weapons;

import com.sekwah.narutomod.block.NarutoBlockStates;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

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
        this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(HIDDEN, Boolean.valueOf(false)).with(FACE, AttachFace.WALL));
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
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
        System.out.println("I AM HERE THINGS BE PLACING");
        worldIn.getPendingBlockTicks().scheduleTick(new BlockPos(pos), this, TRANSPARENT_DELAY);
    }


    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, HIDDEN, FACE);
    }

}
