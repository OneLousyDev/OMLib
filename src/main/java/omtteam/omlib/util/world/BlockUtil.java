package omtteam.omlib.util.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Created by Keridos on 31/01/17.
 * This Class implements some Block related Utility functions
 */
public class BlockUtil {

    // ---------------------------------------------------------------
    // Utility functions for Camo Blockstates
    public static void writeBlockFromStateToNBT(CompoundNBT nbtTagCompound, BlockState state) {
        if (state != null && state.getBlock().getRegistryName() != null) {
            nbtTagCompound.putString("camoBlockRegName", state.getBlock().getRegistryName().toString());
            nbtTagCompound.putInt("camoBlockMeta", state.getBlock().getMetaFromState(state));
        }
    }

    public static BlockState getBlockStateFromNBT(CompoundNBT nbtTagCompound) {
        if (nbtTagCompound.contains("camoBlockRegName") && nbtTagCompound.contains("camoBlockMeta")) {
            Block block = ForgeRegistries.BLOCKS.getValue(
                    new ResourceLocation(nbtTagCompound.getString("camoBlockRegName")));
            if (block != null) {
                return block.getStateFromMeta(nbtTagCompound.getInteger("camoBlockMeta"));
            }
        }
        return null;
    }
    // ---------------------------------------------------------------
}
