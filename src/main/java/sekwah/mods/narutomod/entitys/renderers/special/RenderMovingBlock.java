package sekwah.mods.narutomod.entitys.renderers.special;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.entitys.specials.EntityMovingBlock;

@SideOnly(Side.CLIENT)
public class RenderMovingBlock extends Render {

    private final RenderBlocks blockRenderer = new RenderBlocks();

    public RenderMovingBlock()
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
        GL11.glPushMatrix();

        if(entity.getShaking()) {
            float shakeAmount = 0.02f;
            GL11.glTranslatef((float) (this.shakeOffset(shakeAmount) + x),(float) (this.shakeOffset(shakeAmount) + y) + 0.5f,(float) (this.shakeOffset(shakeAmount) + z));
        }
        else {
            GL11.glTranslatef((float) x,(float) y+ 0.5f,(float) z);
        }
        GL11.glDisable(GL11.GL_LIGHTING);
        this.bindEntityTexture(entity);
        Block block = entity.getBlock();
        this.blockRenderer.setRenderBoundsFromBlock(entity.getBlock());
        this.blockRenderer.renderBlockSandFalling(block, entity.worldObj, MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY), MathHelper.floor_double(entity.posZ), entity.getMetaData());
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        //this.blockRenderer.renderBlockSandFalling(block, world, i, j, k, p_76986_1_.field_145814_a);
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
