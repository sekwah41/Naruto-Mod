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
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;


public class SubstitutionLogRenderer extends MobRenderer<SubstitutionLogEntity, SubstitutionLogModel<SubstitutionLogEntity>> {
   public static final ResourceLocation SUBSTITUTION_LOG = new ResourceLocation("narutomod", "textures/entity/jutsu/substitution_log.png");

   public SubstitutionLogRenderer(EntityRendererProvider.Context manager) {
      super(manager, new SubstitutionLogModel(manager.bakeLayer(SubstitutionLogModel.LAYER_LOCATION)), 0.5F);
      this.shadowRadius = 0.5F;
   }

   public void render(SubstitutionLogEntity p_115976_, float p_115977_, float p_115978_, PoseStack p_115979_, MultiBufferSource p_115980_, int p_115981_) {
      this.shadowRadius = 0.3F;
      super.render(p_115976_, p_115977_, p_115978_, p_115979_, p_115980_, p_115981_);
   }

   @Override
   public ResourceLocation getTextureLocation(SubstitutionLogEntity entity) {
      return SUBSTITUTION_LOG;
   }

}
