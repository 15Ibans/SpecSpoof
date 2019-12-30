package me.ibans.specspoof.commands;

import me.ibans.specspoof.Utils;
import me.ibans.specspoof.Values;
import net.minecraft.client.Minecraft;
import net.minecraft.command.*;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class SetGpuVersionCommand extends CommandBase implements ICommand {

    @Override
    public String getCommandName() {
        return "setgpuversion";
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
            throw new WrongUsageException("/setgpuversion <gpu vendor>");
        } else {
            String gpuVersion = Utils.argsToString(args, 0);
            Values.setSpoofedGPUVersion(gpuVersion);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Set " + EnumChatFormatting.YELLOW + gpuVersion + EnumChatFormatting.RESET + " as the new \"GPU version\"."));
        }
    }
}
