package com.sekwah.narutomod.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sekwah.narutomod.entity.projectile.ShurikenEntity;
import com.sekwah.narutomod.item.NarutoItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShurikenRenderer extends ArrowRenderer<ShurikenEntity> {

   private final net.minecraft.client.renderer.ItemRenderer itemRenderer;

   private ItemStack renderingItem;

   public static final ResourceLocation RES_ARROW = new ResourceLocation("narutomod", "textures/entity/projectiles/kunai.png");

   public ShurikenRenderer(EntityRendererManager manager) {
      super(manager);
      this.itemRenderer = Minecraft.getInstance().getItemRenderer();
      this.renderingItem = new ItemStack(NarutoItems.SHURIKEN.get());
   }


   @Override
   public void render(ShurikenEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
      matrixStackIn.push();
      float rotateSpeed = -50;
      matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90.0F));
      matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
      matrixStackIn.rotate(Vector3f.XP.rotationDegrees(entityIn.getRotOffset()));
      matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.getPrevRotateTicks() * rotateSpeed, entityIn.getRotateTicks() * rotateSpeed)));
      matrixStackIn.scale(0.5f, 0.5f, 0.5f);
      IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(this.renderingItem, entityIn.world, (LivingEntity)null);
      itemRenderer.renderItem(this.renderingItem, ItemCameraTransforms.TransformType.FIXED, false, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, ibakedmodel);
      matrixStackIn.pop();
   }


   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getEntityTexture(ShurikenEntity entity) {
      return RES_ARROW;
   }
}
