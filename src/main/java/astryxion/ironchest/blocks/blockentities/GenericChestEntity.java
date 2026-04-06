package astryxion.ironchest.blocks.blockentities;

import astryxion.ironchest.blocks.ChestTypes;
import astryxion.ironchest.mixin.ChestBlockEntityAccessor;
import astryxion.ironchest.screenhandlers.ChestScreenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import org.jetbrains.annotations.Nullable;


public class GenericChestEntity extends ChestBlockEntity {
    ChestTypes type;

    public GenericChestEntity(ChestTypes type, BlockPos pos, BlockState state) {
        super(type.getBlockEntityType(), pos, state);
        this.type = type;
        // Super creates its own private items list; vanilla load/save only touch that field. Route getItems/setItems there.
        setItems(NonNullList.withSize(type.size, ItemStack.EMPTY));
    }

    private ChestBlockEntityAccessor ironchestAccessor() {
        return (ChestBlockEntityAccessor) (Object) this;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return ironchestAccessor().ironchestGetChestItemStacks();
    }

    @Override
    protected void setItems(NonNullList<ItemStack> list) {
        ironchestAccessor().ironchestSetChestItemStacks(list);
    }

    @Override
    public int getContainerSize() {
        return type.size;
    }

    @Override
    protected AbstractContainerMenu createMenu(int syncId, Inventory inventory) {
        return new ChestScreenHandler(type.getScreenHandlerType(), type, syncId, inventory, ContainerLevelAccess.create(getLevel(), getBlockPos()));
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }

    public ChestTypes type() {
        return type;
    }

    @Override
    public void setChanged() {
        super.setChanged();

        if (this.getLevel() != null && !this.getLevel().isClientSide() && this.getLevel() instanceof ServerLevel) {
            ((ServerLevel) getLevel()).getChunkSource().blockChanged(getBlockPos());
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) {
        // 1.21.11 toInitialChunkDataNbt used createNbt so clients (crystal preview) receive inventory. Default
        // BlockEntity.getUpdateTag is empty; super chain did not add container data.
        return saveWithoutMetadata(registryLookup);
    }
}
