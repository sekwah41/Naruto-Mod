package com.sekwah.narutomod.client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.sekwah.narutomod.client.gui.SubstitutionGUI;
import com.sekwah.narutomod.client.renderer.worldinfo.SubstitutionWorldMarkerRenderer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NarutoWorldRenderEvents {

    public static void registerEvents() {
        MinecraftForge.EVENT_BUS.register(NarutoWorldRenderEvents.class);
    }

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
        SubstitutionWorldMarkerRenderer.render(poseStack, camera, matrix, multiBufferSource, partialTicks);
    }
}
