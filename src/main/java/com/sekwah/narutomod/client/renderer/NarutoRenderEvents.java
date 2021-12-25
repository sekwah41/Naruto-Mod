package com.sekwah.narutomod.client.renderer;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.client.model.item.model.FireballJutsuModel;
import com.sekwah.narutomod.client.renderer.entity.*;
import com.sekwah.narutomod.client.model.item.model.AnbuMaskModel;
import com.sekwah.narutomod.client.model.item.model.HeadbandModel;
import com.sekwah.narutomod.client.renderer.entity.jutsuprojectile.FireballJutsuRenderer;
import com.sekwah.narutomod.entity.NarutoEntities;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Example of new entity render events
 * https://github.com/MinecraftForge/MinecraftForge/blob/1.17.x/src/test/java/net/minecraftforge/debug/client/rendering/EntityRendererEventsTest.java
 */
@Mod.EventBusSubscriber(value=Dist.CLIENT, modid=NarutoMod.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class NarutoRenderEvents {

    public static final BlockEntityWithoutLevelRenderer NARUTO_RENDERER = new NarutoRenderer();

    public static final ModelLayerLocation ANBU_MASK_LAYER =
            new ModelLayerLocation(new ResourceLocation(NarutoMod.MOD_ID, "anbu_mask"), "main");

    public static final ModelLayerLocation ANBU_MASK_WITHOUT_EARS_LAYER =
            new ModelLayerLocation(new ResourceLocation(NarutoMod.MOD_ID, "anbu_mask_without_ears"), "main");

    public static final ModelLayerLocation HEADBAND_LAYER =
            new ModelLayerLocation(new ResourceLocation(NarutoMod.MOD_ID, "headband"), "main");

    public static final ModelLayerLocation FIREBALL_LAYER =
            new ModelLayerLocation(new ResourceLocation(NarutoMod.MOD_ID, "fireball"), "main");

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(NarutoEntities.KUNAI.get(), KunaiRenderer::new);
        event.registerEntityRenderer(NarutoEntities.EXPLOSIVE_KUNAI.get(), ExplosiveKunaiRenderer::new);
        event.registerEntityRenderer(NarutoEntities.SENBON.get(), SenbonRenderer::new);
        event.registerEntityRenderer(NarutoEntities.SHURIKEN.get(), ShurikenRenderer::new);
        event.registerEntityRenderer(NarutoEntities.PAPER_BOMB.get(), PaperBombRenderer::new);

        event.registerEntityRenderer(NarutoEntities.FIREBALL_JUTSU.get(), FireballJutsuRenderer::new);

    }

    public static void registerRenderTypes() {
        ItemBlockRenderTypes.setRenderLayer(NarutoBlocks.PAPER_BOMB.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void reloadListener(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new NarutoRenderer());
    }

    @SubscribeEvent
    public static void layerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(ANBU_MASK_LAYER, () -> AnbuMaskModel.createLayer(true));
        event.registerLayerDefinition(ANBU_MASK_WITHOUT_EARS_LAYER, () -> AnbuMaskModel.createLayer(false));
        event.registerLayerDefinition(HEADBAND_LAYER, HeadbandModel::createLayer);
        event.registerLayerDefinition(FIREBALL_LAYER, FireballJutsuModel::createLayer);
    }

/*    @SubscribeEvent
    public static void entityLayers(EntityRenderersEvent.AddLayers event)
    {
        LivingEntityRenderer<Player, ? extends EntityModel<Player>> renderer = event.getRenderer(EntityType.PLAYER);
    }*/


}
