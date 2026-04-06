package astryxion.ironchest.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class GenericChestBlock extends ChestBlock {
    private final ChestTypes type;

    public GenericChestBlock(BlockBehaviour.Properties settings, ChestTypes type) {
        super(type::getBlockEntityType, getOpenSound(type), getCloseSound(type), settings);
        this.type = type;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return this.type.makeEntity(pos, state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction direction = ctx.getHorizontalDirection().getOpposite();
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        return this.defaultBlockState()
            .setValue(FACING, direction)
            .setValue(TYPE, ChestType.SINGLE)
            .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    public ChestTypes getType() {
        return type;
    }

    private static SoundEvent getOpenSound(ChestTypes type) {
        return usesCopperChestSound(type) ? SoundEvents.COPPER_CHEST_OPEN : SoundEvents.CHEST_OPEN;
    }

    private static SoundEvent getCloseSound(ChestTypes type) {
        return usesCopperChestSound(type) ? SoundEvents.COPPER_CHEST_CLOSE : SoundEvents.CHEST_CLOSE;
    }

    private static boolean usesCopperChestSound(ChestTypes type) {
        return type == ChestTypes.COPPER
            || type == ChestTypes.IRON
            || type == ChestTypes.GOLD
            || type == ChestTypes.NETHERITE;
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        BlockState updatedState = super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        return updatedState.setValue(TYPE, ChestType.SINGLE);
    }

    @Override
    public boolean chestCanConnectTo(BlockState state) {
        return false;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
        return super.getTicker(world, state, type);
    }
}
