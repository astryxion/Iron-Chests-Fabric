package astryxion.ironchest.client;

import astryxion.ironchest.screenhandlers.ChestScreenHandler;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ChestScreen extends CottonInventoryScreen<ChestScreenHandler> {
    private boolean slotBackgroundsApplied;

    public ChestScreen(ChestScreenHandler description, Inventory inventory, Component title) {
        super(description, inventory, title);
    }

    @Override
    public void init() {
        super.init();
        applySlotBackgrounds();
    }

    @Override
    public void paintDescription(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
        if (!slotBackgroundsApplied) {
            applySlotBackgrounds();
        }
        super.paintDescription(context, mouseX, mouseY, delta);
    }

    private void applySlotBackgrounds() {
        if (menu != null && menu.getRootPanel() != null) {
            SlotBackgrounds.applyVanillaSlotBackgrounds(menu.getRootPanel());
            slotBackgroundsApplied = true;
        }
    }
}
