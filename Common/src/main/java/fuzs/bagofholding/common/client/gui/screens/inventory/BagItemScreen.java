package fuzs.bagofholding.common.client.gui.screens.inventory;

import fuzs.bagofholding.common.world.inventory.BagItemMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

/**
 * @see ContainerScreen
 */
public class BagItemScreen extends AbstractContainerScreen<BagItemMenu> {
    private static final Identifier CONTAINER_BACKGROUND = Identifier.withDefaultNamespace(
            "textures/gui/container/generic_54.png");

    private final int containerRows;

    public BagItemScreen(BagItemMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 114 + menu.getRowCount() * 18);
        this.containerRows = menu.getRowCount();
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED,
                CONTAINER_BACKGROUND,
                this.leftPos,
                this.topPos,
                0.0F,
                0.0F,
                this.imageWidth,
                17,
                256,
                256);
        for (int row = 0; row < this.containerRows; row++) {
            graphics.blit(RenderPipelines.GUI_TEXTURED,
                    CONTAINER_BACKGROUND,
                    this.leftPos,
                    this.topPos + row * AbstractContainerMenu.SLOT_SIZE + 17,
                    0.0F,
                    17.0F + (row % 6) * AbstractContainerMenu.SLOT_SIZE,
                    this.imageWidth,
                    AbstractContainerMenu.SLOT_SIZE,
                    256,
                    256);
        }

        graphics.blit(RenderPipelines.GUI_TEXTURED,
                CONTAINER_BACKGROUND,
                this.leftPos,
                this.topPos + this.containerRows * AbstractContainerMenu.SLOT_SIZE + 17,
                0.0F,
                126.0F,
                this.imageWidth,
                96,
                256,
                256);
    }
}
