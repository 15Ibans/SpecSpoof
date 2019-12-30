package me.ibans.specspoof.commands;

import me.ibans.specspoof.Profile;
import me.ibans.specspoof.SpecSpoof;
import me.ibans.specspoof.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.*;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

//// to do:
//- finish delete function
//- make it so it detects if the file doesn't save

public class SpecProfileCommand extends CommandBase implements ICommand {
    @Override
    public String getCommandName() {
        return "specprofile";
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
            throw new WrongUsageException("/specprofile <load/save/delete/list> <profileName>");
        } else if (args.length == 1 && args[0].equals("list")) {
            Profile profile = new Profile(SpecSpoof.configFolder + "\\profiles");
            List<String> profileNames = Utils.listFilesInDirectory(profile.saveDir, ".profile");
            for (int i = 0; i < profileNames.size(); i++) {
                String currentValue = profileNames.get(i);
                profileNames.set(i, currentValue.substring(0, currentValue.length() - 8));
            }
            if (profileNames.size() == 0) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "No spec profiles are currently saved."));
            } else {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Current saved spec profiles:"));
                profileNames.forEach(p -> Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("- " + EnumChatFormatting.YELLOW + p)));
            }
        } else if (args.length >= 2 && args[0].equals("load")) {
            Profile profile = new Profile(SpecSpoof.configFolder + "\\profiles");
            String profileName = Utils.argsToString(args, 1);
            profile.loadProfile(profileName);
        } else if (args.length >= 2 && args[0].equals("save")) {
            Profile profile = new Profile(SpecSpoof.configFolder + "\\profiles");
            String profileName = Utils.argsToString(args, 1);
            profile.saveProfile(profileName);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Saved spec profile as " + EnumChatFormatting.YELLOW + profileName + EnumChatFormatting.GREEN + "."));
        } else if (args.length >= 2 && args[0].equals("delete")) {

        }
    }
}
