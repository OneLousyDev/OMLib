package omtteam.omlib.api.gui;

import java.awt.*;
import java.util.ArrayList;

import net.minecraft.client.gui.screen.Screen;

/**
 * Created by nico on 6/4/15.
 * Abstract class for all blocking UIs.
 */

public abstract class BlockingAbstractGui extends Screen {
    public BlockingAbstractGui() {
        super();
    }

    public abstract ArrayList<Rectangle> getBlockingAreas();
}
