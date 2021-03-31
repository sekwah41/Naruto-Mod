package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NarutoInGameGUI {

    private final ChakraAndStaminaGUI charkaOverlay;

    public NarutoInGameGUI(){
        Minecraft mc = Minecraft.getInstance();
        this.charkaOverlay = new ChakraAndStaminaGUI(mc);
        MinecraftForge.EVENT_BUS.addListener(this::renderGameOverlay);
    }

    @SubscribeEvent
    public void clientTickEvent(TickEvent.ClientTickEvent event) {
        this.charkaOverlay.tick();
    }

    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent event) {
        if(event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        MatrixStack stack = event.getMatrixStack();
        this.charkaOverlay.render(stack);
        /*stack.pushPose();
        RenderSystem.enableBlend();
        RenderSystem.enableAlphaTest();
        textureManager.bind(DEFAULTTEXTURE);
        Screen.blit(stack, 0, 0, 0, 0, barWidths[0], 22, event.getWindow().getGuiScaledWidth(), event.getWindow().getGuiScaledHeight());
        //RenderSystem.disableBlend();
        RenderSystem.disableAlphaTest();
        stack.popPose();*/
    }
}
