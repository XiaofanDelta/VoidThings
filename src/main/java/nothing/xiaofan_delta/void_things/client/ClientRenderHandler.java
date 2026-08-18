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

@Mod.EventBusSubscriber(modid = VoidThings.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRenderHandler {

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {

			// 使用 translucent（半透明混合）
			ItemBlockRenderTypes.setRenderLayer(VTBlocks.SAFETY_GLASS.get(), RenderType.translucent());

			// 使用 cutout
			ItemBlockRenderTypes.setRenderLayer(VTBlocks.CONTROLLER.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(VTBlocks.EMPTY_INGOT_BLOCK.get(), RenderType.cutout());

		});
	}
}