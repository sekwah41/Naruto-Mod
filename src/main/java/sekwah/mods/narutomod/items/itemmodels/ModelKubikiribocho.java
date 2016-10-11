package sekwah.mods.narutomod.items.itemmodels;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelKubikiribocho extends ModelBase {
    //fields
    ModelRenderer Handle;
    ModelRenderer Hilt;
    ModelRenderer Blade_Extrusion_Top;
    ModelRenderer Blade_Extrusion_Base;
    ModelRenderer Bottom_Smooth_Corner;
    ModelRenderer Top_Smooth_Corner;
    ModelRenderer Top_Rivet;
    ModelRenderer Middle_Rivet;
    ModelRenderer Bottom_Rivet;
    ModelRenderer Blade_Top_L;
    ModelRenderer Blade_Top;
    ModelRenderer Indent_Side;
    ModelRenderer Outside_Hole;
    ModelRenderer Blade_Base;
    ModelRenderer Inside_Hole;
    ModelRenderer Blade_Majority;

    public ModelKubikiribocho() {
        textureWidth = 64;
        textureHeight = 32;

        Handle = new ModelRenderer(this, 28, 15);
        Handle.addBox(0F, 0F, 0F, 1, 14, 1);
        Handle.setRotationPoint(1F, 0F, 0F);
        Handle.setTextureSize(64, 32);
        Handle.mirror = true;
        setRotation(Handle, 0F, 0F, 0F);
        Hilt = new ModelRenderer(this, 0, 23);
        Hilt.addBox(0F, 0F, 0F, 15, 1, 3);
        Hilt.setRotationPoint(-6F, 0F, -1F);
        Hilt.setTextureSize(64, 32);
        Hilt.mirror = true;
        setRotation(Hilt, 0F, 0F, 0F);
        Blade_Extrusion_Top = new ModelRenderer(this, 0, 0);
        Blade_Extrusion_Top.addBox(0F, 0F, 0F, 4, 1, 2);
        Blade_Extrusion_Top.setRotationPoint(-0.5F, -3F, -0.5F);
        Blade_Extrusion_Top.setTextureSize(64, 32);
        Blade_Extrusion_Top.mirror = true;
        setRotation(Blade_Extrusion_Top, 0F, 0F, 0F);
        Blade_Extrusion_Base = new ModelRenderer(this, 0, 0);
        Blade_Extrusion_Base.addBox(0F, 0F, 0F, 5, 2, 2);
        Blade_Extrusion_Base.setRotationPoint(-1F, -2F, -0.4833333F);
        Blade_Extrusion_Base.setTextureSize(64, 32);
        Blade_Extrusion_Base.mirror = true;
        setRotation(Blade_Extrusion_Base, 0F, 0F, 0F);
        Bottom_Smooth_Corner = new ModelRenderer(this, 0, 0);
        Bottom_Smooth_Corner.addBox(0F, 0F, 0F, 2, 1, 1);
        Bottom_Smooth_Corner.setRotationPoint(0F, -6F, 0F);
        Bottom_Smooth_Corner.setTextureSize(64, 32);
        Bottom_Smooth_Corner.mirror = true;
        setRotation(Bottom_Smooth_Corner, 0F, 0F, -0.7853982F);
        Top_Smooth_Corner = new ModelRenderer(this, 0, 0);
        Top_Smooth_Corner.addBox(0F, 0F, 0F, 2, 1, 1);
        Top_Smooth_Corner.setRotationPoint(0.5333334F, -11F, 0F);
        Top_Smooth_Corner.setTextureSize(64, 32);
        Top_Smooth_Corner.mirror = true;
        setRotation(Top_Smooth_Corner, 0F, 0F, 0.7853982F);
        Top_Rivet = new ModelRenderer(this, 0, 0);
        Top_Rivet.addBox(0F, 0F, 0F, 2, 3, 2);
        Top_Rivet.setRotationPoint(0.4666667F, 1.6F, -0.5333334F);
        Top_Rivet.setTextureSize(64, 32);
        Top_Rivet.mirror = true;
        setRotation(Top_Rivet, 0F, 0F, 0F);
        Middle_Rivet = new ModelRenderer(this, 0, 0);
        Middle_Rivet.addBox(0F, 0F, 0F, 2, 3, 2);
        Middle_Rivet.setRotationPoint(0.5F, 5.783333F, -0.5F);
        Middle_Rivet.setTextureSize(64, 32);
        Middle_Rivet.mirror = true;
        setRotation(Middle_Rivet, 0F, 0F, 0F);
        Bottom_Rivet = new ModelRenderer(this, 0, 0);
        Bottom_Rivet.addBox(0F, 0F, 0F, 2, 3, 2);
        Bottom_Rivet.setRotationPoint(0.5F, 10F, -0.5F);
        Bottom_Rivet.setTextureSize(64, 32);
        Bottom_Rivet.mirror = true;
        setRotation(Bottom_Rivet, 0F, 0F, 0F);
        Blade_Top_L = new ModelRenderer(this, 0, 0);
        Blade_Top_L.addBox(0F, 0F, 0F, 8, 3, 1);
        Blade_Top_L.setRotationPoint(-2F, -32F, 0F);
        Blade_Top_L.setTextureSize(64, 32);
        Blade_Top_L.mirror = true;
        setRotation(Blade_Top_L, 0F, 0F, 0F);
        Blade_Top = new ModelRenderer(this, 0, 0);
        Blade_Top.addBox(0F, 0F, 0F, 8, 1, 1);
        Blade_Top.setRotationPoint(-1F, -33F, 0F);
        Blade_Top.setTextureSize(64, 32);
        Blade_Top.mirror = true;
        setRotation(Blade_Top, 0F, 0F, 0F);
        Indent_Side = new ModelRenderer(this, 0, 0);
        Indent_Side.addBox(0F, 0F, 0F, 5, 4, 1);
        Indent_Side.setRotationPoint(1F, -10F, 0F);
        Indent_Side.setTextureSize(64, 32);
        Indent_Side.mirror = true;
        setRotation(Indent_Side, 0F, 0F, 0F);
        Outside_Hole = new ModelRenderer(this, 0, 0);
        Outside_Hole.addBox(0F, 0F, 0F, 2, 4, 1);
        Outside_Hole.setRotationPoint(-3F, -30F, 0F);
        Outside_Hole.setTextureSize(64, 32);
        Outside_Hole.mirror = true;
        setRotation(Outside_Hole, 0F, 0F, 0F);
        Blade_Base = new ModelRenderer(this, 0, 0);
        Blade_Base.addBox(0F, 0F, 0F, 9, 6, 1);
        Blade_Base.setRotationPoint(-3F, -6F, 0F);
        Blade_Base.setTextureSize(64, 32);
        Blade_Base.mirror = true;
        setRotation(Blade_Base, 0F, 0F, 0F);
        Inside_Hole = new ModelRenderer(this, 2, 4);
        Inside_Hole.addBox(2F, -29F, 0F, 3, 4, 1);
        Inside_Hole.setRotationPoint(1F, -1F, 0F);
        Inside_Hole.setTextureSize(64, 32);
        Inside_Hole.mirror = true;
        setRotation(Inside_Hole, 0F, 0F, 0F);
        Blade_Majority = new ModelRenderer(this, 0, 0);
        Blade_Majority.addBox(0F, 0F, 0F, 9, 16, 1);
        Blade_Majority.setRotationPoint(-3F, -26F, 0F);
        Blade_Majority.setTextureSize(64, 32);
        Blade_Majority.mirror = true;
        setRotation(Blade_Majority, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);

        /*GL11.glScalef(1.2F, 1.2F, 1.2F);
        GL11.glTranslatef(0.38F, -1.2F, -0.08F);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glRotatef(100.0F, 0.0F, 90.0F, 0.0F);
        GL11.glRotatef(-70.0F, 90.0F, 0.0F, 0.0F);*/



        //GL11.glRotatef(-90, 0, 1, 0);
        //GL11.glScalef(0.7F, 0.7F, 0.7F);
        GL11.glTranslatef(-0.1F, -0.3F, 0);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glRotatef(-20,0,0,1);
        GL11.glTranslatef(-0.1F, 0, -0.05f);
        //GL11.glRotatef(90, 30, 250, 0);

        Handle.render(f5);
        Hilt.render(f5);
        Blade_Extrusion_Top.render(f5);
        Blade_Extrusion_Base.render(f5);
        Bottom_Smooth_Corner.render(f5);
        Top_Smooth_Corner.render(f5);
        Top_Rivet.render(f5);
        Middle_Rivet.render(f5);
        Bottom_Rivet.render(f5);
        Blade_Top_L.render(f5);
        Blade_Top.render(f5);
        Indent_Side.render(f5);
        Outside_Hole.render(f5);
        Blade_Base.render(f5);
        Inside_Hole.render(f5);
        Blade_Majority.render(f5);
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
