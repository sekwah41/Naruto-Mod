package sekwah.mods.narutomod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import sekwah.mods.narutomod.NarutoMod;

import java.util.List;

public class BaseItemAndSub extends Item {
    private final String[] subItems;
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
    private String name;

    public BaseItemAndSub(String... subItems) {
        super();
        this.subItems = subItems;
        setHasSubtypes(true);
        this.setMaxDurability(0);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        icons = new IIcon[subItems.length + 1];
        icons[0] = par1IconRegister.registerIcon(NarutoMod.modid + ":" + this.name);
        for(int i = 1; i <= subItems.length; i++) {
            icons[i] = par1IconRegister.registerIcon(NarutoMod.modid + ":" + this.name + subItems[i - 1]);
        }
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < icons.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        if (0 < par1ItemStack.getMetadata() && par1ItemStack.getMetadata() <= subItems.length) {
            return super.getUnlocalizedName() + "." + subItems[par1ItemStack.getMetadata() - 1];
        } else {
            return super.getUnlocalizedName();
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return icons[par1];
    }

    public Item setName(String name) {
        super.setUnlocalizedName(name);
        this.name = name;
        return this;
    }
}