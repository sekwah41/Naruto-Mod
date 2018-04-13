package sekwah.mods.narutomod.items;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.items.itemmodels.ModelHeadband;

import java.util.List;

public class ItemArmourHeadband extends ItemArmor {
	
	@SideOnly(Side.CLIENT)
	private IIcon[] Icons;

    // TODO add extra data so that way people can dye the headbands different colours. That will be epix :3
    // TODO above and add a crafting recipe or some way of obtaining them.
	public ItemArmourHeadband(ArmorMaterial par2EnumArmorMaterial,
			int par3, int par4) {
			super(par2EnumArmorMaterial, par3, par4);
			setHasSubtypes(true);
			setMaxDamage(0);

	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister){
		Icons = new IIcon[7];
		// change for headband icons
		Icons[0] = par1IconRegister.registerIcon(NarutoMod.modid + ":headProtector");
		Icons[1] = par1IconRegister.registerIcon(NarutoMod.modid + ":custardHeadband");
		Icons[2] = par1IconRegister.registerIcon(NarutoMod.modid + ":headProtectorLava");
		Icons[3] = par1IconRegister.registerIcon(NarutoMod.modid + ":headProtectorYoutube");
		Icons[4] = par1IconRegister.registerIcon(NarutoMod.modid + ":backScrollGreen");
		Icons[5] = par1IconRegister.registerIcon(NarutoMod.modid + ":backScrollRed");
		Icons[6] = par1IconRegister.registerIcon(NarutoMod.modid + ":backScrollBlack");
	}

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {
        if(par1ItemStack.getItemDamage() == 3){
            par2List.add("\u00a77" + I18n.format("item.headProtector.Youtube.desc"));
        }
    }
	
	
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
            // You can also take a more direct approach and do each one individual but I prefer the lazy / right way
            for(int i = 0; i < 4; ++i){
                    list.add(new ItemStack(item, 1, i));
             }
     }
	
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1){
		return Icons[par1];
	}


	public static final String[] names = new String[] {"HiddenInTheLeaves"/*"HiddenInTheTrees"*/, "Custard", "Lava", "Youtube", "Green", "Red", "Black"};

	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		if( 0 <= par1ItemStack.getItemDamage() && par1ItemStack.getItemDamage() <= 6){
			return super.getUnlocalizedName() + "." + names[par1ItemStack.getItemDamage()];
		}
		else{
			return super.getUnlocalizedName() + "." + names[1];
		}
	}

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot,
                                  String type) {

        if (stack.getItem() == NarutoItems.headBand && stack.getItemDamage() == 0 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/leafvillage_headband.png";
        }
        else if (stack.getItem() == NarutoItems.headBand && stack.getItemDamage() == 1 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/custard_headband.png";
        }
        else if (stack.getItem() == NarutoItems.headBand && stack.getItemDamage() == 2 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/lavavillage_headband.png";
        }
        else if (stack.getItem() == NarutoItems.headBand && stack.getItemDamage() == 3 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/youtube_headband.png";
        }
        else if (stack.getItem() == NarutoItems.headBand && stack.getItemDamage() == 4 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/back_scroll_green.png";
        }
        else if (stack.getItem() == NarutoItems.headBand && stack.getItemDamage() == 5 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/back_scroll_red.png";
        }
        else if (stack.getItem() == NarutoItems.headBand && stack.getItemDamage() == 6 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/back_scroll_black.png";
        }
        else{
            return NarutoMod.modid + ":back_scroll_white.png";
        }
    }


    // change so it is a normal armour renderer
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving,
                                    ItemStack itemStack, int armorSlot) {

        ModelHeadband armorModel = null;
        if(itemStack != null){
            if(itemStack.getItem() instanceof ItemArmourHeadband){
                int type = ((ItemArmor)itemStack.getItem()).armorType;

                if(type == 0){
                    armorModel = new ModelHeadband();
                    if(entityLiving instanceof EntityPlayer){
                        DataWatcher dw = entityLiving.getDataWatcher();
                        armorModel.animationID = dw.getWatchableObjectString(20);
                        armorModel.animationlastID = dw.getWatchableObjectString(26);
                        armorModel.animationTick = dw.getWatchableObjectFloat(25);
                    }
                }
            }
        }

        if(armorModel != null){
            armorModel.bipedBody.showModel = armorSlot == 0;

            armorModel.isSneak = entityLiving.isSneaking();
            armorModel.isRiding = entityLiving.isRiding();
            armorModel.isChild = entityLiving.isChild();
            armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null ? 1 :0;
            armorModel.isSprinting = entityLiving.isSprinting();
            if(entityLiving instanceof EntityPlayer){
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
            }
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
