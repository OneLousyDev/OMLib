package omtteam.omlib.compatibility;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import omtteam.omlib.OMLib;
import omtteam.omlib.compatibility.theoneprobe.TOPCompatibility;
import omtteam.omlib.reference.Reference;

/**
 * Created by Keridos on 23/01/2015. This Class
 */
@SuppressWarnings("WeakerAccess")
public class OMLibModCompatibility {
    
    public static final String OCModID = "opencomputers";
    public static final String CCModID = "computercraft";
    public static final String MekModID = "mekanism";
    public static final String TOPModID = "theoneprobe";
    public static final String WailaModID = "waila";
    public static final String OMTModID = "openmodularturrets";
    public static final String OMPDModID = "ompd";
    public static boolean IC2Loaded = false;
    public static boolean TeslaLoaded = false;
    public static boolean CoFHApiLoaded = false;
    public static boolean OpenComputersLoaded = false;
    public static boolean ComputerCraftLoaded = false;
    public static boolean TOPLoaded = false;
    public static boolean WailaLoaded = false;
    public static boolean ThermalExpansionLoaded = false;
    public static boolean EnderIOLoaded = false;
    public static boolean MekanismLoaded = false;
    public static boolean OMTLoaded = false;
    public static boolean OMTCLoaded = false;
    public static boolean OMPDLoaded = false;

    public static void checkForMods() {
        IC2Loaded = Loader.isModLoaded(IC2ModId);
        TeslaLoaded = Loader.isModLoaded(TeslaModId);
        OpenComputersLoaded = Loader.isModLoaded(OCModID);
        ComputerCraftLoaded = Loader.isModLoaded(CCModID);
        TOPLoaded = Loader.isModLoaded(TOPModID);
        WailaLoaded = Loader.isModLoaded(WailaModID);
        ThermalExpansionLoaded = Loader.isModLoaded(TEModID);
        EnderIOLoaded = Loader.isModLoaded(OMLibModCompatibility.EIOModID);
        MekanismLoaded = Loader.isModLoaded(OMLibModCompatibility.MekModID);
        OMTLoaded = Loader.isModLoaded(OMLibModCompatibility.OMTModID);
        OMTCLoaded = Loader.isModLoaded(OMLibModCompatibility.OMTCModID);
        OMPDLoaded = Loader.isModLoaded(OMLibModCompatibility.OMPDModID);

        printDetectedMods();
    }

    private static void printDetectedMods() {
        String foundMods = "Found the following compatability mods: ";
        foundMods += IC2Loaded ? "IC2 " : "";
        foundMods += TeslaLoaded ? "Tesla " : "";
        foundMods += CoFHApiLoaded ? "RedstoneFlux " : "";
        foundMods += OpenComputersLoaded ? "OpenComputers " : "";
        foundMods += TOPLoaded ? "TheOneProbe " : "";
        foundMods += WailaLoaded ? "Waila/Hwyla " : "";
        OMLib.getLogger().info(foundMods);
    }

    private static void addVersionCheckerInfo() {
        NBTTagCompound versionchecker = new NBTTagCompound();
        versionchecker.setString("curseProjectName", "omlib");
        versionchecker.setString("curseFilenameParser", "OMLib-1.10.2-[].jar");
        versionchecker.setString("modDisplayName", "OMLib");
        versionchecker.setString("oldVersion", Reference.VERSION);
        FMLInterModComms.sendRuntimeMessage("omlib", "VersionChecker", "addCurseCheck", versionchecker);
    }

    public static void performModCompat() {
        addVersionCheckerInfo();
        if (TOPLoaded) {
            TOPCompatibility.register();
        }
    }
}
