package nothing.xiaofan_delta.void_things.common.register;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import nothing.xiaofan_delta.void_things.VoidThings;

public class VTCreativeTabs {
	private static final DeferredRegister<CreativeModeTab> TABS =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VoidThings.MODID);

	// 主标签页
	public static final RegistryObject<CreativeModeTab> VOID_TAB = TABS.register("void_tab",
			() -> CreativeModeTab.builder()
					.title(Component.translatable("itemGroup.void_things.void_tab"))
					.icon(() -> new ItemStack(VTItems.EMPTY_INGOT.get()))
					.displayItems((parameters, output) -> {
						// 添加物品到标签页
						output.accept(VTItems.EMPTY_INGOT.get());
						output.accept(VTItems.EMPTY_NUGGET.get());
						output.accept(VTItems.EMPTY_SHARD.get());
						output.accept(VTItems.EMPTY_STICK.get());
						output.accept(VTItems.EMPTY_PLATE.get());
						output.accept(VTItems.EGG.get());
						output.accept(VTMusicDisc.MAN_MUSIC_DISC.get());
						output.accept(VTItems.EMPTY_INGOT_BLOCK_ITEM.get());
						output.accept(VTItems.VOID_BEDROCK_ITEM.get());
						output.accept(VTItems.BEDSTONE_ITEM.get());
						output.accept(VTItems.BEDSTONE_BRICK_ITEM.get());
						output.accept(VTItems.CONTROLLER_ITEM.get());
						output.accept(VTItems.SAFETY_GLASS_ITEM.get());
						output.accept(VTItems.BLACK_BLOCK_ITEM.get());
						output.accept(VTItems.PURPLE_BLOCK_ITEM.get());
					})
					.build());

	// 注册方法
	public static void register(IEventBus bus) {
		TABS.register(bus);
		VoidThings.registerLog("创造模式标签页");
	}
}