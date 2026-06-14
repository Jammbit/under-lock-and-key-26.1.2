package com.jammbit.underlockandkey.blocks;

import com.jammbit.underlockandkey.UnderLockAndKey;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    
    public static final BlockEntityType<VaultBlockEntity> VAULT_BLOCK_ENTITY = register(
        "lock_vault", 
        VaultBlockEntity::new, 
        ModBlocks.VAULTBLOCK
    );


    private static <T extends BlockEntity> BlockEntityType<T> register(
        String name,
        FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
        // ? extends allows the code to accept a broad collection of related objects.
        Block... blocks
        // ... represents varargs / variable-length arguments, Java treats the parameter as an array
    ) {
        Identifier id = Identifier.fromNamespaceAndPath(UnderLockAndKey.MOD_ID, name);
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void initialize() {}

}
