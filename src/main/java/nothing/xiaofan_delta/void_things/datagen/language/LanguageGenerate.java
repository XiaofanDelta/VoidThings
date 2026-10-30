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

	public static void addItem(String translationKey, String english, String chinese) {
		add("item", translationKey, english, chinese);
	}

	public static void addBlock(String translationKey, String english, String chinese) {
		add("block", translationKey, english, chinese);
	}

	public static void addEffect(String translationKey, String english, String chinese) {
		add("effect", translationKey, english, chinese);
	}

	public static void addRaw(String translationKey, String english, String chinese) {
		TRANSLATIONS.put(translationKey, new String[]{english, chinese});
	}

	private static void add(String translationType, String translationKey, String english, String chinese) {
		addRaw(String.format("%s.%s.%s", translationType, VoidThings.MODID, translationKey), english, chinese);
	}

	public static void addEnglish() {
		TRANSLATIONS.forEach((translationKey, translations) -> {
			if (translationKey.startsWith("block.")) {
				return;
			}
			if (translationKey.startsWith("item.")
					&& !translationKey.equals("item.void_things.empty_smithing_template")
					&& !translationKey.equals("item.void_things.water_bucket")
					&& !translationKey.endsWith(".desc")
					&& !translationKey.endsWith(".applies_to")
					&& !translationKey.endsWith(".addition_slot")
					&& !translationKey.endsWith(".ingredients")
					&& !translationKey.endsWith(".base_slot")) {
				return;
			}
			VoidThings.REGISTRATE.addRawLang(translationKey, translations[0]);
		});
	}

	public static Map<String, String[]> entries() {
		return Collections.unmodifiableMap(TRANSLATIONS);
	}
}
