package omtteam.omlib.util.compat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;

public class MinecraftTools {

    public static EntityPlayerSP getPlayer(Minecraft mc) {
        return mc.player;
    }

    public static WorldClient getWorld(Minecraft mc) {
        return mc.world;
    }
}