package omtteam.omlib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import omtteam.omlib.handler.OMConfig;
import omtteam.omlib.handler.registry.BlockRegistry;
import omtteam.omlib.handler.registry.CommandRegistry;
import omtteam.omlib.handler.registry.ItemRegistry;
import omtteam.omlib.reference.Reference;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class OMLib 
{    
	public static final Logger LOGGER = LogManager.getLogger();

    public OMLib()
    {
    	ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, OMConfig.SERVER_CONFIG);
    	
    	BlockRegistry.registerBlocks();
    	ItemRegistry.registerItems();
    	
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
    	
    }
	
    @SubscribeEvent
    public static void serverStarting(RegisterCommandsEvent event) 
    {
        CommandRegistry.register(event.getDispatcher());
    }
}