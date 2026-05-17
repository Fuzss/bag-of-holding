package fuzs.bagofholding.common.client.gui.screens.inventory;

import fuzs.bagofholding.common.world.inventory.BagItemMenu;
import fuzs.bagofholding.common.world.inventory.LockableInventorySlot;
import fuzs.iteminteractions.common.api.v2.client.gui.screens.inventory.tooltip.ClientItemContentsTooltip;
import fuzs.puzzleslib.common.api.client.key.v1.KeyMappingHelper;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;

public class BagItemScreen extends AbstractContainerScreen<BagItemMenu> {
    private static final Identifier CONTAINER_BACKGROUND = Identifier.withDefaultNamespace(
            "textures/gui/container/generic_54.png");

    private final int backgroundColor;

    public BagItemScreen(BagItemMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, DEFAULT_IMAGE_WIDTH, 114 + menu.getInventoryHeight() * 18);
        this.inventoryLabelY = this.imageHeight - 94;
        this.backgroundColor = ClientItemContentsTooltip.computeBackgroundColor(menu.getBackgroundColor());
    }

    @Override
    public void extractSlotHighlightBack(GuiGraphicsExtractor guiGraphics) {
        super.extractSlotHighlightBack(guiGraphics);
        this.renderLockableSlotHighlight(guiGraphics, SLOT_HIGHLIGHT_BACK_SPRITE);
    }

    @Override
    public void extractSlotHighlightFront(GuiGraphicsExtractor guiGraphics) {
        super.extractSlotHighlightFront(guiGraphics);
        this.renderLockableSlotHighlight(guiGraphics, SLOT_HIGHLIGHT_FRONT_SPRITE);
    }

    private void renderLockableSlotHighlight(GuiGraphicsExtractor guiGraphics, Identifier identifier) {
        for (Slot slot : this.menu.slots) {
            if (slot != this.hoveredSlot && slot.isHighlightable()) {
                if (slot instanceof LockableInventorySlot lockableSlot && lockableSlot.locked()) {
                    guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, identifier, slot.x - 4, slot.y - 4, 24, 24);
                }
            }
        }
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED,
                CONTAINER_BACKGROUND,
                this.leftPos,
                this.topPos,
                0,
                0,
                this.imageWidth,
                17,
                256,
                256,
                this.backgroundColor);
        int inventoryHeight = this.menu.getInventoryHeight();
        for (int k = 0; k < (int) Math.ceil(inventoryHeight / 6.0); k++) {
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED,
                    CONTAINER_BACKGROUND,
                    this.leftPos,
                    this.topPos + 17 + 18 * 6 * k,
                    0,
                    17,
                    this.imageWidth,
                    Math.min(inventoryHeight - 6 * k, 6) * 18,
                    256,
                    256,
                    this.backgroundColor);
        }

        guiGraphics.blit(RenderPipelines.GUI_TEXTURED,
                CONTAINER_BACKGROUND,
                this.leftPos,
                this.topPos + inventoryHeight * 18 + 17,
                0,
                126,
                this.imageWidth,
                96,
                256,
                256,
                this.backgroundColor);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY) {
        guiGraphics.text(this.font, this.title, this.titleLabelX, this.titleLabelY, -1, false);
        guiGraphics.text(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, -1, false);
    }

    @Override
    protected boolean checkHotbarKeyPressed(KeyEvent event) {
        // Prevent number keys from extracting items from a locked slot.
        // Vanilla only checks the hovered slot for being accessible, but the hotbar item is directly taken from the inventory, not from a slot,
        // therefore ignoring all restrictions put on the corresponding slot in the menu.
        // Also, the hotbar slot has a varying index as the player inventory is always added last, so we store the first hotbar slot during menu construction.
        if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null) {
            for (int i = 0; i < 9; ++i) {
                if (KeyMappingHelper.isKeyActiveAndMatches(this.minecraft.options.keyHotbarSlots[i], event)) {
                    if (this.menu.getSlot(this.menu.getHotbarStartIndex() + i) instanceof LockableInventorySlot slot
                            && slot.locked()) {
                        return true;
                    }
                }
            }
        }

        return super.checkHotbarKeyPressed(event);
    }

    public boolean isHoveredSlot(Slot slot) {
        return this.hoveredSlot == slot;
    }
}
