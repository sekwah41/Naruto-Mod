package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NarutoInGameGUI {

    private final ChakraAndStaminaGUI charkaOverlay;
    private final SubstitutionGUI substitutionOverlay;
    private final Minecraft minecraft;

    /**
     * Will be false if the entity is not a ninja
     */
    private boolean shouldRender;

    public NarutoInGameGUI(){
        this.minecraft = Minecraft.getInstance();
        this.charkaOverlay = new ChakraAndStaminaGUI(this.minecraft);
        this.substitutionOverlay = new SubstitutionGUI(this.minecraft);
        MinecraftForge.EVENT_BUS.addListener(this::renderGameOverlay);
        MinecraftForge.EVENT_BUS.addListener(this::clientTickEvent);
    }

    @SubscribeEvent
    public void clientTickEvent(TickEvent.ClientTickEvent event) {
        if(this.minecraft.getCameraEntity() instanceof Player player) {
            shouldRender = true;
            this.charkaOverlay.tick(player);
            this.substitutionOverlay.tick(player);
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
        this.charkaOverlay.render(stack);
        this.substitutionOverlay.render(stack);
    }
}
