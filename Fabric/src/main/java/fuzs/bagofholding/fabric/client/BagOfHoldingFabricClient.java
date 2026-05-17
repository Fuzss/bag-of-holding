package fuzs.bagofholding.fabric.client;

import fuzs.bagofholding.common.BagOfHolding;
import fuzs.bagofholding.common.client.BagOfHoldingClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class BagOfHoldingFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(BagOfHolding.MOD_ID, BagOfHoldingClient::new);
    }
}
