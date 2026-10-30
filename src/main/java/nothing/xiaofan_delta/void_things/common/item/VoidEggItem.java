package nothing.xiaofan_delta.void_things.common.item;

import dev.celestiacraft.libs.api.register.item.BasicItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.registries.ForgeRegistries;
import nothing.xiaofan_delta.void_things.VoidThings;

/**
 * 虚空蛋. 落入原版水源时只污染当前一格, 并变成普通鸡蛋.
 */
public class VoidEggItem extends BasicItem {
	public VoidEggItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack eggStack, ItemEntity entity) {
		Level level = entity.level();
		if (level.isClientSide()) {
			return false;
		}

		BlockPos blockPos = entity.blockPosition();
		FluidState fluid = level.getFluidState(blockPos);
		if (!fluid.is(FluidTags.WATER) || !fluid.isSource()) {
			return false;
		}

		if (!level.getBlockState(blockPos).is(Blocks.WATER)) {
			return false;
		}

		// 只替换蛋所在的这一格
		Block waterBlock = ForgeRegistries.BLOCKS.getValue(VoidThings.loadResource("water"));
		if (waterBlock == null || waterBlock == Blocks.AIR) {
			return false;
		}

		level.setBlockAndUpdate(blockPos, waterBlock.defaultBlockState());
		entity.setItem(new ItemStack(Items.EGG, eggStack.getCount()));
		level.playSound(null, blockPos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
		return false;
	}
}
