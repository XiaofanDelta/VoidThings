package nothing.xiaofan_delta.void_things.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import nothing.xiaofan_delta.void_things.VoidThings;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = VoidThings.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonConfig {
	private static final Logger LOGGER = LogUtils.getLogger();

	// ---------- 交互物品配置 ----------
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> VOID_BEDROCK_USABLE_ITEMS;
	// 运行时解析后的物品集合（方便快速查找）
	public static Set<ResourceLocation> VOID_BEDROCK_USABLE_ITEMS_SET;

	public static final ForgeConfigSpec SPEC;

	static {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

		// ===== 交互物品 =====
		builder.comment("Items that can interact with void bedrock (left-click)").push("interaction");
		VOID_BEDROCK_USABLE_ITEMS = builder
				.comment("List of item registry names (e.g. 'minecraft:netherite_pickaxe')")
				.defineListAllowEmpty("usableItems", List.of("minecraft:netherite_pickaxe"),
						CommonConfig::validateItemName);
		builder.pop();

		SPEC = builder.build();
	}

	private static boolean validateItemName(final Object obj) {
		return obj instanceof String s && ResourceLocation.isValidResourceLocation(s);
	}

	@SubscribeEvent
	static void onLoad(final ModConfigEvent event) {
		// 解析物品列表为 ResourceLocation 集合
		VOID_BEDROCK_USABLE_ITEMS_SET = VOID_BEDROCK_USABLE_ITEMS.get().stream()
				.map(ResourceLocation::new)
				.collect(Collectors.toSet());

		LOGGER.info("【无物】配置已加载！");
		LOGGER.info("  - 可用交互物品: {}", VOID_BEDROCK_USABLE_ITEMS_SET);
	}
}