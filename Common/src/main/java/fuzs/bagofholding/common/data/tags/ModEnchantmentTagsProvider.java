package fuzs.bagofholding.common.data.tags;

import fuzs.bagofholding.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;
import fuzs.puzzleslib.common.api.data.v2.tags.AbstractTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantmentTagsProvider extends AbstractTagProvider<Enchantment> {

    public ModEnchantmentTagsProvider(DataProviderContext context) {
        super(Registries.ENCHANTMENT, context);
    }

    @Override
    public void addTags(HolderLookup.Provider registries) {
        this.tag(EnchantmentTags.NON_TREASURE).add(ModRegistry.PRESERVATION_ENCHANTMENT);
    }
}
