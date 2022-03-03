package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.client.renderer.NarutoWorldRenderEvents;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NarutoInGameGUI {

    private final ChakraAndStaminaGUI charkaOverlay;
    private final SubstitutionGUI substitutionOverlay;
    private final WorldMarkerGUI worldMarkerOverlay;
    private final Minecraft minecraft;

    private final PlayerGUI[] overlays;

    /**
     * Will be false if the entity is not a ninja
     */
    private boolean shouldRender;

    private static Matrix4f worldMatrix;
    private static Vec3 cameraPos;

    public NarutoInGameGUI(){
        this.minecraft = Minecraft.getInstance();
        this.charkaOverlay = new ChakraAndStaminaGUI(this.minecraft);
        this.substitutionOverlay = new SubstitutionGUI(this.minecraft);
        this.worldMarkerOverlay = new WorldMarkerGUI(this.minecraft);

        this.overlays = new PlayerGUI[]{this.charkaOverlay, this.substitutionOverlay, this.worldMarkerOverlay};

        MinecraftForge.EVENT_BUS.addListener(this::renderGameOverlay);
        MinecraftForge.EVENT_BUS.addListener(this::clientTickEvent);
        MinecraftForge.EVENT_BUS.addListener(this::renderLevelLast);
    }


    public static void registerEvents() {
    }

    @SubscribeEvent
    public void clientTickEvent(TickEvent.ClientTickEvent event) {
        if(this.minecraft.getCameraEntity() instanceof Player player) {
            shouldRender = true;
            for (PlayerGUI  overlay : overlays) {
                overlay.tick(player);
            }
        } else {
            shouldRender = false;
        }
    }

    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent event) {
        if(event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        if(!shouldRender) {
            return;
        }
        PoseStack stack = event.getMatrixStack();
        for (PlayerGUI  overlay : overlays) {
            overlay.render(stack, worldMatrix, cameraPos);
        }
    }

    @SubscribeEvent
    public void renderLevelLast(RenderLevelLastEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if(mc.options.hideGui || mc.level == null || mc.player == null) {
            return;
        }
        Camera camera = mc.getEntityRenderDispatcher().camera;
        PoseStack poseStack = event.getPoseStack();
        worldMatrix = event.getProjectionMatrix().copy();
        worldMatrix.multiply(poseStack.last().pose());
        cameraPos = camera.getPosition();
        //float partialTicks = event.getPartialTick();
        //MultiBufferSource multiBufferSource = mc.renderBuffers().bufferSource();
    }
}
