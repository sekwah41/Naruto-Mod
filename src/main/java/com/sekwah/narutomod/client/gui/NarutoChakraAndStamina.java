package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sekwah.narutomod.NarutoMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class NarutoChakraAndStamina {

    private static final ResourceLocation DEFAULTTEXTURE = new ResourceLocation(NarutoMod.MOD_ID, "textures/gui/chakrabars/defaultbar.png");
    private static final ResourceLocation KATANATEXTURE = new ResourceLocation(NarutoMod.MOD_ID, "textures/gui/chakrabars/katana.png");
    private static final ResourceLocation KUBITEXTURE = new ResourceLocation(NarutoMod.MOD_ID, "textures/gui/chakrabars/kubi.png");
    private static final ResourceLocation KUNAITEXTURE = new ResourceLocation(NarutoMod.MOD_ID, "textures/gui/chakrabars/kunai.png");
    private static final ResourceLocation LAMPSHADETEXTURE = new ResourceLocation(NarutoMod.MOD_ID, "textures/gui/chakrabars/lampshade.png");
    private static final ResourceLocation SCEPTERTEXTURE = new ResourceLocation(NarutoMod.MOD_ID, "textures/gui/chakrabars/scepter.png");
    private static final ResourceLocation SCROLLTEXTURE = new ResourceLocation(NarutoMod.MOD_ID, "textures/gui/chakrabars/scroll.png");
    private static final ResourceLocation UNNAMEDTEXTURE = new ResourceLocation(NarutoMod.MOD_ID, "textures/gui/chakrabars/unnamedbar.png");

    public static final ResourceLocation[] barDesigns = {DEFAULTTEXTURE, KATANATEXTURE, KUBITEXTURE, KUNAITEXTURE,
            LAMPSHADETEXTURE, SCEPTERTEXTURE, SCROLLTEXTURE, UNNAMEDTEXTURE};

    private static final int[] barWidths = {86,94,86,86,86,86,86,74};

    private static final int[] barXOffset = {6,5,3,9,8,5,6,3};

    public NarutoChakraAndStamina(){
        MinecraftForge.EVENT_BUS.addListener(this::renderGameOverlay);
    }

    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent.Pre event) {
        /*TextureManager textureManager = Minecraft.getInstance().getTextureManager();
        MatrixStack stack = event.getMatrixStack();
        stack.pushPose();
        RenderSystem.enableBlend();
        RenderSystem.enableAlphaTest();
        //textureManager.bind(DEFAULTTEXTURE);
        Screen.blit(stack, 0, 0, 0, 0, barWidths[0], 22, event.getWindow().getGuiScaledWidth(), event.getWindow().getGuiScaledHeight());
        RenderSystem.disableBlend();
        RenderSystem.disableAlphaTest();
        stack.popPose();*/
    }
}
