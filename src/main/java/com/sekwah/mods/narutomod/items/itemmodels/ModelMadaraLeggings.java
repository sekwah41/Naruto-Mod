package com.sekwah.mods.narutomod.items.itemmodels;

import com.sekwah.mods.narutomod.animation.modelparts.ModelRetexturedBoxSharpBend;
import com.sekwah.mods.narutomod.player.models.ModelNinjaBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMadaraLeggings extends ModelNinjaBiped {
    public ModelRenderer front_leg_plate_1;
    public ModelRenderer right_leg;
    public ModelRenderer left_leg;
    public ModelRenderer back_leg_plate_2;
    public ModelRenderer back_leg_plate_1;
    public ModelRenderer left_leg_plate;
    public ModelRenderer right_leg_plate;
    public ModelRenderer front_leg_plate_2;

    public ModelRenderer right_legLower;
    public ModelRenderer left_legLower;

    public ModelRenderer legLockRight;
    public ModelRenderer legLockLeft;

    public ModelRenderer legLockRightLower;
    public ModelRenderer legLockLeftLower;

    private ModelRetexturedBoxSharpBend lowerRightLegBox;
    private ModelRetexturedBoxSharpBend upperRightLegBox;
    private ModelRetexturedBoxSharpBend upperLeftLegBox;
    private ModelRetexturedBoxSharpBend lowerLeftLegBox;

    public ModelMadaraLeggings() {
        textureWidth = 64;
        textureHeight = 64;

        front_leg_plate_1 = new ModelRenderer(this, 4, 38);
        front_leg_plate_1.addBox(-3F, -0.1F, -1F, 7, 6, 1);
        //front_leg_plate_1.setRotationPoint(-0.5F, 11.86667F, -2F);
        front_leg_plate_1.setRotationPoint(-0.5F, 11.36667F, -2F);
        front_leg_plate_1.setTextureSize(64, 64);
        front_leg_plate_1.mirror = true;
        setRotation(front_leg_plate_1, -0.1115358F, 0F, 0F);

        // TODO finish the custom bending cubes.
        right_leg = new ModelRenderer(this, 43, -3);
        //right_leg.addBox(-2F, 0F, -2F, 4, 6, 4, 0.01F);
        upperRightLegBox = new ModelRetexturedBoxSharpBend(this.right_leg, 38, 0, -2F, 0F, -2F, 4, 6, 4, 0.01F, 38, 0, 38, 0);
        right_leg.cubeList.add(upperRightLegBox);
        right_leg.setTextureSize(64, 64);
        right_leg.mirror = true;
        setRotation(right_leg, 0F, 0F, 0F);

        left_leg = new ModelRenderer(this, 43, -3);
        //left_leg.addBox(-2F, 0F, -2F, 4, 6, 4, 0.01F);
        upperLeftLegBox = new ModelRetexturedBoxSharpBend(this.left_leg, 38, 0, -2F, 0F, -2F, 4, 6, 4, 0.01F, 38, 0, 38, 0);
        left_leg.cubeList.add(upperLeftLegBox);
        left_leg.setTextureSize(64, 64);
        left_leg.mirror = true;
        setRotation(left_leg, 0F, 0F, 0F);


        // TODO lower parts

        right_legLower = new ModelRenderer(this, 43, -3);
        //right_leg.addBox(-2F, 0F, -2F, 4, 6, 4, 0.01F);
        lowerRightLegBox = new ModelRetexturedBoxSharpBend(this.right_legLower, 38, 0, -2F, 0F, -2F, 4, 2, 4, 0.01F, 38, 0, 38, 0);
        right_legLower.cubeList.add(lowerRightLegBox);
        right_legLower.setTextureSize(64, 64);
        right_legLower.mirror = true;
        setRotation(right_legLower, 0F, 0F, 0F);

        left_legLower = new ModelRenderer(this, 43, -3);
        //left_leg.addBox(-2F, 0F, -2F, 4, 6, 4, 0.01F);
        lowerLeftLegBox = new ModelRetexturedBoxSharpBend(this.left_legLower, 38, 0, -2F, 0F, -2F, 4, 2, 4, 0.01F, 38, 0, 38, 0);
        left_legLower.cubeList.add(lowerLeftLegBox);
        left_legLower.setTextureSize(64, 64);
        left_legLower.mirror = true;
        setRotation(left_legLower, 0F, 0F, 0F);

        /**right_leg = new ModelRenderer(this, 43, -3);
        right_leg.addBox(-2F, 0F, -2F, 4, 10, 4, 0.01F);
        right_leg.setRotationPoint(0F, 0F, 0F);
        right_leg.setTextureSize(64, 64);
        right_leg.mirror = true;
        setRotation(right_leg, 0F, 0F, 0F);
        left_leg = new ModelRenderer(this, 43, -3);
        left_leg.addBox(-2F, 0F, -2F, 4, 10, 4, 0.01F);
        left_leg.setRotationPoint(0F, 0F, 0F);
        left_leg.setTextureSize(64, 64);
        left_leg.mirror = true;
        setRotation(left_leg, 0F, 0F, 0F);*/

        back_leg_plate_2 = new ModelRenderer(this, 0, 40);
        back_leg_plate_2.addBox(-1.5F, 5F, 0F, 3, 2, 1);
        back_leg_plate_2.setRotationPoint(0F, 0F, 0F);
        back_leg_plate_2.setTextureSize(64, 64);
        back_leg_plate_2.mirror = true;
        setRotation(back_leg_plate_2, 0F, 0F, 0F);
        back_leg_plate_1 = new ModelRenderer(this, 0, 40);
        back_leg_plate_1.addBox(-3F, -0.2F, 0F, 6, 6, 1);
        back_leg_plate_1.setRotationPoint(0F, 12F, 2F);
        back_leg_plate_1.setTextureSize(64, 64);
        back_leg_plate_1.mirror = true;
        setRotation(back_leg_plate_1, 0.0743572F, 0F, 0F);
        left_leg_plate = new ModelRenderer(this, 8, 38);
        left_leg_plate.addBox(0F, 0F, -2.3F, 1, 6, 4);
        left_leg_plate.setRotationPoint(4F, 12F, 0F);
        left_leg_plate.setTextureSize(64, 64);
        left_leg_plate.mirror = true;
        setRotation(left_leg_plate, 0F, 0F, -0.111544F);
        right_leg_plate = new ModelRenderer(this, 8, 38);
        right_leg_plate.addBox(-1F, 0F, -2.3F, 1, 6, 4);
        right_leg_plate.setRotationPoint(-4F, 12F, 0F);
        right_leg_plate.setTextureSize(64, 64);
        right_leg_plate.mirror = true;
        setRotation(right_leg_plate, 0F, 0F, 0.1115358F);
        front_leg_plate_2 = new ModelRenderer(this, 8, 38);
        //front_leg_plate_2.addBox(-2F, 4.666667F, -1.0F, 5, 2, 1);
        front_leg_plate_2.addBox(-2F, 4.166667F, -1.0F, 5, 2, 1);
        //front_leg_plate_2.setRotationPoint(-0.5F, 11.9F, -2F);
        front_leg_plate_2.setRotationPoint(0F, 0.79F, 0.001F);
        front_leg_plate_2.setTextureSize(64, 64);
        front_leg_plate_2.mirror = true;
        setRotation(front_leg_plate_2, 0F, 0F, 0F);


        legLockLeft = new ModelRenderer(this, 1, 1);

        legLockRight = new ModelRenderer(this, 1, 1);

        legLockLeftLower = new ModelRenderer(this, 1, 1);

        legLockRightLower = new ModelRenderer(this, 1, 1);

        legLockLeft.addChild(left_leg);
        legLockRight.addChild(right_leg);

        legLockLeftLower.addChild(left_legLower);
        legLockRightLower.addChild(right_legLower);

        legLockLeft.addChild(legLockLeftLower);
        legLockRight.addChild(legLockRightLower);

        front_leg_plate_1.addChild(front_leg_plate_2);
        back_leg_plate_1.addChild(back_leg_plate_2);

    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

        // TODO add code like the player legs so that the armour bends correctly :D

        //left_leg.cubeList.add();

        right_leg = new ModelRenderer(this, 43, -3);
        right_leg.setTextureSize(64, 64);

        left_leg = new ModelRenderer(this, 43, -3);
        left_leg.setTextureSize(64, 64);
        left_leg.mirror = true;

        right_legLower = new ModelRenderer(this, 43, -3);
        right_legLower.setTextureSize(64, 64);

        left_legLower = new ModelRenderer(this, 43, -3);
        left_legLower.setTextureSize(64, 64);
        left_legLower.mirror = true;


        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (this.bipedLeftLegUpper.rotateAngleX <= -0.05F) {
            front_leg_plate_1.rotateAngleX += this.bipedLeftLegUpper.rotateAngleX + 0.05F;
            //front_leg_plate_2.rotateAngleX += this.bipedLeftLeg.rotateAngleX + 0.05F;
        }
        if (this.bipedRightLegUpper.rotateAngleX <= -0.05F) {
            front_leg_plate_1.rotateAngleX += this.bipedRightLegUpper.rotateAngleX + 0.05F;
            //front_leg_plate_2.rotateAngleX += this.bipedRightLeg.rotateAngleX + 0.05F;
        }

        if (this.bipedLeftLegUpper.rotateAngleX >= 0.05F) {
            back_leg_plate_1.rotateAngleX += this.bipedLeftLegUpper.rotateAngleX - 0.05F;
            //front_leg_plate_2.rotateAngleX += this.bipedLeftLeg.rotateAngleX + 0.05F;
        }
        if (this.bipedRightLegUpper.rotateAngleX >= 0.05F) {
            back_leg_plate_1.rotateAngleX += this.bipedRightLegUpper.rotateAngleX - 0.05F;
            //front_leg_plate_2.rotateAngleX += this.bipedRightLeg.rotateAngleX + 0.05F;
        }

        front_leg_plate_1.setRotationPoint(-0.5F, 11.06667F + bipedLeftLegUpper.rotationPointY - 12, -2F + bipedLeftLegUpper.rotationPointZ);

        back_leg_plate_1.setRotationPoint(0F, 11.06667F + bipedLeftLegUpper.rotationPointY - 12, 2F + bipedLeftLegUpper.rotationPointZ);

        left_leg_plate.setRotationPoint(3.8F, 11.5F + bipedLeftLegUpper.rotationPointY - 12, 0F + bipedLeftLegUpper.rotationPointZ);

        right_leg_plate.setRotationPoint(-3.8F, 11.5F + bipedLeftLegUpper.rotationPointY - 12, 0F + bipedLeftLegUpper.rotationPointZ);

        if (this.bipedLeftLegUpper.rotateAngleX >= 0.05F) {
            left_leg_plate.rotateAngleZ -= (this.bipedLeftLegUpper.rotateAngleX - 0.05F) / 10;
            //front_leg_plate_2.rotateAngleX += this.bipedLeftLeg.rotateAngleX + 0.05F;
        }
        if (this.bipedRightLegUpper.rotateAngleX >= 0.05F) {
            left_leg_plate.rotateAngleZ -= (this.bipedRightLegUpper.rotateAngleX - 0.05F) / 10;
            //front_leg_plate_2.rotateAngleX += this.bipedRightLeg.rotateAngleX + 0.05F;
        }

        if (this.bipedLeftLegUpper.rotateAngleX >= 0.05F) {
            right_leg_plate.rotateAngleZ += (this.bipedLeftLegUpper.rotateAngleX - 0.05F) / 10;
            //front_leg_plate_2.rotateAngleX += this.bipedLeftLeg.rotateAngleX + 0.05F;
        }
        if (this.bipedRightLegUpper.rotateAngleX >= 0.05F) {
            right_leg_plate.rotateAngleZ += (this.bipedRightLegUpper.rotateAngleX - 0.05F) / 10;
            //front_leg_plate_2.rotateAngleX += this.bipedRightLeg.rotateAngleX + 0.05F;
        }

        upperLeftLegBox.setLowerRotation(left_leg,this.bipedLeftLegLower.rotateAngleX);
        upperRightLegBox.setLowerRotation(right_leg,this.bipedRightLegLower.rotateAngleX);

        lowerLeftLegBox.setUpperRotation(left_legLower,this.bipedLeftLegLower.rotateAngleX);
        lowerRightLegBox.setUpperRotation(right_legLower,this.bipedRightLegLower.rotateAngleX);

        left_leg.cubeList.add(upperLeftLegBox);
        right_leg.cubeList.add(upperRightLegBox);

        left_legLower.cubeList.add(lowerLeftLegBox);
        right_legLower.cubeList.add(lowerRightLegBox);

        front_leg_plate_1.render(f5);
        //back_leg_plate_2.render(f5);
        back_leg_plate_1.render(f5);
        left_leg_plate.render(f5);
        right_leg_plate.render(f5);
        //front_leg_plate_2.render(f5);

        trackPart(this.bipedLeftLegLower, legLockLeftLower);

        trackPart(this.bipedRightLegLower, legLockRightLower);

        renderWithLock(this.bipedLeftLegUpper, legLockLeft, f5);

        renderWithLock(this.bipedRightLegUpper, legLockRight, f5);
    }

    private void renderWithLock(ModelRenderer bipedPart, ModelRenderer lockblock, float f5) {

        trackPart(bipedPart, lockblock);

        lockblock.render(f5);
    }

    private void trackPart(ModelRenderer bipedPart, ModelRenderer lockblock) {

        setRotation(lockblock, bipedPart.rotateAngleX, bipedPart.rotateAngleY, bipedPart.rotateAngleZ);

        lockblock.setRotationPoint(bipedPart.rotationPointX, bipedPart.rotationPointY, bipedPart.rotationPointZ);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
