package astryxion.ironchest.registry;

import astryxion.ironchest.IronChests;
import astryxion.ironchest.blocks.ChestTypes;
import astryxion.ironchest.screenhandlers.ChestScreenHandler;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlerType {
    public static MenuType<ChestScreenHandler> COPPER_CHEST;
    public static MenuType<ChestScreenHandler> IRON_CHEST;
    public static MenuType<ChestScreenHandler> GOLD_CHEST;
    public static MenuType<ChestScreenHandler> DIAMOND_CHEST;
    public static MenuType<ChestScreenHandler> EMERALD_CHEST;
    public static MenuType<ChestScreenHandler> CRYSTAL_CHEST;
    public static MenuType<ChestScreenHandler> OBSIDIAN_CHEST;
    public static MenuType<ChestScreenHandler> NETHERITE_CHEST;
    public static MenuType<ChestScreenHandler> CHRISTMAS_CHEST;

    public static void registerScreenHandlers() {
        COPPER_CHEST = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "copper_chest"), new MenuType<>((syncId, inventory) -> new ChestScreenHandler(COPPER_CHEST, ChestTypes.COPPER, syncId, inventory, ContainerLevelAccess.NULL), FeatureFlags.VANILLA_SET));
        IRON_CHEST = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "iron_chest"), new MenuType<>((syncId, inventory) -> new ChestScreenHandler(IRON_CHEST, ChestTypes.IRON, syncId, inventory, ContainerLevelAccess.NULL), FeatureFlags.VANILLA_SET));
        GOLD_CHEST = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "gold_chest"), new MenuType<>((syncId, inventory) -> new ChestScreenHandler(GOLD_CHEST, ChestTypes.GOLD, syncId, inventory, ContainerLevelAccess.NULL), FeatureFlags.VANILLA_SET));
        DIAMOND_CHEST = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "diamond_chest"), new MenuType<>((syncId, inventory) -> new ChestScreenHandler(DIAMOND_CHEST, ChestTypes.DIAMOND, syncId, inventory, ContainerLevelAccess.NULL), FeatureFlags.VANILLA_SET));
        EMERALD_CHEST = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "emerald_chest"), new MenuType<>((syncId, inventory) -> new ChestScreenHandler(EMERALD_CHEST, ChestTypes.EMERALD, syncId, inventory, ContainerLevelAccess.NULL), FeatureFlags.VANILLA_SET));
        CRYSTAL_CHEST = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "crystal_chest"), new MenuType<>((syncId, inventory) -> new ChestScreenHandler(CRYSTAL_CHEST, ChestTypes.CRYSTAL, syncId, inventory, ContainerLevelAccess.NULL), FeatureFlags.VANILLA_SET));
        OBSIDIAN_CHEST = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "obsidian_chest"), new MenuType<>((syncId, inventory) -> new ChestScreenHandler(OBSIDIAN_CHEST, ChestTypes.OBSIDIAN, syncId, inventory, ContainerLevelAccess.NULL), FeatureFlags.VANILLA_SET));
        NETHERITE_CHEST = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "netherite_chest"), new MenuType<>((syncId, inventory) -> new ChestScreenHandler(NETHERITE_CHEST, ChestTypes.NETHERITE, syncId, inventory, ContainerLevelAccess.NULL), FeatureFlags.VANILLA_SET));
        CHRISTMAS_CHEST = Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "christmas_chest"), new MenuType<>((syncId, inventory) -> new ChestScreenHandler(CHRISTMAS_CHEST, ChestTypes.CHRISTMAS, syncId, inventory, ContainerLevelAccess.NULL), FeatureFlags.VANILLA_SET));
    }
}
