package sekwah.mods.narutomod.entitys.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityColouredSmokeTrackingFX extends EntityFX {
    private static final String __OBFID = "CL_00000924";

    private double offsetX = 0;
    private double offsetY = 0;
    private double offsetZ = 0;

    float smokeParticleScale;

    private Entity trackingEntity;

    public EntityColouredSmokeTrackingFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par15, float par16, float par17, Entity entity) {
        this(par1World, par2, par4, par6, par8, par10, par12, 1.0F, par15, par16, par17, entity);
    }

    public EntityColouredSmokeTrackingFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14, float par15, float par16, float par17, Entity entity) {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.offsetX = par8;
        this.offsetY = par10;
        this.offsetZ = par12;
        this.trackingEntity = entity;
        if(this.trackingEntity != null){
            this.posX = this.trackingEntity.posX + this.offsetX;
            this.posY = this.trackingEntity.posY + this.offsetY;
            this.posZ = this.trackingEntity.posZ + this.offsetZ;
        }
        this.particleRed = par15;
        this.particleGreen = par16;
        this.particleBlue = par17;
        this.particleScale *= 0.75F;
        this.particleScale *= par14;
        this.smokeParticleScale = this.particleScale;
        this.particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
        this.particleMaxAge = (int) ((float) this.particleMaxAge * par14);
        this.noClip = true;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }

        this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
        //this.motionY += 0.004D;
        if(this.trackingEntity != null){
            this.posX = this.trackingEntity.posX + this.offsetX;
            this.posY = this.trackingEntity.posY + this.offsetY;
            this.posZ = this.trackingEntity.posZ + this.offsetZ;
        }
        else{
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
        }

        if (this.posY == this.prevPosY) {
            this.motionX *= 1.1D;
            this.motionZ *= 1.1D;
        }

        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;

        /*this.offsetX += motionX;
        this.offsetY += motionY;
        this.offsetZ += motionZ;*/

        if (this.onGround) {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}
