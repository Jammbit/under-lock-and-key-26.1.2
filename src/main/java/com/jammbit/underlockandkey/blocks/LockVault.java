package com.jammbit.underlockandkey.blocks;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class LockVault extends BaseEntityBlock {
	private String openKey = null;
	private boolean locked = false;

    public LockVault(Properties settings){
        super(settings);
    }

    @Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec(LockVault::new);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		
		if (!locked && !level.isClientSide() && level.getBlockEntity(pos) instanceof VaultBlockEntity lockVault){
			player.openMenu(lockVault);
		}

		return InteractionResult.SUCCESS;
	}

    @Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new VaultBlockEntity(pos, state);
	}
}
