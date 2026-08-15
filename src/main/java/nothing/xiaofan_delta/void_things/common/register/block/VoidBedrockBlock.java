package nothing.xiaofan_delta.void_things.common.register.block;

import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class VoidBedrockBlock extends Block {
	public VoidBedrockBlock() {
		super(BlockBehaviour.Properties.of()
				.mapColor(MapColor.COLOR_BLACK) // 地图颜色（黑色）
				.strength(-1.0F, 3600000.0F) // -1 硬度 = 不可破坏（和基岩一样）
				.sound(SoundType.STONE) // 音效（和基岩一致）
				.noLootTable() // 不通过常规方式掉落（我们手动控制）
				.pushReaction(PushReaction.BLOCK) // 不能被活塞推动
				.isValidSpawn((state, level, pos, entity) -> false) // 不能在上面生成生物（和基岩一样）
		);
	}

}
