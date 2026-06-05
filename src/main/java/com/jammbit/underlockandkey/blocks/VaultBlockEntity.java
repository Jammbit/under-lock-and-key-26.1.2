package com.jammbit.underlockandkey.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import com.jammbit.container.ImplementedContainer.java;

public class VaultBlockEntity extends BlockEntity implements ImplementedContainer {

	public static final int CONTAINER_SIZE = 3 * 9;
	private final NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
	private String openKey = null;
	private boolean locked = false;

    public VaultBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.VAULT_BLOCK_ENTITY, pos, state);
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
