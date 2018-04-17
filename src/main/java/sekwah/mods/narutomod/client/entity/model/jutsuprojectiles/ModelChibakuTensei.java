package sekwah.mods.narutomod.client.entity.model.jutsuprojectiles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelChibakuTensei extends ModelBase {

    ModelRenderer blackHole;

    public ModelChibakuTensei() {
        blackHole = new ModelRenderer(this, 0,0);
        int boxSize = 8;
        blackHole.addBox( -boxSize, -boxSize, -boxSize, boxSize, boxSize, boxSize);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    /**
     * @param age in ticks
     */
    public void setPosition(float age) {

    }

}
