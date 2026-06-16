package com.jammbit.underlockandkey.blocks;

import org.jetbrains.annotations.Nullable;

import com.jammbit.underlockandkey.items.ModItems;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class LockVault extends BaseEntityBlock {
    public LockVault(Properties settings){
        super(settings);
    }

    @Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec(LockVault::new);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {

		if (!level.isClientSide() && level.getBlockEntity(pos) instanceof VaultBlockEntity lockVault) {
			// System.out.println("Interaction success!");
			if (!lockVault.isLocked()){
				player.openMenu(lockVault);
				return InteractionResult.SUCCESS;
			}
			else {
				player.sendOverlayMessage(net.minecraft.network.chat.Component.literal("This vault is locked!"));
				return InteractionResult.SUCCESS;
			}
		} 
		return InteractionResult.PASS;
	}

	@Override
	protected InteractionResult useItemOn(final ItemStack itemStack, final BlockState state, final Level level, final BlockPos pos, final Player player, final InteractionHand hand, final BlockHitResult hitResult) {
    	
		if (!level.isClientSide() && level.getBlockEntity(pos) instanceof VaultBlockEntity lockVault) {

			if (itemStack.getItem() == ModItems.VAULTKEY) {
				// System.out.println("Check if key success!");
				if (lockVault.getKey() == null) {
					// System.out.println("Assigning key success!");
					lockVault.setKey(itemStack.getHoverName().getString());
					player.sendOverlayMessage(net.minecraft.network.chat.Component.literal("Key assigned!"));
					return InteractionResult.SUCCESS;
				} else {
					// System.out.println("is \"" + lockVault.getKey() + "\" equal to \"" + itemStack.getItemName().getString() + "\" ?");
					if (itemStack.getHoverName().getString().equals(lockVault.getKey())) {
						// System.out.println("Unlock Success!");
						lockVault.setLocked(!lockVault.isLocked());
						// System.out.println(lockVault.isLocked());
						if (lockVault.isLocked())
							player.sendOverlayMessage(net.minecraft.network.chat.Component.literal("Locked!"));
						else
							player.sendOverlayMessage(net.minecraft.network.chat.Component.literal("Unlocked!"));

						return InteractionResult.SUCCESS;
					}
				}
			}
		}
		return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new VaultBlockEntity(pos, state);
	}
}
