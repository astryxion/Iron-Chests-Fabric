package astryxion.ironchest.registry;

import astryxion.ironchest.IronChests;
import astryxion.ironchest.items.UpgradeItem;
import astryxion.ironchest.items.UpgradeTypes;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemLore;

import java.util.List;

public class ModItems {

    // Upgrade Items
    public static final Item WOOD_COPPER_UPGRADE = new UpgradeItem(UpgradeTypes.WOOD_TO_COPPER, upgradeItemSettings("wood_copper_upgrade", UpgradeTypes.WOOD_TO_COPPER));
    public static final Item WOOD_IRON_UPGRADE = new UpgradeItem(UpgradeTypes.WOOD_TO_IRON, upgradeItemSettings("wood_iron_upgrade", UpgradeTypes.WOOD_TO_IRON));
    public static final Item WOOD_GOLD_UPGRADE = new UpgradeItem(UpgradeTypes.WOOD_TO_GOLD, upgradeItemSettings("wood_gold_upgrade", UpgradeTypes.WOOD_TO_GOLD));
    public static final Item WOOD_DIAMOND_UPGRADE = new UpgradeItem(UpgradeTypes.WOOD_TO_DIAMOND, upgradeItemSettings("wood_diamond_upgrade", UpgradeTypes.WOOD_TO_DIAMOND));
    public static final Item WOOD_EMERALD_UPGRADE = new UpgradeItem(UpgradeTypes.WOOD_TO_EMERALD, upgradeItemSettings("wood_emerald_upgrade", UpgradeTypes.WOOD_TO_EMERALD));
    public static final Item WOOD_CRYSTAL_UPGRADE = new UpgradeItem(UpgradeTypes.WOOD_TO_CRYSTAL, upgradeItemSettings("wood_crystal_upgrade", UpgradeTypes.WOOD_TO_CRYSTAL));
    public static final Item WOOD_OBSIDIAN_UPGRADE = new UpgradeItem(UpgradeTypes.WOOD_TO_OBSIDIAN, upgradeItemSettings("wood_obsidian_upgrade", UpgradeTypes.WOOD_TO_OBSIDIAN));

    public static final Item COPPER_IRON_UPGRADE = new UpgradeItem(UpgradeTypes.COPPER_TO_IRON, upgradeItemSettings("copper_iron_upgrade", UpgradeTypes.COPPER_TO_IRON));
    public static final Item COPPER_GOLD_UPGRADE = new UpgradeItem(UpgradeTypes.COPPER_TO_GOLD, upgradeItemSettings("copper_gold_upgrade", UpgradeTypes.COPPER_TO_GOLD));
    public static final Item COPPER_DIAMOND_UPGRADE = new UpgradeItem(UpgradeTypes.COPPER_TO_DIAMOND, upgradeItemSettings("copper_diamond_upgrade", UpgradeTypes.COPPER_TO_DIAMOND));
    public static final Item COPPER_EMERALD_UPGRADE = new UpgradeItem(UpgradeTypes.COPPER_TO_EMERALD, upgradeItemSettings("copper_emerald_upgrade", UpgradeTypes.COPPER_TO_EMERALD));
    public static final Item COPPER_CRYSTAL_UPGRADE = new UpgradeItem(UpgradeTypes.COPPER_TO_CRYSTAL, upgradeItemSettings("copper_crystal_upgrade", UpgradeTypes.COPPER_TO_CRYSTAL));
    public static final Item COPPER_OBSIDIAN_UPGRADE = new UpgradeItem(UpgradeTypes.COPPER_TO_OBSIDIAN, upgradeItemSettings("copper_obsidian_upgrade", UpgradeTypes.COPPER_TO_OBSIDIAN));

    public static final Item IRON_GOLD_UPGRADE = new UpgradeItem(UpgradeTypes.IRON_TO_GOLD, upgradeItemSettings("iron_gold_upgrade", UpgradeTypes.IRON_TO_GOLD));
    public static final Item IRON_DIAMOND_UPGRADE = new UpgradeItem(UpgradeTypes.IRON_TO_DIAMOND, upgradeItemSettings("iron_diamond_upgrade", UpgradeTypes.IRON_TO_DIAMOND));
    public static final Item IRON_EMERALD_UPGRADE = new UpgradeItem(UpgradeTypes.IRON_TO_EMERALD, upgradeItemSettings("iron_emerald_upgrade", UpgradeTypes.IRON_TO_EMERALD));
    public static final Item IRON_CRYSTAL_UPGRADE = new UpgradeItem(UpgradeTypes.IRON_TO_CRYSTAL, upgradeItemSettings("iron_crystal_upgrade", UpgradeTypes.IRON_TO_CRYSTAL));
    public static final Item IRON_OBSIDIAN_UPGRADE = new UpgradeItem(UpgradeTypes.IRON_TO_OBSIDIAN, upgradeItemSettings("iron_obsidian_upgrade", UpgradeTypes.IRON_TO_OBSIDIAN));

    public static final Item GOLD_DIAMOND_UPGRADE = new UpgradeItem(UpgradeTypes.GOLD_TO_DIAMOND, upgradeItemSettings("gold_diamond_upgrade", UpgradeTypes.GOLD_TO_DIAMOND));
    public static final Item GOLD_EMERALD_UPGRADE = new UpgradeItem(UpgradeTypes.GOLD_TO_EMERALD, upgradeItemSettings("gold_emerald_upgrade", UpgradeTypes.GOLD_TO_EMERALD));
    public static final Item GOLD_CRYSTAL_UPGRADE = new UpgradeItem(UpgradeTypes.GOLD_TO_CRYSTAL, upgradeItemSettings("gold_crystal_upgrade", UpgradeTypes.GOLD_TO_CRYSTAL));
    public static final Item GOLD_OBSIDIAN_UPGRADE = new UpgradeItem(UpgradeTypes.GOLD_TO_OBSIDIAN, upgradeItemSettings("gold_obsidian_upgrade", UpgradeTypes.GOLD_TO_OBSIDIAN));

    public static final Item DIAMOND_OBSIDIAN_UPGRADE = new UpgradeItem(UpgradeTypes.DIAMOND_TO_OBSIDIAN, upgradeItemSettings("diamond_obsidian_upgrade", UpgradeTypes.DIAMOND_TO_OBSIDIAN));
    public static final Item DIAMOND_CRYSTAL_UPGRADE = new UpgradeItem(UpgradeTypes.DIAMOND_TO_CRYSTAL, upgradeItemSettings("diamond_crystal_upgrade", UpgradeTypes.DIAMOND_TO_CRYSTAL));

    public static final Item EMERALD_OBSIDIAN_UPGRADE = new UpgradeItem(UpgradeTypes.EMERALD_TO_OBSIDIAN, upgradeItemSettings("emerald_obsidian_upgrade", UpgradeTypes.EMERALD_TO_OBSIDIAN));
    public static final Item EMERALD_CRYSTAL_UPGRADE = new UpgradeItem(UpgradeTypes.EMERALD_TO_CRYSTAL, upgradeItemSettings("emerald_crystal_upgrade", UpgradeTypes.EMERALD_TO_CRYSTAL));

    public static final Item WOOD_CHRISTMAS_UPGRADE = new UpgradeItem(UpgradeTypes.WOOD_TO_CHRISTMAS, upgradeItemSettings("wood_christmas_upgrade", UpgradeTypes.WOOD_TO_CHRISTMAS));

    // Chest Items
    public static final BlockItem COPPER_CHEST = new BlockItem(ModBlocks.COPPER_CHEST, blockItemSettings("copper_chest"));
    public static final BlockItem IRON_CHEST = new BlockItem(ModBlocks.IRON_CHEST, blockItemSettings("iron_chest"));
    public static final BlockItem GOLD_CHEST = new BlockItem(ModBlocks.GOLD_CHEST, blockItemSettings("gold_chest"));
    public static final BlockItem DIAMOND_CHEST = new BlockItem(ModBlocks.DIAMOND_CHEST, blockItemSettings("diamond_chest"));
    public static final BlockItem EMERALD_CHEST = new BlockItem(ModBlocks.EMERALD_CHEST, blockItemSettings("emerald_chest"));
    public static final BlockItem CRYSTAL_CHEST = new BlockItem(ModBlocks.CRYSTAL_CHEST, blockItemSettings("crystal_chest"));
    public static final BlockItem OBSIDIAN_CHEST = new BlockItem(ModBlocks.OBSIDIAN_CHEST, blockItemSettings("obsidian_chest"));
    public static final BlockItem NETHERITE_CHEST = new BlockItem(ModBlocks.NETHERITE_CHEST, blockItemSettings("netherite_chest").fireResistant());
    public static final BlockItem CHRISTMAS_CHEST = new BlockItem(ModBlocks.CHRISTMAS_CHEST, blockItemSettings("christmas_chest"));

    public static void registerItems() {
        // Item
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "wood_copper_upgrade"), WOOD_COPPER_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "wood_iron_upgrade"), WOOD_IRON_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "wood_gold_upgrade"), WOOD_GOLD_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "wood_diamond_upgrade"), WOOD_DIAMOND_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "wood_emerald_upgrade"), WOOD_EMERALD_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "wood_crystal_upgrade"), WOOD_CRYSTAL_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "wood_obsidian_upgrade"), WOOD_OBSIDIAN_UPGRADE);

        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "copper_iron_upgrade"), COPPER_IRON_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "copper_gold_upgrade"), COPPER_GOLD_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "copper_diamond_upgrade"), COPPER_DIAMOND_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "copper_emerald_upgrade"), COPPER_EMERALD_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "copper_crystal_upgrade"), COPPER_CRYSTAL_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "copper_obsidian_upgrade"), COPPER_OBSIDIAN_UPGRADE);

        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "iron_gold_upgrade"), IRON_GOLD_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "iron_diamond_upgrade"), IRON_DIAMOND_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "iron_emerald_upgrade"), IRON_EMERALD_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "iron_crystal_upgrade"), IRON_CRYSTAL_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "iron_obsidian_upgrade"), IRON_OBSIDIAN_UPGRADE);

        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "gold_diamond_upgrade"), GOLD_DIAMOND_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "gold_emerald_upgrade"), GOLD_EMERALD_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "gold_crystal_upgrade"), GOLD_CRYSTAL_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "gold_obsidian_upgrade"), GOLD_OBSIDIAN_UPGRADE);

        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "diamond_crystal_upgrade"), DIAMOND_CRYSTAL_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "diamond_obsidian_upgrade"), DIAMOND_OBSIDIAN_UPGRADE);

        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "emerald_crystal_upgrade"), EMERALD_CRYSTAL_UPGRADE);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "emerald_obsidian_upgrade"), EMERALD_OBSIDIAN_UPGRADE);

        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "wood_christmas_upgrade"), WOOD_CHRISTMAS_UPGRADE);

        // Block Items
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "copper_chest"), COPPER_CHEST);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "iron_chest"), IRON_CHEST);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "gold_chest"), GOLD_CHEST);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "diamond_chest"), DIAMOND_CHEST);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "emerald_chest"), EMERALD_CHEST);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "crystal_chest"), CRYSTAL_CHEST);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "obsidian_chest"), OBSIDIAN_CHEST);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "netherite_chest"), NETHERITE_CHEST);
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "christmas_chest"), CHRISTMAS_CHEST);

        CreativeModeTabEvents.modifyOutputEvent(IronChests.TAB).register(entries -> {
            entries.accept(COPPER_CHEST);
            entries.accept(IRON_CHEST);
            entries.accept(GOLD_CHEST);
            entries.accept(DIAMOND_CHEST);
            entries.accept(EMERALD_CHEST);
            entries.accept(CRYSTAL_CHEST);
            entries.accept(OBSIDIAN_CHEST);
            entries.accept(NETHERITE_CHEST);
            entries.accept(CHRISTMAS_CHEST);
        });
    }

    private static Item.Properties settings(String name) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, name)));
    }

    private static Item.Properties blockItemSettings(String name) {
        return settings(name).useBlockDescriptionPrefix();
    }

    private static Item.Properties upgradeItemSettings(String name, UpgradeTypes type) {
        Component baseText = Component.translatable(UpgradeTypes.tooltip(type));
        Component styledText = Component.translatable(UpgradeTypes.tooltip(type)).withStyle(ChatFormatting.GREEN);
        return settings(name).component(
            DataComponents.LORE,
            new ItemLore(List.of(baseText), List.of(styledText))
        );
    }
}
