package sekwah.mods.narutomod.common.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sekwah.mods.narutomod.common.entity.ai.EntityAIAttackSprint;
import sekwah.mods.narutomod.common.entity.projectiles.EntityKunai;
import sekwah.mods.narutomod.common.entity.projectiles.EntityShuriken;
import sekwah.mods.narutomod.common.items.NarutoItems;

public class EntityRogueNinja extends EntityMob {

    public int maskID;

    public EntityRogueNinja(World par1World) {
        super(par1World);
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackSprint(this, 1.0D, false));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAILeapAtTarget(this, (float) 0.4D));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityNinjaVillager.class, 0, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityNinjaVillagerAnbu.class, 0, false));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
    }

    protected void entityInit() {
        super.entityInit();

        this.getDataWatcher().addObject(12, Byte.valueOf((byte) 0));
        this.getDataWatcher().addObject(13, Byte.valueOf((byte) 0));
        this.getDataWatcher().addObject(14, Byte.valueOf((byte) 0));

        this.getDataWatcher().addObject(15, new Byte((byte) 0));
    }

    /**
     * Returns the current armour value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    public int getTotalArmorValue() {
        int var1 = super.getTotalArmorValue() + 2;

        if (var1 > 20) {
            var1 = 20;
        }

        return var1;
    }

    protected boolean isValidLightLevel() {
        return true; //don't care about the light level to spawn
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    protected boolean isAIEnabled() {
        return true;
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

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        super.onUpdate();
        // System.out.println(this.posX + " " + this.posY + " " + this.posZ);
    }

    public void onLivingUpdate() {
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
        return "narutomod:ninjavillager.say";
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
     * Returns the item ID for the item the mob drops on death.
     */
    protected void dropFewItems(boolean par1, int par2) {
        int var1 = this.rand.nextInt(25);
        this.entityDropItem(new ItemStack(NarutoItems.CopperRyo, var1, 1), 0.4F);
    }

    protected void dropRareDrop(int par1) {
        switch (this.rand.nextInt(3)) {
            case 0:
                this.dropItem(NarutoItems.Katana, 1);
                break;

            case 1:
                this.dropItem(NarutoItems.Kunai, 1);
                break;

            case 2:
                this.dropItem(NarutoItems.Shuriken, 1);
        }
    }

    public IEntityLivingData onSpawnWithEgg(IEntityLivingData par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        this.addRandomArmor();

        this.setMask(this.rand.nextInt(2) + 1);

        return par1EntityLivingData;
    }

    public int getMask() {
        return this.dataWatcher.getWatchableObjectByte(15);
    }

    private void setMask(int par1) {
        this.dataWatcher.updateObject(15, Byte.valueOf((byte) par1));
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

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);

        if (this.isChild()) {
            par1NBTTagCompound.setBoolean("IsBaby", true);
        }

        par1NBTTagCompound.setByte("MaskID", (byte) this.getMask());
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

        this.setMask(par1NBTTagCompound.getByte("MaskID"));

        // this.weaponID = par1NBTTagCompound.getInteger("WeaponID");
    }

    /**
     * This method gets called when the entity kills another one.
     */
    public void onKillEntity(EntityLiving par1EntityLiving) {
        super.onKillEntity(par1EntityLiving);
    }
}
