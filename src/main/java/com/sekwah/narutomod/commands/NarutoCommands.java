package com.sekwah.narutomod.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;

public class NarutoCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        JutsuCommand.register(dispatcher);
    }

}
