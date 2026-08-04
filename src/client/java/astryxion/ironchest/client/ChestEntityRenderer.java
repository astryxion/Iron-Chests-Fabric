package astryxion.ironchest.client;

import astryxion.ironchest.blocks.GenericChestBlock;
import astryxion.ironchest.blocks.blockentities.CrystalChestEntity;
import astryxion.ironchest.blocks.blockentities.GenericChestEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.ChestModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ChestEntityRenderer<T extends BlockEntity & LidBlockEntity> extends ChestRenderer<T> {
    private final MaterialSet materials;
    private final ChestModel singleModel;
    private final ChestModel doubleLeftModel;
    private final ChestModel doubleRightModel;

    public ChestEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        this.materials = context.materials();
        this.singleModel = new ChestModel(context.bakeLayer(ModelLayers.CHEST));
        this.doubleLeftModel = new ChestModel(context.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT));
        this.doubleRightModel = new ChestModel(context.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT));
    }

    @Override
    public ChestRenderState createRenderState() {
        return new IronChestRenderState();
    }

    @Override
    public void extractRenderState(
        T blockEntity,
        ChestRenderState state,
        float partialTicks,
        Vec3 cameraPosition,
        ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress
    ) {
        super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        IronChestRenderState renderState = (IronChestRenderState) state;
        if (blockEntity instanceof GenericChestEntity && blockEntity.getBlockState().getBlock() instanceof GenericChestBlock chestBlock) {
            renderState.modMaterial = new Material(Sheets.CHEST_SHEET, chestBlock.getType().texture);
        }
        if (blockEntity instanceof CrystalChestEntity crystal) {
            renderState.topStacks = crystal.getTopStacks();
        }
    }

    @Override
    public void submit(ChestRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        IronChestRenderState renderState = (IronChestRenderState) state;
        if (renderState.modMaterial == null) {
            super.submit(state, poseStack, submitNodeCollector, camera);
            return;
        }

        poseStack.pushPose();
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(-state.angle));
        poseStack.translate(-0.5F, -0.5F, -0.5F);
        float open = state.open;
        open = 1.0F - open;
        open = 1.0F - open * open * open;
        ChestModel model = selectModel(state.type);
        Material material = renderState.modMaterial;
        RenderType renderType = material.renderType(RenderType::entityCutout);
        TextureAtlasSprite sprite = this.materials.get(material);
        submitNodeCollector.submitModel(
            model,
            open,
            poseStack,
            renderType,
            state.lightCoords,
            OverlayTexture.NO_OVERLAY,
            -1,
            sprite,
            0,
            state.breakProgress
        );

        if (renderState.topStacks != null) {
            renderItems(poseStack, renderState.topStacks, submitNodeCollector, state.lightCoords, (int) state.blockPos.asLong());
        }

        poseStack.popPose();
    }

    private ChestModel selectModel(ChestType type) {
        return switch (type) {
            case LEFT -> this.doubleLeftModel;
            case RIGHT -> this.doubleRightModel;
            default -> this.singleModel;
        };
    }

    private void renderItems(PoseStack poseStack, NonNullList<ItemStack> items, SubmitNodeCollector collector, int light, int seed) {
        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;
        if (level == null) return;
        float rotation = level.getGameTime() + mc.getDeltaTracker().getGameTimeDeltaPartialTick(false);
        int i = 0;
        for (int j = 0; j < 3; j++) renderItem(0.55, 0.3 + j * 0.5, 0.7, items.get(i++), poseStack, collector, light, seed, mc, level, rotation);
        for (int j = 0; j < 3; j++) renderItem(1.4, 0.3 + j * 0.5, 0.7, items.get(i++), poseStack, collector, light, seed, mc, level, rotation);
        for (int j = 0; j < 3; j++) renderItem(0.55, 0.3 + j * 0.5, 1.4, items.get(i++), poseStack, collector, light, seed, mc, level, rotation);
        for (int j = 0; j < 3; j++) renderItem(1.4, 0.3 + j * 0.5, 1.4, items.get(i++), poseStack, collector, light, seed, mc, level, rotation);
    }

    private void renderItem(
        double x, double y, double z, ItemStack item,
        PoseStack poseStack, SubmitNodeCollector collector, int light, int seed,
        Minecraft mc, Level level, float rotation
    ) {
        if (item.isEmpty()) return;
        poseStack.pushPose();
        poseStack.scale(0.5F, 0.5F, 0.5F);
        poseStack.translate((float) x, (float) y, (float) z);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        ItemStackRenderState itemRenderState = new ItemStackRenderState();
        mc.getItemModelResolver().updateForTopItem(itemRenderState, item, ItemDisplayContext.GROUND, level, null, seed);
        itemRenderState.submit(poseStack, collector, light, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }

    private static final class IronChestRenderState extends ChestRenderState {
        @Nullable
        private Material modMaterial;
        @Nullable
        private NonNullList<ItemStack> topStacks;
    }
}
