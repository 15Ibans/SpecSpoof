package me.ibans.specspoof.commands;

import me.ibans.specspoof.Utils;
import me.ibans.specspoof.Values;
import net.minecraft.client.Minecraft;
import net.minecraft.command.*;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class SetCpuCommand extends CommandBase implements ICommand {
    @Override
    public String getCommandName() {
        return "setcpu";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length <= 0) {
            throw new WrongUsageException("/setcpu <cpu name here>");
        } else {
            String cpuField = Utils.argsToString(args, 0);
            Values.setSpoofedCPU(cpuField);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Set " + EnumChatFormatting.YELLOW + cpuField + EnumChatFormatting.RESET + " as the new \"CPU\"."));
        }
    }
}
