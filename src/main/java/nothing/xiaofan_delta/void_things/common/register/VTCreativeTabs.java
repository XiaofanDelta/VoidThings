package nothing.xiaofan_delta.void_things.common.register;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import nothing.xiaofan_delta.void_things.VoidThings;

public final class VTCreativeTabs {
	public static final RegistryEntry<CreativeModeTab> VOID_TAB;

	static {
		VOID_TAB = VoidThings.REGISTRATE.simple(
				"void_tab",
				Registries.CREATIVE_MODE_TAB,
				() -> CreativeModeTab.builder()
						.title(Component.translatable("itemGroup.void_things.void_tab"))
						.icon(() -> new ItemStack(VTItems.EMPTY_INGOT.get()))
						.build()
		);
	}

	private VTCreativeTabs() {
	}

	public static ResourceKey<CreativeModeTab> getTabKey() {
		return VOID_TAB.getKey();
	}

	public static void register() {
		VoidThings.registerLog("创造模式标签页");
	}
}
