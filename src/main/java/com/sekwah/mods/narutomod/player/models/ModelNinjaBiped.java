package com.sekwah.mods.narutomod.player.models;

import com.sekwah.mods.narutomod.animation.AnimModelRenderer;
import com.sekwah.mods.narutomod.animation.dynamicplayerposes.DynamicPose;
import com.sekwah.mods.narutomod.animation.modelparts.ModelRetexturedBox;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import com.sekwah.mods.narutomod.animation.NarutoAnimator;
import com.sekwah.mods.narutomod.animation.Pose;
import com.sekwah.mods.narutomod.animation.modelparts.ModelRetexturedBoxSharpBend;
import com.sekwah.mods.narutomod.client.PlayerRenderTickEvent;

import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class ModelNinjaBiped extends ModelBiped
{
    public AnimModelRenderer bipedHead;
    public AnimModelRenderer bipedHeadwear;
    public AnimModelRenderer bipedBody;
    public AnimModelRenderer bipedRightArm;
    public AnimModelRenderer bipedLeftArm;
    public AnimModelRenderer bipedRightLeg;
    public AnimModelRenderer bipedLeftLeg;
    public ModelRenderer bipedEars;
    public ModelRenderer bipedCloak;

    public AnimModelRenderer bipedLowerBody;

    public AnimModelRenderer bipedRightArmUpper;
    public AnimModelRenderer bipedRightArmLower;

    public AnimModelRenderer bipedLeftArmUpper;
    public AnimModelRenderer bipedLeftArmLower;

    public AnimModelRenderer bipedRightLegUpper;
    public AnimModelRenderer bipedRightLegLower;

    public AnimModelRenderer bipedLeftLegUpper;
    public AnimModelRenderer bipedLeftLegLower;

    public AnimModelRenderer bipedMask;
    public AnimModelRenderer bipedMasksmall;
    public AnimModelRenderer bipedMaskmed;

    // Used to edit the rotation value
    private ModelRetexturedBoxSharpBend lowerRightArmBox;
    private ModelRetexturedBoxSharpBend upperRightArmBox;
    private ModelRetexturedBoxSharpBend upperLeftArmBox;
    private ModelRetexturedBoxSharpBend lowerLeftArmBox;


    private ModelRetexturedBoxSharpBend lowerRightLegBox;
    private ModelRetexturedBoxSharpBend upperRightLegBox;
    private ModelRetexturedBoxSharpBend upperLeftLegBox;
    private ModelRetexturedBoxSharpBend lowerLeftLegBox;
    /**
     * Records whether the model should be rendered holding an item in the left hand, and if that item is a block.
     */
    public int heldItemLeft;
    /**
     * Records whether the model should be rendered holding an item in the right hand, and if that item is a block.
     */
    public int heldItemRight;
    public boolean isSneak;
    /** Records whether the model should be rendered aiming a bow. */
    public boolean aimedBow;
    private static final String __OBFID = "CL_00000840";


    /** Records if the player is sprinting. */
    public boolean isSprinting = false;

    public boolean isChakraFocus = false;

    public boolean isThrowing = false;

    public boolean isClientThrowing = false;

    public boolean isClient = false;

    private float rightArmBeforeX;
    private float rightArmBeforeY;
    private float rightArmBeforeZ;
    private float leftArmBeforeX;
    private float leftArmBeforeY;
    private float leftArmBeforeZ;
    private int animateBack;

    // for throwing animation(and possibly bow)
    private int animateThrowBack;
    private float rightArmThrowBeforeX = 0;
    private float rightArmThrowBeforeY = 0;
    private float rightArmThrowBeforeZ = 0;
    private float leftArmThrowBeforeX = 0;
    private float leftArmThrowBeforeY = 0;
    private float leftArmThrowBeforeZ = 0;

    public String animationID = "default";
    public String animationlastID = "default";
    public float animationTick = 0;
    private ArrayList<AnimModelRenderer> animatedParts = new ArrayList();

    public ModelNinjaBiped()
    {
        this(0.0F);
    }

    public ModelNinjaBiped(float p_i1148_1_)
    {
        this(p_i1148_1_, 0.0F, 64, 32);
    }

    public ModelNinjaBiped(float p_i1149_1_, float p_i1149_2_, int p_i1149_3_, int p_i1149_4_)
    {

        this.isSneak = false;
        this.aimedBow = false;
        this.isThrowing = false;
        this.textureWidth = p_i1149_3_;
        this.textureHeight = p_i1149_4_;
        this.bipedCloak = new ModelRenderer(this, 0, 0);
        this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, p_i1149_1_);
        this.bipedEars = new ModelRenderer(this, 24, 0);
        this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, p_i1149_1_);
        this.bipedHead = new AnimModelRenderer(this, 0, 0,"head");
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1149_1_);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
        this.bipedHeadwear = new AnimModelRenderer(this, 32, 0,"head");
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1149_1_ + 0.5F);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
        this.bipedBody = new AnimModelRenderer(this, 16, 16,"upperBody");
        //this.bipedBody.addBox(-4F, 0F, -2F, 8, 6, 4, p_i1149_1_);
        this.bipedBody.cubeList.add(new ModelRetexturedBox(this.bipedBody, 16, 16, -4F, 0F, -2F, 8, 6, 4, p_i1149_1_, 20, 16, 28,24));
        this.bipedBody.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);

        this.bipedRightArm = new AnimModelRenderer(this, 40, 16,"rightArm");
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i1149_1_);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + p_i1149_2_, 0.0F);

        this.bipedLeftArm = new AnimModelRenderer(this, 40, 16,"leftArm");
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i1149_1_);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + p_i1149_2_, 0.0F);
        this.bipedRightLeg = new AnimModelRenderer(this, 0, 16,"rightLeg");
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1149_1_);
        this.bipedRightLeg.setRotationPoint(-2F, 12.0F, 0.0F);
        this.bipedLeftLeg = new AnimModelRenderer(this, 0, 16,"leftLeg");
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1149_1_);
        this.bipedLeftLeg.setRotationPoint(2F, 12.0F, 0.0F);

        this.bipedLowerBody = new AnimModelRenderer(this, 16, 22,"lowerBody");
        this.bipedLowerBody.cubeList.add(new ModelRetexturedBox(this.bipedLowerBody, 16, 22, -4F, 0F, -2F, 8, 6, 4, p_i1149_1_, 28, 24, 28,16));
        this.bipedLowerBody.setRotationPoint(0F, 6F, 0F);

        //this.bipedLowerBody.addBox(-4F, 0F, -2F, 8, 6, 4, p_i1149_1_);
        //this.bipedLowerBody.cubeList.add(new ModelRetexturedBox(this.bipedLowerBody, 16, 22, -4F, 0F, -2F, 8, 6, 4, p_i1149_1_));

        this.bipedMask = new AnimModelRenderer(this, 24, 0,"head");
        this.bipedMask.addBox(-2F, -3F, -6F, 4, 3, 2, p_i1149_1_);
        this.bipedMask.setRotationPoint(0F, 0F, 0F);
        this.bipedMaskmed = new AnimModelRenderer(this, 56, 26,"head");
        this.bipedMaskmed.addBox(-4F, -3F, -5.6F, 1, 2, 1, p_i1149_1_);
        this.bipedMaskmed.setRotationPoint(0F, 0F, 0F);
        this.bipedMasksmall = new AnimModelRenderer(this, 56, 29,"head");
        this.bipedMasksmall.addBox(-3F, -2F, -6F, 1, 1, 1, p_i1149_1_);
        this.bipedMasksmall.setRotationPoint(0F, 0F, 0F);

        // use this code to potentially add the retextured boxes instead of normal ones
        //this.bipedLowerBody.cubeList.add(new ModelRetexturedBox(this.bipedLowerBody, 16, 22, -4F, 0F, -2F, 8, 6, 4, p_i1149_1_));
        //this.bipedTestPiece = new ModelRenderer(this, 16, 22);
        //ModelRetexturedBox retexturedBox = new ModelRetexturedBox(this.bipedTestPiece, 40, 16, -4F, 0F, -2F, 4, 6, 4, p_i1149_1_, 44, 16, 44, 26);
        //this.bipedTestPiece.cubeList.add(retexturedBox);
        //this.bipedTestPiece.setRotationPoint(-5.0F, 2.0F + par2, 7.0F);


        //this.bipedTestPieceLower = new ModelRenderer(this, 40, 22);
        //ModelRetexturedBox retexturedBoxLower = new ModelRetexturedBox(this.bipedTestPiece, 40, 22, -4F, 0F, -2F, 4, 6, 4, p_i1149_1_, 44, 26, 48, 16);
        //this.bipedTestPieceLower.cubeList.add(retexturedBoxLower);
        //this.bipedTestPieceLower.setRotationPoint(0.0F, 6F + par2, 0.0F);

        //this.bipedTestPiece.addChild(this.bipedTestPieceLower);


        // Example of what all animated parts may look like.
        // this.bipedRightArmUpper = new AnimModelRenderer(this, 16, 22, "rightArmUpper");

        // TODO it may be something to do with this which is cauzing the problem

        // Arms
        this.bipedRightArmUpper = new AnimModelRenderer(this, 40, 16, "rightArmUpper", bipedRightArmUpper);
        upperRightArmBox = new ModelRetexturedBoxSharpBend(this.bipedRightArmUpper, 40, 16, -3.0F, -2.0F, -2.0F, 4, 6, 4, p_i1149_1_, 44, 16, 44, 26);
        this.bipedRightArmUpper.cubeList.add(upperRightArmBox);
        //this.bipedRightArmUpper.addRetexturedBox(-3.0F, -2.0F, -2.0F, 4, 6, 4, p_i1149_1_, 44, 16, 44, 26);
        this.bipedRightArmUpper.setRotationPoint(-5.0F, 2.0F + p_i1149_2_, 0.0F);


        this.bipedRightArmLower = new AnimModelRenderer(this, 40, 22, "rightArmLower", bipedRightArmLower);
        lowerRightArmBox = new ModelRetexturedBoxSharpBend(this.bipedRightArmLower, 40, 22, -2F, 0F, -2F, 4, 6, 4, p_i1149_1_, 44, 26, 48, 16);
        this.bipedRightArmLower.cubeList.add(lowerRightArmBox);
        this.bipedRightArmLower.setRotationPoint(-1.0F, 4F + p_i1149_2_, 0.0F);

        this.bipedRightArmUpper.addChild(this.bipedRightArmLower);

        this.bipedLeftArmUpper = new AnimModelRenderer(this, 40, 16, "leftArmUpper", bipedLeftArmUpper);
        this.bipedLeftArmUpper.mirror = true;
        upperLeftArmBox = new ModelRetexturedBoxSharpBend(this.bipedLeftArmUpper, 40, 16, -1.0F, -2.0F, -2.0F, 4, 6, 4, p_i1149_1_, 44, 16, 44, 26);
        this.bipedLeftArmUpper.cubeList.add(upperLeftArmBox);
        this.bipedLeftArmUpper.setRotationPoint(5.0F, 2.0F, 0.0F);


        this.bipedLeftArmLower = new AnimModelRenderer(this, 40, 22, "leftArmLower", bipedLeftArmLower);
        this.bipedLeftArmLower.mirror = true;
        lowerLeftArmBox = new ModelRetexturedBoxSharpBend(this.bipedLeftArmLower, 40, 22, -2F, 0F, -2F, 4, 6, 4, p_i1149_1_, 44, 26, 48, 16);
        this.bipedLeftArmLower.cubeList.add(lowerLeftArmBox);
        this.bipedLeftArmLower.setRotationPoint(1.0F, 4F, 0.0F);

        this.bipedLeftArmUpper.addChild(this.bipedLeftArmLower);

        // Legs

        this.bipedRightLegUpper = new AnimModelRenderer(this, 0, 16, "rightLegUpper");
        upperRightLegBox = new ModelRetexturedBoxSharpBend(this.bipedRightLegUpper, 0, 16, -2.0F, 0F, -2.0F, 4, 6, 4, p_i1149_1_, 4, 16, 4, 26);
        this.bipedRightLegUpper.cubeList.add(upperRightLegBox);
        this.bipedRightLegUpper.setRotationPoint(-2.0F, 12.0F + p_i1149_2_, 0.0F);


        this.bipedRightLegLower = new AnimModelRenderer(this, 0, 22, "rightLegLower");
        lowerRightLegBox = new ModelRetexturedBoxSharpBend(this.bipedRightLegLower, 0, 22, -2F, 0F, -2F, 4, 6, 4, p_i1149_1_, 4, 26, 8, 16);
        this.bipedRightLegLower.cubeList.add(lowerRightLegBox);
        this.bipedRightLegLower.setRotationPoint(0.0F, 6F, 0.0F);

        this.bipedRightLegUpper.addChild(this.bipedRightLegLower);

        this.bipedLeftLegUpper = new AnimModelRenderer(this, 0, 16, "leftLegUpper");
        this.bipedLeftLegUpper.mirror = true;
        upperLeftLegBox = new ModelRetexturedBoxSharpBend(this.bipedLeftLegUpper, 0, 16, -2.0F, 0F, -2.0F, 4, 6, 4, p_i1149_1_, 4, 16, 4, 26);
        this.bipedLeftLegUpper.cubeList.add(upperLeftLegBox);
        this.bipedLeftLegUpper.setRotationPoint(2.0F, 12.0F, 0.0F);


        this.bipedLeftLegLower = new AnimModelRenderer(this, 0, 22, "leftLegLower");
        this.bipedLeftLegLower.mirror = true;
        lowerLeftLegBox = new ModelRetexturedBoxSharpBend(this.bipedLeftLegLower, 0, 22, -2F, 0F, -2F, 4, 6, 4, p_i1149_1_, 4, 26, 8, 16);
        this.bipedLeftLegLower.cubeList.add(lowerLeftLegBox);
        this.bipedLeftLegLower.setRotationPoint(0.0F, 6F, 0.0F);

        this.bipedLeftLegUpper.addChild(this.bipedLeftLegLower);


        this.animatedParts.add(this.bipedBody);
        this.animatedParts.add(this.bipedHead);
        this.animatedParts.add(this.bipedHeadwear);
        this.animatedParts.add(this.bipedLowerBody);
        this.animatedParts.add(this.bipedMask);
        this.animatedParts.add(this.bipedMaskmed);
        this.animatedParts.add(this.bipedMasksmall);

        this.animatedParts.add(this.bipedRightArmLower);
        this.animatedParts.add(this.bipedLeftArmUpper);
        this.animatedParts.add(this.bipedLeftArmLower);
        this.animatedParts.add(this.bipedRightArmUpper);

        this.animatedParts.add(this.bipedRightLegLower);
        this.animatedParts.add(this.bipedLeftLegUpper);
        this.animatedParts.add(this.bipedLeftLegLower);
        this.animatedParts.add(this.bipedRightLegUpper);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

        if (this.isChild)
        {
            float var8 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / var8, 1.5F / var8, 1.5F / var8);
            GL11.glTranslatef(0.0F, 16.0F * p_78088_7_, 0.0F);
            this.bipedHead.render(p_78088_7_);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GL11.glTranslatef(0.0F, 24.0F * p_78088_7_, 0.0F);
            this.bipedBody.render(p_78088_7_);
            this.bipedLowerBody.render(p_78088_7_);
            //this.bipedRightArm.render(p_78088_7_);
            //this.bipedLeftArmUpper.render(p_78088_7_);
            this.bipedRightLeg.render(p_78088_7_);
            this.bipedLeftLeg.render(p_78088_7_);
            this.bipedHeadwear.render(p_78088_7_);

            this.bipedRightArmUpper.render(p_78088_7_);

            this.bipedLeftArmUpper.render(p_78088_7_);

            GL11.glPopMatrix();
        }
        else
        {
            this.bipedHead.render(p_78088_7_);
            this.bipedBody.render(p_78088_7_);
            this.bipedLowerBody.render(p_78088_7_);
            //this.bipedRightArm.render(p_78088_7_);
            //this.bipedLeftArm.render(p_78088_7_);
            // this.bipedRightLeg.render(p_78088_7_);
            //this.bipedLeftLeg.render(p_78088_7_);
            this.bipedHeadwear.render(p_78088_7_);

            this.bipedRightArmUpper.render(p_78088_7_);

            this.bipedLeftArmUpper.render(p_78088_7_);
            //this.bipedTestPiece.render(p_78088_7_);

            this.bipedRightLegUpper.render(p_78088_7_);

            this.bipedLeftLegUpper.render(p_78088_7_);
        }
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        //long timeTaken = System.nanoTime();
        this.animatedParts.remove(this.bipedRightArmLower);
        this.animatedParts.remove(this.bipedLeftArmUpper);
        this.animatedParts.remove(this.bipedLeftArmLower);
        this.animatedParts.remove(this.bipedRightArmUpper);

        this.boxList.remove(bipedRightArmLower);
        this.bipedRightArmLower = new AnimModelRenderer(this, 40, 22,"rightArmLower", bipedRightArmLower);
        this.boxList.remove(bipedLeftArmUpper);
        this.bipedLeftArmUpper = new AnimModelRenderer(this, 16, 22,"leftArmUpper", bipedLeftArmUpper);
        this.bipedLeftArmUpper.mirror = true;
        this.boxList.remove(bipedLeftArmLower);
        this.bipedLeftArmLower = new AnimModelRenderer(this, 40, 22,"leftArmLower", bipedLeftArmLower);
        this.boxList.remove(bipedRightArmUpper);
        this.bipedRightArmUpper = new AnimModelRenderer(this, 16, 22,"rightArmUpper", bipedRightArmUpper);

        this.bipedLeftArmLower.setRotationPoint(1.0F, 4F, 0.0F);

        this.bipedRightArmLower.setRotationPoint(-1.0F, 4F, 0.0F);

        this.bipedRightArmLower.rotateAngleX = 0;
        this.bipedRightArmLower.rotateAngleY = 0;
        this.bipedRightArmLower.rotateAngleZ = 0;

        this.bipedLeftArmLower.rotateAngleX = 0;
        this.bipedLeftArmLower.rotateAngleY = 0;
        this.bipedLeftArmLower.rotateAngleZ = 0;

        this.animatedParts.remove(this.bipedRightLegLower);
        this.animatedParts.remove(this.bipedLeftLegUpper);
        this.animatedParts.remove(this.bipedLeftLegLower);
        this.animatedParts.remove(this.bipedRightLegUpper);

        this.boxList.remove(bipedRightLegLower);
        this.bipedRightLegLower = new AnimModelRenderer(this, 0, 22,"rightLegLower", bipedRightLegLower);
        this.boxList.remove(bipedLeftLegUpper);
        this.bipedLeftLegUpper = new AnimModelRenderer(this, 0, 22,"leftLegUpper", bipedLeftLegUpper);
        this.bipedLeftLegUpper.mirror = true;
        this.boxList.remove(bipedLeftLegLower);
        this.bipedLeftLegLower = new AnimModelRenderer(this, 0, 22,"leftLegLower", bipedLeftLegLower);
        this.boxList.remove(bipedRightLegUpper);
        this.bipedRightLegUpper = new AnimModelRenderer(this, 0, 22,"rightLegUpper", bipedRightLegUpper);

        this.bipedLeftLegLower.setRotationPoint(0F, 6F, 0.0F);

        this.bipedRightLegLower.setRotationPoint(0F, 6F, 0.0F);

        this.bipedRightLegLower.rotateAngleX = 0;
        this.bipedRightLegLower.rotateAngleY = 0;
        this.bipedRightLegLower.rotateAngleZ = 0;

        this.bipedLeftLegLower.rotateAngleX = 0;
        this.bipedLeftLegLower.rotateAngleY = 0;
        this.bipedLeftLegLower.rotateAngleZ = 0;

        // Stops animations being wrong when changed back from a pose
        this.bipedLowerBody.rotateAngleY = 0F;
        this.bipedLowerBody.rotateAngleZ = 0F;

        this.bipedBody.rotateAngleY = 0F;
        this.bipedBody.rotateAngleZ = 0F;

        this.bipedHead.rotateAngleY = par4 / (180F / (float)Math.PI);
        this.bipedHead.rotateAngleX = par5 / (180F / (float)Math.PI);
        this.bipedHead.rotateAngleZ = 0F;
        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedRightArmUpper.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.bipedLeftArmUpper.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.bipedRightArmUpper.rotateAngleZ = 0.0F;
        this.bipedLeftArmUpper.rotateAngleZ = 0.0F;
        this.bipedRightLegUpper.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.bipedLeftLegUpper.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.bipedRightLegUpper.rotateAngleY = 0.0F;
        this.bipedLeftLegUpper.rotateAngleY = 0.0F;

        if (this.isRiding)
        {
            this.bipedRightArmUpper.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedLeftArmUpper.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedRightLegUpper.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.bipedLeftLegUpper.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.bipedRightLegUpper.rotateAngleY = ((float)Math.PI / 10F);
            this.bipedLeftLegUpper.rotateAngleY = -((float)Math.PI / 10F);
        }

        if (this.heldItemLeft != 0)
        {
            this.bipedLeftArmUpper.rotateAngleX = this.bipedLeftArmUpper.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
            this.bipedLeftArmLower.rotateAngleX = -0.25F;
        }

        if (this.heldItemRight != 0)
        {
            this.bipedRightArmUpper.rotateAngleX = this.bipedRightArmUpper.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
            this.bipedRightArmLower.rotateAngleX = -0.25F;
        }

        this.bipedRightArmUpper.rotateAngleY = 0.0F;
        this.bipedLeftArmUpper.rotateAngleY = 0.0F;
        float f6;
        float f7;

        if (this.onGround > -9990.0F)
        {
            f6 = this.onGround;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
            this.bipedLowerBody.setRotationPoint(0F, 6F, 0F);
            this.bipedLowerBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
            this.bipedRightArmUpper.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArmUpper.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArmUpper.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArmUpper.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArmUpper.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArmUpper.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArmUpper.rotateAngleX += this.bipedBody.rotateAngleY;
            f6 = 1.0F - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * (float)Math.PI);
            float var10 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.bipedRightArmUpper.rotateAngleX = (float)((double)this.bipedRightArmUpper.rotateAngleX - ((double)f7 * 1.2D + (double)var10));
            this.bipedRightArmUpper.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            this.bipedRightArmUpper.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
            this.bipedRightArmUpper.setRotationPoint(-5.0F, 2.0F + par2, 0.0F);
            this.bipedLeftArmUpper.setRotationPoint(5.0F, 2.0F + par2, 0.0F);
        }

        if (this.isSneak)
        {
            this.bipedRightArmLower.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.4F - 0.3F;

            this.bipedLeftArmLower.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.4F - 0.3F;

            this.bipedBody.rotateAngleX = 0.6F;
            this.bipedLowerBody.rotateAngleX = 0.155F;
            this.bipedLowerBody.setRotationPoint(0F, 4.05F, 3.05F);
            this.bipedRightArmUpper.rotateAngleX += 0.4F;
            this.bipedLeftArmUpper.rotateAngleX += 0.4F;
            this.bipedHead.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
            this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
            this.bipedBody.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
            this.bipedRightArmUpper.setRotationPoint(-5.0F, 2.0F + par2, 0.0F);
            this.bipedLeftArmUpper.setRotationPoint(5.0F, 2.0F + par2, 0.0F);
            this.bipedMask.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
            this.bipedMasksmall.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
            this.bipedMaskmed.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
            this.bipedRightLegUpper.setRotationPoint(-2F, 9.0F + par2, 4.0F);
            this.bipedLeftLegUpper.setRotationPoint(2F, 9.0F + par2, 4.0F);

            this.bipedLeftLegUpper.rotateAngleX += -0.15F;
            this.bipedRightLegUpper.rotateAngleX += -0.15F;

            this.bipedLeftLegLower.rotateAngleX = 0.25F;
            this.bipedRightLegLower.rotateAngleX = 0.25F;
        }
        else
        {
            this.bipedBody.rotateAngleX = 0.0F;
            this.bipedLowerBody.rotateAngleX = 0.0F;
            this.bipedLowerBody.setRotationPoint(0F, 6F, 0F);
            this.bipedRightLegUpper.rotationPointZ = 0.1F;
            this.bipedLeftLegUpper.rotationPointZ = 0.1F;
            this.bipedRightLegUpper.rotationPointY = 12.0F;
            this.bipedLeftLegUpper.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
            this.bipedHeadwear.rotationPointY = 0.0F;
            this.bipedHead.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
            this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
            this.bipedBody.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
            this.bipedLowerBody.setRotationPoint(0F, 6F + par2, 0F);
            this.bipedRightLegUpper.setRotationPoint(-2F, 12.0F + par2, 0.0F);
            this.bipedLeftLegUpper.setRotationPoint(2F, 12.0F + par2, 0.0F);
        }

        this.bipedLeftLegLower.rotateAngleX += MathHelper.cos((par1 * 0.6662F) + 45) * 4.0F * par2 * 0.4F + 0.0F;
        this.bipedRightLegLower.rotateAngleX -= MathHelper.cos((par1 * 0.6662F) + 45) * 4.0F * par2 * 0.4F + 0.0F;

        this.bipedRightArmUpper.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArmUpper.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArmUpper.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.bipedLeftArmUpper.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.bipedMask.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedMasksmall.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedMaskmed.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedCloak.setRotationPoint(0F, 0F, 0F);

        if (this.aimedBow)
        {
            f6 = 0.0F;
            f7 = 0.0F;
            this.bipedRightArmUpper.rotateAngleZ = 0.0F;
            this.bipedLeftArmUpper.rotateAngleZ = 0.0F;
            this.bipedRightArmUpper.rotateAngleY = -(0.1F - f6 * 0.6F) + this.bipedHead.rotateAngleY;
            this.bipedLeftArmUpper.rotateAngleY = 0.1F - f6 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
            this.bipedRightArmUpper.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArmUpper.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedRightArmUpper.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedLeftArmUpper.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedRightArmUpper.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArmUpper.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArmUpper.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
            this.bipedLeftArmUpper.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        }


        if (this.isThrowing)
        {
            this.bipedRightArmUpper.rotateAngleY = -(0.1F) + this.bipedHead.rotateAngleX;
            this.bipedRightArmUpper.rotateAngleX = -(1F + (this.bipedHead.rotateAngleY / 2));
            this.bipedRightArmUpper.rotateAngleZ = -1.2831853071795864769252867665601F;

            this.bipedRightArmLower.rotateAngleX = 0;
        }

        if(this.isClient){
            if (this.isClientThrowing)
            {

                this.animateThrowBack = 40;
                this.bipedRightArmUpper.rotateAngleX = this.rightArmThrowBeforeX;
                this.bipedRightArmUpper.rotateAngleY = this.rightArmThrowBeforeY;
                this.bipedRightArmUpper.rotateAngleZ = this.rightArmThrowBeforeZ;

                if(this.bipedRightArmUpper.rotateAngleX == -(1F + (this.bipedHead.rotateAngleY / 2))){
                    this.bipedRightArmUpper.rotateAngleY = -(0.1F) + this.bipedHead.rotateAngleX;
                    this.bipedRightArmUpper.rotateAngleX = -(1F + (this.bipedHead.rotateAngleY / 2));
                    this.bipedRightArmUpper.rotateAngleZ = -1.2831853071795864769252867665601F; // was 5F
                }
                else{
                    if(PlayerRenderTickEvent.delta != 0){
                        float speed = PlayerRenderTickEvent.delta * 10;
                        this.rightArmThrowBeforeX += (-(1F + (this.bipedHead.rotateAngleY / 2)) - this.rightArmThrowBeforeX) / speed;

                        this.rightArmThrowBeforeY += (-(0.1F) + this.bipedHead.rotateAngleX - this.rightArmThrowBeforeY) / speed;

                        this.rightArmThrowBeforeZ += (-1.2831853071795864769252867665601F - this.rightArmThrowBeforeZ) / speed;
                    }
                }
            }
            else{
                if(PlayerRenderTickEvent.delta != 0){
                    if(this.animateThrowBack-- > 30){
                        float speed = PlayerRenderTickEvent.delta * 10;

                        this.rightArmThrowBeforeX += (-(1F + (this.bipedHead.rotateAngleY / 2)) - 2F - this.rightArmThrowBeforeX) / speed;

                        this.rightArmThrowBeforeY += (-(0.1F) + this.bipedHead.rotateAngleX - this.rightArmThrowBeforeY) / speed;

                        this.rightArmThrowBeforeZ += (-1.2831853071795864769252867665601F - this.rightArmThrowBeforeZ) / speed;

                        this.bipedRightArmUpper.rotateAngleX = this.rightArmThrowBeforeX;
                        this.bipedRightArmUpper.rotateAngleY = this.rightArmThrowBeforeY;
                        this.bipedRightArmUpper.rotateAngleZ = this.rightArmThrowBeforeZ;
                    }
                    else if(this.animateThrowBack-- > 0){
                        float speed = PlayerRenderTickEvent.delta * 10;
                        this.rightArmThrowBeforeX += (this.bipedRightArmUpper.rotateAngleX - this.rightArmThrowBeforeX) / speed;

                        this.rightArmThrowBeforeY += (this.bipedRightArmUpper.rotateAngleY- this.rightArmThrowBeforeY) / speed;

                        this.rightArmThrowBeforeZ += (this.bipedRightArmUpper.rotateAngleZ- this.rightArmThrowBeforeZ) / speed;

                        this.bipedRightArmUpper.rotateAngleX = this.rightArmThrowBeforeX;
                        this.bipedRightArmUpper.rotateAngleY = this.rightArmThrowBeforeY;
                        this.bipedRightArmUpper.rotateAngleZ = this.rightArmThrowBeforeZ;

                    }
                }
                else if(this.animateThrowBack-- > 0){
                    this.bipedRightArmUpper.rotateAngleX = this.rightArmThrowBeforeX;
                    this.bipedRightArmUpper.rotateAngleY = this.rightArmThrowBeforeY;
                    this.bipedRightArmUpper.rotateAngleZ = this.rightArmThrowBeforeZ;
                }
            }
        }

        if (this.isSprinting && this.onGround == 0F)
        {
            this.bipedLowerBody.rotateAngleX = 0F;
            this.bipedBody.rotateAngleX = 0.7435722F;
            this.bipedLowerBody.rotateAngleY = 0F;
            this.bipedBody.rotateAngleY = 0F;
            this.bipedLowerBody.rotateAngleZ = 0F;
            this.bipedBody.rotateAngleZ = 0F;
            this.bipedRightArmUpper.rotateAngleX = 1.412787F;
            this.bipedRightArmUpper.rotateAngleY = 0F;
            this.bipedRightArmUpper.rotateAngleZ = 0F;
            this.bipedLeftArmUpper.rotateAngleX = 1.412787F;
            this.bipedLeftArmUpper.rotateAngleY = 0F;
            this.bipedLeftArmUpper.rotateAngleZ = 0F;
            this.bipedHead.rotateAngleX = 0F;
            this.bipedHeadwear.rotateAngleX = 0F;
            this.bipedHead.setRotationPoint(0F, 3.133333F, -5F);
            this.bipedMask.setRotationPoint(0F, 3.133333F, -5F);
            this.bipedMaskmed.setRotationPoint(0F, 3.133333F, -5F);
            this.bipedMasksmall.setRotationPoint(0F, 3.133333F, -5F);
            this.bipedHeadwear.setRotationPoint(0F, 3.133333F, -5F);
            this.bipedBody.setRotationPoint(0F, 3F, -3.5F);
            this.bipedLowerBody.setRotationPoint(0F, 6F, 0F);
            this.bipedRightArmUpper.setRotationPoint(-5F, 3.933333F, -3F);
            this.bipedLeftArmUpper.setRotationPoint(5F, 3.266667F, -3F);
            this.bipedRightLegUpper.setRotationPoint(-2F, 12.0F, 0.0F);
            this.bipedLeftLegUpper.setRotationPoint(2F, 12.0F, 0.0F);
            this.bipedCloak.setRotationPoint(0F, -3F, 3F);
        }
        else{
            this.bipedLeftLegUpper.rotateAngleX *= 0.6F;
            this.bipedRightLegUpper.rotateAngleX *= 0.6F;
        }

        this.animatedParts.add(this.bipedRightArmLower);
        this.animatedParts.add(this.bipedLeftArmUpper);
        this.animatedParts.add(this.bipedLeftArmLower);
        this.animatedParts.add(this.bipedRightArmUpper);

        this.animatedParts.add(this.bipedRightLegLower);
        this.animatedParts.add(this.bipedLeftLegUpper);
        this.animatedParts.add(this.bipedLeftLegLower);
        this.animatedParts.add(this.bipedRightLegUpper);

        /*if(!this.animationID.equals("default") || !this.animationlastID.equals("default")){
            PlayerPoseAnimator.animate(this, this.animationID, this.animationlastID, this.animationTick);
        }*/

        if(this.animationID.equals(this.animationlastID)){
            Pose pose = NarutoAnimator.getPose(animationID, NarutoAnimator.playerPoses);
            if(pose instanceof DynamicPose){
                ((DynamicPose) pose).updatePose(par1, par2, par3, par4, par5, par6, par7Entity, onGround);
            }
        }
        else{
            Pose pose = NarutoAnimator.getPose(animationID, NarutoAnimator.playerPoses);
            if(pose instanceof DynamicPose){
                ((DynamicPose) pose).updatePose(par1, par2, par3, par4, par5, par6, par7Entity, onGround);
            }
            Pose lastPose = NarutoAnimator.getPose(animationlastID, NarutoAnimator.playerPoses);
            if(lastPose instanceof DynamicPose){
                ((DynamicPose) lastPose).updatePose(par1, par2, par3, par4, par5, par6, par7Entity, onGround);
            }
        }


        if (!this.animationID.equals("default") || !this.animationlastID.equals("default")) {
            NarutoAnimator.animate(this.animationID, this.animationlastID, this.animationTick, this.animatedParts, NarutoAnimator.playerPoses);
        }

        // Arms

        // This can be used if constraints are needed
        if (this.bipedRightArmLower.rotateAngleX > 0) {
            this.bipedRightArmLower.rotateAngleX = 0;
        } else if (this.bipedRightArmLower.rotateAngleX < -1.5F) {
            this.bipedRightArmLower.rotateAngleX = -1.5F;
        }

        if (this.bipedLeftArmLower.rotateAngleX > 0) {
            this.bipedLeftArmLower.rotateAngleX = 0;
        } else if (this.bipedLeftArmLower.rotateAngleX < -1.5F) {
            this.bipedLeftArmLower.rotateAngleX = -1.5F;
        }

        upperLeftArmBox.setLowerRotation(this.bipedLeftArmUpper, this.bipedLeftArmLower.rotateAngleX);
        lowerLeftArmBox.setUpperRotation(this.bipedLeftArmLower, this.bipedLeftArmLower.rotateAngleX);
        upperRightArmBox.setLowerRotation(this.bipedRightArmUpper, this.bipedRightArmLower.rotateAngleX);
        lowerRightArmBox.setUpperRotation(this.bipedRightArmLower, this.bipedRightArmLower.rotateAngleX);


        this.bipedRightArmUpper.cubeList.add(upperRightArmBox);

        this.bipedRightArmLower.cubeList.add(lowerRightArmBox);

        this.bipedRightArmUpper.addChild(this.bipedRightArmLower);

        this.bipedLeftArmUpper.mirror = true;
        this.bipedLeftArmUpper.cubeList.add(upperLeftArmBox);

        this.bipedLeftArmLower.mirror = true;
        this.bipedLeftArmLower.cubeList.add(lowerLeftArmBox);

        this.bipedLeftArmUpper.addChild(this.bipedLeftArmLower);

        // Legs

        // This can be used if constraints are needed
        if (this.bipedRightLegLower.rotateAngleX < 0) {
            this.bipedRightLegLower.rotateAngleX = 0;
        } else if (this.bipedRightLegLower.rotateAngleX > 1.5F) {
            this.bipedRightLegLower.rotateAngleX = 1.5F;
        }

        if (this.bipedLeftLegLower.rotateAngleX < 0) {
            this.bipedLeftLegLower.rotateAngleX = 0;
        } else if (this.bipedLeftLegLower.rotateAngleX > 1.5F) {
            this.bipedLeftLegLower.rotateAngleX = 1.5F;
        }

        upperLeftLegBox.setLowerRotation(this.bipedLeftLegUpper, this.bipedLeftLegLower.rotateAngleX);
        lowerLeftLegBox.setUpperRotation(this.bipedLeftLegLower, this.bipedLeftLegLower.rotateAngleX);
        upperRightLegBox.setLowerRotation(this.bipedRightLegUpper, this.bipedRightLegLower.rotateAngleX);
        lowerRightLegBox.setUpperRotation(this.bipedRightLegLower, this.bipedRightLegLower.rotateAngleX);


        this.bipedRightLegUpper.cubeList.add(upperRightLegBox);

        this.bipedRightLegLower.cubeList.add(lowerRightLegBox);

        this.bipedRightLegUpper.addChild(this.bipedRightLegLower);

        this.bipedLeftLegUpper.mirror = true;
        this.bipedLeftLegUpper.cubeList.add(upperLeftLegBox);

        this.bipedLeftLegLower.mirror = true;
        this.bipedLeftLegLower.cubeList.add(lowerLeftLegBox);

        this.bipedLeftLegUpper.addChild(this.bipedLeftLegLower);

        //NarutoMod.LOGGER.info("Time to recalculate:" + (System.nanoTime() - timeTaken) + " (nanoseconds)");
    }

    /**
     * renders the ears (specifically, deadmau5's)
     */
    public void renderEars(float p_78110_1_)
    {
        this.bipedEars.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedEars.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedEars.rotationPointX = 0.0F;
        this.bipedEars.rotationPointY = 0.0F;
        this.bipedEars.render(p_78110_1_);
    }

    /**
     * For people who help with the mod
     */

    public void renderMask(float par1)
    {
        this.bipedMask.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedMask.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedMask.rotationPointX = this.bipedHead.rotationPointX;
        this.bipedMask.rotationPointY = this.bipedHead.rotationPointY;
        this.bipedMasksmall.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedMasksmall.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedMasksmall.rotationPointX = this.bipedHead.rotationPointX;
        this.bipedMasksmall.rotationPointY = this.bipedHead.rotationPointY;
        this.bipedMaskmed.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedMaskmed.rotateAngleX = this.bipedHead.rotateAngleX + 0.175F;
        this.bipedMaskmed.rotationPointX = this.bipedHead.rotationPointX;
        this.bipedMaskmed.rotationPointY = this.bipedHead.rotationPointY;
        this.bipedMask.render(par1);
        this.bipedMasksmall.render(par1);
        this.bipedMaskmed.render(par1);
    }

    /**
     * Renders the cloak of the current biped (in most cases, it's a player)
     */
    public void renderCloak(float p_78111_1_)
    {
        this.bipedCloak.render(p_78111_1_);
    }
}