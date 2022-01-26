package com.sekwah.narutomod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.sekwah.narutomod.abilities.Ability;
import com.sekwah.narutomod.abilities.NarutoAbilities;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.KeybindComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceKey;

import java.util.Map;
import java.util.stream.Stream;

public class JutsuCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> jutsu = Commands.literal("jutsu")
                .then(Commands.literal("list").executes(ctx -> {
                    Stream<Map.Entry<ResourceKey<Ability>, Ability>> abilities = NarutoAbilities.ABILITY_REGISTRY.getEntries().stream().filter(entry -> entry.getValue().defaultCombo() != -1);
                    CommandSourceStack source = ctx.getSource();

                    source.sendSuccess(new TranslatableComponent(""),false);

                    abilities.forEach(entry -> {
                        TextComponent comboComponent = new TextComponent("");
                        for (char comboKey: String.valueOf(entry.getValue().defaultCombo()).toCharArray()) {
                            comboComponent.append(" ").append(new KeybindComponent("naruto.keys.key" + comboKey));
                        }

                        source.sendSuccess(new TranslatableComponent(entry.getKey().location().toString())
                                .append(" -")
                                .append(comboComponent),false);
                    });

                    source.sendSuccess(new TranslatableComponent(""),false);

                    return Command.SINGLE_SUCCESS;
                }));

        dispatcher.register(jutsu);
    }

}
