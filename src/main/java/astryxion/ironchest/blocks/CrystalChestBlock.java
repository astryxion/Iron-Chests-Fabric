package astryxion.ironchest.blocks;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class CrystalChestBlock extends GenericChestBlock {
    public CrystalChestBlock(BlockBehaviour.Properties settings) {
        super(settings, ChestTypes.CRYSTAL);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return super.getTicker(world, state, type);
    }
}
