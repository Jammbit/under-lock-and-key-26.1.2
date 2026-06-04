package com.jammbit.underlockandkey.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class VaultBlockEntity extends BlockEntity {
    public VaultBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.VAULT_BLOCK_ENTITY, pos, state);
	}
}
