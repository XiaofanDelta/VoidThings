
package net.mcreator.moreitem.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

import net.mcreator.moreitem.init.MoreItemModItems;

public class EmptyHammerHeadItem extends Item {
	public EmptyHammerHeadItem() {
		super(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.COMMON));
	}

	@Override
	public boolean hasCraftingRemainingItem() {
		return true;
	}

	@Override
	public ItemStack getCraftingRemainingItem(ItemStack itemstack) {
		return new ItemStack(MoreItemModItems.EMPTY_INGOT.get());
	}
}
