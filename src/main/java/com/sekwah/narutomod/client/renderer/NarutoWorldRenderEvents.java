package com.sekwah.narutomod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NarutoWorldRenderEvents {


    @SubscribeEvent
    public static void renderLevelLast(RenderLevelLastEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if(mc.options.hideGui || mc.level == null || mc.player == null) {
            return;
        }
        Camera camera = mc.getEntityRenderDispatcher().camera;
        PoseStack poseStack = event.getPoseStack();
        Matrix4f matrix = event.getProjectionMatrix();
        float partialTicks = event.getPartialTick();
        MultiBufferSource multiBufferSource = mc.renderBuffers().bufferSource();
    }
}
