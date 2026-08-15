package nothing.xiaofan_delta.void_things.compact.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;

import net.minecraftforge.registries.ForgeRegistries;
import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.common.register.VTBlocks;
import nothing.xiaofan_delta.void_things.common.register.VTItems;
import nothing.xiaofan_delta.void_things.recipe.LeftClickRecipe;
import nothing.xiaofan_delta.void_things.compact.jei.category.LeftClick;
import nothing.xiaofan_delta.void_things.config.CommonConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JeiPlugin
public class VoidThingsJEIPlugin implements IModPlugin {
	public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(VoidThings.MODID, "jei_plugin");

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
		registration.addRecipeCategories(new LeftClick(guiHelper));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		// -------- 注册默认工作台配方（保持不变）--------
		RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
		registration.addRecipes(
				RecipeTypes.CRAFTING,
				recipeManager.getAllRecipesFor(net.minecraft.world.item.crafting.RecipeType.CRAFTING)
						.stream()
						.filter(recipe -> recipe.getId().getNamespace().equals(VoidThings.MODID))
						.toList()
		);

		// -------- 从配置读取可用工具列表 --------
		List<? extends String> toolIds = CommonConfig.VOID_BEDROCK_USABLE_ITEMS.get();
		List<LeftClickRecipe> leftClickRecipes = new ArrayList<>();

		// 输入/输出方块固定
		ItemStack inputBlock = new ItemStack(VTBlocks.VOID_BEDROCK.get());
		ItemStack outputBlock = new ItemStack(Items.BEDROCK);
		ItemStack resultItem = new ItemStack(VTItems.EMPTY_SHARD.get());

		for (String toolId : toolIds) {
			// 根据物品 ID 获取物品实例
			Item toolItem = ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(toolId));
			if (toolItem == null) {
				VoidThings.LOGGER.warn("配置中的物品 '{}' 不存在，跳过！", toolId);
				continue;
			}

			// 为每个工具生成一个配方实例
			ResourceLocation recipeId = ResourceLocation.parse(
					VoidThings.MODID + ":left_click/" + toolId.replace(':', '/')
			);
			LeftClickRecipe recipe = new LeftClickRecipe(
					recipeId,
					new ItemStack(toolItem),
					inputBlock,
					outputBlock,
					resultItem
			);
			leftClickRecipes.add(recipe);
		}

		// 注册所有生成的配方
		registration.addRecipes(LeftClick.RECIPE_TYPE, leftClickRecipes);
	}


	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		// 可以添加“催化剂”，在 JEI 中点击虚空基岩时显示相关配方
		registration.addRecipeCatalyst(
				new ItemStack(VTBlocks.VOID_BEDROCK.get()),
				LeftClick.RECIPE_TYPE
		);
		registration.addRecipeCatalyst(
				new ItemStack(Items.NETHERITE_PICKAXE),
				LeftClick.RECIPE_TYPE
		);
	}
}