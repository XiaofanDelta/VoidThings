package nothing.xiaofan_delta.void_things.lib;

import dev.celestiacraft.libs.register.NebulaRegistrate;
import net.minecraftforge.eventbus.api.IEventBus;
import nothing.xiaofan_delta.void_things.VoidThings;

/**
 * Registrate 单例入口.
 */
public final class VoidRegistrate {
	public static final NebulaRegistrate REGISTRATE = NebulaRegistrate.create(VoidThings.MODID);

	private VoidRegistrate() {
	}

	public static void register(IEventBus bus) {
		REGISTRATE.registerEventListeners(bus);
	}
}
