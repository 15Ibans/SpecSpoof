package me.ibans.specspoof.commands;

import me.ibans.specspoof.Profile;
import me.ibans.specspoof.SpecSpoof;
import me.ibans.specspoof.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.*;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.io.File;
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
        if(args.length <= 0) throw new WrongUsageException("/specprofile <load/save/delete/list> <profileName>");
        if(args.length == 1) {
            final Profile profile = new Profile(SpecSpoof.configFolder + "\\profiles");
            final List<String> profileNames = Utils.listFilesInDirectory(profile.saveDir, ".profile");
            for (int i = 0; i < profileNames.size(); i++) {
                String currentValue = profileNames.get(i);
                profileNames.set(i, currentValue.substring(0, currentValue.length() - 8));
            }
            if (profileNames.size() == 0) sendMessage(EnumChatFormatting.RED + "No spec profiles are currently saved.");
            else {
                sendMessage(EnumChatFormatting.GREEN + "Current saved spec profiles:");
                profileNames.forEach(p -> sendMessage("- " + EnumChatFormatting.YELLOW + p));
            }
        } else if(args.length >= 2) {
            if (args[0].equalsIgnoreCase("load")) {
                final Profile profile = new Profile(SpecSpoof.configFolder + "\\profiles");
                final String profileName = Utils.argsToString(args, 1);
                final boolean isLoaded = profile.loadProfile(profileName);

                if (isLoaded) sendMessage(EnumChatFormatting.GREEN + "Loaded spec profile " + EnumChatFormatting.YELLOW + profileName);
                else sendMessage(EnumChatFormatting.RED + "Failed to load profile " + EnumChatFormatting.YELLOW + profileName + EnumChatFormatting.RED + ". Maybe it doesn't exist?");
            } else if (args[0].equalsIgnoreCase("save")) {
                final Profile profile = new Profile(SpecSpoof.configFolder + "\\profiles");
                final String profileName = Utils.argsToString(args, 1);

                profile.saveProfile(profileName);
                sendMessage(EnumChatFormatting.GREEN + "Saved spec profile as " + EnumChatFormatting.YELLOW + profileName + EnumChatFormatting.GREEN + ".");
            } else if (args[0].equalsIgnoreCase("delete")) {
                final String profileName = Utils.argsToString(args, 1);
                final List<String> profileNames = Utils.listFilesInDirectory(SpecSpoof.configFolder + "\\profiles", ".profile");
                final boolean isProfileInFiles = false;

                for (String profile : profileNames) {
                    if (profile.substring(0, profile.length() - 8).equals(profileName)) {
                        isProfileInFiles = true;
                        break;
                    }
                }
                
                if (isProfileInFiles) {
                    final File f = new File(SpecSpoof.configFolder + "\\profiles\\" + profileName + ".profile");
                    final boolean fileDeleted = f.delete();
                    if (fileDeleted) sendMessage(EnumChatFormatting.GREEN + "Successfully deleted profile " + EnumChatFormatting.YELLOW + profileName);
                    else sendMessage(EnumChatFormatting.RED + "Unable to delete profile");
                } else sendMessage(EnumChatFormatting.RED + profileName + " doesn't exist");
            }
        }
        
    }

    // should probably be moved into its own Utilities file
    public void sendMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message))
    }
}
