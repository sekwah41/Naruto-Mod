package com.sekwah.mods.narutomod.items.itemmodels;

import com.sekwah.mods.narutomod.player.models.ModelNinjaBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFlakJacket extends ModelNinjaBiped {
    public ModelRenderer RightShoulder;
    public ModelRenderer LeftShoulder;
    public ModelRenderer RightNeck;
    public ModelRenderer LeftNeck;
    public ModelRenderer BackNeck;
    public ModelRenderer TopJacket;
    public ModelRenderer RightPocket;
    public ModelRenderer LeftPocket;
    public ModelRenderer FrontCenterTop;
    public ModelRenderer LowerJacket;
    public ModelRenderer LowerCenterBot;

    public ModelRenderer bodyLock;
    public ModelRenderer bodyLockLower;
    public ModelRenderer armLockRight;
    public ModelRenderer armLockLeft;

    public ModelFlakJacket() {
        textureWidth = 64;
        textureHeight = 64;

        RightShoulder = new ModelRenderer(this, 0, 49);
        RightShoulder.addBox(-3.533333F, -2.5F, -2.6F, 6, 3, 5);
        RightShoulder.setRotationPoint(0F, 0F, 0F);
        RightShoulder.setTextureSize(64, 64);
        RightShoulder.mirror = true;
        setRotation(RightShoulder, 0F, 0F, 0F);
        LeftShoulder = new ModelRenderer(this, 0, 49);
        LeftShoulder.addBox(-2.533333F, -2.5F, -2.5F, 6, 3, 5);
        LeftShoulder.setRotationPoint(0F, 0F, 0F);
        LeftShoulder.setTextureSize(64, 64);
        LeftShoulder.mirror = true;
        setRotation(LeftShoulder, 0F, 0F, 0F);
        RightNeck = new ModelRenderer(this, 0, 49);
        RightNeck.addBox(-5F, -2F, -1.133333F, 1, 3, 4);
        RightNeck.setRotationPoint(0F, 0F, 0F);
        RightNeck.setTextureSize(64, 64);
        RightNeck.mirror = true;
        setRotation(RightNeck, 0F, 0F, 0F);
        LeftNeck = new ModelRenderer(this, 0, 49);
        LeftNeck.addBox(4F, -2F, -1.1F, 1, 3, 4);
        LeftNeck.setRotationPoint(0F, 0F, 0F);
        LeftNeck.setTextureSize(64, 64);
        LeftNeck.mirror = true;
        setRotation(LeftNeck, 0F, 0F, 0F);
        BackNeck = new ModelRenderer(this, 0, 50);
        BackNeck.addBox(-5F, -2F, 1.9F, 10, 3, 1);
        BackNeck.setRotationPoint(0F, 0F, 0F);
        BackNeck.setTextureSize(64, 64);
        BackNeck.mirror = true;
        setRotation(BackNeck, 0F, 0F, 0F);
        TopJacket = new ModelRenderer(this, 0, 32);
        TopJacket.addBox(-4.5F, -0.1F, -2.4F, 9, 6, 5);
        TopJacket.setRotationPoint(0F, 0F, 0F);
        TopJacket.setTextureSize(64, 64);
        TopJacket.mirror = true;
        setRotation(TopJacket, 0F, 0F, 0F);
        RightPocket = new ModelRenderer(this, 32, 0);
        RightPocket.addBox(-3.5F, 3.5F, -3F, 2, 3, 1);
        RightPocket.setRotationPoint(0F, 0F, 0F);
        RightPocket.setTextureSize(64, 64);
        RightPocket.mirror = true;
        setRotation(RightPocket, 0F, 0F, 0F);
        LeftPocket = new ModelRenderer(this, 32, 0);
        LeftPocket.addBox(1.466667F, 3.5F, -3F, 2, 3, 1);
        LeftPocket.setRotationPoint(0F, 0F, 0F);
        LeftPocket.setTextureSize(64, 64);
        LeftPocket.mirror = true;
        setRotation(LeftPocket, 0F, 0F, 0F);
        FrontCenterTop = new ModelRenderer(this, 38, 0);
        FrontCenterTop.addBox(-0.5F, -0.1F, -2.533333F, 1, 6, 1);
        FrontCenterTop.setRotationPoint(0F, 0F, 0F);
        FrontCenterTop.setTextureSize(64, 64);
        FrontCenterTop.mirror = true;
        setRotation(FrontCenterTop, 0F, 0F, 0F);
        LowerJacket = new ModelRenderer(this, 0, 39);
        LowerJacket.addBox(-4.5F, -0.1F, -2.4F, 9, 7, 5);
        LowerJacket.setRotationPoint(0F, 0F, 0F);
        LowerJacket.setTextureSize(64, 64);
        LowerJacket.mirror = true;
        setRotation(LowerJacket, 0F, 0F, 0F);
        LowerCenterBot = new ModelRenderer(this, 38, 6);
        LowerCenterBot.addBox(-0.5F, -0.1F, -2.5F, 1, 7, 1);
        LowerCenterBot.setRotationPoint(0F, 0F, 0F);
        LowerCenterBot.setTextureSize(64, 64);
        LowerCenterBot.mirror = true;
        setRotation(LowerCenterBot, 0F, 0F, 0F);


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


        bodyLock.addChild(RightNeck);
        bodyLock.addChild(LeftNeck);
        bodyLock.addChild(BackNeck);
        bodyLock.addChild(TopJacket);
        bodyLock.addChild(RightPocket);
        bodyLock.addChild(LeftPocket);
        bodyLock.addChild(FrontCenterTop);
        bodyLockLower.addChild(LowerJacket);
        bodyLockLower.addChild(LowerCenterBot);
        armLockLeft.addChild(LeftShoulder);
        armLockRight.addChild(RightShoulder);

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
