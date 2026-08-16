package nothing.xiaofan_delta.void_things.common.register;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.common.register.block.VoidBedrockBlock;

public class VTBlocks {
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, VoidThings.MODID);

	// 注册
	public static final RegistryObject<Block> EMPTY_INGOT_BLOCK = BLOCKS.register("empty_ingot_block",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_LIGHT_GRAY)
					.strength(5.0F, 6.0F)
					.requiresCorrectToolForDrops()
			));

	public static final RegistryObject<Block> BEDSTONE = BLOCKS.register("bedstone",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_LIGHT_GRAY)
					.strength(6.0F, 1200.0F)
					.sound(SoundType.STONE)
					.requiresCorrectToolForDrops()
			));

	public static final RegistryObject<Block> BEDSTONE_BRICK = BLOCKS.register("bedstone_brick",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_LIGHT_GRAY)
					.strength(6.0F, 1200.0F)
					.sound(SoundType.STONE)
					.requiresCorrectToolForDrops()
			));

	public static final RegistryObject<Block> CONTROLLER = BLOCKS.register("controller",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.TERRACOTTA_WHITE)
					.strength(2.0F, 500.0F)
					.sound(SoundType.STONE)
					.requiresCorrectToolForDrops()
			));

	public static final RegistryObject<Block> SAFETY_GLASS = BLOCKS.register("safety_glass",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_RED)
					.strength(1.0F, 100.0F)
					.sound(SoundType.GLASS)
					.noLootTable()
			));

	public static final RegistryObject<Block> BLACK_BLOCK = BLOCKS.register("black_block",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_BLACK)
					.strength(3.0F, 300.0F)
					.sound(SoundType.STONE)
					.requiresCorrectToolForDrops()
			));

	public static final RegistryObject<Block> PURPLE_BLOCK = BLOCKS.register("purple_block",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_PURPLE)
					.strength(3.0F, 300.0F)
					.sound(SoundType.STONE)
					.requiresCorrectToolForDrops()
			));

	public static final RegistryObject<Block> VOID_BEDROCK =
			BLOCKS.register("void_bedrock", VoidBedrockBlock::new);

	// 他在树后面
	public static final RegistryObject<Block> TREE_ROOT = BLOCKS.register("tree_root",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_PURPLE)
					.strength(1.0F, 1.0F)
					.sound(SoundType.WOOD)
			));

	public static final RegistryObject<Block> TREE_TRUNK = BLOCKS.register("tree_trunk",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_PURPLE)
					.strength(1.0F, 1.0F)
					.sound(SoundType.WOOD)
			));

	public static final RegistryObject<Block> TREE_TRUNK_HALF = BLOCKS.register("tree_trunk_half",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_PURPLE)
					.strength(1.0F, 1.0F)
					.sound(SoundType.WOOD)
			));

	public static final RegistryObject<Block> TREE_TRUNK_TOP = BLOCKS.register("tree_trunk_top",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_PURPLE)
					.strength(1.0F, 1.0F)
					.sound(SoundType.WOOD)
			));

	public static final RegistryObject<Block> TREE_BRANCH = BLOCKS.register("tree_branch",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_PURPLE)
					.strength(1.0F, 1.0F)
					.sound(SoundType.WOOD)
			));

	public static final RegistryObject<Block> TREE_LEAVES_PURPLE = BLOCKS.register("tree_leaves_purple",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_PURPLE)
					.strength(1.0F, 1.0F)
					.sound(SoundType.AZALEA_LEAVES)
			));

	public static final RegistryObject<Block> TREE_LEAVES_RED = BLOCKS.register("tree_leaves_red",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_RED)
					.strength(1.0F, 1.0F)
					.sound(SoundType.AZALEA_LEAVES)
			));

	public static final RegistryObject<Block> TREE_LEAVES_DARK_PURPLE = BLOCKS.register("tree_leaves_dark_purple",
			() -> new Block(BlockBehaviour.Properties.of()
					.mapColor(MapColor.COLOR_RED)
					.strength(1.0F, 1.0F)
					.sound(SoundType.AZALEA_LEAVES)
			));

	// 注册方法
	public static void register(IEventBus bus) {
		BLOCKS.register(bus);
		VoidThings.registerLog("方块");
	}
}