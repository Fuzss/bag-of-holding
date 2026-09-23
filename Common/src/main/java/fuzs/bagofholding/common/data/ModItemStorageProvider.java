package fuzs.bagofholding.common.data;

import fuzs.bagofholding.common.init.ModRegistry;
import fuzs.bagofholding.common.world.item.BagType;
import fuzs.bagofholding.common.world.item.storage.BagContainerStorage;
import fuzs.iteminteractions.common.api.v2.data.ItemStorageProvider;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorage;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Item;

public class ModItemStorageProvider implements ItemStorageProvider {

    @Override
    public void run(BootstrapContext<ItemStorage.Definition> output) {
        this.add(output, BagType.LEATHER, ModRegistry.LEATHER_BAG_OF_HOLDING_ITEM.value());
        this.add(output, BagType.IRON, ModRegistry.IRON_BAG_OF_HOLDING_ITEM.value());
        this.add(output, BagType.GOLDEN, ModRegistry.GOLDEN_BAG_OF_HOLDING_ITEM.value());
    }

    public void add(BootstrapContext<ItemStorage.Definition> output, BagType bagType, Item item) {
        this.add(output, new BagContainerStorage(bagType, bagType.color), item);
    }
}
