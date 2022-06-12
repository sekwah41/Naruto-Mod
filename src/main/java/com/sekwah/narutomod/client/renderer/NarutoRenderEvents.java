package com.sekwah.narutomod.client.renderer;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.client.model.entity.SubstitutionLogModel;
import com.sekwah.narutomod.client.model.item.model.AnbuMaskModel;
import com.sekwah.narutomod.client.model.item.model.HeadbandModel;
import com.sekwah.narutomod.client.model.jutsu.FireballJutsuModel;
import com.sekwah.narutomod.client.model.jutsu.WaterBulletModel;
import com.sekwah.narutomod.client.renderer.entity.*;
import com.sekwah.narutomod.client.renderer.entity.jutsuprojectile.FireballJutsuRenderer;
import com.sekwah.narutomod.client.renderer.entity.jutsuprojectile.WaterBulletJutsuRenderer;
import com.sekwah.narutomod.entity.NarutoEntities;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(NarutoEntities.KUNAI.get(), KunaiRenderer::new);
        event.registerEntityRenderer(NarutoEntities.EXPLOSIVE_KUNAI.get(), ExplosiveKunaiRenderer::new);
        event.registerEntityRenderer(NarutoEntities.SENBON.get(), SenbonRenderer::new);
        event.registerEntityRenderer(NarutoEntities.SHURIKEN.get(), ShurikenRenderer::new);
        event.registerEntityRenderer(NarutoEntities.PAPER_BOMB.get(), PaperBombRenderer::new);

        event.registerEntityRenderer(NarutoEntities.FIREBALL_JUTSU.get(), FireballJutsuRenderer::new);
        event.registerEntityRenderer(NarutoEntities.WATER_BULLET_JUTSU.get(), WaterBulletJutsuRenderer::new);

        event.registerEntityRenderer(NarutoEntities.SUBSTITUTION_LOG.get(), SubstitutionLogRenderer::new);

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
        event.registerLayerDefinition(AnbuMaskModel.LAYER_LOCATION, () -> AnbuMaskModel.createLayer(true));
        event.registerLayerDefinition(AnbuMaskModel.LAYER_LOCATION_WITHOUT_EARS, () -> AnbuMaskModel.createLayer(false));
        event.registerLayerDefinition(HeadbandModel.LAYER_LOCATION, HeadbandModel::createLayer);

        // Jutsu
        event.registerLayerDefinition(FireballJutsuModel.LAYER_LOCATION, FireballJutsuModel::createLayer);
        event.registerLayerDefinition(WaterBulletModel.LAYER_LOCATION, WaterBulletModel::createLayer);

        // Entity
        event.registerLayerDefinition(SubstitutionLogModel.LAYER_LOCATION, SubstitutionLogModel::createBodyLayer);
    }

/*    @SubscribeEvent
    public static void entityLayers(EntityRenderersEvent.AddLayers event)
    {
        LivingEntityRenderer<Player, ? extends EntityModel<Player>> renderer = event.getRenderer(EntityType.PLAYER);
    }*/


}
