package me.ibans.specspoof;

import me.ibans.specspoof.commands.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

@Mod(modid = SpecSpoof.MODID, version = SpecSpoof.VERSION)
public class SpecSpoof
{
    public static final String MODID = "specspoof";
    public static final String VERSION = "1.0";
    public static Configuration config;
    public static String configFolder;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
//        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new ModToggleCommand());
        ClientCommandHandler.instance.registerCommand(new SetGpuCommand());
        ClientCommandHandler.instance.registerCommand(new SetCpuCommand());
        ClientCommandHandler.instance.registerCommand(new SetGpuVersionCommand());
        ClientCommandHandler.instance.registerCommand(new SetVendorCommand());
        ClientCommandHandler.instance.registerCommand(new SpecProfileCommand());
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        configFolder = e.getModConfigurationDirectory().getAbsolutePath() + "\\specspoof";
        File configDir = new File(configFolder);
        Utils.createFolder(configDir);

        config = new Configuration(new File(configFolder + "\\specspoof.cfg"));

        try {
            config.load();

            Values.setEnabled(config.getBoolean("isenabled", "mod_values", false, "Whether the mod is enabled or not"));
            Values.setSpoofedCPU(config.getString("spoofedcpu", "spoofed_values", "Spoofed cpu goes here", "Value for the spoofed cpu"));
            Values.setSpoofedGPU(config.getString("spoofedgpu", "spoofed_values", "Spoofed gpu goes here", "Value for the spoofed gpu"));
            Values.setSpoofedGPUVersion(config.getString("spoofedgpuversion", "spoofed_values", "Spoofed GPU version goes here", "Value for the spoofed gpu version"));
            Values.setSpoofedVendor(config.getString("spoofedgpuvendor", "spoofed_values", "Spoofed GPU vendor goes here", "Value for the spoofed gpu vendor"));
        } catch (Exception ex) {
            System.out.println("Config could not be loaded due to an error.");
        } finally {
            config.save();
        }
    }

    @SubscribeEvent
    public void onDebugOpen(RenderGameOverlayEvent e) {
        if (e.type == RenderGameOverlayEvent.ElementType.DEBUG && Values.isEnabled()) {
            e.setCanceled(true);
            NewDebugScreen.newDebugScreen.renderDebugInfo(new ScaledResolution(Minecraft.getMinecraft()));
        }
    }
}
