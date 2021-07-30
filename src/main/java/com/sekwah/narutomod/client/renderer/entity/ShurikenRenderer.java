package com.sekwah.narutomod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.sekwah.narutomod.entity.projectile.ShurikenEntity;
import com.sekwah.narutomod.item.NarutoItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;


public class ShurikenRenderer extends ArrowRenderer<ShurikenEntity> {

   private final ItemRenderer itemRenderer;

   private ItemStack renderingItem;

   public static final ResourceLocation RES_ARROW = new ResourceLocation("narutomod", "textures/entity/projectiles/kunai.png");

   public ShurikenRenderer(EntityRendererProvider.Context manager) {
      super(manager);
      this.itemRenderer = Minecraft.getInstance().getItemRenderer();
      this.renderingItem = new ItemStack(NarutoItems.SHURIKEN.get());
   }


   @Override
   public void render(ShurikenEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
      matrixStackIn.pushPose();
      float rotateSpeed = -50;
      matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90.0F));
      matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
      matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(entityIn.getRotOffset()));
      matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entityIn.getPrevRotateTicks() * rotateSpeed, entityIn.getRotateTicks() * rotateSpeed)));
      matrixStackIn.scale(0.5f, 0.5f, 0.5f);
      BakedModel ibakedmodel = itemRenderer.getModel(this.renderingItem, entityIn.level, null, entityIn.getId());
      itemRenderer.render(this.renderingItem, ItemTransforms.TransformType.FIXED, false, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, ibakedmodel);
      matrixStackIn.popPose();
   }


   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getTextureLocation(ShurikenEntity entity) {
      return RES_ARROW;
   }
}
