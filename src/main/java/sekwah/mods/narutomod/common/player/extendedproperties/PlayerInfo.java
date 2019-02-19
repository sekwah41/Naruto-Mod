package sekwah.mods.narutomod.common.player.extendedproperties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import sekwah.mods.narutomod.common.DataWatcherIDs;

public class PlayerInfo implements IExtendedEntityProperties
{

    public final static String IDENTIFIER = "narutomod_playerdata";

    // could be usefull but not currently used, also this will only exist on a player and will not change so its reasonably safe
    private final EntityPlayer player;

    private int chakra;
    private int maxChakra;
    private int chakraRegenCooldown;
    private double chakraRegenRate;

    private int stamina;
    private int maxStamina;
    private int staminaRegenCooldown;
    private double staminaRegenRate;

    private String clan = "Undefined"; // Start storing custom objects like what jutsus are learnt and make a special saver for those objects and loader for those objects

    public boolean hasAskedToSetClan = false;

    private int levelXP, level;

    private int health, maxHealth; // will potentially be used to overwrite the current health or if another way is found it may be good.


    private String rpFirstName, rpLastName; // May be used for role playing names rather than usernames.


    // Individual stats

    private int strengthStat, jutsuStat, intStat, defStat, luckStat, speedStat, dexStat, fortitudeStat, willpowerStat;

    /*
    The default constructor takes no arguments, but I put in the Entity so I can initialize the above variable 'player'

    Also, it's best to initialize any other variables you may have added, just like in any constructor.
    */
    public PlayerInfo(EntityPlayer player)
    {
        this.player = player;

        // Load with max mana, maybe save the mana amount and load it to stop it messing with single player, also have stats and stuff change the max ki
        this.maxChakra = this.chakra = 100;
        this.maxStamina = this.stamina = 100;

        this.staminaRegenRate = 0.22;

        this.chakraRegenRate = 0.025;

        this.hasAskedToSetClan = false;
    }

    /**
     * Used to register these extended properties for the player during EntityConstructing event and is used to register its self
     * also it makes the code look nice so why not
     */
    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(PlayerInfo.IDENTIFIER, new PlayerInfo(player));
    }

    public void copyData(PlayerInfo info) {
        this.clan = info.clan;
        player.getDataWatcher().updateObject(DataWatcherIDs.clan, clan);
    }


    public void reloadDW() {
        player.getDataWatcher().updateObject(DataWatcherIDs.clan, "Undefined");

        System.out.println("Clan: " + clan);
        player.getDataWatcher().updateObject(DataWatcherIDs.clan, clan);
    }

    /**
     * Returns ExtendedPlayer properties for player
     * This method is for convenience only
     */
    public static final PlayerInfo get(EntityPlayer player)
    {
        return (PlayerInfo) player.getExtendedProperties(IDENTIFIER);
    }

    // Save any custom data that needs saving here
    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();

        properties.setString("Clan", clan);
        properties.setInteger("CurrentChakra", this.chakra);
        properties.setInteger("MaxChakra", this.maxChakra);// possibly calculate the maxKi when a player loads to stop potential cheating with nbt data
        properties.setInteger("CurrentStamina", this.stamina);
        properties.setInteger("MaxStamina", this.maxStamina);
        compound.setTag(IDENTIFIER, properties);
    }

    // Load whatever data you saved
    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(IDENTIFIER);
        this.clan = properties.getString("Clan");
        this.chakra = properties.getInteger("CurrentChakra");
        this.maxChakra = properties.getInteger("MaxChakra"); // Soon change max to be calculated by stats
        this.stamina = properties.getInteger("CurrentStamina");
        this.maxStamina = properties.getInteger("MaxStamina");
        this.reloadDW();
    }

    @Override
    public void init(Entity entity, World world)
    {

    }

    // Extra custom methods to interact with data

    /**
     * Returns true if the amount of mana was consumed or false
     * if the player's current mana was insufficient
     */
    public boolean consumeChakra(int amount)
    {
        // Does the player have enough chakra?
        boolean sufficient = amount <= this.chakra;
        // Consume the amount anyway; if it's more than the player's current chakra,
        // chakra will be set to 0
        this.chakra -= (sufficient ? amount : 0); // false, take away no mana
        // Return true if the player had enough ki
        return sufficient;
    }

    public void rechargeChakra(int amount)
    {
        // Does the player have enough ki?
        boolean sufficient = amount <= this.chakra;
        // Consume the amount anyway; if it's more than the player's current ki,
        // ki will be set to 0
        this.chakra += (amount < this.chakra ? amount : this.maxChakra);
    }

    /**
     * Refill the entire Chakra bar
     */
    public void replenishChakra()
    {
        this.chakra = this.maxChakra;
    }

    public String getClan(){
        return clan;
    }

    public void setClan(String clanName){
        clan = clanName;
    }

    public void chakraRegenTick() {
        if (chakraRegenCooldown > 0) {
            chakraRegenCooldown--;
        } else if (chakra < maxChakra) {
            chakra += chakraRegenRate;
        }
    }
}