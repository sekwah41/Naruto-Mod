package com.sekwah.narutomod.capabilities;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.registries.NarutoRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID)
public class NinjaCapabilityHandler {

    public static final Capability<INinjaData> NINJA_DATA = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(NarutoMod.MOD_ID, "ninja_data"), new NinjaData(!event.getObject().level.isClientSide));
        }
    }

    @SubscribeEvent
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        if(event.phase.equals(TickEvent.Phase.END)) {
            Player player = event.player;
            player.getCapability(NINJA_DATA).ifPresent(data -> {
                if(event.side.isServer()) {
                    if(player.isCreative()) {
                        data.setChakra(data.getMaxChakra());
                        data.setStamina(data.getMaxStamina());
                    }
                    data.updateDataServer(player);
                    if(!player.isSpectator()) {
                        data.getToggleAbilityData().getAbilitiesHashSet().forEach(abilityName -> {
                            Ability ability = NarutoRegistries.ABILITIES.getValue(abilityName);
                            if(ability.handleCost(player, data)) {
                                ability.performServer(player, data);
                            } else {
                                data.getToggleAbilityData().removeAbilityEnded(player, data, ability);
                            }
                        });
                    }
                } else {
                    data.updateDataClient(player);
                    if(!player.isSpectator()) {
                        data.getToggleAbilityData().getAbilitiesHashSet().forEach(abilityName -> {
                            Ability ability = NarutoRegistries.ABILITIES.getValue(abilityName);
                            if(ability instanceof Ability.Toggled toggleAbility) toggleAbility.performToggleClient(player, data);
                        });
                    }
                }
            });
        }

    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone event) {
        event.getOriginal().reviveCaps();
        event.getOriginal().getCapability(NINJA_DATA).ifPresent(original -> {
            event.getPlayer().getCapability(NINJA_DATA).ifPresent(future -> {
                future.deserializeNBT(original.serializeNBT());
            });
        });
    }

    //public static final EntitySize STANDING_SIZE = EntitySize.flexible(0.1F, 0.1F);

    // For things like the transformation jutsus (make sure to trigger it manually when you change)
/*    @SubscribeEvent
    public static void playerSize(EntityEvent.Size event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            event.setNewEyeHeight(5f);
            event.setNewSize(EntityDimensions.scalable(10F, 10F));
        }
    }*/
}
