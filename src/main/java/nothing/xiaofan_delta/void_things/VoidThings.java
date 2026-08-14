package nothing.xiaofan_delta.void_things;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import nothing.xiaofan_delta.void_things.common.config.CommonConfig;
import nothing.xiaofan_delta.void_things.common.register.VTBlocks;
import nothing.xiaofan_delta.void_things.common.register.VTCreativeTabs;
import nothing.xiaofan_delta.void_things.common.register.VTItems;
import org.slf4j.Logger;

@Mod(VoidThings.MODID)
public class VoidThings {
	public static final String MODID = "void_things";
	public static final String NAME = "Void Things";
	private static final Logger LOGGER = LogUtils.getLogger();

	// 工具方法：获取模组命名空间下的资源路径
	public static ResourceLocation loadResource(String path) {
		return ResourceLocation.fromNamespaceAndPath(MODID, path);
	}

	// 工具方法：获取 GUI 纹理路径
	public static ResourceLocation loadGui(String path) {
		return loadResource("textures/gui/%s.png".formatted(path));
	}

	// 工具方法：记录注册信息
	public static void registerLog(String registerType) {
		LOGGER.info("{} {} 已注册！", NAME, registerType);
	}

	public VoidThings(FMLJavaModLoadingContext context) {
		IEventBus modEventBus = context.getModEventBus();

		// -------- 注册模块（拆分为独立类） --------
		VTBlocks.register(modEventBus);
		VTItems.register(modEventBus);
		VTCreativeTabs.register(modEventBus);

		// -------- 注册事件监听 --------
		modEventBus.addListener(this::commonSetup);
		modEventBus.addListener(this::addCreative);

		// -------- 注册通用事件总线 --------
		MinecraftForge.EVENT_BUS.register(this);

		// -------- 注册配置文件 --------
		context.registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);
	}

	// -------- 通用初始化（FMLCommonSetupEvent） --------
	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			// 在这里执行一些跨端（客户端/服务端）的初始化逻辑
			LOGGER.info("{} 通用初始化完成！", NAME);
		});
	}

	// -------- 将物品添加到创造模式标签页 --------
	private void addCreative(BuildCreativeModeTabContentsEvent event) {
		// 如果你的模组有创造模式标签页，这里可以添加额外的物品到原版标签页
		// 例如：将 void_ingot 添加到“原材料”标签页
		// if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
		//	 event.accept(VTItems.VOID_INGOT.get());
		// }
	}

	// ============================================
	// 服务端事件（@SubscribeEvent）
	// ============================================
	@SubscribeEvent
	public void onServerStarting(ServerStartingEvent event) {
		LOGGER.info("{} 服务端启动！", NAME);
	}

	// ============================================
	// 客户端事件（@EventBusSubscriber）
	// ============================================
	@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientModEvents {
		@SubscribeEvent
		public static void onClientSetup(FMLClientSetupEvent event) {
			// 客户端初始化逻辑（如：注册按键绑定、模型等）
			LOGGER.info("{} 客户端初始化完成！", NAME);
		}
	}
}