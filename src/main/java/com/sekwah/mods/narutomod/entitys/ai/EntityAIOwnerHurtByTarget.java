package com.sekwah.mods.narutomod.entitys.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import com.sekwah.mods.narutomod.entitys.EntityShadowClone;

public class EntityAIOwnerHurtByTarget extends EntityAITarget {
    EntityShadowClone theDefendingClone;
    EntityLivingBase theOwnerAttacker;
    private int field_142051_e;

    public EntityAIOwnerHurtByTarget(EntityShadowClone par1Entity) {
        super(par1Entity, false);
        this.theDefendingClone = par1Entity;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.theDefendingClone.getMaster();

        if (entitylivingbase == null) {
            return false;
        } else {
            this.theOwnerAttacker = entitylivingbase.getAITarget();
            int i = entitylivingbase.func_142015_aE();
            return i != this.field_142051_e && this.isSuitableTarget(this.theOwnerAttacker, false);
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.taskOwner.setAttackTarget(this.theOwnerAttacker);
        EntityLivingBase entitylivingbase = this.theDefendingClone.getMaster();

        if (entitylivingbase != null) {
            this.field_142051_e = entitylivingbase.func_142015_aE();
        }

        super.startExecuting();
    }
}