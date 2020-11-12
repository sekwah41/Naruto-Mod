package com.sekwah.narutomod.entity;

import com.sekwah.narutomod.entity.projectile.KunaiEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

public class NarutoEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MOD_ID);

    public static final RegistryObject<EntityType<KunaiEntity>> KUNAI =register("kunai",
            EntityType.Builder.<KunaiEntity>create(KunaiEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).trackingRange(4).func_233608_b_(20));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String key, EntityType.Builder<T> builder) {
        return ENTITIES.register(key, () -> builder.build(key));
    }

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

}
