package astryxion.ironchest.mixin;

import astryxion.ironchest.screenhandlers.ChestScreenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerEntityViewingMixin {
    @Inject(
        method = "hasContainerOpen(Lnet/minecraft/world/level/block/entity/ContainerOpenersCounter;Lnet/minecraft/core/BlockPos;)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    private void ironchest$matchChestScreen(ContainerOpenersCounter manager, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        Player player = (Player) (Object) this;
        if (!(player.containerMenu instanceof ChestScreenHandler handler)) {
            return;
        }

        Level level = player.level();
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof ChestBlockEntity)) {
            cir.setReturnValue(false);
            return;
        }

        Container inventory = handler.getBlockInventory();
        if (inventory == blockEntity) {
            cir.setReturnValue(true);
            return;
        }

        if (inventory instanceof CompoundContainer compoundContainer && compoundContainer.contains((Container) blockEntity)) {
            cir.setReturnValue(true);
            return;
        }

        cir.setReturnValue(false);
    }
}
