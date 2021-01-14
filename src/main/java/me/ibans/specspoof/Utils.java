package me.ibans.specspoof;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String argsToString(String[] args, int startPos) {
        StringBuilder string = new StringBuilder();
        for (int i = startPos; i < args.length; i++) {
            string.append(args[i]).append(" ");
        }
        string = new StringBuilder(string.toString().trim());
        return string.toString();
    }

    public static void updateConfig(String category, String name, String newValue) {
        SpecSpoof.config.getCategory(category).get(name).set(newValue);
        SpecSpoof.config.save();
    }

    public static void updateConfig(String category, String name, boolean newValue) {
        SpecSpoof.config.getCategory(category).get(name).set(newValue);
        SpecSpoof.config.save();
    }

    public static List<String> listFilesInDirectory(String dir, String ext) {
        File f = new File(dir);
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(ext);
            }
        };
        return Arrays.asList(f.list(filter));
    }

    // Creates a new folder if it doesn't exist
    public static void createFolder(File dir) {
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println(dir.getAbsolutePath() + " has been created since it didn't exist.");
            } else {
                System.out.println("Unable to create " + dir.mkdir());
            }
        }
    }

    private static String format(String s) {
        s = s.concat("&r");
        Pattern pattern = Pattern.compile("(&)([0123456789abcdefklmnor])");
        Matcher matcher = pattern.matcher(s);
        return matcher.replaceAll("\u00a7$2");
    }

    public static void sendMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(format(message)));
    }

}
