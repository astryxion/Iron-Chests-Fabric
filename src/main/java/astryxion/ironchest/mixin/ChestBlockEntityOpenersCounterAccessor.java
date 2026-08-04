package astryxion.ironchest.mixin;

import net.minecraft.world.level.block.entity.ChestBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.minecraft.world.level.block.entity.ChestBlockEntity$1")
public interface ChestBlockEntityOpenersCounterAccessor {
    @Accessor("field_27211")
    ChestBlockEntity ironchest$getChestBlockEntity();
}
