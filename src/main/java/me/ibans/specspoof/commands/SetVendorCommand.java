package me.ibans.specspoof.commands;

import me.ibans.specspoof.Utils;
import me.ibans.specspoof.Values;
import net.minecraft.client.Minecraft;
import net.minecraft.command.*;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class SetVendorCommand extends CommandBase implements ICommand {
    @Override
    public String getCommandName() {
        return "setvendor";
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
            throw new WrongUsageException("/setvendor <gpu vendor>");
        } else {
            String gpuVendor = Utils.argsToString(args, 0);
            Values.setSpoofedVendor(gpuVendor);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Set " + EnumChatFormatting.YELLOW + gpuVendor + EnumChatFormatting.RESET + " as the new \"GPU vendor\"."));
        }
    }
}
