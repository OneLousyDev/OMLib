package omtteam.omlib.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import omtteam.omlib.util.world.Pos;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Created by Keridos on 01/10/2015.
 * This Class implements some Math functions.
 */
@SuppressWarnings({"unused", "SuspiciousNameCombination"})
public class MathUtil {
    public static int truncateDoubleToInt(double number) {
        return (int) Math.floor(number);
    }

    @ParametersAreNonnullByDefault
    public static AxisAlignedBB rotateAABB(AxisAlignedBB box, Direction facing) {
        switch (facing) {
            case NORTH:
                return new AxisAlignedBB(box.minX, box.minY, box.maxZ, box.maxX, box.maxY, box.minZ);
            case EAST:
                return new AxisAlignedBB(box.minZ, box.minY, box.minX, box.maxZ, box.maxY, box.maxX);
            case SOUTH:
                return box;
            case WEST:
                return new AxisAlignedBB(box.minZ, box.minY, box.maxX, box.maxZ, box.maxY, box.minX);
            case UP:
                return new AxisAlignedBB(box.minY, box.minZ, box.minX, box.maxY, box.maxZ, box.maxX);
            case DOWN:
                return new AxisAlignedBB(box.maxY, box.minZ, box.minX, box.minY, box.maxZ, box.maxX);
        }
        return box;
    }
    
    public static AxisAlignedBB getAABBAroundBlockPos(BlockPos pos, int range) {
        return getAABBAroundPos(new Pos(pos), range);
    }

    public static AxisAlignedBB getAABBAroundEntity(Entity entity, int range) {
        return getAABBAroundPos(new Pos(entity), range);
    }

    public static AxisAlignedBB getAABBAroundPos(Pos pos, int range) {
        return new AxisAlignedBB(pos.getX() - range, pos.getY() - range, pos.getZ() - range,
                                 pos.getX() + range, pos.getY() + range, pos.getZ() + range);
    }

    public static Vector3d getVectorFromYawPitch(float yaw, float pitch) {
        float f = MathHelper.cos(-yaw * 0.017453292F - (float) Math.PI);
        float f1 = MathHelper.sin(-yaw * 0.017453292F - (float) Math.PI);
        float f2 = -MathHelper.cos(-pitch * 0.017453292F);
        float f3 = MathHelper.sin(-pitch * 0.017453292F);
        return new Vector3d((double) (f1 * f2), (double) f3, (double) (f * f2)).normalize();
    }

    public static Vector3d getVelocityVectorFromYawPitch(float yaw, float pitch, float velocity) {
        return getVectorFromYawPitch(yaw, pitch).scale(velocity);
    }

    public static float getRotationXYFromYawPitch(float yaw, float pitch) {
        return (float) ((pitch + 270F) / 90F * (Math.PI / 2F));
    }

    public static float getRotationXZFromYawPitch(float yaw, float pitch) {
        return (float) ((yaw - 90F) / 90F * (Math.PI / 2F));
    }
}
