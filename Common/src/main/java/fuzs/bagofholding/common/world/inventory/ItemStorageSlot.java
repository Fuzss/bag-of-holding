package fuzs.bagofholding.common.world.inventory;

import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorageHolder;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ItemStorageSlot extends Slot {
    private final ItemStorageHolder holder;

    public ItemStorageSlot(ItemStorageHolder holder, Container container, int slot, int x, int y) {
        super(container, slot, x, y);
        this.holder = holder;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return this.holder.storage().isItemAllowedInContainer(itemStack);
    }
}
