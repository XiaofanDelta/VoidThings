package nothing.xiaofan_delta.void_things.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.world.level.block.Block;

import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.common.register.VTBlocks;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = VoidThings.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRenderHandler {

	@SubscribeEvent
	public static void onClientSetup(@NotNull FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			// 方块渲染层
			ItemBlockRenderTypes.setRenderLayer(VTBlocks.EMPTY_INGOT_BLOCK.get(), RenderType.cutoutMipped());
		});
	}
}