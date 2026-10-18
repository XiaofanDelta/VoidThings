package nothing.xiaofan_delta.void_things.common.block;

import dev.celestiacraft.libs.api.register.block.BasicBlock;
import dev.celestiacraft.libs.api.register.block.BlockFacing;

public class HorizontalBlock extends BasicBlock {
	public HorizontalBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected BlockFacing useFacingType() {
		return BlockFacing.HORIZONTAL;  // 启用水平方向
	}
}