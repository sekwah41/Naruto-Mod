package sekwah.mods.narutomod.common.entity.particles;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import static org.lwjgl.opengl.GL11.*;

@SideOnly(Side.CLIENT)
public class EntityBirbFX extends EntityFX {

    private static final ResourceLocation texture = new ResourceLocation("narutomod:textures/entitys/jutsu/chidori.png");

    private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");

    private float breezeX = 0;
    private float breezeY = 0;
    private float breezeZ = 0;

    public EntityBirbFX(World world, double x, double y, double z) {
        super(world, x, y, z);
        this.particleMaxAge = (int) (20 * 5 + Math.random() * 20 * 2);
    }

    public void renderParticle(Tessellator tessellator1, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        glDepthMask(false);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glAlphaFunc(GL_GREATER, 0.003921569F);
        Tessellator tessellator = new Tessellator();
        tessellator.startDrawingQuads();
        tessellator.setBrightness(getBrightnessForRender(partialTicks));
        float scale = 1F * particleScale;
        float x = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
        float y = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
        float z = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);
        //tessellator.setColorRGBA_F(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha);
        tessellator.addVertexWithUV(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, 1, 1);
        tessellator.addVertexWithUV(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, 1, 0);
        tessellator.addVertexWithUV(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale, 0, 0);
        tessellator.addVertexWithUV(x + par3 * scale - par6 * scale, y - par4 * scale, z + par5 * scale - par7 * scale, 0, 1);
        tessellator.draw();
        glDisable(GL_BLEND);
        glAlphaFunc(GL_GREATER, 0.1F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(particleTextures);
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }
    }

    public int getFXLayer() {
        return 0;
    }

    public EntityBirbFX setMaxAge(int maxAge) {
        particleMaxAge = maxAge;
        return this;
    }

    public EntityBirbFX setGravity(float maxGravity) {
        particleGravity = maxGravity;
        return this;
    }

    public EntityBirbFX setScale(float maxScale) {
        particleScale = maxScale;
        return this;
    }

    public EntityBirbFX setMotion(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        return this;
    }

}
