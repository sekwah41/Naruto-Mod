package sekwah.mods.narutomod.player.extendedproperties;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerInfo implements IExtendedEntityProperties
{

    public final static String IDENTIFIER = "narutomod_playerdata";

    // could be usefull but not currently used, also this will only exist on a player and will not change so its reasonably safe
    private final EntityPlayer player;

    private int currentChakra, maxChakra;

    private int currentStamina, maxStamina;

    //private int RACE_WATCHER = 21;

    /*
    The default constructor takes no arguments, but I put in the Entity so I can initialize the above variable 'player'

    Also, it's best to initialize any other variables you may have added, just like in any constructor.
    */
    public PlayerInfo(EntityPlayer player)
    {
        this.player = player;

        // Load with max mana, maybe save the mana amount and load it to stop it messing with single player, also have stats and stuff change the max ki
        this.maxChakra = this.currentChakra = 50;
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
        /*this.race = info.race;
        player.getDataWatcher().updateObject(RACE_WATCHER, race);*/
    }


    public void reloadDW() {
        /*player.getDataWatcher().updateObject(RACE_WATCHER, "Undefined");

        System.out.println("Race: " + race);
        player.getDataWatcher().updateObject(RACE_WATCHER, race);*/
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

        //properties.setString("Race", race);
        properties.setInteger("CurrentKi", this.currentChakra);
        properties.setInteger("MaxKi", this.maxChakra);// possibly calculate the maxKi when a player loads to stop potential cheating with nbt data

        compound.setTag(IDENTIFIER, properties);
    }

    // Load whatever data you saved
    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound) compound.getTag(IDENTIFIER);
        //this.race = properties.getString("Race");
        this.currentChakra = properties.getInteger("CurrentKi");
        this.maxChakra = properties.getInteger("MaxKi");
        this.reloadDW();
        //System.out.println("[Dragon Ball Z] Current Ki for player from NBT: " + this.currentChakra + "/" + this.maxKi);
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
        boolean sufficient = amount <= this.currentChakra;
        // Consume the amount anyway; if it's more than the player's current chakra,
        // chakra will be set to 0
        this.currentChakra -= (sufficient ? amount : 0); // false, take away no mana
        // Return true if the player had enough ki
        return sufficient;
    }

    public void rechargeChakra(int amount)
    {
        // Does the player have enough ki?
        boolean sufficient = amount <= this.currentChakra;
        // Consume the amount anyway; if it's more than the player's current ki,
        // ki will be set to 0
        this.currentChakra += (amount < this.currentChakra ? amount : this.maxChakra);
    }

    /**
     * Refill the entire Chakra bar
     */
    public void replenishChakra()
    {
        this.currentChakra = this.maxChakra;
;
    }

    /**public void setRace(String race) {
        this.race = race;
    }*/
}