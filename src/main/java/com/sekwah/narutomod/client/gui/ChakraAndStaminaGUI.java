package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
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

    public enum BarInfo {

        DEFAULT(DEFAULTTEXTURE, 86, 8),
        KATANA(KATANATEXTURE, 94, 1),
        KUBI(KUBITEXTURE, 86, 11),
        KUNAI(KUNAITEXTURE, 86, 9),
        LAMP(LAMPSHADETEXTURE, 86, 8),
        SCEPTER(SCEPTERTEXTURE, 86, 9),
        SCROLL(SCROLLTEXTURE, 86, 8),
        UNNAMED(UNNAMEDTEXTURE, 97, 3),
        ;

        private final ResourceLocation texture;
        private final int width;
        private final int offset;

        BarInfo(ResourceLocation texture, int width, int offset) {

            this.texture = texture;
            this.width = width;
            this.offset = offset;
        }
    }

    public static final BarInfo[] barTypes = BarInfo.values();

    private final Minecraft minecraft;
    private int screenWidth;
    private int screenHeight;

    private int chakra;
    private int stamina;

    private float barDesignLoop;

    /*private PlayerEntity getCameraPlayer() {
        return !(this.minecraft.getCameraEntity() instanceof PlayerEntity) ? null : (PlayerEntity)this.minecraft.getCameraEntity();
    }*/

    public ChakraAndStaminaGUI(Minecraft mc) {
        this.minecraft = mc;
    }

    public void render(MatrixStack stack) {
        this.screenWidth = this.minecraft.getWindow().getGuiScaledWidth();
        this.screenHeight = this.minecraft.getWindow().getGuiScaledHeight();
        int barDesign = (int) barDesignLoop % barTypes.length;

        float maxOfEach = 100;

        float currentChakraPercent = (chakra % maxOfEach) / maxOfEach;
        float currentStaminaPercent = (chakra % maxOfEach) / maxOfEach;

        int width = 100;
        int offset = 128;

        this.minecraft.getTextureManager().bind(barTypes[barDesign].texture);
        int barWidth = barTypes[barDesign].width;
        int xOffset = barTypes[barDesign].offset;
        // stack, x, y, tx, ty, width, height, textureWidth, textureHeight

        // Charka Bar
        int chakraWidth = (int) (barWidth * currentChakraPercent);
        AbstractGui.blit(stack, this.screenWidth / 2 - width - offset, this.screenHeight - 22,
                0 , 22,
                width, 22,
                width, 44);

        RenderSystem.color4f(1.0F, 0.0F, 0.0F, 1.0F);
        AbstractGui.blit(stack, this.screenWidth / 2 - chakraWidth - offset - (width - xOffset - barWidth), this.screenHeight - 22,
                xOffset + (barWidth - chakraWidth), 0,
                chakraWidth, 22,
                width, 44);

        // Stamina Bar
        int staminaWidth = (int) (barWidth * currentChakraPercent);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        AbstractGui.blit(stack, this.screenWidth / 2 + offset, this.screenHeight - 22,
                0, 22,
                width, 22,
                -width, 44);

        RenderSystem.color4f(0.0F, 1.0F, 0.0F, 1.0F);
        AbstractGui.blit(stack, this.screenWidth / 2 + offset + (100 - barWidth - xOffset), this.screenHeight - 22,
                -barWidth - xOffset, 0,
                staminaWidth, 22,
                -width, 44);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void tick() {
        chakra++;
        stamina++;

        barDesignLoop += (0.01 * barTypes.length);
    }
}
