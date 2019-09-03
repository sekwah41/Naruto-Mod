package sekwah.mods.narutomod.client.item.model.armour;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import sekwah.mods.narutomod.client.player.models.ModelNinjaBiped;

public class ModelHanHat extends ModelNinjaBiped {

    public ModelRenderer shape15;
    public ModelRenderer shape15_1;
    public ModelRenderer base;
    public ModelRenderer field_78116_c_1;
    public ModelRenderer field_78116_c_2;
    public ModelRenderer field_78116_c_3;
    public ModelRenderer field_78116_c_4;
    public ModelRenderer field_78116_c_5;
    public ModelRenderer field_78116_c_6;
    public ModelRenderer field_78116_c_7;
    public ModelRenderer field_78116_c_8;
    public ModelRenderer shape15_2;
    public ModelRenderer shape15_3;
    public ModelRenderer field_78116_c_9;
    public ModelRenderer field_78116_c_10;
    public ModelRenderer field_78116_c_11;
    public ModelRenderer shape15_4;
    public ModelRenderer shape15_5;
    public ModelRenderer shape15_6;

    public ModelHanHat() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.base = new ModelRenderer(this, 0, 30);
        this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.base.addBox(-4.0F, -9.0F, -4.0F, 8, 1, 8, 0.0F);
        this.field_78116_c_5 = new ModelRenderer(this, 0, 30);
        this.field_78116_c_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c_5.addBox(-6.5F, -7.9F, -6.7F, 13, 2, 1, -0.3F);
        this.field_78116_c_4 = new ModelRenderer(this, 0, 30);
        this.field_78116_c_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c_4.addBox(-6.5F, -8.7F, -6.5F, 13, 2, 13, -0.3F);
        this.field_78116_c_8 = new ModelRenderer(this, 0, 30);
        this.field_78116_c_8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c_8.addBox(5.8F, -7.9F, -6.6F, 1, 2, 13, -0.3F);
        this.shape15_5 = new ModelRenderer(this, 0, 0);
        this.shape15_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15_5.addBox(1.6F, -1.8F, -4.6F, 2, 4, 1, -0.3F);
        this.setRotateAngle(shape15_5, 0.0F, 0.0F, -0.9560913642424937F);
        this.shape15_6 = new ModelRenderer(this, 0, 0);
        this.shape15_6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15_6.addBox(-4.5F, -7.8F, -4.5F, 9, 2, 9, 0.2F);
        this.shape15 = new ModelRenderer(this, 0, 23);
        this.shape15.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15.addBox(-1.0F, -3.8F, -4.6F, 2, 4, 1, 0.0F);
        this.shape15_1 = new ModelRenderer( this, 0, 24);
        this.shape15_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15_1.addBox(-2.8F, -2.7F, -4.6F, 2, 3, 1, 0.0F);
        this.setRotateAngle(shape15_1, 0.0F, 0.0F, 0.5462880558742251F);
        this.field_78116_c_6 = new ModelRenderer(this, 0, 30);
        this.field_78116_c_6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c_6.addBox(-6.5F, -7.9F, 5.7F, 13, 2, 1, -0.3F);
        this.field_78116_c_1 = new ModelRenderer(this, 0, 30);
        this.field_78116_c_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c_1.addBox(-4.0F, -9.6F, -4.0F, 8, 1, 8, -0.4F);
        this.shape15_4 = new ModelRenderer(this, 0, 0);
        this.shape15_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15_4.addBox(-3.6F, -1.8F, -4.6F, 2, 4, 1, -0.3F);
        this.setRotateAngle(shape15_4, 0.0F, 0.0F, 0.9560913642424937F);
        this.field_78116_c_10 = new ModelRenderer(this, 0, 0);
        this.field_78116_c_10.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c_10.addBox(4.3F, -8.0F, -4.0F, 1, 10, 9, -0.3F);
        this.field_78116_c_11 = new ModelRenderer(this, 0, 0);
        this.field_78116_c_11.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c_11.addBox(-5.0F, -8.0F, 4.2F, 10, 10, 1, -0.3F);
        this.shape15_2 = new ModelRenderer(this, 0, 24);
        this.shape15_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15_2.addBox(0.8F, -2.7F, -4.6F, 2, 3, 1, 0.0F);
        this.setRotateAngle(shape15_2, 0.0F, 0.0F, -0.5462880558742251F);
        this.field_78116_c_7 = new ModelRenderer(this, 0, 30);
        this.field_78116_c_7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c_7.addBox(-6.7F, -7.9F, -6.6F, 1, 2, 13, -0.3F);
        this.shape15_3 = new ModelRenderer(this, 0, 22);
        this.shape15_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape15_3.addBox(-4.5F, -1.7F, -4.5F, 9, 2, 9, 0.1F);
        this.field_78116_c_3 = new ModelRenderer(this, 0, 30);
        this.field_78116_c_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c_3.addBox(-5.5F, -9.0F, -5.5F, 11, 1, 11, -0.3F);
        this.field_78116_c_9 = new ModelRenderer(this, 0, 0);
        this.field_78116_c_9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c_9.addBox(-5.3F, -8.0F, -4.0F, 1, 10, 9, -0.3F);
        this.field_78116_c_2 = new ModelRenderer(this, 0, 30);
        this.field_78116_c_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c_2.addBox(-3.0F, -9.8F, -3.0F, 6, 1, 6, -0.4F);

        this.base.addChild(shape15);
        this.base.addChild(shape15_1);
        this.base.addChild(field_78116_c_1);
        this.base.addChild(field_78116_c_2);
        this.base.addChild(field_78116_c_3);
        this.base.addChild(field_78116_c_4);
        this.base.addChild(field_78116_c_5);
        this.base.addChild(field_78116_c_6);
        this.base.addChild(field_78116_c_7);
        this.base.addChild(field_78116_c_8);
        this.base.addChild(shape15_2);
        this.base.addChild(shape15_3);
        this.base.addChild(field_78116_c_9);
        this.base.addChild(field_78116_c_10);
        this.base.addChild(field_78116_c_11);
        this.base.addChild(shape15_4);
        this.base.addChild(shape15_5);
        this.base.addChild(shape15_6);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        this.renderTracked(this.base, f5, this.bipedHead);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
