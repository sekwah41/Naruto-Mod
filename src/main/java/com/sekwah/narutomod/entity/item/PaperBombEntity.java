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

    private static final DataParameter<Direction> ROTATION = EntityDataManager.createKey(PaperBombEntity.class, NarutoDataSerialisers.BLOCK_DIRECTION);
    private static final DataParameter<AttachFace> VERT_ROT = EntityDataManager.createKey(PaperBombEntity.class, NarutoDataSerialisers.ATTACH_FACE);
    protected static final DataParameter<BlockPos> ORIGIN = EntityDataManager.createKey(PaperBombEntity.class, DataSerializers.BLOCK_POS);

    private BlockPos anchorLoc;

    public BlockState renderBlockState = NarutoBlocks.PAPER_BOMB.get().getDefaultState();

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

    public PaperBombEntity(World worldIn, double x, double y, double z, LivingEntity igniter) {
        this(NarutoEntities.PAPER_BOMB.get(), worldIn);
        this.setPosition(x, y, z);
        double d0 = worldIn.rand.nextDouble() * (double)((float)Math.PI * 2F);
        //this.setMotion(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.tntPlacedBy = igniter;
        this.setOrigin(this.getPosition());
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(ROTATION, Direction.NORTH);
        this.dataManager.register(VERT_ROT, AttachFace.FLOOR);
        this.dataManager.register(ORIGIN, BlockPos.ZERO);
    }

    public void setOrigin(BlockPos origin) {
        this.dataManager.set(ORIGIN, origin);
    }

    public BlockPos getOrigin() {
        return this.dataManager.get(ORIGIN);
    }

    public Direction getRotation() {
        return this.dataManager.get(ROTATION);
    }

    public void setRotation(Direction rotation) {
        this.dataManager.set(ROTATION, rotation);
    }

    public AttachFace getVertRotation() {
        return this.dataManager.get(VERT_ROT);
    }

    public void setVertRotation(AttachFace rotation) {
        this.dataManager.set(VERT_ROT, rotation);
    }

    @Override
    public void tick() {
        super.tick();
        if(!world.isRemote()) {
            if(anchorLoc != null) {
                if (this.isAnchoredBlockAir()) {
                    this.setAnchored(false);
                }
            }
            if((anchorLoc == null && this.hasNoGravity())
                    || (this.prevPosX != this.getPosX()
                    || this.prevPosY != this.getPosY()
                    || this.prevPosZ != this.getPosZ())) {
                this.setAnchored(false);
            }
        }
    }

    public boolean isAnchoredBlockAir() {
        BlockState state = this.world.getBlockState(anchorLoc);
        return state.getBlock().isAir(state, world, anchorLoc);
    }

    public void setAnchored(boolean anchored) {
        if(!anchored) {
            anchorLoc = null;
            this.setVertRotation(AttachFace.FLOOR);
        }
        this.setNoGravity(anchored);
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        super.notifyDataManagerChange(key);
        if((ROTATION.equals(key) || VERT_ROT.equals(key)) && this.world.isRemote) {
            this.renderBlockState = NarutoBlocks.PAPER_BOMB.get().getDefaultState()
                    .with(HorizontalBlock.HORIZONTAL_FACING, this.getRotation())
                    .with(HIDDEN, Boolean.valueOf(false))
                    .with(FACE, this.getVertRotation());
        }
    }

    @Override
    protected void explode() {
        this.world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(),
                NarutoConfig.PAPERBOMB_EXPLOSION_RADIUS,
                NarutoConfig.PAPERBOMB_BLOCK_DAMAGE ? Explosion.Mode.BREAK : Explosion.Mode.NONE);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
