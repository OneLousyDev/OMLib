package omtteam.omlib.handler.registry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import omtteam.omlib.items.ItemDebugTool;
import omtteam.omlib.items.ItemMultiTool;
import omtteam.omlib.reference.Reference;

public class ItemRegistry 
{
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
	
	public static final RegistryObject<Item> DEBUG_TOOL = ITEMS.register("debug_tool", ItemDebugTool::new);
	private static final RegistryObject<Item> MULTI_TOOL = ITEMS.register("multi_tool", ItemMultiTool::new);
	private static final RegistryObject<Item> CABLE = ITEMS.register("cable", () -> new BlockItem(BlockRegistry.CABLE.get(), new Item.Properties().tab(Reference.ITEM_GROUP)));
	
	public static void registerItems()
	{
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
