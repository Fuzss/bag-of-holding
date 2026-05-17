package fuzs.bagofholding.world.item;

import fuzs.bagofholding.BagOfHolding;
import fuzs.bagofholding.config.ServerConfig;
import fuzs.bagofholding.network.ClientboundLockSlotMessage;
import fuzs.bagofholding.world.inventory.BagItemMenu;
import fuzs.bagofholding.world.inventory.LockableInventorySlot;
import fuzs.iteminteractions.common.api.v2.world.item.storage.ItemStorageHolder;
import fuzs.puzzleslib.common.api.container.v1.ContainerMenuHelper;
import fuzs.puzzleslib.common.api.network.v4.MessageSender;
import fuzs.puzzleslib.common.api.network.v4.PlayerSet;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;

public class BagOfHoldingItem extends Item {

    public BagOfHoldingItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        if (player.isSecondaryUseActive() || !BagOfHolding.CONFIG.get(ServerConfig.class).sneakToOpenBag) {
            ItemStack itemInHand = player.getItemInHand(interactionHand);
            if (level instanceof ServerLevel) {
                ContainerMenuHelper.openMenu(player, this.getMenuProvider(itemInHand), itemInHand.typeHolder());
                player.awardStat(Stats.ITEM_USED.get(this));
                this.lockMySlot((ServerPlayer) player, itemInHand);
            }

            player.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + level.getRandom().nextFloat() * 0.4F);
            return InteractionResult.SUCCESS;
        } else {
            return super.use(level, player, interactionHand);
        }
    }

    private MenuProvider getMenuProvider(ItemStack itemStack) {
        return new SimpleMenuProvider((int containerId, Inventory inventory, Player player) -> {
            ItemStorageHolder holder = ItemStorageHolder.ofItem(itemStack);
            Container container = holder.getItemContainer(itemStack, player);
            return new BagItemMenu(containerId, inventory, container, holder);
        }, itemStack.getHoverName());
    }

    private void lockMySlot(ServerPlayer serverPlayer, ItemStack itemStack) {
        if (serverPlayer.containerMenu instanceof BagItemMenu menu) {
            NonNullList<ItemStack> items = menu.getItems();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) == itemStack) {
                    if (menu.getSlot(i) instanceof LockableInventorySlot slot) {
                        slot.lock();
                        MessageSender.broadcast(PlayerSet.ofPlayer(serverPlayer),
                                new ClientboundLockSlotMessage(menu.containerId, i));
                    } else {
                        BagOfHolding.LOGGER.warn("Unable to lock bag slot at {} in container {} containing {}",
                                i,
                                menu,
                                itemStack);
                    }

                    break;
                }
            }
        }
    }

    /**
     * @see net.minecraft.world.item.BlockItem#onDestroyed(ItemEntity)
     */
    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        ItemContainerContents container = itemEntity.getItem()
                .set(DataComponents.CONTAINER, ItemContainerContents.EMPTY);
        if (container != null) {
            ItemUtils.onContainerDestroyed(itemEntity, container.nonEmptyItemCopyStream());
        }
    }
}
