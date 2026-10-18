package nothing.xiaofan_delta.void_things.common.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.api.register.item.VTSmithingTemplateHelper;
import nothing.xiaofan_delta.void_things.common.item.VoidEggItem;

public final class VTItems {
	public static final ItemEntry<Item> EMPTY_INGOT;
	public static final ItemEntry<Item> EMPTY_NUGGET;
	public static final ItemEntry<Item> EMPTY_SHARD;
	public static final ItemEntry<Item> EMPTY_STICK;
	public static final ItemEntry<Item> EMPTY_PLATE;
	public static final ItemEntry<VoidEggItem> EGG;
	public static final ItemEntry<Item> BEDROCK_SHARD;
	public static final ItemEntry<SmithingTemplateItem> EMPTY_SMITHING_TEMPLATE;

	static {
		EMPTY_INGOT = VoidThings.REGISTRATE.item("empty_ingot", Item::new)
				.properties(p -> p.stacksTo(64))
				.defaultLang()
				.tab(VTCreativeTabs.getTabKey())
				.register();

		EMPTY_NUGGET = VoidThings.REGISTRATE.item("empty_nugget", Item::new)
				.properties(p -> p.stacksTo(64))
				.defaultLang()
				.tab(VTCreativeTabs.getTabKey())
				.register();

		EMPTY_SHARD = VoidThings.REGISTRATE.item("empty_shard", Item::new)
				.properties(p -> p.stacksTo(16))
				.defaultLang()
				.tab(VTCreativeTabs.getTabKey())
				.register();

		EMPTY_STICK = VoidThings.REGISTRATE.item("empty_stick", Item::new)
				.properties(p -> p.stacksTo(64))
				.defaultLang()
				.tab(VTCreativeTabs.getTabKey())
				.register();

		EMPTY_PLATE = VoidThings.REGISTRATE.item("empty_plate", Item::new)
				.properties(p -> p.stacksTo(64))
				.defaultLang()
				.tab(VTCreativeTabs.getTabKey())
				.register();

		EGG = VoidThings.REGISTRATE.item("egg", VoidEggItem::new)
				.properties(p -> p.stacksTo(16))
				.defaultLang()
				.tab(VTCreativeTabs.getTabKey())
				.register();

		BEDROCK_SHARD = VoidThings.REGISTRATE.item("bedrock_shard", Item::new)
				.properties(p -> p.stacksTo(8))
				.defaultLang()
				.tab(VTCreativeTabs.getTabKey())
				.register();

		EMPTY_SMITHING_TEMPLATE = VoidThings.REGISTRATE.item("empty_smithing_template",
						p -> VTSmithingTemplateHelper.createSimple(
								"empty",
								VoidThings.loadResource("item/material/empty_shard"),
								VoidThings.loadResource("item/material/empty_ingot")
						))
				.tab(VTCreativeTabs.getTabKey())
				.register();
	}

	private VTItems() {
	}

	public static void register() {
		VoidThings.registerLog("物品");
	}
}
