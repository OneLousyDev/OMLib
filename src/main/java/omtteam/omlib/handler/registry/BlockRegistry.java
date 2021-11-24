package omtteam.omlib.handler.registry;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import omtteam.omlib.blocks.BlockCable;
import omtteam.omlib.reference.Reference;

public class BlockRegistry 
{
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
	
	public static final RegistryObject<Block> CABLE = BLOCKS.register("cable", BlockCable::new);
	
	public static void registerBlocks()
	{
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
