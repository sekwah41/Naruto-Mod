package sekwah.mods.narutomod.entitys;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import sekwah.mods.narutomod.entitys.ai.EntityAIDefendNinjaVillage;
import sekwah.mods.narutomod.entitys.ai.EntityAILookAtNinjaVillager;
import sekwah.mods.narutomod.entitys.projectiles.EntityKunai;
import sekwah.mods.narutomod.entitys.projectiles.EntityShuriken;
import sekwah.mods.narutomod.items.NarutoItems;

public class EntityNinjaVillagerAnbu extends EntityGolem {
    Village villageObj = null;
    /**
     * deincrements, and a distance-to-home check is done at 0
     */
    private int homeCheckTimer = 0;
    private int attackTimer;
    private int followAnbuTick;

    public EntityNinjaVillagerAnbu(World par1World) {
        super(par1World);

        this.getNavigator().setBreakDoors(true);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.5D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.5D, 32.0F));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.5D, true));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5D));
        this.tasks.addTask(5, new EntityAILookAtNinjaVillager(this));
        this.tasks.addTask(6, new EntityAIWander(this, 0.5D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIDefendNinjaVillage(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 1, false, true, IMob.mobSelector));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));

        this.getDataWatcher().addObject(15, new Byte((byte) 0));
    }

    @SideOnly(Side.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        this.attackTimer = 0;
        this.worldObj.setEntityState(this, (byte) 4);
        boolean var2 = par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), 7 + this.rand.nextInt(15));

        return var2;
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled() {
        return true;
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        this.addRandomArmor();

        this.setMask(this.rand.nextInt(2) + 1);

        return par1EntityLivingData;
    }

    protected void addRandomArmor() {
        super.addRandomArmor();

        int var1 = this.rand.nextInt(5);

        if (var1 == 1 || var1 == 2) {
            this.setCurrentItemOrArmor(0, new ItemStack(NarutoItems.Shuriken));
        } else if (var1 == 3) {
            this.setCurrentItemOrArmor(0, new ItemStack(NarutoItems.Katana));
        } else {
            this.setCurrentItemOrArmor(0, new ItemStack(NarutoItems.Kunai));
        }
    }

    public int getMask() {
        return this.dataWatcher.getWatchableObjectByte(15);
    }

    private void setMask(int par1) {
        this.dataWatcher.updateObject(15, Byte.valueOf((byte) par1));
    }

    /**
     * main AI tick function, replaces updateEntityActionState
     */
    protected void updateAITick() {
        if(this.getAITarget() instanceof EntityNinjaVillagerAnbu){
            this.setAttackTarget(null);
        }
        if (--this.homeCheckTimer <= 0) {
            this.homeCheckTimer = 70 + this.rand.nextInt(50);
            this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 32);

            if (this.villageObj == null) {
                this.detachHome();
            } else {
                ChunkCoordinates var1 = this.villageObj.getCenter();
                this.setHomeArea(var1.posX, var1.posY, var1.posZ, (int) ((float) this.villageObj.getVillageRadius() * 0.6F));
            }
        }

        super.updateAITick();
    }

    protected void collideWithEntity(Entity par1Entity) {
        if (par1Entity instanceof IMob && this.getRNG().nextInt(20) == 0) {
            this.setAttackTarget((EntityLiving) par1Entity);
        }

        super.collideWithEntity(par1Entity);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.attackTimer > 0) {
            --this.attackTimer;
        }

        if (this.followAnbuTick > 0) {
            --this.followAnbuTick;
        }

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
    }

    /**
     * Returns true if this entity can attack entities of the specified class.
     */
    public boolean canAttackClass(Class par1Class) {
        return this.isPlayerCreated() && EntityPlayer.class.isAssignableFrom(par1Class) ? false : super.canAttackClass(par1Class);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.getBoolean("MaskID")) {
            this.setMask(par1NBTTagCompound.getByte("MaskID"));
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);

        par1NBTTagCompound.setByte("MaskID", (byte) this.getMask());
    }

    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte par1) {
        if (par1 == 16) {
            this.worldObj.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "mob.zombie.remedy", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
        } else if (par1 == 11) {
            this.followAnbuTick = 400;
        } else {
            super.handleHealthUpdate(par1);
        }
    }

    public Village getVillage() {
        return this.villageObj;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return "none";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound() {
        return "narutomod:ninjavillager.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return "narutomod:ninjavillager.death";
    }

    /**
     * Plays step sound at given x, y, z for the entity
     */
    protected void playStepSound(int par1, int par2, int par3, int par4) {
        this.playSound("mob.zombie.step", 0.15F, 1.0F);
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2) {
        int var1 = this.rand.nextInt(10);
        this.entityDropItem(new ItemStack(NarutoItems.SilverRyo, var1, 1), 0.4F);
    }

    protected void dropRareDrop(int par1) {
        int var1 = this.rand.nextInt(5);

        if (var1 == 2) {
            this.dropItem(NarutoItems.Samehada, 1);
        }
    }

    public boolean isPlayerCreated() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setPlayerCreated(boolean par1) {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1) {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }


    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource par1DamageSource) {
        if (!this.isPlayerCreated() && this.attackingPlayer != null && this.villageObj != null) {
            this.villageObj.setReputationForPlayer(this.attackingPlayer.getCommandSenderName(), -5);
        }

        super.onDeath(par1DamageSource);
    }

    public void setfollowAnbuTick(boolean par1) {
        this.followAnbuTick = par1 ? 400 : 0;
        this.worldObj.setEntityState(this, (byte) 11);
    }

    public int followAnbuTick() {
        return this.followAnbuTick;
    }
}
