package nothing.xiaofan_delta.void_things.common.register;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.celestiacraft.libs.api.register.item.BasicRecordItem;
import dev.celestiacraft.libs.utils.sounds.RecordSpec;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Rarity;
import nothing.xiaofan_delta.void_things.VoidThings;

/**
 * 唱片注册: 声音事件 + BasicRecordItem.
 */
public final class VTMusicDisc {
	public static final RegistryEntry<SoundEvent> MAN_SOUND;
	private static final RecordSpec MAN_RECORD_SPEC;
	public static final ItemEntry<BasicRecordItem> MAN_MUSIC_DISC;

	static {
		MAN_SOUND = VoidThings.REGISTRATE.simple(
				"music_disc.man",
				Registries.SOUND_EVENT,
				() -> SoundEvent.createVariableRangeEvent(
						VoidThings.loadResource("music_disc.man")
				)
		);

		MAN_RECORD_SPEC = RecordSpec.of(
				() -> MAN_SOUND.get(),
				15,
				70 * 20
		);

		MAN_MUSIC_DISC = VoidThings.REGISTRATE.item("man_music_disc",
						p -> new BasicRecordItem(MAN_RECORD_SPEC, p))
				.properties(p -> p.rarity(Rarity.RARE))
				.defaultModel()
				.defaultLang()
				.tab(VTCreativeTabs.getTabKey())
				.register();
	}

	private VTMusicDisc() {
	}

	public static void register() {
		VoidThings.registerLog("唱片");
	}
}
