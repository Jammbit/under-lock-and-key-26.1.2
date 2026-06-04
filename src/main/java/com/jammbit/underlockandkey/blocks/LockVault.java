package com.jammbit.underlockandkey.blocks;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LockVault extends BaseEntityBlock {

    public LockVault(Properties settings){
        super(settings);
    }

    @Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec(LockVault::new);
	}

    @Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new VaultBlockEntity(pos, state);
	}
}
