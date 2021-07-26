package com.sekwah.narutomod.client.renderer.item.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

import java.util.Collections;

public class HeadbandModel<T extends LivingEntity> extends HumanoidModel<T>
{
    public ModelPart Shape3;
    public ModelPart Shape4;
    public ModelPart Shape2;
    public ModelPart Shape5;
    public ModelPart Shape1;

    public ModelPart Shape8;
    public ModelPart Shape9;
    public ModelPart Shape10;

    public ModelPart headLock;

    public HeadbandModel(float modelSize)
    {
        super(modelSize);

        int textureWidth = 64;
        int textureHeight = 32;

        float yOffset = 1.6F;

        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(0F, 0F, 0F, 8, 2, 1);
        Shape3.setPos(-4F, -8F + yOffset, -4.233333F);
        Shape3.setTexSize(textureWidth, textureHeight);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, 0F, 8, 2, 1);
        Shape4.setPos(-4F, -8F + yOffset, 3.2F);
        Shape4.setTexSize(textureWidth, textureHeight);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, 0F, 0F, 8, 2, 1);
        Shape2.setPos(3.1F, -8F + yOffset, 4F);
        Shape2.setTexSize(textureWidth, textureHeight);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 1.570796F, 0F);
        Shape5 = new ModelRenderer(this, 0, 0);
        Shape5.addBox(0F, 0F, 0F, 8, 2, 1);
        Shape5.setPos(-4.2F, -8F + yOffset, 4F);
        Shape5.setTexSize(textureWidth, textureHeight);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 1.570796F, 0F);
        Shape1 = new ModelRenderer(this, 19, 0);
        Shape1.addBox(0F, 0F, 0F, 3, 2, 1, 0.01F);
        Shape1.setPos(-1.5F, -8F + yOffset, -4.533333F);
        Shape1.setTexSize(textureWidth, textureHeight);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);

        Shape8 = new ModelRenderer(this, 0, 3);
        Shape8.addBox(-1F, 0F, 0F, 1, 3, 1);
        Shape8.setPos(0F, -6.333333F + yOffset, 3.6F);
        Shape8.setTexSize(textureWidth, textureHeight);
        Shape8.mirror = true;
        setRotation(Shape8, 0.4833219F, 1.226894F, 0F);
        Shape9 = new ModelRenderer(this, 0, 3);
        Shape9.addBox(0F, 0F, 0F, 1, 3, 1);
        Shape9.setPos(0F, -7.066667F + yOffset, 4.7F);
        Shape9.setTexSize(textureWidth, textureHeight);
        Shape9.mirror = true;
        setRotation(Shape9, -0.5576792F, 1.953013F, 0F);
        Shape10 = new ModelRenderer(this, 0, 0);
        Shape10.addBox(0F, 0F, 0F, 1, 1, 1);
        Shape10.setPos(0.2666667F, -7.533333F + yOffset, 3.933333F);
        Shape10.setTexSize(textureWidth, textureHeight);
        Shape10.mirror = true;
        setRotation(Shape10, 0.4833219F, -0.3717861F, 0.8551081F);

        headLock = new ModelRenderer(this, 1, 1);
        headLock.addBox(0F, 0F, 0F, 0, 0, 0);
        headLock.setPos(0F, 0F, 0F);

        headLock.addChild(Shape1);
        headLock.addChild(Shape2);
        headLock.addChild(Shape3);
        headLock.addChild(Shape4);
        headLock.addChild(Shape5);
        headLock.addChild(Shape8);
        headLock.addChild(Shape9);
        headLock.addChild(Shape10);
    }


    protected Iterable<ModelRenderer> headParts() {
        return ImmutableList.of(this.headLock);
    }

    protected Iterable<ModelRenderer> bodyParts() {
        return Collections.emptyList();
    }

    private void updateLockedLocations(ModelPart trackedPart, ModelPart lockBlock) {

        setRotation(lockBlock, trackedPart.xRot, trackedPart.yRot, trackedPart.zRot);

        lockBlock.setPos(trackedPart.x,trackedPart.y,trackedPart.z);
    }
    @Override
    public void renderToBuffer(PoseStack matrixStack, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        this.updateLockedLocations(this.head, this.headLock);
        super.renderToBuffer(matrixStack, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
    }

    private void setRotation(ModelPart model, float x, float y, float z)
    {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

}
