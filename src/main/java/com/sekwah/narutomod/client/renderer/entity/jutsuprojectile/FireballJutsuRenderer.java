package com.sekwah.narutomod.client.renderer.entity.jutsuprojectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sekwah.narutomod.client.model.item.model.FireballJutsuModel;
import com.sekwah.narutomod.client.renderer.NarutoRenderEvents;
import com.sekwah.narutomod.entity.jutsuprojectile.FireballJutsuEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;


public class FireballJutsuRenderer extends EntityRenderer<FireballJutsuEntity> {
   public static final ResourceLocation FIREBALL_TEX = new ResourceLocation("narutomod", "textures/entity/jutsu/projectiles/fireball.png");
   private final FireballJutsuModel model;

   public FireballJutsuRenderer(EntityRendererProvider.Context manager) {
      super(manager);
      this.model = new FireballJutsuModel(manager.bakeLayer(NarutoRenderEvents.FIREBALL_LAYER));
   }

   @Override
   public void render(FireballJutsuEntity p_114485_, float p_114486_, float p_114487_, PoseStack p_114488_, MultiBufferSource p_114489_, int p_114490_) {
      VertexConsumer vertexconsumer = p_114489_.getBuffer(this.model.renderType(FIREBALL_TEX));
      this.model.renderToBuffer(p_114488_, vertexconsumer, p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      super.render(p_114485_, p_114486_, p_114487_, p_114488_, p_114489_, p_114490_);
   }

   @Override
   public ResourceLocation getTextureLocation(FireballJutsuEntity p_114482_) {
      return FIREBALL_TEX;
   }

}
