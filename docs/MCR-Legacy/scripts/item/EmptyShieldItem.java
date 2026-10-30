
package net.mcreator.moreitem.item;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class EmptyShieldItem extends ShieldItem {
	public EmptyShieldItem() {
		super(new Item.Properties().durability(100000).fireResistant());
	}

	@Override
	public boolean isValidRepairItem(ItemStack itemstack, ItemStack repairitem) {
		return Ingredient.of(ItemTags.create(new ResourceLocation("more_item:empty_items"))).test(repairitem);
	}
}
