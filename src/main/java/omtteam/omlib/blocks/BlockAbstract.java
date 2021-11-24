package omtteam.omlib.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Created by Keridos on 05/12/2015.
 * This Class
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class BlockAbstract extends Block 
{
    public BlockAbstract(Material material) 
    {
        super(Block.Properties.of(material));
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos, LivingEntity.SpawnPlacementType type) {
        return false;
    }
}
