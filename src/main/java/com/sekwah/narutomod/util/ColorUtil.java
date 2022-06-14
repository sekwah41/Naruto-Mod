package com.sekwah.narutomod.util;

import net.minecraft.network.chat.TextColor;

import java.awt.*;

public class ColorUtil {

    public static Color recalculateHue(float hue, int brightness) {
        // check
        // http://stackoverflow.com/questions/25713206/calculate-hue-rotation-from-color-a-to-color-b
        double radiansHue = Math.toRadians(hue);
        int red = (int) (Math.sqrt(Math.cos(radiansHue)+1/2) * 255);
        int green = (int) (Math.sqrt(Math.cos(radiansHue-(Math.PI+1)/2)+1/2) * 255);
        int blue = (int) (Math.sqrt(Math.cos(radiansHue+(Math.PI+1)/2)+1/2) * 255);
        if(brightness > 50){
            float multiValue = (float) (brightness - 50) / 50f;
            red = (int) ((255 - red) * multiValue + red);
            green = (int) ((255 - green) * multiValue + green);
            blue = (int) ((255 - blue) * multiValue + blue);
        }
        else if(brightness < 50){
            float multiValue = (float) brightness / 50f;
            red *= multiValue;
            green *= multiValue;
            blue *= multiValue;
        }

        return new Color(red, green, blue);

    }

    /**
     * Basically just because i wanted to enable the intellij color picker >:)
     *
     * @param color
     * @return
     */
    public static TextColor toMCColor(Color color) {
        return TextColor.parseColor(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
    }

}
