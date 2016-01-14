package sekwah.mods.narutomod.entitys.jutsuprojectiles;

import sekwah.mods.narutomod.common.NarutoDamageSources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class EntityWaterBullet extends Entity // TODO actually code the water bullet and change it from being a fireball
{
    public EntityLivingBase shootingEntity;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    public int life = 50;
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private boolean inGround;
    private int ticksAlive;

    //public float explosionPower = 1;
    private int ticksInAir;

    //private int rainFizz = 20;

    //public int fireballGrowth = 0;

    //public int fireballMaxGrowth = 120;

    //public int fireballRotation;

    public EntityWaterBullet(World par1World) {
        super(par1World);
        this.setSize(0.3F, 0.3F);
    }

    @SideOnly(Side.CLIENT)
    public EntityWaterBullet(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
        super(par1World);
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(par2, par4, par6, this.rotationYaw, this.rotationPitch);
        this.setPosition(par2, par4, par6);
        double d6 = (double) MathHelper.sqrt_double(par8 * par8 + par10 * par10 + par12 * par12);
        this.accelerationX = par8 / d6 * 0.1D;
        this.accelerationY = par10 / d6 * 0.1D;
        this.accelerationZ = par12 / d6 * 0.1D;
    }

    public EntityWaterBullet(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
        super(par1World);
        this.shootingEntity = par2EntityLivingBase;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = this.motionY = this.motionZ = 0.0D;
        par3 += this.rand.nextGaussian() * 0.4D;
        par5 += this.rand.nextGaussian() * 0.4D;
        par7 += this.rand.nextGaussian() * 0.4D;
        double d3 = (double) MathHelper.sqrt_double(par3 * par3 + par5 * par5 + par7 * par7);
        this.accelerationX = par3 / d3 * 0.1D;
        this.accelerationY = par5 / d3 * 0.1D;
        this.accelerationZ = par7 / d3 * 0.1D;
    }

    protected void entityInit() {
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        int splashRaduis = 3;

        for (int i = 0; i < 100; ++i) {
            this.worldObj.spawnParticle("splash", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + this.height / 2 + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, 0, 0, 0);
        }


        for (int i = 0; i < 100; ++i) {
            this.worldObj.spawnParticle("splash", this.posX + (double) (this.rand.nextFloat() * splashRaduis * 2.0F) - splashRaduis, this.posY + (double) (this.rand.nextFloat() * splashRaduis * 2) - splashRaduis, this.posZ + (double) (this.rand.nextFloat() * splashRaduis * 2.0F) - splashRaduis, 0, 0, 0);
        }

        this.playSound("game.neutral.swim.splash", 0.6F, 1.0F);

        if (!this.worldObj.isRemote) {

            // TODO FINISH THIS NOWWWW!!!!!!!
            //List entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.posX - 4, this.posY - 4, this.posZ - 4).expand(2.0D, 2.0D,2.0D));
            List entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.setBounds(this.posX - splashRaduis, this.posY - splashRaduis, this.posZ - splashRaduis, this.posX + splashRaduis, this.posY + splashRaduis, this.posZ + splashRaduis));

            if (par1MovingObjectPosition.entityHit != null) {
                /**if(par1MovingObjectPosition.entityHit instanceof EntityFlameFireball){
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

                //par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, shootingEntity), 4.0F);
                par1MovingObjectPosition.entityHit.attackEntityFrom(NarutoDamageSources.causeWaterBullet(this, shootingEntity), 4.0F);
                par1MovingObjectPosition.entityHit.extinguish();
            }

            for (Object entityObj : entities) {
                if (entityObj instanceof EntityLiving) {
                    EntityLiving entity = (EntityLiving) entityObj;
                    if (entity != par1MovingObjectPosition.entityHit) {
                        double differenceX = this.posX - entity.posX;
                        double differenceY = this.posY - entity.posY;
                        double differenceZ = this.posZ - entity.posZ;
                        double distance = Math.sqrt(differenceX * differenceX + differenceY * differenceY + differenceZ * differenceZ);
                        entity.attackEntityFrom(NarutoDamageSources.causeWaterBullet(this, shootingEntity), (int) (8 / ((distance * 2) + 1)));
                        entity.extinguish();
                    }
                }
            }

            for (int x = (int) this.posX - 2; x < (int) this.posX + 1; x++) {
                for (int y = (int) this.posY - 1; y < (int) this.posY + 2; y++) {
                    for (int z = (int) this.posZ - 1; z < (int) this.posZ + 2; z++) {
                        Block block = this.worldObj.getBlock(x, y, z);
                        if (block == Blocks.fire) {
                            this.worldObj.setBlockToAir(x, y, z);
                        }
                    }
                }
            }

            //this.worldObj.newExplosion((Entity)null, this.posX, this.posY, this.posZ, explosionPower, true, false);
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
        //super.onUpdate();
        this.updateMovement();

        if (this.ticksInAir == 4) {
            this.playSound("game.neutral.swim", 0.4F, 1.0F);
        }

        // the growth code is now handled by the renderer along with the rotation
        // ++this.innerRotation;

        //if(this.fireballMaxGrowth > this.fireballGrowth){
        //	++this.fireballGrowth;
        //}

        //if(this.rainFizz -- >= 0){

        //rainFizz = 20;
        //double d1 = this.rand.nextGaussian() * 0.02D;
        //if(this.isWet()){
        //	this.worldObj.spawnParticle("explode", this.posX, this.posY + this.height / 2, this.posZ, 0, d1, 0);
        //}
        //else{
        //this.worldObj.spawnParticle("largesmoke", this.posX, this.posY + this.height / 2, this.posZ, 0, d1, 0);
        //}
        //}

        if (this.isBurning()) {
            this.setFire(0);
            life--;
            //this.playSound("random.fizz", 1.0F, 1.0F);
            //for (int i = 0; i < 40; ++i)
            //{
            //double d0 = this.rand.nextGaussian() * 0.02D;
            //double d1 = this.rand.nextGaussian() * 0.02D;
            //double d2 = this.rand.nextGaussian() * 0.02D;
            //this.worldObj.spawnParticle("explode", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + this.height / 2 + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
            //}
            //this.setDead();
        }
        if (life-- <= 0) {
            this.playSound("game.neutral.swim.splash", 0.6F, 1.0F);
            for (int i = 0; i < 20; ++i) {
                this.worldObj.spawnParticle("splash", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + this.height / 2 + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, 0, 0, 0);
            }
            this.setDead();
        }
    }

    private void updateMovement() {
        if (!this.worldObj.isRemote && (this.shootingEntity != null && this.shootingEntity.isDead || !this.worldObj.blockExists((int) this.posX, (int) this.posY, (int) this.posZ))) {
            this.setDead();
        } else {
            super.onUpdate();

            if (this.inGround) {
                Block i = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);

                if (i == this.inTile) {
                    ++this.ticksAlive;

                    if (this.ticksAlive == 600) {
                        this.setDead();
                    }

                    return;
                }

                this.inGround = false;
                this.motionX *= (double) (this.rand.nextFloat() * 0.2F);
                this.motionY *= (double) (this.rand.nextFloat() * 0.2F);
                this.motionZ *= (double) (this.rand.nextFloat() * 0.2F);
                this.ticksAlive = 0;
                this.ticksInAir = 0;
            } else {
                ++this.ticksInAir;
            }

            Vec3 vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            Vec3 vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
            vec3 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            vec31 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null) {
                vec31 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }

            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;

            for (int j = 0; j < list.size(); ++j) {
                Entity entity1 = (Entity) list.get(j);

                if (entity1.canBeCollidedWith() && (!entity1.isEntityEqual(this.shootingEntity) || this.ticksInAir >= 25)) {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double) f, (double) f, (double) f);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

                    if (movingobjectposition1 != null) {
                        double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D) {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }

            if (movingobjectposition != null) {
                this.onImpact(movingobjectposition);
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float) (Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) + 90.0F;

            for (this.rotationPitch = (float) (Math.atan2((double) f1, this.motionY) * 180.0D / Math.PI) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float f2 = this.getMotionFactor();

            if (this.isInWater()) {
                for (int k = 0; k < 4; ++k) {
                    float f3 = 0.25F;
                    this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double) f3, this.posY - this.motionY * (double) f3, this.posZ - this.motionZ * (double) f3, this.motionX, this.motionY, this.motionZ);
                }

                f2 = 0.8F;
            }

            this.motionX += this.accelerationX;
            this.motionY += this.accelerationY;
            this.motionZ += this.accelerationZ;
            this.motionX *= (double) f2;
            this.motionY *= (double) f2;
            this.motionZ *= (double) f2;
            this.worldObj.spawnParticle("splash", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected float getMotionFactor() {
        return 0.95F;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setShort("xTile", (short) this.xTile);
        par1NBTTagCompound.setShort("yTile", (short) this.yTile);
        par1NBTTagCompound.setShort("zTile", (short) this.zTile);
        par1NBTTagCompound.setByte("inTile", (byte) Block.getIdFromBlock(this.inTile));
        par1NBTTagCompound.setByte("inGround", (byte) (this.inGround ? 1 : 0));
        par1NBTTagCompound.setTag("direction", this.newDoubleNBTList(this.motionX, this.motionY, this.motionZ));
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        this.xTile = par1NBTTagCompound.getShort("xTile");
        this.yTile = par1NBTTagCompound.getShort("yTile");
        this.zTile = par1NBTTagCompound.getShort("zTile");
        this.inTile = Block.getBlockById(par1NBTTagCompound.getByte("inTile") & 255);
        this.inGround = par1NBTTagCompound.getByte("inGround") == 1;

        if (par1NBTTagCompound.hasKey("direction")) {
            NBTTagList nbttaglist = par1NBTTagCompound.getTagList("direction", 6);
            this.motionX = nbttaglist.func_150309_d(0);
            this.motionY = nbttaglist.func_150309_d(1);
            this.motionZ = nbttaglist.func_150309_d(2);
        } else {
            this.setDead();
        }
    }
}
