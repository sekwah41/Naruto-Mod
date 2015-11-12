package com.sekwah.mods.narutomod.items.dispenserbehavior;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.world.World;
import com.sekwah.mods.narutomod.entitys.projectiles.EntityExplosiveKunai;

public final class DispenserBehaviorExplosiveKunai extends BehaviorProjectileDispense {
    /**
     * Return the projectile entity spawned by this dispense behavior.
     */
    @Override
    protected IProjectile getProjectileEntity(World par1World, IPosition par2IPosition) {
        EntityExplosiveKunai entityarrow = new EntityExplosiveKunai(par1World, par2IPosition.getX(), par2IPosition.getY(), par2IPosition.getZ());
        entityarrow.canBePickedUp = 1;
        return entityarrow;
    }
}
