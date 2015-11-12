package com.sekwah.mods.narutomod.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBonsai extends ModelBase {
    //fields
    public ModelRenderer Shape1;
    public ModelRenderer Shape2;
    public ModelRenderer Shape3;
    public ModelRenderer Shape4;
    public ModelRenderer Shape5;
    public ModelRenderer Shape6;
    public ModelRenderer Shape7;
    public ModelRenderer Shape8;
    public ModelRenderer Shape9;
    public ModelRenderer Shape10;
    public ModelRenderer Shape11;
    public ModelRenderer Shape12;
    public ModelRenderer Shape13;
    public ModelRenderer Shape14;
    public ModelRenderer Shape15;
    public ModelRenderer Shape16;

    public ModelBonsai() {
        textureWidth = 64;
        textureHeight = 32;


        Shape1 = new ModelRenderer(this, 32, 12);
        Shape1.addBox(-4F, 18F, -4F, 8, 4, 8);
        Shape1.setRotationPoint(0F, 0F, 0F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 3, 11);
        Shape2.addBox(-3.5F, 19.73333F, -3.5F, 7, 1, 7);
        Shape2.setRotationPoint(0F, -2F, 0F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 22);
        Shape3.addBox(-3.5F, 21F, -3.5F, 7, 3, 7);
        Shape3.setRotationPoint(0F, 0F, 0F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(5F, 14F, 0F, 2, 5, 2);
        Shape4.setRotationPoint(0.5F, 0F, -2.5F);
        Shape4.setTextureSize(64, 32);
        Shape4.mirror = true;
        setRotation(Shape4, -0.0743572F, -0.4833219F, 0.2974289F);
        Shape5 = new ModelRenderer(this, 0, 0);
        Shape5.addBox(0.5333334F, 12.6F, -0.4F, 2, 3, 1);
        Shape5.setRotationPoint(-0.05F, 0F, -0.3F);
        Shape5.setTextureSize(64, 32);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, -0.3717861F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(-1.533333F, 8.866667F, -0.3333333F, 1, 4, 1);
        Shape6.setRotationPoint(-0.2F, 0F, 0.5F);
        Shape6.setTextureSize(64, 32);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, -0.2974289F, -0.1858931F);
        Shape7 = new ModelRenderer(this, 0, 0);
        Shape7.addBox(-1F, 10F, -11F, 1, 2, 1);
        Shape7.setRotationPoint(-0.2F, 0F, -1.3F);
        Shape7.setTextureSize(64, 32);
        Shape7.mirror = true;
        setRotation(Shape7, 0.7807508F, 0.5205006F, -0.1115358F);
        Shape8 = new ModelRenderer(this, 8, 1);
        Shape8.addBox(-3F, 8.4F, 0F, 7, 2, 4);
        Shape8.setRotationPoint(0F, 0F, 0F);
        Shape8.setTextureSize(64, 32);
        Shape8.mirror = true;
        setRotation(Shape8, 0F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 8, 1);
        Shape9.addBox(-2.333333F, 7.8F, -2.066667F, 5, 2, 3);
        Shape9.setRotationPoint(0F, 0F, 0F);
        Shape9.setTextureSize(64, 32);
        Shape9.mirror = true;
        setRotation(Shape9, 0F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 10, 0);
        Shape10.addBox(-1.533333F, 9F, -4F, 4, 2, 5);
        Shape10.setRotationPoint(0F, 0F, 0F);
        Shape10.setTextureSize(64, 32);
        Shape10.mirror = true;
        setRotation(Shape10, 0F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 8, 3);
        Shape11.addBox(-0.4F, 14F, -2.133333F, 3, 1, 2);
        Shape11.setRotationPoint(0F, 0F, -0.3F);
        Shape11.setTextureSize(64, 32);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape12 = new ModelRenderer(this, 8, 1);
        Shape12.addBox(-0.6666667F, 9.6F, 0.06666667F, 3, 2, 2);
        Shape12.setRotationPoint(0F, 0F, 0F);
        Shape12.setTextureSize(64, 32);
        Shape12.mirror = true;
        setRotation(Shape12, 0F, 0F, 0F);
        Shape13 = new ModelRenderer(this, 8, 18);
        Shape13.addBox(-3.5F, 17.2F, -4.5F, 8, 1, 1);
        Shape13.setRotationPoint(0F, 0F, 0F);
        Shape13.setTextureSize(64, 32);
        Shape13.mirror = true;
        setRotation(Shape13, 0F, 0F, 0F);
        Shape14 = new ModelRenderer(this, 8, 18);
        Shape14.addBox(-3.5F, 17.2F, -4.5F, 8, 1, 1);
        Shape14.setRotationPoint(0F, 0F, 0F);
        Shape14.setTextureSize(64, 32);
        Shape14.mirror = true;
        setRotation(Shape14, 0F, 1.570796F, 0F);
        Shape15 = new ModelRenderer(this, 8, 18);
        Shape15.addBox(-3.5F, 17.2F, -4.5F, 8, 1, 1);
        Shape15.setRotationPoint(0F, 0F, 0F);
        Shape15.setTextureSize(64, 32);
        Shape15.mirror = true;
        setRotation(Shape15, 0F, 3.141593F, 0F);
        Shape16 = new ModelRenderer(this, 8, 18);
        Shape16.addBox(-3.5F, 17.2F, -4.5F, 8, 1, 1);
        Shape16.setRotationPoint(0F, 0F, 0F);
        Shape16.setTextureSize(64, 32);
        Shape16.mirror = true;
        setRotation(Shape16, 0F, -1.570796F, 0F);

        Shape1.addChild(Shape2);
        Shape1.addChild(Shape3);
        Shape1.addChild(Shape4);
        Shape1.addChild(Shape5);
        Shape1.addChild(Shape6);
        Shape1.addChild(Shape7);
        Shape1.addChild(Shape8);
        Shape1.addChild(Shape9);
        Shape1.addChild(Shape10);
        Shape1.addChild(Shape11);
        Shape1.addChild(Shape12);
        Shape1.addChild(Shape13);
        Shape1.addChild(Shape14);
        Shape1.addChild(Shape15);
        Shape1.addChild(Shape16);

        Shape1.setRotationPoint(0F, -23F, 0F);


    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Shape1.render(f5);
        /**Shape2.render(f5);
         Shape3.render(f5);
         Shape4.render(f5);
         Shape5.render(f5);
         Shape6.render(f5);
         Shape7.render(f5);
         Shape8.render(f5);
         Shape9.render(f5);
         Shape10.render(f5);
         Shape11.render(f5);
         Shape12.render(f5);
         Shape13.render(f5);
         Shape14.render(f5);
         Shape15.render(f5);
         Shape16.render(f5);*/
    }

    public void render() {
        Shape1.render(0.0625F);
        /**Shape2.render(0.0625F);
         Shape3.render(0.0625F);
         Shape4.render(0.0625F);
         Shape5.render(0.0625F);
         Shape6.render(0.0625F);
         Shape7.render(0.0625F);
         Shape8.render(0.0625F);
         Shape9.render(0.0625F);
         Shape10.render(0.0625F);
         Shape11.render(0.0625F);
         Shape12.render(0.0625F);
         Shape13.render(0.0625F);
         Shape14.render(0.0625F);
         Shape15.render(0.0625F);
         Shape16.render(0.0625F);*/
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
