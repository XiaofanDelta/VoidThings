package nothing.xiaofan_delta.void_things.datagen.language.type;

import nothing.xiaofan_delta.void_things.datagen.language.LanguageGenerate;

/**
 * 方块类翻译键.
 */
public final class BlockLanguage {
	private BlockLanguage() {
	}

	public static void addLang() {
		LanguageGenerate.addBlock("empty_ingot_block", "Empty Ingot Block", "无之锭块");
		LanguageGenerate.addBlock("void_bedrock", "Void Bedrock", "虚空基岩");
		LanguageGenerate.addBlock("bedstone", "Bedstone", "基石");
		LanguageGenerate.addBlock("bedstone_brick", "Bedstone Brick", "基石砖");
		LanguageGenerate.addBlock("controller", "Controller", "控制器");
		LanguageGenerate.addBlock("safety_glass", "Safety Glass", "安全玻璃");
		LanguageGenerate.addBlock("black_block", "§0Black Block§r", "§0黑色方块§r");
		LanguageGenerate.addBlock("purple_block", "§5Purple Block§r", "§5紫色方块§r");
		LanguageGenerate.addBlock("tree_root", "§lTREE§r Root", "§l树§r根");
		LanguageGenerate.addBlock("tree_branch", "§lTREE§r Branch", "§l树§r枝");
		LanguageGenerate.addBlock("tree_trunk", "§lTREE§r Trunk", "§l树§r干");
		LanguageGenerate.addBlock("tree_trunk_top", "§lTREE§r Trunk (Top)", "§l树§r干 (顶端)");
		LanguageGenerate.addBlock("tree_trunk_half", "§lTREE§r Trunk (Bottom)", "§l树§r干 (底端)");
		LanguageGenerate.addBlock("tree_leaves_red", "§lTREE§r Leaves (Red)", "§l树§r叶 (§4红色§r)");
		LanguageGenerate.addBlock("tree_leaves_purple", "§lTREE§r Leaves (Purple)", "§l树§r叶 (§d紫色§r)");
		LanguageGenerate.addBlock("tree_leaves_dark_purple", "§lTREE§r Leaves (Dark Purple)", "§l树§r叶 (§5暗紫色§r)");
		LanguageGenerate.addBlock("tree_planks", "§lTREE§r Planks", "§l树§r板材");
	}
}
