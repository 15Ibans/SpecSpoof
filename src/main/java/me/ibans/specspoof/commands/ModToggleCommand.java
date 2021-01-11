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
        boolean toggled = !Values.isEnabled();
        Values.setEnabled(toggled);
        if (toggled) {
            Utils.sendMessage("&aSpecSpoof enabled");
        } else {
            Utils.sendMessage("&cSpecSpoof disabled");
        }
    }
}
