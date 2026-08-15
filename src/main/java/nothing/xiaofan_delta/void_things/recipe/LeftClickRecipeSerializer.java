package nothing.xiaofan_delta.void_things.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;

public class LeftClickRecipeSerializer implements RecipeSerializer<LeftClickRecipe> {
	public static final LeftClickRecipeSerializer INSTANCE = new LeftClickRecipeSerializer();

	@Override
	public LeftClickRecipe fromJson(ResourceLocation id, JsonObject json) {
		// 我们暂时不实现从 JSON 读取，而是在代码中硬编码注册
		// 如果你想做成数据驱动的，可以在这里实现
		throw new UnsupportedOperationException("Data-driven not implemented yet");
	}

	@Override
	public LeftClickRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
		ItemStack tool = buffer.readItem();
		ItemStack inputBlock = buffer.readItem();
		ItemStack outputBlock = buffer.readItem();
		ItemStack resultItem = buffer.readItem();
		return new LeftClickRecipe(id, tool, inputBlock, outputBlock, resultItem);
	}

	@Override
	public void toNetwork(FriendlyByteBuf buffer, LeftClickRecipe recipe) {
		buffer.writeItemStack(recipe.getTool(), false);
		buffer.writeItemStack(recipe.getInputBlock(), false);
		buffer.writeItemStack(recipe.getOutputBlock(), false);
		buffer.writeItemStack(recipe.getResultItem(), false);
	}
}