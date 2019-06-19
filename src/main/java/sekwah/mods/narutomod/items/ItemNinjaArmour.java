package sekwah.mods.narutomod.items;

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
import sekwah.mods.narutomod.client.player.models.ModelNinjaBiped;
import sekwah.mods.narutomod.common.DataWatcherIDs;

public class ItemNinjaArmour extends ItemArmor {

    private final String armourTexture;
    private ModelNinjaBiped modelArmor;
    @SideOnly(Side.CLIENT)
    private IIcon[] Icons;

    public void setModelArmor(ModelNinjaBiped armor) {
        this.modelArmor = armor;
    }

    public ItemNinjaArmour(ArmorMaterial par2EnumArmorMaterial,
                           int par3, int par4, String armourTexture) {
        super(par2EnumArmorMaterial, par3, par4);
        this.armourTexture = armourTexture;

    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(NarutoMod.modid + ":" + (this.getUnlocalizedName().substring(5)));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return NarutoMod.modid + ":" + this.armourTexture;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving,
                                    ItemStack itemStack, int armorSlot) {
        if (itemStack != null) {
            if (itemStack.getItem() instanceof ItemNinjaArmour) {
                int type = ((ItemArmor) itemStack.getItem()).armorType;

                if (type == 1) {
                    if (entityLiving instanceof EntityPlayer) {
                        DataWatcher dw = entityLiving.getDataWatcher();
                        modelArmor.animationID = dw.getWatchableObjectString(DataWatcherIDs.jutsuPose);
                        modelArmor.animationlastID = dw.getWatchableObjectString(DataWatcherIDs.lastPose);
                        modelArmor.animationTick = dw.getWatchableObjectFloat(DataWatcherIDs.animationTick);
                    }
                }
            }
        }

        if (modelArmor != null) {
            modelArmor.bipedBody.showModel = armorSlot == 1;

            modelArmor.isSneak = entityLiving.isSneaking();
            modelArmor.isRiding = entityLiving.isRiding();
            modelArmor.isChild = entityLiving.isChild();
            modelArmor.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 : 0;
            modelArmor.isSprinting = entityLiving.isSprinting();
            if (entityLiving instanceof EntityPlayer) {
                EntityPlayer entityPlayer = (EntityPlayer) entityLiving;
                if (itemStack != null && entityPlayer.getItemInUseCount() > 0) {
                    if (entityPlayer.isUsingItem()) {

                        EnumAction enumaction = entityPlayer.getItemInUse().getItemUseAction();

                        if (enumaction == EnumAction.block) {
                            modelArmor.heldItemRight = 3;
                        } else if (enumaction == EnumAction.bow) {
                            modelArmor.aimedBow = true;
                        } else if (enumaction == NarutoItems.Throw) {
                            modelArmor.isThrowing = true;
                        }
                    }
                }
            }
        }

        return modelArmor;
    }

}
