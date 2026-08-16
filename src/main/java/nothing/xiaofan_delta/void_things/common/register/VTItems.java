package nothing.xiaofan_delta.void_things.common.register;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nothing.xiaofan_delta.void_things.VoidThings;

public class VTItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, VoidThings.MODID);

	// 注册物品
	public static final RegistryObject<Item> EMPTY_INGOT = ITEMS.register("empty_ingot",
			() -> new Item(new Item.Properties().stacksTo(64)));

	public static final RegistryObject<Item> EMPTY_NUGGET = ITEMS.register("empty_nugget",
			() -> new Item(new Item.Properties().stacksTo(64)));

	public static final RegistryObject<Item> EMPTY_SHARD = ITEMS.register("empty_shard",
			() -> new Item(new Item.Properties().stacksTo(16)));

	public static final RegistryObject<Item> EMPTY_STICK = ITEMS.register("empty_stick",
			() -> new Item(new Item.Properties().stacksTo(64)));

	public static final RegistryObject<Item> EMPTY_PLATE = ITEMS.register("empty_plate",
			() -> new Item(new Item.Properties().stacksTo(64)));

	public static final RegistryObject<Item> EGG = ITEMS.register("egg",
			() -> new Item(new Item.Properties().stacksTo(16)));

	// 注册方块对应物品
	public static final RegistryObject<Item> EMPTY_INGOT_BLOCK_ITEM = ITEMS.register("empty_ingot_block",
			() -> new BlockItem(VTBlocks.EMPTY_INGOT_BLOCK.get(), new Item.Properties()));

	public static final RegistryObject<Item> VOID_BEDROCK_ITEM = ITEMS.register("void_bedrock",
			() -> new BlockItem(VTBlocks.VOID_BEDROCK.get(), new Item.Properties()));

	public static final RegistryObject<Item> BEDSTONE_ITEM =
			ITEMS.register("bedstone",
					() -> new BlockItem(VTBlocks.BEDSTONE.get(), new Item.Properties()));

	public static final RegistryObject<Item> BEDSTONE_BRICK_ITEM =
			ITEMS.register("bedstone_brick",
					() -> new BlockItem(VTBlocks.BEDSTONE_BRICK.get(), new Item.Properties()));

	public static final RegistryObject<Item> CONTROLLER_ITEM =
			ITEMS.register("controller",
					() -> new BlockItem(VTBlocks.CONTROLLER.get(), new Item.Properties()));

	public static final RegistryObject<Item> SAFETY_GLASS_ITEM =
			ITEMS.register("safety_glass",
					() -> new BlockItem(VTBlocks.SAFETY_GLASS.get(), new Item.Properties()));

	public static final RegistryObject<Item> BLACK_BLOCK_ITEM =
			ITEMS.register("black_block",
					() -> new BlockItem(VTBlocks.BLACK_BLOCK.get(), new Item.Properties()));

	public static final RegistryObject<Item> PURPLE_BLOCK_ITEM =
			ITEMS.register("purple_block",
					() -> new BlockItem(VTBlocks.PURPLE_BLOCK.get(), new Item.Properties()));

	// 树后面有一个男人
	public static final RegistryObject<Item> TREE_TRUNK_ITEM =
			ITEMS.register("tree_trunk",
					() -> new BlockItem(VTBlocks.TREE_TRUNK.get(), new Item.Properties()));

	public static final RegistryObject<Item> TREE_TRUNK_TOP_ITEM =
			ITEMS.register("tree_trunk_top",
					() -> new BlockItem(VTBlocks.TREE_TRUNK_TOP.get(), new Item.Properties()));

	public static final RegistryObject<Item> TREE_TRUNK_HALF_ITEM =
			ITEMS.register("tree_trunk_half",
					() -> new BlockItem(VTBlocks.TREE_TRUNK_HALF.get(), new Item.Properties()));

	public static final RegistryObject<Item> TREE_BRANCH_ITEM =
			ITEMS.register("tree_branch",
					() -> new BlockItem(VTBlocks.TREE_BRANCH.get(), new Item.Properties()));

	public static final RegistryObject<Item> TREE_ROOT_ITEM =
			ITEMS.register("tree_root",
					() -> new BlockItem(VTBlocks.TREE_ROOT.get(), new Item.Properties()));

	public static final RegistryObject<Item> TREE_LEAVES_RED_ITEM =
			ITEMS.register("tree_leaves_red",
					() -> new BlockItem(VTBlocks.TREE_LEAVES_RED.get(), new Item.Properties()));

	public static final RegistryObject<Item> TREE_LEAVES_PURPLE_ITEM =
			ITEMS.register("tree_leaves_purple",
					() -> new BlockItem(VTBlocks.TREE_LEAVES_PURPLE.get(), new Item.Properties()));

	public static final RegistryObject<Item> TREE_LEAVES_DARK_PURPLE_ITEM =
			ITEMS.register("tree_leaves_dark_purple",
					() -> new BlockItem(VTBlocks.TREE_LEAVES_DARK_PURPLE.get(), new Item.Properties()));

	// 注册方法
	public static void register(IEventBus bus) {
		ITEMS.register(bus);
		VoidThings.registerLog("物品");
	}
}