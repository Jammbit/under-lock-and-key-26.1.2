package com.jammbit.underlockandkey.blocks;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.jammbit.underlockandkey.container.ImplementedContainer;
import com.jammbit.underlockandkey.menu.custom.VaultBlockMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class VaultBlockEntity extends BlockEntity implements ImplementedContainer, MenuProvider {

	public static final int CONTAINER_SIZE = 3 * 9;
	private final NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
	

    public VaultBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.VAULT_BLOCK_ENTITY, pos, state);
	}

	@Override
	@NonNull
	public Component getDisplayName() {
		return Component.translatable("block.underlockandkey.lock_vault");
	}

	@Override
	public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
		return new VaultBlockMenu(containerId, inventory, this);
	}

	@Override
	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		ContainerHelper.loadAllItems(input, this.items);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		ContainerHelper.saveAllItems(output, this.items);
		super.saveAdditional(output);
	}



}
