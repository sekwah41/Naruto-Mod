package com.sekwah.narutomod.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sekwah.narutomod.entity.item.PaperBombEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class PaperBombRenderer extends EntityRenderer<PaperBombEntity> {

    public PaperBombRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager);
    }

    @Override
    public void render(PaperBombEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {

        matrixStackIn.pushPose();

        matrixStackIn.translate(-0.5D, -0.25D, -0.5D);

        Vec3i dir = entityIn.renderBlockState.getValue(BlockStateProperties.HORIZONTAL_FACING).getNormal();
        AttachFace face = entityIn.renderBlockState.getValue(FaceAttachedHorizontalDirectionalBlock.FACE);

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

        BlockPos blockpos = new BlockPos(entityIn.getBlockX(), Mth.floor(entityIn.getBoundingBox().maxY), entityIn.getBlockZ());
        BlockRenderDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRenderer();

        RenderType rendertype = ItemBlockRenderTypes.getMovingBlockRenderType(entityIn.renderBlockState);
        VertexConsumer vertexconsumer = bufferIn.getBuffer(rendertype);
        blockrendererdispatcher.getModelRenderer().tesselateBlock(entityIn.level(),
                blockrendererdispatcher.getBlockModel(entityIn.renderBlockState),
                entityIn.renderBlockState, blockpos, matrixStackIn,
                vertexconsumer,
                false,
                RandomSource.create(),
                entityIn.renderBlockState.getSeed(entityIn.getOrigin()),
                OverlayTexture.NO_OVERLAY);

        matrixStackIn.popPose();

    }

    @Override
    public ResourceLocation getTextureLocation(PaperBombEntity entity) {
        return null;
    }
}
