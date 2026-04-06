package astryxion.ironchest.blocks.blockentities;

import astryxion.ironchest.blocks.ChestTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ObsidianChestEntity extends GenericChestEntity {
    public ObsidianChestEntity(BlockPos pos, BlockState state) {
        super(ChestTypes.OBSIDIAN, pos, state);
    }
}
