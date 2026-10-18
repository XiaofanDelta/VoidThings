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
	private final ItemStack tool;        // 工具
	private final ItemStack inputBlock;  // 输入方块
	private final ItemStack outputBlock; // 输出方块
	private final ItemStack resultItem;  // 掉落物

	public LeftClickRecipe(ResourceLocation id, ItemStack tool, ItemStack inputBlock,
	                       ItemStack outputBlock, ItemStack resultItem) {
		this.id = id;
		this.tool = tool;
		this.inputBlock = inputBlock;
		this.outputBlock = outputBlock;
		this.resultItem = resultItem;
	}

	// Getters
	public ItemStack getTool() { return tool; }
	public ItemStack getInputBlock() { return inputBlock; }
	public ItemStack getOutputBlock() { return outputBlock; }
	public ItemStack getResultItem() { return resultItem; }

	// Recipe 方法
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
	 * 仅用于 JEI 显示.
	 *
	 * @param container 容器
	 * @param level 世界
	 * @return 始终 false
	 */
	@Override
	public boolean matches(Container container, Level level) {
		return false; // 仅用于 JEI 显示
	}

	/**
	 * 返回配方结果.
	 *
	 * @param container 容器
	 * @param registryAccess 注册表访问
	 * @return 结果副本
	 */
	@Override
	public ItemStack assemble(Container container, RegistryAccess registryAccess) {
		return resultItem.copy();
	}

	/**
	 * 返回 JEI 展示结果.
	 *
	 * @param registryAccess 注册表访问
	 * @return 结果副本
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