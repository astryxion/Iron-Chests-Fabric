package astryxion.ironchest.blocks.blockentities;

import astryxion.ironchest.blocks.ChestTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class DiamondChestEntity extends GenericChestEntity {
    public DiamondChestEntity(BlockPos pos, BlockState state) {
        super(ChestTypes.DIAMOND, pos, state);
    }
}