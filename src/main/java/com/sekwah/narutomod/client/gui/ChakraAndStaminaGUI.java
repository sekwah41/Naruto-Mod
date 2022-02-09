package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.client.renderer.entity.config.NarutoConfig;
import com.sekwah.narutomod.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Player;

import java.awt.*;

public class ChakraAndStaminaGUI extends GuiComponent {


    public static final BarDesigns.BarInfo[] barTypes = BarDesigns.BarInfo.values();

    private final Minecraft minecraft;
    private int screenWidth;
    private int screenHeight;

    private float chakra;
    private float stamina;

    private float maxChakra;
    private float maxStamina;

    public ChakraAndStaminaGUI(Minecraft mc) {
        this.minecraft = mc;
    }

    public void render(PoseStack matrixStack) {
        this.screenWidth = this.minecraft.getWindow().getGuiScaledWidth();
        this.screenHeight = this.minecraft.getWindow().getGuiScaledHeight();
        int barDesign = NarutoConfig.chakraBarDesign;

        float currentChakraPercent = maxChakra > 0 ? (chakra) / maxChakra : 0;
        float currentStaminaPercent = maxStamina > 0 ? (stamina) / maxStamina : 0;

        int width = 100;
        int offset = 128;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, barTypes[barDesign].texture);
        int barWidth = barTypes[barDesign].width;
        int xOffset = barTypes[barDesign].offset;

        int valuesOffset = 128 + (barWidth / 2);
        int valuesHeight = 26;

        int screenMid = this.screenWidth / 2;

        float darkenFactor = 0.25f;

        Color chakraColor = new Color(20,179,255);
        Color staminaColor = new Color(0,255,0);
        int intStaminaColor = ColorUtil.toMCColor(staminaColor).getValue();
        int intChakraColor = ColorUtil.toMCColor(chakraColor).getValue();

        int intStaminaColorDarker = ColorUtil.toMCColor(new Color((int) (staminaColor.getRed() * darkenFactor),
                (int) (staminaColor.getGreen() * darkenFactor),
                (int) (staminaColor.getBlue() * darkenFactor))).getValue();
        int intChakraColorDarker = ColorUtil.toMCColor(new Color((int) (chakraColor.getRed() * darkenFactor),
                (int) (chakraColor.getGreen() * darkenFactor),
                (int) (chakraColor.getBlue() * darkenFactor))).getValue();


        // Charka Bar underlay
        int chakraWidth = (int) (barWidth * currentChakraPercent);
        // stack, x, y, tx, ty, width, height, textureWidth, textureHeight
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        blit(matrixStack, screenMid - width - offset, this.screenHeight - 22,
                0 , 22,
                width, 22,
                width, 44);


        // Stamina Bar underlay
        int staminaWidth = (int) (barWidth * currentStaminaPercent);
        blit(matrixStack, screenMid + offset, this.screenHeight - 22,
                0, 22,
                width, 22,
                -width, 44);


        // Chakra Bar color
        this.setColor(chakraColor);
        blit(matrixStack, screenMid - chakraWidth - offset - (width - xOffset - barWidth), this.screenHeight - 22,
                xOffset + (barWidth - chakraWidth), 0,
                chakraWidth, 22,
                width, 44);

        // Stamina Bar color
        this.setColor(staminaColor);
        blit(matrixStack, screenMid + offset + (100 - barWidth - xOffset), this.screenHeight - 22,
                -barWidth - xOffset, 0,
                staminaWidth, 22,
                -width, 44);

        String chakraText = (int) chakra + "/" + (int) maxChakra;
        this.centeredTextOutlined(matrixStack,
                chakraText,
                screenMid - valuesOffset,
                this.screenHeight - valuesHeight,
                intChakraColor,
                intChakraColorDarker);


        String staminaText = (int) stamina + "/" + (int) maxStamina;
        this.centeredTextOutlined(matrixStack,
                staminaText,
                screenMid + valuesOffset,
                this.screenHeight - valuesHeight,
                intStaminaColor,
                intStaminaColorDarker);
    }

    private void centeredTextOutlined(PoseStack matrixStack, String text, int x, int y, int color, int backgroundColor) {
        int width = this.getFont().width(text) / 2;
        this.getFont().draw(matrixStack, text, (float) x+1 - width, y, backgroundColor);
        this.getFont().draw(matrixStack, text, (float) x-1 - width, y, backgroundColor);
        this.getFont().draw(matrixStack, text, (float) x - width, (float) y+1, backgroundColor);
        this.getFont().draw(matrixStack, text, (float) x - width, (float) y-1, backgroundColor);
        this.getFont().draw(matrixStack, text, (float) x - width, y, color);
    }

    public void tick(Player player) {
        player.getCapability(NinjaCapabilityHandler.NINJA_DATA).ifPresent(ninjaData -> {
            this.chakra = ninjaData.getChakra();
            this.stamina = ninjaData.getStamina();
            this.maxChakra = ninjaData.getMaxChakra();
            this.maxStamina = ninjaData.getMaxStamina();
        });
    }

    private void setColor(Color color) {
        RenderSystem.setShaderColor(color.getRed() / 255f,
                color.getGreen() / 255f,
                color.getBlue() / 255f,
                1.0F);
    }

    private Font getFont() {
        return this.minecraft.font;
    }
}
