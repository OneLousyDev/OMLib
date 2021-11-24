package omtteam.omlib.items;

import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Keridos on 06/08/17.
 * This Class
 */
public interface IDrawOutline {
    AxisAlignedBB getRenderOutline(Direction facing, World world, BlockPos pos);
}
