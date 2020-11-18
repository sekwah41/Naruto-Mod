package com.sekwah.narutomod.client.renderer.entity;

import com.sekwah.narutomod.entity.NarutoEntities;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class NarutoEntityRenderers {

    public static void register() {
        RenderingRegistry.registerEntityRenderingHandler(NarutoEntities.KUNAI.get(), KunaiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NarutoEntities.EXPLOSIVE_KUNAI.get(), ExplosiveKunaiRenderer::new);
    }

}
