package astryxion.ironchest.mixin;

import java.util.function.Function;

import astryxion.ironchest.IronChests;
import astryxion.ironchest.blocks.ChestTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mod metal chests: same as 1.21.11 {@code TexturedRenderLayers.getChest()} → solid + z-offset on the chest atlas.
 * Crystal: {@link RenderTypes#entityCutoutZOffset} so low-alpha pixels are discarded (26.1 {@code entitySolidZOffsetForward}
 * treats the surface as opaque; crystal looked like a solid blue block).
 */
@Environment(EnvType.CLIENT)
@Mixin(SpriteId.class)
public abstract class ChestModelRendererMixin {
    @Inject(method = "renderType", at = @At("HEAD"), cancellable = true)
    private void ironchest$useChestRenderType(Function<Identifier, RenderType> function, CallbackInfoReturnable<RenderType> cir) {
        SpriteId self = (SpriteId) (Object) this;
        if (!Sheets.CHEST_SHEET.equals(self.atlasLocation()) || !IronChests.MOD_ID.equals(self.texture().getNamespace())) {
            return;
        }
        Identifier texture = self.texture();
        if (isCrystalChestTexture(texture)) {
            cir.setReturnValue(RenderTypes.entityCutoutZOffset(Sheets.CHEST_SHEET));
        } else {
            cir.setReturnValue(RenderTypes.entitySolidZOffsetForward(Sheets.CHEST_SHEET));
        }
    }

    private static boolean isCrystalChestTexture(Identifier texture) {
        String path = texture.getPath();
        return ChestTypes.CRYSTAL.texture.equals(texture)
            || path.equals("crystal_chest")
            || path.endsWith("/crystal_chest");
    }
}
