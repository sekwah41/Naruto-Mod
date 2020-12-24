package com.sekwah.narutomod.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.sekwah.narutomod.block.NarutoBlocks;
import com.sekwah.narutomod.entity.item.PaperBombEntity;
import com.sekwah.narutomod.entity.projectile.ShurikenEntity;
import com.sekwah.narutomod.item.NarutoItems;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class PaperBombRenderer extends EntityRenderer<PaperBombEntity> {


    private final ItemStack renderingItem;
    private final ItemRenderer itemRenderer;

    public PaperBombRenderer(EntityRendererManager renderManager) {
        super(renderManager);
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
        this.renderingItem = new ItemStack(NarutoItems.SHURIKEN.get());
    }

    @Override
    public void render(PaperBombEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {

        BlockState state = NarutoBlocks.PAPER_BOMB.get().getDefaultState();

        BlockPos blockpos = new BlockPos(entityIn.getPosX(), entityIn.getBoundingBox().maxY, entityIn.getPosZ());
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRendererDispatcher();
        for (net.minecraft.client.renderer.RenderType type : net.minecraft.client.renderer.RenderType.getBlockRenderTypes()) {
            if (RenderTypeLookup.canRenderInLayer(state, type)) {
                blockrendererdispatcher.getBlockModelRenderer().renderModel(entityIn.world, blockrendererdispatcher.getModelForState(state), state, blockpos, matrixStackIn, bufferIn.getBuffer(type), false, new Random(), state.getPositionRandom(entityIn.getOrigin()), OverlayTexture.NO_OVERLAY);
            }
        }
    }

    @Override
    public ResourceLocation getEntityTexture(PaperBombEntity entity) {
        return null;
    }
}
