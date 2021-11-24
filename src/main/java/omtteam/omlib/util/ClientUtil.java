package omtteam.omlib.util;

import net.minecraft.entity.player.PlayerEntity;
import omtteam.omlib.api.gui.GuiParameters;

public class ClientUtil {
    public static void openGuiFromParameters(PlayerEntity player, GuiParameters params) {
        player.openGui(params.getMod(), params.getModGuiId(), params.getWorld(), params.getPosX(), params.getPosY(), params.getPosZ());
    }
}
