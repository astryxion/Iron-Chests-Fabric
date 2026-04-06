package astryxion.ironchest.mixin;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChestBlockEntity.class)
public interface ChestBlockEntityAccessor {
    @Accessor("items")
    void ironchestSetChestItemStacks(NonNullList<ItemStack> items);

    @Accessor("items")
    NonNullList<ItemStack> ironchestGetChestItemStacks();
}
