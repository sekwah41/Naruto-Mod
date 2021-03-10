package com.sekwah.narutomod.entity.item;

import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.config.NarutoConfig;
import com.sekwah.narutomod.entity.NarutoDataSerialisers;
import com.sekwah.narutomod.entity.NarutoEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static com.sekwah.narutomod.block.NarutoBlockStates.HIDDEN;
import static net.minecraft.block.HorizontalFaceBlock.FACE;

public class PaperBombEntity extends TNTEntity {

    private static final DataParameter<Direction> ROTATION = EntityDataManager.defineId(PaperBombEntity.class, NarutoDataSerialisers.BLOCK_DIRECTION);
    private static final DataParameter<AttachFace> VERT_ROT = EntityDataManager.defineId(PaperBombEntity.class, NarutoDataSerialisers.ATTACH_FACE);
    protected static final DataParameter<BlockPos> ORIGIN = EntityDataManager.defineId(PaperBombEntity.class, DataSerializers.BLOCK_POS);

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
        super.defineSynchedData();
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
        super.tick();
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

    @Override
    public void onSyncedDataUpdated(DataParameter<?> key) {
        super.onSyncedDataUpdated(key);
        if((ROTATION.equals(key) || VERT_ROT.equals(key)) && this.level.isClientSide) {
            this.renderBlockState = NarutoBlocks.PAPER_BOMB.get().defaultBlockState()
                    .setValue(HorizontalBlock.FACING, this.getRotation())
                    .setValue(HIDDEN, Boolean.FALSE)
                    .setValue(FACE, this.getVertRotation());
        }
    }

    @Override
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
