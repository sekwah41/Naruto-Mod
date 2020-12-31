package com.sekwah.narutomod.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.entity.item.PaperBombEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class PaperBombRenderer extends EntityRenderer<PaperBombEntity> {

    public PaperBombRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(PaperBombEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {

        matrixStackIn.push();

        matrixStackIn.translate(-0.5D, 0.0D, -0.5D);

        BlockPos blockpos = new BlockPos(entityIn.getPosX(), entityIn.getBoundingBox().maxY, entityIn.getPosZ());
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRendererDispatcher();
        for (net.minecraft.client.renderer.RenderType type : net.minecraft.client.renderer.RenderType.getBlockRenderTypes()) {
            if (RenderTypeLookup.canRenderInLayer(entityIn.renderBlockState, type)) {
                blockrendererdispatcher.getBlockModelRenderer().renderModel(entityIn.world,
                        blockrendererdispatcher.getModelForState(entityIn.renderBlockState),
                        entityIn.renderBlockState, blockpos, matrixStackIn,
                        bufferIn.getBuffer(type),
                        false,
                        new Random(),
                        entityIn.renderBlockState.getPositionRandom(entityIn.getOrigin()),
                        OverlayTexture.NO_OVERLAY);
            }
        }

        matrixStackIn.pop();

    }

    @Override
    public ResourceLocation getEntityTexture(PaperBombEntity entity) {
        return null;
    }
}
