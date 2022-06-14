package com.sekwah.narutomod.entity;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class SubstitutionLogEntity extends Mob {

    private int aliveTicks = 20 * 3;

    public SubstitutionLogEntity(EntityType<SubstitutionLogEntity> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
    }


    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        this.setInvulnerable(true);
        return super.hurt(pSource, pAmount);
    }

    @Override
    public void tick() {
        super.tick();
        this.aliveTicks--;
        if(this.aliveTicks <= 0) {
            this.kill();
        }
    }

    public SubstitutionLogEntity(Level worldIn) {
        this(NarutoEntities.SUBSTITUTION_LOG.get(), worldIn);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.6D).add(Attributes.ATTACK_DAMAGE, 0.0D);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket entityPacket) {
        super.recreateFromPacket(entityPacket);
        this.setYBodyRot(entityPacket.getYRot());
    }
}
