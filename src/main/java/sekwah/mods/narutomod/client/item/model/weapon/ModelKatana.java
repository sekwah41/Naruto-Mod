package sekwah.mods.narutomod.client.item.model.weapon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Katana - Sekwah
 * Created using Tabula 7.0.1
 */
public class ModelKatana extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;

    public ModelKatana() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape1.addBox(-0.5F, 0.0F, -1.0F, 1, 8, 2, 0.0F);
        this.shape3 = new ModelRenderer(this, 8, -3);
        this.shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape3.addBox(0.0F, -20.0F, -1.0F, 0, 20, 2, 0.0F);
        this.shape2 = new ModelRenderer(this, -3, 10);
        this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2.addBox(-1.5F, 0.0F, -1.5F, 3, 0, 3, 0.0F);
        this.setRotateAngle(shape2, 0.0F, 0.7853981633974483F, 0.0F);
        this.shape1.addChild(this.shape3);
        this.shape1.addChild(this.shape2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape1.render(f5);
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
