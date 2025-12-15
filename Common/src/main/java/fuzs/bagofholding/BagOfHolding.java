package fuzs.bagofholding;

import fuzs.bagofholding.attachment.SoulboundItems;
import fuzs.bagofholding.config.ClientConfig;
import fuzs.bagofholding.config.ServerConfig;
import fuzs.bagofholding.init.ModRegistry;
import fuzs.bagofholding.network.ClientboundLockSlotMessage;
import fuzs.puzzleslib.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import fuzs.puzzleslib.api.core.v1.context.PayloadTypesContext;
import net.minecraft.resources.Identifier;
import fuzs.puzzleslib.api.event.v1.entity.living.LivingDropsCallback;
import fuzs.puzzleslib.api.event.v1.entity.player.PlayerCopyEvents;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BagOfHolding implements ModConstructor {
    public static final String MOD_ID = "bagofholding";
    public static final String MOD_NAME = "Bag Of Holding";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID)
            .client(ClientConfig.class)
            .server(ServerConfig.class);

    @Override
    public void onConstructMod() {
        ModRegistry.bootstrap();
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        PlayerCopyEvents.COPY.register(SoulboundItems::onCopy);
        LivingDropsCallback.EVENT.register(SoulboundItems::onLivingDrops);
    }

    @Override
    public void onRegisterPayloadTypes(PayloadTypesContext context) {
        context.playToClient(ClientboundLockSlotMessage.class, ClientboundLockSlotMessage.STREAM_CODEC);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
