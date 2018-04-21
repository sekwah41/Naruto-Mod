package sekwah.mods.narutomod.common.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Tuple;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import sekwah.mods.narutomod.common.block.NarutoBlocks;
import sekwah.mods.narutomod.common.entity.ai.*;
import sekwah.mods.narutomod.items.NarutoItems;

import java.util.*;

public class EntityNinjaVillager extends EntityAgeable implements INpc, IMerchant {
    /**
     * a villagers recipe list is intialized off this list ; the 2 params are min/max amount they will trade for 1
     * emerald
     */
    public static final Map villagerStockList = new HashMap();
    /**
     * Selling list of Blacksmith items. negative numbers mean 1 emerald for n items, positive numbers are n emeralds
     * for 1 item
     */
    private static final Map blacksmithSellingList = new HashMap();
    Village villageObj;
    private int randomTickDivider;
    private boolean isMating;
    private boolean isPlaying;
    /**
     * This villager's current customer.
     */
    private EntityPlayer buyingPlayer;
    /**
     * Initialises the MerchantRecipeList.java
     */
    private MerchantRecipeList buyingList;
    private int timeUntilReset;
    /**
     * addDefaultEquipmentAndRecipies is called if this is true
     */
    private boolean needsInitilization;
    private int wealth;
    /**
     * Last player to trade with this villager, used for aggressivity.
     */
    private String lastBuyingPlayer;
    private boolean field_82190_bM;
    private float field_82191_bN;

    public EntityNinjaVillager(World par1World) {
        this(par1World, 0);
    }

    public EntityNinjaVillager(World par1World, int par2) {
        super(par1World);
        this.setProfession(par1World.rand.nextInt(3));
        this.setSize(0.6F, 1.8F);
        this.getNavigator().setBreakDoors(true);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityZombie.class, 8.0F, 0.6F, 0.6F));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityRogueNinja.class, 8.0F, 0.6F, 0.6F));
        this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityBandit.class, 8.0F, 0.6F, 0.6F));
        this.tasks.addTask(1, new EntityAINinjaTradePlayer(this));
        this.tasks.addTask(1, new EntityAILookAtNinjaTradePlayer(this));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(4, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.6F));
        this.tasks.addTask(6, new EntityAINinjaVillagerMate(this));
        this.tasks.addTask(7, new EntityAIFollowAnbu(this));
        this.tasks.addTask(8, new EntityAINinjaPlay(this, 0.32F));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest2(this, EntityVillager.class, 5.0F, 0.02F));
        this.tasks.addTask(9, new EntityAIWander(this, 0.6F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
    }

    /**
     * each recipie takes a random stack from villagerStockList and offers it for 1 emerald
     */
    private static void addMerchantItem(MerchantRecipeList par0MerchantRecipeList, Item item, Random par2Random, float par3) {
        if (par2Random.nextFloat() < par3) {
            par0MerchantRecipeList.add(new MerchantRecipe(getRandomSizedStack(item, par2Random), NarutoItems.SilverRyo));
        }
    }

    private static ItemStack getRandomSizedStack(Item item, Random par1Random) {
        return new ItemStack(item, getRandomCountForItem(item, par1Random), 0);
    }

    /**
     * default to 1, and villagerStockList contains a min/max amount for each index
     */
    private static int getRandomCountForItem(Item item, Random par1Random) {
        Tuple var2 = (Tuple) villagerStockList.get(item);
        return var2 == null ? 1 : (((Integer) var2.getFirst()).intValue() >= ((Integer) var2.getSecond()).intValue() ? ((Integer) var2.getFirst()).intValue() : ((Integer) var2.getFirst()).intValue() + par1Random.nextInt(((Integer) var2.getSecond()).intValue() - ((Integer) var2.getFirst()).intValue()));
    }

    private static void addBlacksmithItem(MerchantRecipeList par0MerchantRecipeList, Item item, Random par2Random, float par3) {
        if (par2Random.nextFloat() < par3) {
            int j = getRandomCountForBlacksmithItem(item, par2Random);
            ItemStack itemstack;
            ItemStack itemstack1;

            if (j < 0) {
                itemstack = new ItemStack(NarutoItems.SilverRyo, 1, 0);
                itemstack1 = new ItemStack(item, -j, 0);
            } else {
                itemstack = new ItemStack(NarutoItems.SilverRyo, j, 0);
                itemstack1 = new ItemStack(item, 1, 0);
            }

            par0MerchantRecipeList.add(new MerchantRecipe(itemstack, itemstack1));
        }
    }

    private static int getRandomCountForBlacksmithItem(Item item, Random par1Random) {
        Tuple var2 = (Tuple) blacksmithSellingList.get(item);
        return var2 == null ? 1 : (((Integer) var2.getFirst()).intValue() >= ((Integer) var2.getSecond()).intValue() ? ((Integer) var2.getFirst()).intValue() : ((Integer) var2.getFirst()).intValue() + par1Random.nextInt(((Integer) var2.getSecond()).intValue() - ((Integer) var2.getFirst()).intValue()));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled() {
        return true;
    }

    /**
     * main AI tick function, replaces updateEntityActionState
     */
    protected void updateAITick() {
        if (--this.randomTickDivider <= 0) {
            this.worldObj.villageCollectionObj.addVillagerPosition(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
            this.randomTickDivider = 70 + this.rand.nextInt(50);
            this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 32);

            if (this.villageObj == null) {
                this.detachHome();
            } else {
                ChunkCoordinates var1 = this.villageObj.getCenter();
                this.setHomeArea(var1.posX, var1.posY, var1.posZ, (int) ((float) this.villageObj.getVillageRadius() * 0.6F));

                if (this.field_82190_bM) {
                    this.field_82190_bM = false;
                    this.villageObj.setDefaultPlayerReputation(5);
                }
            }
        }

        if (!this.isTrading() && this.timeUntilReset > 0) {
            --this.timeUntilReset;

            if (this.timeUntilReset <= 0) {
                if (this.needsInitilization) {
                    if (this.buyingList.size() > 1) {
                        Iterator var3 = this.buyingList.iterator();

                        while (var3.hasNext()) {
                            MerchantRecipe merchantrecipe = (MerchantRecipe) var3.next();

                            if (merchantrecipe.isRecipeDisabled()) {
                                merchantrecipe.func_82783_a(this.rand.nextInt(6) + this.rand.nextInt(6) + 2);
                            }
                        }
                    }

                    this.addDefaultEquipmentAndRecipies(1);
                    this.needsInitilization = false;

                    if (this.villageObj != null && this.lastBuyingPlayer != null) {
                        this.worldObj.setEntityState(this, (byte) 14);
                        this.villageObj.setReputationForPlayer(this.lastBuyingPlayer, 1);
                    }
                }

                this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 0));
            }
        }

        super.updateAITick();
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer) {
        ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
        boolean flag = itemstack != null && itemstack.getItem() == Items.spawn_egg;

        if (!flag && this.isEntityAlive() && !this.isTrading() && !this.isChild() && !par1EntityPlayer.isSneaking()) {
            if (!this.worldObj.isRemote) {
                this.setCustomer(par1EntityPlayer);
                if (this.getCustomNameTag() == "") {
                    par1EntityPlayer.displayGUIMerchant(this, "Ninja Villager");
                } else {
                    par1EntityPlayer.displayGUIMerchant(this, this.getCustomNameTag());
                }
            }

            return true;
        } else {
            return super.interact(par1EntityPlayer);
        }
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(16, Integer.valueOf(0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Profession", this.getProfession());
        par1NBTTagCompound.setInteger("Riches", this.wealth);

        if (this.buyingList != null) {
            par1NBTTagCompound.setTag("Offers", this.buyingList.getRecipiesAsTags());
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setProfession(par1NBTTagCompound.getInteger("Profession"));
        this.wealth = par1NBTTagCompound.getInteger("Riches");

        if (par1NBTTagCompound.hasKey("Offers")) {
            NBTTagCompound var2 = par1NBTTagCompound.getCompoundTag("Offers");
            this.buyingList = new MerchantRecipeList(var2);
        }
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn() {
        return false;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return "mob.villager.default";
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

    public int getProfession() {
        return this.dataWatcher.getWatchableObjectInt(16);
    }

    public void setProfession(int par1) {
        this.dataWatcher.updateObject(16, Integer.valueOf(par1));
    }

    public boolean isMating() {
        return this.isMating;
    }

    public void setMating(boolean par1) {
        this.isMating = par1;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void setPlaying(boolean par1) {
        this.isPlaying = par1;
    }

    public void setRevengeTarget(EntityLivingBase par1EntityLivingBase) {
        super.setRevengeTarget(par1EntityLivingBase);

        if (this.villageObj != null && par1EntityLivingBase != null) {
            this.villageObj.addOrRenewAgressor(par1EntityLivingBase);

            if (par1EntityLivingBase instanceof EntityPlayer) {
                byte var2 = -1;

                if (this.isChild()) {
                    var2 = -3;
                }

                this.villageObj.setReputationForPlayer(par1EntityLivingBase.getCommandSenderName(), var2);

                if (this.isEntityAlive()) {
                    this.worldObj.setEntityState(this, (byte) 13);
                }
            }
        }
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource par1DamageSource) {
        if (this.villageObj != null) {
            Entity var2 = par1DamageSource.getEntity();

            if (var2 != null) {
                if (var2 instanceof EntityPlayer) {
                    this.villageObj.setReputationForPlayer(var2.getCommandSenderName(), -2);
                } else if (var2 instanceof IMob) {
                    this.villageObj.endMatingSeason();
                }
            } else if (var2 == null) {
                EntityPlayer var3 = this.worldObj.getClosestPlayerToEntity(this, 16.0D);

                if (var3 != null) {
                    this.villageObj.endMatingSeason();
                }
            }
        }

        super.onDeath(par1DamageSource);
    }

    public EntityPlayer getCustomer() {
        return this.buyingPlayer;
    }

    public void setCustomer(EntityPlayer par1EntityPlayer) {
        this.buyingPlayer = par1EntityPlayer;
    }

    public boolean isTrading() {
        return this.buyingPlayer != null;
    }

    public void useRecipe(MerchantRecipe par1MerchantRecipe) {
        par1MerchantRecipe.incrementToolUses();

        if (par1MerchantRecipe.hasSameIDsAs((MerchantRecipe) this.buyingList.get(this.buyingList.size() - 1))) {
            this.timeUntilReset = 40;
            this.needsInitilization = true;

            if (this.buyingPlayer != null) {
                this.lastBuyingPlayer = this.buyingPlayer.getCommandSenderName();
            } else {
                this.lastBuyingPlayer = null;
            }
        }

        if (par1MerchantRecipe.getItemToBuy().getItem() == NarutoItems.SilverRyo) {
            this.wealth += par1MerchantRecipe.getItemToBuy().stackSize;
        }
    }

    public MerchantRecipeList getRecipes(EntityPlayer par1EntityPlayer) {
        if (this.buyingList == null) {
            this.addDefaultEquipmentAndRecipies(1);
        }

        return this.buyingList;
    }

    private float adjustProbability(float par1) {
        float var2 = par1 + this.field_82191_bN;
        return var2 > 0.9F ? 0.9F - (var2 - 0.9F) : var2;
    }

    /**
     * based on the villagers profession add items, equipment, and recipies adds par1 random items to the list of things
     * that the villager wants to buy. (at most 1 of each wanted type is added)
     */
    private void addDefaultEquipmentAndRecipies(int par1) {
        if (this.buyingList != null) {
            this.field_82191_bN = MathHelper.sqrt_float((float) this.buyingList.size()) * 0.2F;
        } else {
            this.field_82191_bN = 0.0F;
        }

        MerchantRecipeList merchantrecipelist;
        merchantrecipelist = new MerchantRecipeList();
        int j;
        label50:

        switch (this.getProfession()) {
            case 0:
                //addBlacksmithItem(merchantrecipelist, NarutoItems.MILITARY_PILLS.itemID, this.rand, this.adjustProbability(0.3F));
                addBlacksmithItem(merchantrecipelist, NarutoItems.NOODLES, this.rand, this.adjustProbability(0.9F));
                addMerchantItem(merchantrecipelist, Items.paper, this.rand, this.adjustProbability(0.8F));
                addMerchantItem(merchantrecipelist, Items.book, this.rand, this.adjustProbability(0.8F));
                addMerchantItem(merchantrecipelist, Item.getItemFromBlock(NarutoBlocks.Sakura_Sapling), this.rand, this.adjustProbability(0.3F));

                break;

            case 1:
                addBlacksmithItem(merchantrecipelist, NarutoItems.Katana, this.rand, this.adjustProbability(0.4F));
                addBlacksmithItem(merchantrecipelist, NarutoItems.Shuriken, this.rand, this.adjustProbability(0.4F));
                addBlacksmithItem(merchantrecipelist, NarutoItems.bokken, this.rand, this.adjustProbability(0.5F));
                addBlacksmithItem(merchantrecipelist, NarutoItems.Kunai, this.rand, this.adjustProbability(0.4F));
                addBlacksmithItem(merchantrecipelist, NarutoItems.ExplosiveKunai, this.rand, this.adjustProbability(0.01F));

                break;

            case 2:
                addBlacksmithItem(merchantrecipelist, Item.getItemFromBlock(NarutoBlocks.Sakura_Sapling), this.rand, this.adjustProbability(0.3F));
                addBlacksmithItem(merchantrecipelist, Items.experience_bottle, this.rand, this.adjustProbability(0.2F));
                addBlacksmithItem(merchantrecipelist, Items.redstone, this.rand, this.adjustProbability(0.4F));
                addBlacksmithItem(merchantrecipelist, Item.getItemFromBlock(Blocks.glowstone), this.rand, this.adjustProbability(0.3F));
                break;
        }

        Collections.shuffle(merchantrecipelist);

        if (this.buyingList == null) {
            this.buyingList = new MerchantRecipeList();
        }

        for (int var9 = 0; var9 < par1 && var9 < merchantrecipelist.size(); ++var9) {
            this.buyingList.addToListWithCheck((MerchantRecipe) merchantrecipelist.get(var9));
        }
    }

    public void setRecipes(MerchantRecipeList par1MerchantRecipeList) {
    }

    public void handleHealthUpdate(byte par1) {
        if (par1 == 12) {
            this.generateRandomParticles("heart");
        } else if (par1 == 13) {
            this.generateRandomParticles("angryVillager");
        } else if (par1 == 14) {
            this.generateRandomParticles("happyVillager");
        } else {
            super.handleHealthUpdate(par1);
        }
    }

    /**
     * par1 is the particleName
     */
    private void generateRandomParticles(String par1Str) {
        for (int var2 = 0; var2 < 5; ++var2) {
            double var3 = this.rand.nextGaussian() * 0.02D;
            double var5 = this.rand.nextGaussian() * 0.02D;
            double var7 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle(par1Str, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 1.0D + (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, var3, var5, var7);
        }
    }

    /**
     * Initialize this creature.
     */
    public void initCreature() {
        this.setProfession(this.worldObj.rand.nextInt(3));
    }

    public void func_82187_q() {
        this.field_82190_bM = true;
    }

    public EntityNinjaVillager func_90012_b(EntityAgeable par1EntityAgeable) {
        EntityNinjaVillager var2 = new EntityNinjaVillager(this.worldObj);
        var2.initCreature();
        return var2;
    }

    public boolean allowLeashing() {
        return false;
    }

    public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
        return this.func_90012_b(par1EntityAgeable);
    }


    public void func_110297_a_(ItemStack par1ItemStack) {
    }

    static {
        blacksmithSellingList.put(Items.shears, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        blacksmithSellingList.put(NarutoItems.Katana, new Tuple(Integer.valueOf(7), Integer.valueOf(11)));
        blacksmithSellingList.put(NarutoItems.Shuriken, new Tuple(Integer.valueOf(4), Integer.valueOf(7)));
        blacksmithSellingList.put(NarutoItems.Kunai, new Tuple(Integer.valueOf(4), Integer.valueOf(7)));
        blacksmithSellingList.put(NarutoItems.ExplosiveKunai, new Tuple(Integer.valueOf(14), Integer.valueOf(16)));
        villagerStockList.put(NarutoBlocks.Sakura_Sapling, new Tuple(Integer.valueOf(4), Integer.valueOf(13)));
        villagerStockList.put(NarutoItems.NOODLES, new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
        villagerStockList.put(Items.paper, new Tuple(Integer.valueOf(16), Integer.valueOf(24)));
        villagerStockList.put(Items.experience_bottle, new Tuple(Integer.valueOf(13), Integer.valueOf(16)));
        villagerStockList.put(Items.redstone, new Tuple(Integer.valueOf(5), Integer.valueOf(15)));
        villagerStockList.put(Blocks.glowstone, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
        villagerStockList.put(Items.paper, new Tuple(Integer.valueOf(3), Integer.valueOf(4)));
    }
}


