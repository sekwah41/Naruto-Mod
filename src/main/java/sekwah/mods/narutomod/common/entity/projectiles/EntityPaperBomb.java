package sekwah.mods.narutomod.common.entity.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sekwah.mods.narutomod.settings.NarutoSettings;

public class EntityPaperBomb extends Entity {
    /**
     * How long the fuse is
     */
    public int fuse;
    private EntityLivingBase placedBy;

    public EntityPaperBomb(World par1World) {
        super(par1World);
        this.fuse = 80;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
    }

    public EntityPaperBomb(World par1World, double par2, double par4, double par6, EntityLivingBase par8EntityLivingBase) {
        this(par1World);
        this.setPosition(par2, par4, par6);
        float var8 = (float) (Math.random() * Math.PI * 2.0D);
        this.fuse = 80;
        this.prevPosX = par2;
        this.prevPosY = par4;
        this.prevPosZ = par6;
        this.placedBy = par8EntityLivingBase;
    }

    protected void entityInit() {
        this.getDataWatcher().addObject(10, new Byte((byte) 0));
    }

    public int getRotation() {
        return this.dataWatcher.getWatchableObjectByte(10);
    }

    public void setRotation(int par1) {
        this.dataWatcher.updateObject(10, Byte.valueOf((byte) par1));
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking() {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround) {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
            this.motionY *= -0.5D;
        }

        if (this.fuse-- <= 0) {
            this.setDead();

            if (!this.worldObj.isRemote) {
                this.explode();
            }
        } else {
            double firepos = Math.random();
            if (this.getRotation() == 0) {
                this.worldObj.spawnParticle("flame", this.posX - 0.2 + (firepos * 0.4D), this.posY - 0.25D, this.posZ + 0.27, 0.0D, 0.0D, 0.0D);
            } else {
                this.worldObj.spawnParticle("flame", this.posX + 0.27, this.posY - 0.25D, this.posZ - 0.2 + (firepos * 0.4D), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void explode() {
        float var1 = 4.0F;
        if(!this.worldObj.isRemote) {
            this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, var1, !NarutoSettings.nonDestructiveMode);
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setByte("Fuse", (byte) this.fuse);
        par1NBTTagCompound.setByte("Rotation", (byte) this.getRotation());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        this.fuse = par1NBTTagCompound.getByte("Fuse");
        this.setRotation(par1NBTTagCompound.getByte("Rotation"));
    }

    public float getShadowSize() {
        return 0.0F;
    }

    /**
     * returns null or the entityliving it was placed or ignited by
     */
    public EntityLivingBase placedBy() {
        return this.placedBy;
    }
}
