package com.sekwah.narutomod.block;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class NarutoMaterial {

    /**
     * Can be broken by pistons
     */
    public static final Material UNSTABLE_EXPLOSIVE = (new Material.Builder(MaterialColor.FIRE)).noCollider().flammable().notSolidBlocking().nonSolid().destroyOnPush().build();

}
