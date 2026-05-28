public class ModBlocks{
    
    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem){
        // Creates the registry key for the block
        ResourceKey<Block> blockKey = keyOfBlock(name);
        // Creates the block instance
        Block block = blockFactory.apply(settings.setId(blockKey));

        if (shouldRegisterItem){

            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, newItem.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }
        return Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(UnderLockAndKey.MOD_ID, name))
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(UnderLockAndKey.MOD_ID, name))
    }

    public static void initializeModBlocks() {}

    public static final block TESTBLOCK = register(
        "test_block",
        Block::new,
        BlockBehaviour.Properties.of().sound(SoundType.GRASS),
        true
    );


}