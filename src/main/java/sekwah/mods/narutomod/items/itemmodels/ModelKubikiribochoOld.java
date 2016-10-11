package sekwah.mods.narutomod.items.itemmodels;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelKubikiribochoOld extends ModelBase {
    //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;

    public ModelKubikiribochoOld() {
        textureWidth = 64;
        textureHeight = 32;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-1F, 0F, 8.133333F, 2, 2, 6);
        Shape1.setRotationPoint(0F, 13F, 0F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(0F, -1.466667F, -7F, 1, 3, 19);
        Shape2.setRotationPoint(-0.5F, 13F, 0F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 49, 24);
        Shape3.addBox(0F, 0.3333333F, 13.46667F, 1, 1, 7);
        Shape3.setRotationPoint(-0.5F, 13F, 0F);
        Shape3.setTextureSize(64, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(0F, 0F, 20.46667F, 2, 2, 2);
        Shape4.setRotationPoint(-1F, 13F, 0F);
        Shape4.setTextureSize(64, 32);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 17, 17);
        Shape5.addBox(0F, 0F, 6.066667F, 1, 3, 6);
        Shape5.setRotationPoint(-0.5F, 13F, 0F);
        Shape5.setTextureSize(64, 32);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(0F, 1F, -10.06667F, 1, 2, 14);
        Shape6.setRotationPoint(-0.5F, 13F, 0F);
        Shape6.setTextureSize(64, 32);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 26, 14);
        Shape7.addBox(0F, 2.766667F, -12.6F, 1, 3, 4);
        Shape7.setRotationPoint(-0.5F, 13F, 0F);
        Shape7.setTextureSize(64, 32);
        Shape7.mirror = true;
        setRotation(Shape7, -0.2974289F, 0F, 0F);
        Shape8 = new ModelRenderer(this, 30, 15);
        Shape8.addBox(-0.5F, -10.8F, -11.4F, 1, 3, 2);
        Shape8.setRotationPoint(0F, 13F, 0F);
        Shape8.setTextureSize(64, 32);
        Shape8.mirror = true;
        setRotation(Shape8, 0.7435722F, 0F, 0F);
        Shape9 = new ModelRenderer(this, 31, 20);
        Shape9.addBox(-0.5F, -0.7F, -15.73333F, 1, 1, 3);
        Shape9.setRotationPoint(0F, 13.1F, 0F);
        Shape9.setTextureSize(64, 32);
        Shape9.mirror = true;
        setRotation(Shape9, -0.0743572F, 0F, 0F);
        Shape10 = new ModelRenderer(this, 33, 19);
        Shape10.addBox(-0.5F, -1.9F, -15.7F, 1, 1, 1);
        Shape10.setRotationPoint(0F, 13F, 0F);
        Shape10.setTextureSize(64, 32);
        Shape10.mirror = true;
        setRotation(Shape10, 0.0371786F, 0F, 0F);
        Shape11 = new ModelRenderer(this, 26, 15);
        Shape11.addBox(-0.5F, -1F, -14F, 1, 2, 5);
        Shape11.setRotationPoint(0F, 13F, 0F);
        Shape11.setTextureSize(64, 32);
        Shape11.mirror = true;
        setRotation(Shape11, 0F, 0F, 0F);
        Shape12 = new ModelRenderer(this, 28, 17);
        Shape12.addBox(0F, -0.4F, -13.26667F, 1, 1, 8);
        Shape12.setRotationPoint(-0.5F, 12F, 0F);
        Shape12.setTextureSize(64, 32);
        Shape12.mirror = true;
        setRotation(Shape12, -0.0125893F, 0F, 0F);

    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);

        GL11.glScalef(1.2F, 1.2F, 1.2F);
        GL11.glTranslatef(0.38F, -1.2F, -0.08F);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glRotatef(100.0F, 0.0F, 90.0F, 0.0F);
        GL11.glRotatef(-70.0F, 90.0F, 0.0F, 0.0F);

        /**GL11.glScalef(1.3F, 1.3F, 1.3F);
         GL11.glTranslatef(0.68F, -1.6F, -0.12F);
         setRotationAngles(f, f1, f2, f3, f4, f5, entity);
         GL11.glRotatef(100, 0F, 90F, 0F);
         GL11.glRotatef(-70, 90F, 0F, 0F);

         //GL11.glScalef(0.5F, 1.3F, 1.3F);*/


        Shape1.render(f5);
        Shape2.render(f5);
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
