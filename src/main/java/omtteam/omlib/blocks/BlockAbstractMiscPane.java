package omtteam.omlib.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Created by Keridos on 05/12/2015.
 * This Class
 */
@SuppressWarnings("unused")
public abstract class BlockAbstractMiscPane extends PaneBlock {
    protected BlockAbstractMiscPane(String par1, String par2, Material material, boolean par4) {
        super(material, par4);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public abstract TileEntity createTileEntity(World world, BlockState state);

    @Override
    @ParametersAreNonnullByDefault
    public boolean canCreatureSpawn(BlockState state, IBlockAccess world, BlockPos pos, LivingEntity.SpawnPlacementType type) {
        return false;
    }
}
