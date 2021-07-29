package com.sekwah.narutomod.client.renderer;

import com.sekwah.narutomod.client.renderer.item.model.AnbuMaskModel;
import com.sekwah.narutomod.client.renderer.item.model.HeadbandModel;
import com.sekwah.narutomod.item.NarutoItems;
import com.sekwah.narutomod.item.armor.NarutoArmorItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.packs.resources.ResourceManager;

public class NarutoRenderer extends BlockEntityWithoutLevelRenderer {

    public NarutoRenderer() {
        super(null, null);
    }

    public void onResourceManagerReload(ResourceManager resourceManager) {
        EntityModelSet modelSet = Minecraft.getInstance().getEntityModels();

        ((NarutoArmorItem) NarutoItems.RED_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(NarutoRenderEvents.ANBU_MASK_LAYER), true))
                .setArmorTexture("textures/models/armor/red_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.BLUE_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(NarutoRenderEvents.ANBU_MASK_LAYER), true))
                .setArmorTexture("textures/models/armor/blue_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.GREEN_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(NarutoRenderEvents.ANBU_MASK_LAYER), false))
                .setArmorTexture("textures/models/armor/green_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.MIST_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(NarutoRenderEvents.ANBU_MASK_LAYER), false))
                .setArmorTexture("textures/models/armor/mist_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.YELLOW_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(NarutoRenderEvents.ANBU_MASK_LAYER), false))
                .setArmorTexture("textures/models/armor/yellow_anbu_mask.png");


        ((NarutoArmorItem) NarutoItems.BLUE_HEADBAND.get())
                .setArmorModel(new HeadbandModel(modelSet.bakeLayer(NarutoRenderEvents.HEADBAND_LAYER)))
                .setArmorTexture("textures/models/armor/blank_blue_headband.png");
        ((NarutoArmorItem) NarutoItems.BLACK_HEADBAND.get())
                .setArmorModel(new HeadbandModel(modelSet.bakeLayer(NarutoRenderEvents.HEADBAND_LAYER)))
                .setArmorTexture("textures/models/armor/blank_black_headband.png");
        ((NarutoArmorItem) NarutoItems.RED_HEADBAND.get())
                .setArmorModel(new HeadbandModel(modelSet.bakeLayer(NarutoRenderEvents.HEADBAND_LAYER)))
                .setArmorTexture("textures/models/armor/blank_red_headband.png");

    }
}
