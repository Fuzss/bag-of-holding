package fuzs.bagofholding.fabric;

import fuzs.bagofholding.common.BagOfHolding;
import fuzs.bagofholding.fabric.init.FabricModRegistry;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class BagOfHoldingFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        FabricModRegistry.bootstrap();
        ModConstructor.construct(BagOfHolding.MOD_ID, BagOfHolding::new);
    }
}
