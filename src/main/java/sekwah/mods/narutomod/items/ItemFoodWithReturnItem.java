package sekwah.mods.narutomod.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sekwah.mods.narutomod.NarutoMod;

public class ItemFoodWithReturnItem extends ItemFood {
    private static Item par8 = null;

    /**
     * Number of ticks to run while 'EnumAction'ing until result.
     */
    public final int itemUseDuration;

    /**
     * The amount this food item heals the player.
     */
    private final int healAmount;
    private final float saturationModifier;

    /**
     * Whether wolves like this food (true for raw and cooked porkchop).
     */
    private final boolean isWolfsFavoriteMeat;

    /**
     * If this field is true, the food can be consumed even if the player don't need to eat.
     */
    private boolean alwaysEdible;

    /**
     * represents the potion effect that will occurr upon eating this food. Set by setPotionEffect
     */
    private int potionId;

    /**
     * set by setPotionEffect
     */
    private int potionDuration;

    /**
     * set by setPotionEffect
     */
    private int potionAmplifier;

    /**
     * probably of the set potion effect occurring
     */
    private float potionEffectProbability;

    public ItemFoodWithReturnItem(int par2, float par3, Item par9, boolean par4) {
        super(par2, par3, false);
        this.itemUseDuration = 32;
        this.healAmount = par2;
        this.isWolfsFavoriteMeat = par4;
        this.saturationModifier = par3;
        this.setCreativeTab(CreativeTabs.tabFood);
        par8 = par9;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(NarutoMod.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        super.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        par3EntityPlayer.getFoodStats().addStats(this, par1ItemStack);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        return new ItemStack(par8);
    }
}