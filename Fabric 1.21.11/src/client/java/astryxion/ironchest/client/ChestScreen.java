package astryxion.ironchest.client;

import astryxion.ironchest.screenhandlers.ChestGuiLayout;
import astryxion.ironchest.screenhandlers.ChestGuiLayout.LayoutKind;
import astryxion.ironchest.screenhandlers.ChestScreenHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class ChestScreen extends AbstractContainerScreen<ChestScreenHandler> {
    private final int containerRows;
    private final int containerColumns;
    private final LayoutKind layoutKind;

    public ChestScreen(ChestScreenHandler menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.imageWidth = ChestGuiLayout.panelWidth(menu.getChestColumns());
        this.imageHeight = ChestGuiLayout.screenHeight(menu.getChestRows());
        this.containerRows = menu.getChestRows();
        this.containerColumns = menu.getChestColumns();
        this.layoutKind = ChestGuiLayout.layoutKind(this.containerColumns, this.containerRows);
        this.inventoryLabelY = this.imageHeight - 94;
        if (this.layoutKind == LayoutKind.WIDE_STRIPS) {
            this.inventoryLabelX = ChestGuiLayout.playerInventoryX(this.containerColumns);
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int xo = this.leftPos;
        int yo = this.topPos;

        switch (this.layoutKind) {
            case VANILLA_BLIT -> blitVanillaFullPanel(graphics, xo, yo);
            case TALL_VANILLA_ROWS -> renderTallVanillaLayout(graphics, xo, yo);
            case WIDE_STRIPS -> renderWideStripLayout(graphics, xo, yo);
        }
    }

    private void blitVanillaFullPanel(GuiGraphics graphics, int xo, int yo) {
        int bodyHeight = bodyHeight();
        blitVanillaRegion(graphics, xo, yo, this.imageWidth, bodyHeight, 0.0F, 0.0F);
        blitVanillaPlayerFooter(graphics, xo, yo + bodyHeight, this.imageWidth, this.imageHeight - bodyHeight);
    }

    private void renderTallVanillaLayout(GuiGraphics graphics, int xo, int yo) {
        int panelWidth = this.imageWidth;
        blitVanillaRegion(graphics, xo, yo, panelWidth, ChestGuiLayout.TITLE_HEIGHT, 0.0F, 0.0F);

        for (int row = 0; row < this.containerRows; row++) {
            int rowY = yo + ChestGuiLayout.TITLE_HEIGHT + row * ChestGuiLayout.ROW_HEIGHT;
            float textureV = ChestGuiLayout.usesVanillaChestRow(this.containerColumns, row)
                ? ChestGuiLayout.TITLE_HEIGHT + row * ChestGuiLayout.ROW_HEIGHT
                : ChestGuiLayout.TITLE_HEIGHT + 5 * ChestGuiLayout.ROW_HEIGHT;
            blitVanillaRegion(graphics, xo, rowY, panelWidth, ChestGuiLayout.ROW_HEIGHT, 0.0F, textureV);
        }

        blitTallPlayerFooter(graphics, xo, yo);
    }

    private void renderWideStripLayout(GuiGraphics graphics, int xo, int yo) {
        int panelWidth = this.imageWidth;

        blitWideStrip(graphics, ChestGuiLayout.wideTitleTexture(this.containerColumns), xo, yo, panelWidth, ChestGuiLayout.TITLE_HEIGHT);

        for (int row = 0; row < this.containerRows; row++) {
            int rowY = yo + ChestGuiLayout.TITLE_HEIGHT + row * ChestGuiLayout.ROW_HEIGHT;
            blitWideStrip(graphics, ChestGuiLayout.wideRowTexture(this.containerColumns), xo, rowY, panelWidth, ChestGuiLayout.ROW_HEIGHT);
        }

        renderWidePlayerFooter(graphics, xo, yo + bodyHeight(), panelWidth);
    }

    private void renderWidePlayerFooter(GuiGraphics graphics, int xo, int footerY, int panelWidth) {
        blitVanillaPlayerFooter(graphics, playerFooterX(xo), footerY, ChestGuiLayout.VANILLA_PANEL_WIDTH, ChestGuiLayout.PLAYER_PANEL_HEIGHT);
        blitWideStrip(graphics, ChestGuiLayout.widePlayerTexture(this.containerColumns), xo, footerY, panelWidth, ChestGuiLayout.WIDE_FOOTER_FRAME_HEIGHT);
    }

    private void blitTallPlayerFooter(GuiGraphics graphics, int xo, int yo) {
        int bodyHeight = bodyHeight();
        blitVanillaPlayerFooter(graphics, playerFooterX(xo), yo + bodyHeight, ChestGuiLayout.VANILLA_PANEL_WIDTH, this.imageHeight - bodyHeight);
    }

    private int bodyHeight() {
        return this.containerRows * ChestGuiLayout.ROW_HEIGHT + ChestGuiLayout.TITLE_HEIGHT;
    }

    private int playerFooterX(int xo) {
        return xo + ChestGuiLayout.playerInventoryX(this.containerColumns) - ChestGuiLayout.LEFT_INSET;
    }

    private static void blitWideStrip(GuiGraphics graphics, Identifier texture, int x, int y, int width, int height) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, texture, x, y, 0.0F, 0.0F, width, height, width, height);
    }

    private static void blitVanillaRegion(GuiGraphics graphics, int x, int y, int width, int height, float u, float v) {
        graphics.blit(
            RenderPipelines.GUI_TEXTURED,
            ChestGuiLayout.VANILLA_BACKGROUND,
            x,
            y,
            u,
            v,
            width,
            height,
            ChestGuiLayout.TEXTURE_SIZE,
            ChestGuiLayout.TEXTURE_SIZE
        );
    }

    private static void blitVanillaPlayerFooter(GuiGraphics graphics, int x, int y, int width, int height) {
        blitVanillaRegion(graphics, x, y, width, height, 0.0F, ChestGuiLayout.PLAYER_PANEL_V);
    }
}
