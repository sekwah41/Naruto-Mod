package sekwah.mods.narutomod.items.itemmodels;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import sekwah.mods.narutomod.animation.modelparts.ModelRetexturedBoxSharpBend;
import sekwah.mods.narutomod.client.player.models.ModelNinjaBiped;
import sekwah.mods.narutomod.settings.NarutoSettings;

public class ModelMadaraLeggings extends ModelNinjaBiped {

    // TODO change the leg plates to track the lower body

    public ModelRenderer front_leg_plate_1;
    public ModelRenderer right_leg;
    public ModelRenderer left_leg;

    public ModelRenderer left_leg_plate;
    public ModelRenderer right_leg_plate;
    public ModelRenderer front_leg_plate_2;
    public ModelRenderer back_leg_plate_2;
    public ModelRenderer back_leg_plate_1;

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

    public ModelRenderer lowerBodyLock;

    public ModelMadaraLeggings() {
        textureWidth = 64;
        textureHeight = 64;

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

        // was 6 lower before tracked to lower body

        front_leg_plate_1 = new ModelRenderer(this, 4, 38);
        front_leg_plate_1.addBox(-3F, -0.1F, -1F, 7, 6, 1);
        //front_leg_plate_1.setRotationPoint(-0.5F, 11.86667F, -2F);
        front_leg_plate_1.setRotationPoint(-0.5F, 5.36667F, -2F);
        front_leg_plate_1.setTextureSize(64, 64);
        front_leg_plate_1.mirror = true;
        setRotation(front_leg_plate_1, -0.1115358F, 0F, 0F);

        back_leg_plate_2 = new ModelRenderer(this, 0, 40);
        back_leg_plate_2.addBox(-1.5F, 5F, 0F, 3, 2, 1);
        back_leg_plate_2.setRotationPoint(0F, 0F, 0F);
        back_leg_plate_2.setTextureSize(64, 64);
        back_leg_plate_2.mirror = true;
        setRotation(back_leg_plate_2, 0F, 0F, 0F);
        back_leg_plate_1 = new ModelRenderer(this, 0, 40);
        back_leg_plate_1.addBox(-3F, -0.2F, 0F, 6, 6, 1);
        back_leg_plate_1.setRotationPoint(0F, 6F, 2F);
        back_leg_plate_1.setTextureSize(64, 64);
        back_leg_plate_1.mirror = true;
        setRotation(back_leg_plate_1, 0.0743572F, 0F, 0F);
        left_leg_plate = new ModelRenderer(this, 8, 38);
        left_leg_plate.addBox(0F, 0F, -2.3F, 1, 6, 4);
        left_leg_plate.setRotationPoint(4F, 6F, 0F);
        left_leg_plate.setTextureSize(64, 64);
        left_leg_plate.mirror = true;
        setRotation(left_leg_plate, 0F, 0F, -0.111544F);
        right_leg_plate = new ModelRenderer(this, 8, 38);
        right_leg_plate.addBox(-1F, 0F, -2.3F, 1, 6, 4);
        right_leg_plate.setRotationPoint(-4F, 6F, 0F);
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


        this.legLockLeft = new ModelRenderer(this, 1, 1);

        this.legLockRight = new ModelRenderer(this, 1, 1);

        this.legLockLeftLower = new ModelRenderer(this, 1, 1);

        this.legLockRightLower = new ModelRenderer(this, 1, 1);

        this.lowerBodyLock = new ModelRenderer(this, 1, 1);

        this.lowerBodyLock.addChild(this.left_leg_plate);
        this.lowerBodyLock.addChild(this.right_leg_plate);
        this.lowerBodyLock.addChild(this.back_leg_plate_1);
        this.lowerBodyLock.addChild(this.front_leg_plate_1);

        this.legLockLeft.addChild(this.left_leg);
        this.legLockRight.addChild(this.right_leg);

        this.legLockLeftLower.addChild(this.left_legLower);
        this.legLockRightLower.addChild(this.right_legLower);

        this.legLockLeft.addChild(this.legLockLeftLower);
        this.legLockRight.addChild(this.legLockRightLower);

        this.front_leg_plate_1.addChild(this.front_leg_plate_2);
        this.back_leg_plate_1.addChild(this.back_leg_plate_2);

    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

        // TODO add code like the player legs so that the armour bends correctly :D

        //left_leg.cubeList.add();

        /*if(NarutoSettings.betterArms) {

            this.right_leg = new ModelRenderer(this, 43, -3);
            this.right_leg.setTextureSize(64, 64);

            this.left_leg = new ModelRenderer(this, 43, -3);
            this.left_leg.setTextureSize(64, 64);
            this.left_leg.mirror = true;

            this.right_legLower = new ModelRenderer(this, 43, -3);
            this.right_legLower.setTextureSize(64, 64);

            this.left_legLower = new ModelRenderer(this, 43, -3);
            this.left_legLower.setTextureSize(64, 64);
            this.left_legLower.mirror = true;
        }*/

        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (this.bipedLeftLegUpper.rotateAngleX <= -0.05F) {
            this.front_leg_plate_1.rotateAngleX += this.bipedLeftLegUpper.rotateAngleX + 0.05F;
            //front_leg_plate_2.rotateAngleX += this.bipedLeftLeg.rotateAngleX + 0.05F;
        }
        if (this.bipedRightLegUpper.rotateAngleX <= -0.05F) {
            this.front_leg_plate_1.rotateAngleX += this.bipedRightLegUpper.rotateAngleX + 0.05F;
            //front_leg_plate_2.rotateAngleX += this.bipedRightLeg.rotateAngleX + 0.05F;
        }

        if (this.bipedLeftLegUpper.rotateAngleX >= 0.05F) {
            this.back_leg_plate_1.rotateAngleX += this.bipedLeftLegUpper.rotateAngleX - 0.05F;
            //front_leg_plate_2.rotateAngleX += this.bipedLeftLeg.rotateAngleX + 0.05F;
        }
        if (this.bipedRightLegUpper.rotateAngleX >= 0.05F) {
            this.back_leg_plate_1.rotateAngleX += this.bipedRightLegUpper.rotateAngleX - 0.05F;
            //front_leg_plate_2.rotateAngleX += this.bipedRightLeg.rotateAngleX + 0.05F;
        }

        // old stuff for crouching and things
        /*this.front_leg_plate_1.setRotationPoint(-0.5F, 11.06667F + this.bipedLeftLegUpper.rotationPointY - 12, -2F + this.bipedLeftLegUpper.rotationPointZ);

        this.back_leg_plate_1.setRotationPoint(0F, 11.06667F + this.bipedLeftLegUpper.rotationPointY - 12, 2F + this.bipedLeftLegUpper.rotationPointZ);

        this.left_leg_plate.setRotationPoint(3.8F, 11.5F + this.bipedLeftLegUpper.rotationPointY - 12, 0F + this.bipedLeftLegUpper.rotationPointZ);

        this.right_leg_plate.setRotationPoint(-3.8F, 11.5F + this.bipedLeftLegUpper.rotationPointY - 12, 0F + this.bipedLeftLegUpper.rotationPointZ);*/

        if (this.bipedLeftLegUpper.rotateAngleX >= 0.05F) {
            this.left_leg_plate.rotateAngleZ -= (this.bipedLeftLegUpper.rotateAngleX - 0.05F) / 10;
            //front_leg_plate_2.rotateAngleX += this.bipedLeftLeg.rotateAngleX + 0.05F;
        }
        if (this.bipedRightLegUpper.rotateAngleX >= 0.05F) {
            this.left_leg_plate.rotateAngleZ -= (this.bipedRightLegUpper.rotateAngleX - 0.05F) / 10;
            //front_leg_plate_2.rotateAngleX += this.bipedRightLeg.rotateAngleX + 0.05F;
        }

        if (this.bipedLeftLegUpper.rotateAngleX >= 0.05F) {
            right_leg_plate.rotateAngleZ += (this.bipedLeftLegUpper.rotateAngleX - 0.05F) / 10;
            //front_leg_plate_2.rotateAngleX += this.bipedLeftLeg.rotateAngleX + 0.05F;
        }
        if (this.bipedRightLegUpper.rotateAngleX >= 0.05F) {
            this.right_leg_plate.rotateAngleZ += (this.bipedRightLegUpper.rotateAngleX - 0.05F) / 10;
            //front_leg_plate_2.rotateAngleX += this.bipedRightLeg.rotateAngleX + 0.05F;
        }

        /*if(NarutoSettings.betterArms) {
            this.upperLeftLegBox.setLowerRotation(this.left_leg,this.bipedLeftLegLower.rotateAngleX);
            this.upperRightLegBox.setLowerRotation(this.right_leg,this.bipedRightLegLower.rotateAngleX);

            this.lowerLeftLegBox.setUpperRotation(this.left_legLower,this.bipedLeftLegLower.rotateAngleX);
            this.lowerRightLegBox.setUpperRotation(this.right_legLower,this.bipedRightLegLower.rotateAngleX);

            this.left_leg.cubeList.add(upperLeftLegBox);
            this.right_leg.cubeList.add(upperRightLegBox);

            this.left_legLower.cubeList.add(lowerLeftLegBox);
            this.right_legLower.cubeList.add(lowerRightLegBox);
        }*/

        /*front_leg_plate_1.render(f5);
        //back_leg_plate_2.render(f5);
        back_leg_plate_1.render(f5);
        left_leg_plate.render(f5);
        right_leg_plate.render(f5);*/
        //front_leg_plate_2.render(f5);

        trackPart(this.bipedLeftLegLower, this.legLockLeftLower);

        trackPart(this.bipedRightLegLower, this.legLockRightLower);

        renderWithLock(this.bipedLeftLegUpper, this.legLockLeft, f5);

        renderWithLock(this.bipedRightLegUpper, this.legLockRight, f5);

        renderWithLock(this.bipedLowerBody, this.lowerBodyLock, f5);
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
