package me.ibans.specspoof;

import com.google.gson.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Profile {

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static final Profile INSTANCE = new Profile();

    public String saveDir = SpecSpoof.configFolder + "/profiles";

    public Profile() {
        Utils.createFolder(new File(saveDir));
    }

    public void saveProfile(String profileName) {
        JsonObject obj = new JsonObject();
        obj.addProperty("profileName", profileName);
        obj.addProperty("spoofedCPU", Values.getSpoofedCPU());
        obj.addProperty("spoofedGPU", Values.getSpoofedGPU());
        obj.addProperty("spoofedGPUVersion", Values.getSpoofedGPUVersion());
        obj.addProperty("spoofedVendor", Values.getSpoofedVendor());

        try (FileWriter file = new FileWriter(saveDir + "/" + profileName + ".profile")) {
            file.write(gson.toJson(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loadProfile(String profileName) throws JsonParseException, IllegalStateException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(saveDir + "/" + profileName + ".profile"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(builder.toString()).getAsJsonObject();

        if (obj == null) {
            return false;
        }

        Values.setSpoofedCPU(obj.get("spoofedCPU").getAsString());
        Values.setSpoofedGPU(obj.get("spoofedGPU").getAsString());
        Values.setSpoofedGPUVersion(obj.get("spoofedGPUVersion").getAsString());
        Values.setSpoofedVendor(obj.get("spoofedVendor").getAsString());
        return true;
    }

}
