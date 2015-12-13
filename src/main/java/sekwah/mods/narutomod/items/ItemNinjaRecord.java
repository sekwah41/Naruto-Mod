package sekwah.mods.narutomod.items;

import sekwah.mods.narutomod.NarutoMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockJukebox;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemNinjaRecord extends ItemRecord {

    private static final Map field_150928_b = new HashMap();
    /**
     * The name of the record.
     */
    public final String recordName;

    protected ItemNinjaRecord(String par2Str) {
        super(par2Str);
        this.recordName = "narutomod:" + par2Str;
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabMisc);
        field_150928_b.put("narutomod:records." + par2Str, this);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(NarutoMod.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(this.getRecordTitle());
    }

    @SideOnly(Side.CLIENT)
    public static ItemRecord getRecord(String p_150926_0_)
    {
        return (ItemRecord)field_150928_b.get(p_150926_0_);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Return the title for this record.
     */
    public String getRecordTitle() {
        return "Naruto - " + this.recordName.replaceFirst("narutomod:", "");
    }
    /**
     * Retrieves the resource location of the sound to play for this record.
     *
     * @param name The name of the record to play
     * @return The resource location for the audio, null to use default.
     */
    public ResourceLocation getRecordResource(String name)
    {
        return new ResourceLocation("narutomod",name);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        if (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_) == Blocks.jukebox && p_77648_3_.getBlockMetadata(p_77648_4_, p_77648_5_, p_77648_6_) == 0)
        {
            if (p_77648_3_.isRemote)
            {
                return true;
            }
            else
            {
                ((BlockJukebox)Blocks.jukebox).func_149926_b(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_1_);
                p_77648_3_.playAuxSFXAtEntity((EntityPlayer)null, 1005, p_77648_4_, p_77648_5_, p_77648_6_, Item.getIdFromItem(this));
                --p_77648_1_.stackSize;
                return true;
            }
        }
        else
        {
            return false;
        }
    }

}