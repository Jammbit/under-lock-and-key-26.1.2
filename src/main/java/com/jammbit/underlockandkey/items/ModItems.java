package com.jammbit.underlockandkey.items;

import java.util.function.Function;

import com.jammbit.underlockandkey.UnderLockAndKey;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ModItems {
    
    public static final Item TESTITEM = registerItem("test_item", Item::new);

    //Helper method that allows for registering items
    private static Item registerItem(String name, Function<Item.Properties, Item> function){
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(UnderLockAndKey.MOD_ID, name), 
            function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(UnderLockAndKey.MOD_ID, name)))));
    }

    public static void initializeModItems() {
        UnderLockAndKey.LOGGER.info("Registering Mod Items for " + UnderLockAndKey.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {output.accept(TESTITEM);});
    }

}
