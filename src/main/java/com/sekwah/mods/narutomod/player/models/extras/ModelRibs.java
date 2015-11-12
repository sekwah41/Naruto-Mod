package com.sekwah.mods.narutomod.player.models.extras;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRibs extends ModelBase
{
    //fields
    ModelRenderer leftRib12;
    ModelRenderer leftRib22;
    ModelRenderer leftRib32;
    ModelRenderer leftRib42;
    ModelRenderer rightRib12;
    ModelRenderer rightRib22;
    ModelRenderer rightRib32;
    ModelRenderer rightRib42;
    ModelRenderer Spine;
    ModelRenderer leftRib41;
    ModelRenderer rightRib41;
    ModelRenderer leftRib31;
    ModelRenderer leftRib21;
    ModelRenderer leftRib11;
    ModelRenderer rightRib31;
    ModelRenderer rightRib21;
    ModelRenderer rightRib11;
    ModelRenderer leftRib43;
    ModelRenderer leftRib33;
    ModelRenderer leftRib23;
    ModelRenderer leftRib13;
    ModelRenderer rightRib43;
    ModelRenderer rightRib33;
    ModelRenderer rightRib23;
    ModelRenderer rightRib13;

    public ModelRibs()
    {
        textureWidth = 128;
        textureHeight = 64;

        leftRib12 = new ModelRenderer(this, 0, 0);
        leftRib12.addBox(0F, 0F, 0F, 1, 3, 10);
        leftRib12.setRotationPoint(9F, 18F, -5F);
        leftRib12.setTextureSize(64, 32);
        leftRib12.mirror = true;
        setRotation(leftRib12, 0F, 0F, 0F);
        leftRib22 = new ModelRenderer(this, 0, 0);
        leftRib22.addBox(0F, 0F, 0F, 1, 3, 14);
        leftRib22.setRotationPoint(11F, 10F, -7F);
        leftRib22.setTextureSize(64, 32);
        leftRib22.mirror = true;
        setRotation(leftRib22, 0F, 0F, 0F);
        leftRib32 = new ModelRenderer(this, 0, 0);
        leftRib32.addBox(0F, 0F, 0F, 1, 3, 14);
        leftRib32.setRotationPoint(11F, 2F, -7F);
        leftRib32.setTextureSize(64, 32);
        leftRib32.mirror = true;
        setRotation(leftRib32, 0F, 0F, 0F);
        leftRib42 = new ModelRenderer(this, 0, 0);
        leftRib42.addBox(0F, 0F, 0F, 1, 3, 10);
        leftRib42.setRotationPoint(9F, -5F, -5F);
        leftRib42.setTextureSize(64, 32);
        leftRib42.mirror = true;
        setRotation(leftRib42, 0F, 0F, 0F);
        rightRib12 = new ModelRenderer(this, 0, 0);
        rightRib12.addBox(0F, 0F, 0F, 1, 3, 10);
        rightRib12.setRotationPoint(-10F, 18F, -5F);
        rightRib12.setTextureSize(64, 32);
        rightRib12.mirror = true;
        setRotation(rightRib12, 0F, 0F, 0F);
        rightRib22 = new ModelRenderer(this, 0, 0);
        rightRib22.addBox(0F, 0F, 0F, 1, 3, 14);
        rightRib22.setRotationPoint(-12F, 10F, -7F);
        rightRib22.setTextureSize(64, 32);
        rightRib22.mirror = true;
        setRotation(rightRib22, 0F, 0F, 0F);
        rightRib32 = new ModelRenderer(this, 0, 0);
        rightRib32.addBox(0F, 0F, 0F, 1, 3, 14);
        rightRib32.setRotationPoint(-12F, 2F, -7F);
        rightRib32.setTextureSize(64, 32);
        rightRib32.mirror = true;
        setRotation(rightRib32, 0F, 0F, 0F);
        rightRib42 = new ModelRenderer(this, 0, 0);
        rightRib42.addBox(0F, 0F, 0F, 1, 3, 10);
        rightRib42.setRotationPoint(-10F, -5F, -5F);
        rightRib42.setTextureSize(64, 32);
        rightRib42.mirror = true;
        setRotation(rightRib42, 0F, 0F, 0F);
        Spine = new ModelRenderer(this, 0, 0);
        Spine.addBox(0F, 0F, 0F, 4, 26, 3);
        Spine.setRotationPoint(-2F, -5F, 9F);
        Spine.setTextureSize(64, 32);
        Spine.mirror = true;
        setRotation(Spine, 0F, 0F, 0F);
        leftRib41 = new ModelRenderer(this, 0, 0);
        leftRib41.addBox(0F, 0F, 0F, 10, 3, 1);
        leftRib41.setRotationPoint(1.5F, -5F, 10.3F);
        leftRib41.setTextureSize(64, 32);
        leftRib41.mirror = true;
        setRotation(leftRib41, 0F, 0.6632251F, 0F);
        rightRib41 = new ModelRenderer(this, 0, 0);
        rightRib41.addBox(-10F, 0F, 0F, 10, 3, 1);
        rightRib41.setRotationPoint(-1.6F, -5F, 10.5F);
        rightRib41.setTextureSize(64, 32);
        rightRib41.mirror = true;
        setRotation(rightRib41, 0F, -0.6894051F, 0F);
        leftRib31 = new ModelRenderer(this, 0, 0);
        leftRib31.addBox(0F, 0F, 0F, 11, 3, 1);
        leftRib31.setRotationPoint(1.3F, 2F, 10F);
        leftRib31.setTextureSize(64, 32);
        leftRib31.mirror = true;
        setRotation(leftRib31, 0F, 0.3665191F, 0F);
        leftRib21 = new ModelRenderer(this, 0, 0);
        leftRib21.addBox(0F, 0F, 0F, 11, 3, 1);
        leftRib21.setRotationPoint(1.3F, 10F, 10F);
        leftRib21.setTextureSize(64, 32);
        leftRib21.mirror = true;
        setRotation(leftRib21, 0F, 0.3665191F, 0F);
        leftRib11 = new ModelRenderer(this, 0, 0);
        leftRib11.addBox(0F, -1F, 0F, 10, 3, 1);
        leftRib11.setRotationPoint(1.5F, 19F, 10.3F);
        leftRib11.setTextureSize(64, 32);
        leftRib11.mirror = true;
        setRotation(leftRib11, 0F, 0.6632251F, 0F);
        rightRib31 = new ModelRenderer(this, 0, 0);
        rightRib31.addBox(-11F, 0F, 0F, 11, 3, 1);
        rightRib31.setRotationPoint(-1.3F, 2F, 10F);
        rightRib31.setTextureSize(64, 32);
        rightRib31.mirror = true;
        setRotation(rightRib31, 0F, -0.3665191F, 0F);
        rightRib21 = new ModelRenderer(this, 0, 0);
        rightRib21.addBox(-11F, 0F, 0F, 11, 3, 1);
        rightRib21.setRotationPoint(-1.3F, 10F, 10F);
        rightRib21.setTextureSize(64, 32);
        rightRib21.mirror = true;
        setRotation(rightRib21, 0F, -0.3665191F, 0F);
        rightRib11 = new ModelRenderer(this, 0, 0);
        rightRib11.addBox(-10F, 0F, 0F, 10, 3, 1);
        rightRib11.setRotationPoint(-1.6F, 18F, 10.5F);
        rightRib11.setTextureSize(64, 32);
        rightRib11.mirror = true;
        setRotation(rightRib11, 0F, -0.6894051F, 0F);
        leftRib43 = new ModelRenderer(this, 0, 0);
        leftRib43.addBox(0F, 0F, 0F, 7, 3, 1);
        leftRib43.setRotationPoint(4.6F, -5F, -9.5F);
        leftRib43.setTextureSize(64, 32);
        leftRib43.mirror = true;
        setRotation(leftRib43, 0F, -0.6894051F, 0F);
        leftRib33 = new ModelRenderer(this, 0, 0);
        leftRib33.addBox(0F, 0F, 0F, 7, 3, 1);
        leftRib33.setRotationPoint(6.5F, 2F, -11.4F);
        leftRib33.setTextureSize(64, 32);
        leftRib33.mirror = true;
        setRotation(leftRib33, 0F, -0.6894051F, 0F);
        leftRib23 = new ModelRenderer(this, 0, 0);
        leftRib23.addBox(0F, 0F, 0F, 7, 3, 1);
        leftRib23.setRotationPoint(6.5F, 10F, -11.4F);
        leftRib23.setTextureSize(64, 32);
        leftRib23.mirror = true;
        setRotation(leftRib23, 0F, -0.6894051F, 0F);
        leftRib13 = new ModelRenderer(this, 0, 0);
        leftRib13.addBox(0F, 0F, 0F, 7, 3, 1);
        leftRib13.setRotationPoint(4.6F, 18F, -9.5F);
        leftRib13.setTextureSize(64, 32);
        leftRib13.mirror = true;
        setRotation(leftRib13, 0F, -0.6894051F, 0F);
        rightRib43 = new ModelRenderer(this, 0, 0);
        rightRib43.addBox(0F, 0F, 0F, 7, 3, 1);
        rightRib43.setRotationPoint(-10F, -5F, -5F);
        rightRib43.setTextureSize(64, 32);
        rightRib43.mirror = true;
        setRotation(rightRib43, 0F, 0.6632251F, 0F);
        rightRib33 = new ModelRenderer(this, 0, 0);
        rightRib33.addBox(0F, 0F, 0F, 7, 3, 1);
        rightRib33.setRotationPoint(-12F, 2F, -7F);
        rightRib33.setTextureSize(64, 32);
        rightRib33.mirror = true;
        setRotation(rightRib33, 0F, 0.6632251F, 0F);
        rightRib23 = new ModelRenderer(this, 0, 0);
        rightRib23.addBox(0F, 0F, 0F, 7, 3, 1);
        rightRib23.setRotationPoint(-12F, 10F, -7F);
        rightRib23.setTextureSize(64, 32);
        rightRib23.mirror = true;
        setRotation(rightRib23, 0F, 0.6632251F, 0F);
        rightRib13 = new ModelRenderer(this, 0, 0);
        rightRib13.addBox(0F, 0F, 0F, 7, 3, 1);
        rightRib13.setRotationPoint(-10F, 18F, -5F);
        rightRib13.setTextureSize(64, 32);
        rightRib13.mirror = true;
        setRotation(rightRib13, 0F, 0.6632251F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        leftRib12.render(f5);
        leftRib22.render(f5);
        leftRib32.render(f5);
        leftRib42.render(f5);
        rightRib12.render(f5);
        rightRib22.render(f5);
        rightRib32.render(f5);
        rightRib42.render(f5);
        Spine.render(f5);
        leftRib41.render(f5);
        rightRib41.render(f5);
        leftRib31.render(f5);
        leftRib21.render(f5);
        leftRib11.render(f5);
        rightRib31.render(f5);
        rightRib21.render(f5);
        rightRib11.render(f5);
        leftRib43.render(f5);
        leftRib33.render(f5);
        leftRib23.render(f5);
        leftRib13.render(f5);
        rightRib43.render(f5);
        rightRib33.render(f5);
        rightRib23.render(f5);
        rightRib13.render(f5);

    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
