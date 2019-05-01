package sekwah.mods.narutomod.common.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySubstitutionLog extends EntityCreature {
    public int destuctionTime = 0;


    private int lifetime = 0; // how long they live for, base time

    public EntitySubstitutionLog(World par1World) {
        super(par1World);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0D);
    }

    protected void entityInit() {
        super.entityInit();

        this.getDataWatcher().addObject(12, Byte.valueOf((byte) 0));
        this.getDataWatcher().addObject(13, Byte.valueOf((byte) 0));
        this.getDataWatcher().addObject(14, Byte.valueOf((byte) 0));
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    public int getTotalArmorValue() {

        return 0;
    }

    protected void dropEquipment(boolean par1, int par2) {

    }


    public void setVelocity(double par1, double par3, double par5) {
        this.motionX = par1 * 2.5;
        this.motionY = par3;
        this.motionZ = par5 * 2.5;
    }

    public void onLivingUpdate() {
        lifetime--;

        if (lifetime <= 0) { // how many ticks they last
            this.damageEntity(DamageSource.magic, 30);
        }
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild()) {
            float var1 = this.getBrightness(1.0F);
        }

        super.onLivingUpdate();
    }

    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
    {
        if (lifetime >= 0)
        {
            return false;
        }
        else
        {
            this.setBeenAttacked();
            return false;
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return "";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound() {
        return "";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return "";
    }

    /**
     * handles entity death timer, experience orb and particle creation
     */
    protected void onDeathUpdate() {
        ++this.destuctionTime;

        if (this.destuctionTime == 1) {
            for (int i = 0; i < 2; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("explode", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
            }
        } else if (this.destuctionTime == 20) {

            for (int i = 0; i < 20; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("explode", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
            }

            this.playSound("narutomod:jutsusounds.clone_poof", 0.15F, 1.0F);
            this.setDead();
            // add spawn log code and add rotation and velocitys
        }
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected void dropFewItems(boolean par1, int par2) {

    }

    protected void dropRareDrop(int par1) {

    }
}
