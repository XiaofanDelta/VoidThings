package nothing.xiaofan_delta.void_things.common.block;

import dev.celestiacraft.libs.api.register.block.BasicBlock;
import dev.celestiacraft.libs.api.register.block.BlockFacing;

public class HorizontalBlock extends BasicBlock {
	public HorizontalBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected BlockFacing useFacingType() {
		return BlockFacing.HORIZONTAL;  // 启用水平方向（N/E/S/W）
	}

	// 可选：如果树根放置时想让它的"正面"（纹理不同的一面）朝向玩家
	// 默认 getStateForPlacement 已经做了这件事（水平方向会朝向玩家反方向）
}