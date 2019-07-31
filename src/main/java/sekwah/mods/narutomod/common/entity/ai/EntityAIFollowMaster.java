package sekwah.mods.narutomod.common.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import sekwah.mods.narutomod.common.entity.EntityShadowClone;

public class EntityAIFollowMaster extends EntityAIBase {
    World theWorld;
    float maxDist;
    float minDist;
    private EntityShadowClone theClone;
    private EntityLivingBase theOwner;
    private double field_75336_f;
    private PathNavigate petPathfinder;
    private int field_75343_h;
    private boolean field_75344_i;

    private boolean sprinting = false;

    public EntityAIFollowMaster(EntityShadowClone par1Entity, double par2, float par4, float par5) {
        this.theClone = par1Entity;
        this.theWorld = par1Entity.worldObj;
        this.field_75336_f = par2;
        this.petPathfinder = par1Entity.getNavigator();
        this.minDist = par4;
        this.maxDist = par5;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.theClone.getMaster();

        if (entitylivingbase == null) {
            return false;
        } else if (this.theClone.getDistanceSqToEntity(entitylivingbase) < (double) (this.minDist * this.minDist)) {
            return false;
        } else {
            this.theOwner = entitylivingbase;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        return !this.petPathfinder.noPath() && this.theClone.getDistanceSqToEntity(this.theOwner) > (double) (this.maxDist * this.maxDist);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.field_75343_h = 0;
        this.field_75344_i = this.theClone.getNavigator().getAvoidsWater();
        this.theClone.getNavigator().setAvoidsWater(false);
    }

    /**
     * Resets the task
     */
    public void resetTask() {
        this.theOwner = null;
        this.petPathfinder.clearPathEntity();
        this.theClone.getNavigator().setAvoidsWater(this.field_75344_i);
    }

    /**
     * Updates the task
     */
    public void updateTask() {
        this.theClone.getLookHelper().setLookPositionWithEntity(this.theOwner, 10.0F, (float) this.theClone.getVerticalFaceSpeed());

        if (--this.field_75343_h <= 0) {
            this.field_75343_h = 10;

            if (!this.petPathfinder.tryMoveToEntityLiving(this.theOwner, this.field_75336_f)) {
                if (!this.theClone.getLeashed()) {

                }
            }
            if (!this.sprinting && this.theClone.getDistanceSqToEntity(this.theOwner) >= 256.0D) {
                this.sprinting = true;
                this.theClone.setSprinting(true);
            }
            else if(this.sprinting && this.theClone.getDistanceSqToEntity(this.theOwner) <= 32.0D) {
                this.sprinting = false;
                this.theClone.setSprinting(false);
            }
        }
    }
}
