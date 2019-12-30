package me.ibans.specspoof;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Profile {

    public String saveDir;

    public Profile(String saveDir) {
        this.saveDir = saveDir;
        Utils.createFolder(new File(saveDir));
    }

    public void saveProfile(String profileName) {
        JSONObject obj = new JSONObject();
        obj.put("profileName", profileName);
        obj.put("spoofedCPU", Values.getSpoofedCPU());
        obj.put("spoofedGPU", Values.getSpoofedGPU());
        obj.put("spoofedGPUVersion", Values.getSpoofedGPUVersion());
        obj.put("spoofedVendor", Values.getSpoofedVendor());

        try (FileWriter file = new FileWriter(saveDir + "\\" + profileName + ".profile")) {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loadProfile(String profileName) {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(saveDir + "\\" + profileName + ".profile"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(builder.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        Values.setSpoofedCPU((String) obj.get("spoofedCPU"));
        Values.setSpoofedGPU((String) obj.get("spoofedGPU"));
        Values.setSpoofedGPUVersion((String) obj.get("spoofedGPUVersion"));
        Values.setSpoofedVendor((String) obj.get("spoofedVendor"));
        return true;
    }

}
