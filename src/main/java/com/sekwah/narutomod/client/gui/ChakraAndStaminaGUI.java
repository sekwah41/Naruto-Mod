package com.sekwah.narutomod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class ChakraAndStaminaGUI extends GuiComponent {

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

    public void render(PoseStack matrixStack) {
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

        this.minecraft.getTextureManager().bindForSetup(barTypes[barDesign].texture);
        int barWidth = barTypes[barDesign].width;
        int xOffset = barTypes[barDesign].offset;

        int valuesOffset = 128 + (barWidth / 2);
        int valuesHeight = 26;

        int screenMid = this.screenWidth / 2;



        float darkenFactor = 0.25f;

        Color chakraColor = new Color(255,0,0);
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
        this.blit(matrixStack, screenMid - width - offset, this.screenHeight - 22,
                0 , 22,
                width, 22,
                width, 44);


        // Stamina Bar underlay
        int staminaWidth = (int) (barWidth * currentStaminaPercent);
        this.blit(matrixStack, screenMid + offset, this.screenHeight - 22,
                0, 22,
                width, 22,
                -width, 44);


        // Chakra Bar color
        // TODO pass this data into the render this.setColor(chakraColor);
        this.blit(matrixStack, screenMid - chakraWidth - offset - (width - xOffset - barWidth), this.screenHeight - 22,
                xOffset + (barWidth - chakraWidth), 0,
                chakraWidth, 22,
                width, 44);

        // Stamina Bar color
        // TODO pass this data into the render this.setColor(staminaColor);
        this.blit(matrixStack, screenMid + offset + (100 - barWidth - xOffset), this.screenHeight - 22,
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


        String staminaText = (int) chakra + "/" + (int) maxStamina;
        this.centeredTextOutlined(matrixStack,
                staminaText,
                screenMid + valuesOffset,
                this.screenHeight - valuesHeight,
                intStaminaColor,
                intStaminaColorDarker);
    }

    private void centeredTextOutlined(PoseStack matrixStack, String text, int x, int y, int color, int backgroundColor) {
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

    private Font getFont() {
        return this.minecraft.font;
    }
}
