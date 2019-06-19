package sekwah.mods.narutomod.common.items.itemmodels;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import sekwah.mods.narutomod.animation.modelparts.ModelRetexturedBox;
import sekwah.mods.narutomod.client.player.models.ModelNinjaBiped;

public class ModelFlakJacket extends ModelNinjaBiped {
    public ModelRenderer rightShoulder;
    public ModelRenderer leftShoulder;
    public ModelRenderer rightNeck;
    public ModelRenderer leftNeck;
    public ModelRenderer backNeck;
    public ModelRenderer topJacket;
    public ModelRenderer rightPocket;
    public ModelRenderer leftPocket;
    public ModelRenderer frontCenterTop;
    public ModelRenderer lowerJacket;
    public ModelRenderer lowerCenterBot;

    public ModelRenderer bodyLock;
    public ModelRenderer bodyLockLower;
    public ModelRenderer armLockRight;
    public ModelRenderer armLockLeft;

    public ModelFlakJacket() {
        textureWidth = 64;
        textureHeight = 64;

        rightShoulder = new ModelRenderer(this, 0, 49);
        rightShoulder.addBox(-3.533333F, -2.5F, -2.6F, 6, 3, 5);
        rightShoulder.setRotationPoint(0F, 0F, 0F);
        rightShoulder.setTextureSize(64, 64);
        setRotation(rightShoulder, 0F, 0F, 0F);
        leftShoulder = new ModelRenderer(this, 0, 39);
        leftShoulder.addBox(-2.533333F, -2.5F, -2.5F, 6, 3, 5);
        leftShoulder.setRotationPoint(0F, 0F, 0F);
        leftShoulder.setTextureSize(64, 64);
        leftShoulder.mirror = true;
        setRotation(leftShoulder, 0F, 0F, 0F);
        rightNeck = new ModelRenderer(this, 0, 49);
        rightNeck.addBox(-5F, -2F, -1.133333F, 1, 3, 4);
        rightNeck.setRotationPoint(0F, 0F, 0F);
        rightNeck.setTextureSize(64, 64);
        rightNeck.mirror = true;
        setRotation(rightNeck, 0F, 0F, 0F);
        leftNeck = new ModelRenderer(this, 0, 49);
        leftNeck.addBox(4F, -2F, -1.1F, 1, 3, 4);
        leftNeck.setRotationPoint(0F, 0F, 0F);
        leftNeck.setTextureSize(64, 64);
        leftNeck.mirror = true;
        setRotation(leftNeck, 0F, 0F, 0F);
        backNeck = new ModelRenderer(this, 0, 50);
        backNeck.addBox(-5F, -2F, 1.9F, 10, 3, 1);
        backNeck.setRotationPoint(0F, 0F, 0F);
        backNeck.setTextureSize(64, 64);
        backNeck.mirror = true;
        setRotation(backNeck, 0F, 0F, 0F);
        topJacket = new ModelRenderer(this, 0, 32);
        topJacket.addBox(-4.5F, -0.1F, -2.4F, 9, 6, 5);
        topJacket.setRotationPoint(0F, 0F, 0F);
        topJacket.setTextureSize(64, 64);
        topJacket.mirror = true;
        setRotation(topJacket, 0F, 0F, 0F);
        rightPocket = new ModelRenderer(this, 32, 0);
        rightPocket.addBox(-3.5F, 3.5F, -3F, 2, 3, 1);
        rightPocket.setRotationPoint(0F, 0F, 0F);
        rightPocket.setTextureSize(64, 64);
        rightPocket.mirror = true;
        setRotation(rightPocket, 0F, 0F, 0F);
        leftPocket = new ModelRenderer(this, 32, 0);
        leftPocket.addBox(1.466667F, 3.5F, -3F, 2, 3, 1);
        leftPocket.setRotationPoint(0F, 0F, 0F);
        leftPocket.setTextureSize(64, 64);
        leftPocket.mirror = true;
        setRotation(leftPocket, 0F, 0F, 0F);
        frontCenterTop = new ModelRenderer(this, 38, 0);
        frontCenterTop.addBox(-0.5F, -0.1F, -2.533333F, 1, 6, 1);
        frontCenterTop.setRotationPoint(0F, 0F, 0F);
        frontCenterTop.setTextureSize(64, 64);
        frontCenterTop.mirror = true;
        setRotation(frontCenterTop, 0F, 0F, 0F);
        lowerJacket = new ModelRenderer(this, 0, 39);
        lowerJacket.cubeList.add(new ModelRetexturedBox(this.lowerJacket, 0, 39, -4.5F, -0.1F, -2.4F, 9, 7, 5, 0, 0, 39, 0, 39));
        //lowerJacket.addBox(-4.5F, -0.1F, -2.4F, 9, 7, 5);
        //lowerJacket.addBox(-4.5F, -0.1F, -2.4F, 9, 7, 5);
        lowerJacket.setRotationPoint(0F, 0F, 0F);
        lowerJacket.setTextureSize(64, 64);
        lowerJacket.mirror = true;
        setRotation(lowerJacket, 0F, 0F, 0F);
        lowerCenterBot = new ModelRenderer(this, 38, 6);
        lowerCenterBot.addBox(-0.5F, -0.1F, -2.5F, 1, 7, 1);
        lowerCenterBot.setRotationPoint(0F, 0F, 0F);
        lowerCenterBot.setTextureSize(64, 64);
        lowerCenterBot.mirror = true;
        setRotation(lowerCenterBot, 0F, 0F, 0F);


        bodyLock = new ModelRenderer(this, 1, 1);
        bodyLock.addBox(0F, 0F, 0F, 0, 0, 0);
        bodyLock.setRotationPoint(0F, 0F, 0F);

        bodyLockLower = new ModelRenderer(this, 1, 1);
        bodyLockLower.addBox(0F, 0F, 0F, 0, 0, 0);
        bodyLockLower.setRotationPoint(0F, 0F, 0F);

        armLockLeft = new ModelRenderer(this, 1, 1);
        armLockLeft.addBox(0F, 0F, 0F, 0, 0, 0);
        armLockLeft.setRotationPoint(0F, 0F, 0F);

        armLockRight = new ModelRenderer(this, 1, 1);
        armLockRight.addBox(0F, 0F, 0F, 0, 0, 0);
        armLockRight.setRotationPoint(0F, 0F, 0F);


        bodyLock.addChild(rightNeck);
        bodyLock.addChild(leftNeck);
        bodyLock.addChild(backNeck);
        bodyLock.addChild(topJacket);
        bodyLock.addChild(rightPocket);
        bodyLock.addChild(leftPocket);
        bodyLock.addChild(frontCenterTop);
        bodyLockLower.addChild(lowerJacket);
        bodyLockLower.addChild(lowerCenterBot);
        armLockLeft.addChild(leftShoulder);
        armLockRight.addChild(rightShoulder);

    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        renderWithLock(bipedBody, bodyLock, f5);

        renderWithLock(bipedLowerBody, bodyLockLower, f5);

        renderWithLock(this.bipedLeftArmUpper, armLockLeft, f5);

        renderWithLock(this.bipedRightArmUpper, armLockRight, f5);
    }

    private void renderWithLock(ModelRenderer bipedBody, ModelRenderer lockblock, float f5) {

        setRotation(lockblock, bipedBody.rotateAngleX, bipedBody.rotateAngleY, bipedBody.rotateAngleZ);

        lockblock.setRotationPoint(bipedBody.rotationPointX, bipedBody.rotationPointY, bipedBody.rotationPointZ);

        lockblock.render(f5);
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
