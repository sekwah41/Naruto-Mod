package com.sekwah.mods.narutomod.entitys.particles;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import static org.lwjgl.opengl.GL11.*;

public class EntitySakuraPetalFX extends EntityFX {

    private static final ResourceLocation texture = new ResourceLocation("narutomod:textures/particles/sakuraParticle.png");

    private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");

    private float breezeX = 0;
    private float breezeY = 0;
    private float breezeZ = 0;

    public EntitySakuraPetalFX(World world, double x, double y, double z) {
        super(world, x, y, z);
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
        float scale = 0.1F * particleScale;
        float x = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
        float y = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
        float z = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);
        //tessellator.setColorRGBA_F(this.particleRed * f14, this.particleGreen * f14, this.particleBlue * f14, this.particleAlpha);
        tessellator.addVertexWithUV(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, 0, 0);
        tessellator.addVertexWithUV(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, 1, 0);
        tessellator.addVertexWithUV(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale, 0, 1);
        tessellator.addVertexWithUV(x + par3 * scale - par6 * scale, y - par4 * scale, z + par5 * scale - par7 * scale, 1, 1);
        tessellator.draw();
        glDisable(GL_BLEND);
        glAlphaFunc(GL_GREATER, 0.1F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(particleTextures);
    }

    public void onUpdate() {
        super.onUpdate();
        if (!this.onGround) {
            this.motionX = breezeX;
            this.motionZ = breezeZ;
            particleAge -= 1;
        }

    }

    public int getFXLayer() {
        return 0;
    }

    public EntitySakuraPetalFX setMaxAge(int maxAge) {
        particleMaxAge = maxAge;
        return this;
    }

    public EntitySakuraPetalFX setGravity(float maxGravity) {
        particleGravity = maxGravity;
        return this;
    }

    public EntitySakuraPetalFX setScale(float maxScale) {
        particleScale = maxScale;
        return this;
    }

    public EntitySakuraPetalFX setBreeze(float x, float y, float z) {
        breezeX = x;
        breezeY = y;
        breezeZ = z;
        return this;
    }

}
