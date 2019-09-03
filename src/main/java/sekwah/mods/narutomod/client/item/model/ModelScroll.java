package sekwah.mods.narutomod.client.item.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelScroll extends ModelBase {
    //fields
    public ModelRenderer Paper;
    public ModelRenderer Center;

    public ModelScroll() {
        textureWidth = 64;
        textureHeight = 32;

        Paper = new ModelRenderer(this, 28, 0);
        Paper.addBox(-1.5F, -1.5F, 0F, 3, 3, 10);
        Paper.setRotationPoint(0F, 0F, 0.5F);
        Paper.setTextureSize(64, 32);
        Paper.mirror = true;
        setRotation(Paper, 1.570796F, 0F, 0F);
        Center = new ModelRenderer(this, 0, 0);
        Center.addBox(-1F, -1F, -1F, 2, 2, 12);
        Center.setRotationPoint(0F, 0F, 0.5F);
        Center.setTextureSize(64, 32);
        Center.mirror = true;
        setRotation(Center, 1.570796F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Paper.render(f5);
        Center.render(f5);
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
