package nothing.xiaofan_delta.void_things.datagen.language.type;

import nothing.xiaofan_delta.void_things.datagen.language.LanguageGenerate;

/**
 * 标签页 / JEI 等其他翻译键.
 */
public final class OtherLanguage {
	private OtherLanguage() {
	}

	public static void addLang() {
		LanguageGenerate.addRaw("itemGroup.void_things.void_tab", "Void Things", "无物");
		LanguageGenerate.addRaw("jei.void_things.left_click", "Left Clicking", "左键点击");
		LanguageGenerate.addRaw("fluid_type.void_things.water", "Water", "水");
		LanguageGenerate.addRaw("block.void_things.water", "Void Water", "虚空水");
		LanguageGenerate.addRaw("item.void_things.water_bucket", "Water Bucket", "水桶");
	}
}
