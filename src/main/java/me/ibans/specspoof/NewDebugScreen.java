package me.ibans.specspoof;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.WorldType;
import org.lwjgl.opengl.Display;

import java.util.List;
import java.util.Map;

public class NewDebugScreen extends GuiOverlayDebug {

    public static NewDebugScreen newDebugScreen = new NewDebugScreen(Minecraft.getMinecraft());

    private final Minecraft mc;

    public NewDebugScreen(Minecraft mc) {
        super(mc);
        this.mc = mc;
    }

    private boolean isReducedDebug()
    {
        return this.mc.thePlayer.hasReducedDebug() || this.mc.gameSettings.reducedDebugInfo;
    }

    private static long bytesToMb(long bytes)
    {
        return bytes / 1024L / 1024L;
    }

    @Override
    protected List<String> getDebugInfoRight()
    {
        long i = Runtime.getRuntime().maxMemory();
        long j = Runtime.getRuntime().totalMemory();
        long k = Runtime.getRuntime().freeMemory();
        long l = j - k;
        List<String> list = Lists.newArrayList(new String[] {String.format("Java: %s %dbit", new Object[]{System.getProperty("java.version"), Integer.valueOf(this.mc.isJava64bit() ? 64 : 32)}), String.format("Mem: % 2d%% %03d/%03dMB", new Object[]{Long.valueOf(l * 100L / i), Long.valueOf(bytesToMb(l)), Long.valueOf(bytesToMb(i))}), String.format("Allocated: % 2d%% %03dMB", new Object[]{Long.valueOf(j * 100L / i), Long.valueOf(bytesToMb(j))}), "", String.format("CPU: %s", new Object[]{Values.getSpoofedCPU()}), "", String.format("Display: %dx%d (%s)", new Object[]{Integer.valueOf(Display.getWidth()), Integer.valueOf(Display.getHeight()), Values.getSpoofedVendor()}), Values.getSpoofedGPU(), Values.getSpoofedGPUVersion()});

        list.add("");
        list.addAll(net.minecraftforge.fml.common.FMLCommonHandler.instance().getBrandings(false));

        if (this.isReducedDebug())
        {
            return list;
        }
        else
        {
            if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.mc.objectMouseOver.getBlockPos() != null)
            {
                BlockPos blockpos = this.mc.objectMouseOver.getBlockPos();
                IBlockState iblockstate = this.mc.theWorld.getBlockState(blockpos);

                if (this.mc.theWorld.getWorldType() != WorldType.DEBUG_WORLD)
                {
                    iblockstate = iblockstate.getBlock().getActualState(iblockstate, this.mc.theWorld, blockpos);
                }

                list.add("");
                list.add(String.valueOf(Block.blockRegistry.getNameForObject(iblockstate.getBlock())));

                for (Map.Entry<IProperty, Comparable> entry : iblockstate.getProperties().entrySet())
                {
                    String s = ((Comparable)entry.getValue()).toString();

                    if (entry.getValue() == Boolean.TRUE)
                    {
                        s = EnumChatFormatting.GREEN + s;
                    }
                    else if (entry.getValue() == Boolean.FALSE)
                    {
                        s = EnumChatFormatting.RED + s;
                    }

                    list.add(((IProperty)entry.getKey()).getName() + ": " + s);
                }
            }

            return list;
        }
    }

}
