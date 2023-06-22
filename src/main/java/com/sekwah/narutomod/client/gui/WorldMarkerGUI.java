package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sekwah.narutomod.abilities.jutsus.SubstitutionJutsuAbility;
import com.sekwah.narutomod.util.GuiUtils;
import net.minecraft.client.gui.GuiGraphics;
import org.joml.Matrix4f;
import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector4f;

import java.awt.*;

/**
 * For now this will just render the subsitution locations but modify it to show more in the future.
 */
public class WorldMarkerGUI implements PlayerGUI {

    public static final ResourceLocation LOG_TEXTURE = new ResourceLocation(NarutoMod.MOD_ID, "textures/gui/jutsu/jutsu_substiutution_marker.png");

    private final Minecraft minecraft;
    private int screenWidth;
    private int screenHeight;
    private final int intTextColor;
    private final int outOfRangeColor;
    private final int intTextOutline;
    private Vec3 substitutionLoc;

    public WorldMarkerGUI(Minecraft mc) {
        this.minecraft = mc;

        Color textColor = new Color(255, 255, 255);
        Color textOutline = new Color(0, 0, 0);
        Color outOfRangeColor = new Color(255, 99, 99);
        this.intTextColor = ColorUtil.toMCColor(textColor).getValue();
        this.outOfRangeColor = ColorUtil.toMCColor(outOfRangeColor).getValue();
        this.intTextOutline = ColorUtil.toMCColor(textOutline).getValue();
    }

    public void render(GuiGraphics guiGraphics, Matrix4f worldMatrix, Vec3 cameraPos) {
        if(substitutionLoc == null) {
            return;
        }
        this.screenWidth = this.minecraft.getWindow().getGuiScaledWidth();
        this.screenHeight = this.minecraft.getWindow().getGuiScaledHeight();
        int halfWidth = this.screenWidth / 2;
        int halfHeight = this.screenHeight / 2;

        float xPos = halfWidth;
        float yOffset = halfHeight;

        int textureWidth = 32;
        int textureHeight = 32;

        // stack, x, y, tx, ty, width, height, textureWidth, textureHeight
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, LOG_TEXTURE);

        Vector4f vec = new Vector4f((float) (substitutionLoc.x - cameraPos.x), (float) (substitutionLoc.y - cameraPos.y), (float) (substitutionLoc.z - cameraPos.z), 1F);
        double distance = cameraPos.distanceTo(substitutionLoc);

        // Was vec.transform
        vec.mul(worldMatrix);

        // Perspective divide
        vec.div(vec.w);

        xPos += vec.x() * halfWidth;
        yOffset -= (vec.y() * halfHeight);


        if(vec.z() > 0 && vec.z() < 1) {
            float fadeOut = (float) Math.max(Math.min(0.6f, ((distance) / 8) - 0.5), 0);
            RenderSystem.setShaderColor(1, 1, 1, fadeOut);
            float scale = (float) (0.5 / Math.pow((distance + 30) / 80, 2));
            var poseStack = guiGraphics.pose();
            poseStack.pushPose();
            poseStack.translate(xPos, yOffset, 0);
            poseStack.scale(scale, scale, scale);
            guiGraphics.blit(LOG_TEXTURE, -textureWidth/2, -textureHeight/2,
                    0, 0,
                    32, 32,
                    32, 32);
            long roundedDistance = Math.round(distance);
            RenderSystem.setShaderColor(1, 1, 1, Math.min(fadeOut * 1.5f, 1));
            GuiUtils.centeredTextOutlined(guiGraphics, this.getFont(), roundedDistance + " M", 0, 11, roundedDistance <= SubstitutionJutsuAbility.MAX_MARKER_DISTANCE ? intTextColor : outOfRangeColor, intTextOutline);
            poseStack.popPose();
        }

    }

    public void tick(Player player) {
        player.getCapability(NinjaCapabilityHandler.NINJA_DATA).ifPresent(ninjaData -> {
            if(player.level().dimension().location().equals(ninjaData.getSubstitutionDimension())) {
                this.substitutionLoc = ninjaData.getSubstitutionLoc();
            } else {
                this.substitutionLoc = null;
            }
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
