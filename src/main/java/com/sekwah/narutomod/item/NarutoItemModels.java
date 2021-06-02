package com.sekwah.narutomod.item;

import com.sekwah.narutomod.client.renderer.item.model.AnbuMaskModel;
import com.sekwah.narutomod.item.armor.NarutoArmorItem;

public class NarutoItemModels {
    public static void setItemModels() {
        ((NarutoArmorItem) NarutoItems.ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(0))
                .setArmorTexture("textures/models/armor/red_anbu_mask.png");
    }
}
