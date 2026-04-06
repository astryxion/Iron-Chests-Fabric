package astryxion.ironchest.registry;

import astryxion.ironchest.IronChests;
import astryxion.ironchest.blocks.ChestTypes;
import astryxion.ironchest.blocks.CrystalChestBlock;
import astryxion.ironchest.blocks.GenericChestBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
public class ModBlocks {
    public static final Block COPPER_CHEST = new GenericChestBlock(settings("copper_chest", ChestTypes.COPPER), ChestTypes.COPPER);
    public static final Block IRON_CHEST = new GenericChestBlock(settings("iron_chest", ChestTypes.IRON), ChestTypes.IRON);
    public static final Block GOLD_CHEST = new GenericChestBlock(settings("gold_chest", ChestTypes.GOLD), ChestTypes.GOLD);
    public static final Block DIAMOND_CHEST = new GenericChestBlock(settings("diamond_chest", ChestTypes.DIAMOND), ChestTypes.DIAMOND);
    public static final Block EMERALD_CHEST = new GenericChestBlock(settings("emerald_chest", ChestTypes.EMERALD), ChestTypes.EMERALD);
    public static final Block CRYSTAL_CHEST = new CrystalChestBlock(settings("crystal_chest", ChestTypes.CRYSTAL));
    public static final Block OBSIDIAN_CHEST = new GenericChestBlock(settings("obsidian_chest", ChestTypes.OBSIDIAN), ChestTypes.OBSIDIAN);
    public static final Block NETHERITE_CHEST = new GenericChestBlock(settings("netherite_chest", ChestTypes.NETHERITE), ChestTypes.NETHERITE);
    public static final Block CHRISTMAS_CHEST = new GenericChestBlock(settings("christmas_chest", ChestTypes.CHRISTMAS), ChestTypes.CHRISTMAS);

    public static void registerBlocks() {
        Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "copper_chest"), COPPER_CHEST);
        Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "iron_chest"), IRON_CHEST);
        Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "gold_chest"), GOLD_CHEST);
        Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "diamond_chest"), DIAMOND_CHEST);
        Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "emerald_chest"), EMERALD_CHEST);
        Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "crystal_chest"), CRYSTAL_CHEST);
        Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "obsidian_chest"), OBSIDIAN_CHEST);
        Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "netherite_chest"), NETHERITE_CHEST);
        Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "christmas_chest"), CHRISTMAS_CHEST);
    }

    private static BlockBehaviour.Properties settings(String name, ChestTypes type) {
        return type.setting().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, name)));
    }
}
