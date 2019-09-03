package sekwah.mods.narutomod.client.item.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import sekwah.mods.narutomod.client.player.models.ModelNinjaBiped;

public class ModelBackBokken extends ModelNinjaBiped {

    public ModelRenderer blade;
    public ModelRenderer handle;
    public ModelRenderer strap1;
    public ModelRenderer strap2;

    public ModelRenderer bodyLock;
    public ModelRenderer bodyLock2;

    public ModelBackBokken() {
        textureWidth = 64;
        textureHeight = 64;

        bodyLock = new ModelRenderer(this, 1, 1);
        bodyLock.addBox(0F, 0F, 0F, 0, 0, 0);
        bodyLock.setRotationPoint(0F, 0F, 0F);

        blade = new ModelRenderer(this, 32, 1);
        blade.addBox(-4F, -2F, 2F, 1, 14, 1);
        blade.setRotationPoint(0F, 0F, 0F);
        blade.setTextureSize(64, 64);
        blade.mirror = true;
        setRotation(blade, 0F, 0F, -0.7435722F);
        handle = new ModelRenderer(this, 36, 7);
        handle.addBox(-4F, -7F, 2F, 1, 5, 1);
        handle.setRotationPoint(0F, 0F, 0F);
        handle.setTextureSize(64, 64);
        handle.mirror = true;
        setRotation(handle, 0F, 0F, -0.7435722F);
        strap1 = new ModelRenderer(this, 36, 4);
        strap1.addBox(0F, 2.5F, 1.733333F, 1, 2, 1);
        strap1.setRotationPoint(0F, 0F, 0F);
        strap1.setTextureSize(64, 64);
        strap1.mirror = true;
        setRotation(strap1, 0F, 0F, 0.7435722F);

        /**strap2 = new ModelRenderer(this, 36, 4);
         strap2.addBox(6F, 3F, 1.733333F, 1, 2, 1);
         strap2.setRotationPoint(0F, 0F, 0F);
         strap2.setTextureSize(64, 64);
         strap2.mirror = true;
         setRotation(strap2, 0F, 0F, 0.7435722F);*/

        bodyLock2 = new ModelRenderer(this, 1, 1);
        bodyLock2.addBox(0F, 0F, 0F, 0, 0, 0);
        bodyLock2.setRotationPoint(0F, 0F, 0F);

        strap2 = new ModelRenderer(this, 36, 4);
        strap2.addBox(2F, -1.4F, 1.7F, 1, 2, 1);
        strap2.setRotationPoint(0F, 0F, 0F);
        strap2.setTextureSize(64, 64);
        strap2.mirror = true;
        setRotation(strap2, 0F, 0F, 0.7435722F);

        bodyLock.addChild(blade);
        bodyLock.addChild(handle);
        bodyLock.addChild(strap1);

        // possibly add to lower body
        bodyLock2.addChild(strap2);

    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        renderWithLock(bipedBody, bodyLock, f5);

        renderWithLock(bipedLowerBody, bodyLock2, f5);
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
