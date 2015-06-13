package sekwah.mods.narutomod.items.itemmodels;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSamehadaOld extends ModelBase {
    //fields
    public ModelRenderer HandleTop;
    public ModelRenderer MainBlade;
    public ModelRenderer BladeTip;
    public ModelRenderer HandleMid;
    public ModelRenderer HandleBottom;

    public ModelSamehadaOld() {
        textureWidth = 128;
        textureHeight = 64;

        HandleTop = new ModelRenderer(this, 0, 25);
        HandleTop.addBox(-1F, -1F, 1.9F, 2, 2, 5);
        HandleTop.setRotationPoint(0F, 0F, 0F);
        HandleTop.setTextureSize(128, 64);
        HandleTop.mirror = true;
        setRotation(HandleTop, 1.570796F, 0F, 0F);
        MainBlade = new ModelRenderer(this, 14, 7);
        MainBlade.addBox(-2.5F, -1.5F, 4F, 5, 3, 20);
        MainBlade.setRotationPoint(0F, 0F, 0F);
        MainBlade.setTextureSize(128, 64);
        MainBlade.mirror = true;
        setRotation(MainBlade, 1.570796F, 0F, 0F);
        BladeTip = new ModelRenderer(this, 14, 7);
        BladeTip.addBox(-1.6F, -1F, 22.8F, 3, 2, 2);
        BladeTip.setRotationPoint(0F, 0F, 0F);
        BladeTip.setTextureSize(128, 64);
        BladeTip.mirror = true;
        setRotation(BladeTip, 1.570796F, 0F, 0F);
        HandleMid = new ModelRenderer(this, 0, 32);
        HandleMid.addBox(-0.5F, -0.5F, -3F, 1, 1, 10);
        HandleMid.setRotationPoint(0F, 0F, 0F);
        HandleMid.setTextureSize(128, 64);
        HandleMid.mirror = true;
        setRotation(HandleMid, 1.570796F, 0F, 0F);
        HandleBottom = new ModelRenderer(this, 6, 21);
        HandleBottom.addBox(-1F, -1F, -4F, 2, 2, 2);
        HandleBottom.setRotationPoint(0F, 0F, -0.03333334F);
        HandleBottom.setTextureSize(128, 64);
        HandleBottom.mirror = true;
        setRotation(HandleBottom, 1.570796F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        HandleTop.render(f5);
        MainBlade.render(f5);
        BladeTip.render(f5);
        HandleMid.render(f5);
        HandleBottom.render(f5);
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
