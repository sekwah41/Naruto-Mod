package com.sekwah.narutomod.entity.projectile;

import com.sekwah.narutomod.config.NarutoConfig;
import com.sekwah.narutomod.entity.NarutoEntities;
import com.sekwah.narutomod.item.NarutoItems;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ExplosiveKunaiEntity extends KunaiEntity {

    public ExplosiveKunaiEntity(EntityType<? extends ExplosiveKunaiEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ExplosiveKunaiEntity(World worldIn, LivingEntity shooter) {
        super(NarutoEntities.EXPLOSIVE_KUNAI.get(), shooter, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if(!this.world.isRemote && this.timeInGround >= 10) {
            explodeKunai(this);
            this.setDead();
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult rayTraceResult) {
        super.onEntityHit(rayTraceResult);

        if(!this.world.isRemote)
            explodeKunai(this);
            this.setDead();
    }

    public static void explodeKunai(Entity entity) {
        entity.world.createExplosion(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), NarutoConfig.KUNAI_EXPLOSION_RADIUS,
                NarutoConfig.KUNAI_BLOCK_DAMAGE ? Explosion.Mode.BREAK : Explosion.Mode.NONE);
    }

    @Override
    protected SoundEvent getHitEntitySound() {
        return NarutoSounds.KUNAI_THUD.get();
    }


    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(NarutoItems.EXPLOSIVE_KUNAI.get());
    }
}
