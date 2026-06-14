package com.jammbit.underlockandkey.client.rendering.inventory;

import com.jammbit.underlockandkey.menu.custom.VaultBlockMenu;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class LockVaultScreen extends AbstractContainerScreen<VaultBlockMenu>{
    private static final Identifier CONTAINER_TEXTURE = Identifier.fromNamespaceAndPath("under_lock_and_key", "textures/gui/container/lock_vault.png");

    public LockVaultScreen(VaultBlockMenu abstractContainerMenu, Inventory inventory, Component component){
        super(abstractContainerMenu, inventory, component);
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(RenderPipelines.GUI_TEXTURED, CONTAINER_TEXTURE, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
	}

}
