package fuzs.bagofholding.neoforge;

import fuzs.bagofholding.common.BagOfHolding;
import fuzs.bagofholding.common.data.ModItemStorageDefinitionsProvider;
import fuzs.bagofholding.common.data.ModRecipeProvider;
import fuzs.bagofholding.common.data.tags.ModEnchantmentTagsProvider;
import fuzs.bagofholding.common.data.tags.ModItemTagsProvider;
import fuzs.bagofholding.common.init.ModRegistry;
import fuzs.bagofholding.neoforge.init.NeoForgeModRegistry;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.fml.common.Mod;

@Mod(BagOfHolding.MOD_ID)
public class BagOfHoldingNeoForge {

    public BagOfHoldingNeoForge() {
        NeoForgeModRegistry.bootstrap();
        ModConstructor.construct(BagOfHolding.MOD_ID, BagOfHolding::new);
        DataProviderHelper.registerDataProviders(BagOfHolding.MOD_ID,
                ModRegistry.REGISTRY_SET_BUILDER,
                ModItemTagsProvider::new,
                ModEnchantmentTagsProvider::new,
                ModRecipeProvider::new,
                ModItemStorageDefinitionsProvider::new);
    }
}
