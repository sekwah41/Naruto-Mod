package sekwah.mods.narutomod.common.entity;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.properties.Property;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.common.DataWatcherIDs;
import sekwah.mods.narutomod.common.entity.ai.EntityAIFollowMaster;
import sekwah.mods.narutomod.common.entity.ai.EntityAIOwnerHurtTarget;
import sekwah.mods.narutomod.common.entity.projectiles.EntityKunai;
import sekwah.mods.narutomod.common.entity.projectiles.EntityShuriken;
import sekwah.mods.narutomod.items.NarutoItems;

import java.io.IOException;
import java.util.UUID;

public class EntityShadowClone extends EntityCreature implements SkinCallback, IEntityAdditionalSpawnData {

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

    public static final ResourceLocation locationStevePng = new ResourceLocation("textures/entity/steve.png");

    private GameProfile gameProfile;

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
        this.targetTasks.addTask(1, new sekwah.mods.narutomod.common.entity.ai.EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
    }

    public EntityShadowClone(World par1World, GameProfile gameProfile) {
        this(par1World);

        this.gameProfile = gameProfile;

        //this.loadSkinFromProfile(gameProfile);

    }

    private void loadSkinFromProfile(GameProfile gameProfile) {
        NarutoMod.proxy.getSkin(gameProfile, this);
    }

    public boolean attackEntityAsMob(Entity p_attackEntityAsMob_1_) {
        float var2 = (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int var3 = 0;
        if (p_attackEntityAsMob_1_ instanceof EntityLivingBase) {
            var2 += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)p_attackEntityAsMob_1_);
            var3 += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)p_attackEntityAsMob_1_);
        }

        boolean var4 = p_attackEntityAsMob_1_.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
        if (var4) {
            if (var3 > 0) {
                p_attackEntityAsMob_1_.addVelocity((double)(-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * (float)var3 * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * (float)var3 * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int var5 = EnchantmentHelper.getFireAspectModifier(this);
            if (var5 > 0) {
                p_attackEntityAsMob_1_.setFire(var5 * 4);
            }

            if (p_attackEntityAsMob_1_ instanceof EntityLivingBase) {
                EnchantmentHelper.func_151384_a((EntityLivingBase)p_attackEntityAsMob_1_, this);
            }

            EnchantmentHelper.func_151385_b(this, p_attackEntityAsMob_1_);
        }

        return var4;
    }



    protected void attackEntity(Entity p_attackEntity_1_, float p_attackEntity_2_) {
        if (this.attackTime <= 0 && p_attackEntity_2_ < 2.0F && p_attackEntity_1_.boundingBox.maxY > this.boundingBox.minY && p_attackEntity_1_.boundingBox.minY < this.boundingBox.maxY) {
            this.attackTime = 20;
            this.attackEntityAsMob(p_attackEntity_1_);
        }

    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
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

        this.getDataWatcher().addObject(DataWatcherIDs.eyerenderer, 0);
    }

    // TODO store master and the display name seperately and take into account the user's team and other stuff
    // to render on the nametags :)
    public EntityLivingBase getMaster() {
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

        if (lifetime == poofTime) {
            this.playSound("narutomod:jutsusounds.clone_poof", 0.15F, this.isChild() ? 1.2F : 1.0f);
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
            this.playSound("narutomod:jutsusounds.clone_poof", 0.15F, this.isChild() ? 1.2F : 1.0f);
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

        gameProfile = MinecraftServer.getServer().func_152358_ax().func_152655_a("sekwah41");

        return par1EntityLivingData;
    }

    public void setUsername(String name) {
        GameProfile gameProfile = MinecraftServer.getServer().func_152358_ax().func_152655_a(name);
    }

    public void setUUID(UUID uuid) {
        GameProfile gameProfile = MinecraftServer.getServer().func_152358_ax().func_152652_a(uuid);
        this.setGameProfile(gameProfile);
    }

    private void setGameProfile(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
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
        par1NBTTagCompound.setString("profileUUID", this.gameProfile.getId().toString());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.getBoolean("IsBaby")) {
            this.setChild(true);
        }

        gameProfile = new GameProfile(UUID.fromString(par1NBTTagCompound.getString("profileUUID")), this.getCustomNameTag());

        // this.weaponID = par1NBTTagCompound.getInteger("WeaponID");
    }

    /**
     * This method gets called when the entity kills another one.
     */
    public void onKillEntity(EntityLiving par1EntityLiving) {
        super.onKillEntity(par1EntityLiving);
    }


    public ResourceLocation getLocationSkin()
    {
        return this.locationSkin == null ? locationStevePng : this.locationSkin;
    }

    public ResourceLocation getLocationCape() {
        return this.locationCape;
    }

    @Override
    public void returnedSkin(MinecraftProfileTexture.Type p_152121_1_, ResourceLocation p_152121_2_) {
        switch (EntityShadowClone.SwitchType.field_152630_a[p_152121_1_.ordinal()])
        {
            case 1:
                this.locationSkin = p_152121_2_;
                break;
            case 2:
                this.locationCape = p_152121_2_;
        }
    }

    @SideOnly(Side.CLIENT)

    static final class SwitchType
    {
        static final int[] field_152630_a = new int[MinecraftProfileTexture.Type.values().length];
        private static final String __OBFID = "CL_00001832";

        static
        {
            try
            {
                field_152630_a[MinecraftProfileTexture.Type.SKIN.ordinal()] = 1;
            }
            catch (NoSuchFieldError var2)
            {
                ;
            }

            try
            {
                field_152630_a[MinecraftProfileTexture.Type.CAPE.ordinal()] = 2;
            }
            catch (NoSuchFieldError var1)
            {
                ;
            }
        }
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        PacketBuffer packetBuffer = new PacketBuffer(buffer);

        Property textureProperty = Iterables.getFirst(gameProfile.getProperties().get("textures"), null);
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString("name", gameProfile.getName());
        nbtTagCompound.setString("id", gameProfile.getId().toString());
        if (textureProperty != null) {
            nbtTagCompound.setString("tex_name", textureProperty.getName());
            nbtTagCompound.setString("tex_value", textureProperty.getName());
            nbtTagCompound.setString("tex_sig", textureProperty.getSignature());
        }
        try {
            packetBuffer.writeNBTTagCompoundToBuffer(nbtTagCompound);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*if(gameProfile != null) {
            ByteBufUtils.writeUTF8String(buffer, gameProfile.getName() + "\n" + gameProfile.getId().toString());
        }
        else {
            ByteBufUtils.writeUTF8String(buffer, "null");
        }*/
    }

    @Override
    public void readSpawnData(ByteBuf buffer) {
        PacketBuffer packetBuffer = new PacketBuffer(buffer);
        NBTTagCompound tagCompound = null;
        try {
            tagCompound = packetBuffer.readNBTTagCompoundFromBuffer();

            GameProfile gameProfile = new GameProfile(UUID.fromString(tagCompound.getString("id")), tagCompound.getString("name"));
            if(tagCompound.hasKey("tex_name")) {
                gameProfile.getProperties().put("textures", new Property(tagCompound.getString("tex_name"),
                        tagCompound.getString("tex_value"), tagCompound.getString("tex_sig")));
            }
            this.loadSkinFromProfile(gameProfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*String string = ByteBufUtils.readUTF8String(buffer);
        if(string.equals("null")) {
            return;
        }
        String[] details = string.split("\n");
        this.loadSkinFromProfile(new GameProfile(UUID.fromString(details[1]), details[0]));*/
    }
}
