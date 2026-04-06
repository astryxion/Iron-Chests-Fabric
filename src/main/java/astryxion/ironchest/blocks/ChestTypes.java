package astryxion.ironchest.blocks;

import astryxion.ironchest.IronChests;
import astryxion.ironchest.registry.ModBlockEntityType;
import astryxion.ironchest.registry.ModBlocks;
import astryxion.ironchest.registry.ModScreenHandlerType;
import astryxion.ironchest.screenhandlers.ChestScreenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public enum ChestTypes {
    NETHERITE(126, 14, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "entity/chest/netherite_chest")),
    OBSIDIAN(108, 12, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "entity/chest/obsidian_chest")),
    CRYSTAL(108, 12, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "entity/chest/crystal_chest")),
    DIAMOND(108, 12, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "entity/chest/diamond_chest")),
    EMERALD(108, 12, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "entity/chest/emerald_chest")),
    GOLD(81, 9, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "entity/chest/gold_chest")),
    IRON(54, 9, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "entity/chest/iron_chest")),
    COPPER(45, 9, Identifier.fromNamespaceAndPath(IronChests.MOD_ID, "entity/chest/copper_chest")),
    CHRISTMAS(27, 9, Identifier.parse("minecraft:entity/chest/christmas")),
    WOOD(27, 9, Identifier.parse("minecraft:entity/chest/normal"));

    public final int size;
    public final int rowLength;
    public final Identifier texture;

    ChestTypes(int size, int rowLength, Identifier texture) {
        this.size = size;
        this.rowLength = rowLength;
        this.texture = texture;
    }

    public int getRowCount() {
        return this.size / this.rowLength;
    }

    public static Block get(ChestTypes type) {
        return switch (type) {
            case COPPER -> ModBlocks.COPPER_CHEST;
            case IRON -> ModBlocks.IRON_CHEST;
            case GOLD -> ModBlocks.GOLD_CHEST;
            case DIAMOND -> ModBlocks.DIAMOND_CHEST;
            case EMERALD -> ModBlocks.EMERALD_CHEST;
            case CRYSTAL -> ModBlocks.CRYSTAL_CHEST;
            case OBSIDIAN -> ModBlocks.OBSIDIAN_CHEST;
            case NETHERITE -> ModBlocks.NETHERITE_CHEST;
            case CHRISTMAS -> ModBlocks.CHRISTMAS_CHEST;
            default -> Blocks.CHEST;
        };
    }

    // Used to implement Item Upgrades
    public ChestBlockEntity makeEntity(BlockPos pos, BlockState state) {
        return switch (this) {
            case COPPER -> ModBlockEntityType.COPPER_CHEST.create(pos, state);
            case IRON -> ModBlockEntityType.IRON_CHEST.create(pos, state);
            case GOLD -> ModBlockEntityType.GOLD_CHEST.create(pos, state);
            case DIAMOND -> ModBlockEntityType.DIAMOND_CHEST.create(pos, state);
            case EMERALD -> ModBlockEntityType.EMERALD_CHEST.create(pos, state);
            case CRYSTAL -> ModBlockEntityType.CRYSTAL_CHEST.create(pos, state);
            case OBSIDIAN -> ModBlockEntityType.OBSIDIAN_CHEST.create(pos, state);
            case NETHERITE -> ModBlockEntityType.NETHERITE_CHEST.create(pos, state);
            case CHRISTMAS -> ModBlockEntityType.CHRISTMAS_CHEST.create(pos, state);
            default -> new ChestBlockEntity(pos, state);
        };
    }

    public MenuType<ChestScreenHandler> getScreenHandlerType() {
        return switch (this) {
            case COPPER -> ModScreenHandlerType.COPPER_CHEST;
            case IRON -> ModScreenHandlerType.IRON_CHEST;
            case GOLD -> ModScreenHandlerType.GOLD_CHEST;
            case DIAMOND -> ModScreenHandlerType.DIAMOND_CHEST;
            case EMERALD -> ModScreenHandlerType.EMERALD_CHEST;
            case CRYSTAL -> ModScreenHandlerType.CRYSTAL_CHEST;
            case OBSIDIAN -> ModScreenHandlerType.OBSIDIAN_CHEST;
            case NETHERITE -> ModScreenHandlerType.NETHERITE_CHEST;
            default -> ModScreenHandlerType.CHRISTMAS_CHEST;
        };
    }

    public BlockEntityType<? extends ChestBlockEntity> getBlockEntityType() {
        return switch (this) {
            case COPPER -> ModBlockEntityType.COPPER_CHEST;
            case IRON -> ModBlockEntityType.IRON_CHEST;
            case GOLD -> ModBlockEntityType.GOLD_CHEST;
            case DIAMOND -> ModBlockEntityType.DIAMOND_CHEST;
            case EMERALD -> ModBlockEntityType.EMERALD_CHEST;
            case CRYSTAL -> ModBlockEntityType.CRYSTAL_CHEST;
            case OBSIDIAN -> ModBlockEntityType.OBSIDIAN_CHEST;
            case NETHERITE -> ModBlockEntityType.NETHERITE_CHEST;
            case CHRISTMAS -> ModBlockEntityType.CHRISTMAS_CHEST;
            default -> BlockEntityType.CHEST;
        };
    }

    public BlockBehaviour.Properties setting() {
        return switch (this) {
            case COPPER, GOLD -> BlockBehaviour.Properties.of()
                    .strength(3.0F, 6.0F)
                    .sound(SoundType.COPPER)
                    .requiresCorrectToolForDrops();
            case IRON -> BlockBehaviour.Properties.of()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.IRON)
                    .requiresCorrectToolForDrops();
            case DIAMOND, EMERALD -> BlockBehaviour.Properties.of()
                    .strength(5.0F, 6.0F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops();
            case CRYSTAL -> BlockBehaviour.Properties.of()
                    .strength(3.0F, 3.0F)
                    .sound(SoundType.AMETHYST)
                    .requiresCorrectToolForDrops();
            case OBSIDIAN -> BlockBehaviour.Properties.of()
                    .strength(50.0F, 1200.0F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops();
            case NETHERITE -> BlockBehaviour.Properties.of()
                    .strength(50.0F, 1200.0F)
                    .sound(SoundType.NETHERITE_BLOCK)
                    .requiresCorrectToolForDrops();
            case WOOD, CHRISTMAS -> BlockBehaviour.Properties.of()
                    .strength(3.0F, 3.0F)
                    .sound(SoundType.WOOD);
            default -> BlockBehaviour.Properties.of();
        };
    }
}
