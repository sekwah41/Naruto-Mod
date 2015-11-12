package com.sekwah.mods.narutomod.entitys.specials;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMovingBlock extends Entity {
    public int blockID;
    public int metadata;

    /**
     * Animation data
     */

    public double fromPosX;
    public double fromPosY;
    public double fromPosZ;

    public double toPosX;
    public double toPosY;
    public double toPosZ;

    public double transitionLength;

    /**
     * How long the block has been falling for.
     */
    public int fallTime;
    public boolean shouldDropItem;
    public NBTTagCompound fallingBlockTileEntityData;
    private boolean isBreakingAnvil;
    private boolean isAnvil;
    /**
     * Maximum amount of damage dealt to entities hit by falling block
     */
    private int fallHurtMax;
    /**
     * Actual damage dealt to entities hit by falling block
     */
    private float fallHurtAmount;

    public EntityMovingBlock(World par1World) {
        super(par1World);
        this.shouldDropItem = true;
        this.fallHurtMax = 40;
        this.fallHurtAmount = 2.0F;
    }

    public EntityMovingBlock(World par1World, double par2, double par4, double par6, int par8) {
        this(par1World, par2, par4, par6, par8, 0);
    }

    public EntityMovingBlock(World par1World, double par2, double par4, double par6, int par8, int par9) {
        super(par1World);
        this.shouldDropItem = true;
        this.fallHurtMax = 40;
        this.fallHurtAmount = 2.0F;
        this.blockID = par8;
        this.metadata = par9;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
        this.setPosition(par2, par4, par6);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = par2;
        this.prevPosY = par4;
        this.prevPosZ = par6;
    }

    // TODO get base code from falling block entity

    @Override
    protected void entityInit() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
        // TODO Auto-generated method stub

    }
}
