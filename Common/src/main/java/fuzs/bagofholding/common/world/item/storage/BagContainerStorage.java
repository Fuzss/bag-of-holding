package fuzs.bagofholding.common.world.item.storage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.bagofholding.common.BagOfHolding;
import fuzs.bagofholding.common.config.ServerConfig;
import fuzs.bagofholding.common.init.ModRegistry;
import fuzs.bagofholding.common.world.item.BagType;
import fuzs.iteminteractions.common.api.v2.world.item.DyeBackedColor;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ContainerStorage;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorageType;
import fuzs.iteminteractions.common.api.v2.world.item.storage.StorageOptions;
import net.minecraft.util.Util;
import net.minecraft.world.entity.EquipmentSlotGroup;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class BagContainerStorage extends ContainerStorage {
    public static final MapCodec<BagContainerStorage> CODEC = RecordCodecBuilder.mapCodec(instance -> {
        return instance.group(BagType.CODEC.fieldOf("bag_type").forGetter(provider -> provider.bagType),
                        backgroundColorCodec(),
                        itemContentsCodec(),
                        interactionPermissionsCodec(),
                        equipmentSlotsCodec())
                .apply(instance,
                        (BagType bagType, Optional<DyeBackedColor> dyeColor, StorageOptions storageOptions, InteractionPermissions interactionPermissions, EquipmentSlotGroup equipmentSlots) -> {
                            return Util.make(new BagContainerStorage(bagType, dyeColor.orElse(null), storageOptions),
                                    (BagContainerStorage storage) -> storage.interactionPermissions(
                                            interactionPermissions).equipmentSlots(equipmentSlots));
                        });
    });

    private final BagType bagType;

    public BagContainerStorage(BagType bagType, @Nullable DyeBackedColor dyeColor, StorageOptions storageOptions) {
        super(dyeColor, storageOptions);
        this.bagType = bagType;
    }

    @Override
    public int getInventoryHeight() {
        return switch (this.bagType) {
            case LEATHER -> BagOfHolding.CONFIG.get(ServerConfig.class).leatherBagRows;
            case IRON -> BagOfHolding.CONFIG.get(ServerConfig.class).ironBagRows;
            case GOLDEN -> BagOfHolding.CONFIG.get(ServerConfig.class).goldenBagRows;
        };
    }

    @Override
    public ItemStorageType<?> getType() {
        return ModRegistry.BAG_ITEM_STORAGE_TYPE.value();
    }
}
