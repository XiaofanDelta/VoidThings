package nothing.xiaofan_delta.void_things.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;

import net.minecraftforge.registries.ForgeRegistries;
import nothing.xiaofan_delta.void_things.VoidThings;
import nothing.xiaofan_delta.void_things.common.register.VTItems;
import nothing.xiaofan_delta.void_things.common.register.VTBlocks;
import nothing.xiaofan_delta.void_things.config.CommonConfig;

@Mod.EventBusSubscriber(modid = VoidThings.MODID)
public class VoidBedrockHandler {
	@SubscribeEvent
	public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
		// 1. 获取玩家和世界
		Player player = event.getEntity();
		Level level = event.getLevel();
		BlockPos pos = event.getPos();

		// 2. 检查点击的是否是虚空基岩
		if (!level.getBlockState(pos).is(VTBlocks.VOID_BEDROCK.get())) {
			return;
		}

		// 3. 检查是否拿着下界合金镐
		ItemStack heldItem = player.getMainHandItem();
		ResourceLocation heldItemId = ForgeRegistries.ITEMS.getKey(heldItem.getItem());
		if (heldItemId == null || !CommonConfig.VOID_BEDROCK_USABLE_ITEMS_SET.contains(heldItemId)) {
			return;  // 物品不在配置列表中，无反应
		}

		// 4. 防止在服务端逻辑中重复触发（只处理服务端）
		if (level.isClientSide()) {
			return;
		}

		// 5a. 替换为基岩
		level.setBlockAndUpdate(pos, Blocks.BEDROCK.defaultBlockState());

		// 5b. 将“无之碎片”生成在基岩上方
		BlockPos dropPos = pos.above();  // 基岩正上方一格
		ItemStack shard = new ItemStack(VTItems.EMPTY_SHARD.get(), 1);
		// 使用 addFreshEntity 生成在世界中
		level.addFreshEntity(new ItemEntity(level,
				dropPos.getX() + 0.5,  // X 居中
				dropPos.getY() + 0.5,  // Y 在方块中心偏上一点
				dropPos.getZ() + 0.5,  // Z 居中
				shard
		));

		// 5c. 减少下界合金镐耐久
		heldItem.hurt(1, player.getRandom(), (ServerPlayer) player);

		// 5d. 播放音效
		level.playSound(
				null,
				pos,
				SoundEvents.PORTAL_TRIGGER,
				SoundSource.BLOCKS,
				1.0F,
				1.0F
		);

		// 5e. 生成粒子（黑色/紫色漩涡）
		if (level instanceof ServerLevel serverLevel) {
			serverLevel.sendParticles(ParticleTypes.PORTAL,
					pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
					30, 0.5, 0.5, 0.5, 0.1);
		}

	}
}
