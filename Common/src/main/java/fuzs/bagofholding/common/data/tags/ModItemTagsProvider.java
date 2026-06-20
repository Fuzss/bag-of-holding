package fuzs.bagofholding.common.data.tags;

import fuzs.bagofholding.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;

public class ModItemTagsProvider extends AbstractTagProvider<Item> {

    public ModItemTagsProvider(DataProviderContext context) {
        super(Registries.ITEM, context);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.VANISHING_ENCHANTABLE).addTag(ModRegistry.BAGS_ITEM_TAG);
        this.tag(ModRegistry.BAGS_ITEM_TAG)
                .add(ModRegistry.LEATHER_BAG_OF_HOLDING_ITEM,
                        ModRegistry.IRON_BAG_OF_HOLDING_ITEM,
                        ModRegistry.GOLDEN_BAG_OF_HOLDING_ITEM);
        this.tag(ModRegistry.RECIPES_IGNORE_COMPONENTS_ITEM_TAG).addTag(ModRegistry.BAGS_ITEM_TAG);
    }
}
