package com.sekwah.narutomod.entity.projectile;

import com.sekwah.narutomod.sounds.NarutoSounds;
import com.sekwah.narutomod.entity.NarutoEntities;
import com.sekwah.narutomod.item.NarutoItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class KunaiEntity extends AbstractArrowEntity {

    public KunaiEntity(EntityType<? extends KunaiEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public KunaiEntity(World worldIn, LivingEntity shooter) {
        super(NarutoEntities.KUNAI.get(), shooter, worldIn);
    }

    public KunaiEntity(EntityType<? extends KunaiEntity> kunaiEntityEntityType, LivingEntity shooter, World worldIn) {
        super(kunaiEntityEntityType, shooter, worldIn);
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
        return new ItemStack(NarutoItems.KUNAI.get());
    }
}
