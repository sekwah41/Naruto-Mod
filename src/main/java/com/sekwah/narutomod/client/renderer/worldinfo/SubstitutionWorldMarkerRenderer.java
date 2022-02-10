package com.sekwah.narutomod.client.renderer.worldinfo;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.client.gui.SubstitutionGUI;
import com.sekwah.narutomod.client.renderer.NarutoRenderTypes;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;

public class SubstitutionWorldMarkerRenderer {

    public static void render(PoseStack poseStack, Camera camera, Matrix4f matrix, MultiBufferSource multiBufferSource, float partialTicks) {
        int textureWidth = 19;
        int textureHeight = 18;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, SubstitutionGUI.LOG_TEXTURE);
        poseStack.pushPose();
        poseStack.translate(camera.getPosition().x, camera.getPosition().y, camera.getPosition().z);

        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(NarutoRenderTypes.JUTSU_INFO);

        poseStack.popPose();
    }
}
