package nothing.xiaofan_delta.void_things.common.register;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.common.effect.DarknessEchoEffect;

public final class VTEffects {
	public static final RegistryEntry<DarknessEchoEffect> DARKNESS_ECHO;

	static {
		DARKNESS_ECHO = VoidThings.REGISTRATE.effect("darkness_echo", DarknessEchoEffect::new)
				.category(MobEffectCategory.HARMFUL)
				.color(0x2B1038)
				.durationEffectTick((duration, amplifier) -> duration % 20 == 0)
				.effectTick((entity, amplifier) -> {
					if (entity.level().isClientSide()) {
						return;
					}

					// 每秒补一次短时反胃, 避免每 tick 重置
					if (!entity.hasEffect(MobEffects.CONFUSION)) {
						entity.addEffect(new MobEffectInstance(
								MobEffects.CONFUSION,
								100,
								0,
								true,
								false,
								false
						));
					}
				})
				.register();
	}

	private VTEffects() {
	}

	public static void register() {
		VoidThings.registerLog("药水效果");
	}
}
