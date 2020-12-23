package com.sekwah.narutomod.entity.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class PaperBombEntity extends TNTEntity {

    public PaperBombEntity(EntityType<? extends PaperBombEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public PaperBombEntity(World worldIn, double x, int y, double z, LivingEntity igniter) {
        super(worldIn, x, y, z, igniter);
    }
    
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
