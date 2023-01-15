package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

import java.awt.*;

public class SubstitutionGUI extends GuiComponent implements PlayerGUI {

    public static final ResourceLocation LOG_TEXTURE = new ResourceLocation(NarutoMod.MOD_ID, "textures/gui/jutsu/jutsu_substiutution.png");

    private final Minecraft minecraft;

    private final int intTextColor;
    private final int intTextOutline;

    private float substitutions;

    public SubstitutionGUI(Minecraft mc) {
        this.minecraft = mc;

        Color textColor = new Color(255, 255, 255);
        Color textOutline = new Color(0, 0, 0);
        this.intTextColor = ColorUtil.toMCColor(textColor).getValue();
        this.intTextOutline = ColorUtil.toMCColor(textOutline).getValue();
    }

    public void render(PoseStack matrixStack, Matrix4f worldMatrix, Vec3 cameraPos) {
        int screenWidth = this.minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = this.minecraft.getWindow().getGuiScaledHeight();
        int screenMid = screenWidth / 2;

        int xPos = screenMid + 102;
        int yOffset = 23;
        float brightness = 0.4f;

        int textureWidth = 19;
        int textureHeight = 18;

        float height = this.substitutions % 1;

        if(height == 0) {
            height = 1;
        }

        int pixelHeight = Math.round(textureHeight * height);

        // stack, x, y, tx, ty, width, height, textureWidth, textureHeight
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, LOG_TEXTURE);
        RenderSystem.setShaderColor(brightness, brightness, brightness, 1.0F);
        blit(matrixStack, xPos, screenHeight - yOffset,
                6, 7,
                textureWidth, textureHeight,
                32, 32);
        RenderSystem.setShaderColor(1.0f, 1.0f,1.0f,1.0F);
        blit(matrixStack, xPos, screenHeight - yOffset + textureHeight - pixelHeight,
                6, 7 + textureHeight - pixelHeight,
                textureWidth, pixelHeight,
                32, 32);



        this.centeredTextOutlined(matrixStack,
                String.valueOf((int) Math.floor(substitutions)),
                xPos + 15,
                screenHeight - yOffset + 12,
                intTextColor,
                intTextOutline);

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
            this.substitutions = ninjaData.getSubstitutionCount();
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
