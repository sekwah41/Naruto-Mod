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
import sekwah.mods.narutomod.common.entity.specials.EntityChibakuBlock;

@SideOnly(Side.CLIENT)
public class RenderChibakuBlock extends Render
{
    private final RenderBlocks renderBlocks = new RenderBlocks();

    public RenderChibakuBlock()
    {
        this.shadowSize = 0.5F;
    }

    public void doRender(EntityChibakuBlock entity, double x, double y, double z, float p_76986_8_, float p_76986_9_)
    {
        GL11.glPushMatrix();
        World world = entity.getWorld();
        Block block = entity.getBlock();
        int i = MathHelper.floor_double(entity.posX);
        int j = MathHelper.floor_double(entity.posY);
        int k = MathHelper.floor_double(entity.posZ);

        GL11.glTranslatef(0, 0.5f, 0);

        if (block != null && block != world.getBlock(i, j, k))
        {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)x, (float)y, (float)z);
            float rot = ((float) entity.ticksExisted + p_76986_9_) * 10f;
            GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(60.0F, 0.7071F, 0.0F, 0.7071F);
            GL11.glRotatef(60.0F, 0.7071F, 0.0F, 0.7071F);
            GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(60.0F, 0.7071F, 0.0F, 0.7071F);
            GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
            this.bindEntityTexture(entity);
            GL11.glDisable(GL11.GL_LIGHTING);
            Tessellator tessellator;

            if (block instanceof BlockAnvil)
            {
                this.renderBlocks.blockAccess = world;
                tessellator = Tessellator.instance;
                tessellator.startDrawingQuads();
                tessellator.setTranslation((double)((float)(-i) - 0.5F), (double)((float)(-j) - 0.5F), (double)((float)(-k) - 0.5F));
                this.renderBlocks.renderBlockAnvilMetadata((BlockAnvil)block, i, j, k, entity.metadata);
                tessellator.setTranslation(0.0D, 0.0D, 0.0D);
                tessellator.draw();
            }
            else if (block instanceof BlockDragonEgg)
            {
                this.renderBlocks.blockAccess = world;
                tessellator = Tessellator.instance;
                tessellator.startDrawingQuads();
                tessellator.setTranslation((double)((float)(-i) - 0.5F), (double)((float)(-j) - 0.5F), (double)((float)(-k) - 0.5F));
                this.renderBlocks.renderBlockDragonEgg((BlockDragonEgg)block, i, j, k);
                tessellator.setTranslation(0.0D, 0.0D, 0.0D);
                tessellator.draw();
            }
            else
            {
                this.renderBlocks.setRenderBoundsFromBlock(block);
                this.renderBlocks.renderBlockSandFalling(block, world, i, j, k, entity.metadata);
            }

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(EntityChibakuBlock entity)
    {
        return TextureMap.locationBlocksTexture;
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((EntityChibakuBlock)p_110775_1_);
    }

    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((EntityChibakuBlock)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}