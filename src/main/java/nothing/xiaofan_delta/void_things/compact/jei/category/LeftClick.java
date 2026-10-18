package nothing.xiaofan_delta.void_things.compact.jei.category;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.common.register.VTItems;
import nothing.xiaofan_delta.void_things.recipe.LeftClickRecipe;

public class LeftClick implements IRecipeCategory<LeftClickRecipe> {
	public static final RecipeType<LeftClickRecipe> RECIPE_TYPE =
			RecipeType.create(VoidThings.MODID, "left_click", LeftClickRecipe.class);

	private static final ResourceLocation GUI_TEXTURE =
			VoidThings.loadResource("textures/gui/block_left_click.png");

	// 背景尺寸
	private static final int GUI_WIDTH = 128;
	private static final int GUI_HEIGHT = 101;

	private final IDrawable background;
	private final IDrawable icon;
	private final Component title;

	public LeftClick(IGuiHelper guiHelper) {
		// 背景纹理, UV 从 (0, 0) 开始
		this.background = guiHelper.createDrawable(GUI_TEXTURE, 0, 0, GUI_WIDTH, GUI_HEIGHT);

		// 图标: 无之碎片
		this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
				new ItemStack(VTItems.EMPTY_SHARD.get()));

		this.title = Component.translatable("jei.void_things.left_click");
	}

	@Override
	public RecipeType<LeftClickRecipe> getRecipeType() {
		return RECIPE_TYPE;
	}

	@Override
	public Component getTitle() {
		return title;
	}

	@SuppressWarnings({"removal"})
	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, LeftClickRecipe recipe, IFocusGroup focuses) {
		// 工具槽
		builder.addSlot(RecipeIngredientRole.CATALYST, 47, 11)
				.addItemStack(recipe.getTool());

		// 方块输入槽
		builder.addSlot(RecipeIngredientRole.INPUT, 15, 45)
				.addItemStack(recipe.getInputBlock());

		// 方块输出槽
		builder.addSlot(RecipeIngredientRole.OUTPUT, 97, 45)
				.addItemStack(recipe.getOutputBlock());

		// 物品输出槽
		builder.addSlot(RecipeIngredientRole.OUTPUT, 56, 74)
				.addItemStack(recipe.getResultItem());
	}

	@Override
	public void draw(LeftClickRecipe recipe, IRecipeSlotsView slotsView, GuiGraphics graphics,
	                 double mouseX, double mouseY) {
		// 可在此绘制额外内容
	}
}