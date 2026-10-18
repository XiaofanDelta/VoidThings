package nothing.xiaofan_delta.void_things.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.datagen.language.VTChineseLanguageProvider;
import nothing.xiaofan_delta.void_things.datagen.model.VTItemModelProvider;

/**
 * 数据生成入口. 方块状态/战利品表/英文 lang 由 Registrate provider 输出.
 */
@Mod.EventBusSubscriber(modid = VoidThings.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
	private DataGenerators() {
	}

	@SubscribeEvent
	public static void onGatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeClient(), new VTItemModelProvider(output, helper));
		generator.addProvider(event.includeClient(), new VTChineseLanguageProvider(output));
	}
}
