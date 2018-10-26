package sekwah.mods.narutomod.client.entity.render;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.client.PlayerClientTickEvent;
import sekwah.mods.narutomod.client.player.models.ModelNinjaBiped;
import sekwah.mods.narutomod.common.entity.EntitySubstitution;
import sekwah.mods.narutomod.sekcore.SkinLoader;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

@SideOnly(Side.CLIENT)
public class RenderSubstitution extends RendererLivingEntity {
    private static final ResourceLocation steveTextures = new ResourceLocation("textures/entity/steve.png");

    private static final ResourceLocation sharingan2Overlay = new ResourceLocation("narutomod:textures/skinOverlays/mongekyo.png");

    private static final ResourceLocation sharinganOverlay = new ResourceLocation("narutomod:textures/skinOverlays/sharingan.png");

    private static final ResourceLocation rinneganOverlay = new ResourceLocation("narutomod:textures/skinOverlays/rinnegan2x2.png");

    private static final ResourceLocation motherFuckingDEMONSOverlay = new ResourceLocation("narutomod:textures/skinOverlays/demonEyes2x2.png");

    private static final ResourceLocation byakugan = new ResourceLocation("narutomod:textures/skinOverlays/byakugan_2x2.png");

    private static final ResourceLocation sharingan1eye2x2 = new ResourceLocation("narutomod:textures/skinOverlays/sharingan1eye2x2.png");

    private static final ResourceLocation hiroCurseMark = new ResourceLocation("narutomod:textures/skinOverlays/hiro_cursemark.png");

    private static final ResourceLocation sharingan1eye2_2x2 = new ResourceLocation("narutomod:textures/skinOverlays/sharingan1eye2_2x2.png");

    private ModelNinjaBiped modelBipedMain;
    private ModelNinjaBiped modelArmorChestplate;
    private ModelNinjaBiped modelArmor;

    private ModelNinjaBiped modelSkinOverlay;

    public RenderSubstitution() {
        super(new ModelNinjaBiped(0.0F), 0.5F);
        this.modelBipedMain = (ModelNinjaBiped) this.mainModel;
        this.modelArmorChestplate = new ModelNinjaBiped(1.0F);
        this.modelArmor = new ModelNinjaBiped(0.5F);

        this.modelSkinOverlay = new ModelNinjaBiped(0.001F);
    }

    /**
     * Set the specified armor model as the player model. Args: player, armorSlot, partialTick
     */
    protected int setArmorModel(EntitySubstitution par1EntitySubstitution, int par2, float par3) {
        ItemStack itemstack = par1EntitySubstitution.getEquipmentInSlot(par2 + 1);

        if (itemstack != null) {
            Item item = itemstack.getItem();

            if (item instanceof ItemArmor) {
                ItemArmor itemarmor = (ItemArmor) item;
                this.bindTexture(RenderBiped.getArmorResource(par1EntitySubstitution, itemstack, par2, null));
                ModelNinjaBiped modelbiped = par2 == 2 ? this.modelArmor : this.modelArmorChestplate;
                modelbiped.bipedHead.showModel = par2 == 0;
                modelbiped.bipedHeadwear.showModel = par2 == 0;
                modelbiped.bipedBody.showModel = par2 == 1 || par2 == 2;
                modelbiped.bipedLowerBody.showModel = par2 == 1 || par2 == 2;
                modelbiped.bipedRightArmUpper.showModel = par2 == 1;
                modelbiped.bipedRightArmLower.showModel = par2 == 1;
                modelbiped.bipedLeftArmUpper.showModel = par2 == 1;
                modelbiped.bipedLeftArmLower.showModel = par2 == 1;
                modelbiped.bipedRightLeg.showModel = par2 == 2 || par2 == 3;
                modelbiped.bipedLeftLeg.showModel = par2 == 2 || par2 == 3;
                modelbiped = (ModelNinjaBiped) ForgeHooksClient.getArmorModel(par1EntitySubstitution, itemstack, par2, modelbiped);
                this.setRenderPassModel(modelbiped);
                modelbiped.onGround = this.mainModel.onGround;
                modelbiped.isRiding = this.mainModel.isRiding;
                modelbiped.isChild = this.mainModel.isChild;
                float f1 = 1.0F;

                //Move outside if to allow for more then just CLOTH
                int j = itemarmor.getColor(itemstack);
                if (j != -1) {
                    float f2 = (float) (j >> 16 & 255) / 255.0F;
                    float f3 = (float) (j >> 8 & 255) / 255.0F;
                    float f4 = (float) (j & 255) / 255.0F;
                    GL11.glColor3f(f1 * f2, f1 * f3, f1 * f4);

                    if (itemstack.isItemEnchanted()) {
                        return 31;
                    }

                    return 16;
                }

                GL11.glColor3f(f1, f1, f1);

                if (itemstack.isItemEnchanted()) {
                    return 15;
                }

                return 1;
            }
        }

        return -1;
    }

    protected void func_82408_c(EntitySubstitution par1EntitySubstitution, int par2, float par3) {
        ItemStack itemstack = par1EntitySubstitution.getEquipmentInSlot(3 - par2);

        if (itemstack != null) {
            Item item = itemstack.getItem();

            if (item instanceof ItemArmor) {
                this.bindTexture(RenderBiped.getArmorResource(par1EntitySubstitution, itemstack, par2, "overlay"));
                float f1 = 1.0F;
                GL11.glColor3f(f1, f1, f1);
            }
        }
    }

    public void func_130009_a(EntitySubstitution par1Entity, double par2, double par4, double par6, float par8, float par9) {
        float f2 = 1.0F;
        GL11.glColor3f(f2, f2, f2);
        ItemStack itemstack = par1Entity.getEquipmentInSlot(0);
        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = itemstack != null ? 1 : 0;

        if (itemstack != null && par1Entity.getEquipmentInSlot(0).stackSize > 0) {
            EnumAction enumaction = itemstack.getItemUseAction();

            if (enumaction == EnumAction.block) {
                this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 3;
            }
            //else if (enumaction == EnumAction.bow)
            //{
            //    this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = true;
            // }
            //else if (enumaction == EnumAction.Throw)
            //{
            //    this.modelArmorChestplate.isThrowing = this.modelArmor.isThrowing = this.modelBipedMain.isThrowing = true;
            //}
        }

        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = par1Entity.isSneaking();
        this.modelArmorChestplate.isSprinting = this.modelArmor.isSprinting = this.modelBipedMain.isSprinting = par1Entity.isSprinting();

        if (FMLClientHandler.instance().getClient().thePlayer.getCommandSenderName().equals(par1Entity.getCustomNameTag())) {
            this.modelArmorChestplate.isChakraFocus = this.modelArmor.isChakraFocus = this.modelBipedMain.isChakraFocus = PlayerClientTickEvent.isChakraFocus;
        }

        double d3 = par4 - (double) par1Entity.yOffset;

        if (par1Entity.isSneaking()) {
            d3 -= 0.125D;
        }

        super.doRender(par1Entity, par2, d3, par6, par8, par9);

        this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = false;
        this.modelArmorChestplate.isThrowing = this.modelArmor.isThrowing = this.modelBipedMain.isThrowing = false;
        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = false;
        this.modelArmorChestplate.isSprinting = this.modelArmor.isSprinting = this.modelBipedMain.isSprinting = false;
        this.modelArmorChestplate.isChakraFocus = this.modelArmor.isChakraFocus = this.modelBipedMain.isChakraFocus = false;
        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 0;
    }

    protected ResourceLocation func_110817_a(EntitySubstitution par1EntitySubstitution) {
        ResourceLocation locationSkin = AbstractClientPlayer.locationStevePng;

        if (par1EntitySubstitution.getCustomNameTag() != null && par1EntitySubstitution.getCustomNameTag().length() > 0) {
            locationSkin = SkinLoader.getUserSkin(par1EntitySubstitution.getCustomNameTag());
            SkinLoader.getDownloadImageSkin(locationSkin, null, par1EntitySubstitution.getCustomNameTag());
        }

        return locationSkin;
    }

    /**
     * Method for adding special render rules
     */
    protected void renderSpecials(EntitySubstitution par1EntitySubstitution, float par2) {

        float f1 = 1.0F;
        GL11.glColor3f(f1, f1, f1);
        super.renderEquippedItems(par1EntitySubstitution, par2);
        super.renderArrowsStuckInEntity(par1EntitySubstitution, par2);
        ItemStack itemstack = par1EntitySubstitution.getEquipmentInSlot(3);

        if (itemstack != null) {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedHead.postRender(0.0625F);
            float f2;

            if (itemstack != null && itemstack.getItem() instanceof ItemBlock) {
                IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, EQUIPPED);
                boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(EQUIPPED, itemstack, BLOCK_3D));

                if (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType())) {
                    f2 = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(f2, -f2, -f2);
                }

                this.renderManager.itemRenderer.renderItem(par1EntitySubstitution, itemstack, 0);
            } else if (itemstack.getItem() == Items.skull) {
                f1 = 1.0625F;
                GL11.glScalef(f1, -f1, -f1);
                GameProfile gameprofile = null;

                if (itemstack.hasTagCompound()) {
                    NBTTagCompound nbttagcompound = itemstack.getTagCompound();

                    if (nbttagcompound.hasKey("SkullOwner", 10)) {
                        gameprofile = NBTUtil.func_152459_a(nbttagcompound.getCompoundTag("SkullOwner"));
                    } else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty(nbttagcompound.getString("SkullOwner"))) {
                        gameprofile = new GameProfile(null, nbttagcompound.getString("SkullOwner"));
                    }
                }

                TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack.getItemDamage(), gameprofile);
            }

            GL11.glPopMatrix();
        }

        if (par1EntitySubstitution.getCustomNameTag().equals("deadmau5")) {

            ResourceLocation locationSkin = AbstractClientPlayer.locationStevePng;

            locationSkin = SkinLoader.getUserSkin(par1EntitySubstitution.getCustomNameTag());
            SkinLoader.getDownloadImageSkin(locationSkin, null, par1EntitySubstitution.getCustomNameTag());

            this.bindTexture(locationSkin);

            for (int i = 0; i < 2; ++i) {
                float f3 = par1EntitySubstitution.prevRotationYaw + (par1EntitySubstitution.rotationYaw - par1EntitySubstitution.prevRotationYaw) * par2 - (par1EntitySubstitution.prevRenderYawOffset + (par1EntitySubstitution.renderYawOffset - par1EntitySubstitution.prevRenderYawOffset) * par2);
                float f4 = par1EntitySubstitution.prevRotationPitch + (par1EntitySubstitution.rotationPitch - par1EntitySubstitution.prevRotationPitch) * par2;
                GL11.glPushMatrix();
                GL11.glRotatef(f3, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(f4, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.375F * (float) (i * 2 - 1), 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -0.375F, 0.0F);
                GL11.glRotatef(-f4, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-f3, 0.0F, 1.0F, 0.0F);
                float f5 = 1.3333334F;
                GL11.glScalef(f5, f5, f5);
                this.modelBipedMain.renderEars(0.0625F);
                GL11.glPopMatrix();
            }
        }

        this.bindTexture(func_110817_a(par1EntitySubstitution));

        String[] MaskUsers = {"sekwah41", "Praneeth98", "Orcwaagh"};
        for (String MaskUser : MaskUsers) {
            if (MaskUser.endsWith(par1EntitySubstitution.getCustomNameTag())) {
                ResourceLocation locationSkin = AbstractClientPlayer.locationStevePng;

                this.modelBipedMain.renderMask(0.0625F);
            }
        }

        boolean flag1 = !par1EntitySubstitution.isInvisible();
        boolean flag2 = false; //!par1EntitySubstitution.getHideCape();
        float f6;

        if (flag1 && flag2) {

            ResourceLocation locationSkin = null;

            locationSkin = SkinLoader.getUserSkin(par1EntitySubstitution.getCustomNameTag());
            SkinLoader.getDownloadImageSkin(locationSkin, null, par1EntitySubstitution.getCustomNameTag());

            this.bindTexture(locationSkin);
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
            double d0 = par1EntitySubstitution.field_71091_bM + (par1EntitySubstitution.field_71094_bP - par1EntitySubstitution.field_71091_bM) * (double) par2 - (par1EntitySubstitution.prevPosX + (par1EntitySubstitution.posX - par1EntitySubstitution.prevPosX) * (double) par2);
            double d1 = par1EntitySubstitution.field_71096_bN + (par1EntitySubstitution.field_71095_bQ - par1EntitySubstitution.field_71096_bN) * (double) par2 - (par1EntitySubstitution.prevPosY + (par1EntitySubstitution.posY - par1EntitySubstitution.prevPosY) * (double) par2);
            double d2 = par1EntitySubstitution.field_71097_bO + (par1EntitySubstitution.field_71085_bR - par1EntitySubstitution.field_71097_bO) * (double) par2 - (par1EntitySubstitution.prevPosZ + (par1EntitySubstitution.posZ - par1EntitySubstitution.prevPosZ) * (double) par2);
            f6 = par1EntitySubstitution.prevRenderYawOffset + (par1EntitySubstitution.renderYawOffset - par1EntitySubstitution.prevRenderYawOffset) * par2;
            double d3 = (double) MathHelper.sin(f6 * (float) Math.PI / 180.0F);
            double d4 = (double) (-MathHelper.cos(f6 * (float) Math.PI / 180.0F));
            float f7 = (float) d1 * 10.0F;

            if (f7 < -6.0F) {
                f7 = -6.0F;
            }

            if (f7 > 32.0F) {
                f7 = 32.0F;
            }

            float f8 = (float) (d0 * d3 + d2 * d4) * 100.0F;
            float f9 = (float) (d0 * d4 - d2 * d3) * 100.0F;

            if (f8 < 0.0F) {
                f8 = 0.0F;
            }

            float f10 = par1EntitySubstitution.prevRotationYaw + (par1EntitySubstitution.rotationYaw - par1EntitySubstitution.prevRotationYaw) * par2;
            f7 += MathHelper.sin((par1EntitySubstitution.prevDistanceWalkedModified + (par1EntitySubstitution.distanceWalkedModified - par1EntitySubstitution.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * f10;

            if (par1EntitySubstitution.isSneaking()) {
                f7 += 25.0F;
            }

            GL11.glRotatef(6.0F + f8 / 2.0F + f7, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(f9 / 2.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-f9 / 2.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            this.modelBipedMain.renderCloak(0.0625F);
            GL11.glPopMatrix();
        }

        ItemStack itemstack1 = par1EntitySubstitution.getEquipmentInSlot(0);

        if (itemstack1 != null) {
            GL11.glPushMatrix();
            //this.modelBipedMain.bipedRightArmUpper.postRender(0.0625F);

            this.modelBipedMain.bipedRightArmUpper.postRender(0.0625F);
            this.modelBipedMain.bipedRightArmLower.postRender(0.0625F);

            //GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
            GL11.glTranslatef(0.0125F, 0.2775F, 0.0625F);

            EnumAction enumaction = null;

            float f11;

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D));

            if (is3D || itemstack1.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack1.getItem()).getRenderType())) {
                f11 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f11 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f11, -f11, f11);
            } else if (itemstack1.getItem() == Items.bow) {
                f11 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f11, -f11, f11);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else if (itemstack1.getItem().isFull3D()) {
                f11 = 0.625F;

                if (itemstack1.getItem().shouldRotateAroundWhenRendering()) {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                if (par1EntitySubstitution.getEquipmentInSlot(0).stackSize > 0 && enumaction == EnumAction.block) {
                    GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                }

                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(f11, -f11, f11);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else {
                f11 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f11, f11, f11);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            float f12;
            float f13;
            int j;

            if (itemstack1.getItem().requiresMultipleRenderPasses()) {
                for (j = 0; j < itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); ++j) {
                    int k = itemstack1.getItem().getColorFromItemStack(itemstack1, j);
                    f13 = (float) (k >> 16 & 255) / 255.0F;
                    f12 = (float) (k >> 8 & 255) / 255.0F;
                    f6 = (float) (k & 255) / 255.0F;
                    GL11.glColor4f(f13, f12, f6, 1.0F);
                    this.renderManager.itemRenderer.renderItem(par1EntitySubstitution, itemstack1, j);
                }
            } else {
                j = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
                float f14 = (float) (j >> 16 & 255) / 255.0F;
                f13 = (float) (j >> 8 & 255) / 255.0F;
                f12 = (float) (j & 255) / 255.0F;
                GL11.glColor4f(f14, f13, f12, 1.0F);
                this.renderManager.itemRenderer.renderItem(par1EntitySubstitution, itemstack1, 0);
            }

            GL11.glPopMatrix();
        }

        ResourceLocation overlay = null;

        DataWatcher dw = par1EntitySubstitution.getDataWatcher();

        int eyeStatus = dw.getWatchableObjectInt(23);

        overlay = NarutoMod.instance.sharinganHandler.getEyes(par1EntitySubstitution.getCommandSenderName(), eyeStatus);
        if(overlay == null) {
            eyeStatus = 0;
            dw.updateObject(23,0);
        }

        // TODO add more colour values, this makes it so it can only be 1 colour and also makes it render nicer
        // in SEUS, if anything the eyes should be grayscale with this enabled. But it would screw up eyes which have
        // multiple colours...
        float[] glColor = NarutoMod.instance.sharinganHandler.getColor(par1EntitySubstitution.getCommandSenderName(), eyeStatus);

        // TODO readd
//        if (par1EntitySubstitution.getCommandSenderName().endsWith("Zaromaru")) {
//            overlay = rinneganOverlay;
//        }
//        else if(par1EntitySubstitution.getCommandSenderName().endsWith("Gingershadow")){
//            overlay = motherFuckingDEMONSOverlay;
//        }
//        else if(par1EntitySubstitution.getCommandSenderName().endsWith("owTreyalP")){
//            overlay = sharingan1eye2x2;
//        }
//        else if(par1EntitySubstitution.getCommandSenderName().endsWith("KawaiiRae")){
//            overlay = sharingan1eye2_2x2;
//        }
//        // add miches name
//        else if(par1EntitySubstitution.getCommandSenderName().endsWith("CrazyMtch42")){
//            overlay = byakugan;
//        }
//        else if(par1EntitySubstitution.getCommandSenderName().endsWith("liam3011")){
//            overlay = sharingan2Overlay;
//            glColor[1] = 0;
//            glColor[2] = 0;
//        }
        /*else if(par1EntitySubstitution.getCustomNameTag().endsWith("SSJHiro11")){
            overlay = this.hiroCurseMark;
        }*/

        if (overlay != null) {

            //GL11.glEnable(GL11.GL_BLEND);
            //GL11.glDisable(GL11.GL_ALPHA_TEST);
            //GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(true);

            this.bindTexture(overlay);
            // TODO BLAH BLAH BLAH add some shit here
            this.modelSkinOverlay.bipedHead.rotateAngleX = this.modelBipedMain.bipedHead.rotateAngleX;
            this.modelSkinOverlay.bipedHead.rotateAngleY = this.modelBipedMain.bipedHead.rotateAngleY;
            this.modelSkinOverlay.bipedHead.rotateAngleZ = this.modelBipedMain.bipedHead.rotateAngleZ;

            this.modelSkinOverlay.bipedHead.rotationPointX = this.modelBipedMain.bipedHead.rotationPointX;
            this.modelSkinOverlay.bipedHead.rotationPointY = this.modelBipedMain.bipedHead.rotationPointY;
            this.modelSkinOverlay.bipedHead.rotationPointZ = this.modelBipedMain.bipedHead.rotationPointZ;

            //this.modelSkinOverlay.bipedHead.render(0.0625F);

            float lastBrightnessX = OpenGlHelper.lastBrightnessX;
            float lastBrightnessY = OpenGlHelper.lastBrightnessY;

            char c0 = 61680;
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j / 1.0F, (float) k / 1.0F);

            GL11.glColor4f(glColor[0], glColor[1], glColor[2], 1.0F);

            this.modelSkinOverlay.bipedHead.render(0.0625F);

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            GL11.glEnable(GL11.GL_LIGHTING);

            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);

            this.bindTexture(func_110817_a(par1EntitySubstitution));

        }
    }

    protected void renderPlayerScale(EntitySubstitution par1EntitySubstitution, float par2) {
        float f1 = 0.9375F;
        GL11.glScalef(f1, f1, f1);
    }

    public void renderFirstPersonArm(EntityPlayer par1EntityPlayer) {
        float f = 1.0F;
        GL11.glColor3f(f, f, f);
        this.modelBipedMain.onGround = 0.0F;
        this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, par1EntityPlayer);
        this.modelBipedMain.bipedRightArm.render(0.0625F);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        this.renderPlayerScale((EntitySubstitution) par1EntityLivingBase, par2);
    }

    protected void func_82408_c(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        this.func_82408_c((EntitySubstitution) par1EntityLivingBase, par2, par3);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        return this.setArmorModel((EntitySubstitution) par1EntityLivingBase, par2, par3);
    }

    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
        this.renderSpecials((EntitySubstitution) par1EntityLivingBase, par2);
    }

    public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
        this.func_130009_a((EntitySubstitution) par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.func_110817_a((EntitySubstitution) par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is common
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.func_130009_a((EntitySubstitution) par1Entity, par2, par4, par6, par8, par9);
    }
}

