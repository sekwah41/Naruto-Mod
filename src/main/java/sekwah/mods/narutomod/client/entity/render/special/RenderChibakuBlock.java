package sekwah.mods.narutomod.client.entity.render.special;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.common.entity.specials.EntityMovingBlock;

@SideOnly(Side.CLIENT)
public class RenderChibakuBlock extends Render {

    private final RenderBlocks blockRenderer = new RenderBlocks();

    public RenderChibakuBlock()
    {
        this.shadowSize = 0.0F;
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
        this.renderBlock((EntityMovingBlock) entity, x, y, z, p_76986_8_, p_76986_9_);
    }

    private double shakeOffset(float shakeAmount) {
        return (Math.random() - 0.5) * shakeAmount;
    }

    private void renderBlock(EntityMovingBlock entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
        World world = entity.worldObj;
        Block block = entity.getBlock();
        int i = MathHelper.floor_double(entity.posX);
        int j = MathHelper.floor_double(entity.posY);
        int k = MathHelper.floor_double(entity.posZ);

        if (block != null && block != world.getBlock(i, j, k))
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)x, (float)y, (float)z);
            this.bindEntityTexture(entity);
            GL11.glDisable(GL11.GL_LIGHTING);
            Tessellator tessellator;

            if (block instanceof BlockAnvil)
            {
                this.blockRenderer.blockAccess = world;
                tessellator = Tessellator.instance;
                tessellator.startDrawingQuads();
                tessellator.setTranslation((double)((float)(-i) - 0.5F), (double)((float)(-j) - 0.5F), (double)((float)(-k) - 0.5F));
                this.blockRenderer.renderBlockAnvilMetadata((BlockAnvil)block, i, j, k, entity.getMetaData());
                tessellator.setTranslation(0.0D, 0.0D, 0.0D);
                tessellator.draw();
            }
            else if (block instanceof BlockDragonEgg)
            {
                this.blockRenderer.blockAccess = world;
                tessellator = Tessellator.instance;
                tessellator.startDrawingQuads();
                tessellator.setTranslation((double)((float)(-i) - 0.5F), (double)((float)(-j) - 0.5F), (double)((float)(-k) - 0.5F));
                this.blockRenderer.renderBlockDragonEgg((BlockDragonEgg)block, i, j, k);
                tessellator.setTranslation(0.0D, 0.0D, 0.0D);
                tessellator.draw();
            }
            else
            {
                this.blockRenderer.setRenderBoundsFromBlock(block);
                this.blockRenderer.renderBlockSandFalling(block, world, i, j, k, entity.getMetaData());
            }

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityMovingBlock)p_110775_1_);
    }

    protected ResourceLocation getEntityTexture(EntityMovingBlock entity)
    {
        return TextureMap.locationBlocksTexture;
    }
}
