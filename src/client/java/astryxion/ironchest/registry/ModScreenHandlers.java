package astryxion.ironchest.registry;

import astryxion.ironchest.client.ChestScreen;
import astryxion.ironchest.screenhandlers.ChestScreenHandler;
import net.minecraft.client.gui.screens.MenuScreens;

public class ModScreenHandlers {
    public static void registerScreenHandlers() {
        MenuScreens.register(ModScreenHandlerType.COPPER_CHEST, ChestScreen::new);
        MenuScreens.register(ModScreenHandlerType.IRON_CHEST, ChestScreen::new);
        MenuScreens.register(ModScreenHandlerType.GOLD_CHEST, ChestScreen::new);
        MenuScreens.register(ModScreenHandlerType.DIAMOND_CHEST, ChestScreen::new);
        MenuScreens.register(ModScreenHandlerType.EMERALD_CHEST, ChestScreen::new);
        MenuScreens.register(ModScreenHandlerType.CRYSTAL_CHEST, ChestScreen::new);
        MenuScreens.register(ModScreenHandlerType.OBSIDIAN_CHEST, ChestScreen::new);
        MenuScreens.register(ModScreenHandlerType.NETHERITE_CHEST, ChestScreen::new);
        MenuScreens.register(ModScreenHandlerType.CHRISTMAS_CHEST, ChestScreen::new);
    }
}
