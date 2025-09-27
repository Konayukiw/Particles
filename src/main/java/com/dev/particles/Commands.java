package com.dev.particles;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.Arrays;
import java.util.List;

public class Commands extends CommandBase {
    private static final List<String> SCALES = Arrays.asList(
            "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1",
            "1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9", "2",
            "3", "4", "5", "10"
    );

    @Override
    public String getCommandName() {
        return "particles";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/particles <scale>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.addChatMessage(new ChatComponentText("Usage: " + getCommandUsage(sender)));
            return;
        }

        try {
            float scale = Float.parseFloat(args[0]);
            if (!SCALES.contains(args[0])) {
                sender.addChatMessage(new ChatComponentText("Invalid scale. Available: " + String.join(", ", SCALES)));
                return;
            }
            Configs.setGlobalScale(scale);
            sender.addChatMessage(new ChatComponentText("Particle scale set to " + scale + "x"));
        } catch (NumberFormatException e) {
            sender.addChatMessage(new ChatComponentText("Invalid number."));
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args, net.minecraft.util.BlockPos pos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, SCALES);
        }
        return null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}