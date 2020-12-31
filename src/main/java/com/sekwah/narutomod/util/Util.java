package com.sekwah.narutomod.util;

import net.minecraft.state.properties.AttachFace;

import java.awt.Color;

public class Util {

    /**
     * Basically just because i wanted to enable the intellij color picker >:)
     *
     * @param color
     * @return
     */
    public static net.minecraft.util.text.Color toMCColor(Color color) {
        return net.minecraft.util.text.Color.fromHex(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
    }

    public int colorToInt(Color color) {
        return toMCColor(color).getColor();
    }

}
