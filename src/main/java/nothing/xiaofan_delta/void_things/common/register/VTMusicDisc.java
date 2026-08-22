package nothing.xiaofan_delta.void_things.common.register;

import dev.celestiacraft.libs.api.register.item.BasicRecordItem;
import dev.celestiacraft.libs.sounds.RecordSpec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import nothing.xiaofan_delta.void_things.VoidThings;

/**
 * 无物模组的唱片注册类
 * 使用 Nebula Libs 的 RecordSpec + BasicRecordItem 简化注册流程
 */
public class VTMusicDisc {
	// 1. 声音事件注册器
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
			DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, VoidThings.MODID);

	// 2. 声音事件定义
	public static final RegistryObject<SoundEvent> MAN_SOUND =
			SOUND_EVENTS.register("music_disc.man",
					() -> SoundEvent.createVariableRangeEvent(
							ResourceLocation.fromNamespaceAndPath(VoidThings.MODID, "music_disc.man")
					));

	// 3. 唱片规格（参数集中管理）
	/**
	 * 唱片参数配置
	 * - 声音：MAN (延迟引用，支持 Forge 注册顺序)
	 * - 红石信号：15 (最大值，与原版唱片一致)
	 * - 时长：70 秒
	 */
	private static final RecordSpec MAN_RECORD_SPEC =
			RecordSpec.of(
					() -> MAN_SOUND.get(),  // 使用 Supplier 延迟获取 SoundEvent
					15,                     // 红石比较器输出信号强度 (0-15)
					70 * 20                 // 播放时长 (Ticks)
			);

	// 4. 注册唱片物品
	public static final RegistryObject<Item> MAN_MUSIC_DISC =
			VTItems.ITEMS.register("man_music_disc",
					() -> new BasicRecordItem(
							MAN_RECORD_SPEC,                    // 唱片规格
							new Item.Properties()
									.rarity(Rarity.RARE)        // 稀有度
					));

}