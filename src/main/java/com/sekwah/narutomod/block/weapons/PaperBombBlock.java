package com.sekwah.narutomod.block.weapons;

import com.sekwah.narutomod.block.NarutoBlockStates;
import com.sekwah.narutomod.entity.item.PaperBombEntity;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class PaperBombBlock extends FaceAttachedHorizontalDirectionalBlock {

    public static final BooleanProperty HIDDEN = NarutoBlockStates.HIDDEN;

    protected static final double HEIGHT = 2;
    protected static final double LENGTH = 8;
    protected static final double WIDTH = 6;

    protected static final int TRANSPARENT_DELAY = 5 * 20;

    protected static final VoxelShape AABB_CEILING_X = Block.box(8.0D - (LENGTH / 2D), 16 - HEIGHT, 8.0D - (WIDTH / 2D), 8.0D + (LENGTH / 2D), 16D, 8.0D + (WIDTH / 2D));
    protected static final VoxelShape AABB_CEILING_Z = Block.box(8.0D - (WIDTH / 2D), 16 - HEIGHT, 8.0D - (LENGTH / 2D), 8.0D + (WIDTH / 2D), 16D, 8.0D + (LENGTH / 2D));
    protected static final VoxelShape AABB_FLOOR_X = Block.box(8.0D - (LENGTH / 2D), 0, 8.0D - (WIDTH / 2D), 8.0D + (LENGTH / 2D), HEIGHT, 8.0D + (WIDTH / 2D));
    protected static final VoxelShape AABB_FLOOR_Z = Block.box(8.0D - (WIDTH / 2D), 0, 8.0D - (LENGTH / 2D), 8.0D + (WIDTH / 2D), HEIGHT, 8.0D + (LENGTH / 2D));
    protected static final VoxelShape AABB_NORTH = Block.box(8.0D - (WIDTH / 2D), 8.0D - (LENGTH / 2D), 16.0D - HEIGHT, 8.0D + (WIDTH / 2D), 8.0D + (LENGTH / 2D), 16.0D);
    protected static final VoxelShape AABB_SOUTH = Block.box(8.0D - (WIDTH / 2D), 8.0D - (LENGTH / 2D), 0, 8.0D + (WIDTH / 2D), 8.0D + (LENGTH / 2D), HEIGHT);
    protected static final VoxelShape AABB_WEST = Block.box(16.0D - HEIGHT, 8.0D - (LENGTH / 2D), 8.0D - (WIDTH / 2D), 16.0D, 8.0D + (LENGTH / 2D), 8.0D + (WIDTH / 2D));
    protected static final VoxelShape AABB_EAST = Block.box(0D, 8.0D - (LENGTH / 2D), 8.0D - (WIDTH / 2D), HEIGHT, 8.0D + (LENGTH / 2D), 8.0D + (WIDTH / 2D));

    public PaperBombBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HIDDEN, Boolean.valueOf(false)).setValue(FACE, AttachFace.FLOOR));
    }

    @Override
    public void wasExploded(Level levelIn, BlockPos pos, Explosion explosionIn) {
        spawnPaperbomb(null, levelIn, pos, explosionIn.getIndirectSourceEntity(), true);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 100;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        switch(state.getValue(FACE)) {
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
    public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
        if (!state.getValue(HIDDEN)) {
            worldIn.setBlock(pos, state.setValue(HIDDEN, Boolean.TRUE), 3);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (worldIn.hasNeighborSignal(pos)) {
            onCaughtFire(state, worldIn, pos, null, null);
            worldIn.removeBlock(pos, false);
        }

    }

    @Override
    public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, worldIn, pos, oldState, isMoving);
        worldIn.scheduleTick(new BlockPos(pos), this, TRANSPARENT_DELAY);
    }

    @Override
    public void onCaughtFire(BlockState state, Level worldIn, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        spawnPaperbomb(state, worldIn, pos, igniter);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if(!worldIn.isClientSide) {
            spawnPaperbomb(state, worldIn, pos, player);
            worldIn.removeBlock(pos, false);
        }
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if(!worldIn.isClientSide) {
            spawnPaperbomb(state, worldIn, pos, entityIn instanceof LivingEntity ? (LivingEntity) entityIn : null, true);
            worldIn.removeBlock(pos, false);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HIDDEN, FACE);
    }

    public void spawnPaperbomb(@Nullable BlockState state, Level worldIn, BlockPos pos, @Nullable LivingEntity igniter) {
        spawnPaperbomb(state, worldIn, pos, igniter, false);
    }


    @Override
    public void handlePrecipitation(BlockState blockState, Level level, BlockPos pos, Biome.Precipitation precipitation) {
        if (precipitation == Biome.Precipitation.RAIN && level instanceof ServerLevel serverLevel) {
            level.destroyBlock(pos, true);
        }
    }

    public void spawnPaperbomb(@Nullable BlockState state, Level worldIn, BlockPos pos, @Nullable LivingEntity igniter, boolean shortFuse) {
        if (!worldIn.isClientSide) {
            Direction dir = state != null ? state.getValue(FACING) : Direction.NORTH;
            Vec3i dirVec = dir.getNormal();
            AttachFace face = state != null ? state.getValue(FACE) : AttachFace.FLOOR;

            BlockPos attachBlock;

            double xOffset = 0.5D;
            double yOffset = 0;
            double zOffset = 0.5D;

            if(face.equals(AttachFace.CEILING)) {
                yOffset += 0.5;

                attachBlock = pos.above();
            }
            else if(face.equals(AttachFace.WALL)) {
                attachBlock = pos.subtract(dirVec);
                yOffset += 0.25;
                xOffset -= 0.25D * dirVec.getX();
                zOffset -= 0.25D * dirVec.getZ();
            }
            else {
                attachBlock = pos.below();
            }


            PaperBombEntity paperBombEntity = new PaperBombEntity(worldIn,
                    (double)pos.getX() + xOffset, (double)pos.getY() + yOffset,(double)pos.getZ() + zOffset,
                    igniter, dir, face, attachBlock);
            worldIn.addFreshEntity(paperBombEntity);

            if(shortFuse) {
                paperBombEntity.setFuse((short)(paperBombEntity.getFuse() / 32));
            }
            else {
                worldIn.playSound(null, paperBombEntity.getX(), paperBombEntity.getY(), paperBombEntity.getZ(), NarutoSounds.SIZZLE.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

}
