package com.sekwah.narutomod.network.c2s;

import com.sekwah.narutomod.NarutoMod;
import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.abilities.NarutoAbilities;
import com.sekwah.narutomod.capabilities.NinjaCapabilityHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

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
                        if(ability.handleCost(player, ninjaData)) {
                            ability.perform(player, ninjaData);
                        }
                    } else if(ability.activationType() == Ability.ActivationType.TOGGLE) {
                        if(ninjaData.getToogleAbilityData().abilities.contains(ability.getRegistryName())) {
                            // Toggle ability off
                            ninjaData.getToogleAbilityData().abilities.remove(ability.getRegistryName());
                        } else {
                            // Toggle ability on
                            ninjaData.getToogleAbilityData().abilities.add(ability.getRegistryName());
                        }
                    }

                    // TODO look into a way to

                });
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
