package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sekwah.narutomod.NarutoMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

public class ChakraAndStaminaGUI {

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

    private final Minecraft minecraft;
    private int screenWidth;
    private int screenHeight;

    /*private PlayerEntity getCameraPlayer() {
        return !(this.minecraft.getCameraEntity() instanceof PlayerEntity) ? null : (PlayerEntity)this.minecraft.getCameraEntity();
    }*/

    public ChakraAndStaminaGUI(Minecraft mc) {
        this.minecraft = mc;
    }

    public void render(MatrixStack stack) {
        this.screenWidth = this.minecraft.getWindow().getGuiScaledWidth();
        this.screenHeight = this.minecraft.getWindow().getGuiScaledHeight();

        int barDesign = 0;

        int width = 100;
        int offset = 128;

        this.minecraft.getTextureManager().bind(barDesigns[barDesign]);
        // stack, x, y, tx, ty, width, height, textureWidth, textureHeight
        AbstractGui.blit(stack, this.screenWidth / 2 - width - offset, this.screenHeight - 22,
                0, 22,
                100, 22,
                100, 44);
        AbstractGui.blit(stack, this.screenWidth / 2 - width - offset, this.screenHeight - 22,
                0, 0,
                100, 22,
                100, 44);

        AbstractGui.blit(stack, this.screenWidth / 2 + offset, this.screenHeight - 22,
                0, 22,
                100, 22,
                -100, 44);
        AbstractGui.blit(stack, this.screenWidth / 2 + offset, this.screenHeight - 22,
                0, 0,
                100, 22,
                -100, 44);
    }
}
