package com.sekwah.narutomod.client.renderer;

import com.sekwah.narutomod.client.model.item.model.AnbuMaskModel;
import com.sekwah.narutomod.client.model.item.model.HeadbandModel;
import com.sekwah.narutomod.item.NarutoItems;
import com.sekwah.narutomod.item.armor.NarutoArmorItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;

public class NarutoRenderer extends BlockEntityWithoutLevelRenderer {

    public NarutoRenderer() {
        super(null, null);
    }

    public void onResourceManagerReload(ResourceManager resourceManager) {
        EntityModelSet modelSet = Minecraft.getInstance().getEntityModels();

        ((NarutoArmorItem) NarutoItems.RED_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(NarutoRenderEvents.ANBU_MASK_LAYER)))
                .setArmorTexture("textures/models/armor/red_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.BLUE_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(NarutoRenderEvents.ANBU_MASK_LAYER)))
                .setArmorTexture("textures/models/armor/blue_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.GREEN_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(NarutoRenderEvents.ANBU_MASK_WITHOUT_EARS_LAYER)))
                .setArmorTexture("textures/models/armor/green_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.MIST_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(NarutoRenderEvents.ANBU_MASK_WITHOUT_EARS_LAYER)))
                .setArmorTexture("textures/models/armor/mist_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.YELLOW_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(NarutoRenderEvents.ANBU_MASK_WITHOUT_EARS_LAYER)))
                .setArmorTexture("textures/models/armor/yellow_anbu_mask.png");

        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_BLUE, "textures/models/armor/headband_blue_blank.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_BLACK, "textures/models/armor/headband_black_blank.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_RED, "textures/models/armor/headband_red_blank.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_CUSTARD, "textures/models/armor/headband_custard.png");
    }

    public void setHeadbandRenderer(EntityModelSet modelSet, RegistryObject<Item> item, String texture) {
        ((NarutoArmorItem) item.get())
                .setArmorModel(new HeadbandModel(modelSet.bakeLayer(NarutoRenderEvents.HEADBAND_LAYER)))
                .setArmorTexture(texture);
    }
}
