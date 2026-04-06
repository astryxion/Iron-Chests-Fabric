package astryxion.ironchest.blocks.blockentities;

import astryxion.ironchest.blocks.ChestTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class EmeraldChestEntity extends GenericChestEntity {
    public EmeraldChestEntity(BlockPos pos, BlockState state) {
        super(ChestTypes.EMERALD, pos, state);
    }
}
