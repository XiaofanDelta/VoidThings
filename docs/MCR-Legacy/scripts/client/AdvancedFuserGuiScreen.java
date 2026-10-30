package net.mcreator.moreitem.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.moreitem.world.inventory.AdvancedFuserGuiMenu;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class AdvancedFuserGuiScreen extends AbstractContainerScreen<AdvancedFuserGuiMenu> {
	private final static HashMap<String, Object> guistate = AdvancedFuserGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public AdvancedFuserGuiScreen(AdvancedFuserGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 180;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("more_item:textures/screens/advanced_fuser_gui.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("more_item:textures/screens/advanced_fuser_gui_step3.png"), this.leftPos + 119, this.topPos + 41, 0, 0, 32, 16, 32, 16);

		guiGraphics.blit(new ResourceLocation("more_item:textures/screens/advanced_fuser_gui_step1.png"), this.leftPos + 8, this.topPos + 33, 0, 0, 64, 32, 64, 32);

		guiGraphics.blit(new ResourceLocation("more_item:textures/screens/advanced_fuser_gui_step2.png"), this.leftPos + 60, this.topPos + 20, 0, 0, 72, 72, 72, 72);

		guiGraphics.blit(new ResourceLocation("more_item:textures/screens/black_ingot_slot.png"), this.leftPos + 10, this.topPos + 19, 0, 0, 16, 16, 16, 16);

		guiGraphics.blit(new ResourceLocation("more_item:textures/screens/white_ingot_slot.png"), this.leftPos + 10, this.topPos + 63, 0, 0, 16, 16, 16, 16);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.more_item.advanced_fuser_gui.label_advanced_fusertitle"), 9, 5, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
	}
}
