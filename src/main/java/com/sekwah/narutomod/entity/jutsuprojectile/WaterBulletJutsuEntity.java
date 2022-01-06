package com.sekwah.narutomod.entity.jutsuprojectile;

import com.sekwah.narutomod.damagesource.NarutoDamageSource;
import com.sekwah.narutomod.entity.NarutoEntities;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class WaterBulletJutsuEntity extends AbstractHurtingProjectile {

    public int time;
    // Ticks in rain before destroying
    public int lifeSpan = 70;

    public WaterBulletJutsuEntity(EntityType<WaterBulletJutsuEntity> entityConstructor, Level level) {
        super(entityConstructor, level);
        this.time = 0;
    }

    public WaterBulletJutsuEntity(EntityType<? extends AbstractHurtingProjectile> p_36817_, double p_36818_, double p_36819_, double p_36820_, double p_36821_, double p_36822_, double p_36823_, Level p_36824_) {
        super(p_36817_, p_36824_);
        this.moveTo(p_36818_, p_36819_, p_36820_, this.getYRot(), this.getXRot());
        this.reapplyPosition();
        double d0 = Math.sqrt(p_36821_ * p_36821_ + p_36822_ * p_36822_ + p_36823_ * p_36823_);
        if (d0 != 0.0D) {
            this.xPower = p_36821_ / d0 * 0.2D;
            this.yPower = p_36822_ / d0 * 0.2D;
            this.zPower = p_36823_ / d0 * 0.2D;
        }

    }

    public WaterBulletJutsuEntity(LivingEntity player, double xVel, double yVel, double zVel) {
        this(NarutoEntities.WATER_BULLET_JUTSU.get(), player.getX(), player.getEyeY() - 0.6f, player.getZ(),  xVel, yVel, zVel, player.getLevel());
        this.setOwner(player);
        this.setRot(player.getYRot(), player.getXRot());
    }

    @Override
    public void tick() {
        super.tick();
        ++this.time;

        if (lifeSpan-- <= 0) {

            if(this.level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.SPLASH,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        100,
                        3, 3, 3, 1);
            }
            this.playSound(NarutoSounds.WATER_BULLET_SPLASH.get(), 3f, 1.0f);
            this.discard();
        }
        if (this.isInWater()) {
            Vec3 vec3 = this.getDeltaMovement();
            // To counteract the slowdown by water in super code
            this.setDeltaMovement(vec3.add(this.xPower, this.yPower, this.zPower).scale(1f));
        }
    }

    protected void onHitEntity(EntityHitResult p_37216_) {
        super.onHitEntity(p_37216_);
        if (!this.level.isClientSide) {
            Entity entity = p_37216_.getEntity();
            Entity entity1 = this.getOwner();
            entity.hurt(NarutoDamageSource.causeWaterBullet(this, entity1), 7.0F);
            if (entity1 instanceof LivingEntity) {
                this.doEnchantDamageEffects((LivingEntity)entity1, entity);
            }

        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);

        if(this.level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.SPLASH,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    100,
                    this.getBbWidth(), this.getBbWidth(), this.getBbHeight(), 1);

            serverLevel.sendParticles(ParticleTypes.SPLASH,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    100,
                    3, 3, 3, 1);
        }

        if (!this.level.isClientSide) {

            int splashRadius = 8;
            this.level.getEntities(this, this.getBoundingBox().inflate(splashRadius, splashRadius, splashRadius)).forEach(entity -> {
                double distance = this.position().distanceToSqr(entity.position());
                // Remember increasing the division reduces the falloff (I keep accidentally moving it the wrong way)
                if(entity != this.getOwner()) {
                    float damage = (float) (5f - (distance / 4f));
                    if(damage > 0) {
                        Entity entity1 = this.getOwner();
                        entity.hurt(NarutoDamageSource.causeWaterBullet(this, entity1), damage);
                        if (entity1 instanceof LivingEntity) {
                            this.doEnchantDamageEffects((LivingEntity)entity1, entity);
                        }
                    }
                }
                entity.clearFire();
            });

            boolean flag = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getOwner());

            if(flag) {
                int extinguish = 2;
                for (int x = (int) this.getX() - extinguish; x < (int) this.getX() + extinguish - 1; x++) {
                    for (int y = (int) this.getY() - extinguish + 1; y < (int) this.getY() + extinguish; y++) {
                        for (int z = (int) this.getZ() - extinguish + 1; z < (int) this.getZ() + extinguish; z++) {
                            BlockPos blockPos = new BlockPos(x, y, z);
                            if (this.level.getBlockState(blockPos).getBlock() == Blocks.FIRE) {
                                this.level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
                            }
                        }
                    }
                }
            }

            this.playSound(NarutoSounds.WATER_BULLET_SPLASH.get(), 3f, 1.0f);

            this.discard();
        }

    }

    @Override
    public boolean hurt(DamageSource p_36910_, float p_36911_) {
        return false;
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.SPLASH;
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    public void recreateFromPacket(ClientboundAddEntityPacket p_150128_) {
        super.recreateFromPacket(p_150128_);
        // To counteract weird tracking delay
        if(this.getOwner() != null) {
            this.setYRot(this.getOwner().getYRot() - 180);
            this.yRotO = this.getYRot();
        }
        double d0 = p_150128_.getXa();
        double d1 = p_150128_.getYa();
        double d2 = p_150128_.getZa();
        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
        if (d3 != 0.0D) {
            this.xPower = d0 / d3 * 0.2D;
            this.yPower = d1 / d3 * 0.2D;
            this.zPower = d2 / d3 * 0.2D;
        }

    }
}
