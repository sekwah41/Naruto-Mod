package sekwah.mods.narutomod.items;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.items.itemmodels.ModelBackBokken;

public class ItemArmourBokken extends ItemArmor {

    @SideOnly(Side.CLIENT)
    private IIcon[] Icons;

    public ItemArmourBokken(ArmorMaterial par2EnumArmorMaterial,
                                  int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setHasSubtypes(true);
        setMaxDamage(0);

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {

        this.itemIcon = par1IconRegister.registerIcon(NarutoMod.modid + ":" + (this.getUnlocalizedName().substring(5)));

    }

    /**
     @SideOnly(Side.CLIENT)
     public void registerIcons(IconRegister par1IconRegister){
     Icons = new Icon[7];
     Icons[0] = par1IconRegister.registerIcon(NarutoMod.modid + ":backBokken");
     }*/


    /**@SideOnly(Side.CLIENT)
    public void getSubItems(int itemID, CreativeTabs tabs, List list){
    // You can also take a more direct approach and do each one individual but I prefer the lazy / right way
    for(int i = 0; i < 7; ++i){
    list.add(new ItemStack(itemID, 1, i));
    }
    }*/

    /**
     @SideOnly(Side.CLIENT)
     public Icon getIconFromDamage(int par1){
     return Icons[par1];
     }*/


    /**
     * public static final String[] names = new String[] {"White", "Yellow", "Purple", "Blue", "Green", "Red", "Black"};
     * <p/>
     * public String getUnlocalizedName(ItemStack par1ItemStack)
     * {
     * if( 0 <= par1ItemStack.getItemDamage() && par1ItemStack.getItemDamage() <= 6){
     * return super.getUnlocalizedName() + "." + names[par1ItemStack.getItemDamage()];
     * }
     * else{
     * return super.getUnlocalizedName() + "." + names[1];
     * }
     * }
     */

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot,
                                  String type) {

        if (stack.getItem() == NarutoItems.backBokken && slot == 1) {
            return NarutoMod.modid + ":textures/armour/back_bokken.png";
        } else {
            return NarutoMod.modid + ":textures/armour/back_bokken.png";
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving,
                                    ItemStack itemStack, int armorSlot) {

        ModelBackBokken armorModel = null;
        if (itemStack != null) {
            if (itemStack.getItem() instanceof ItemArmourBokken) {
                int type = ((ItemArmor) itemStack.getItem()).armorType;

                if (type == 1) {
                    armorModel = new ModelBackBokken();
                    if (entityLiving instanceof EntityPlayer) {
                        DataWatcher dw = entityLiving.getDataWatcher();
                        armorModel.animationID = dw.getWatchableObjectString(20);
                        armorModel.animationlastID = dw.getWatchableObjectString(26);
                        armorModel.animationTick = dw.getWatchableObjectInt(25);
                    }
                }
            }
        }
        if (entityLiving.getHeldItem() != null) {
            if (entityLiving.getHeldItem().getItem() == NarutoItems.bokken) {
                //armorModel.blade.isHidden = true;
                armorModel.handle.isHidden = true;
            } else {
                //armorModel.blade.isHidden = false;
                armorModel.handle.isHidden = false;
            }
        } else {
            //armorModel.blade.isHidden = false;
            armorModel.handle.isHidden = false;
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
