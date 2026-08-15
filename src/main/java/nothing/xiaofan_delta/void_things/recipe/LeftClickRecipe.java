package nothing.xiaofan_delta.void_things.recipe;

import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class LeftClickRecipe implements Recipe<Container> {
	private final ResourceLocation id;
	private final ItemStack tool;          // 需要的工具（如下界合金镐）
	private final ItemStack inputBlock;    // 输入方块（虚空基岩）
	private final ItemStack outputBlock;   // 输出方块（基岩）
	private final ItemStack resultItem;    // 掉落物（无之碎片）

	public LeftClickRecipe(ResourceLocation id, ItemStack tool, ItemStack inputBlock,
	                       ItemStack outputBlock, ItemStack resultItem) {
		this.id = id;
		this.tool = tool;
		this.inputBlock = inputBlock;
		this.outputBlock = outputBlock;
		this.resultItem = resultItem;
	}

	// -------- Getters ----------
	public ItemStack getTool() { return tool; }
	public ItemStack getInputBlock() { return inputBlock; }
	public ItemStack getOutputBlock() { return outputBlock; }
	public ItemStack getResultItem() { return resultItem; }

	// -------- Recipe 接口必须实现的方法 ----------
	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return LeftClickRecipeSerializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType() {
		return LeftClickRecipeType.INSTANCE;
	}

	/**
	 * 1.20.1 官方映射中，matches 需要 Container 和 Level 参数
	 */
	@Override
	public boolean matches(Container container, Level level) {
		return false; // 我们只用于 JEI 显示，不需要容器匹配
	}

	/**
	 * 1.20.1 官方映射中，assemble 需要 Container 和 RegistryAccess 参数
	 */
	@Override
	public ItemStack assemble(Container container, RegistryAccess registryAccess) {
		return resultItem.copy();
	}

	/**
	 * 1.20.1 官方映射中，getResultItem 需要 RegistryAccess 参数
	 * 注意：这里的返回值是「配方展示用的结果」，实际掉落逻辑在事件中处理
	 */
	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return resultItem.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return true;
	}
}