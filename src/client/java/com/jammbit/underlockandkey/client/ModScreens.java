package com.jammbit.underlockandkey.client;

import com.jammbit.underlockandkey.client.rendering.inventory.LockVaultScreen;
import com.jammbit.underlockandkey.menu.ModMenuType;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public class ModScreens implements ClientModInitializer{
    @Override
	public void onInitializeClient() {
		MenuScreens.register(ModMenuType.VAULT_BLOCK, LockVaultScreen::new);
	}
}
