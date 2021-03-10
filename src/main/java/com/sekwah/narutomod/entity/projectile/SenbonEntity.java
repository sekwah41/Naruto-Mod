package com.sekwah.narutomod.entity.projectile;

import com.sekwah.narutomod.entity.NarutoEntities;
import com.sekwah.narutomod.item.NarutoItems;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SenbonEntity extends AbstractArrowEntity {

    public SenbonEntity(EntityType<? extends SenbonEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public SenbonEntity(World worldIn, LivingEntity shooter) {
        super(NarutoEntities.SENBON.get(), shooter, worldIn);
    }

    public SenbonEntity(EntityType<? extends SenbonEntity> kunaiEntityEntityType, LivingEntity shooter, World worldIn) {
        super(kunaiEntityEntityType, shooter, worldIn);
    }

    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return NarutoSounds.NEEDLE_HIT.get();
    }


    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(NarutoItems.SENBON.get());
    }
}
