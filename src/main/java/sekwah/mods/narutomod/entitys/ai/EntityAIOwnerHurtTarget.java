package sekwah.mods.narutomod.entitys.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import sekwah.mods.narutomod.entitys.EntityShadowClone;

public class EntityAIOwnerHurtTarget extends EntityAITarget {
    EntityShadowClone theEntityClone;
    EntityLivingBase theTarget;
    private int field_142050_e;

    public EntityAIOwnerHurtTarget(EntityShadowClone par1Entity) {
        super(par1Entity, false);
        this.theEntityClone = par1Entity;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.theEntityClone.func_130012_q();

        if (entitylivingbase == null) {
            return false;
        } else {
            this.theTarget = entitylivingbase.getLastAttacker();
            int i = entitylivingbase.getLastAttackerTime();
            return i != this.field_142050_e && this.isSuitableTarget(this.theTarget, false);
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.taskOwner.setAttackTarget(this.theTarget);
        EntityLivingBase entitylivingbase = this.theEntityClone.func_130012_q();

        if (entitylivingbase != null) {
            this.field_142050_e = entitylivingbase.getLastAttackerTime();
        }

        super.startExecuting();
    }
}
