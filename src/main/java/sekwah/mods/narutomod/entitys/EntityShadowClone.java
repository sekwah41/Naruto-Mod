package sekwah.mods.narutomod.entitys;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import sekwah.mods.narutomod.entitys.ai.EntityAIFollowMaster;
import sekwah.mods.narutomod.entitys.ai.EntityAIOwnerHurtByTarget;
import sekwah.mods.narutomod.entitys.ai.EntityAIOwnerHurtTarget;
import sekwah.mods.narutomod.entitys.projectiles.EntityKunai;
import sekwah.mods.narutomod.entitys.projectiles.EntityShuriken;
import sekwah.mods.narutomod.items.NarutoItems;

public class EntityShadowClone extends EntityMob {

    //public static final ResourceLocation locationStevePng = new ResourceLocation("textures/entity/steve.png");

    public double field_71091_bM;
    public double field_71096_bN;
    public double field_71097_bO;
    public double field_71094_bP;
    public double field_71095_bQ;
    public double field_71085_bR;
    private ResourceLocation locationSkin;
    private ResourceLocation locationCape;
    private int lifetime = 500; // how long they live for, base time
    private int poofTime = (int) (lifetime - Math.random() * 5);

    public EntityShadowClone(World par1World) {
        super(par1World);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIFollowMaster(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));

        this.setupCustomSkin();

    }

    protected void setupCustomSkin() {
        if (this.getCustomNameTag() != null && !this.getCustomNameTag().isEmpty()) {

            this.locationSkin = AbstractClientPlayer.locationStevePng;

            if (this.getCustomNameTag() != null && this.getCustomNameTag().length() > 0) {
                this.locationSkin = AbstractClientPlayer.getLocationSkin(this.getCustomNameTag());
                AbstractClientPlayer.getDownloadImageSkin(this.locationSkin, this.getCustomNameTag());
            }

            // this.locationCape = getLocationCape("cloaks/" + this.getCustomNameTag());
        }
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
    }

    protected void entityInit() {
        super.entityInit();

        this.getDataWatcher().addObject(12, Byte.valueOf((byte) 0));
        this.getDataWatcher().addObject(13, Byte.valueOf((byte) 0));
        this.getDataWatcher().addObject(14, Byte.valueOf((byte) 0));
    }

    public EntityLivingBase func_130012_q() {
        return this.worldObj.getPlayerEntityByName(this.getCustomNameTag());
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    public int getTotalArmorValue() {

        return 0;
    }

    protected void dropEquipment(boolean par1, int par2) {

    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (!super.attackEntityFrom(par1DamageSource, par2)) {
            return false;
        } else {
            EntityLivingBase entitylivingbase = this.getAttackTarget();

            System.out.println(entitylivingbase);

            if (entitylivingbase == null && this.getEntityToAttack() instanceof EntityLivingBase) {
                entitylivingbase = (EntityLivingBase) this.getEntityToAttack();
            }

            if (entitylivingbase == null && par1DamageSource.getEntity() instanceof EntityLivingBase) {
                entitylivingbase = (EntityLivingBase) par1DamageSource.getEntity();
            }

            this.setAttackTarget(entitylivingbase);

            return true;
        }
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    protected boolean isAIEnabled() {
        return true;
    }


    public void setVelocity(double par1, double par3, double par5) {
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;
    }

    /**
     * If Animal, checks if the age timer is negative
     */
    public boolean isChild() {
        return this.getDataWatcher().getWatchableObjectByte(12) == 1;
    }

    /**
     * Set whether this ninja is a child.
     */
    public void setChild(boolean par1) {
        this.getDataWatcher().updateObject(12, Byte.valueOf((byte) 1));
    }

    public void onLivingUpdate() {
        lifetime--;

        // leave this out if it doesnt work
        /**if(this.getAttackTarget() != null) {
         System.out.println(this.getAttackTarget().getDistanceToEntity(this));
         if(this.getAttackTarget().getDistanceToEntity(this) >= 0.5F){
         this.setSprinting(true);
         }
         else{
         this.setSprinting(false);
         }
         }
         else{
         this.setSprinting(false);
         }
         System.out.println("Swag" + this.isSprinting());*/

        if (lifetime == poofTime) {
            this.playSound("narutomod:jutsusounds.clone_poof", 0.15F, 1.0F);
        }

        if (lifetime <= 0) { // how many ticks they last
            this.damageEntity(DamageSource.magic, 30);
        }
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild()) {
            float var1 = this.getBrightness(1.0F);
        }

        // Add some simple raytracing or something to try and detect other entities in the way
        if (this.getAttackTarget() != null) {
            EntityLivingBase attackEntity = this.getAttackTarget();
            double distanceToEntity = Math.sqrt((this.posX * this.posX - attackEntity.posX * attackEntity.posX) + (this.posY * this.posY - attackEntity.posY * attackEntity.posY) + (this.posZ * this.posZ - attackEntity.posZ * attackEntity.posZ));
            if (distanceToEntity > 2 && distanceToEntity < 32 && this.getEquipmentInSlot(0) != null) {

                double var3 = attackEntity.posX - this.posX;
                double var5 = attackEntity.boundingBox.minY + (double) (attackEntity.height / 2.0F) - (this.posY + (double) (this.height / 2.0F));
                double var7 = attackEntity.posZ - this.posZ;

                if (this.attackTime <= 0) {
                    this.attackTime = (int) (30 + Math.round(60 * this.rand.nextDouble()));

                    if (this.getEquipmentInSlot(0).getItem() == NarutoItems.Kunai) {
                        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
                        EntityKunai var11 = new EntityKunai(this.worldObj, this, attackEntity, 1.6F, (float) (14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
                        var11.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
                        this.worldObj.spawnEntityInWorld(var11);
                    } else if (this.getEquipmentInSlot(0).getItem() == NarutoItems.Shuriken) {
                        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
                        EntityShuriken var11 = new EntityShuriken(this.worldObj, this, attackEntity, 1.6F, (float) (14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
                        var11.posY = this.posY + (double) (this.height / 2.0F) + 0.5D;
                        this.worldObj.spawnEntityInWorld(var11);
                    }
                    this.rotationYaw = (float) (Math.atan2(var7, var3) * 180.0D / Math.PI) - 90.0F;
                    this.hasAttacked = true;
                }
            }
        }

        super.onLivingUpdate();
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
        return "minecraft:game.player.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return "minecraft:game.player.die";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.playSound("mob.zombie.step", 0.15F, 1.0F);
    }

    /**
     * handles entity death timer, experience orb and particle creation
     */
    protected void onDeathUpdate() {
        ++this.deathTime;

        if (this.deathTime == 5) {
            this.playSound("narutomod:jutsusounds.clone_poof", 0.15F, 1.0F);
            this.setDead();

            for (int i = 0; i < 20; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("explode", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
            }
        }
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected void dropFewItems(boolean par1, int par2) {

    }

    protected void dropRareDrop(int par1) {

    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);

        this.setCustomNameTag("sekwah41");

        return par1EntityLivingData;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);

        if (this.isChild()) {
            par1NBTTagCompound.setBoolean("IsBaby", true);
        }

        // par1NBTTagCompound.setInteger("WeaponID", this.weaponID);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.getBoolean("IsBaby")) {
            this.setChild(true);
        }

        // this.weaponID = par1NBTTagCompound.getInteger("WeaponID");
    }

    /**
     * This method gets called when the entity kills another one.
     */
    public void onKillEntity(EntityLiving par1EntityLiving) {
        super.onKillEntity(par1EntityLiving);
    }


    public ResourceLocation getLocationSkin() {
        return this.locationSkin;
    }

    public ResourceLocation getLocationCape() {
        return this.locationCape;
    }
}
