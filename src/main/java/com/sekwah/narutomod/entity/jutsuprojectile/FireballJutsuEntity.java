package com.sekwah.narutomod.entity.jutsuprojectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;

public class FireballJutsuEntity extends LargeFireball {

    public int time;
    private int rainFizz = 20;

    public FireballJutsuEntity(EntityType<? extends LargeFireball> p_37199_, Level p_37200_) {
        super(p_37199_, p_37200_);
        this.time = this.random.nextInt(100000);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.time;
    }
}
