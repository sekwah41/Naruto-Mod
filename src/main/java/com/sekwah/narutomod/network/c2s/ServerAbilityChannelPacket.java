package com.sekwah.narutomod.network.c2s;

import com.mojang.logging.LogUtils;
import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import com.sekwah.narutomod.gameevents.NarutoGameEvents;
import com.sekwah.narutomod.registries.NarutoRegistries;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.GameType;
import net.minecraftforge.network.NetworkEvent;
import org.slf4j.Logger;

import java.util.function.Supplier;

/**
 * Tell the server that the user wants to cast a specific ability.
 */
public class ServerAbilityChannelPacket {

    private static final Logger LOGGER = LogUtils.getLogger();

    private final ResourceLocation abilityResource;
    private final ChannelStatus status;

    public ServerAbilityChannelPacket(ResourceLocation abilityResource, ChannelStatus status) {
        this.abilityResource = abilityResource;
        this.status = status;
    }

    public enum ChannelStatus {
        START,
        STOP,
        MIN_ACTIVATE
    }

    public static void encode(ServerAbilityChannelPacket msg, FriendlyByteBuf outBuffer) {
        outBuffer.writeResourceLocation(msg.abilityResource);
        outBuffer.writeInt(msg.status.ordinal());
    }

    public static ServerAbilityChannelPacket decode(FriendlyByteBuf inBuffer) {
        ResourceLocation abilityResource = inBuffer.readResourceLocation();
        int status = inBuffer.readInt();
        return new ServerAbilityChannelPacket(abilityResource, ChannelStatus.values()[status]);
    }

    public static class Handler {
        public static void handle(ServerAbilityChannelPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                final ServerPlayer player = ctx.get().getSender();
                if(player == null || player.gameMode.getGameModeForPlayer() == GameType.SPECTATOR) {
                    return;
                }
                player.getCapability(NinjaCapabilityHandler.NINJA_DATA).ifPresent(ninjaData -> {
                    Ability ability = NarutoRegistries.ABILITIES.getValue(msg.abilityResource);
                    if(ability == null) {
                        LOGGER.error("Ability doesnt exist {}", msg.abilityResource);
                        return;
                    }
                    // Just check if its
                    if (ability.activationType() == Ability.ActivationType.CHANNELED) {
                        if (msg.status == ChannelStatus.START) {
                            ninjaData.setCurrentlyChanneledAbility(player, ability);
                        } else if (msg.status == ChannelStatus.STOP) {
                            NarutoRegistries.ABILITIES.getResourceKey(ability).ifPresent(resourceKey -> {
                                if(ninjaData.getCurrentlyChanneledAbility().equals(resourceKey.location())) {
                                    ability.performServer(player, ninjaData, ninjaData.getCurrentlyChanneledTicks());
                                    ninjaData.setCurrentlyChanneledAbility(player, null);
                                }
                            });
                        } else if(msg.status == ChannelStatus.MIN_ACTIVATE) {
                            if (ability instanceof Ability.Channeled channeled && channeled.canActivateBelowMinCharge()) {
                                if(ability.handleCost(player, ninjaData, -1)) {
                                    if (ability.castingSound() != null) {
                                        player.getLevel().playSound(null, player, ability.castingSound(), SoundSource.PLAYERS, 0.5f, 1.0f);

                                        player.getLevel().gameEvent(player, NarutoGameEvents.JUTSU_CASTING.get(), player.position().add(0, player.getEyeHeight() * 0.7, 0));
                                    }
                                    player.displayClientMessage(Component.translatable("jutsu.cast", Component.translatable(ability.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GREEN), true);
                                    ability.performServer(player, ninjaData, -1);
                                }
                            } else {
                                player.displayClientMessage(Component.translatable("jutsu.channel.needed", Component.translatable(ability.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.RED), true);
                            }
                        }
                    }
                });
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
