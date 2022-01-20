package com.sekwah.narutomod.abilities;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.abilities.jutsus.FireballJutsuAbility;
import com.sekwah.narutomod.abilities.jutsus.WaterBulletJutsuAbility;
import com.sekwah.narutomod.abilities.utility.*;
import com.sekwah.narutomod.network.PacketHandler;
import com.sekwah.narutomod.network.c2s.ServerAbilityActivatePacket;
import com.sekwah.narutomod.network.c2s.ServerAbilityChannelPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

import static com.sekwah.narutomod.NarutoMod.MOD_ID;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NarutoAbilities {

    public static final DeferredRegister<Ability> ABILITY = DeferredRegister.create(Ability.class, MOD_ID);

    public static final Map<Long, ResourceLocation> COMBO_MAP = new HashMap<>();

    public static ForgeRegistry<Ability> ABILITY_REGISTRY;

    public static final RegistryObject<LeapAbility> LEAP = ABILITY.register("leap", LeapAbility::new);

    //public static final RegistryObject<ChakraDashAbility> CHAKRA_DASH = ABILITY.register("chakra_dash", ChakraDashAbility::new);

    public static final RegistryObject<WaterWalkAbility> WATER_WALK = ABILITY.register("water_walk", WaterWalkAbility::new);

    public static final RegistryObject<FireballJutsuAbility> FIREBALL = ABILITY.register("fireball", FireballJutsuAbility::new);

    public static final RegistryObject<WaterBulletJutsuAbility> WATER_BULLET = ABILITY.register("water_bullet", WaterBulletJutsuAbility::new);

    public static final RegistryObject<ChakraChargeAbility> CHAKRA_CHARGE = ABILITY.register("chakra_charge", ChakraChargeAbility::new);

    public static final RegistryObject<DoubleJumpAbility> DOUBLE_JUMP = ABILITY.register("double_jump", DoubleJumpAbility::new);

    public static void register(IEventBus eventBus) {
        ABILITY.register(eventBus);
    }

    @SubscribeEvent
    public static void getRegistryObject(RegistryEvent.Register<Ability> event) {
        ABILITY_REGISTRY = (ForgeRegistry<Ability>) event.getRegistry();
    }

    /**
     * May change how key combos are handled in the future but these will be default
     */
    public static void registerKeyCombos() {
        ABILITY.getEntries().forEach(abilityEntry -> {
            Ability ability = abilityEntry.get();
            long combo = ability.defaultCombo();
            if (combo > 0) {
                if(COMBO_MAP.containsKey(combo)) {
                    NarutoMod.LOGGER.error("Ability already registered with that combo {}", combo);
                } else {
                    COMBO_MAP.put(combo, ability.getRegistryName());
                }
            }
        });
    }

    /**
     * Send to the server that the player wants to use a specific ability
     */
    public static void triggerAbility(ResourceLocation ability) {
        PacketHandler.sendToServer(new ServerAbilityActivatePacket(ability));
    }

    public static Ability getAbilityFromCombo(long combo) {
        if(COMBO_MAP.containsKey(combo)) {
            return ABILITY_REGISTRY.getValue(COMBO_MAP.get(combo));
        } else {
            return null;
        }
    }

    public static boolean handleCharging(long combo, ServerAbilityChannelPacket.ChannelStatus channelStatus) {
        if(COMBO_MAP.containsKey(combo)) {
            ResourceLocation abilityResource = COMBO_MAP.get(combo);
            PacketHandler.sendToServer(new ServerAbilityChannelPacket(abilityResource, channelStatus));
            return true;
        } else {
            return false;
        }
    }

    public static boolean triggerAbility(long combo) {
        if(COMBO_MAP.containsKey(combo)) {
            triggerAbility(COMBO_MAP.get(combo));
            return true;
        } else {
            return false;
        }
    }


    @SubscribeEvent
    public static void clientSetup(FMLCommonSetupEvent event) {
        registerKeyCombos();
    }
}
