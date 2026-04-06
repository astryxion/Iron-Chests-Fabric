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
import net.minecraft.client.model.object.chest.ChestModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ChestEntityRenderer<T extends ChestBlockEntity> extends ChestRenderer<T> {
    private final SpriteGetter spriteGetter;
    private final ItemModelResolver itemModelResolver;
    private final ChestModel chestModel;

    public ChestEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        this.spriteGetter = context.sprites();
        this.itemModelResolver = context.itemModelResolver();
        this.chestModel = new ChestModel(context.bakeLayer(ModelLayers.CHEST));
    }

    @Override
    public ChestRenderState createRenderState() {
        return new GenericChestRenderState();
    }

    @Override
    public void extractRenderState(T entity, ChestRenderState state, float tickDelta, Vec3 cameraPos, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        super.extractRenderState(entity, state, tickDelta, cameraPos, crumblingOverlay);
        GenericChestRenderState renderState = (GenericChestRenderState) state;
        if (entity instanceof GenericChestEntity chest) {
            state.open = chest.getOpenNess(tickDelta);
        }
        BlockState blockState = entity.getLevel() != null
            ? entity.getBlockState()
            : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH).setValue(ChestBlock.TYPE, ChestType.SINGLE);
        renderState.blockStateForSubmit = blockState;
        if (blockState.getBlock() instanceof GenericChestBlock chest) {
            renderState.customSpriteId = new SpriteId(Sheets.CHEST_SHEET, chest.getType().texture);
        } else {
            renderState.customSpriteId = Sheets.chooseSprite(state.material, state.type);
        }
        if (entity instanceof CrystalChestEntity crystal) {
            renderState.topStacks = crystal.getTopStacks();
            renderState.renderItems = true;
        } else {
            renderState.topStacks = null;
            renderState.renderItems = false;
        }
        renderState.itemRenderSeed = (int) entity.getBlockPos().asLong();
    }

    @Override
    public void submit(ChestRenderState state, PoseStack poseStack, SubmitNodeCollector queue, CameraRenderState cameraRenderState) {
        if (!(state instanceof GenericChestRenderState renderState) || !(renderState.blockStateForSubmit.getBlock() instanceof GenericChestBlock)) {
            super.submit(state, poseStack, queue, cameraRenderState);
            return;
        }

        SpriteId spriteId = renderState.customSpriteId;

        poseStack.pushPose();
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(-state.facing.toYRot()));
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        // 1.21.11 ChestEntityRenderer order: chest model first, then floating items (same OrderedRenderCommandQueue usage).
        queue.submitModel(
            this.chestModel,
            state.open,
            poseStack,
            state.lightCoords,
            OverlayTexture.NO_OVERLAY,
            -1,
            spriteId,
            this.spriteGetter,
            0,
            state.breakProgress
        );

        if (renderState.renderItems && renderState.topStacks != null) {
            renderItems(poseStack, renderState.topStacks, queue, state.lightCoords, renderState.itemRenderSeed);
        }

        poseStack.popPose();
    }

    private void renderItems(PoseStack poseStack, NonNullList<ItemStack> inv, SubmitNodeCollector queue, int light, int seed) {
        int counter = 0;
        for (int j = 0; j < 3; j++) {
            renderItem(0.55, 0.3 + (j * 0.5), 0.7, inv, counter, poseStack, queue, light, seed);
            counter++;
        }
        for (int j = 0; j < 3; j++) {
            renderItem(1.4, 0.3 + (j * 0.5), 0.7, inv, counter, poseStack, queue, light, seed);
            counter++;
        }
        for (int j = 0; j < 3; j++) {
            renderItem(0.55, 0.3 + (j * 0.5), 1.4, inv, counter, poseStack, queue, light, seed);
            counter++;
        }
        for (int j = 0; j < 3; j++) {
            renderItem(1.4, 0.3 + (j * 0.5), 1.4, inv, counter, poseStack, queue, light, seed);
            counter++;
        }
    }

    private void renderItem(double x, double y, double z, NonNullList<ItemStack> inv, int counter, PoseStack poseStack, SubmitNodeCollector queue, int light, int seed) {
        poseStack.pushPose();
        ItemStack item = inv.get(counter);
        if (item.isEmpty()) {
            poseStack.popPose();
            return;
        }
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.translate(x, y, z);
        Level level = Minecraft.getInstance().level;
        float tickDelta = Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaTicks();
        long time = level != null ? level.getGameTime() : 0L;
        poseStack.mulPose(Axis.YP.rotationDegrees(time + tickDelta));
        if (level != null) {
            ItemStackRenderState itemRenderState = new ItemStackRenderState();
            itemModelResolver.updateForTopItem(itemRenderState, item, ItemDisplayContext.GROUND, level, null, seed);
            itemRenderState.submit(poseStack, queue, light, OverlayTexture.NO_OVERLAY, 0);
        }
        poseStack.popPose();
    }

    private static final class GenericChestRenderState extends ChestRenderState {
        private BlockState blockStateForSubmit;
        private SpriteId customSpriteId;
        private boolean renderItems;
        private int itemRenderSeed;
        @Nullable
        private NonNullList<ItemStack> topStacks;
    }
}
