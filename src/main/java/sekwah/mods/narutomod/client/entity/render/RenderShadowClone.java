package sekwah.mods.narutomod.client.entity.render;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
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
import sekwah.mods.narutomod.common.DataWatcherIDs;
import sekwah.mods.narutomod.common.entity.EntityShadowClone;
import sekwah.mods.narutomod.common.items.NarutoItems;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;
import static net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D;

@SideOnly(Side.CLIENT)
public class RenderShadowClone extends RendererLivingEntity {

    private ModelNinjaBiped modelBipedMain;
    private ModelNinjaBiped modelArmourChestplate;
    private ModelNinjaBiped modelArmour;

    private ModelNinjaBiped modelSkinOverlay;

    public RenderShadowClone() {
        super(new ModelNinjaBiped(0.0F), 0.5F);
        this.modelBipedMain = (ModelNinjaBiped) this.mainModel;
        this.modelArmourChestplate = new ModelNinjaBiped(1.0F);
        this.modelArmour = new ModelNinjaBiped(0.5F);

        this.modelSkinOverlay = new ModelNinjaBiped(0.001F);
    }

    /**
     * Set the specified armour model as the player model. Args: player, armorSlot, partialTick
     */
    protected int setArmorModel(EntityShadowClone par1EntityShadowClone, int par2, float par3) {
        ItemStack itemstack = par1EntityShadowClone.getEquipmentInSlot(par2 + 1);

        if (itemstack != null) {
            Item item = itemstack.getItem();

            if (item instanceof ItemArmor) {
                ItemArmor itemArmour = (ItemArmor) item;
                this.bindTexture(RenderBiped.getArmorResource(par1EntityShadowClone, itemstack, par2, null));
                ModelNinjaBiped modelbiped = par2 == 2 ? this.modelArmour : this.modelArmourChestplate;
                modelbiped.bipedHead.showModel = par2 == 0;
                modelbiped.bipedHeadwear.showModel = par2 == 0;
                modelbiped.bipedBody.showModel = par2 == 1 || par2 == 2;
                modelbiped.bipedLowerBody.showModel = par2 == 1 || par2 == 2;
                modelbiped.bipedRightArmUpper.showModel = par2 == 1;
                modelbiped.bipedRightArmLower.showModel = par2 == 1;
                modelbiped.bipedLeftArmUpper.showModel = par2 == 1;
                modelbiped.bipedLeftArmLower.showModel = par2 == 1;
                modelbiped.bipedRightLegUpper.showModel = par2 == 2 || par2 == 3;
                modelbiped.bipedRightLegLower.showModel = par2 == 2 || par2 == 3;
                modelbiped.bipedLeftLegUpper.showModel = par2 == 2 || par2 == 3;
                modelbiped.bipedLeftLegLower.showModel = par2 == 2 || par2 == 3;
                modelbiped = (ModelNinjaBiped) ForgeHooksClient.getArmorModel(par1EntityShadowClone, itemstack, par2, modelbiped);
                this.setRenderPassModel(modelbiped);
                modelbiped.swingProgress = this.mainModel.swingProgress;
                modelbiped.isRiding = this.mainModel.isRiding;
                modelbiped.isChild = this.mainModel.isChild;
                float f1 = 1.0F;

                //Move outside if to allow for more then just CLOTH
                int j = itemArmour.getColor(itemstack);
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

    protected void func_130220_b(EntityShadowClone par1EntityShadowClone, int par2, float par3) {
        ItemStack itemstack = par1EntityShadowClone.getEquipmentInSlot(3 - par2);

        if (itemstack != null) {
            Item item = itemstack.getItem();

            if (item instanceof ItemArmor) {
                this.bindTexture(RenderBiped.getArmorResource(par1EntityShadowClone, itemstack, par2, "overlay"));
                float f1 = 1.0F;
                GL11.glColor3f(f1, f1, f1);
            }
        }
    }

    public void func_130009_a(EntityShadowClone par1Entity, double par2, double par4, double par6, float par8, float par9) {
        float f2 = 1.0F;
        GL11.glColor3f(f2, f2, f2);
        ItemStack itemstack = par1Entity.getEquipmentInSlot(0);
        this.modelArmourChestplate.heldItemRight = this.modelArmour.heldItemRight = this.modelBipedMain.heldItemRight = itemstack != null ? 1 : 0;

        this.modelArmourChestplate.isSneak = this.modelArmour.isSneak = this.modelBipedMain.isSneak = par1Entity.isSneaking();
        this.modelArmourChestplate.isSprinting = this.modelArmour.isSprinting = this.modelBipedMain.isSprinting = par1Entity.isSprinting();

        if (FMLClientHandler.instance().getClient().thePlayer.getCommandSenderName().equals(par1Entity.getCustomNameTag())) {
            this.modelArmourChestplate.isChakraFocus = this.modelArmour.isChakraFocus = this.modelBipedMain.isChakraFocus = PlayerClientTickEvent.isChakraFocus;
        }

        if(this.modelBipedMain instanceof ModelNinjaBiped) {
            ItemStack stack = (par1Entity).getEquipmentInSlot(2);
            modelBipedMain.bipedHeadwear.showModel = !(stack != null && NarutoItems.shouldHideHair(stack.getItem()));
        }

        double d3 = par4 - (double) par1Entity.yOffset;

        if (par1Entity.isSneaking()) {
            d3 -= 0.125D;
        }

        super.doRender(par1Entity, par2, d3, par6, par8, par9);

        this.modelArmourChestplate.aimedBow = this.modelArmour.aimedBow = this.modelBipedMain.aimedBow = false;
        this.modelArmourChestplate.isThrowing = this.modelArmour.isThrowing = this.modelBipedMain.isThrowing = false;
        this.modelArmourChestplate.isSneak = this.modelArmour.isSneak = this.modelBipedMain.isSneak = false;
        this.modelArmourChestplate.isSprinting = this.modelArmour.isSprinting = this.modelBipedMain.isSprinting = false;
        this.modelArmourChestplate.isChakraFocus = this.modelArmour.isChakraFocus = this.modelBipedMain.isChakraFocus = false;
        this.modelArmourChestplate.heldItemRight = this.modelArmour.heldItemRight = this.modelBipedMain.heldItemRight = 0;

        this.modelArmourChestplate.swingProgress = this.modelArmour.swingProgress = this.modelBipedMain.swingProgress = this.getSwingProgress(par1Entity, par9);

        this.modelArmourChestplate.isSneak = this.modelArmour.isSneak = this.modelBipedMain.isSneak = par1Entity.isSneaking();
        this.modelArmourChestplate.isSprinting = this.modelArmour.isSprinting = this.modelBipedMain.isSprinting = par1Entity.isSprinting();

    }

    protected ResourceLocation func_110817_a(EntityShadowClone par1EntityShadowClone) {
        return par1EntityShadowClone.getLocationSkin();
    }

    /**
     * Method for adding special render rules
     */
    protected void renderSpecials(EntityShadowClone par1EntityShadowClone, float par2) {

        float f1 = 1.0F;
        GL11.glColor3f(f1, f1, f1);
        super.renderEquippedItems(par1EntityShadowClone, par2);
        super.renderArrowsStuckInEntity(par1EntityShadowClone, par2);
        ItemStack itemstack = par1EntityShadowClone.getEquipmentInSlot(3);

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

                this.renderManager.itemRenderer.renderItem(par1EntityShadowClone, itemstack, 0);
            } else if (itemstack.getItem() == Items.skull) {
                f1 = 1.0625F;
                GL11.glScalef(f1, -f1, -f1);
                GameProfile gameprofile = null;

                if (itemstack.hasTagCompound()) {
                    NBTTagCompound nbttagcompound = itemstack.getTagCompound();

                    if (nbttagcompound.hasKey("SkullOwner", 10)) {
                        gameprofile = NBTUtil.readGameProfileFromNBT(nbttagcompound.getCompoundTag("SkullOwner"));
                    } else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isNullOrEmpty(nbttagcompound.getString("SkullOwner"))) {
                        gameprofile = new GameProfile(null, nbttagcompound.getString("SkullOwner"));
                    }
                }

                TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack.getMetadata(), gameprofile);
            }

            GL11.glPopMatrix();
        }

        if (par1EntityShadowClone.getCustomNameTag().equals("deadmau5")) {

            this.bindTexture(par1EntityShadowClone.getLocationSkin());

            for (int i = 0; i < 2; ++i) {
                float f3 = par1EntityShadowClone.prevRotationYaw + (par1EntityShadowClone.rotationYaw - par1EntityShadowClone.prevRotationYaw) * par2 - (par1EntityShadowClone.prevRenderYawOffset + (par1EntityShadowClone.renderYawOffset - par1EntityShadowClone.prevRenderYawOffset) * par2);
                float f4 = par1EntityShadowClone.prevRotationPitch + (par1EntityShadowClone.rotationPitch - par1EntityShadowClone.prevRotationPitch) * par2;
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

        this.bindTexture(func_110817_a(par1EntityShadowClone));

        String[] MaskUsers = {"sekwah41", "Praneeth98", "Orcwaagh"};
        for (String MaskUser : MaskUsers) {
            if (MaskUser.endsWith(par1EntityShadowClone.getCustomNameTag())) {
                this.modelBipedMain.renderMask(0.0625F);
            }
        }

        boolean flag1 = !par1EntityShadowClone.isInvisible();
        boolean flag2 = false; //!par1EntityShadowClone.getHideCape();
        float f6;

        if (flag1 && flag2) {

            this.bindTexture(par1EntityShadowClone.getLocationSkin());
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
            double d0 = par1EntityShadowClone.field_71091_bM + (par1EntityShadowClone.field_71094_bP - par1EntityShadowClone.field_71091_bM) * (double) par2 - (par1EntityShadowClone.prevPosX + (par1EntityShadowClone.posX - par1EntityShadowClone.prevPosX) * (double) par2);
            double d1 = par1EntityShadowClone.field_71096_bN + (par1EntityShadowClone.field_71095_bQ - par1EntityShadowClone.field_71096_bN) * (double) par2 - (par1EntityShadowClone.prevPosY + (par1EntityShadowClone.posY - par1EntityShadowClone.prevPosY) * (double) par2);
            double d2 = par1EntityShadowClone.field_71097_bO + (par1EntityShadowClone.field_71085_bR - par1EntityShadowClone.field_71097_bO) * (double) par2 - (par1EntityShadowClone.prevPosZ + (par1EntityShadowClone.posZ - par1EntityShadowClone.prevPosZ) * (double) par2);
            f6 = par1EntityShadowClone.prevRenderYawOffset + (par1EntityShadowClone.renderYawOffset - par1EntityShadowClone.prevRenderYawOffset) * par2;
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

            float f10 = par1EntityShadowClone.prevRotationYaw + (par1EntityShadowClone.rotationYaw - par1EntityShadowClone.prevRotationYaw) * par2;
            f7 += MathHelper.sin((par1EntityShadowClone.prevDistanceWalkedModified + (par1EntityShadowClone.distanceWalkedModified - par1EntityShadowClone.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * f10;

            if (par1EntityShadowClone.isSneaking()) {
                f7 += 25.0F;
            }

            GL11.glRotatef(6.0F + f8 / 2.0F + f7, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(f9 / 2.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-f9 / 2.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            this.modelBipedMain.renderCloak(0.0625F);
            GL11.glPopMatrix();
        }

        ItemStack itemstack1 = par1EntityShadowClone.getEquipmentInSlot(0);

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

                if (par1EntityShadowClone.getEquipmentInSlot(0).stackSize > 0 && enumaction == EnumAction.block) {
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
                for (j = 0; j < itemstack1.getItem().getRenderPasses(itemstack1.getMetadata()); ++j) {
                    int k = itemstack1.getItem().getColorFromItemStack(itemstack1, j);
                    f13 = (float) (k >> 16 & 255) / 255.0F;
                    f12 = (float) (k >> 8 & 255) / 255.0F;
                    f6 = (float) (k & 255) / 255.0F;
                    GL11.glColor4f(f13, f12, f6, 1.0F);
                    this.renderManager.itemRenderer.renderItem(par1EntityShadowClone, itemstack1, j);
                }
            } else {
                j = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
                float f14 = (float) (j >> 16 & 255) / 255.0F;
                f13 = (float) (j >> 8 & 255) / 255.0F;
                f12 = (float) (j & 255) / 255.0F;
                GL11.glColor4f(f14, f13, f12, 1.0F);
                this.renderManager.itemRenderer.renderItem(par1EntityShadowClone, itemstack1, 0);
            }

            GL11.glPopMatrix();
        }


        ResourceLocation overlay = null;


        DataWatcher dw = par1EntityShadowClone.getDataWatcher();

        int eyeStatus = dw.getWatchableObjectInt(DataWatcherIDs.eyerenderer);

        overlay = NarutoMod.instance.sharinganHandler.getEyes(par1EntityShadowClone, eyeStatus);
        if(overlay == null) {
            eyeStatus = 0;
            dw.updateObject(DataWatcherIDs.eyerenderer,0);
        }

        // TODO add more colour values, this makes it so it can only be 1 colour and also makes it render nicer
        // in SEUS, if anything the eyes should be grayscale with this enabled. But it would screw up eyes which have
        // multiple colours...
        float[] glColor = NarutoMod.instance.sharinganHandler.getColor(par1EntityShadowClone.getCommandSenderName(), eyeStatus);

        // TODO readd
//        if (par1EntityShadowClone.getCommandSenderName().endsWith("Zaromaru")) {
//            overlay = rinneganOverlay;
//        }
//        else if(par1EntityShadowClone.getCommandSenderName().endsWith("Gingershadow")){
//            overlay = motherFuckingDEMONSOverlay;
//        }
//        else if(par1EntityShadowClone.getCommandSenderName().endsWith("owTreyalP")){
//            overlay = sharingan1eye2x2;
//        }
//        else if(par1EntityShadowClone.getCommandSenderName().endsWith("KawaiiRae")){
//            overlay = sharingan1eye2_2x2;
//        }
//        // add miches name
//        else if(par1EntityShadowClone.getCommandSenderName().endsWith("CrazyMtch42")){
//            overlay = byakugan;
//        }
//        else if(par1EntityShadowClone.getCommandSenderName().endsWith("liam3011")){
//            overlay = sharingan2Overlay;
//            glColor[1] = 0;
//            glColor[2] = 0;
//        }
        /*else if(par1EntityShadowClone.getCustomNameTag().endsWith("SSJHiro11")){
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

            this.bindTexture(func_110817_a(par1EntityShadowClone));

        }
    }

    protected void renderPlayerScale(EntityShadowClone par1EntityShadowClone, float par2) {
        float f1 = 0.9375F;
        GL11.glScalef(f1, f1, f1);
    }

    public void renderFirstPersonArm(EntityPlayer par1EntityPlayer) {
        float f = 1.0F;
        GL11.glColor3f(f, f, f);
        this.modelBipedMain.swingProgress = 0.0F;
        this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, par1EntityPlayer);
        this.modelBipedMain.bipedRightArm.render(0.0625F);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
        this.renderPlayerScale((EntityShadowClone) par1EntityLivingBase, par2);
    }

    protected void func_82408_c(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        this.func_130220_b((EntityShadowClone) par1EntityLivingBase, par2, par3);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        return this.setArmorModel((EntityShadowClone) par1EntityLivingBase, par2, par3);
    }

    protected void renderEquippedItems(EntityLivingBase par1EntityLivingBase, float par2) {
        this.renderSpecials((EntityShadowClone) par1EntityLivingBase, par2);
    }

    public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
        this.func_130009_a((EntityShadowClone) par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.func_110817_a((EntityShadowClone) par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is common
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.func_130009_a((EntityShadowClone) par1Entity, par2, par4, par6, par8, par9);
    }
}

