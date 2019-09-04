package sekwah.mods.narutomod.common.items;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
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
import sekwah.mods.narutomod.common.DataWatcherIDs;
import sekwah.mods.narutomod.client.item.model.armour.ModelHeadband;

import java.util.List;

public class ItemArmourHeadbands extends ItemArmor {

    private final SUB_HEADBAND[] headbands;

    public enum SUB_HEADBAND {
        BLANK(0, "blankBlueHeadBand", "blank_headband"),
        BLANK_BLACK(1, "blankBlackHeadBand", "blankblack_headband"),
        BLANK_RED(2, "blankRedHeadBand", "blankred_headband"),
        LEAVES(3, "headProtector", "leafvillage_headband"),
        CUSTARD(4, "custardHeadband", "custard_headband"),
        LAVA(5, "headProtectorLava", "lavavillage_headband"),
        ROCK(6, "headProtectorRock", "rock_headband"),
        SCRATCHED(7, "headProtectorScratched", "leafvillage_headband_scratched"),
        RAINBOW(8, "headProtectorRainbow", "pride_headband"),
        LEAVES_BLACK(9, "headProtectorBlack", "leafvillage_headband_black"),
        SAND(10, "headProtectorSand", "sandblack_headband"),
        SOUND(11, "headProtectorSound", "soundvillage_headband"),
        MIST(12, "headProtectorMist", "mistblack_headband"),
        WATERFALL(13, "headProtectorWaterfall", "waterfallblack_headband"),
        CLOUD(14, "headProtectorCloud", "cloudblack_headband"),
        RAIN(15, "headProtectorRain", "rainblack_headband"),
        GRASS(16, "headProtectorGrass", "grassblack_headband"),
        YOUTUBE(17, "headProtectorYoutube", "youtube_headband"),
        LEAVES_BLACK_SCRATCHED(18, "headProtectorBlackScratched", "leafvillage_headband_black_scratched"),
        ROCK_SCRATCHED(19, "headProtectorRockScratched", "rock_headband_scratched"),
        SAND_SCRATCHED(20, "headProtectorSandScratched", "sandblack_headband_scratched"),
        SOUND_SCRATCHED(21, "headProtectorSoundScratched", "soundvillage_headband_scratched"),
        MIST_SCRATCHED(22, "headProtectorMistScratched", "mistblack_headband_scratched"),
        WATERFALL_SCRATCHED(23, "headProtectorWaterfallScratched", "waterfallblack_headband_scratched"),
        CLOUD_SCRATCHED(24, "headProtectorCloudScratched", "cloudblack_headband_scratched"),
        RAIN_SCRATCHED(25, "headProtectorRainScratched", "rainblack_headband_scratched"),
        GRASS_SCRATCHED(26, "headProtectorGrassScratched", "grassblack_headband_scratched");

        private final int subId;
        private final String name;
        private final String armourName;

        SUB_HEADBAND(int subID, String name, String armourName) {
            this.subId = subID;
            this.name = name;
            this.armourName = armourName;
        }

        public int getSubId() {
            return subId;
        }

        public String getName() {
            return name;
        }

        public String getArmourName() {
            return armourName;
        }
    }
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    private String name;

    // TODO add extra data so that way people can dye the headbands different colours. That will be epix :3
    // TODO above and add a crafting recipe or some way of obtaining them.
    public ItemArmourHeadbands(ArmorMaterial par2EnumArmorMaterial,
                               int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        this.headbands = SUB_HEADBAND.values();
        this.setHasSubtypes(true);
        this.setMaxDurability(0);

    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister){
        icons = new IIcon[this.headbands.length];
        for(int i = 0; i < this.headbands.length; i++) {
            icons[i] = par1IconRegister.registerIcon(NarutoMod.modid + ":" + this.headbands[i].getName());
        }
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4) {

    }



    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < icons.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1){
        return icons[par1];
    }

    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        if (par1ItemStack.getMetadata() <= this.headbands.length) {
            return "item." + this.headbands[par1ItemStack.getMetadata()].getName();
        } else {
            return super.getUnlocalizedName();
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot,
                                  String type) {
        return NarutoMod.modid + ":textures/armour/" + this.headbands[stack.getMetadata()].getArmourName() + ".png";
        /*if (stack.getItem() == NarutoItems.headBand && stack.getMetadata() == 0 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/leafvillage_headband.png";
        }
        else if (stack.getItem() == NarutoItems.headBand && stack.getMetadata() == 1 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/custard_headband.png";
        }
        else if (stack.getItem() == NarutoItems.headBand && stack.getMetadata() == 2 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/lavavillage_headband.png";
        }
        else if (stack.getItem() == NarutoItems.headBand && stack.getMetadata() == 3 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/rock_headband.png";
        }
        else if (stack.getItem() == NarutoItems.headBand && stack.getMetadata() == 4 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/leafvillage_headband_scratched.png";
        }
        else if (stack.getItem() == NarutoItems.headBand && stack.getMetadata() == 5 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/pride_headband.png";
        }
        else if (stack.getItem() == NarutoItems.headBand && stack.getMetadata() == 6 && slot == 0) {
            return NarutoMod.modid + ":textures/armour/back_scroll_black.png";
        }
        else{
            return NarutoMod.modid + ":back_scroll_white.png";
        }*/
    }


    // change so it is a normal armour renderer
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving,
                                    ItemStack itemStack, int armorSlot) {

        ModelHeadband armorModel = null;
        if(itemStack != null){
            if(itemStack.getItem() instanceof ItemArmourHeadbands){
                int type = ((ItemArmor)itemStack.getItem()).armorType;

                if(type == 0){
                    armorModel = new ModelHeadband();
                    if(entityLiving instanceof EntityPlayer){
                        DataWatcher dw = entityLiving.getDataWatcher();
                        armorModel.animationID = dw.getWatchableObjectString(DataWatcherIDs.poseClient);
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

    public Item setName(String name) {
        super.setUnlocalizedName(name);
        this.name = name;
        return this;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack) // not exactly needed but when working its funny :3
    {
        if(par1ItemStack.getMetadata() == SUB_HEADBAND.CUSTARD.getSubId()){
            return EnumAction.eat;
        }
        return super.getItemUseAction(par1ItemStack);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if(par1ItemStack.getMetadata() != 1){
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
