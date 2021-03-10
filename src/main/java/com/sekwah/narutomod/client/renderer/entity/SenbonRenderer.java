package com.sekwah.narutomod.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.sekwah.narutomod.entity.projectile.SenbonEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

public class SenbonRenderer extends ArrowRenderer<SenbonEntity> {
   public static final ResourceLocation RES_ARROW = new ResourceLocation("narutomod", "textures/entity/projectiles/senbon.png");

   public SenbonRenderer(EntityRendererManager manager) {
      super(manager);
   }

   @Override
   public void render(SenbonEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
      matrixStackIn.pushPose();
      matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) - 90.0F));
      matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.xRotO, entityIn.xRot)));
      matrixStackIn.scale(0.5f, 0.5f, 0.5f);

      matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(45.0F));
      matrixStackIn.scale(0.05625F, 0.05625F, 0.05625F);
      matrixStackIn.translate(-4.0D, 0.0D, 0.0D);
      IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
      MatrixStack.Entry matrixstack$entry = matrixStackIn.last();
      Matrix4f matrix4f = matrixstack$entry.pose();
      Matrix3f matrix3f = matrixstack$entry.normal();

      for(int j = 0; j < 4; ++j) {
         matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F));
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
