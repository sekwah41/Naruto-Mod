package sekwah.mods.narutomod.entitys.jutsuprojectiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.List;

public class EntityFlameFireball extends EntityFireball {
    public float explosionPower = 1;

    public int life = 50;
    public float fireballGrowth = 0;
    public float fireballMaxGrowth = 120;
    public float fireballRotation;
    private int rainFizz = 20;

    public EntityFlameFireball(World par1World) {
        super(par1World);
        this.fireballRotation = this.rand.nextInt(100);
        this.setSize(1.0F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public EntityFlameFireball(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World, par2, par4, par6, par8, par10, par12);
    }

    public EntityFlameFireball(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
        super(par1World, par2EntityLivingBase, par3, par5, par7);
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        int flameRaduis = 6;
        for (int i = 0; i < 200; ++i) {
            double d0 = this.rand.nextGaussian() * 0.5D;
            double d1 = this.rand.nextGaussian() * 0.5D;
            double d2 = this.rand.nextGaussian() * 0.5D;
            this.worldObj.spawnParticle("flame", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + this.height / 2 + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
        }
        this.worldObj.spawnParticle("flame", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + this.height / 2 + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, 0, 0, 0);
        if (!this.worldObj.isRemote) {
            if (par1MovingObjectPosition.entityHit != null) {
                /**if(par1MovingObjectPosition.entityHit instanceof EntityWaterBullet){
                 this.playSound("random.fizz", 1.0F, 1.0F);
                 for (int i = 0; i < 40; ++i)
                 {
                 double d0 = this.rand.nextGaussian() * 0.02D;
                 double d1 = this.rand.nextGaussian() * 0.02D;
                 double d2 = this.rand.nextGaussian() * 0.02D;
                 this.worldObj.spawnParticle("explode", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + this.height / 2 + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
                 }
                 par1MovingObjectPosition.entityHit.setDead();
                 this.setDead();
                 }*/

                par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 3.0F);
                par1MovingObjectPosition.entityHit.setFire(3);
            }

            List entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.setBounds(this.posX - flameRaduis, this.posY - flameRaduis, this.posZ - flameRaduis, this.posX + flameRaduis, this.posY + flameRaduis, this.posZ + flameRaduis));


            for (Object entityObj : entities) {
                if (entityObj instanceof Entity) {
                    Entity entity = (Entity) entityObj;

                    double differenceX = this.posX - entity.posX;
                    double differenceY = this.posY - entity.posY;
                    double differenceZ = this.posZ - entity.posZ;
                    double distance = Math.sqrt(differenceX * differenceX + differenceY * differenceY + differenceZ * differenceZ);
                    System.out.println((16 / ((distance * 2))));
                    entity.setFire((int) (16 / ((distance * 2))));
                }
            }

            for (int x = (int) this.posX - 2; x < (int) this.posX + 1; x++) {
                for (int y = (int) this.posY - 1; y < (int) this.posY + 2; y++) {
                    for (int z = (int) this.posZ - 1; z < (int) this.posZ + 2; z++) {
                        Block block = this.worldObj.getBlock(x, y, z);
                        if (block == Blocks.air) {
                            this.worldObj.setBlock(x, y, z, Blocks.fire);
                        }
                    }
                }
            }

            this.worldObj.newExplosion(null, this.posX, this.posY, this.posZ, explosionPower, true, false);
            this.setDead();
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        return false;
    }

    public void onUpdate() {
        super.onUpdate();


        // the growth code is now handled by the renderer along with the rotation
        // ++this.innerRotation;

        //if(this.fireballMaxGrowth > this.fireballGrowth){
        //	++this.fireballGrowth;
        //}

        if (this.rainFizz-- >= 0) {

            rainFizz = 20;
            double d1 = this.rand.nextGaussian() * 0.02D;
            if (this.isWet()) {
                this.worldObj.spawnParticle("explode", this.posX, this.posY + this.height / 2, this.posZ, 0, d1, 0);
            } else {
                this.worldObj.spawnParticle("largesmoke", this.posX, this.posY + this.height / 2, this.posZ, 0, d1, 0);
            }
        }
        if (this.isWet()) {
            life--;
        }

        if (this.inWater) {
            this.playSound("random.fizz", 1.0F, 1.0F);
            for (int i = 0; i < 40; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("explode", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + this.height / 2 + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
            }
            this.setDead();
        }
        if (life-- <= 0) {
            this.playSound("random.fizz", 1.0F, 1.0F);
            for (int i = 0; i < 40; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("explode", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + this.height / 2 + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
            }
            this.setDead();
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
    }
}
