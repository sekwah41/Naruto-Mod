package sekwah.mods.narutomod.common.items;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.common.DataWatcherIDs;
import sekwah.mods.narutomod.client.item.model.armour.ModelBackScroll;

import java.util.List;

public class ItemArmourScroll extends ItemArmor {

    public static final String[] names = new String[]{"White", "Yellow", "Purple", "Blue", "Green", "Red", "Black"};
    @SideOnly(Side.CLIENT)
    private IIcon[] Icons;

    public ItemArmourScroll(ArmorMaterial par2EnumArmorMaterial,
                            int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setHasSubtypes(true);
        this.setMaxDurability(0);

    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        Icons = new IIcon[7];
        Icons[0] = par1IconRegister.registerIcon(NarutoMod.modid + ":backScrollWhite");
        Icons[1] = par1IconRegister.registerIcon(NarutoMod.modid + ":backScrollYellow");
        Icons[2] = par1IconRegister.registerIcon(NarutoMod.modid + ":backScrollPurple");
        Icons[3] = par1IconRegister.registerIcon(NarutoMod.modid + ":backScrollBlue");
        Icons[4] = par1IconRegister.registerIcon(NarutoMod.modid + ":backScrollGreen");
        Icons[5] = par1IconRegister.registerIcon(NarutoMod.modid + ":backScrollRed");
        Icons[6] = par1IconRegister.registerIcon(NarutoMod.modid + ":backScrollBlack");
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        // You can also take a more direct approach and do each one individual but I prefer the lazy / right way
        for (int i = 0; i < 7; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot,
                                  String type) {

        if (stack.getItem() == NarutoItems.backScroll && stack.getMetadata() == 0 && slot == 1) {
            return NarutoMod.modid + ":textures/armour/back_scroll_white.png";
        } else if (stack.getItem() == NarutoItems.backScroll && stack.getMetadata() == 1 && slot == 1) {
            return NarutoMod.modid + ":textures/armour/back_scroll_yellow.png";
        } else if (stack.getItem() == NarutoItems.backScroll && stack.getMetadata() == 2 && slot == 1) {
            return NarutoMod.modid + ":textures/armour/back_scroll_purple.png";
        } else if (stack.getItem() == NarutoItems.backScroll && stack.getMetadata() == 3 && slot == 1) {
            return NarutoMod.modid + ":textures/armour/back_scroll_blue.png";
        } else if (stack.getItem() == NarutoItems.backScroll && stack.getMetadata() == 4 && slot == 1) {
            return NarutoMod.modid + ":textures/armour/back_scroll_green.png";
        } else if (stack.getItem() == NarutoItems.backScroll && stack.getMetadata() == 5 && slot == 1) {
            return NarutoMod.modid + ":textures/armour/back_scroll_red.png";
        } else if (stack.getItem() == NarutoItems.backScroll && stack.getMetadata() == 6 && slot == 1) {
            return NarutoMod.modid + ":textures/armour/back_scroll_black.png";
        } else {
            return NarutoMod.modid + ":textures/armour/back_scroll_white.png";
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return Icons[par1];
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        if (0 <= par1ItemStack.getMetadata() && par1ItemStack.getMetadata() <= 6) {
            return super.getUnlocalizedName() + "." + names[par1ItemStack.getMetadata()];
        } else {
            return super.getUnlocalizedName() + "." + names[1];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving,
                                         ItemStack itemStack, int armorSlot) {

        ModelBackScroll armorModel = null;
        if (itemStack != null) {
            if (itemStack.getItem() instanceof ItemArmourScroll) {
                int type = ((ItemArmor) itemStack.getItem()).armorType;

                if (type == 1) {
                    armorModel = new ModelBackScroll();
                    if (entityLiving instanceof EntityPlayer) {
                        DataWatcher dw = entityLiving.getDataWatcher();
                        armorModel.animationID = dw.getWatchableObjectString(DataWatcherIDs.poseClient);
                        armorModel.animationlastID = dw.getWatchableObjectString(DataWatcherIDs.lastPose);
                        armorModel.animationTick = dw.getWatchableObjectFloat(DataWatcherIDs.animationTick);
                    }
                }
            }
        }

        if (armorModel != null) {
            armorModel.bipedBody.showModel = armorSlot == 1;

            armorModel.isSneak = entityLiving.isSneaking();
            armorModel.isRiding = entityLiving.isRiding();
            armorModel.isChild = entityLiving.isChild();
            armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 : 0;
            armorModel.isSprinting = entityLiving.isSprinting();
            if (entityLiving instanceof EntityPlayer) {
                EntityPlayer entityPlayer = (EntityPlayer) entityLiving;
                if (itemStack != null && entityPlayer.getItemInUseCount() > 0) {
                    EnumAction enumaction = itemStack.getItemUseAction();

                    if (enumaction == EnumAction.block) {
                        armorModel.heldItemRight = 3;
                    } else if (enumaction == EnumAction.bow) {
                        armorModel.aimedBow = true;
                    } else if (enumaction == NarutoItems.Throw) {
                        if (FMLClientHandler.instance().getClient().thePlayer == entityPlayer) {
                            armorModel.isClientThrowing = true;
                        } else {
                            armorModel.isThrowing = true;
                        }
                    }
                }
            }
        }

        return armorModel;
    }

}
