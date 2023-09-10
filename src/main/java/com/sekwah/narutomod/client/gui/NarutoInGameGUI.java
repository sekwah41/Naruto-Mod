package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import org.joml.Matrix4f;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
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

        this.overlays = new PlayerGUI[]{this.worldMarkerOverlay, this.substitutionOverlay, this.charkaOverlay};

        MinecraftForge.EVENT_BUS.addListener(this::renderGameOverlay);
        MinecraftForge.EVENT_BUS.addListener(this::clientTickEvent);
        MinecraftForge.EVENT_BUS.addListener(this::renderLevelLast);
    }

    // TODO switch over to new renderer
//    @SubscribeEvent
//    public void registerOverlays(RegisterGuiOverlaysEvent event) {
//        for (var overlay : this.overlays) {
//            event.registerAboveAll("test", overlay);
//        }
//    }


    public static void registerEvents() {
    }

    @SubscribeEvent
    public void clientTickEvent(TickEvent.ClientTickEvent event) {
        if(this.minecraft.getCameraEntity() instanceof Player player) {
            player.getCapability(NinjaCapabilityHandler.NINJA_DATA).ifPresent(ninjaData -> {
                shouldRender = ninjaData.isNinjaModeEnabled();
            });
            for (PlayerGUI  overlay : overlays) {
                overlay.tick(player);
            }
        } else {
            shouldRender = false;
        }
    }

    /**
     * THIS SHOULD NOT BE DONE THIS WAY, TAKE A LOOK AT THE NEW RegisterGuiOverlaysEvent
     *
     * This was just a quick fix for now so I could focus on updating other parts.
     *
     * @deprecated
     * @param event
     */
    @SubscribeEvent
    public void renderGameOverlay(RenderGuiOverlayEvent.Post event) {
        if(event.getOverlay() != VanillaGuiOverlay.HOTBAR.type()) {
            return;
        }
        if(!shouldRender) {
            return;
        }
        if(Minecraft.getInstance().options.hideGui) {
            return;
        }
        GuiGraphics guiGraphics = event.getGuiGraphics();
        for (PlayerGUI  overlay : overlays) {
            overlay.render(guiGraphics, worldMatrix, cameraPos);
        }
    }

    @SubscribeEvent
    public void renderLevelLast(RenderLevelStageEvent event) {
        if(event.getStage() != RenderLevelStageEvent.Stage.AFTER_SOLID_BLOCKS) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        if(mc.options.hideGui || mc.level == null || mc.player == null) {
            return;
        }
        Camera camera = mc.getEntityRenderDispatcher().camera;
        //noinspection ConstantConditions the IDE inspects this as not possible to be null. There has been at least one error report proving this to be false.
        if(camera == null) return;
        PoseStack poseStack = event.getPoseStack();
        worldMatrix = new Matrix4f(event.getProjectionMatrix());
        worldMatrix.mul(poseStack.last().pose());
        cameraPos = camera.getPosition();
        //float partialTicks = event.getPartialTick();
        //MultiBufferSource multiBufferSource = mc.renderBuffers().bufferSource();
    }
}
