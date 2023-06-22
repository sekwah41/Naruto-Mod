package com.sekwah.narutomod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.registries.NarutoRegistries;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;

import java.util.Map;
import java.util.stream.Stream;

public class JutsuCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> jutsu = Commands.literal("jutsu")
                .then(Commands.literal("list").executes(ctx -> {
                    Stream<Map.Entry<ResourceKey<Ability>, Ability>> abilities = NarutoRegistries.ABILITIES.getEntries().stream().filter(entry -> entry.getValue().defaultCombo() != -1);
                    CommandSourceStack source = ctx.getSource();

                    source.sendSuccess(Component::empty,false);

                    abilities.forEach(entry -> {
                        var comboComponent = Component.literal("");
                        for (char comboKey: String.valueOf(entry.getValue().defaultCombo()).toCharArray()) {
                            comboComponent.append(" ").append(Component.keybind("naruto.keys.key" + comboKey));
                        }

                        source.sendSuccess(() -> Component.translatable(entry.getKey().location().toString())
                                .append(" -")
                                .append(comboComponent), false);
                    });
                    source.sendSuccess(() -> Component.translatable("naruto.keys.leap")
                            .append(" - ")
                            .append(Component.keybind("naruto.keys.leap")), false);
                    source.sendSuccess(Component::empty, false);

                    return Command.SINGLE_SUCCESS;
                }));

        dispatcher.register(jutsu);
    }

}
