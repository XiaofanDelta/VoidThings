package nothing.xiaofan_delta.void_things.common.register;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nothing.xiaofan_delta.void_things.VoidThings;

public class VTItems {
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VoidThings.MODID);

	// 注册物品
	public static final RegistryObject<Item> EMPTY_INGOT = ITEMS.register("empty_ingot",
			() -> new Item(new Item.Properties().stacksTo(64)));

	public static final RegistryObject<Item> EMPTY_NUGGET = ITEMS.register("empty_nugget",
			() -> new Item(new Item.Properties().stacksTo(64)));

	public static final RegistryObject<Item> EMPTY_SHARD = ITEMS.register("empty_shard",
			() -> new Item(new Item.Properties().stacksTo(16)));

	// 注册方块对应物品
	public static final RegistryObject<Item> EMPTY_INGOT_BLOCK_ITEM = ITEMS.register("empty_ingot_block",
			() -> new BlockItem(VTBlocks.EMPTY_INGOT_BLOCK.get(), new Item.Properties()));

	public static final RegistryObject<Item> VOID_BEDROCK_ITEM = ITEMS.register("void_bedrock",
			() -> new BlockItem(VTBlocks.VOID_BEDROCK.get(), new Item.Properties()));

	// 注册方法
	public static void register(IEventBus bus) {
		ITEMS.register(bus);
		VoidThings.registerLog("物品");
	}
}