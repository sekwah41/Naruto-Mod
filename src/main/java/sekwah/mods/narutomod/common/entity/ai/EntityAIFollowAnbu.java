package sekwah.mods.narutomod.common.entity.ai;

import sekwah.mods.narutomod.common.entity.EntityNinjaVillager;
import net.minecraft.entity.ai.EntityAIBase;
import sekwah.mods.narutomod.common.entity.EntityNinjaVillagerAnbu;

import java.util.Iterator;
import java.util.List;

public class EntityAIFollowAnbu extends EntityAIBase {
    private EntityNinjaVillager theVillager;
    private EntityNinjaVillagerAnbu theAnbu;
    private int followAnbuTick;
    private boolean stopfollowingAnbu = false;

    public EntityAIFollowAnbu(EntityNinjaVillager par1EntityNinjaVillager) {
        this.theVillager = par1EntityNinjaVillager;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (this.theVillager.getGrowingAge() >= 0) {
            return false;
        } else if (!this.theVillager.worldObj.isDaytime()) {
            return false;
        } else {
            List var1 = this.theVillager.worldObj.getEntitiesWithinAABB(EntityNinjaVillagerAnbu.class, this.theVillager.boundingBox.expand(6.0D, 2.0D, 6.0D));

            if (var1.isEmpty()) {
                return false;
            } else {
                Iterator var2 = var1.iterator();

                while (var2.hasNext()) {
                    EntityNinjaVillagerAnbu var3 = (EntityNinjaVillagerAnbu) var2.next();

                    if (var3.followAnbuTick() > 0) {
                        this.theAnbu = var3;
                        break;
                    }
                }

                return this.theAnbu != null;
            }
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        return this.theAnbu.followAnbuTick() > 0;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.followAnbuTick = this.theVillager.getRNG().nextInt(320);
        this.stopfollowingAnbu = false;
        this.theAnbu.getNavigator().clearPathEntity();
    }

    /**
     * Resets the task
     */
    public void resetTask() {
        this.theAnbu = null;
        this.theVillager.getNavigator().clearPathEntity();
    }

    /**
     * Updates the task
     */
    public void updateTask() {
        this.theVillager.getLookHelper().setLookPositionWithEntity(this.theAnbu, 30.0F, 30.0F);

        if (this.theAnbu.followAnbuTick() == this.followAnbuTick) {
            this.theVillager.getNavigator().tryMoveToEntityLiving(this.theAnbu, 0.15F);
            this.stopfollowingAnbu = true;
        }

        if (this.stopfollowingAnbu && this.theVillager.getDistanceSqToEntity(this.theAnbu) < 4.0D) {
            this.theVillager.getNavigator().clearPathEntity();
        }
    }
}