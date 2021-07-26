package com.sekwah.narutomod.client.renderer.item.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelRendererScaled extends ModelPart {
    private final float scale;

    public ModelRendererScaled(Model p_i46358_1_, int p_i46358_2_, int p_i46358_3_, float scale) {
        super(p_i46358_1_, p_i46358_2_, p_i46358_3_);
        this.scale = scale;
    }

    public void translateAndRotate(PoseStack matrixStack) {
        super.translateAndRotate(matrixStack);
        matrixStack.scale(this.scale, this.scale, this.scale);
    }
}
