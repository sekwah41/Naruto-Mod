package sekwah.mods.narutomod.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import sekwah.mods.narutomod.common.entity.EntityNinjaVillager;

public class EntityAINinjaTradePlayer extends EntityAIBase {
    private EntityNinjaVillager villager;

    public EntityAINinjaTradePlayer(EntityNinjaVillager par1EntityNinjaVillager) {
        this.villager = par1EntityNinjaVillager;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (!this.villager.isEntityAlive()) {
            return false;
        } else if (this.villager.isInWater()) {
            return false;
        } else if (!this.villager.onGround) {
            return false;
        } else if (this.villager.velocityChanged) {
            return false;
        } else {
            EntityPlayer var1 = this.villager.getCustomer();
            return var1 == null ? false : (this.villager.getDistanceSqToEntity(var1) > 16.0D ? false : var1.openContainer instanceof Container);
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.villager.getNavigator().clearPathEntity();
    }

    /**
     * Resets the task
     */
    public void resetTask() {
        this.villager.setCustomer(null);
    }
}
