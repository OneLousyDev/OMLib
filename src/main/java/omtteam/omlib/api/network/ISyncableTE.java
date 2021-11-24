package omtteam.omlib.api.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.vector.Vector3d;

import java.util.List;
import java.util.ListIterator;

public interface ISyncableTE extends ISyncable {
    List<ServerPlayerEntity> getSyncPlayerList();

    TileEntity getTE();

    void informUpdate();

    default void scrubSyncPlayerList() {
        ListIterator<ServerPlayerEntity> iter = getSyncPlayerList().listIterator();
        while (iter.hasNext()) {
            ServerPlayerEntity player = iter.next();
            TileEntity te = getTE();
            if (player.isAlive() || player.level.dimension() != te.getLevel().dimension()
                    || player.getHorizontalDistanceSqr(new Vector3d(te.getBlockPos().getX(), te.getBlockPos().getY(), te.getBlockPos().getZ())) > 25) {
                iter.remove();
            }
        }
    }
}
