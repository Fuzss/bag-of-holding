package fuzs.bagofholding.common.data.client;

import fuzs.bagofholding.common.BagOfHolding;
import fuzs.bagofholding.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(ModRegistry.CREATIVE_MODE_TAB.value(), BagOfHolding.MOD_NAME);
        this.add(ModRegistry.LEATHER_BAG_OF_HOLDING_ITEM.value(), "Leather Bag of Holding");
        this.add(ModRegistry.IRON_BAG_OF_HOLDING_ITEM.value(), "Iron Bag of Holding");
        this.add(ModRegistry.GOLDEN_BAG_OF_HOLDING_ITEM.value(), "Golden Bag of Holding");
        this.add(ModRegistry.PRESERVATION_ENCHANTMENT, "Preservation");
        this.add(ModRegistry.PRESERVATION_ENCHANTMENT,
                "desc",
                "Prevents a bag of holding from being lost on death. The enchantment level is reduced by one each time.");
        this.add(ModRegistry.BAGS_ITEM_TAG, "Bags");
        this.add(ModRegistry.RECIPES_IGNORE_COMPONENTS_ITEM_TAG, "Recipes Ignore Components");
    }
}
