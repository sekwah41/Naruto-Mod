package com.sekwah.narutomod.client.renderer.entity;

import com.sekwah.narutomod.entity.projectile.KunaiEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KunaiRenderer extends ArrowRenderer<KunaiEntity> {
   public static final ResourceLocation RES_ARROW = new ResourceLocation("narutomod", "textures/entity/projectiles/kunai.png");

   public KunaiRenderer(EntityRendererManager manager) {
      super(manager);
   }

   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getEntityTexture(KunaiEntity entity) {
      return RES_ARROW;
   }
}
