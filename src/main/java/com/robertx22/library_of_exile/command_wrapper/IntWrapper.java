package com.robertx22.library_of_exile.command_wrapper;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;

import static net.minecraft.commands.Commands.argument;

public class IntWrapper extends ArgumentWrapper<Integer> {
    public IntWrapper(String argName) {
        super(argName);
    }

    @Override
    public Integer getter(CommandContext<CommandSourceStack> ctx) {
        return IntegerArgumentType.getInteger(ctx, argName);
    }

    @Override
    public String id() {
        return "number";
    }

    @Override
    public RequiredArgumentBuilder getType() {
        return argument(argName, IntegerArgumentType.integer());
    }


}
