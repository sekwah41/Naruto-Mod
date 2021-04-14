package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

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
        UNNAMED(UNNAMEDTEXTURE, 74, 23),
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

    private float chakra;
    private float stamina;

    private float barDesignLoop;

    /*private PlayerEntity getCameraPlayer() {
        return !(this.minecraft.getCameraEntity() instanceof PlayerEntity) ? null : (PlayerEntity)this.minecraft.getCameraEntity();
    }*/

    public ChakraAndStaminaGUI(Minecraft mc) {
        this.minecraft = mc;
    }

    public void render(MatrixStack matrixStack) {
        this.screenWidth = this.minecraft.getWindow().getGuiScaledWidth();
        this.screenHeight = this.minecraft.getWindow().getGuiScaledHeight();
        int barDesign = (int) barDesignLoop % barTypes.length;

        float maxOfEach = 100;
        float maxChakra = 100;
        float maxStamina = 100;

        chakra %= maxOfEach;
        stamina %= maxOfEach;

        float currentChakraPercent = (chakra ) / maxOfEach;
        float currentStaminaPercent = (stamina) / maxOfEach;

        int width = 100;
        int offset = 128;

        this.minecraft.getTextureManager().bind(barTypes[barDesign].texture);
        int barWidth = barTypes[barDesign].width;
        int xOffset = barTypes[barDesign].offset;

        int valuesOffset = 128 + (barWidth / 2);
        int valuesHeight = 26;
        // stack, x, y, tx, ty, width, height, textureWidth, textureHeight

        int screenMid = this.screenWidth / 2;



        Color chakraColor = new Color(255,0,0);
        Color staminaColor = new Color(0,255,0);

        // Charka Bar underlay
        int chakraWidth = (int) (barWidth * currentChakraPercent);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        AbstractGui.blit(matrixStack, screenMid - width - offset, this.screenHeight - 22,
                0 , 22,
                width, 22,
                width, 44);


        // Stamina Bar underlay
        int staminaWidth = (int) (barWidth * currentStaminaPercent);
        AbstractGui.blit(matrixStack, screenMid + offset, this.screenHeight - 22,
                0, 22,
                width, 22,
                -width, 44);


        // Chakra Bar color
        this.setColor(chakraColor);
        AbstractGui.blit(matrixStack, screenMid - chakraWidth - offset - (width - xOffset - barWidth), this.screenHeight - 22,
                xOffset + (barWidth - chakraWidth), 0,
                chakraWidth, 22,
                width, 44);

        // Stamina Bar color
        this.setColor(staminaColor);
        AbstractGui.blit(matrixStack, screenMid + offset + (100 - barWidth - xOffset), this.screenHeight - 22,
                -barWidth - xOffset, 0,
                staminaWidth, 22,
                -width, 44);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        float darkenFactor = 0.25f;

        String chakraText = (int) chakra + "/" + (int) maxChakra;
        this.centeredTextOutlined(matrixStack,
                chakraText,
                screenMid - valuesOffset,
                this.screenHeight - valuesHeight,
                ColorUtil.toMCColor(chakraColor).getValue(),
                ColorUtil.toMCColor(new Color((int) (chakraColor.getRed() * darkenFactor),
                        (int) (chakraColor.getGreen() * darkenFactor),
                        (int) (chakraColor.getBlue() * darkenFactor))).getValue());


        String staminaText = (int) chakra + "/" + (int) maxStamina;
        this.centeredTextOutlined(matrixStack,
                staminaText,
                screenMid + valuesOffset,
                this.screenHeight - valuesHeight,
                ColorUtil.toMCColor(staminaColor).getValue(),
                ColorUtil.toMCColor(new Color((int) (staminaColor.getRed() * darkenFactor),
                        (int) (staminaColor.getGreen() * darkenFactor),
                        (int) (staminaColor.getBlue() * darkenFactor))).getValue());
    }

    private void centeredTextOutlined(MatrixStack matrixStack, String text, int x, int y, int color, int backgroundColor) {
        int width = this.getFont().width(text) / 2;
        this.getFont().draw(matrixStack, text, x+1 - width, y, backgroundColor);
        this.getFont().draw(matrixStack, text, x-1 - width, y, backgroundColor);
        this.getFont().draw(matrixStack, text, x - width, y+1, backgroundColor);
        this.getFont().draw(matrixStack, text, x - width, y-1, backgroundColor);
        this.getFont().draw(matrixStack, text, x - width, y, color);
    }

    public void tick() {
        chakra++;
        stamina++;

        barDesignLoop += (0.01 * barTypes.length);
    }

    private FontRenderer getFont() {
        return this.minecraft.font;
    }
    private void setColor(Color color) {
        RenderSystem.color4f(color.getRed() / 255f,
                color.getGreen() / 255f,
                color.getBlue() / 255f,
                1.0F);
    }
}
