package nothing.xiaofan_delta.void_things.common.fluid;

import dev.celestiacraft.libs.api.register.fluid.BasicFluidType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidType;

/**
 * 虚空水流体类型.
 */
public class VoidWaterFluidType extends BasicFluidType {
	private final ResourceLocation stillTexture;
	private final ResourceLocation flowingTexture;

	public VoidWaterFluidType(FluidType.Properties properties, ResourceLocation stillTexture, ResourceLocation flowingTexture) {
		super(properties);
		this.stillTexture = stillTexture;
		this.flowingTexture = flowingTexture;
	}

	@Override
	public ResourceLocation getStillTexture() {
		return stillTexture;
	}

	@Override
	public ResourceLocation getFlowingTexture() {
		return flowingTexture;
	}

	@Override
	public int getTintColor() {
		return 0xFFFFFFFF;
	}

	@Override
	public Component getDescription() {
		return Component.translatable("fluid_type.void_things.water")
				.withStyle(style -> style.withItalic(true).withStrikethrough(true));
	}
}
