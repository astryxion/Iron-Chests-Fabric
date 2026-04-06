package astryxion.ironchest.mixin;

import astryxion.ironchest.screenhandlers.ChestScreenHandler;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.level.block.entity.ChestBlockEntity$1")
public abstract class ChestBlockEntityViewerCountMixin {
    @Shadow
    @Final
    private ChestBlockEntity this$0;

    @Inject(method = "isOwnContainer", at = @At("HEAD"), cancellable = true)
    private void ironchest$checkCustomScreenHandler(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (!(player.containerMenu instanceof ChestScreenHandler handler)) {
            return;
        }

        Container inventory = handler.getBlockInventory();
        if (inventory == this.this$0) {
            cir.setReturnValue(true);
            return;
        }

        if (inventory instanceof CompoundContainer compoundContainer && compoundContainer.contains(this.this$0)) {
            cir.setReturnValue(true);
        }
    }
}
