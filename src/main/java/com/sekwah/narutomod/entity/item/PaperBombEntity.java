package com.sekwah.narutomod.entity.item;

import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.entity.NarutoDataSerialisers;
import com.sekwah.narutomod.entity.NarutoEntities;
import com.sekwah.narutomod.util.StateUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import static com.sekwah.narutomod.block.NarutoBlockStates.HIDDEN;
import static net.minecraft.block.HorizontalFaceBlock.FACE;

public class PaperBombEntity extends TNTEntity {

    private static final DataParameter<Direction> ROTATION = EntityDataManager.createKey(PaperBombEntity.class, NarutoDataSerialisers.BLOCK_DIRECTION);
    private static final DataParameter<AttachFace> VERT_ROT = EntityDataManager.createKey(PaperBombEntity.class, NarutoDataSerialisers.ATTACH_FACE);
    protected static final DataParameter<BlockPos> ORIGIN = EntityDataManager.createKey(PaperBombEntity.class, DataSerializers.BLOCK_POS);

    protected static final DataParameter<Boolean> ANCHORED = EntityDataManager.createKey(PaperBombEntity.class, DataSerializers.BOOLEAN);

    private static final String ROTATION_NBT = "Rotation";
    private static final String VERT_ROTATION_NBT = "Vert_Rotation";

    private BlockPos anchorLoc;

    public BlockState renderBlockState = NarutoBlocks.PAPER_BOMB.get().getDefaultState();

    public PaperBombEntity(EntityType<? extends PaperBombEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public PaperBombEntity(World worldIn, double x, int y, double z, LivingEntity igniter,
                           Direction direction, AttachFace face, BlockPos anchorTo) {
        this(worldIn, x, y, z, igniter);
        this.anchorLoc = anchorTo;
        this.setRotation(direction);
        this.setVertRotation(face);
    }

    public PaperBombEntity(World worldIn, double x, int y, double z, LivingEntity igniter) {
        this(NarutoEntities.PAPER_BOMB.get(), worldIn);
        this.setPosition(x, y, z);
        double d0 = worldIn.rand.nextDouble() * (double)((float)Math.PI * 2F);
        //this.setMotion(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80000);
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
        this.dataManager.register(ANCHORED, false);
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

    public boolean getAnchored() {
        return this.dataManager.get(ANCHORED);
    }

    public void setAnchored(boolean anchored) {
        this.dataManager.set(ANCHORED, anchored);
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
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putByte(ROTATION_NBT, (byte) this.getRotation().getIndex());
        compound.putByte(VERT_ROTATION_NBT, StateUtils.faceToByte(this.getVertRotation()));
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setRotation(Direction.byHorizontalIndex(compound.getByte(ROTATION_NBT)));
        this.setVertRotation(StateUtils.byteToFace(compound.getByte(VERT_ROTATION_NBT)));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
