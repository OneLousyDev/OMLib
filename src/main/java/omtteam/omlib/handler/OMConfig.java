package omtteam.omlib.handler;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;

public class OMConfig {
	//Todo add back config for recipes from other mods
	public static final String CATEGORY_GENERAL = "general";
    public static ForgeConfigSpec CLIENT_CONFIG;
    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec.BooleanValue CAN_OP_ACCESS_OWNER_BLOCKS;
    public static ForgeConfigSpec.BooleanValue DO_DEBUG_CHAT;
    public static ForgeConfigSpec.BooleanValue DO_DEBUG_LOGGING;
    public static ForgeConfigSpec.BooleanValue OFFLINE_MODE_SUPPORT;
    public static ForgeConfigSpec.BooleanValue EU_SUPPORT;
    public static ForgeConfigSpec.DoubleValue EU_CONVERSION_RATIO;
    
    static
    {
    	ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    	ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    
        SERVER_BUILDER.comment("General Settings").push(CATEGORY_GENERAL);
        setup(SERVER_BUILDER);
        SERVER_CONFIG = SERVER_BUILDER.build();
        
    }
    
	public static void setup(ForgeConfigSpec.Builder SERVER_BUILDER)
	{
		CAN_OP_ACCESS_OWNER_BLOCKS = SERVER_BUILDER.comment("Can OPs access all owned blocks?").define("opOwnedBlocks", false);
		DO_DEBUG_CHAT = SERVER_BUILDER.comment("Should some blocks write debug messages on interaction?").define("debugMessages", false);
		DO_DEBUG_LOGGING = SERVER_BUILDER.comment("Should OpenModular Mods print out debug messages in log? (Warning, spammy)").define("debugLogging", false);
        OFFLINE_MODE_SUPPORT = SERVER_BUILDER.comment("Enable compat for offline mode servers?").define("offlineSupport", false);
        EU_SUPPORT = SERVER_BUILDER.comment("Is EU support (IndustrialCraft 2 Energy) enabled? (THIS CURRENTLY HAS NO EFFECT SINCE IC2 IS NOT ON 1.16)").define("euSupport", false);
        EU_CONVERSION_RATIO = SERVER_BUILDER.comment("How much RF is one EU? (THIS CURRENTLY HAS NO EFFECT SINCE IC2 IS NOT ON 1.16)").defineInRange("powerRatio", 1D, 4D, 4D);
    }
	

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading event)
    {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading event)
    {

    }
}
