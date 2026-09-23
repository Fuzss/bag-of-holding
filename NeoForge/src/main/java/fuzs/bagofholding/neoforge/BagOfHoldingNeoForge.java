package fuzs.bagofholding.neoforge;

import fuzs.bagofholding.common.BagOfHolding;
import fuzs.bagofholding.common.data.ModItemStorageProvider;
import fuzs.bagofholding.common.data.recipes.ModRecipeProvider;
import fuzs.bagofholding.common.data.tags.ModEnchantmentTagsProvider;
import fuzs.bagofholding.common.data.tags.ModItemTagsProvider;
import fuzs.bagofholding.common.init.ModRegistry;
import fuzs.bagofholding.neoforge.init.NeoForgeModRegistry;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorage;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.minecraft.core.registries.Registries;
import net.neoforged.fml.common.Mod;

@Mod(BagOfHolding.MOD_ID)
public class BagOfHoldingNeoForge {

    public BagOfHoldingNeoForge() {
        NeoForgeModRegistry.bootstrap();
        ModConstructor.construct(BagOfHolding.MOD_ID, BagOfHolding::new);
        DataProviderBuilder.of(BagOfHolding.MOD_ID)
                .add(Registries.ENCHANTMENT, ModRegistry::bootstrapEnchantments)
                .add(ItemStorage.Definition.REGISTRY_KEY, new ModItemStorageProvider())
                .addProvider(ModItemTagsProvider::new, ModEnchantmentTagsProvider::new)
                .addRecipeProvider(ModRecipeProvider::new);
    }
}
