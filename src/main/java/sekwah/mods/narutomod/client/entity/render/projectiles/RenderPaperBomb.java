package sekwah.mods.narutomod.client.entity.render.projectiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.common.block.NarutoBlocks;
import sekwah.mods.narutomod.common.entity.projectiles.EntityPaperBomb;

@SideOnly(Side.CLIENT)
public class RenderPaperBomb extends Render {
    private RenderBlocks blockRenderer = new RenderBlocks();

    public RenderPaperBomb() {
        this.shadowSize = 0.5F;
    }

    public void renderPaperBomb(EntityPaperBomb par1RenderPaperBomb, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        float var10;

        if ((float) par1RenderPaperBomb.fuse - par9 + 1.0F < 10.0F) {
            var10 = 1.0F - ((float) par1RenderPaperBomb.fuse - par9 + 1.0F) / 10.0F;

            if (var10 < 0.0F) {
                var10 = 0.0F;
            }

            if (var10 > 1.0F) {
                var10 = 1.0F;
            }

            var10 *= var10;
            var10 *= var10;
            float var11 = 1.0F + var10 * 0.3F;
            GL11.glScalef(var11, var11, var11);
        }

        var10 = (1.0F - ((float) par1RenderPaperBomb.fuse - par9 + 1.0F) / 100.0F) * 0.8F;
        this.bindEntityTexture(par1RenderPaperBomb);
        this.blockRenderer.renderBlockAsItem(NarutoBlocks.PaperBombBlock, par1RenderPaperBomb.getRotation(), par1RenderPaperBomb.getBrightness(par9));

        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110808_a(EntityPaperBomb par1EntityPaperBomb) {
        return TextureMap.locationBlocksTexture;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.func_110808_a((EntityPaperBomb) par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is common
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderPaperBomb((EntityPaperBomb) par1Entity, par2, par4, par6, par8, par9);
    }
}