package nothing.xiaofan_delta.void_things.datagen.language.type;

import nothing.xiaofan_delta.void_things.datagen.language.LanguageGenerate;

/**
 * 药水效果类翻译键.
 */
public final class EffectLanguage {
	private EffectLanguage() {
	}

	public static void addLang() {
		LanguageGenerate.addEffect("darkness_echo", "Darkness Echo", "暗影残响");
		LanguageGenerate.addEffect("darkness_echo.description", "Shadows cloud your vision and weigh down your steps.", "视野被暗影侵蚀, 脚步变得沉重.");
	}
}
