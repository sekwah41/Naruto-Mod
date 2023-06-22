package com.sekwah.narutomod.util;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;


public class GuiUtils {

    public static void centeredTextOutlined(GuiGraphics guiGraphics, Font font, String text, int x, int y, int color, int backgroundColor) {
        int width = font.width(text) / 2;
        guiGraphics.drawString(font, text, (float) x+1 - width, (float) y, backgroundColor, false);
        guiGraphics.drawString(font, text, (float) x-1 - width, y, backgroundColor, false);
        guiGraphics.drawString(font, text, (float) x - width, (float) y+1, backgroundColor, false);
        guiGraphics.drawString(font, text, (float) x - width, (float) y-1, backgroundColor, false);
        guiGraphics.drawString(font, text, (float) x - width, (float) y, color, false);
    }

    public static void centeredText(GuiGraphics guiGraphics, Font font, Component component, int x, int y) {
        centeredText(guiGraphics, font, component, x, y, 16777215);
    }
    public static void centeredText(GuiGraphics guiGraphics, Font font, Component component, int x, int y, int color) {
        int width = font.width(component) / 2;
        guiGraphics.drawString(font, component, x - width, y, color, false);
    }

    public static void centeredText(GuiGraphics guiGraphics, Font font, String text, int x, int y, int color) {
        int width = font.width(text) / 2;
        guiGraphics.drawString(font, text, (float) x - width, (float) y, color, false);
    }
}
