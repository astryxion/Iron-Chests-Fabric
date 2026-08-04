package astryxion.ironchest.registry;

import astryxion.ironchest.IronChests;
import astryxion.ironchest.blocks.ChestTypes;
import astryxion.ironchest.screenhandlers.ChestScreenHandler;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlerType {
    public static void registerScreenHandlers() {
        for (ChestTypes type : ChestTypes.PLAYABLE) {
            MenuType<ChestScreenHandler> menuType = new MenuType<>((syncId, inventory) -> new ChestScreenHandler(
                type.getMenuType(), type, syncId, inventory, ChestScreenHandler.createClientContainer(type)
            ), FeatureFlags.VANILLA_SET);

            MenuType<ChestScreenHandler> registered = Registry.register(
                BuiltInRegistries.MENU,
                ResourceLocation.fromNamespaceAndPath(IronChests.MOD_ID, type.registryId),
                menuType
            );
            
            type.bindMenuType(registered);
        }
    }
}