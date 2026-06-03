package com.jammbit.underlockandkey.items;


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
