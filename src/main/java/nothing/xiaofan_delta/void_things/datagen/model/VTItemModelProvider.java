package nothing.xiaofan_delta.void_things.datagen.model;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import nothing.xiaofan_delta.void_things.VoidThings;

/**
 * 生成非方块物品模型. 方块物品模型由 Registrate 自动生成.
 */
public class VTItemModelProvider extends ItemModelProvider {
	public VTItemModelProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, VoidThings.MODID, helper);
	}

	@Override
	protected void registerModels() {
		generated("empty_ingot", "item/material/empty/ingot");
		generated("empty_nugget", "item/material/empty/nugget");
		generated("empty_shard", "item/material/empty/shard");
		generated("empty_stick", "item/material/empty/stick");
		generated("empty_plate", "item/material/empty/plate");
		generated("empty_smithing_template", "item/material/empty/smithing_template");
		generated("bedrock_shard", "item/material/bedrock_shard");
		generated("egg", "item/void/egg");
		generated("man_music_disc", "item/void/music_disc");
	}

	private void generated(String name, String texture) {
		withExistingParent(name, mcLoc("item/generated"))
				.texture("layer0", modLoc(texture));
	}
}
