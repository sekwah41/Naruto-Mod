package sekwah.mods.narutomod.player;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import sekwah.mods.narutomod.NarutoMod;
import sekwah.mods.narutomod.animation.NarutoAnimator;
import sekwah.mods.narutomod.items.NarutoItems;
import sekwah.mods.narutomod.player.models.ModelNinjaBiped;
import sekwah.mods.narutomod.player.models.extras.ModelRibs;

@SideOnly(Side.CLIENT)
public class RenderNinjaPlayer extends RenderPlayer {
    public static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    private static final ResourceLocation steveTextures = new ResourceLocation("textures/entity/steve.png");

    private static final ResourceLocation susanooRibs = new ResourceLocation("narutomod:textures/otherStuff/susanoo.png");

    private static final String __OBFID = "CL_00001020";
    public ModelNinjaBiped modelBipedMain;
    public ModelNinjaBiped modelArmorChestplate;
    public ModelNinjaBiped modelArmor;
    private ModelNinjaBiped modelSkinOverlay;

    private ModelRibs modelRibs;

    private boolean isRenderingFirstPerson = false;

    public RenderNinjaPlayer() {
        this(new ModelNinjaBiped(0.0F), 0.5F);
        this.modelBipedMain = (ModelNinjaBiped) this.mainModel;
        this.modelArmorChestplate = new ModelNinjaBiped(1.0F);
        this.modelArmor = new ModelNinjaBiped(0.5F);

        modelRibs = new ModelRibs();

        this.modelSkinOverlay = new ModelNinjaBiped(0.001F);
    }

    public RenderNinjaPlayer(ModelBase p_i1261_1_, float p_i1261_2_) {
        this.mainModel = p_i1261_1_;
        this.shadowSize = p_i1261_2_;
    }

    public void firstPersonChanger() {
        this.modelBipedMain.bipedHead.isHidden = this.modelBipedMain.bipedHead.isHidden = this.modelArmorChestplate.bipedHead.isHidden = true;
        this.modelBipedMain.bipedHeadwear.isHidden = this.modelBipedMain.bipedHeadwear.isHidden = this.modelArmorChestplate.bipedHeadwear.isHidden = true;
        this.modelBipedMain.bipedMask.isHidden = true;
        this.modelBipedMain.bipedMaskMed.isHidden = true;
        this.modelBipedMain.bipedMaskSmall.isHidden = true;

        this.modelSkinOverlay.bipedHead.isHidden = this.modelSkinOverlay.bipedHead.isHidden = this.modelArmorChestplate.bipedHead.isHidden = true;
        this.modelSkinOverlay.bipedHeadwear.isHidden = this.modelSkinOverlay.bipedHeadwear.isHidden = this.modelArmorChestplate.bipedHeadwear.isHidden = true;
        this.modelSkinOverlay.bipedMask.isHidden = true;
        this.modelSkinOverlay.bipedMaskMed.isHidden = true;
        this.modelSkinOverlay.bipedMaskSmall.isHidden = true;

        this.isRenderingFirstPerson = true;
    }


    public void firstPersonChangeBack() {
        this.modelBipedMain.bipedHead.isHidden = this.modelBipedMain.bipedHead.isHidden = this.modelArmorChestplate.bipedHead.isHidden = false;
        this.modelBipedMain.bipedHeadwear.isHidden = this.modelBipedMain.bipedHeadwear.isHidden = this.modelArmorChestplate.bipedHeadwear.isHidden = false;
        this.modelBipedMain.bipedMask.isHidden = false;
        this.modelBipedMain.bipedMaskMed.isHidden = false;
        this.modelBipedMain.bipedMaskSmall.isHidden = false;

        this.modelSkinOverlay.bipedHead.isHidden = this.modelSkinOverlay.bipedHead.isHidden = this.modelArmorChestplate.bipedHead.isHidden = false;
        this.modelSkinOverlay.bipedHeadwear.isHidden = this.modelSkinOverlay.bipedHeadwear.isHidden = this.modelArmorChestplate.bipedHeadwear.isHidden = false;
        this.modelSkinOverlay.bipedMask.isHidden = false;
        this.modelSkinOverlay.bipedMaskMed.isHidden = false;
        this.modelSkinOverlay.bipedMaskSmall.isHidden = false;

        this.isRenderingFirstPerson = false;
    }


    public float getHeadXOffset() {
        return this.modelBipedMain.bipedHead.rotationPointX;
    }

    public float getHeadYOffset() {
        return this.modelBipedMain.bipedHead.rotationPointY;
    }

    public float getHeadZOffset() {
        return this.modelBipedMain.bipedHead.rotationPointZ;
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(AbstractClientPlayer p_77032_1_, int p_77032_2_, float p_77032_3_) {
        ItemStack itemstack = p_77032_1_.inventory.armorItemInSlot(3 - p_77032_2_);

        net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel event = new net.minecraftforge.client.event.RenderPlayerEvent.SetArmorModel(p_77032_1_, this, 3 - p_77032_2_, p_77032_3_, itemstack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.result != -1) {
            return event.result;
        }

        if (itemstack != null) {
            Item item = itemstack.getItem();

            if (item instanceof ItemArmor) {
                ItemArmor itemarmor = (ItemArmor) item;
                this.bindTexture(RenderBiped.getArmorResource(p_77032_1_, itemstack, p_77032_2_, null));
                ModelNinjaBiped modelbiped = p_77032_2_ == 2 ? this.modelArmor : this.modelArmorChestplate;
                modelbiped.bipedHead.showModel = p_77032_2_ == 0;
                modelbiped.bipedHeadwear.showModel = p_77032_2_ == 0;
                modelbiped.bipedBody.showModel = p_77032_2_ == 1 || p_77032_2_ == 2;
                modelbiped.bipedLowerBody.showModel = p_77032_2_ == 1 || p_77032_2_ == 2;
                modelbiped.bipedRightArmUpper.showModel = p_77032_2_ == 1;
                modelbiped.bipedRightArmLower.showModel = p_77032_2_ == 1;
                modelbiped.bipedLeftArm.showModel = p_77032_2_ == 1;
                modelbiped.bipedLeftArmUpper.showModel = p_77032_2_ == 1;
                modelbiped.bipedLeftArmLower.showModel = p_77032_2_ == 1;
                modelbiped.bipedRightArm.showModel = p_77032_2_ == 1;
                modelbiped.bipedLeftArm.showModel = p_77032_2_ == 1;
                modelbiped.bipedRightLeg.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
                modelbiped.bipedLeftLeg.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
                modelbiped.bipedRightLegUpper.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
                modelbiped.bipedLeftLegUpper.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
                modelbiped.bipedRightLegLower.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
                modelbiped.bipedLeftLegLower.showModel = p_77032_2_ == 2 || p_77032_2_ == 3;
                modelbiped = (ModelNinjaBiped) net.minecraftforge.client.ForgeHooksClient.getArmorModel(p_77032_1_, itemstack, p_77032_2_, modelbiped);
                this.setRenderPassModel(modelbiped);
                modelbiped.onGround = this.mainModel.onGround;
                modelbiped.isRiding = this.mainModel.isRiding;
                modelbiped.isChild = this.mainModel.isChild;

                //Move outside if to allow for more then just CLOTH
                int j = itemarmor.getColor(itemstack);
                if (j != -1) {
                    float f1 = (float) (j >> 16 & 255) / 255.0F;
                    float f2 = (float) (j >> 8 & 255) / 255.0F;
                    float f3 = (float) (j & 255) / 255.0F;
                    GL11.glColor3f(f1, f2, f3);

                    if (itemstack.isItemEnchanted()) {
                        return 31;
                    }

                    return 16;
                }

                GL11.glColor3f(1.0F, 1.0F, 1.0F);

                if (itemstack.isItemEnchanted()) {
                    return 15;
                }

                return 1;
            }
        }

        return -1;
    }

    protected void func_82408_c(AbstractClientPlayer p_82408_1_, int p_82408_2_, float p_82408_3_) {
        ItemStack itemstack = p_82408_1_.inventory.armorItemInSlot(3 - p_82408_2_);

        if (itemstack != null) {
            Item item = itemstack.getItem();

            if (item instanceof ItemArmor) {
                this.bindTexture(RenderBiped.getArmorResource(p_82408_1_, itemstack, p_82408_2_, "overlay"));
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            }
        }
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is common
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(AbstractClientPlayer p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        if (MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Pre(p_76986_1_, this, p_76986_9_)))
            return;
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        ItemStack itemstack = p_76986_1_.inventory.getCurrentItem();
        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = itemstack != null ? 1 : 0;

        if (itemstack != null && p_76986_1_.getItemInUseCount() > 0) {
            EnumAction enumaction = itemstack.getItemUseAction();

            if (enumaction == EnumAction.block) {
                this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 3;
            } else if (enumaction == EnumAction.bow) {
                this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = true;
            } else if (enumaction == NarutoItems.Throw) {
                //this.modelArmorChestplate.isThrowing = this.modelArmor.isThrowing = this.modelBipedMain.isThrowing = true;
                if (FMLClientHandler.instance().getClient().thePlayer == p_76986_1_) {
                    this.modelArmorChestplate.isClientThrowing = this.modelArmor.isClientThrowing = this.modelBipedMain.isClientThrowing = true;
                } else {
                    this.modelArmorChestplate.isThrowing = this.modelArmor.isThrowing = this.modelBipedMain.isThrowing = true;
                }
            }
        }


        if (FMLClientHandler.instance().getClient().thePlayer == p_76986_1_) {
            this.modelArmorChestplate.isClient = this.modelArmor.isClient = this.modelBipedMain.isClient = true;
        }

        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = p_76986_1_.isSneaking();
        this.modelArmorChestplate.isSprinting = this.modelArmor.isSprinting = this.modelBipedMain.isSprinting = p_76986_1_.isSprinting();

        DataWatcher dw = p_76986_1_.getDataWatcher();
        EntityClientPlayerMP playerMP = FMLClientHandler.instance().getClient().thePlayer;

        if (p_76986_1_ != playerMP) {
            NarutoAnimator.updateEntity(dw, playerMP, NarutoAnimator.playerPoses);
        }


        // 20 is current animation, 27 is last client animation. Using 27 as it is set to the value of 20 when 25 is set back to 0.
        this.modelArmorChestplate.animationID = this.modelArmor.animationID = this.modelBipedMain.animationID = dw.getWatchableObjectString(27);
        this.modelArmorChestplate.animationlastID = this.modelArmor.animationlastID = this.modelBipedMain.animationlastID = dw.getWatchableObjectString(26);
        this.modelArmorChestplate.animationTick = this.modelArmor.animationTick = this.modelBipedMain.animationTick = dw.getWatchableObjectFloat(25);

        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = p_76986_1_.isSneaking();
        this.modelArmorChestplate.isSprinting = this.modelArmor.isSprinting = this.modelBipedMain.isSprinting = p_76986_1_.isSprinting();
        double d3 = p_76986_4_ - (double) p_76986_1_.yOffset;

        if (p_76986_1_.isSneaking() && !(p_76986_1_ instanceof EntityPlayerSP)) {
            d3 -= 0.125D;
        }

        this.doFinalRender(p_76986_1_, p_76986_2_, d3, p_76986_6_, p_76986_8_, p_76986_9_);
        this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = false;
        this.modelArmorChestplate.isSprinting = this.modelArmor.isSprinting = this.modelBipedMain.isSprinting = false;
        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = false;
        this.modelArmorChestplate.isThrowing = this.modelArmor.isThrowing = this.modelBipedMain.isThrowing = false;
        this.modelArmorChestplate.isClientThrowing = this.modelArmor.isClientThrowing = this.modelBipedMain.isClientThrowing = false;
        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 0;
        MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Post(p_76986_1_, this, p_76986_9_));
    }

    /**
     * Returns a rotation angle that is inbetween two other rotation angles. par1 and par2 are the angles between which
     * to interpolate, par3 is probably a float between 0.0 and 1.0 that tells us where "between" the two angles we are.
     * Example: par1 = 30, par2 = 50, par3 = 0.5, then return = 40
     */
    private float interpolateRotation(float p_77034_1_, float p_77034_2_, float p_77034_3_) {
        float f3;

        for (f3 = p_77034_2_ - p_77034_1_; f3 < -180.0F; f3 += 360.0F) {
        }

        while (f3 >= 180.0F) {
            f3 -= 360.0F;
        }

        return p_77034_1_ + p_77034_3_ * f3;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is common
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doFinalRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        if (MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Pre(p_76986_1_, this, p_76986_2_, p_76986_4_, p_76986_6_)))
            return;
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        this.mainModel.onGround = this.renderSwingProgress(p_76986_1_, p_76986_9_);

        if (this.renderPassModel != null) {
            this.renderPassModel.onGround = this.mainModel.onGround;
        }

        this.mainModel.isRiding = p_76986_1_.isRiding();

        if (this.renderPassModel != null) {
            this.renderPassModel.isRiding = this.mainModel.isRiding;
        }

        this.mainModel.isChild = p_76986_1_.isChild();

        if (this.renderPassModel != null) {
            this.renderPassModel.isChild = this.mainModel.isChild;
        }

        try {
            float f2 = this.interpolateRotation(p_76986_1_.prevRenderYawOffset, p_76986_1_.renderYawOffset, p_76986_9_);
            float f3 = this.interpolateRotation(p_76986_1_.prevRotationYawHead, p_76986_1_.rotationYawHead, p_76986_9_);
            float f4;

            if (p_76986_1_.isRiding() && p_76986_1_.ridingEntity instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase1 = (EntityLivingBase) p_76986_1_.ridingEntity;
                f2 = this.interpolateRotation(entitylivingbase1.prevRenderYawOffset, entitylivingbase1.renderYawOffset, p_76986_9_);
                f4 = MathHelper.wrapAngleTo180_float(f3 - f2);

                if (f4 < -85.0F) {
                    f4 = -85.0F;
                }

                if (f4 >= 85.0F) {
                    f4 = 85.0F;
                }

                f2 = f3 - f4;

                if (f4 * f4 > 2500.0F) {
                    f2 += f4 * 0.2F;
                }
            }

            float f13 = p_76986_1_.prevRotationPitch + (p_76986_1_.rotationPitch - p_76986_1_.prevRotationPitch) * p_76986_9_;
            this.renderLivingAt(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
            f4 = this.handleRotationFloat(p_76986_1_, p_76986_9_);
            this.rotateCorpse(p_76986_1_, f4, f2, p_76986_9_);
            float f5 = 0.0625F;
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(-1.0F, -1.0F, 1.0F);
            this.preRenderCallback(p_76986_1_, p_76986_9_);
            GL11.glTranslatef(0.0F, -24.0F * f5 - 0.0078125F, 0.0F);
            float f6 = p_76986_1_.prevLimbSwingAmount + (p_76986_1_.limbSwingAmount - p_76986_1_.prevLimbSwingAmount) * p_76986_9_;
            float f7 = p_76986_1_.limbSwing - p_76986_1_.limbSwingAmount * (1.0F - p_76986_9_);

            if (p_76986_1_.isChild()) {
                f7 *= 3.0F;
            }

            if (f6 > 1.0F) {
                f6 = 1.0F;
            }

            GL11.glEnable(GL11.GL_ALPHA_TEST);
            this.mainModel.setLivingAnimations(p_76986_1_, f7, f6, p_76986_9_);
            this.renderModel(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
            int j;
            float f8;
            float f9;
            float f10;

            for (int i = 0; i < 4; ++i) {
                j = this.shouldRenderPass(p_76986_1_, i, p_76986_9_);

                if (j > 0) {
                    this.renderPassModel.setLivingAnimations(p_76986_1_, f7, f6, p_76986_9_);
                    this.renderPassModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);

                    if ((j & 240) == 16) {
                        this.func_82408_c(p_76986_1_, i, p_76986_9_);
                        this.renderPassModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                    }

                    if ((j & 15) == 15) {
                        f8 = (float) p_76986_1_.ticksExisted + p_76986_9_;
                        this.bindTexture(RES_ITEM_GLINT);
                        GL11.glEnable(GL11.GL_BLEND);
                        f9 = 0.5F;
                        GL11.glColor4f(f9, f9, f9, 1.0F);
                        GL11.glDepthFunc(GL11.GL_EQUAL);
                        GL11.glDepthMask(false);

                        for (int k = 0; k < 2; ++k) {
                            GL11.glDisable(GL11.GL_LIGHTING);
                            f10 = 0.76F;
                            GL11.glColor4f(0.5F * f10, 0.25F * f10, 0.8F * f10, 1.0F);
                            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                            GL11.glMatrixMode(GL11.GL_TEXTURE);
                            GL11.glLoadIdentity();
                            float f11 = f8 * (0.001F + (float) k * 0.003F) * 20.0F;
                            float f12 = 0.33333334F;
                            GL11.glScalef(f12, f12, f12);
                            GL11.glRotatef(30.0F - (float) k * 60.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glTranslatef(0.0F, f11, 0.0F);
                            GL11.glMatrixMode(GL11.GL_MODELVIEW);
                            this.renderPassModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                        }

                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                        GL11.glMatrixMode(GL11.GL_TEXTURE);
                        GL11.glDepthMask(true);
                        GL11.glLoadIdentity();
                        GL11.glMatrixMode(GL11.GL_MODELVIEW);
                        GL11.glEnable(GL11.GL_LIGHTING);
                        GL11.glDisable(GL11.GL_BLEND);
                        GL11.glDepthFunc(GL11.GL_LEQUAL);
                    }

                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                }
            }

            GL11.glDepthMask(true);
            this.renderEquippedItems(p_76986_1_, p_76986_9_);
            float f14 = p_76986_1_.getBrightness(p_76986_9_);
            j = this.getColorMultiplier(p_76986_1_, f14, p_76986_9_);
            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

            if ((j >> 24 & 255) > 0 || p_76986_1_.hurtTime > 0 || p_76986_1_.deathTime > 0) {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glDepthFunc(GL11.GL_EQUAL);

                if (p_76986_1_.hurtTime > 0 || p_76986_1_.deathTime > 0) {
                    GL11.glColor4f(f14, 0.0F, 0.0F, 0.4F);
                    this.mainModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);

                    for (int l = 0; l < 4; ++l) {
                        if (this.inheritRenderPass(p_76986_1_, l, p_76986_9_) >= 0) {
                            GL11.glColor4f(f14, 0.0F, 0.0F, 0.4F);
                            this.renderPassModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                        }
                    }
                }

                if ((j >> 24 & 255) > 0) {
                    f8 = (float) (j >> 16 & 255) / 255.0F;
                    f9 = (float) (j >> 8 & 255) / 255.0F;
                    float f15 = (float) (j & 255) / 255.0F;
                    f10 = (float) (j >> 24 & 255) / 255.0F;
                    GL11.glColor4f(f8, f9, f15, f10);
                    this.mainModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);

                    for (int i1 = 0; i1 < 4; ++i1) {
                        if (this.inheritRenderPass(p_76986_1_, i1, p_76986_9_) >= 0) {
                            GL11.glColor4f(f8, f9, f15, f10);
                            this.renderPassModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                        }
                    }
                }

                GL11.glDepthFunc(GL11.GL_LEQUAL);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
            }

            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        } catch (Exception exception) {
            NarutoMod.logger.error("Couldn't render entity", exception);
        }

        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
        this.passSpecialRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
        MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post(p_76986_1_, this, p_76986_2_, p_76986_4_, p_76986_6_));
    }

    /**
     * Renders the model in RenderLiving
     */
    protected void renderModel(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
        this.bindEntityTexture(p_77036_1_);

        if (!p_77036_1_.isInvisible()) {
            this.mainModel.render(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
        } else if (!p_77036_1_.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
            this.mainModel.render(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glPopMatrix();
            GL11.glDepthMask(true);
        } else {
            this.mainModel.setRotationAngles(p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_, p_77036_1_);
        }

        /*this.bindTexture(susanooRibs);
        GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.6F);
        GL11.glScalef(1.1F, 1.1F, 1.1F);
        GL11.glTranslatef(0,-0.1F,0);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
        modelRibs.render(p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        this.bindEntityTexture(p_77036_1_);*/

    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(AbstractClientPlayer p_110775_1_) {
        return p_110775_1_.getLocationSkin();
    }

    protected void renderEquippedItems(AbstractClientPlayer p_77029_1_, float p_77029_2_) {
        net.minecraftforge.client.event.RenderPlayerEvent.Specials.Pre event = new net.minecraftforge.client.event.RenderPlayerEvent.Specials.Pre(p_77029_1_, this, p_77029_2_);
        if (MinecraftForge.EVENT_BUS.post(event)) return;
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        //super.renderEquippedItems(p_77029_1_, p_77029_2_);
        super.renderArrowsStuckInEntity(p_77029_1_, p_77029_2_);
        ItemStack itemstack = p_77029_1_.inventory.armorItemInSlot(3);

        if (itemstack != null && event.renderHelmet) {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedHead.postRender(0.0625F);
            float f1;

            if (itemstack.getItem() instanceof ItemBlock) {
                net.minecraftforge.client.IItemRenderer customRenderer = net.minecraftforge.client.MinecraftForgeClient.getItemRenderer(itemstack, net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED);
                boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED, itemstack, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));

                if (is3D || RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType())) {
                    f1 = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(f1, -f1, -f1);
                }

                this.renderManager.itemRenderer.renderItem(p_77029_1_, itemstack, 0);
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

        float f2;

        if (p_77029_1_.getCommandSenderName().equals("deadmau5") && p_77029_1_.func_152123_o()) {
            this.bindTexture(p_77029_1_.getLocationSkin());

            for (int j = 0; j < 2; ++j) {
                float f9 = p_77029_1_.prevRotationYaw + (p_77029_1_.rotationYaw - p_77029_1_.prevRotationYaw) * p_77029_2_ - (p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_);
                float f10 = p_77029_1_.prevRotationPitch + (p_77029_1_.rotationPitch - p_77029_1_.prevRotationPitch) * p_77029_2_;
                GL11.glPushMatrix();
                GL11.glRotatef(f9, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(f10, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.375F * (float) (j * 2 - 1), 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -0.375F, 0.0F);
                GL11.glRotatef(-f10, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-f9, 0.0F, 1.0F, 0.0F);
                f2 = 1.3333334F;
                GL11.glScalef(f2, f2, f2);
                this.modelBipedMain.renderEars(0.0625F);
                GL11.glPopMatrix();
            }
        }

        String[] MaskUsers = {"sekwah41", "Praneeth98", "Orcwaagh"};
        for (int mask = 0; mask < MaskUsers.length; ++mask) {
            if (p_77029_1_.getCommandSenderName().endsWith(MaskUsers[mask]) && p_77029_1_.func_152123_o()) {
                this.bindTexture(p_77029_1_.getLocationSkin());
                this.modelBipedMain.renderMask(0.0625F);
            }
        }

        boolean flag = p_77029_1_.func_152122_n();
        flag = event.renderCape && flag;
        float f4;

        if (flag && !p_77029_1_.isInvisible() && !p_77029_1_.getHideCape()) {
            this.bindTexture(p_77029_1_.getLocationCape());
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
            double d3 = p_77029_1_.field_71091_bM + (p_77029_1_.field_71094_bP - p_77029_1_.field_71091_bM) * (double) p_77029_2_ - (p_77029_1_.prevPosX + (p_77029_1_.posX - p_77029_1_.prevPosX) * (double) p_77029_2_);
            double d4 = p_77029_1_.field_71096_bN + (p_77029_1_.field_71095_bQ - p_77029_1_.field_71096_bN) * (double) p_77029_2_ - (p_77029_1_.prevPosY + (p_77029_1_.posY - p_77029_1_.prevPosY) * (double) p_77029_2_);
            double d0 = p_77029_1_.field_71097_bO + (p_77029_1_.field_71085_bR - p_77029_1_.field_71097_bO) * (double) p_77029_2_ - (p_77029_1_.prevPosZ + (p_77029_1_.posZ - p_77029_1_.prevPosZ) * (double) p_77029_2_);
            f4 = p_77029_1_.prevRenderYawOffset + (p_77029_1_.renderYawOffset - p_77029_1_.prevRenderYawOffset) * p_77029_2_;
            double d1 = (double) MathHelper.sin(f4 * (float) Math.PI / 180.0F);
            double d2 = (double) (-MathHelper.cos(f4 * (float) Math.PI / 180.0F));
            float f5 = (float) d4 * 10.0F;
            if (f5 < -6.0F) {
                f5 = -6.0F;
            }

            if (f5 > 32.0F) {
                f5 = 32.0F;
            }

            float f6 = (float) (d3 * d1 + d0 * d2) * 100.0F;
            float f7 = (float) (d3 * d2 - d0 * d1) * 100.0F;

            if (f6 < 0.0F) {
                f6 = 0.0F;
            }

            float f8 = p_77029_1_.prevCameraYaw + (p_77029_1_.cameraYaw - p_77029_1_.prevCameraYaw) * p_77029_2_;
            f5 += MathHelper.sin((p_77029_1_.prevDistanceWalkedModified + (p_77029_1_.distanceWalkedModified - p_77029_1_.prevDistanceWalkedModified) * p_77029_2_) * 6.0F) * 32.0F * f8;

            if (p_77029_1_.isSneaking()) {
                f5 += 25.0F;
            }

            if (p_77029_1_.isSprinting()) {
                f7 += 45.0F;
                GL11.glTranslatef(0.0F, 0.0F, -0.125F);
            }

            GL11.glRotatef(6.0F + f6 / 2.0F + f5, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(f7 / 2.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-f7 / 2.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            this.modelBipedMain.renderCloak(0.0625F);
            GL11.glPopMatrix();
        }

        ItemStack itemstack1 = p_77029_1_.inventory.getCurrentItem();

        if (itemstack1 != null && event.renderItem) {
            GL11.glPushMatrix();
            //this.modelBipedMain.bipedRightArm.postRender(0.0625F);
            //this.modelBipedMain.bipedRightArmUpper.postRender(0.0625F);

            this.modelBipedMain.bipedRightArmUpper.postRender(0.0625F);
            this.modelBipedMain.bipedRightArmLower.postRender(0.0625F);

            //GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
            GL11.glTranslatef(0.0125F, 0.2775F, 0.0625F);

            if (p_77029_1_.fishEntity != null) {
                itemstack1 = new ItemStack(Items.stick);
            }

            EnumAction enumaction = null;

            if (p_77029_1_.getItemInUseCount() > 0) {
                enumaction = itemstack1.getItemUseAction();
            }

            net.minecraftforge.client.IItemRenderer customRenderer = net.minecraftforge.client.MinecraftForgeClient.getItemRenderer(itemstack1, net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED);
            boolean is3D = (customRenderer != null && customRenderer.shouldUseRenderHelper(net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, net.minecraftforge.client.IItemRenderer.ItemRendererHelper.BLOCK_3D));

            if (is3D || itemstack1.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack1.getItem()).getRenderType())) {
                f2 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f2 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f2, -f2, f2);
            } else if (itemstack1.getItem() == Items.bow) {
                f2 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f2, -f2, f2);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else if (itemstack1.getItem().isFull3D()) {
                f2 = 0.625F;

                if (itemstack1.getItem().shouldRotateAroundWhenRendering()) {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                if (p_77029_1_.getItemInUseCount() > 0 && enumaction == EnumAction.block) {
                    GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                }

                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(f2, -f2, f2);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else {
                f2 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f2, f2, f2);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            float f3;
            int k;
            float f12;

            if (itemstack1.getItem().requiresMultipleRenderPasses()) {
                for (k = 0; k < itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); ++k) {
                    int i = itemstack1.getItem().getColorFromItemStack(itemstack1, k);
                    f12 = (float) (i >> 16 & 255) / 255.0F;
                    f3 = (float) (i >> 8 & 255) / 255.0F;
                    f4 = (float) (i & 255) / 255.0F;
                    GL11.glColor4f(f12, f3, f4, 1.0F);
                    this.renderManager.itemRenderer.renderItem(p_77029_1_, itemstack1, k);
                }
            } else {
                k = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
                float f11 = (float) (k >> 16 & 255) / 255.0F;
                f12 = (float) (k >> 8 & 255) / 255.0F;
                f3 = (float) (k & 255) / 255.0F;
                GL11.glColor4f(f11, f12, f3, 1.0F);
                this.renderManager.itemRenderer.renderItem(p_77029_1_, itemstack1, 0);
            }

            GL11.glPopMatrix();
        }

        ResourceLocation overlay = null;

        DataWatcher dw = p_77029_1_.getDataWatcher();

        int eyeStatus = dw.getWatchableObjectInt(23);

        overlay = NarutoMod.instance.sharinganHandler.getEyes(p_77029_1_.getCommandSenderName(), eyeStatus);

        // TODO add more colour values, this makes it so it can only be 1 colour and also makes it render nicer
        // in SEUS, if anything the eyes should be grayscale with this enabled. But it would screw up eyes which have
        // multiple colours...
        float[] glColor = NarutoMod.instance.sharinganHandler.getColor(p_77029_1_.getCommandSenderName(), eyeStatus);

        if (overlay != null) {

            //if(p_76986_1_.getEntityData().getBoolean("showEyes")){

            //GL11.glEnable(GL11.GL_BLEND);
            //GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_LIGHTING);

            GL11.glDepthMask(true);

            this.bindTexture(overlay);
            //this.bindTexture(sharinganOverlay);

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


            //this.bindTexture(new ResourceLocation("narutomod:textures/particles/sakuraParticle.png"));
            //Tessellator tessellator = Tessellator.instance;

            //this.modelBipedMain.bipedHead.postRender(0.0625F);

            /**tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)(10) / 64, (double)(12) / 32);
            tessellator.addVertexWithUV(0.0D, -2.0D, 0.0D, (double)(11) / 64, (double)(12) / 32);
            tessellator.addVertexWithUV(-2.0D, -2.0D, 0.0D, (double)(11) / 64, (double)(13) / 32);
            tessellator.addVertexWithUV(-2.0D, 0.0D, 0.0D, (double)(10) / 64, (double)(13) / 32);
            tessellator.draw();*/

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            GL11.glEnable(GL11.GL_ALPHA_TEST);

            GL11.glEnable(GL11.GL_LIGHTING);

            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);

            this.bindTexture(p_77029_1_.getLocationSkin());

        }

        /*if (p_77029_1_.getCommandSenderName().endsWith("liam3011")) {
            //if(p_76986_1_.getEntityData().getBoolean("showEyes")){

            //GL11.glEnable(GL11.GL_BLEND);
            //GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_LIGHTING);

            GL11.glDepthMask(true);

            this.bindTexture(sharingan2Overlay);
            //this.bindTexture(sharinganOverlay);

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


            GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);

            this.modelSkinOverlay.bipedHead.render(0.0625F);


            //this.bindTexture(new ResourceLocation("narutomod:textures/particles/sakuraParticle.png"));
            //Tessellator tessellator = Tessellator.instance;

            //this.modelBipedMain.bipedHead.postRender(0.0625F);

            *//**tessellator.startDrawingQuads();
             tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)(10) / 64, (double)(12) / 32);
             tessellator.addVertexWithUV(0.0D, -2.0D, 0.0D, (double)(11) / 64, (double)(12) / 32);
             tessellator.addVertexWithUV(-2.0D, -2.0D, 0.0D, (double)(11) / 64, (double)(13) / 32);
             tessellator.addVertexWithUV(-2.0D, 0.0D, 0.0D, (double)(10) / 64, (double)(13) / 32);
             tessellator.draw();*//*

            GL11.glEnable(GL11.GL_ALPHA_TEST);

            GL11.glEnable(GL11.GL_LIGHTING);

            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);

            this.bindTexture(p_77029_1_.getLocationSkin());

        }*/



        /*if (p_77029_1_.getCommandSenderName().endsWith("SSJHiro11")) {
            //if(par1AbstractClientPlayer.getEntityData().getBoolean("showEyes")){

            //GL11.glEnable(GL11.GL_BLEND);
            //GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDisable(GL11.GL_LIGHTING);

            GL11.glDepthMask(true);

            this.bindTexture(hiroCurseMark);

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


            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            this.modelSkinOverlay.bipedHead.render(0.0625F);


            //this.bindTexture(new ResourceLocation("narutomod:textures/particles/sakuraParticle.png"));
            //Tessellator tessellator = Tessellator.instance;

            //this.modelBipedMain.bipedHead.postRender(0.0625F);

            *//**tessellator.startDrawingQuads();
             tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double)(10) / 64, (double)(12) / 32);
             tessellator.addVertexWithUV(0.0D, -2.0D, 0.0D, (double)(11) / 64, (double)(12) / 32);
             tessellator.addVertexWithUV(-2.0D, -2.0D, 0.0D, (double)(11) / 64, (double)(13) / 32);
             tessellator.addVertexWithUV(-2.0D, 0.0D, 0.0D, (double)(10) / 64, (double)(13) / 32);
             tessellator.draw();*//*

            GL11.glEnable(GL11.GL_ALPHA_TEST);

            GL11.glEnable(GL11.GL_LIGHTING);

            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);

            this.bindTexture(p_77029_1_.getLocationSkin());

        }*/

        MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Specials.Post(p_77029_1_, this, p_77029_2_));
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(AbstractClientPlayer p_77041_1_, float p_77041_2_) {
        float f1 = 0.9375F;
        GL11.glScalef(f1, f1, f1);
    }

    protected void func_96449_a(AbstractClientPlayer p_96449_1_, double p_96449_2_, double p_96449_4_, double p_96449_6_, String p_96449_8_, float p_96449_9_, double p_96449_10_) {
        if (p_96449_10_ < 100.0D) {
            Scoreboard scoreboard = p_96449_1_.getWorldScoreboard();
            ScoreObjective scoreobjective = scoreboard.func_96539_a(2);

            if (scoreobjective != null) {
                Score score = scoreboard.func_96529_a(p_96449_1_.getCommandSenderName(), scoreobjective);

                if (p_96449_1_.isPlayerSleeping()) {
                    this.func_147906_a(p_96449_1_, score.getScorePoints() + " " + scoreobjective.getDisplayName(), p_96449_2_, p_96449_4_ - 1.5D, p_96449_6_, 64);
                } else {
                    this.func_147906_a(p_96449_1_, score.getScorePoints() + " " + scoreobjective.getDisplayName(), p_96449_2_, p_96449_4_, p_96449_6_, 64);
                }

                p_96449_4_ += (double) ((float) this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * p_96449_9_);
            }
        }

        super.func_96449_a(p_96449_1_, p_96449_2_, p_96449_4_, p_96449_6_, p_96449_8_, p_96449_9_, p_96449_10_);
    }

    public void renderFirstPersonArm(EntityPlayer p_82441_1_) {
        /**if (!NarutoSettings.experimentalFirstPerson) {
            float f = 1.0F;
            GL11.glColor3f(f, f, f);
            this.modelBipedMain.onGround = 0.0F;
            this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, p_82441_1_);
            this.modelBipedMain.bipedRightArm.render(0.0625F);
        }*/

        float f = 1.0F;
        GL11.glColor3f(f, f, f);
        this.modelBipedMain.onGround = 0.0F;
        this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, p_82441_1_);
        this.modelBipedMain.bipedRightArm.render(0.0625F);
    }

    /**
     * Sets a simple glTranslate on a LivingEntity.
     */
    protected void renderLivingAt(AbstractClientPlayer p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
        if (p_77039_1_.isEntityAlive() && p_77039_1_.isPlayerSleeping()) {
            super.renderLivingAt(p_77039_1_, p_77039_2_ + (double) p_77039_1_.field_71079_bU, p_77039_4_ + (double) p_77039_1_.field_71082_cx, p_77039_6_ + (double) p_77039_1_.field_71089_bV);
        } else {
            super.renderLivingAt(p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
        }
    }

    protected void rotateCorpse(AbstractClientPlayer p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        if (p_77043_1_.isEntityAlive() && p_77043_1_.isPlayerSleeping()) {
            GL11.glRotatef(p_77043_1_.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.getDeathMaxRotation(p_77043_1_), 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
        } else {
            super.rotateCorpse(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
        }
    }

    protected void func_96449_a(EntityLivingBase p_96449_1_, double p_96449_2_, double p_96449_4_, double p_96449_6_, String p_96449_8_, float p_96449_9_, double p_96449_10_) {
        this.func_96449_a((AbstractClientPlayer) p_96449_1_, p_96449_2_, p_96449_4_, p_96449_6_, p_96449_8_, p_96449_9_, p_96449_10_);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
        this.preRenderCallback((AbstractClientPlayer) p_77041_1_, p_77041_2_);
    }

    protected void func_82408_c(EntityLivingBase p_82408_1_, int p_82408_2_, float p_82408_3_) {
        this.func_82408_c((AbstractClientPlayer) p_82408_1_, p_82408_2_, p_82408_3_);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
        return this.shouldRenderPass((AbstractClientPlayer) p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected void renderEquippedItems(EntityLivingBase p_77029_1_, float p_77029_2_) {
        this.renderEquippedItems((AbstractClientPlayer) p_77029_1_, p_77029_2_);
    }

    protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        this.rotateCorpse((AbstractClientPlayer) p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

    /**
     * Sets a simple glTranslate on a LivingEntity.
     */
    protected void renderLivingAt(EntityLivingBase p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
        this.renderLivingAt((AbstractClientPlayer) p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is common
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((AbstractClientPlayer) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return this.getEntityTexture((AbstractClientPlayer) p_110775_1_);
    }
}