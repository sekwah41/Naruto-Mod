package com.sekwah.narutomod.entity.projectile;

import com.sekwah.narutomod.config.NarutoConfig;
import com.sekwah.narutomod.entity.NarutoEntities;
import com.sekwah.narutomod.item.NarutoItems;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ExplosiveKunaiEntity extends KunaiEntity {

    public ExplosiveKunaiEntity(EntityType<? extends ExplosiveKunaiEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public ExplosiveKunaiEntity(Level worldIn, LivingEntity shooter) {
        super(NarutoEntities.EXPLOSIVE_KUNAI.get(), shooter, worldIn);
    }

    public ExplosiveKunaiEntity(Level level, double x, double y, double z) {
        super(NarutoEntities.EXPLOSIVE_KUNAI.get(), x, y, z, level);
    }


    @Override
    public void tick() {
        super.tick();
        if(!this.level.isClientSide && this.inGroundTime >= 10) {
            explodeKunai(this);
            this.discard();
        }
    }


    @Override
    protected void onHitEntity(EntityHitResult rayTraceResult) {
        super.onHitEntity(rayTraceResult);

        if(!this.level.isClientSide) {
            explodeKunai(this);
            this.discard();
        }
    }

    public static void explodeKunai(Entity entity) {
        entity.level.explode(null, entity.getX(), entity.getY(), entity.getZ(), NarutoConfig.kunaiExplosionRadius,
                NarutoConfig.kunaiBlockDamage ? Explosion.BlockInteraction.BREAK : Explosion.BlockInteraction.NONE);
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return NarutoSounds.KUNAI_THUD.get();
    }

    @Override
    public void setSoundEvent(SoundEvent pSound) {
        super.setSoundEvent(this.getDefaultHitGroundSoundEvent());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(NarutoItems.EXPLOSIVE_KUNAI.get());
    }
}
