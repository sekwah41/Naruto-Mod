package com.sekwah.narutomod.network.c2s;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.abilities.NarutoAbilities;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.capabilities.toggleabilitydata.ToggleAbilityData;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashSet;
import java.util.function.Supplier;

/**
 * Tell the server that the user wants to cast a specific ability.
 */
public class ServerAbilityActivatePacket {

    private final int abilityId;

    public ServerAbilityActivatePacket(ResourceLocation ability) {
        this.abilityId = NarutoAbilities.ABILITY_REGISTRY.getID(ability);
    }

    public ServerAbilityActivatePacket(int abilityId) {
        this.abilityId = abilityId;
    }

    public static void encode(ServerAbilityActivatePacket msg, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt(msg.abilityId);
    }

    public static ServerAbilityActivatePacket decode(FriendlyByteBuf inBuffer) {
        int abilityId = inBuffer.readInt();
        return new ServerAbilityActivatePacket(abilityId);
    }

    public static class Handler {
        public static void handle(ServerAbilityActivatePacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                final ServerPlayer player = ctx.get().getSender();
                player.getCapability(NinjaCapabilityHandler.NINJA_DATA).ifPresent(ninjaData -> {

                    Ability ability = NarutoAbilities.ABILITY_REGISTRY.getValue(msg.abilityId);
                    if (ability.activationType() == Ability.ActivationType.INSTANT) {

                        boolean canTriggerJutsu = true;
                        if (ability  instanceof Ability.Cooldown) {
                            canTriggerJutsu = !((Ability.Cooldown) ability).checkCooldown(player, ninjaData, ability.getTranslationKey());
                        }

                        if(canTriggerJutsu && ability.handleCost(player, ninjaData)) {
                            if (ability.logInChat()) {
                                player.sendMessage(new TranslatableComponent("jutsu.cast", new TranslatableComponent(ability.getTranslationKey()).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GREEN), player.getUUID());
                            }
                            if(ability.castingSound() != null) {
                                player.getLevel().playSound(null,
                                        player, ability.castingSound(), SoundSource.PLAYERS, 0.5f, 1.0f);
                            }
                            ability.performServer(player, ninjaData);

                            if (ability  instanceof Ability.Cooldown) {
                               ((Ability.Cooldown) ability).registerCooldown(ninjaData, ability.getTranslationKey());
                            }
                        }
                    } else if(ability.activationType() == Ability.ActivationType.TOGGLE) {
                        ToggleAbilityData abilityTracker = ninjaData.getToggleAbilityData();
                        HashSet<ResourceLocation> abilities = abilityTracker.getAbilitiesHashSet();
                        if(abilities.contains(ability.getRegistryName())) {
                            // Toggle ability off
                            abilityTracker.removeAbilityEnded(player, ninjaData, ability);
                        } else {
                            // Toggle ability on
                            abilityTracker.addAbilityStarted(player, ninjaData, ability);
                        }
                    }
                });
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
