package com.sekwah.narutomod.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sekwah.narutomod.entity.item.PaperBombEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;

import java.util.Random;

import static net.minecraft.block.HorizontalFaceBlock.FACE;
import static net.minecraft.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class PaperBombRenderer extends EntityRenderer<PaperBombEntity> {

    public PaperBombRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(PaperBombEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {

        matrixStackIn.pushPose();

        matrixStackIn.translate(-0.5D, -0.25D, -0.5D);

        Vector3i dir = entityIn.renderBlockState.getValue(HORIZONTAL_FACING).getNormal();
        AttachFace face = entityIn.renderBlockState.getValue(FACE);

        switch (face) {
            case FLOOR:
                matrixStackIn.translate(0, 0.25D, 0);
                break;
            case CEILING:
                matrixStackIn.translate(0, -0.25D, 0);
                break;
            case WALL:
                matrixStackIn.translate(0.25D * dir.getX(), 0, 0.25D * dir.getZ());
                break;
        }

        BlockPos blockpos = new BlockPos(entityIn.getX(), entityIn.getBoundingBox().maxY, entityIn.getZ());
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRenderer();
        for (net.minecraft.client.renderer.RenderType type : net.minecraft.client.renderer.RenderType.chunkBufferLayers()) {
            if (RenderTypeLookup.canRenderInLayer(entityIn.renderBlockState, type)) {
                blockrendererdispatcher.getModelRenderer().tesselateWithoutAO(entityIn.level,
                        blockrendererdispatcher.getBlockModel(entityIn.renderBlockState),
                        entityIn.renderBlockState, blockpos, matrixStackIn,
                        bufferIn.getBuffer(type),
                        false,
                        new Random(),
                        entityIn.renderBlockState.getSeed(entityIn.getOrigin()),
                        OverlayTexture.NO_OVERLAY);
            }
        }

        matrixStackIn.popPose();

    }

    @Override
    public ResourceLocation getTextureLocation(PaperBombEntity entity) {
        return null;
    }
}
