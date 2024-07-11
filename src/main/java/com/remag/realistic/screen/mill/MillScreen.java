package com.remag.realistic.screen.mill;

import com.mojang.blaze3d.systems.RenderSystem;
import com.remag.realistic.RealisticEverything;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MillScreen extends AbstractContainerScreen<MillMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RealisticEverything.MODID, "textures/gui/mill_gui.png");

    public MillScreen(MillMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 73;
        this.titleLabelY = 5;
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
        renderFuelBar(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 79, y + 34, 176, 14, menu.getScaledProgress() + 1, 16);
        }
    }

    private void renderFuelBar(GuiGraphics guiGraphics, int x, int y) {
        if (menu.isBurning()) {
            guiGraphics.blit(TEXTURE, x + 56, y + 36 + 12 - menu.getFuelProgress(), 176, 12 - menu.getFuelProgress(), 14, menu.getFuelProgress() + 1);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
