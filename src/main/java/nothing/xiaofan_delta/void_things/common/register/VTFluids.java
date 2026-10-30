package nothing.xiaofan_delta.void_things.common.register;

import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.common.fluid.VoidWaterFluidType;

public final class VTFluids {
	public static final FluidEntry<ForgeFlowingFluid.Flowing> WATER;

	static {
		WATER = VoidThings.REGISTRATE.fluid(
						"water",
						VoidThings.loadResource("block/fluid/water/still"),
						VoidThings.loadResource("block/fluid/water/flowing"),
						(properties, stillTexture, flowingTexture) ->
								new VoidWaterFluidType(properties, stillTexture, flowingTexture)
				)
				.properties((properties) -> properties
						.density(1000)
						.viscosity(1000)
						.canConvertToSource(false)
						.canDrown(true)
						.canSwim(true)
						.supportsBoating(false))
				.fluidProperties((properties) -> properties
						.slopeFindDistance(4)
						.levelDecreasePerBlock(1)
						.tickRate(5))
				.register();
	}

	private VTFluids() {
	}

	public static void register() {
		VoidThings.registerLog("流体");
	}
}
