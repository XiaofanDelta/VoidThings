package nothing.xiaofan_delta.void_things.common.effect;

import dev.celestiacraft.libs.api.register.effect.BasicEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

/**
 * 暗影残响。
 * <p>
 * 反胃与减速的持续效果由 {@link BasicEffect} 的回调驱动；
 * 具体触发场景 (梦魇死亡惊醒) 在 DP/梦魇模块中接入
 */
public class DarknessEchoEffect extends BasicEffect {
	private static final String MOVEMENT_SPEED_MODIFIER =
			"6a2f6b7e-3c1a-4e8b-9f0d-2b5c7d8e9f01";

	public DarknessEchoEffect(MobEffectCategory category, int color) {
		super(category, color);

		addAttributeModifier(
				Attributes.MOVEMENT_SPEED,
				MOVEMENT_SPEED_MODIFIER,
				-0.05D,
				AttributeModifier.Operation.MULTIPLY_TOTAL
		);
	}
}
