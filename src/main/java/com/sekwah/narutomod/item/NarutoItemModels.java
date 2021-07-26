package com.sekwah.narutomod.item;

import com.sekwah.narutomod.client.renderer.item.model.AnbuMaskModel;
import com.sekwah.narutomod.client.renderer.item.model.HeadbandModel;
import com.sekwah.narutomod.item.armor.NarutoArmorItem;

public class NarutoItemModels {
    public static void setItemModels() {
        ((NarutoArmorItem) NarutoItems.RED_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(0, true))
                .setArmorTexture("textures/models/armor/red_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.BLUE_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(0, true))
                .setArmorTexture("textures/models/armor/blue_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.GREEN_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(0, false))
                .setArmorTexture("textures/models/armor/green_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.MIST_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(0, false))
                .setArmorTexture("textures/models/armor/mist_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.YELLOW_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(0, false))
                .setArmorTexture("textures/models/armor/yellow_anbu_mask.png");


        ((NarutoArmorItem) NarutoItems.BLUE_HEADBAND.get())
                .setArmorModel(new HeadbandModel(0))
                .setArmorTexture("textures/models/armor/blank_blue_headband.png");
        ((NarutoArmorItem) NarutoItems.BLACK_HEADBAND.get())
                .setArmorModel(new HeadbandModel(0))
                .setArmorTexture("textures/models/armor/blank_black_headband.png");
        ((NarutoArmorItem) NarutoItems.RED_HEADBAND.get())
                .setArmorModel(new HeadbandModel(0))
                .setArmorTexture("textures/models/armor/blank_red_headband.png");
    }
}
