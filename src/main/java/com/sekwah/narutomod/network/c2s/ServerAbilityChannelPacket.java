package com.sekwah.narutomod.network.c2s;

import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.abilities.NarutoAbilities;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.GameType;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Tell the server that the user wants to cast a specific ability.
 */
public class ServerAbilityChannelPacket {

    private final int abilityId;
    private ChannelStatus status;

    public enum ChannelStatus {
        START,
        STOP,
        MIN_ACTIVATE
    }

    public ServerAbilityChannelPacket(ResourceLocation ability, ChannelStatus status) {
        this.abilityId = NarutoAbilities.ABILITY_REGISTRY.getID(ability);
        this.status = status;
    }

    public ServerAbilityChannelPacket(int abilityId, ChannelStatus status) {
        this.abilityId = abilityId;
        this.status = status;
    }

    public static void encode(ServerAbilityChannelPacket msg, FriendlyByteBuf outBuffer) {
        outBuffer.writeInt(msg.abilityId);
        outBuffer.writeInt(msg.status.ordinal());
    }

    public static ServerAbilityChannelPacket decode(FriendlyByteBuf inBuffer) {
        int abilityId = inBuffer.readInt();
        int status = inBuffer.readInt();
        return new ServerAbilityChannelPacket(abilityId, ChannelStatus.values()[status]);
    }

    public static class Handler {
        public static void handle(ServerAbilityChannelPacket msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                final ServerPlayer player = ctx.get().getSender();
                if(player != null && player.gameMode.getGameModeForPlayer() == GameType.SPECTATOR) {
                    return;
                }
                player.getCapability(NinjaCapabilityHandler.NINJA_DATA).ifPresent(ninjaData -> {

                    Ability ability = NarutoAbilities.ABILITY_REGISTRY.getValue(msg.abilityId);
                    // Just check if its
                    if (ability.activationType() == Ability.ActivationType.CHANNELED) {
                        if (msg.status == ChannelStatus.START) {
                            ninjaData.setCurrentlyChanneledAbility(player, ability);
                        } else if (msg.status == ChannelStatus.STOP) {
                            if(ninjaData.getCurrentlyChanneledAbility().equals(ability.getRegistryName())) {
                                ability.performServer(player, ninjaData, ninjaData.getCurrentlyChanneledTicks());
                                ninjaData.setCurrentlyChanneledAbility(player, null);
                            }
                        } else if(msg.status == ChannelStatus.MIN_ACTIVATE) {
                            if (ability instanceof Ability.Channeled channeled && channeled.canActivateBelowMinCharge()) {
                                if(ability.handleCost(player, ninjaData, 0)) {
                                    if (ability.castingSound() != null) {
                                        player.getLevel().playSound(null, player, ability.castingSound(), SoundSource.PLAYERS, 0.5f, 1.0f);
                                    }
                                    player.sendMessage(new TranslatableComponent("jutsu.cast", new TranslatableComponent(ability.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.GREEN), player.getUUID());
                                    ability.performServer(player, ninjaData, 0);
                                }
                            } else {
                                player.sendMessage(new TranslatableComponent("jutsu.channel.needed", new TranslatableComponent(ability.getTranslationKey(ninjaData)).withStyle(ChatFormatting.YELLOW)).withStyle(ChatFormatting.RED), player.getUUID());
                            }
                        }
                    }
                });
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
