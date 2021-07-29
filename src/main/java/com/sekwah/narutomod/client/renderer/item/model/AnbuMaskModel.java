package com.sekwah.narutomod.client.renderer.item.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sekwah.narutomod.util.ModelUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

import java.util.Collections;

public class AnbuMaskModel<T extends LivingEntity> extends HumanoidModel<T> {

    private final ModelPart head;

    /*public ModelPart mask;
    public ModelPart mouth;
    public ModelPart eyebrowLeft;
    public ModelPart eyebrowRight;
    public ModelPart nose;
    public ModelPart rightEar;
    public ModelPart leftEar;
    public ModelPart headBandLeft;
    public ModelPart headbandRight;
    public ModelPart headbandBack;*/

    public ModelPart headLock;

    public AnbuMaskModel(ModelPart modelPart, boolean hasEars)
    {
        super(modelPart);
        this.head = modelPart.getChild("head");

        /*int textureWidth = 64;
        int textureHeight = 32;

        mask = new ModelPart(this, 32, 0);
        mask.add(-4F, -8.466666F, -4.266667F, 8, 9, 1, 0.01F);
        mask.setPos(0F, 0F, 0F);
        mask.setTexSize(textureWidth, textureHeight);
        mask.mirror = true;
        setRotation(mask, 0F, 0F, 0F);
        mouth = new ModelPart(this, 50, 8);
        mouth.addBox(-2F, -5F, -5F, 4, 5, 1);
        mouth.setPos(0F, 0F, 0F);
        mouth.setTexSize(textureWidth, textureHeight);
        mouth.mirror = true;
        setRotation(mouth, -0.0743572F, 0F, 0F);
        eyebrowLeft = new ModelPart(this, 50, 3);
        eyebrowLeft.addBox(1F, -6F, -5F, 2, 1, 1);
        eyebrowLeft.setPos(0F, 0F, 0F);
        eyebrowLeft.setTexSize(textureWidth, textureHeight);
        eyebrowLeft.mirror = true;
        setRotation(eyebrowLeft, 0F, 0F, 0F);
        eyebrowRight = new ModelPart(this, 50, 3);
        eyebrowRight.addBox(-3F, -6F, -5F, 2, 1, 1);
        eyebrowRight.setPos(0F, 0F, 0F);
        eyebrowRight.setTexSize(textureWidth, textureHeight);
        eyebrowRight.mirror = true;
        setRotation(eyebrowRight, 0F, 0F, 0F);
        nose = new ModelPart(this, 50, 5);
        nose.addBox(-1F, -5F, -5F, 2, 2, 1);
        nose.setPos(0F, 0F, 0F);
        nose.setTexSize(textureWidth, textureHeight);
        nose.mirror = true;
        setRotation(nose, 0F, 0F, 0F);
        if(hasEars) {
            // TODO render as seperate layer
            rightEar = new ModelPart(this, 50, 0);
            rightEar.addBox(-8.066667F, -6F, -4.3F, 2, 2, 1);
            rightEar.setPos(-0.35F, 0.04F, 0F);
            rightEar.setTexSize(textureWidth, textureHeight);
            rightEar.mirror = true;
            setRotation(rightEar, 0F, 0F, 0.7853982F);
            leftEar = new ModelPart(this, 50, 0);
            leftEar.addBox(-5F, -9F, -4.3F, 2, 2, 1);
            leftEar.setPos(-0.9333333F, 0F, 0F);
            leftEar.setTexSize(textureWidth, textureHeight);
            leftEar.mirror = true;
            setRotation(leftEar, 0F, 0F, 0.7853982F);
        }
        headBandLeft = new ModelPart(this, 60, 23);
        headBandLeft.addBox(3.1F, -3.6F, 3F, 1, 8, 1);
        headBandLeft.setPos(0F, 0F, 0F);
        headBandLeft.setTexSize(textureWidth, textureHeight);
        headBandLeft.mirror = true;
        setRotation(headBandLeft, 1.570796F, 0F, 0F);
        headbandRight = new ModelPart(this, 60, 23);
        headbandRight.addBox(-4.1F, -3.733333F, 3F, 1, 8, 1);
        headbandRight.setPos(0F, 0F, 0F);
        headbandRight.setTexSize(textureWidth, textureHeight);
        headbandRight.mirror = true;
        setRotation(headbandRight, 1.570796F, 0F, 0F);
        headbandBack = new ModelPart(this, 60, 23);
        headbandBack.addBox(-4.4F, -4F, 3F, 1, 8, 1);
        headbandBack.setPos(0F, 0F, 0F);
        headbandBack.setTexSize(textureWidth, textureHeight);
        headbandBack.mirror = true;
        setRotation(headbandBack, 1.570796F, 1.570796F, 0F);

        headLock = new ModelPartScaled(this, 1, 1, 1.1f);
        headLock.addBox(0F, 0F, 0F, 0, 0, 0);
        headLock.setPos(0F, 0F, 0F);

        headLock.addChild(mask);
        headLock.addChild(mouth);
        headLock.addChild(eyebrowLeft);
        headLock.addChild(eyebrowRight);
        headLock.addChild(nose);
        if(hasEars) {
            headLock.addChild(rightEar);
            headLock.addChild(leftEar);
        }
        headLock.addChild(headBandLeft);
        headLock.addChild(headbandRight);
        headLock.addChild(headbandBack);*/
    }

    public static LayerDefinition createLayer() {
        MeshDefinition definition = ModelUtils.createBlankHumanoidMesh();
        PartDefinition partdefinition = definition.getRoot();
        PartDefinition root = definition.getRoot();
        float f = -16.0F;
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().addBox("upper_lip", -6.0F, -1.0F, -24.0F, 12, 5, 16, 176, 44).addBox("upper_head", -8.0F, -8.0F, -10.0F, 16, 16, 16, 112, 30).mirror(true).addBox("scale", -5.0F, -12.0F, -4.0F, 2, 4, 6, 0, 0).addBox("nostril", -5.0F, -3.0F, -22.0F, 2, 2, 4, 112, 0).mirror(false).addBox("scale", 3.0F, -12.0F, -4.0F, 2, 4, 6, 0, 0).addBox("nostril", 3.0F, -3.0F, -22.0F, 2, 2, 4, 112, 0), PartPose.ZERO);
        partdefinition1.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(176, 65).addBox("jaw", -6.0F, 0.0F, -16.0F, 12.0F, 4.0F, 16.0F), PartPose.offset(0.0F, 4.0F, -8.0F));

        return LayerDefinition.create(definition, 64, 32);
    }

    private void updateLockedLocations(ModelPart trackedPart, ModelPart lockBlock) {

        setRotation(lockBlock, trackedPart.xRot, trackedPart.yRot, trackedPart.zRot);

        lockBlock.setPos(trackedPart.x,trackedPart.y,trackedPart.z);
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        matrixStack.pushPose();
        matrixStack.scale(0.75F, 0.75F, 0.75F);
        this.head.render(matrixStack, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
        matrixStack.popPose();
    }

    private void setRotation(ModelPart model, float x, float y, float z)
    {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

}
