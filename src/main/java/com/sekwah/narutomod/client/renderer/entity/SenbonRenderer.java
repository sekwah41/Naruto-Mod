package com.sekwah.narutomod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import com.sekwah.narutomod.entity.projectile.SenbonEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SenbonRenderer extends ArrowRenderer<SenbonEntity> {
   public static final ResourceLocation RES_ARROW = new ResourceLocation("narutomod", "textures/entity/projectiles/senbon.png");

   public SenbonRenderer(EntityRendererProvider.Context manager) {
      super(manager);
   }

   @Override
   public void render(SenbonEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
      matrixStackIn.pushPose();
      matrixStackIn.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90.0F));
      matrixStackIn.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
      matrixStackIn.scale(0.5f, 0.5f, 0.5f);

      matrixStackIn.mulPose(Axis.XP.rotationDegrees(45.0F));
      matrixStackIn.scale(0.05625F, 0.05625F, 0.05625F);
      matrixStackIn.translate(-4.0D, 0.0D, 0.0D);
      VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
      PoseStack.Pose matrixstack$entry = matrixStackIn.last();
      Matrix4f matrix4f = matrixstack$entry.pose();
      Matrix3f matrix3f = matrixstack$entry.normal();

      for(int j = 0; j < 4; ++j) {
         matrixStackIn.mulPose(Axis.XP.rotationDegrees(90.0F));
         this.vertex(matrix4f, matrix3f, ivertexbuilder, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, packedLightIn);
         this.vertex(matrix4f, matrix3f, ivertexbuilder, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, packedLightIn);
         this.vertex(matrix4f, matrix3f, ivertexbuilder, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, packedLightIn);
         this.vertex(matrix4f, matrix3f, ivertexbuilder, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, packedLightIn);
      }

      matrixStackIn.popPose();
   }


   /**
    * Returns the location of an entity's texture.
    */
   @Override
   public ResourceLocation getTextureLocation(SenbonEntity entity) {
      return RES_ARROW;
   }
}
