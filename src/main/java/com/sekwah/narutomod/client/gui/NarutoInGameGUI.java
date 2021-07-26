package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
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
        MinecraftForge.EVENT_BUS.addListener(this::clientTickEvent);
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
        PoseStack stack = event.getMatrixStack();
        this.charkaOverlay.render(stack);
    }
}
