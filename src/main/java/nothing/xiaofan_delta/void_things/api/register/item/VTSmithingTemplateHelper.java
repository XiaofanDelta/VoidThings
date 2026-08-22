package nothing.xiaofan_delta.void_things.api.register.item;

import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import nothing.xiaofan_delta.void_things.VoidThings;

import java.util.List;

/**
 * 简化锻造模板（SmithingTemplateItem）的创建工具类
 * <p>
 * 严格遵循 SmithingTemplateItem 的构造函数参数顺序：
 * <ol>
 *     <li>appliesToDescription   -> [可应用于：] 下的描述</li>
 *     <li>ingredientDescription  -> [所需原材料：] 下的描述</li>
 *     <li>smithingTemplateName   -> 物品名称下方的升级标题（如“虚无升级”）</li>
 *     <li>baseSlotDescription    -> Base 格的描述/提示</li>
 *     <li>additionSlotDescription -> Addition 格的描述/提示</li>
 *     <li>baseSlotIcons          -> Base 格展示的图标列表</li>
 *     <li>additionSlotIcons      -> Addition 格展示的图标列表</li>
 * </ol>
 * <p>
 * 使用示例：
 * <pre>{@code
 * // 最简用法（自动生成所有翻译键）
 * public static final RegistryObject<Item> VOID_SMITHING_TEMPLATE =
 *         ITEMS.register("smithing_template",
 *                 () -> VTSmithingTemplateHelper.createSimple(
 *                         "void_upgrade",
 *                         ResourceLocation.withDefaultNamespace("item/empty_slot_ingot"),
 *                         VoidThings.loadResource("item/empty_slot_shard")
 *                 ));
 * }</pre>
 */
public class VTSmithingTemplateHelper {

	/**
	 * 生成翻译键
	 *
	 * @param desc 描述后缀，如 "void_upgrade_smithing_template.applies_to"
	 * @return 翻译键字符串
	 */
	private static String makeDescription(String desc) {
		return Util.makeDescriptionId(
				"item",
				ResourceLocation.fromNamespaceAndPath(VoidThings.MODID, desc)
		);
	};

	/**
	 * 最简创建：只需 ID 和两个占位图标，自动生成所有翻译键
	 *
	 * @param id                模板 ID（如 "void_upgrade"），用于生成升级标题翻译键
	 * @param baseSlotIcon      Base 槽位占位图标（ResourceLocation）
	 * @param additionSlotIcon  Addition 槽位占位图标（ResourceLocation）
	 * @return 新的 SmithingTemplateItem 实例
	 */
	public static SmithingTemplateItem createSimple(String id, ResourceLocation baseSlotIcon, ResourceLocation additionSlotIcon) {
		String itemId = id + "_smithing_template";

		// 生成各翻译键
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
	 * 完全自定义版本：允许覆盖任意一个 Component
	 *
	 * @param appliesTo          [可应用于：] 描述
	 * @param ingredients        [所需原材料：] 描述
	 * @param title              升级标题
	 * @param baseSlotDesc       Base 格描述
	 * @param additionSlotDesc   Addition 格描述
	 * @param baseSlotIcon       Base 格占位图标
	 * @param additionSlotIcon   Addition 格占位图标
	 * @return 新的 SmithingTemplateItem 实例
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
	 * 快捷获取升级标题的翻译键
	 * <p>
	 * 例如：upgrade.void_things.void_upgrade
	 *
	 * @param id 模板 ID
	 * @return 翻译键
	 */
	public static String getTitleKey(String id) {
		return Util.makeDescriptionId("upgrade", ResourceLocation.fromNamespaceAndPath(VoidThings.MODID, id));
	}

	/**
	 * 快捷获取公共描述/槽位翻译键
	 * <p>
	 * 例如：item.void_things.smithing_template.applies_to
	 *
	 * @param suffix 后缀，如 "applies_to"、"ingredients"、"base_slot"、"addition_slot"
	 * @return 翻译键
	 */
	public static String getCommonKey(String suffix) {
		return Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(VoidThings.MODID, "smithing_template." + suffix));
	}
}