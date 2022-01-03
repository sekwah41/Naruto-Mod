package com.sekwah.narutomod.client.renderer.entity.jutsuprojectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.sekwah.narutomod.client.model.jutsu.FireballJutsuModel;
import com.sekwah.narutomod.client.renderer.NarutoRenderEvents;
import com.sekwah.narutomod.entity.jutsuprojectile.FireballJutsuEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;


public class FireballJutsuRenderer extends EntityRenderer<FireballJutsuEntity> {
   public static final ResourceLocation FIREBALL_TEX = new ResourceLocation("narutomod", "textures/entity/jutsu/projectiles/fireball.png");
   private static final RenderType RENDER_TYPE = RenderType.entityTranslucent(FIREBALL_TEX);
   private final FireballJutsuModel model;

   public FireballJutsuRenderer(EntityRendererProvider.Context manager) {
      super(manager);
      this.model = new FireballJutsuModel(manager.bakeLayer(NarutoRenderEvents.FIREBALL_LAYER));
   }

   @Override
   public void render(FireballJutsuEntity fireballJutsuEntity, float p_114486_, float partial, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_114490_) {
      VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RENDER_TYPE);
      poseStack.pushPose();
      poseStack.translate(0,fireballJutsuEntity.getBbHeight() / 2f,0);
      float time = (fireballJutsuEntity.time + partial) * 10.0F;
      float scaleTime = FireballJutsuEntity.GROW_TIME * 10;
      if(time < scaleTime) {
         float scale = FireballJutsuEntity.INITIAL_SCALE
                 + (FireballJutsuEntity.GROW_SCALE - (FireballJutsuEntity.GROW_SCALE * ((scaleTime - time) / scaleTime)));
         poseStack.scale(scale, scale, scale);
      }
      poseStack.mulPose(new Vector3f(1.0F, 0.0F, 0.0F).rotationDegrees(time));
      poseStack.mulPose(new Vector3f(0.0F, 1.0F, 0.0F).rotationDegrees(time));
      this.model.renderToBuffer(poseStack, vertexconsumer, p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.popPose();
      super.render(fireballJutsuEntity, p_114486_, partial, poseStack, multiBufferSource, p_114490_);
   }

   @Override
   protected int getBlockLightLevel(FireballJutsuEntity p_114087_, BlockPos p_114088_) {
      return 15;
   }

   @Override
   public ResourceLocation getTextureLocation(FireballJutsuEntity p_114482_) {
      return FIREBALL_TEX;
   }

}
