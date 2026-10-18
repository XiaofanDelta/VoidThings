package nothing.xiaofan_delta.void_things.datagen.language.type;

import nothing.xiaofan_delta.void_things.datagen.language.LanguageGenerate;

/**
 * 物品类翻译键.
 */
public final class ItemLanguage {
	private ItemLanguage() {
	}

	public static void addLang() {
		LanguageGenerate.addItem("empty_ingot", "Empty Ingot", "空之锭");
		LanguageGenerate.addItem("empty_nugget", "Empty Nugget", "空之粒");
		LanguageGenerate.addItem("empty_shard", "Empty Shard", "空之碎片");
		LanguageGenerate.addItem("empty_stick", "Empty Stick", "空之棍");
		LanguageGenerate.addItem("empty_plate", "Empty Plate", "空之板");
		LanguageGenerate.addItem("bedrock_shard", "Bedrock Shard", "基岩碎片");
		LanguageGenerate.addItem("egg", "Egg", "蛋");
		LanguageGenerate.addItem("man_music_disc", "Music Disc", "音乐唱片");
		LanguageGenerate.addItem("man_music_disc.desc", "Toby Fox, §k??????§r - §k???§r", "托比福克斯, §k???§r - §k??§r");
		LanguageGenerate.addItem("empty_smithing_template", "Empty Conversion", "空之转换");
		LanguageGenerate.addItem("empty_smithing_template.applies_to", "Something", "某物");
		LanguageGenerate.addItem("empty_smithing_template.addition_slot", "Add something", "放入某物");
		LanguageGenerate.addItem("empty_smithing_template.ingredients", "Empty Shard", "空之碎片");
		LanguageGenerate.addItem("empty_smithing_template.base_slot", "Add empty shard", "放入空之碎片");
	}
}
