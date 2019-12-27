package sekwah.mods.narutomod.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import sekwah.mods.narutomod.NarutoMod;

import java.util.List;

public class IconItem extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon[] Icons;

    public IconItem() {
        super();
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        Icons = new IIcon[9];
        Icons[0] = par1IconRegister.registerIcon(NarutoMod.modid + ":jutsu_chakra_charge");
        Icons[1] = par1IconRegister.registerIcon(NarutoMod.modid + ":jutsu_clone_tecnique");
        Icons[2] = par1IconRegister.registerIcon(NarutoMod.modid + ":jutsu_fire_ball");
        Icons[3] = par1IconRegister.registerIcon(NarutoMod.modid + ":jutsu_water_walk");
        Icons[4] = par1IconRegister.registerIcon(NarutoMod.modid + ":jutsu_susanoo");
        Icons[5] = par1IconRegister.registerIcon(NarutoMod.modid + ":jutsu_chakra_dash");
        Icons[6] = par1IconRegister.registerIcon(NarutoMod.modid + ":jutsu_substiutution");
        Icons[7] = par1IconRegister.registerIcon(NarutoMod.modid + ":jutsu_water_bullet");
        Icons[8] = par1IconRegister.registerIcon(NarutoMod.modid + ":jutsu_earth_wall");
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
        par2List.add("\u00a77" + "Wait, how on earth did you get me?");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        if (par1 < 0) {
            par1 = 0;
        }
        if (par1 > Icons.length - 1) {
            par1 = Icons.length - 1;
        }
        return Icons[par1];
    }
}

