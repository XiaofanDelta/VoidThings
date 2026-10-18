package nothing.xiaofan_delta.void_things.datagen.language;

import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.datagen.language.type.BlockLanguage;
import nothing.xiaofan_delta.void_things.datagen.language.type.EffectLanguage;
import nothing.xiaofan_delta.void_things.datagen.language.type.ItemLanguage;
import nothing.xiaofan_delta.void_things.datagen.language.type.OtherLanguage;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 翻译键收集入口. 各类型在 {@code language.type} 下分别维护.
 */
public final class LanguageGenerate {
	private static final Map<String, String[]> TRANSLATIONS = new LinkedHashMap<>();

	static {
		ItemLanguage.addLang();
		BlockLanguage.addLang();
		EffectLanguage.addLang();
		OtherLanguage.addLang();
	}

	private LanguageGenerate() {
	}

	public static void addItem(String key, String english, String chinese) {
		add("item", key, english, chinese);
	}

	public static void addBlock(String key, String english, String chinese) {
		add("block", key, english, chinese);
	}

	public static void addEffect(String key, String english, String chinese) {
		add("effect", key, english, chinese);
	}

	public static void addRaw(String key, String english, String chinese) {
		TRANSLATIONS.put(key, new String[]{english, chinese});
	}

	private static void add(String type, String key, String english, String chinese) {
		addRaw(String.format("%s.%s.%s", type, VoidThings.MODID, key), english, chinese);
	}

	public static void addEnglish() {
		TRANSLATIONS.forEach((key, values) -> {
			if (key.startsWith("block.")) {
				return;
			}
			if (key.startsWith("item.")
					&& !key.equals("item.void_things.empty_smithing_template")
					&& !key.equals("item.void_things.water_bucket")
					&& !key.endsWith(".desc")
					&& !key.endsWith(".applies_to")
					&& !key.endsWith(".addition_slot")
					&& !key.endsWith(".ingredients")
					&& !key.endsWith(".base_slot")) {
				return;
			}
			VoidThings.REGISTRATE.addRawLang(key, values[0]);
		});
	}

	public static Map<String, String[]> entries() {
		return Collections.unmodifiableMap(TRANSLATIONS);
	}
}
