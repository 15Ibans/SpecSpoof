package me.ibans.specspoof.commands;

import me.ibans.specspoof.Utils;
import me.ibans.specspoof.Values;
import net.minecraft.client.Minecraft;
import net.minecraft.command.*;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class SetGpuCommand extends CommandBase implements ICommand {
    @Override
    public String getCommandName() {
        return "setgpu";
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
            throw new WrongUsageException("/setgpu <gpu name here>");
        } else {
            String gpuField = Utils.argsToString(args, 0);
            Values.setSpoofedGPU(gpuField);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Set " + EnumChatFormatting.YELLOW + gpuField + EnumChatFormatting.RESET + " as the new \"GPU\"."));
        }
    }
}
