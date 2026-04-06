package astryxion.ironchest.blocks.blockentities;

import astryxion.ironchest.blocks.ChestTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class IronChestEntity extends GenericChestEntity {
    public IronChestEntity(BlockPos pos, BlockState state) {
        super(ChestTypes.IRON, pos, state);
    }
}