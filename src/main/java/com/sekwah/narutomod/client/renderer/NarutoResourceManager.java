package com.sekwah.narutomod.client.renderer;

import com.sekwah.narutomod.client.model.item.model.AnbuMaskModel;
import com.sekwah.narutomod.client.model.item.model.FlakJacketModel;
import com.sekwah.narutomod.client.model.item.model.FlakJacketNewModel;
import com.sekwah.narutomod.client.model.item.model.HeadbandModel;
import com.sekwah.narutomod.item.NarutoItems;
import com.sekwah.narutomod.item.armor.NarutoArmorItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class NarutoResourceManager extends BlockEntityWithoutLevelRenderer {

    public NarutoResourceManager() {
        super(null, null);
    }

    public void onResourceManagerReload(ResourceManager resourceManager) {
        EntityModelSet modelSet = Minecraft.getInstance().getEntityModels();

        ((NarutoArmorItem) NarutoItems.RED_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(AnbuMaskModel.LAYER_LOCATION)))
                .setArmorTexture("textures/models/armor/anbu_mask/red_anbu_mask.png");

        ((NarutoArmorItem) NarutoItems.BLUE_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(AnbuMaskModel.LAYER_LOCATION)))
                .setArmorTexture("textures/models/armor/anbu_mask/blue_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.GREEN_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(AnbuMaskModel.LAYER_LOCATION_WITHOUT_EARS)))
                .setArmorTexture("textures/models/armor/anbu_mask/green_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.MIST_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(AnbuMaskModel.LAYER_LOCATION_WITHOUT_EARS)))
                .setArmorTexture("textures/models/armor/anbu_mask/mist_anbu_mask.png");
        ((NarutoArmorItem) NarutoItems.YELLOW_ANBU_MASK.get())
                .setArmorModel(new AnbuMaskModel(modelSet.bakeLayer(AnbuMaskModel.LAYER_LOCATION_WITHOUT_EARS)))
                .setArmorTexture("textures/models/armor/anbu_mask/yellow_anbu_mask.png");

        ((NarutoArmorItem) NarutoItems.FLAK_JACKET_NEW.get())
                .setArmorModel(new FlakJacketNewModel(modelSet.bakeLayer(FlakJacketNewModel.LAYER_LOCATION)))
                .setArmorTexture("textures/models/armor/flak_jacket_new.png");

        ((NarutoArmorItem) NarutoItems.FLAK_JACKET.get())
                .setArmorModel(new FlakJacketModel(modelSet.bakeLayer(FlakJacketModel.LAYER_LOCATION)))
                .setArmorTexture("textures/models/armor/flak_jacket.png");

        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_BLUE, "textures/models/armor/headband/headband_blue_blank.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_BLACK, "textures/models/armor/headband/headband_black_blank.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_RED, "textures/models/armor/headband/headband_red_blank.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_CUSTARD, "textures/models/armor/headband/headband_custard.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_LEAF, "textures/models/armor/headband/headband_leafvillage.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_LEAF_SCRATCHED, "textures/models/armor/headband/headband_leafvillage_scratched.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_LEAF_BLACK, "textures/models/armor/headband/headband_leafvillage_black.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_LEAF_BLACK_SCRATCHED, "textures/models/armor/headband/headband_leafvillage_black_scratched.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_ROCK, "textures/models/armor/headband/headband_rock.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_ROCK_SCRATCHED, "textures/models/armor/headband/headband_rock_scratched.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_SAND, "textures/models/armor/headband/headband_sandblack.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_SAND_SCRATCHED, "textures/models/armor/headband/headband_sandblack_scratched.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_SOUND, "textures/models/armor/headband/headband_soundvillage.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_SOUND_SCRATCHED, "textures/models/armor/headband/headband_soundvillage_scratched.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_MIST, "textures/models/armor/headband/headband_mistblack.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_MIST_SCRATCHED, "textures/models/armor/headband/headband_mistblack_scratched.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_WATERFALL, "textures/models/armor/headband/headband_waterfallblack.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_WATERFALL_SCRATCHED, "textures/models/armor/headband/headband_waterfallblack_scratched.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_CLOUD, "textures/models/armor/headband/headband_cloudblack.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_CLOUD_SCRATCHED, "textures/models/armor/headband/headband_cloudblack_scratched.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_RAIN, "textures/models/armor/headband/headband_rainblack.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_RAIN_SCRATCHED, "textures/models/armor/headband/headband_rainblack_scratched.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_GRASS, "textures/models/armor/headband/headband_grassblack.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_GRASS_SCRATCHED, "textures/models/armor/headband/headband_grassblack_scratched.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_PRIDE, "textures/models/armor/headband/headband_pride.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_YOUTUBE, "textures/models/armor/headband/headband_youtube.png");
        setHeadbandRenderer(modelSet, NarutoItems.HEADBAND_LAVA, "textures/models/armor/headband/headband_lavavillage.png");
    }

    private void setHeadbandRenderer(EntityModelSet modelSet, RegistryObject<Item> item, String texture) {
        ((NarutoArmorItem) item.get())
                .setArmorModel(new HeadbandModel(modelSet.bakeLayer(HeadbandModel.LAYER_LOCATION)))
                .setArmorTexture(texture);
    }
}
