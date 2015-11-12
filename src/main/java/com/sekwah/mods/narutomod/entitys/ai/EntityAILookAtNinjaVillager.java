package com.sekwah.mods.narutomod.entitys.ai;

import com.sekwah.mods.narutomod.entitys.EntityNinjaVillager;
import com.sekwah.mods.narutomod.entitys.EntityNinjaVillagerAnbu;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAILookAtNinjaVillager extends EntityAIBase {
    private EntityNinjaVillagerAnbu theAnbu;
    private EntityNinjaVillager theVillager;
    private int lookTime;

    public EntityAILookAtNinjaVillager(EntityNinjaVillagerAnbu entityNinjaVillagerAnbu) {
        this.theAnbu = entityNinjaVillagerAnbu;
        this.setMutexBits(3);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (!this.theAnbu.worldObj.isDaytime()) {
            return false;
        } else if (this.theAnbu.getRNG().nextInt(8000) != 0) {
            return false;
        } else {
            this.theVillager = (EntityNinjaVillager) this.theAnbu.worldObj.findNearestEntityWithinAABB(EntityNinjaVillager.class, this.theAnbu.boundingBox.expand(6.0D, 2.0D, 6.0D), this.theAnbu);
            return this.theVillager != null;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        return this.lookTime > 0;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.lookTime = 400;
    }

    /**
     * Resets the task
     */
    public void resetTask() {
        this.theVillager = null;
    }

    /**
     * Updates the task
     */
    public void updateTask() {
        this.theAnbu.getLookHelper().setLookPositionWithEntity(this.theVillager, 30.0F, 30.0F);
        --this.lookTime;
    }
}