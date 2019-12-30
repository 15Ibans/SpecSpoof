package me.ibans.specspoof.commands;

import me.ibans.specspoof.Utils;
import me.ibans.specspoof.Values;
import net.minecraft.client.Minecraft;
import net.minecraft.command.*;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ModToggleCommand extends CommandBase implements ICommand {

    @Override
    public String getCommandName() {
        return "specspoof";
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
            throw new WrongUsageException("/specspoof <enable/disable>");
        } else {
            String parameter = Utils.argsToString(args, 0);
            if (parameter.equals("enable")) {
                Values.changeStat(true);
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "SpecSpoof enabled."));
            } else if (parameter.equals("disable")) {
                Values.changeStat(false);
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "SpecSpoof disabled."));
            } else {
                throw new WrongUsageException("/specspoof <enable/disable>");
            }
        }
    }
}
