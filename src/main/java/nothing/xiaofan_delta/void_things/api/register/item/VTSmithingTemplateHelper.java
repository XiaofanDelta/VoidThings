package nothing.xiaofan_delta.void_things.api.register.item;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import nothing.xiaofan_delta.void_things.VoidThings;

import java.util.List;

/**
 * 简化锻造模板创建工具.
 * <p>
 * 参数顺序: appliesTo, ingredients, title, baseSlot, additionSlot, baseIcons, additionIcons.
 */
public class VTSmithingTemplateHelper {

	/**
	 * 生成翻译键.
	 *
	 * @param desc 描述后缀
	 * @return 翻译键
	 */
	private static String makeDescription(String desc) {
		return Util.makeDescriptionId(
				"item",
				ResourceLocation.fromNamespaceAndPath(VoidThings.MODID, desc)
		);
	};

	/**
	 * 最简创建, 自动生成翻译键.
	 *
	 * @param id 模板 ID
	 * @param baseSlotIcon Base 槽位图标
	 * @param additionSlotIcon Addition 槽位图标
	 * @return 新的 SmithingTemplateItem
	 */
	public static SmithingTemplateItem createSimple(String id, ResourceLocation baseSlotIcon, ResourceLocation additionSlotIcon) {
		String itemId = id + "_smithing_template";

		// 生成翻译键
		Component appliesTo = Component.translatable(makeDescription(itemId + ".applies_to"));
		Component ingredients = Component.translatable(makeDescription(itemId + ".ingredients"));
		Component title = Component.translatable(makeDescription(itemId));
		Component baseSlotDesc = Component.translatable(makeDescription(itemId + ".base_slot"));
		Component additionSlotDesc = Component.translatable(makeDescription(itemId + ".addition_slot"));

		List<ResourceLocation> baseIcons = List.of(baseSlotIcon);
		List<ResourceLocation> additionIcons = List.of(additionSlotIcon);

		return new SmithingTemplateItem(
				appliesTo,
				ingredients,
				title,
				baseSlotDesc,
				additionSlotDesc,
				baseIcons,
				additionIcons
		);
	}

	/**
	 * 完全自定义创建.
	 *
	 * @param appliesTo 可应用描述
	 * @param ingredients 原材料描述
	 * @param title 升级标题
	 * @param baseSlotDesc Base 槽描述
	 * @param additionSlotDesc Addition 槽描述
	 * @param baseSlotIcon Base 槽图标
	 * @param additionSlotIcon Addition 槽图标
	 * @return 新的 SmithingTemplateItem
	 */
	public static SmithingTemplateItem createCustom(
			Component appliesTo,
			Component ingredients,
			Component title,
			Component baseSlotDesc,
			Component additionSlotDesc,
			ResourceLocation baseSlotIcon,
			ResourceLocation additionSlotIcon
	) {
		return new SmithingTemplateItem(
				appliesTo,
				ingredients,
				title,
				baseSlotDesc,
				additionSlotDesc,
				List.of(baseSlotIcon),
				List.of(additionSlotIcon)
		);
	}

	/**
	 * 获取升级标题翻译键.
	 *
	 * @param id 模板 ID
	 * @return 翻译键
	 */
	public static String getTitleKey(String id) {
		return Util.makeDescriptionId("upgrade", ResourceLocation.fromNamespaceAndPath(VoidThings.MODID, id));
	}

	/**
	 * 获取公共描述翻译键.
	 *
	 * @param suffix 后缀
	 * @return 翻译键
	 */
	public static String getCommonKey(String suffix) {
		return Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(VoidThings.MODID, "smithing_template." + suffix));
	}
}