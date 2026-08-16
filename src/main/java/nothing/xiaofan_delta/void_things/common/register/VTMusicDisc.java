package nothing.xiaofan_delta.void_things.common.register;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import nothing.xiaofan_delta.void_things.VoidThings;

public class VTMusicDisc {
	// 声音事件注册器
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
			DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, VoidThings.MODID);

	// 注册唱片的声音事件
	public static final RegistryObject<SoundEvent> MAN_MUSIC_DISC_SOUND =
			SOUND_EVENTS.register("music_disc.man",
					() -> SoundEvent.createVariableRangeEvent(
							ResourceLocation.fromNamespaceAndPath(VoidThings.MODID, "music_disc.man")
					));

	// 注册唱片物品（使用 RecordItem）
	public static final RegistryObject<Item> MAN_MUSIC_DISC =
			VTItems.ITEMS.register("man_music_disc",
					() -> new RecordItem(
							15, // 红石比较器输出信号强度 (0-15)
							MAN_MUSIC_DISC_SOUND.get(), // 关联的声音事件
							new Item.Properties()
									.stacksTo(1) // 最大堆叠数为 1
									.rarity(Rarity.RARE), // 稀有度（显示为紫色名称）
							73
					));
}