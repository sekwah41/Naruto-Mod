package com.sekwah.narutomod.client.renderer.entity;

import com.sekwah.narutomod.entity.projectile.KunaiEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;


public class KunaiRenderer extends ArrowRenderer<KunaiEntity> {
   public static final ResourceLocation RES_ARROW = new ResourceLocation("narutomod", "textures/entity/projectiles/kunai.png");

   public KunaiRenderer(EntityRendererManager manager) {
      super(manager);
   }

   /**
    * Returns the location of an entity's texture.
    */
   @Override
   public ResourceLocation getTextureLocation(KunaiEntity entity) {
      return RES_ARROW;
   }

}
