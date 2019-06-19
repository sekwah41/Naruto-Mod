package sekwah.mods.narutomod.common.items.creativetabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sekwah.mods.narutomod.common.items.NarutoItems;

public class CreativeTabNinja extends CreativeTabs {

    private ItemStack itemStack;

    public CreativeTabNinja(int par1, String par2Str) {
        super(par1, par2Str);
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getIconItemStack() {
        return itemStack;
    }

    @SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public Item getTabIconItem() {
        return NarutoItems.Kunai;
    }

}
