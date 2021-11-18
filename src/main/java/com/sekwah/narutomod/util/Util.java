package com.sekwah.narutomod.util;

import net.minecraft.network.chat.TextColor;

import java.awt.*;

public class Util {

    /**
     * Basically just because i wanted to enable the intellij color picker >:)
     *
     * @param color
     * @return
     */
    public static TextColor toMCColor(Color color) {
        return TextColor.parseColor(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
    }

    public int colorToInt(Color color) {
        return toMCColor(color).getValue();
    }

}
