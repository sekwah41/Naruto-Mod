package com.sekwah.narutomod.entity.item;

import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.config.NarutoConfig;
import com.sekwah.narutomod.entity.NarutoDataSerialisers;
import com.sekwah.narutomod.entity.NarutoEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

import static com.sekwah.narutomod.block.NarutoBlockStates.HIDDEN;
import static net.minecraft.block.HorizontalFaceBlock.FACE;
import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class PaperBombEntity extends Entity {
    private static final DataParameter<Integer> DATA_FUSE_ID = EntityDataManager.defineId(PaperBombEntity.class, DataSerializers.INT);

    @Nullable
    public LivingEntity owner;

    private static final DataParameter<Direction> ROTATION = EntityDataManager.defineId(PaperBombEntity.class, NarutoDataSerialisers.BLOCK_DIRECTION);
    private static final DataParameter<AttachFace> VERT_ROT = EntityDataManager.defineId(PaperBombEntity.class, NarutoDataSerialisers.ATTACH_FACE);
    protected static final DataParameter<BlockPos> ORIGIN = EntityDataManager.defineId(PaperBombEntity.class, DataSerializers.BLOCK_POS);

    private int life = 80;

    private BlockPos anchorLoc;

    public BlockState renderBlockState = NarutoBlocks.PAPER_BOMB.get().defaultBlockState();

    public PaperBombEntity(EntityType<? extends PaperBombEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public PaperBombEntity(World worldIn, double x, double y, double z, LivingEntity igniter,
                           Direction direction, AttachFace face, BlockPos anchorTo) {
        this(worldIn, x, y, z, igniter);
        this.anchorLoc = anchorTo;
        this.setRotation(direction);
        this.setVertRotation(face);
        this.setAnchored(!this.isAnchoredBlockAir());
    }

    public PaperBombEntity(World worldIn, double x, double y, double z, LivingEntity owner) {
        this(NarutoEntities.PAPER_BOMB.get(), worldIn);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.owner = owner;
        this.setOrigin(this.getOrigin());
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_FUSE_ID, 80);
        this.entityData.define(ROTATION, Direction.NORTH);
        this.entityData.define(VERT_ROT, AttachFace.FLOOR);
        this.entityData.define(ORIGIN, BlockPos.ZERO);
    }

    public void setOrigin(BlockPos origin) {
        this.entityData.set(ORIGIN, origin);
    }

    public BlockPos getOrigin() {
        return this.entityData.get(ORIGIN);
    }

    public Direction getRotation() {
        return this.entityData.get(ROTATION);
    }

    public void setRotation(Direction rotation) {
        this.entityData.set(ROTATION, rotation);
    }

    public AttachFace getVertRotation() {
        return this.entityData.get(VERT_ROT);
    }

    public void setVertRotation(AttachFace rotation) {
        this.entityData.set(VERT_ROT, rotation);
    }

    @Override
    public void tick() {
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
        if (this.onGround) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
        }

        --this.life;
        if (this.life <= 0) {
            this.remove();
            if (!this.level.isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level.isClientSide) {
                if(this.random.nextFloat() < 0.3f) {
                    Vector3i dir = this.renderBlockState.getValue(HORIZONTAL_FACING).getNormal();

                    float yOffset = 0;
                    float dirMulti = 0.25f;

                    float randomOffset = (this.random.nextFloat() - 0.5f) * 0.25f;

                    switch (this.getVertRotation()) {
                        case FLOOR:
                            yOffset = 0.15f;
                            break;
                        case WALL:
                            yOffset = 0.58f;
                            dirMulti = -0.18f;
                            break;
                        case CEILING:
                            yOffset = 0.5f;
                            break;
                    }
                    this.level.addParticle(ParticleTypes.FLAME,
                            this.getX() + dir.getX() * dirMulti + dir.getZ() * randomOffset,
                            this.getY() + yOffset,
                            this.getZ() + dir.getZ() * dirMulti + dir.getX() * randomOffset, 0.0D, 0.0D, 0.0D);
                }
            }
        }

        if(!level.isClientSide()) {
            if(anchorLoc != null) {
                if (this.isAnchoredBlockAir()) {
                    this.setAnchored(false);
                }
            }
            if((anchorLoc == null && this.isNoGravity())
                    || (this.xo != this.getX()
                    || this.yo != this.getY()
                    || this.zo != this.getZ())) {
                this.setAnchored(false);
            }
        }
    }

    public boolean isAnchoredBlockAir() {
        BlockState state = this.level.getBlockState(anchorLoc);
        return state.getBlock().isAir(state, level, anchorLoc);
    }

    public void setAnchored(boolean anchored) {
        if(!anchored) {
            anchorLoc = null;
            this.setVertRotation(AttachFace.FLOOR);
        }
        this.setNoGravity(anchored);
    }


    public void onSyncedDataUpdated(DataParameter<?> key) {
        if (DATA_FUSE_ID.equals(key)) {
            this.life = this.getFuse();
        }
        else  if((ROTATION.equals(key) || VERT_ROT.equals(key)) && this.level.isClientSide) {
            this.renderBlockState = NarutoBlocks.PAPER_BOMB.get().defaultBlockState()
                    .setValue(HorizontalBlock.FACING, this.getRotation())
                    .setValue(HIDDEN, Boolean.FALSE)
                    .setValue(FACE, this.getVertRotation());
        }
    }

    public void setFuse(int p_184534_1_) {
        this.entityData.set(DATA_FUSE_ID, p_184534_1_);
        this.life = p_184534_1_;
    }

    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        p_213281_1_.putShort("Fuse", (short)this.getLife());
    }

    public int getLife() {
        return this.life;
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        this.setFuse(p_70037_1_.getShort("Fuse"));
    }

    protected void explode() {
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(),
                NarutoConfig.PAPERBOMB_EXPLOSION_RADIUS,
                NarutoConfig.PAPERBOMB_BLOCK_DAMAGE ? Explosion.Mode.BREAK : Explosion.Mode.NONE);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
