package me.ibans.specspoof;

import scala.actors.threadpool.Arrays;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

public class Utils {

    public static String argsToString(String[] args, int startPos) {
        String string = "";
        for (int i = startPos; i < args.length; i++) {
            string += args[i] + " ";
        }
        string = string.trim();
        return string;
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

}
