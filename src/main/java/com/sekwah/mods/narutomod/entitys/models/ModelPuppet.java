package com.sekwah.mods.narutomod.entitys.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(Side.CLIENT)
public class ModelPuppet extends ModelBase {
    /**
     * The sticks that fly around the Blaze.
     */
    private ModelRenderer upper_body;
    private ModelRenderer lower_body;
    private ModelRenderer upper_left_leg;
    private ModelRenderer upper_right_leg;
    private ModelRenderer upper_upper_left_arm;
    private ModelRenderer lower_upper_left_arm;
    private ModelRenderer upper_lower_left_arm;
    private ModelRenderer lower_lower_left_arm;
    private ModelRenderer lower_upper_right_arm;
    private ModelRenderer upper_upper_right_arm;
    private ModelRenderer lower_lower_right_arm;
    private ModelRenderer upper_lower_right_arm;
    private ModelRenderer lower_left_leg;
    private ModelRenderer lower_right_leg;
    private ModelRenderer upper_body_coat;
    private ModelRenderer lower_body_coat;
    private ModelRenderer upper_upper_left_arm_coat;
    private ModelRenderer upper_lower_left_arm_coat;
    private ModelRenderer upper_upper_right_arm_coat;
    private ModelRenderer upper_lower_right_arm_coat;
    private ModelRenderer puppetHead;

    public ModelPuppet() {
        this.textureWidth = 128;
        this.textureHeight = 32;
        this.upper_body = new ModelRenderer(this, 32, 0);
        this.upper_body.addBox(-3.5F, 0F, -2F, 7, 7, 4);
        this.upper_body.setRotationPoint(0F, 0F, 0F);
        this.upper_body.mirror = true;
        this.upper_body.setRotationPoint(0F, 0F, 0F);
        this.lower_body = new ModelRenderer(this, 54, 0);
        this.lower_body.addBox(-3F, 0F, -1.5F, 6, 5, 3);
        this.lower_body.setRotationPoint(0F, 7F, 0F);
        this.lower_body.mirror = true;
        this.lower_body.setRotationPoint(0F, 0F, 0F);
        this.upper_left_leg = new ModelRenderer(this, 104, 0);
        this.upper_left_leg.addBox(-1F, 0F, -1F, 2, 4, 2);
        this.upper_left_leg.setRotationPoint(1.5F, 12F, 0F);
        this.upper_left_leg.setRotationPoint(0F, 0F, 0F);
        this.upper_right_leg = new ModelRenderer(this, 104, 0);
        this.upper_right_leg.addBox(-1F, 0F, -1F, 2, 4, 2);
        this.upper_right_leg.setRotationPoint(-1.5F, 12F, 0F);
        this.upper_right_leg.setRotationPoint(0F, 0F, 0F);
        this.upper_upper_left_arm = new ModelRenderer(this, 72, 0);
        this.upper_upper_left_arm.addBox(-1F, 0F, -1F, 2, 6, 2);
        this.upper_upper_left_arm.setRotationPoint(3F, 1F, 0F);
        this.upper_upper_left_arm.mirror = true;
        this.upper_upper_left_arm.setRotationPoint(0F, 0F, -1.003822F);
        this.lower_upper_left_arm = new ModelRenderer(this, 80, 0);
        this.lower_upper_left_arm.addBox(2F, 4.6F, -1F, 2, 6, 2);
        this.lower_upper_left_arm.setRotationPoint(3F, 1F, 0F);
        this.lower_upper_left_arm.mirror = true;
        this.lower_upper_left_arm.setRotationPoint(0F, 0F, -0.446147F);
        this.upper_lower_left_arm = new ModelRenderer(this, 88, 0);
        this.upper_lower_left_arm.addBox(-1F, 0F, -1F, 2, 6, 2);
        this.upper_lower_left_arm.setRotationPoint(3F, 5F, 0F);
        this.upper_lower_left_arm.mirror = true;
        this.upper_lower_left_arm.setRotationPoint(0F, 0F, -0.6320364F);
        this.lower_lower_left_arm = new ModelRenderer(this, 96, 0);
        this.lower_lower_left_arm.addBox(1.8F, 4.666667F, -1F, 2, 5, 2);
        this.lower_lower_left_arm.setRotationPoint(3F, 5F, 0F);
        this.lower_lower_left_arm.mirror = true;
        this.lower_lower_left_arm.setRotationPoint(0F, 0F, -0.1115394F);
        this.lower_upper_right_arm = new ModelRenderer(this, 80, 0);
        this.lower_upper_right_arm.addBox(-4F, 4.6F, -1F, 2, 6, 2);
        this.lower_upper_right_arm.setRotationPoint(-3F, 1F, 0F);
        this.lower_upper_right_arm.mirror = true;
        this.lower_upper_right_arm.setRotationPoint(0F, 0F, 0.4461411F);
        this.upper_upper_right_arm = new ModelRenderer(this, 72, 0);
        this.upper_upper_right_arm.addBox(-1F, 0F, -1F, 2, 6, 2);
        this.upper_upper_right_arm.setRotationPoint(-3F, 1F, 0F);
        this.upper_upper_right_arm.mirror = true;
        this.upper_upper_right_arm.setRotationPoint(0F, 0F, 1.003826F);
        this.upper_lower_right_arm = new ModelRenderer(this, 88, 0);
        this.upper_lower_right_arm.addBox(-1F, 0F, -1F, 2, 6, 2);
        this.upper_lower_right_arm.setRotationPoint(-3F, 5F, 0F);
        this.upper_lower_right_arm.mirror = true;
        this.upper_lower_right_arm.setRotationPoint(0F, 0F, 0.6320361F);
        this.upper_lower_right_arm.mirror = false;
        this.lower_lower_right_arm = new ModelRenderer(this, 96, 0);
        this.lower_lower_right_arm.addBox(-3.8F, 4.7F, -1F, 2, 5, 2);
        this.lower_lower_right_arm.setRotationPoint(-3F, 5F, 0F);
        this.lower_lower_right_arm.mirror = true;
        this.lower_lower_right_arm.setRotationPoint(0F, 0F, 0.111544F);
        this.lower_left_leg = new ModelRenderer(this, 112, 0);
        this.lower_left_leg.addBox(-1F, 3.1F, -2.6F, 2, 5, 2);
        this.lower_left_leg.setRotationPoint(1.5F, 12F, 0F);
        this.lower_left_leg.mirror = true;
        this.lower_left_leg.setRotationPoint(0.4461433F, 0F, 0F);
        this.lower_right_leg = new ModelRenderer(this, 112, 0);
        this.lower_right_leg.addBox(-1F, 3.1F, -2.6F, 2, 5, 2);
        this.lower_right_leg.setRotationPoint(-1.5F, 12F, 0F);
        this.lower_right_leg.mirror = true;
        this.lower_right_leg.setRotationPoint(0.4461433F, 0F, 0F);
        this.lower_right_leg.mirror = false;
        this.upper_body_coat = new ModelRenderer(this, 54, 8);
        this.upper_body_coat.addBox(-4F, -0.2F, -2.5F, 8, 8, 5);
        this.upper_body_coat.setRotationPoint(0F, 0F, 0F);
        this.upper_body_coat.mirror = true;
        this.upper_body_coat.setRotationPoint(0F, 0F, 0F);
        this.lower_body_coat = new ModelRenderer(this, 80, 8);
        this.lower_body_coat.addBox(-3.5F, -0.5F, -2F, 7, 6, 4);
        this.lower_body_coat.setRotationPoint(0F, 7F, 0F);
        this.lower_body_coat.mirror = true;
        this.lower_body_coat.setRotationPoint(0F, 0F, 0F);
        this.upper_upper_left_arm_coat = new ModelRenderer(this, 32, 11);
        this.upper_upper_left_arm_coat.addBox(-1.5F, 0F, -1.5F, 3, 4, 3);
        this.upper_upper_left_arm_coat.setRotationPoint(3F, 1F, 0F);
        this.upper_upper_left_arm_coat.mirror = true;
        this.upper_upper_left_arm_coat.setRotationPoint(0F, 0F, -1.003822F);
        this.upper_lower_left_arm_coat = new ModelRenderer(this, 0, 16);
        this.upper_lower_left_arm_coat.addBox(-1.5F, 0F, -1.5F, 3, 4, 3);
        this.upper_lower_left_arm_coat.setRotationPoint(3F, 5F, 0F);
        this.upper_lower_left_arm_coat.mirror = true;
        this.upper_lower_left_arm_coat.setRotationPoint(0F, 0F, -0.6320364F);
        this.upper_upper_right_arm_coat = new ModelRenderer(this, 32, 11);
        this.upper_upper_right_arm_coat.addBox(-1.5F, 0F, -1.5F, 3, 4, 3);
        this.upper_upper_right_arm_coat.setRotationPoint(-3F, -1F, 0F);
        this.upper_upper_right_arm_coat.mirror = true;
        this.upper_upper_right_arm_coat.setRotationPoint(0F, 0F, 1.003826F);
        this.upper_lower_right_arm_coat = new ModelRenderer(this, 0, 16);
        this.upper_lower_right_arm_coat.addBox(-1.5F, 0F, -1.5F, 3, 4, 3);
        this.upper_lower_right_arm_coat.setRotationPoint(-3F, 5F, 0F);
        this.upper_lower_right_arm_coat.mirror = true;
        this.upper_lower_right_arm_coat.setRotationPoint(0F, 0F, 0.6320361F);
        this.upper_lower_right_arm_coat.mirror = false;
        this.puppetHead = new ModelRenderer(this, 0, 0);
        this.puppetHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
    }

    public int func_78104_a() {
        return 8;
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        this.upper_body.render(par7);
        this.lower_body.render(par7);
        this.upper_left_leg.render(par7);
        this.upper_right_leg.render(par7);
        this.upper_upper_left_arm.render(par7);
        this.lower_upper_left_arm.render(par7);
        this.upper_lower_left_arm.render(par7);
        this.lower_lower_left_arm.render(par7);
        this.lower_upper_right_arm.render(par7);
        this.upper_upper_right_arm.render(par7);
        this.upper_lower_right_arm.render(par7);
        this.lower_lower_right_arm.render(par7);
        this.lower_left_leg.render(par7);
        this.lower_right_leg.render(par7);
        this.upper_body_coat.render(par7);
        this.lower_body_coat.render(par7);
        this.upper_upper_left_arm_coat.render(par7);
        this.upper_lower_left_arm_coat.render(par7);
        this.upper_upper_right_arm_coat.render(par7);
        this.upper_lower_right_arm_coat.render(par7);
        this.puppetHead.render(par7);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.puppetHead.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.puppetHead.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.upper_body.rotateAngleZ = 0F;
        this.lower_body.rotateAngleZ = 0F;
        this.upper_left_leg.rotateAngleZ = 0F;
        this.upper_right_leg.rotateAngleZ = 0F;
        this.upper_upper_left_arm.rotateAngleZ = -1.003822F;
        this.lower_upper_left_arm.rotateAngleZ = -0.446147F;
        this.upper_lower_left_arm.rotateAngleZ = -0.6320364F;
        this.lower_lower_left_arm.rotateAngleZ = -0.1115394F;
        this.lower_upper_right_arm.rotateAngleZ = 0.4461411F;
        this.upper_upper_right_arm.rotateAngleZ = 1.003826F;
        this.upper_lower_right_arm.rotateAngleZ = 0.6320361F;
        this.lower_lower_right_arm.rotateAngleZ = 0.111544F;
        this.lower_left_leg.rotateAngleX = 0.4461433F;
        this.lower_right_leg.rotateAngleX = 0.4461433F;
        this.upper_upper_left_arm_coat.rotateAngleZ = -1.003822F;
        this.upper_lower_left_arm_coat.rotateAngleZ = -0.6320364F;
        this.upper_upper_right_arm_coat.rotateAngleZ = 1.003826F;
        this.upper_lower_right_arm_coat.rotateAngleZ = 0.6320361F;
        this.upper_body.setRotationPoint(0F, 0F, 0F);
        this.lower_body.setRotationPoint(0F, 7F, 0F);
        this.upper_left_leg.setRotationPoint(1.5F, 12F, 0F);
        this.upper_right_leg.setRotationPoint(-1.5F, 12F, 0F);
        this.upper_upper_left_arm.setRotationPoint(3F, 1F, 0F);
        this.lower_upper_left_arm.setRotationPoint(3F, 1F, 0F);
        this.upper_lower_left_arm.setRotationPoint(3F, 5F, 0F);
        this.lower_lower_left_arm.setRotationPoint(3F, 5F, 0F);
        this.lower_upper_right_arm.setRotationPoint(-3F, 1F, 0F);
        this.upper_upper_right_arm.setRotationPoint(-3F, 1F, 0F);
        this.upper_lower_right_arm.setRotationPoint(-3F, 5F, 0F);
        this.lower_lower_right_arm.setRotationPoint(-3F, 5F, 0F);
        this.lower_left_leg.setRotationPoint(1.5F, 12F, 0F);
        this.lower_right_leg.setRotationPoint(-1.5F, 12F, 0F);
        this.upper_body_coat.setRotationPoint(0F, 0F, 0F);
        this.lower_body_coat.setRotationPoint(0F, 7F, -0.01F);
        this.upper_upper_left_arm_coat.setRotationPoint(3F, 1F, 0F);
        this.upper_lower_left_arm_coat.setRotationPoint(3F, 5F, 0F);
        this.upper_upper_right_arm_coat.setRotationPoint(-3F, 1F, 0F);
        this.upper_lower_right_arm_coat.setRotationPoint(-3F, 5F, 0F);
        this.lower_lower_right_arm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
        this.upper_lower_right_arm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
        this.lower_lower_left_arm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.upper_lower_left_arm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.lower_upper_right_arm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.upper_upper_right_arm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.lower_upper_left_arm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
        this.upper_upper_left_arm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
        this.upper_upper_left_arm_coat.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
        this.upper_lower_left_arm_coat.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.upper_upper_right_arm_coat.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.upper_lower_right_arm_coat.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
    }
}
