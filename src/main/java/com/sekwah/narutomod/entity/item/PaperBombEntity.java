package com.sekwah.narutomod.entity.item;

import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.config.NarutoConfig;
import com.sekwah.narutomod.entity.NarutoDataSerialisers;
import com.sekwah.narutomod.entity.NarutoEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import javax.annotation.Nullable;
import static com.sekwah.narutomod.block.NarutoBlockStates.HIDDEN;

public class PaperBombEntity extends Entity {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(PaperBombEntity.class, EntityDataSerializers.INT);

    @Nullable
    public LivingEntity owner;

    private static final EntityDataAccessor<Direction> ROTATION = SynchedEntityData.defineId(PaperBombEntity.class, NarutoDataSerialisers.BLOCK_DIRECTION);
    private static final EntityDataAccessor<AttachFace> VERT_ROT = SynchedEntityData.defineId(PaperBombEntity.class, NarutoDataSerialisers.ATTACH_FACE);
    protected static final EntityDataAccessor<BlockPos> ORIGIN = SynchedEntityData.defineId(PaperBombEntity.class, EntityDataSerializers.BLOCK_POS);

    private int life = 80;

    private BlockPos anchorLoc;

    public BlockState renderBlockState = NarutoBlocks.PAPER_BOMB.get().defaultBlockState();

    public PaperBombEntity(EntityType<? extends PaperBombEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public PaperBombEntity(Level worldIn, double x, double y, double z, LivingEntity igniter,
                           Direction direction, AttachFace face, BlockPos anchorTo) {
        this(worldIn, x, y, z, igniter);
        this.anchorLoc = anchorTo;
        this.setRotation(direction);
        this.setVertRotation(face);
        this.setAnchored(!this.isAnchoredBlockAir());
    }

    public PaperBombEntity(Level worldIn, double x, double y, double z, LivingEntity owner) {
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
            this.remove(false);
            if (!this.level.isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level.isClientSide) {
                if(this.random.nextFloat() < 0.3f) {
                    Vec3i dir = this.renderBlockState.getValue(BlockStateProperties.HORIZONTAL_FACING).getNormal();

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
        return state.isAir();
    }

    public void setAnchored(boolean anchored) {
        if(!anchored) {
            anchorLoc = null;
            this.setVertRotation(AttachFace.FLOOR);
        }
        this.setNoGravity(anchored);
    }


    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
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
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putShort("Fuse", (short)this.getLife());
    }

    public int getLife() {
        return this.life;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.setFuse(tag.getShort("Fuse"));
    }

    protected void explode() {
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(),
                NarutoConfig.paperbombExplosionRadius,
                NarutoConfig.paperbombBlockDamage ? Explosion.BlockInteraction.BREAK : Explosion.BlockInteraction.NONE);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
