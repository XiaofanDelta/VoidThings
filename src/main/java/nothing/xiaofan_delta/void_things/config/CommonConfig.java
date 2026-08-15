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

	// ---------- 世界生成配置 ----------
	public static ForgeConfigSpec.BooleanValue VOID_BEDROCK_ENABLED;
	public static ForgeConfigSpec.DoubleValue VOID_BEDROCK_SPAWN_RATE;

	// ---------- 交互物品配置 ----------
	public static ForgeConfigSpec.ConfigValue<List<? extends String>> VOID_BEDROCK_USABLE_ITEMS;
	// 运行时解析后的物品集合（方便快速查找）
	public static Set<ResourceLocation> VOID_BEDROCK_USABLE_ITEMS_SET;

	public static final ForgeConfigSpec SPEC;

	static {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

		// ===== 世界生成 =====
		builder.comment("World Generation Settings").push("worldgen");
		VOID_BEDROCK_ENABLED = builder
				.comment("Enable void bedrock generation in the world")
				.define("voidBedrockEnabled", true);
		VOID_BEDROCK_SPAWN_RATE = builder
				.comment("Spawn rate of void bedrock (0.0 = none, 1.0 = all bedrock replaced)")
				.defineInRange("voidBedrockSpawnRate", 0.3, 0.0, 1.0);
		builder.pop();

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
		LOGGER.info("  - 虚空基岩生成: {}", VOID_BEDROCK_ENABLED.get());
		LOGGER.info("  - 虚空基岩生成概率: {}", VOID_BEDROCK_SPAWN_RATE.get());
		LOGGER.info("  - 可用交互物品: {}", VOID_BEDROCK_USABLE_ITEMS_SET);
	}
}