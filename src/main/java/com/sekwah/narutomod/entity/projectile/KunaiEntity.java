package com.sekwah.narutomod.entity.projectile;

import com.sekwah.narutomod.entity.NarutoEntities;
import com.sekwah.narutomod.item.NarutoItems;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;

public class KunaiEntity extends AbstractArrow {

    public KunaiEntity(EntityType<? extends KunaiEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public KunaiEntity(Level worldIn, LivingEntity shooter) {
        super(NarutoEntities.KUNAI.get(), shooter, worldIn);
    }

    public KunaiEntity(EntityType<? extends KunaiEntity> kunaiEntityEntityType, LivingEntity shooter, Level worldIn) {
        super(kunaiEntityEntityType, shooter, worldIn);
    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return NarutoSounds.KUNAI_THUD.get();
    }


    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(NarutoItems.KUNAI.get());
    }
}
