package com.jammbit.underlockandkey.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class VaultKey extends Item {
    public VaultKey(Properties properties) {
        super(properties);
    }

    // Example Interaction of Item (Taken from documentation)
    @Override
    public InteractionResult use(Level level, Player user, InteractionHand hand) {
        // Ensure we don't spawn the lightning only on the client.
        // This is to prevent desync.
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        BlockPos frontOfPlayer = user.blockPosition().relative(user.getDirection(), 10);

        // Spawn the lightning bolt.
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
        lightningBolt.setPos(frontOfPlayer.getCenter());
        level.addFreshEntity(lightningBolt);

        return InteractionResult.SUCCESS;
    }

}
