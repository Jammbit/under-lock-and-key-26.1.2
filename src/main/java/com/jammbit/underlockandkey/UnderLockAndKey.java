package com.jammbit.underlockandkey;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jammbit.underlockandkey.blocks.ModBlocks;
import com.jammbit.underlockandkey.items.ModItems;

public class UnderLockAndKey implements ModInitializer {
	public static final String MOD_ID = "under_lock_and_key";

	// This logger is used to write text to the console and the log file.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// This code runs as soon as Minecraft is in a mod-load-ready state.
	// However, some things (like resources) may still be uninitialized.
	@Override
	public void onInitialize() {

		ModItems.initializeModItems();
		ModBlocks.initializeModBlocks();
		
	}
}