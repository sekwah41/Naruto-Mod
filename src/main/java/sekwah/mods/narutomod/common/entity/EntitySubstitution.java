package sekwah.mods.narutomod.common.entity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import sekwah.mods.narutomod.common.DataWatcherIDs;
import sekwah.mods.narutomod.sekcore.SkinLoader;

public class EntitySubstitution extends EntityMob {

    //public static final ResourceLocation locationStevePng = new ResourceLocation("textures/entity/steve.png");

    public double field_71091_bM;
    public double field_71096_bN;
    public double field_71097_bO;
    public double field_71094_bP;
    public double field_71095_bQ;
    public double field_71085_bR;
    public int lifetime = 900; // how long they live for, base time
    private ResourceLocation locationSkin;
    private ResourceLocation locationCape;

    public EntitySubstitution(World par1World) {
        super(par1World);
        this.setupCustomSkin();

    }

    protected void setupCustomSkin() {
        if (this.getCustomNameTag() != null && !this.getCustomNameTag().isEmpty()) {

            this.locationSkin = AbstractClientPlayer.locationStevePng;

            if (this.getCustomNameTag() != null && this.getCustomNameTag().length() > 0) {
                this.locationSkin = SkinLoader.getUserSkin(this.getCustomNameTag());
                SkinLoader.getDownloadImageSkin(this.locationSkin, null, this.getCustomNameTag());
            }

            // this.locationCape = getLocationCape("cloaks/" + this.getCustomNameTag());
        }
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
    }

    protected void entityInit() {
        super.entityInit();

        this.getDataWatcher().addObject(12, Byte.valueOf((byte) 0));

        this.getDataWatcher().addObject(13, Byte.valueOf((byte) 0));
        this.getDataWatcher().addObject(14, Byte.valueOf((byte) 0));

        this.getDataWatcher().addObject(DataWatcherIDs.eyerenderer, 0);

        this.getDataWatcher().addObject(18, 0F);
        this.getDataWatcher().addObject(19, 0F);
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

    /**
     * Set sprinting switch for Entity.
     */
    public void setSprinting(boolean par1) {
        this.setFlag(3, par1);
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

    public void setMovement(float par1, float par5) {
        this.getDataWatcher().updateObject(18, par1);
        this.getDataWatcher().updateObject(19, par5);
    }

    public void onLivingUpdate() {
        lifetime--;

        this.motionX = this.getDataWatcher().getWatchableObjectFloat(18);
        this.motionZ = this.getDataWatcher().getWatchableObjectFloat(19);

        if (lifetime <= 0) { // how many ticks they last
            this.damageEntity(DamageSource.magic, 30);
        }
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild()) {
            float var1 = this.getBrightness(1.0F);

            if (var1 > 0.5F && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
                boolean var2 = true;
                ItemStack var3 = this.getEquipmentInSlot(4);

                if (var3 != null) {
                    if (var3.isItemStackDamageable()) {
                        var3.setItemDamage(var3.getItemDamageForDisplay() + this.rand.nextInt(2));

                        if (var3.getItemDamageForDisplay() >= var3.getMaxDamage()) {
                            this.renderBrokenItemStack(var3);
                            this.setCurrentItemOrArmor(4, null);
                        }
                    }

                    var2 = false;
                }

                if (var2) {
                }
            }
        }

        super.onLivingUpdate();
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

        if (this.deathTime == 1) {
            for (int i = 0; i < 5; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("explode", this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2);
            }
        } else if (this.deathTime == 2) {
            this.playSound("narutomod:jutsusounds.clone_poof", 0.15F, 1.0F);
            this.setDead();

            Side side = FMLCommonHandler.instance().getEffectiveSide();
            if (side == Side.SERVER) {
                EntitySubstitutionLog substitutionLog = new EntitySubstitutionLog(this.worldObj);

                substitutionLog.setLocationAndAngles(this.posX, this.posY + 1, this.posZ, this.rotationYaw, this.rotationPitch);
                substitutionLog.setVelocity(this.motionX, this.motionY, this.motionZ);

                this.worldObj.spawnEntityInWorld(substitutionLog);
            }
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

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);

        this.setCustomNameTag("SEKWAH41");

        return par1EntityLivingData;
    }


    public ResourceLocation getLocationSkin() {
        return this.locationSkin;
    }

    public ResourceLocation getLocationCape() {
        return this.locationCape;
    }
}
