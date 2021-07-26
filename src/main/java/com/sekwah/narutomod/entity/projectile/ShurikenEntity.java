package com.sekwah.narutomod.entity.projectile;

import com.sekwah.narutomod.entity.NarutoEntities;
import com.sekwah.narutomod.item.NarutoItems;
import com.sekwah.narutomod.sounds.NarutoSounds;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;

public class ShurikenEntity extends AbstractArrow {

    private int prevRotateTicks = 0;

    private int rotateTicks = 0;

    private int rotOffset = 0;

    public ShurikenEntity(EntityType<? extends ShurikenEntity> type, Level worldIn) {
        super(type, worldIn);
        this.rotateTicks = worldIn.random.nextInt(140);
        this.rotOffset = (worldIn.random.nextInt(100) - 50);
    }

    public ShurikenEntity(Level worldIn, LivingEntity shooter) {
        super(NarutoEntities.SHURIKEN.get(), shooter, worldIn);
    }

    public ShurikenEntity(EntityType<? extends ShurikenEntity> kunaiEntityEntityType, LivingEntity shooter, Level worldIn) {
        super(kunaiEntityEntityType, shooter, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        this.prevRotateTicks = this.rotateTicks;
        if (!this.inGround) {
            this.rotateTicks += 1;
        }
    }

    public int getRotateTicks() {
        return rotateTicks;
    }

    public int getPrevRotateTicks() {
        return prevRotateTicks;
    }

    public int getRotOffset() {
        return rotOffset;
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
        return new ItemStack(NarutoItems.SHURIKEN.get());
    }
}
