package sekwah.mods.narutomod.client.entity.render.jutsuprojectiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import sekwah.mods.narutomod.client.PlayerRenderTickEvent;
import sekwah.mods.narutomod.common.entity.jutsuprojectiles.EntityWaterBullet;
import sekwah.mods.narutomod.common.entity.models.jutsuprojectiles.ModelWaterBullet;

@SideOnly(Side.CLIENT)
public class RenderWaterBullet extends Render {

    private static final ResourceLocation entityTexture = new ResourceLocation("narutomod:textures/entitys/waterBullet.png");

    private ModelBase mainModel;

    private float field_77002_a;

    public RenderWaterBullet() {
        this.mainModel = new ModelWaterBullet();
    }

    /**public void doRenderFireball(EntityFireball par1EntityFireball, double par2, double par4, double par6, float par8, float par9)
     {
     GL11.glPushMatrix();
     this.bindEntityTexture(par1EntityFireball);
     GL11.glTranslatef((float)par2, (float)par4, (float)par6);
     GL11.glEnable(GL12.GL_RESCALE_NORMAL);
     float f2 = this.field_77002_a;
     GL11.glScalef(2.4F, 2.4F, 2.4F);
     Tessellator tessellator = Tessellator.instance;
     float f3 = 0F;
     float f4 = 1F;
     float f5 = 0F;
     float f6 = 1F;
     float f7 = 1.0F;
     float f8 = 0.5F;
     float f9 = 0.25F;
     GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
     GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
     tessellator.startDrawingQuads();
     tessellator.setNormal(0.0F, 1.0F, 0.0F);
     tessellator.addVertexWithUV((double)(0.0F - f8), (double)(0.0F - f9), 0.0D, (double)f3, (double)f6);
     tessellator.addVertexWithUV((double)(f7 - f8), (double)(0.0F - f9), 0.0D, (double)f4, (double)f6);
     tessellator.addVertexWithUV((double)(f7 - f8), (double)(1.0F - f9), 0.0D, (double)f4, (double)f5);
     tessellator.addVertexWithUV((double)(0.0F - f8), (double)(1.0F - f9), 0.0D, (double)f3, (double)f5);
     tessellator.draw();
     GL11.glDisable(GL12.GL_RESCALE_NORMAL);
     GL11.glPopMatrix();
     }*/

    /**
     * Renders the entity's shadow and fire (if its on fire). Args: entity, x, y, z, yaw, partialTickTime
     */
    public void doRenderShadowAndFire(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        if (par1Entity.canRenderOnFire()) {
            //this.renderEntityOnFire(par1Entity, par2, par4, par6, par9);
        }
    }

    private void renderEntityOnFire(Entity par1Entity, double par2, double par4, double par6, float par8) {
        GL11.glDisable(GL11.GL_LIGHTING);
        IIcon icon = Blocks.fire.getFireIcon(0);
        IIcon icon1 = Blocks.fire.getFireIcon(1);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2, (float) par4, (float) par6);
        float f1 = par1Entity.width * 1.4F;

        EntityWaterBullet entityFireball = (EntityWaterBullet) par1Entity;

        GL11.glScalef(f1, f1, f1);
        Tessellator tessellator = Tessellator.instance;
        float f2 = 0.5F;
        float f3 = 0.0F;
        float f4 = par1Entity.height / f1;
        float f5 = (float) (par1Entity.posY - par1Entity.boundingBox.minY);
        GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, 0.0F, -0.3F + (float) ((int) f4) * 0.02F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f6 = 0.0F;
        int i = 0;
        tessellator.startDrawingQuads();

        while (f4 > 0.0F) {
            IIcon icon2 = i % 2 == 0 ? icon : icon1;
            this.bindTexture(TextureMap.locationBlocksTexture);
            float f7 = icon2.getMinU();
            float f8 = icon2.getMinV();
            float f9 = icon2.getMaxU();
            float f10 = icon2.getMaxV();

            if (i / 2 % 2 == 0) {
                float f11 = f9;
                f9 = f7;
                f7 = f11;
            }

            tessellator.addVertexWithUV((double) (f2 - f3), (double) (0.0F - f5), (double) f6, (double) f9, (double) f10);
            tessellator.addVertexWithUV((double) (-f2 - f3), (double) (0.0F - f5), (double) f6, (double) f7, (double) f10);
            tessellator.addVertexWithUV((double) (-f2 - f3), (double) (1.4F - f5), (double) f6, (double) f7, (double) f8);
            tessellator.addVertexWithUV((double) (f2 - f3), (double) (1.4F - f5), (double) f6, (double) f9, (double) f8);
            f4 -= 0.45F;
            f5 -= 0.45F;
            f2 *= 0.9F;
            f6 += 0.03F;
            ++i;
        }

        tessellator.draw();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    public void doRenderWaterBullet(EntityWaterBullet par1EntityWaterBullet, double par2, double par4, double par6, float par8, float par9) {
        // uses the same code as the player renderer to get the smooth
        float delta = PlayerRenderTickEvent.delta;
        //while(delta-- >= 1){
        //	if(par1EntityWaterBullet.fireballMaxGrowth > par1EntityWaterBullet.fireballGrowth){
        //		++par1EntityWaterBullet.fireballGrowth;
        //	}
        //	++par1EntityWaterBullet.fireballRotation;
        //}
        // float f2 = (float)par1EntityWaterBullet.innerRotation + par9;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) par2, (float) par4 + par1EntityWaterBullet.height / 2, (float) par6);
        GL11.glRotatef(-par1EntityWaterBullet.rotationYaw - 0.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(par1EntityWaterBullet.rotationPitch, 0.0F, 0.0F, 1.0F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.65F);
        GL11.glEnable(GL11.GL_BLEND);
        this.bindTexture(entityTexture);
        float f3 = MathHelper.sin(0.2F) / 2.0F + 0.5F;
        f3 += f3 * f3;
        this.mainModel.render(par1EntityWaterBullet, 0.0F, 3.0F, f3 * 0.2F, 0, 0, 0.0625F);
        GL11.glPopMatrix();
    }

    protected ResourceLocation entityTexture(EntityWaterBullet par1Entity) {
        return entityTexture;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return this.entityTexture((EntityWaterBullet) par1Entity);
    }

    //protected ResourceLocation getWaterBulletTextures(EntityWaterBullet par1EntityWaterBullet)
    //{
    //     return TextureMap.locationItemsTexture;
    //}

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    //protected ResourceLocation getEntityTexture(Entity par1Entity)
    //{
    //    return this.getWaterBulletTextures((EntityWaterBullet)par1Entity);
    //}

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is common
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderWaterBullet((EntityWaterBullet) par1Entity, par2, par4, par6, par8, par9);
    }
}
