package omtteam.omlib.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerEntityMP;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.util.text.TextComponentString;
import omtteam.omlib.handler.OMConfig;

import java.util.List;

/**
 * Created by nico on 21/05/17.
 */
public class DebugHandler {
    private static DebugHandler instance;
    private List<IContainerListener> listeners;
    private PlayerEntity player;

    private DebugHandler() {
    }

    public static DebugHandler getInstance() {
        if (instance == null) {
            instance = new DebugHandler();
        }
        return instance;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public List<IContainerListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<IContainerListener> listeners) {
        this.listeners = listeners;
    }

    public void sendMessageToDebugChat(String message) {
        if (this.player != null && !this.player.getEntityWorld().isRemote && OMConfig.GENERAL.doDebugChat) {
            this.player.sendMessage(new TextComponentString(message));
        }
        if (this.listeners != null && OMConfig.GENERAL.doDebugChat) {
            for (IContainerListener listener : this.listeners) {
                if (listener instanceof PlayerEntityMP && !((PlayerEntityMP) listener).getEntityWorld().isRemote) {
                    ((PlayerEntityMP) listener).sendMessage(new TextComponentString(message));
                }
            }
        }
    }
}
