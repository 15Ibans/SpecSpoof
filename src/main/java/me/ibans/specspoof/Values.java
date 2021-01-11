package me.ibans.specspoof;

public class Values {

    private static boolean isEnabled = false;

    private static String spoofedCPU;
    private static String spoofedGPU;
    private static String spoofedVendor;
    private static String spoofedGPUVersion;

    public static boolean isEnabled() {
        return isEnabled;
    }

    public static void setEnabled(boolean value) {
        isEnabled = value;
        Utils.updateConfig("mod_values", "isenabled", value);
    }

    public static String getSpoofedCPU() {
        return spoofedCPU;
    }

    public static void setSpoofedCPU(String cpu) {
        spoofedCPU = cpu;
        Utils.updateConfig("spoofed_values", "spoofedcpu", cpu);
    }

    public static String getSpoofedGPU() {
        return spoofedGPU;
    }

    public static void setSpoofedGPU(String gpu) {
        spoofedGPU = gpu;
        Utils.updateConfig("spoofed_values", "spoofedgpu", gpu);
    }

    public static void setSpoofedVendor(String vendor) {
        spoofedVendor = vendor;
        Utils.updateConfig("spoofed_values", "spoofedgpuvendor", vendor);
    }

    public static String getSpoofedVendor() {
        return spoofedVendor;
    }

    public static void setSpoofedGPUVersion(String gpuVersion) {
        spoofedGPUVersion = gpuVersion;
        Utils.updateConfig("spoofed_values", "spoofedgpuversion", gpuVersion);
    }

    public static String getSpoofedGPUVersion() {
        return spoofedGPUVersion;
    }

}
