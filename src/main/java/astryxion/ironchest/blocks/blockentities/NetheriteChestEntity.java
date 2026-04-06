package astryxion.ironchest.blocks.blockentities;

import astryxion.ironchest.blocks.ChestTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class NetheriteChestEntity extends GenericChestEntity {
    public NetheriteChestEntity(BlockPos pos, BlockState state) {
        super(ChestTypes.NETHERITE, pos, state);
    }
}
