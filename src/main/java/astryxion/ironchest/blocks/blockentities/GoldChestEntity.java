package astryxion.ironchest.blocks.blockentities;

import astryxion.ironchest.blocks.ChestTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class GoldChestEntity extends GenericChestEntity {
    public GoldChestEntity(BlockPos pos, BlockState state) {
        super(ChestTypes.GOLD, pos, state);
    }
}
