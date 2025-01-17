package omtteam.omlib.api.network;

import net.minecraft.util.Direction;

/**
 * Created by Keridos on 30/08/17.
 * This Class
 */
@SuppressWarnings("ALL")
public interface INetworkCable extends INetworkTile {
    /**
     * Return true if the cable should connect to the given side.
     *
     * @param side side to check connection state to
     * @return connection state.
     */
    boolean shouldConnect(Direction side);
}

