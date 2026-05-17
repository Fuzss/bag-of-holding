package fuzs.bagofholding.common.client;

import fuzs.bagofholding.common.BagOfHolding;
import fuzs.bagofholding.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.MenuScreensContext;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;

public class BagOfHoldingClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        ConfigHolder.registerConfigurationScreen(BagOfHolding.MOD_ID, "iteminteractions");
    }

    @Override
    public void onRegisterMenuScreens(MenuScreensContext context) {
        context.registerMenuScreen(ModRegistry.BAG_MENU_TYPE.value(), ContainerScreen::new);
    }
}
