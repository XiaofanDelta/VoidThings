# scripts/ 说明

本目录存放从旧 MCreator 工程 `more_item` 原样复制的 Java 实现. 它们**不是生产代码**, 不参与 Gradle 构建, 也不能直接编译 (缺少 MCR 的注册类与包结构).

- 每个文件与旧工程一致, 保留原始包名 `net.mcreator.moreitem.*`.
- 归档目的: 保留可直接复用的实现思路, 移植时按 [CONTRIBUTING.md](../../../CONTRIBUTING.md) 改写.
- 数值与设定的来源见 [Setting.md](../Setting.md); 归档总体说明见 [README.md](../README.md).

## 通用改写清单

移植任意一个文件时, 至少处理以下事项:

| 项 | 旧工程 | `void_things` 要求 |
|---|---|---|
| 包名 | `net.mcreator.moreitem.item` / `.block` / `.world.inventory` / `.client.gui` | `nothing.xiaofan_delta.void_things.common.item` / `.common.block` / `.client` 等, 见现有目录结构 |
| 缩进 | 空格 | Tab, 显示宽度 4 |
| 注册 | `DeferredRegister` + `RegistryObject` | `Registrate` + `ItemEntry` / `BlockEntry`, 字段集中在类顶部, `static {}` 中注册 |
| 资源路径 | `new ResourceLocation("more_item:...")` | `VoidThings.loadResource(...)` / `VoidThings.loadGui(...)` |
| 他模组资源 | 同上 | `ResourceLocation.fromNamespaceAndPath("modid", "path")` 或 `tryParse` |
| 文本 | `Component.literal(...)` | `Component.translatable(...)`, 语言键进 Datagen |
| 客户端限制 | `@Mod.EventBusSubscriber(value = Dist.CLIENT)` | 保持, 客户端专用代码放 `client` 包 |
| 通用能力 | MCR 样板 (`guiState`、`bound`、`Supplier<Map>` 等) | 删除无用脚手架; 能复用 Nebula Libs `Basic*` 的优先复用 |
| 注释 | 无 | 中文 Javadoc, 复杂逻辑分步注释 |

## 逐文件说明

### `item/`

| 文件 | 职责 | 移植要点 |
|---|---|---|
| `EmptyOreItem.java` | 空矿: 堆叠 4, 耐久 64, 合成时返回"磨损 1 点的自身", 耗尽消失 | 1.20.1 只有 `getCraftingRemainingItem` 会被调用, 机制可行但与堆叠/配方书配合脆弱. 建议移植前决定是否保留 |
| `EmptyHammerItem.java` | 空锤: 合成后返回自身 | 依赖 `hasCraftingRemainingItem`; 可继承 `Item` 直接实现 |
| `EmptyWrenchItem.java` | 空扳手: 合成后返回自身 | 同上, 与空锤实现完全一致, 可共用一个基类 |
| `EmptyHammerHeadItem.java` | 空锤头: 合成后返回空锭 | 返回 `VTItems.EMPTY_INGOT.get()` |
| `EmptyShieldItem.java` | 空盾: 自定义修复材料 | 还需在客户端注册 `blocking` 物品属性, 否则举盾无动画 |
| `ObsidianBreakerItem.java` | 黑曜石破坏者: 只对黑曜石/哭泣的黑曜石/重生锚高速挖掘 | 旧实现没有 `Tier`, 因此挖黑曜石**不掉落**; 应改用 `TieredItem` 或自定义 `isCorrectToolForDrops` |
| `EmptyArmorItem.java` | 空护甲: 匿名 `ArmorMaterial` + 四个内部类 + 材质贴图 | 1.20.1 的 `ArmorMaterial` 接口方法保持兼容; 注意 `getName()` 与材质路径; 数值需重平衡 |
| `EmptyPickaxeItem.java` | 自定义 `Tier` 的镐 | 与以下 4 个工具几乎相同, 建议合并为一个 `EmptyToolTier` |
| `EmptySwordItem.java` | 剑 | 旧数值: 攻击 +46, 攻速 +46, 属于作弊级 |
| `EmptyAxeItem.java` | 斧 | 攻击 +18, 攻速 +16 |
| `EmptyShovelItem.java` | 铲 | 攻击 +8, 攻速 +6 |
| `EmptyHoeItem.java` | 锄 | 攻击 +9, 攻速 +6 |

### `block/`

| 文件 | 职责 | 移植要点 |
|---|---|---|
| `EmptyIngotBlockBlock.java` | 空锭方块: 玻璃质感、可含水、无碰撞遮挡、活塞可破坏 | 移植时改用 loot table 控制"掉 9 锭"; `getVisualShape` 返回空 + `skipRendering` 才是玻璃质感的关键 |
| `EmptyOreBlockBlock.java` | 空矿方块: 同上, 掉落空矿 | 世界生成需在 Datagen 中单独写 configured/placed feature |
| `WhiteBlockBlock.java` | 白块: 自发光、亮度 2、信标白色、方块速度 ×2、推动只被推 | 注意 `org.checkerframework.checker.units.qual.s` 是 MCR 为 `lightLevel` lambda 生成的辅助 import, 可直接删除 |
| `AdvancedFuserBlock.java` | 高级融合器: 朝向方块 + 右键打开 GUI + 破坏掉落内容 | 旧实现 `use` 里重复调用 `super.use`; 移植建议用 Registrate 的 `.menu(...)` 与 `BlockEntity` 菜单, 不要照抄 `NetworkHooks.openScreen` |
| `AdvancedFuserBlockEntity.java` | 6 格容器 + `SidedInvWrapper` 物品处理 | 可参考 Nebula Libs 的 `BasicBlockEntity`; 自动化不检查黑白锭限制这一点需要修正 |

### `menu/`

| 文件 | 职责 | 移植要点 |
|---|---|---|
| `AdvancedFuserGuiMenu.java` | 6 槽位 (0 只放黑锭, 1 只放白锭, 5 只能取出) + 快速移动 | 文件内 `moveItemStackTo` 是 MCR 复制的原版实现, 可直接删除改用父类; `removed` 中逐槽 `if` 可改为循环 |

### `client/`

| 文件 | 职责 | 移植要点 |
|---|---|---|
| `AdvancedFuserGuiScreen.java` | 背景 + 叠加贴图绘制 | 只需参考贴图坐标; 旧实现把 4 张图标硬编码在 `renderBg`, 可改为常量表 |

## 不要照搬的部分

- 旧的注册样板 (`MoreItemModItems` 等) 与 `DeferredRegister` 用法.
- `Component.literal("Advanced Fuser")` 之类的硬编码文本.
- `new ResourceLocation("more_item:...")` 字面量.
- 极端数值: 10000 耐久、+46 攻击、50 攻速、100 附魔、护甲韧性 10 / 击退抗性 1.0.
- 元素/方块数值中以"魔法数字"出现的 `strength`、`lightLevel` 等, 应改为具名常量并写注释.
