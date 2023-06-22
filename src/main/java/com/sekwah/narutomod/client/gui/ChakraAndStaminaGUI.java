package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sekwah.narutomod.util.GuiUtils;
import net.minecraft.client.gui.GuiGraphics;
import org.joml.Matrix4f;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.config.NarutoConfig;
import com.sekwah.narutomod.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

public class ChakraAndStaminaGUI implements PlayerGUI {


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

    public void render(GuiGraphics guiGraphics, Matrix4f worldMatrix, Vec3 cameraPos) {
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
        guiGraphics.blit(barTypes[barDesign].texture, screenMid - width - offset, this.screenHeight - 22,
                0 , 22,
                width, 22,
                width, 44);


        // Stamina Bar underlay
        int staminaWidth = (int) (barWidth * currentStaminaPercent);
        guiGraphics.blit(barTypes[barDesign].texture, screenMid + offset, this.screenHeight - 22,
                0, 22,
                width, 22,
                -width, 44);


        // Chakra Bar color
        this.setColor(chakraColor);
        guiGraphics.blit(barTypes[barDesign].texture, screenMid - chakraWidth - offset - (width - xOffset - barWidth), this.screenHeight - 22,
                xOffset + (barWidth - chakraWidth), 0,
                chakraWidth, 22,
                width, 44);

        // Stamina Bar color
        this.setColor(staminaColor);
        guiGraphics.blit(barTypes[barDesign].texture, screenMid + offset + (100 - barWidth - xOffset), this.screenHeight - 22,
                -barWidth - xOffset, 0,
                staminaWidth, 22,
                -width, 44);

        this.setColor(Color.WHITE);

        String chakraText = (int) chakra + "/" + (int) maxChakra;
        GuiUtils.centeredTextOutlined(guiGraphics, this.getFont(),
                chakraText,
                screenMid - valuesOffset,
                this.screenHeight - valuesHeight,
                intChakraColor,
                intChakraColorDarker);


        String staminaText = (int) stamina + "/" + (int) maxStamina;
        GuiUtils.centeredTextOutlined(guiGraphics, this.getFont(),
                staminaText,
                screenMid + valuesOffset,
                this.screenHeight - valuesHeight,
                intStaminaColor,
                intStaminaColorDarker);
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
