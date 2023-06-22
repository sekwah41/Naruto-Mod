package com.sekwah.narutomod.entity.projectile;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public abstract class NoArrowAbstractArrow extends AbstractArrow {

    protected NoArrowAbstractArrow(EntityType<? extends AbstractArrow> p_36721_, Level p_36722_) {
        super(p_36721_, p_36722_);
    }

    protected NoArrowAbstractArrow(EntityType<? extends AbstractArrow> p_36711_, double p_36712_, double p_36713_, double p_36714_, Level p_36715_) {
        this(p_36711_, p_36715_);
        this.setPos(p_36712_, p_36713_, p_36714_);
    }

    protected NoArrowAbstractArrow(EntityType<? extends AbstractArrow> p_36717_, LivingEntity p_36718_, Level p_36719_) {
        super(p_36717_, p_36718_, p_36719_);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        // I want to write as little as possible, no point in re-implementing the hit behavior
        Entity entity = pResult.getEntity();
        if (entity instanceof LivingEntity livingentity) {
            if (!this.level().isClientSide && this.getPierceLevel() <= 0) {
                if(livingentity.getArrowCount() > 0) {
                    livingentity.setArrowCount(livingentity.getArrowCount() - 1);
                }
            }
        }
    }

}
