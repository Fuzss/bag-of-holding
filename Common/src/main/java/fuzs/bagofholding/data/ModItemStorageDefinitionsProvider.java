package fuzs.bagofholding.data;

import fuzs.bagofholding.init.ModRegistry;
import fuzs.bagofholding.world.item.BagType;
import fuzs.bagofholding.world.item.container.BagContainerStorage;
import fuzs.iteminteractions.common.api.v2.data.AbstractItemStorageDefinitionsProvider;
import fuzs.iteminteractions.common.api.v2.world.item.storage.StorageOptions;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;

public class ModItemStorageDefinitionsProvider extends AbstractItemStorageDefinitionsProvider {

    public ModItemStorageDefinitionsProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addItemStorageDefinitions(HolderLookup.Provider registries) {
        this.add(BagType.LEATHER, ModRegistry.LEATHER_BAG_OF_HOLDING_ITEM.value());
        this.add(BagType.IRON, ModRegistry.IRON_BAG_OF_HOLDING_ITEM.value());
        this.add(BagType.GOLDEN, ModRegistry.GOLDEN_BAG_OF_HOLDING_ITEM.value());
    }

    public void add(BagType bagType, Item item) {
        this.add(new BagContainerStorage(bagType,
                bagType.fallbackColor,
                StorageOptions.DEFAULT.setFilterContainerItems()), item);
    }
}
