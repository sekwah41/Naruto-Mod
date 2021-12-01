package com.sekwah.narutomod.entity.projectile;

import com.sekwah.narutomod.entity.NarutoEntities;
import com.sekwah.narutomod.item.NarutoItems;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class SenbonEntity extends AbstractArrow {

    public SenbonEntity(EntityType<? extends SenbonEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public SenbonEntity(Level worldIn, LivingEntity shooter) {
        super(NarutoEntities.SENBON.get(), shooter, worldIn);
    }

    public SenbonEntity(EntityType<? extends SenbonEntity> kunaiEntityEntityType, LivingEntity shooter, Level worldIn) {
        super(kunaiEntityEntityType, shooter, worldIn);
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return NarutoSounds.NEEDLE_HIT.get();
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void setSoundEvent(SoundEvent pSound) {
        super.setSoundEvent(this.getDefaultHitGroundSoundEvent());
    }


    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(NarutoItems.SENBON.get());
    }
}
