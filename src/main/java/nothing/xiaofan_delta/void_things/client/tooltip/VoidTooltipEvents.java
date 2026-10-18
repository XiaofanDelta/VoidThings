package nothing.xiaofan_delta.void_things.client.tooltip;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.common.register.VTFluids;

/**
 * 水桶 tooltip: 显示带斜体+删除线的流体名.
 */
@Mod.EventBusSubscriber(modid = VoidThings.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class VoidTooltipEvents {
	private VoidTooltipEvents() {
	}

	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		Item bucket = VTFluids.WATER.getBucket().orElse(null);
		if (bucket == null || !event.getItemStack().is(bucket)) {
			return;
		}

		event.getToolTip().add(
				Component.translatable("fluid_type.void_things.water")
						.withStyle(style -> style.withItalic(true).withStrikethrough(true))
		);
	}
}
