package sekwah.mods.narutomod.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.common.DataWatcherIDs;
import sekwah.mods.narutomod.items.itemmodels.ModelAnbuMask;

public class ItemArmourAnbuMask extends ItemArmor {

    // TODO add extra data so that way people can dye the headbands different colours. That will be epix :3
    // TODO above and add a crafting recipe or some way of obtaining them.
	public ItemArmourAnbuMask(ArmorMaterial par2EnumArmorMaterial,
                              int par3, int par4) {
			super(par2EnumArmorMaterial, par3, par4);
			setMaxDamage(0);

	}

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister) {

        this.itemIcon = par1IconRegister.registerIcon(NarutoMod.modid + ":" + (this.getUnlocalizedName().substring(5)));

    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot,
                                  String type) {
        if (stack.getItem() == NarutoItems.redAnbuMask && slot == 0) {
            return NarutoMod.modid + ":textures/armour/redAnbuMask.png";
        }
        return null;
    }


    // change so it is a normal armour renderer
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving,
                                    ItemStack itemStack, int armorSlot) {

        ModelAnbuMask armorModel = null;
        if(itemStack != null){
            if(itemStack.getItem() instanceof ItemArmourAnbuMask){
                int type = ((ItemArmor)itemStack.getItem()).armorType;

                if(type == 0){
                    armorModel = new ModelAnbuMask();
                    if(entityLiving instanceof EntityPlayer){
                        DataWatcher dw = entityLiving.getDataWatcher();
                        armorModel.animationID = dw.getWatchableObjectString(DataWatcherIDs.jutsuPose);
                        armorModel.animationlastID = dw.getWatchableObjectString(DataWatcherIDs.lastPose);
                        armorModel.animationTick = dw.getWatchableObjectFloat(DataWatcherIDs.animationTick);
                    }
                }
            }
        }

        if(armorModel != null){
            armorModel.bipedBody.showModel = armorSlot == 0;

            armorModel.isSneak = entityLiving.isSneaking();
            armorModel.isRiding = entityLiving.isRiding();
            armorModel.isChild = entityLiving.isChild();
            //armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 :0;
            armorModel.isSprinting = entityLiving.isSprinting();
            /*if(entityLiving instanceof EntityPlayer){
                EntityPlayer entityPlayer = (EntityPlayer) entityLiving;
                if (itemStack != null && entityPlayer.getItemInUseCount() > 0)
                {
                    EnumAction enumaction = itemStack.getItemUseAction();

                    if (enumaction == EnumAction.block)
                    {
                        armorModel.heldItemRight = 3;
                    }
                    else if (enumaction == EnumAction.bow)
                    {
                        armorModel.aimedBow = true;
                    }
                    else if (enumaction == NarutoItems.Throw)
                    {
                        if(FMLClientHandler.instance().getClient().thePlayer == entityPlayer){
                            armorModel.isClientThrowing = true;
                        }
                        else{
                            armorModel.isThrowing = true;
                        }
                    }
                }
            }*/
        }

        return armorModel;
    }

	/**
     * returns the action that specifies what animation to play when the items is being used
     */
	/**public EnumAction getItemUseAction(ItemStack par1ItemStack) // not exactly needed but when working its funny :3
	{
		if(par1ItemStack.getItemDamage() == 1){
			return EnumAction.eat;
		}
		return EnumAction.none;
    }*/

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(par1ItemStack.getItemDamage() != 1){
    		int i = EntityLiving.getArmorPosition(par1ItemStack) - 1;
            ItemStack itemstack1 = par3EntityPlayer.getCurrentArmor(i);

            if (itemstack1 == null)
            {
                par3EntityPlayer.setCurrentItemOrArmor(i + 1, par1ItemStack.copy()); //Forge: Vanilla bug fix associated with fixed setCurrentItemOrArmor indexs for players.
                par1ItemStack.stackSize = 0;
            }
    	}
    	else {
    		if (par3EntityPlayer.canEat(true))
            {
                par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
            }
    	}

        return par1ItemStack;
    }

}
