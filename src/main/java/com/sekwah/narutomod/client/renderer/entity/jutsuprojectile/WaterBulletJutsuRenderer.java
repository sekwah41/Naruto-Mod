package com.sekwah.narutomod.client.renderer.entity.jutsuprojectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.sekwah.narutomod.client.model.jutsu.WaterBulletModel;
import com.sekwah.narutomod.client.renderer.NarutoRenderEvents;
import com.sekwah.narutomod.entity.jutsuprojectile.FireballJutsuEntity;
import com.sekwah.narutomod.entity.jutsuprojectile.WaterBulletJutsuEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class WaterBulletJutsuRenderer extends EntityRenderer<WaterBulletJutsuEntity> {
   public static final ResourceLocation WATER_BULLET_TEX = new ResourceLocation("narutomod", "textures/entity/jutsu/projectiles/water_bullet.png");
   private static final RenderType RENDER_TYPE = RenderType.entityTranslucent(WATER_BULLET_TEX);
   private final WaterBulletModel model;

   public WaterBulletJutsuRenderer(EntityRendererProvider.Context manager) {
      super(manager);
      this.model = new WaterBulletModel(manager.bakeLayer(NarutoRenderEvents.WATER_BULLET_LAYER));
   }

   @Override
   public void render(WaterBulletJutsuEntity entity, float p_114486_, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_114490_) {
      VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RENDER_TYPE);
      poseStack.pushPose();
      poseStack.translate(0, 0.16F, 0);
      poseStack.mulPose(Vector3f.YN.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
      this.model.renderToBuffer(poseStack, vertexconsumer, p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.popPose();
      super.render(entity, p_114486_, partialTicks, poseStack, multiBufferSource, p_114490_);
   }

   @Override
   protected int getBlockLightLevel(WaterBulletJutsuEntity p_114087_, BlockPos p_114088_) {
      return 15;
   }

   @Override
   public ResourceLocation getTextureLocation(WaterBulletJutsuEntity p_114482_) {
      return WATER_BULLET_TEX;
   }

}
