package astryxion.ironchest.items;

import astryxion.ironchest.IronChests;
import astryxion.ironchest.blocks.ChestTypes;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.core.Direction;

public class UpgradeItem extends Item {

    UpgradeTypes type;

    public UpgradeItem(UpgradeTypes type, Item.Properties settings) {
        super(settings);
        CreativeModeTabEvents.modifyOutputEvent(IronChests.TAB).register(entries -> entries.accept(this));
        this.type = type;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        Player player = context.getPlayer();
        if (player == null) {
            return InteractionResult.PASS;
        }

        BlockPos blockPos = context.getClickedPos();
        if (this.type.canUpgrade(ChestTypes.WOOD)) {
            if (!(level.getBlockState(blockPos).getBlock() instanceof ChestBlock)) {
                return InteractionResult.PASS;
            }
        } else {
            if (level.getBlockState(blockPos).getBlock() != ChestTypes.get(this.type.source)) {
                return InteractionResult.PASS;
            }
        }

        BlockEntity blockEntity = level.getBlockEntity(blockPos);

        ItemStack itemStack = context.getItemInHand();
        Direction chestFacing;

        if (blockEntity != null) {
            ChestBlockEntity chest = (ChestBlockEntity) blockEntity;

            if (ChestBlockEntity.getOpenCount(level, blockPos) > 0) {
                return InteractionResult.PASS;
            }
            if (!chest.stillValid(player)) {
                return InteractionResult.PASS;
            }

            BlockState oldState = level.getBlockState(blockPos);
            chestFacing = level.getBlockState(blockPos).getValue(ChestBlock.FACING);
            level.removeBlockEntity(blockPos);
            level.removeBlock(blockPos, false);
            level.addDestroyBlockEffect(blockPos, oldState);

            BlockState blockState = ChestTypes.get(type.target).defaultBlockState().setValue(ChestBlock.FACING, chestFacing).setValue(ChestBlock.WATERLOGGED, false);
            CompoundTag oldChestTag = chest.saveWithoutMetadata(level.registryAccess());
            level.setBlock(blockPos, blockState, 3);
            level.setBlocksDirty(blockPos, blockState, blockState);
            BlockEntity newBlockEntity = level.getBlockEntity(blockPos);
            if (newBlockEntity != null) {
                newBlockEntity.loadWithComponents(TagValueInput.create(ProblemReporter.DISCARDING, level.registryAccess(), oldChestTag));
                newBlockEntity.setChanged();
            }
            SoundType oldSounds = oldState.getSoundType();
            SoundType newSounds = blockState.getSoundType();
            level.playSound(null, blockPos, oldSounds.getBreakSound(), SoundSource.BLOCKS, oldSounds.getVolume(), oldSounds.getPitch());
            level.playSound(null, blockPos, newSounds.getPlaceSound(), SoundSource.BLOCKS, newSounds.getVolume(), newSounds.getPitch());
            itemStack.shrink(1);
        }
        return InteractionResult.PASS;
    }

}
