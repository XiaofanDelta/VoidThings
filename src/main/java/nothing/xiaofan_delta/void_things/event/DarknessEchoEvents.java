package nothing.xiaofan_delta.void_things.event;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.common.register.VTEffects;
import nothing.xiaofan_delta.void_things.config.CommonConfig;

/**
 * 暗影残响的环境清除逻辑。
 * <p>
 * 只在服务端、主世界类维度、白天时移除；梦魇维度通常固定夜晚，
 * 不会被这里误清。
 */
@Mod.EventBusSubscriber(modid = VoidThings.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class DarknessEchoEvents {
	private DarknessEchoEvents() {
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase != TickEvent.Phase.END) {
			return;
		}

		Player player = event.player;
		if (player.level().isClientSide()) {
			return;
		}

		if (!CommonConfig.DARKNESS_ECHO_CLEAR_AT_DAY.get()) {
			return;
		}

		if (!player.hasEffect(VTEffects.DARKNESS_ECHO.get())) {
			return;
		}

		if (player.level().isDay() && player.level().dimensionType().natural()) {
			player.removeEffect(VTEffects.DARKNESS_ECHO.get());
		}
	}
}
