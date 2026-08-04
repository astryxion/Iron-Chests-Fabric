package astryxion.ironchest.registry;

import astryxion.ironchest.blocks.ChestTypes;
import astryxion.ironchest.client.ChestScreen;
import net.minecraft.client.gui.screens.MenuScreens;

public class ModScreenHandlers {
    public static void registerScreenHandlers() {
        for (ChestTypes type : ChestTypes.PLAYABLE) {
            MenuScreens.register(type.getMenuType(), ChestScreen::new);
        }
    }
}
