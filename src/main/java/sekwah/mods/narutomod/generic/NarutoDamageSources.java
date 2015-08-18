package sekwah.mods.narutomod.generic;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

/**
 * Created by sekwah on 16/08/2015.
 *
 * Just to keep track of EVERY single different type of damage there is. May be able to code it so if
 */
public class NarutoDamageSources {

    // http://www.minecraftforge.net/forum/index.php?topic=10393.0

    //public DamageSource fireballJutsu = new DamageSource("narutomod.fireball").setFireDamage();

    public static DamageSource causeWaterBullet(Entity p_76354_0_, Entity p_76354_1_)
    {
        return (new EntityDamageSourceIndirect("waterBullet", p_76354_0_, p_76354_1_)).setDamageBypassesArmor().setMagicDamage();
    }

}
