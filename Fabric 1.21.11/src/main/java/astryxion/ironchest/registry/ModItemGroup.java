package astryxion.ironchest.registry;

import astryxion.ironchest.IronChests;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class ModItemGroup {
    public static void registerItemGroup() {
        Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            IronChests.TAB,
            FabricItemGroup.builder()
                .icon(() -> new ItemStack(ModBlocks.IRON_CHEST))
                .title(Component.translatable("itemGroup.ironchest.general"))
                .build()
        );
    }
}
