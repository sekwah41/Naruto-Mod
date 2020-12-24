package com.sekwah.narutomod.entity.item;

import com.sekwah.narutomod.entity.NarutoEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class PaperBombEntity extends TNTEntity {

    private static final DataParameter<Byte> ROTATION = EntityDataManager.createKey(PaperBombEntity.class, DataSerializers.BYTE);
    private static final DataParameter<Byte> VERT_ROT = EntityDataManager.createKey(PaperBombEntity.class, DataSerializers.BYTE);
    protected static final DataParameter<BlockPos> ORIGIN = EntityDataManager.createKey(PaperBombEntity.class, DataSerializers.BLOCK_POS);

    public PaperBombEntity(EntityType<? extends PaperBombEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public PaperBombEntity(World worldIn, double x, int y, double z, LivingEntity igniter) {
        this(NarutoEntities.PAPER_BOMB.get(), worldIn);
        this.setPosition(x, y, z);
        double d0 = worldIn.rand.nextDouble() * (double)((float)Math.PI * 2F);
        this.setMotion(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(80);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.tntPlacedBy = igniter;
        this.setOrigin(this.getPosition());
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(ROTATION, (byte)0);
        this.dataManager.register(VERT_ROT, (byte)0);
        this.dataManager.register(ORIGIN, BlockPos.ZERO);
    }

    public void setOrigin(BlockPos origin) {
        this.dataManager.set(ORIGIN, origin);
    }

    @OnlyIn(Dist.CLIENT)
    public BlockPos getOrigin() {
        return this.dataManager.get(ORIGIN);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        //compound.putBoolean("Sheared", this.getSheared());
        //compound.putByte("Color", (byte)this.getFleeceColor().getId());
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        //this.setSheared(compound.getBoolean("Sheared"));
        //this.setFleeceColor(DyeColor.byId(compound.getByte("Color")));
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
