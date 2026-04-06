package astryxion.ironchest.registry;

import astryxion.ironchest.client.ChestEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class ModBlockEntityRenderer {
    public static void registerBlockEntityRenderer() {
        BlockEntityRenderers.register(ModBlockEntityType.COPPER_CHEST, ChestEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.IRON_CHEST, ChestEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.GOLD_CHEST, ChestEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.DIAMOND_CHEST, ChestEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.EMERALD_CHEST, ChestEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.CRYSTAL_CHEST, ChestEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.OBSIDIAN_CHEST, ChestEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.NETHERITE_CHEST, ChestEntityRenderer::new);
        BlockEntityRenderers.register(ModBlockEntityType.CHRISTMAS_CHEST, ChestEntityRenderer::new);
    }
}
