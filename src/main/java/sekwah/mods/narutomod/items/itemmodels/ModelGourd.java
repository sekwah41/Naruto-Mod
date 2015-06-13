package sekwah.mods.narutomod.items.itemmodels;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import sekwah.mods.narutomod.player.models.ModelNinjaBiped;

public class ModelGourd extends ModelNinjaBiped {
    public ModelRenderer gord_lower_body;
    public ModelRenderer gord_bottom;
    public ModelRenderer gord_shaft;
    public ModelRenderer gord_upper_body;
    public ModelRenderer gord_top;

    public ModelGourd() {
        textureWidth = 64;
        textureHeight = 64;

        gord_lower_body = new ModelRenderer(this, 0, 35);
        gord_lower_body.addBox(-3F, 6F, 2F, 6, 7, 5);
        gord_lower_body.setRotationPoint(0F, 0F, 0F);
        gord_lower_body.setTextureSize(64, 64);
        gord_lower_body.mirror = true;
        setRotation(gord_lower_body, 0F, 0F, 0F);
        gord_bottom = new ModelRenderer(this, 0, 48);
        gord_bottom.addBox(-4F, 7F, 1F, 8, 5, 7);
        gord_bottom.setRotationPoint(0F, 0F, 0F);
        gord_bottom.setTextureSize(64, 64);
        gord_bottom.mirror = true;
        setRotation(gord_bottom, 0F, 0F, 0F);
        gord_shaft = new ModelRenderer(this, 0, 35);
        gord_shaft.addBox(-2F, 2F, 2.5F, 4, 6, 4);
        gord_shaft.setRotationPoint(0F, 0F, 0F);
        gord_shaft.setTextureSize(64, 64);
        gord_shaft.mirror = true;
        setRotation(gord_shaft, 0F, 0F, 0F);
        gord_upper_body = new ModelRenderer(this, 0, 35);
        gord_upper_body.addBox(-2.5F, -2.333333F, 2F, 5, 7, 5);
        gord_upper_body.setRotationPoint(0F, 0F, 0F);
        gord_upper_body.setTextureSize(64, 64);
        gord_upper_body.mirror = true;
        setRotation(gord_upper_body, 0F, 0F, 0F);
        gord_top = new ModelRenderer(this, 0, 36);
        gord_top.addBox(-3F, -1.266667F, 2F, 6, 5, 6);
        gord_top.setRotationPoint(0F, 0F, 0F);
        gord_top.setTextureSize(64, 64);
        gord_top.mirror = true;
        setRotation(gord_top, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        renderWithLock(bipedBody, gord_lower_body, f5);

        renderWithLock(bipedBody, gord_bottom, f5);

        renderWithLock(bipedBody, gord_shaft, f5);

        renderWithLock(bipedBody, gord_upper_body, f5);

        renderWithLock(bipedBody, gord_top, f5);
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
