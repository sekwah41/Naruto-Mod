package sekwah.mods.narutomod.entitys.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.village.Village;
import sekwah.mods.narutomod.entitys.EntityNinjaVillagerAnbu;

public class EntityAIDefendNinjaVillage extends EntityAITarget {
    EntityNinjaVillagerAnbu villageranbu;

    /**
     * The aggressor of the iron golem's village which is now the golem's attack target.
     */
    EntityLivingBase villageAgressorTarget;

    public EntityAIDefendNinjaVillage(EntityNinjaVillagerAnbu entityNinjaVillagerAnbu) {
        super(entityNinjaVillagerAnbu, false, true);
        this.villageranbu = entityNinjaVillagerAnbu;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        Village var1 = this.villageranbu.getVillage();

        if (var1 == null) {
            return false;
        } else {
            this.villageAgressorTarget = var1.findNearestVillageAggressor(this.villageranbu);

            if (!this.isSuitableTarget(this.villageAgressorTarget, false)) {
                if (this.taskOwner.getRNG().nextInt(20) == 0) {
                    this.villageAgressorTarget = var1.func_82685_c(this.villageranbu);
                    return this.isSuitableTarget(this.villageAgressorTarget, false);
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.villageranbu.setAttackTarget(this.villageAgressorTarget);
        super.startExecuting();
    }
}
