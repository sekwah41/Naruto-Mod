package sekwah.mods.narutomod.client.entity.model.jutsuprojectiles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelFireball extends ModelBase {

    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;

    public ModelFireball() {
        textureWidth = 128;
        textureHeight = 128;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-9F, -9F, -11F, 18, 18, 22);
        Shape1.setRotationPoint(0F, 0F, 0F);
        Shape1.setTextureSize(128, 128);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 0);
        Shape2.addBox(-11F, -9F, -9F, 22, 18, 18);
        Shape2.setRotationPoint(0F, 0F, 0F);
        Shape2.setTextureSize(128, 128);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
        Shape3 = new ModelRenderer(this, 0, 0);
        Shape3.addBox(-9F, -11F, -9F, 18, 22, 18);
        Shape3.setRotationPoint(0F, 0F, 0F);
        Shape3.setTextureSize(128, 128);
        Shape3.mirror = true;
        setRotation(Shape3, 0F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 0);
        Shape4.addBox(-13F, -5F, -5F, 26, 10, 10);
        Shape4.setRotationPoint(0F, 0F, 0F);
        Shape4.setTextureSize(128, 128);
        Shape4.mirror = true;
        setRotation(Shape4, 0F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 0);
        Shape5.addBox(-5F, -13F, -5F, 10, 26, 10);
        Shape5.setRotationPoint(0F, 0F, 0F);
        Shape5.setTextureSize(128, 128);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, 0F);
        Shape6 = new ModelRenderer(this, 0, 0);
        Shape6.addBox(-5F, -5F, -13F, 10, 10, 26);
        Shape6.setRotationPoint(0F, 0F, 0F);
        Shape6.setTextureSize(128, 128);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);

        // all children of 1 so only 1 lot of rot code needs to be done
        Shape1.addChild(Shape2);
        Shape1.addChild(Shape3);
        Shape1.addChild(Shape4);
        Shape1.addChild(Shape5);
        Shape1.addChild(Shape6);
    }

    /**public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
     {
     super.render(entity, f, f1, f2, f3, f4, f5);
     setRotationAngles(f, f1, f2, f3, f4, f5, entity);
     Shape1.render(f5);*/
    /**Shape2.render(f5);
     Shape3.render(f5);
     Shape4.render(f5);
     Shape5.render(f5);
     Shape6.render(f5);*/
    //}

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        GL11.glPushMatrix();
        float f6 = 2.2F;
        f6 *= par5 / par6;
        GL11.glScalef(f6, f6, f6);
        GL11.glRotatef(60.0F, 0.7071F, 0.0F, 0.7071F);
        GL11.glRotatef(par3, 0.0F, 1.0F, 0.0F); // try and add more random animation rotation or odd stuff
        this.Shape1.render(par7);

        GL11.glPopMatrix();
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
