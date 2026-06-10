package com.jammbit.underlockandkey.menu.custom;

import com.jammbit.underlockandkey.menu.ModMenuType;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class VaultBlockMenu extends AbstractContainerMenu{
    private static final int SLOT_ROWS = 3;
    private static final int SLOT_COLUMNS = 9;
    private static final int SLOTS_COUNT = SLOT_ROWS * SLOT_COLUMNS;

	private static final int CONTAINER_START = 0;
	private static final int CONTAINER_END = SLOTS_COUNT;
	private static final int INVENTORY_START = CONTAINER_END;
	private static final int INVENTORY_END = INVENTORY_START + Inventory.INVENTORY_SIZE;

	private static final int CONTAINER_START_X = 8;
	private static final int CONTAINER_START_Y = 84;
	private static final int INVENTORY_START_X = 8;
	private static final int INVENTORY_START_Y = 84;

    private final Container container;

    //client
    public VaultBlockMenu(final int containerId, final Inventory inventory){
        this(containerId, inventory, new SimpleContainer(SLOTS_COUNT));
    }

    //server
    public VaultBlockMenu(final int containerId, final Inventory inventory, final Container container){
        super(ModMenuType.VAULT_BLOCK, containerId);
        checkContainerSize(container, SLOTS_COUNT);
        this.container = container;
        this.addSlots();
        this.addStandardInventorySlots(container, INVENTORY_START_X, INVENTORY_START_Y);
    }

    private void addSlots() {
        for (int y = 0; y < SLOT_ROWS; y++) {
			for (int x = 0; x < SLOT_COLUMNS; x++) {
				final int slot = x + y * SLOT_COLUMNS;
				this.addSlot(new Slot(
								this.container,
								slot,
								CONTAINER_START_X + x * SLOT_SIZE,
								CONTAINER_START_Y + y * SLOT_SIZE
				));
			}
		}
    }

    @Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		Slot slot = this.slots.get(slotIndex);

		if (!slot.hasItem()) {
			return ItemStack.EMPTY;
		}

		ItemStack stack = slot.getItem();
		ItemStack clicked = stack.copy();

		if (slotIndex < CONTAINER_END) {
			if (!this.moveItemStackTo(stack, INVENTORY_START, INVENTORY_END, /* backwards: */ true)) {
				return ItemStack.EMPTY;
			}
		} else {
			if (!this.moveItemStackTo(stack, CONTAINER_START, CONTAINER_END, /* backwards: */ false)) {
				return ItemStack.EMPTY;
			}
		}

		if (stack.isEmpty()) {
			slot.setByPlayer(ItemStack.EMPTY);
		} else {
			slot.setChanged();
		}

		return clicked;
	}

	@Override
	public boolean stillValid(Player player) {
		return this.container.stillValid(player);
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		this.container.stopOpen(player);
	}
}
