package nothing.xiaofan_delta.void_things.datagen.language;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import nothing.xiaofan_delta.void_things.VoidThings;

/**
 * 生成 zh_cn.json.
 */
public class VTChineseLanguageProvider extends LanguageProvider {
	public VTChineseLanguageProvider(PackOutput output) {
		super(output, VoidThings.MODID, "zh_cn");
	}

	@Override
	protected void addTranslations() {
		LanguageGenerate.entries().forEach((key, values) -> add(key, values[1]));
	}
}
