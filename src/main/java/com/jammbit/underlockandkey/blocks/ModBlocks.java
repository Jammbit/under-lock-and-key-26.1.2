package com.jammbit.underlockandkey.blocks;

import java.util.function.Function;

import com.jammbit.underlockandkey.UnderLockAndKey;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks{
    
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem){
        // Creates the registry key for the block
        ResourceKey<Block> blockKey = keyOfBlock(name);
        // Creates the block instance
        Block block = blockFactory.apply(settings.setId(blockKey));

        if (shouldRegisterItem){

            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(UnderLockAndKey.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(UnderLockAndKey.MOD_ID, name));
    }

    public static void initializeModBlocks() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((creativeTab) -> {
	    creativeTab.accept(ModBlocks.TESTBLOCK.asItem());
});
    }

    public static final Block TESTBLOCK = register(
        "test_block",
        Block::new,
        BlockBehaviour.Properties.of().sound(SoundType.GRASS),
        true
    );


}