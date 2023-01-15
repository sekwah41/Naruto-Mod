package com.sekwah.narutomod.client.renderer.entity.jutsuprojectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import org.joml.Vector3f;
import com.sekwah.narutomod.client.model.jutsu.WaterBulletModel;
import com.sekwah.narutomod.entity.jutsuprojectile.WaterBulletJutsuEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class WaterBulletJutsuRenderer extends EntityRenderer<WaterBulletJutsuEntity> {
   public static final ResourceLocation WATER_BULLET_TEX = new ResourceLocation("narutomod", "textures/entity/jutsu/projectiles/water_bullet.png");
   private static final RenderType RENDER_TYPE = RenderType.entityTranslucent(WATER_BULLET_TEX);
   private final WaterBulletModel model;

   public WaterBulletJutsuRenderer(EntityRendererProvider.Context manager) {
      super(manager);
      this.model = new WaterBulletModel(manager.bakeLayer(WaterBulletModel.LAYER_LOCATION));
   }

   @Override
   public void render(WaterBulletJutsuEntity entity, float p_114486_, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_114490_) {
      VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RENDER_TYPE);
      poseStack.pushPose();
      poseStack.translate(0, 0.16F, 0);
      poseStack.mulPose(Axis.YN.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot())));
      poseStack.mulPose(Axis.XN.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot())));
      this.model.renderToBuffer(poseStack, vertexconsumer, p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.popPose();
      super.render(entity, p_114486_, partialTicks, poseStack, multiBufferSource, p_114490_);
   }

   @Override
   public ResourceLocation getTextureLocation(WaterBulletJutsuEntity p_114482_) {
      return WATER_BULLET_TEX;
   }

}
