package sekwah.mods.narutomod.client.entity.render.projectiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import sekwah.mods.narutomod.common.entity.projectiles.EntitySenbon;

@SideOnly(Side.CLIENT)
public class RenderSenbon extends Render {
    private static final ResourceLocation entityTexture = new ResourceLocation("narutomod:textures/entitys/Senbon.png");

    public void renderSenbon(EntitySenbon par1EntitySenbon, double par2, double par4, double par6, float par8, float par9) {

        this.bindEntityTexture(par1EntitySenbon);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        GL11.glRotatef(par1EntitySenbon.prevRotationYaw + (par1EntitySenbon.rotationYaw - par1EntitySenbon.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(par1EntitySenbon.prevRotationPitch + (par1EntitySenbon.rotationPitch - par1EntitySenbon.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
        Tessellator var10 = Tessellator.instance;
        byte var11 = 0;
        float var12 = 0.0F;
        float var13 = 0.5F;
        float var14 = (float) (0 + var11 * 10) / 32.0F;
        float var15 = (float) (5 + var11 * 10) / 32.0F;
        float var16 = 0.0F;
        float var17 = 0.15625F;
        float var18 = (float) (5 + var11 * 10) / 32.0F;
        float var19 = (float) (10 + var11 * 10) / 32.0F;
        float var20 = 0.02625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float var21 = (float) par1EntitySenbon.arrowShake - par9;

        if (var21 > 0.0F) {
            float var22 = -MathHelper.sin(var21 * 3.0F) * var21;
            GL11.glRotatef(var22, 0.0F, 0.0F, 1.0F);
        }

        GL11.glScalef(var20, var20, var20);

        for (int var23 = 0; var23 < 4; ++var23) {
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, var20);
            var10.startDrawingQuads();
            var10.addVertexWithUV(-8.0D, -2.0D, 0.0D, (double) var12, (double) var14);
            var10.addVertexWithUV(8.0D, -2.0D, 0.0D, (double) var13, (double) var14);
            var10.addVertexWithUV(8.0D, 2.0D, 0.0D, (double) var13, (double) var15);
            var10.addVertexWithUV(-8.0D, 2.0D, 0.0D, (double) var12, (double) var15);
            var10.draw();
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    protected ResourceLocation entityTexture(EntitySenbon par1EntitySenbon) {
        return entityTexture;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.entityTexture((EntitySenbon) par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is common
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderSenbon((EntitySenbon) par1Entity, par2, par4, par6, par8, par9);
    }
}