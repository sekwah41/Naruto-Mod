package com.sekwah.narutomod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.sekwah.narutomod.client.model.entity.SubstitutionLogModel;
import com.sekwah.narutomod.client.model.jutsu.FireballJutsuModel;
import com.sekwah.narutomod.client.renderer.NarutoRenderEvents;
import com.sekwah.narutomod.entity.SubstitutionLogEntity;
import com.sekwah.narutomod.entity.jutsuprojectile.FireballJutsuEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;


public class SubstitutionLogRenderer extends EntityRenderer<SubstitutionLogEntity> {
   public static final ResourceLocation SUBSTITUTION_LOG = new ResourceLocation("narutomod", "textures/entity/jutsu/substitution_log.png");
   private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(SUBSTITUTION_LOG);
   private final SubstitutionLogModel model;

   public SubstitutionLogRenderer(EntityRendererProvider.Context manager) {
      super(manager);
      this.model = new SubstitutionLogModel(manager.bakeLayer(SubstitutionLogModel.LAYER_LOCATION));
   }

   @Override
   public void render(SubstitutionLogEntity substitutionLogEntity, float p_114486_, float partial, PoseStack poseStack, MultiBufferSource multiBufferSource, int p_114490_) {
      VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RENDER_TYPE);
      poseStack.pushPose();
      poseStack.scale(-1.0F, -1.0F, 1.0F);
      poseStack.translate(0.0D, (double)-1.501F, 0.0D);
      this.model.renderToBuffer(poseStack, vertexconsumer, p_114490_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
      poseStack.popPose();
      super.render(substitutionLogEntity, p_114486_, partial, poseStack, multiBufferSource, p_114490_);
   }

   @Override
   public ResourceLocation getTextureLocation(SubstitutionLogEntity p_114482_) {
      return SUBSTITUTION_LOG;
   }

}
