package nothing.xiaofan_delta.void_things.common.register;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.common.block.HorizontalBlock;
import nothing.xiaofan_delta.void_things.common.register.block.VoidBedrockBlock;

public final class VTBlocks {
	public static final BlockEntry<Block> EMPTY_INGOT_BLOCK;
	public static final BlockEntry<Block> BEDSTONE;
	public static final BlockEntry<Block> BEDSTONE_BRICK;
	public static final BlockEntry<Block> CONTROLLER;
	public static final BlockEntry<Block> SAFETY_GLASS;
	public static final BlockEntry<Block> BLACK_BLOCK;
	public static final BlockEntry<Block> PURPLE_BLOCK;
	public static final BlockEntry<VoidBedrockBlock> VOID_BEDROCK;
	public static final BlockEntry<HorizontalBlock> TREE_ROOT;
	public static final BlockEntry<Block> TREE_TRUNK;
	public static final BlockEntry<HorizontalBlock> TREE_TRUNK_HALF;
	public static final BlockEntry<Block> TREE_TRUNK_TOP;
	public static final BlockEntry<HorizontalBlock> TREE_BRANCH;
	public static final BlockEntry<Block> TREE_LEAVES_PURPLE;
	public static final BlockEntry<Block> TREE_LEAVES_RED;
	public static final BlockEntry<Block> TREE_LEAVES_DARK_PURPLE;
	public static final BlockEntry<Block> TREE_PLANKS;

	static {
		EMPTY_INGOT_BLOCK = VoidThings.REGISTRATE.block("empty_ingot_block", Block::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_LIGHT_GRAY)
						.strength(5.0F, 6.0F)
						.requiresCorrectToolForDrops())
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/empty_ingot_block"))))
				.item()
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		BEDSTONE = VoidThings.REGISTRATE.block("bedstone", Block::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_LIGHT_GRAY)
						.strength(6.0F, 1200.0F)
						.sound(SoundType.STONE)
						.requiresCorrectToolForDrops())
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().cubeAll(ctx.getName(), VoidThings.loadResource("block/deco/bedstone"))))
				.item()
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		BEDSTONE_BRICK = VoidThings.REGISTRATE.block("bedstone_brick", Block::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_LIGHT_GRAY)
						.strength(6.0F, 1200.0F)
						.sound(SoundType.STONE)
						.requiresCorrectToolForDrops())
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().cubeAll(ctx.getName(), VoidThings.loadResource("block/deco/bedstone_brick"))))
				.item()
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		CONTROLLER = VoidThings.REGISTRATE.block("controller", Block::new)
				.properties(p -> p
						.mapColor(MapColor.TERRACOTTA_WHITE)
						.strength(2.0F, 500.0F)
						.sound(SoundType.STONE)
						.requiresCorrectToolForDrops())
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/controller"))))
				.item()
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		SAFETY_GLASS = VoidThings.REGISTRATE.block("safety_glass", Block::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_RED)
						.strength(1.0F, 100.0F)
						.sound(SoundType.GLASS)
						.noLootTable()
						.noOcclusion())
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/safety_glass"))))
				.item()
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		BLACK_BLOCK = VoidThings.REGISTRATE.block("black_block", Block::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_BLACK)
						.strength(3.0F, 300.0F)
						.sound(SoundType.STONE)
						.requiresCorrectToolForDrops())
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().cubeAll(ctx.getName(), VoidThings.loadResource("block/deco/black"))))
				.lang("§0Black Block§r")
				.item()
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		PURPLE_BLOCK = VoidThings.REGISTRATE.block("purple_block", Block::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_PURPLE)
						.strength(3.0F, 300.0F)
						.sound(SoundType.STONE)
						.requiresCorrectToolForDrops())
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().cubeAll(ctx.getName(), VoidThings.loadResource("block/deco/purple"))))
				.lang("§5Purple Block§r")
				.item()
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		VOID_BEDROCK = VoidThings.REGISTRATE.block("void_bedrock", VoidBedrockBlock::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_BLACK)
						.strength(-1.0F, 3600000.0F)
						.sound(SoundType.STONE)
						.noLootTable()
						.pushReaction(PushReaction.BLOCK)
						.isValidSpawn((state, level, pos, entity) -> false))
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/void_bedrock"))))
				.item()
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		TREE_ROOT = VoidThings.REGISTRATE.block("tree_root", HorizontalBlock::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_PURPLE)
						.strength(1.0F, 1.0F)
						.sound(SoundType.WOOD))
				.blockstate((ctx, prov) -> prov.horizontalBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/tree/root"))))
				.lang("§lTREE§r Root")
				.item()
				.model((ctx, prov) -> prov.withExistingParent(ctx.getName(),
						VoidThings.loadResource("block/tree/root")))
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		TREE_TRUNK = VoidThings.REGISTRATE.block("tree_trunk", Block::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_PURPLE)
						.strength(1.0F, 1.0F)
						.sound(SoundType.WOOD))
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/tree/trunk"))))
				.lang("§lTREE§r Trunk")
				.item()
				.model((ctx, prov) -> prov.withExistingParent(ctx.getName(),
						VoidThings.loadResource("block/tree/trunk")))
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		TREE_TRUNK_HALF = VoidThings.REGISTRATE.block("tree_trunk_half", HorizontalBlock::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_PURPLE)
						.strength(1.0F, 1.0F)
						.sound(SoundType.WOOD))
				.blockstate((ctx, prov) -> prov.horizontalBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/tree/trunk_half"))))
				.lang("§lTREE§r Trunk (Bottom)")
				.item()
				.model((ctx, prov) -> prov.withExistingParent(ctx.getName(),
						VoidThings.loadResource("block/tree/trunk_half")))
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		TREE_TRUNK_TOP = VoidThings.REGISTRATE.block("tree_trunk_top", Block::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_PURPLE)
						.strength(1.0F, 1.0F)
						.sound(SoundType.WOOD))
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/tree/trunk_top"))))
				.lang("§lTREE§r Trunk (Top)")
				.item()
				.model((ctx, prov) -> prov.withExistingParent(ctx.getName(),
						VoidThings.loadResource("block/tree/trunk_top")))
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		TREE_BRANCH = VoidThings.REGISTRATE.block("tree_branch", HorizontalBlock::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_PURPLE)
						.strength(1.0F, 1.0F)
						.sound(SoundType.WOOD))
				.blockstate((ctx, prov) -> prov.horizontalBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/tree/branch"))))
				.lang("§lTREE§r Branch")
				.item()
				.model((ctx, prov) -> prov.withExistingParent(ctx.getName(),
						VoidThings.loadResource("block/tree/branch")))
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		TREE_LEAVES_PURPLE = VoidThings.REGISTRATE.block("tree_leaves_purple", Block::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_PURPLE)
						.strength(1.0F, 1.0F)
						.sound(SoundType.AZALEA_LEAVES))
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/tree/leaves/purple"))))
				.lang("§lTREE§r Leaves (Purple)")
				.item()
				.model((ctx, prov) -> prov.withExistingParent(ctx.getName(),
						VoidThings.loadResource("block/tree/leaves/purple")))
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		TREE_LEAVES_RED = VoidThings.REGISTRATE.block("tree_leaves_red", Block::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_RED)
						.strength(1.0F, 1.0F)
						.sound(SoundType.AZALEA_LEAVES))
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/tree/leaves/red"))))
				.lang("§lTREE§r Leaves (Red)")
				.item()
				.model((ctx, prov) -> prov.withExistingParent(ctx.getName(),
						VoidThings.loadResource("block/tree/leaves/red")))
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		TREE_LEAVES_DARK_PURPLE = VoidThings.REGISTRATE.block("tree_leaves_dark_purple", Block::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_RED)
						.strength(1.0F, 1.0F)
						.sound(SoundType.AZALEA_LEAVES))
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/tree/leaves/dark_purple"))))
				.lang("§lTREE§r Leaves (Dark Purple)")
				.item()
				.model((ctx, prov) -> prov.withExistingParent(ctx.getName(),
						VoidThings.loadResource("block/tree/leaves/dark_purple")))
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();

		TREE_PLANKS = VoidThings.REGISTRATE.block("tree_planks", Block::new)
				.properties(p -> p
						.mapColor(MapColor.COLOR_PURPLE)
						.strength(2.0F, 20.0F)
						.sound(SoundType.WOOD))
				.blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(),
						prov.models().getExistingFile(VoidThings.loadResource("block/tree/planks"))))
				.lang("§lTREE§r Planks")
				.item()
				.model((ctx, prov) -> prov.withExistingParent(ctx.getName(),
						VoidThings.loadResource("block/tree/planks")))
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();
	}

	private VTBlocks() {
	}

	public static void register() {
		VoidThings.registerLog("方块");
	}
}
