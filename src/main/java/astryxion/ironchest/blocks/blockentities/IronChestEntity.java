package astryxion.ironchest.blocks.blockentities;

import astryxion.ironchest.blocks.ChestTypes;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class IronChestEntity extends GenericChestEntity {
    public IronChestEntity(BlockPos pos, BlockState state) {
        super(ChestTypes.IRON, pos, state);
    }
}