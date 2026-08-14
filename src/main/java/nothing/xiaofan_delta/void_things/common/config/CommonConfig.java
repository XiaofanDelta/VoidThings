package nothing.xiaofan_delta.void_things.common.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;
import nothing.xiaofan_delta.void_things.VoidThings;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 无物模组通用配置（Common Config）
 * 此配置在客户端和服务端均会加载，适用于跨端共享的设置。
 */
@Mod.EventBusSubscriber(modid = VoidThings.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonConfig {
	private static final Logger LOGGER = LogUtils.getLogger();
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

	// ============================================
	// 配置项分组
	// ============================================

	// -------- 日志相关 --------
	private static final ForgeConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
			.comment("是否在通用初始化时记录泥土方块的信息（调试用）")
			.define("logDirtBlock", false);

	// -------- 数值相关 --------
	private static final ForgeConfigSpec.IntValue MAGIC_NUMBER = BUILDER
			.comment("一个魔法数字，可用于模组内部的数值调整")
			.defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

	private static final ForgeConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
			.comment("魔法数字的提示信息")
			.define("magicNumberIntroduction", "这个魔法数字是... ");

	// -------- 物品列表相关 --------
	private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
			.comment("在通用初始化时记录的物品列表（用资源路径指定）")
			.defineListAllowEmpty("items", List.of("void_things:void_ingot"), CommonConfig::validateItemName);

	// -------- 构建配置规范 --------
	public static final ForgeConfigSpec SPEC = BUILDER.build();

	// ============================================
	// 公共静态字段（供模组代码直接访问）
	// ============================================
	public static boolean logDirtBlock;
	public static int magicNumber;
	public static String magicNumberIntroduction;
	public static Set<Item> items;

	// ============================================
	// 工具方法
	// ============================================

	/**
	 * 验证物品名称是否有效（即是否已在 Forge 注册表中存在）
	 */
	private static boolean validateItemName(final Object obj) {
		return obj instanceof final String itemName
				&& ForgeRegistries.ITEMS.containsKey(ResourceLocation.parse(itemName));
	}

	// ============================================
	// 配置加载事件
	// ============================================

	@SubscribeEvent
	static void onLoad(final ModConfigEvent event) {
		// 从 ForgeConfigSpec 同步值到静态字段
		logDirtBlock = LOG_DIRT_BLOCK.get();
		magicNumber = MAGIC_NUMBER.get();
		magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();

		items = ITEM_STRINGS.get().stream()
				.map(itemName -> ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(itemName)))
				.collect(Collectors.toSet());

		// 配置加载日志
		LOGGER.info("【无物】配置已加载！");
		LOGGER.info("  - 记录泥土方块: {}", logDirtBlock);
		LOGGER.info("  - 魔法数字: {}", magicNumber);
		LOGGER.info("  - 提示信息: {}", magicNumberIntroduction);
		LOGGER.info("  - 物品列表: {}", ITEM_STRINGS.get());
	}
}