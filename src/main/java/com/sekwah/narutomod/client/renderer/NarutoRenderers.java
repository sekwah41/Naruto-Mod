package com.sekwah.narutomod.client.renderer;

import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.client.renderer.entity.*;
import com.sekwah.narutomod.entity.NarutoEntities;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class NarutoRenderers {

    public static void registerEntity() {
        RenderingRegistry.registerEntityRenderingHandler(NarutoEntities.KUNAI.get(), KunaiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NarutoEntities.EXPLOSIVE_KUNAI.get(), ExplosiveKunaiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NarutoEntities.SENBON.get(), SenbonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NarutoEntities.SHURIKEN.get(), ShurikenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NarutoEntities.PAPER_BOMB.get(), PaperBombRenderer::new);
    }

    public static void registerRenderTypes() {
        RenderTypeLookup.setRenderLayer(NarutoBlocks.PAPER_BOMB.get(), RenderType.translucent());
    }

}
