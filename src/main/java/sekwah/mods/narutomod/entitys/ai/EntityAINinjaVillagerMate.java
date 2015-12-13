package sekwah.mods.narutomod.entitys.ai;

import sekwah.mods.narutomod.entitys.EntityNinjaVillager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityAINinjaVillagerMate extends EntityAIBase {
    Village villageObj;
    private EntityNinjaVillager villagerObj;
    private EntityNinjaVillager mate;
    private World worldObj;
    private int matingTimeout = 0;

    public EntityAINinjaVillagerMate(EntityNinjaVillager par1EntityNinjaVillager) {
        this.villagerObj = par1EntityNinjaVillager;
        this.worldObj = par1EntityNinjaVillager.worldObj;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (this.villagerObj.getGrowingAge() != 0) {
            return false;
        } else if (this.villagerObj.getRNG().nextInt(500) != 0) {
            return false;
        } else {
            this.villageObj = this.worldObj.villageCollectionObj.findNearestVillage(MathHelper.floor_double(this.villagerObj.posX), MathHelper.floor_double(this.villagerObj.posY), MathHelper.floor_double(this.villagerObj.posZ), 0);

            if (this.villageObj == null) {
                return false;
            } else if (!this.checkSufficientDoorsPresentForNewVillager()) {
                return false;
            } else {
                Entity var1 = this.worldObj.findNearestEntityWithinAABB(EntityNinjaVillager.class, this.villagerObj.boundingBox.expand(8.0D, 3.0D, 8.0D), this.villagerObj);

                if (var1 == null) {
                    return false;
                } else {
                    this.mate = (EntityNinjaVillager) var1;
                    return this.mate.getGrowingAge() == 0;
                }
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.matingTimeout = 300;
        this.villagerObj.setMating(true);
    }

    /**
     * Resets the task
     */
    public void resetTask() {
        this.villageObj = null;
        this.mate = null;
        this.villagerObj.setMating(false);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        return this.matingTimeout >= 0 && this.checkSufficientDoorsPresentForNewVillager() && this.villagerObj.getGrowingAge() == 0;
    }

    /**
     * Updates the task
     */
    public void updateTask() {
        --this.matingTimeout;
        this.villagerObj.getLookHelper().setLookPositionWithEntity(this.mate, 10.0F, 30.0F);

        if (this.villagerObj.getDistanceSqToEntity(this.mate) > 2.25D) {
            this.villagerObj.getNavigator().tryMoveToEntityLiving(this.mate, 0.25F);
        } else if (this.matingTimeout == 0 && this.mate.isMating()) {
            this.giveBirth();
        }

        if (this.villagerObj.getRNG().nextInt(35) == 0) {
            this.worldObj.setEntityState(this.villagerObj, (byte) 12);
        }
    }

    private boolean checkSufficientDoorsPresentForNewVillager() {
        if (!this.villageObj.isMatingSeason()) {
            return false;
        } else {
            int var1 = (int) ((double) ((float) this.villageObj.getNumVillageDoors()) * 0.35D);
            return this.villageObj.getNumVillagers() < var1;
        }
    }

    private void giveBirth() {
        EntityNinjaVillager var1 = this.villagerObj.func_90012_b(this.mate);
        this.mate.setGrowingAge(6000);
        this.villagerObj.setGrowingAge(6000);
        var1.setGrowingAge(-24000);
        var1.setLocationAndAngles(this.villagerObj.posX, this.villagerObj.posY, this.villagerObj.posZ, 0.0F, 0.0F);
        this.worldObj.spawnEntityInWorld(var1);
        this.worldObj.setEntityState(var1, (byte) 12);
    }
}
