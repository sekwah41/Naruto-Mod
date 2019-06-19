package sekwah.mods.narutomod.common.items.itemmodels;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import sekwah.mods.narutomod.client.player.models.ModelNinjaBiped;

public class ModelBackScroll extends ModelNinjaBiped {
    //fields
    public ModelRenderer Scroll_paper;
    public ModelRenderer Scroll_shaft;

    public ModelRenderer bodyLock;

    public ModelBackScroll() {
        textureWidth = 64;
        textureHeight = 64;

        bodyLock = new ModelRenderer(this, 1, 1);
        bodyLock.addBox(0F, 0F, 0F, 0, 0, 0);
        bodyLock.setRotationPoint(0F, 0F, 0F);

        Scroll_paper = new ModelRenderer(this, 32, 1);
        Scroll_paper.addBox(-4F, 0F, 2F, 4, 12, 3);
        Scroll_paper.setRotationPoint(0F, 0F, 0F);
        Scroll_paper.setTextureSize(64, 64);
        Scroll_paper.mirror = true;
        setRotation(Scroll_paper, 0F, 0F, -0.3717861F);
        Scroll_shaft = new ModelRenderer(this, 46, 0);
        Scroll_shaft.addBox(-3F, -1F, 2.4F, 2, 14, 2);
        Scroll_shaft.setRotationPoint(0F, 0F, 0F);
        Scroll_shaft.setTextureSize(64, 64);
        Scroll_shaft.mirror = true;
        setRotation(Scroll_shaft, 0F, 0F, -0.3717861F);

        bodyLock.addChild(Scroll_paper);
        bodyLock.addChild(Scroll_shaft);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        renderWithLock(bipedBody, bodyLock, f5);

        //renderWithLock(bipedBody, Scroll_paper, f5);

        //renderWithLock(bipedBody, Scroll_shaft, f5);

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
