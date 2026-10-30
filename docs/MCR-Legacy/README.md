# MCR 遗留工程归档

本目录归档旧 MCreator 工程 `more_item` (Xiaofan's More Item 4.6) 中仍然有价值的内容, 供 `void_things` 后续移植时查表与取用.

- 数值、主题、配方、标签、进度、GUI 与文本 → [Setting.md](./Setting.md)
- 可复用的 Java 实现 (原样归档) → [scripts/](./scripts/)
- 脚本逐文件说明与移植清单 → [scripts/README.md](./scripts/README.md)

## 目录结构

```text
docs/MCR-Legacy/
├── README.md          # 本文件: 归档说明、脚本索引、使用方式
├── Setting.md         # 旧工程设定参考
└── scripts/           # 可复用的 Java 实现, 原样复制自旧工程
    ├── README.md
    ├── item/          # 物品逻辑: 消耗型材料、合成剩余物、护甲、工具、黑曜石破坏者
    ├── block/         # 方块与方块实体: 玻璃质感方块、白块、融合器
    ├── menu/          # 融合器菜单
    └── client/        # 融合器界面
```

## 归档原则

1. **原样归档**: `scripts/` 下的 Java 与旧工程逐一致, 没有改写, 方便直接对照原始实现.
2. **不参与构建**: `docs/` 不在 Gradle 的 `sourceSets` 内, 这些 `.java` 不会被编译, 也不会进入 jar. 它们不是生产代码.
3. **移植时必须重写**: 旧代码是 MCreator 生成物, 使用 `DeferredRegister`、`ResourceLocation` 字面量、`Component.literal`, 且是空格缩进. 移植时按 [CONTRIBUTING.md](../../CONTRIBUTING.md) 改为 Registrate + Nebula Libs、`VoidThings.loadResource`、Datagen 语言文件、Tab 缩进.
4. **数值不等于设计**: 旧工程的工具/护甲数值接近作弊级, 照搬会破坏平衡. 见 [Setting.md](./Setting.md) 末尾的取舍建议.

## 脚本索引

| 文件 | 原路径 | 职责 | 复用等级 |
|---|---|---|---|
| `scripts/item/EmptyOreItem.java` | `item/EmptyOreItem.java` | 用耐久当"可消耗合成材料" | 参考 (机制需重做) |
| `scripts/item/EmptyHammerItem.java` | `item/EmptyHammerItem.java` | 合成后返回自身 | 高 |
| `scripts/item/EmptyWrenchItem.java` | `item/EmptyWrenchItem.java` | 合成后返回自身 | 高 |
| `scripts/item/EmptyHammerHeadItem.java` | `item/EmptyHammerHeadItem.java` | 合成后返回空锭 | 高 |
| `scripts/item/EmptyShieldItem.java` | `item/EmptyShieldItem.java` | 自定义修复材料的盾 | 高 |
| `scripts/item/ObsidianBreakerItem.java` | `item/ObsidianBreakerItem.java` | 按目标方块决定挖掘速度 | 高 |
| `scripts/item/EmptyArmorItem.java` | `item/EmptyArmorItem.java` | 匿名 `ArmorMaterial` + 四部位子类 + 材质贴图 | 高 |
| `scripts/item/EmptyPickaxeItem.java` 等 5 个 | `item/Empty*.java` | 自定义 `Tier` 的工具模板 | 中 (5 份几乎相同, 建议合并为一个 `Tier`) |
| `scripts/block/EmptyIngotBlockBlock.java` | `block/EmptyIngotBlockBlock.java` | 玻璃质感 + 可含水方块 | 高 |
| `scripts/block/EmptyOreBlockBlock.java` | `block/EmptyOreBlockBlock.java` | 同上, 掉落空矿 | 高 |
| `scripts/block/WhiteBlockBlock.java` | `block/WhiteBlockBlock.java` | 自发光 + 信标色 + 加速方块 | 高 |
| `scripts/block/AdvancedFuserBlock.java` | `block/AdvancedFuserBlock.java` | 朝向方块 + 右键开 GUI + 破坏掉落 | 中 (需改为 Registrate 菜单) |
| `scripts/block/AdvancedFuserBlockEntity.java` | `block/entity/AdvancedFuserBlockEntity.java` | 6 格容器 + 物品处理器 | 中 |
| `scripts/menu/AdvancedFuserGuiMenu.java` | `world/inventory/AdvancedFuserGuiMenu.java` | 6 槽位约束 + 快速移动 | 中 |
| `scripts/client/AdvancedFuserGuiScreen.java` | `client/gui/AdvancedFuserGuiScreen.java` | 背景与叠加贴图绘制 | 中 |

## 使用方式

移植一项内容时:

1. 在 [Setting.md](./Setting.md) 找到对应数值、配方、标签与文本.
2. 从 `scripts/` 取实现, 先读 [scripts/README.md](./scripts/README.md) 的"必须改动"清单.
3. 按 [CONTRIBUTING.md](../../CONTRIBUTING.md) 改写: 注册走 Registrate, 资源路径走 `VoidThings.loadResource`, 语言文本走 Datagen, 统计信息写 Javadoc.
4. 运行 `gradlew build` 与 `gradlew runData` 验证, 检查 `src/generated/resources/assets/void_things/lang/`.
5. 更新 [docs/TODO.md](../TODO.md) 与 [UpdateLogs.md](../../UpdateLogs.md) (中英双语).

## 未归档的内容

以下内容保留了但未放进本目录, 需要时回原工程查:

| 内容 | 原位置 | 原因 |
|---|---|---|
| MCR 注册样板 (`MoreItemModItems` / `Blocks` / `BlockEntities` / `Menus` / `Screens` / `Tabs`) | `src/main/java/net/mcreator/moreitem/init/` | 被 Registrate 取代, 无复用价值 |
| 元素定义 JSON | `elements/*.mod.json` | 数值已完整摘录到 [Setting.md](./Setting.md), 原始文件体积大 |
| 配方 / 进度 / 标签 JSON | `src/main/resources/data/more_item/` | 结构已在 [Setting.md](./Setting.md) 列成表 |
| 贴图与模型 | `src/main/resources/assets/more_item/` | 部分已复制进 `void_things`, 其余按需取用 |

## 已知半成品与可疑项

- `advanced_fuser` 只有容器, **没有处理逻辑**; `advanced_fuser_gui_step4.png` (进度条) 从未被绘制.
- `empty_ore_block` 的世界生成直接替换玻璃/染色玻璃/沙, 与常见矿石生成方式不同.
- `get_empty_shield` 的英文标题误写成 `Shovel`; `get_empty_pickaxe` 英文标题风格与其余不一致.
- `empty_shield` 英文名少了 `§k`: `§eShield made of §r______`.
- `EmptyIngot` 的元素定义里 `recipeRemainder` 指向空粒, 但生成代码没有实现.
- 元素定义 `specialInformation` 引用 `item_tips...tips1/tips2`, 与实际语言键 `...description_0/1` 不一致.
- 标签 `forge:wrenches` 同时包含空锤与空扳手, `forge:tools` 只指向 `forge:wrenches`.
- 旧工程 `textures/item/新建文本文档.txt` 疑似误放文件.

## 与 `void_things` 的对应现状

| 旧工程内容 | `void_things` 现状 |
|---|---|
| `empty_ingot` / `empty_nugget` / `empty_stick` / `empty_plate` | 已注册, 见 [VTItems.java](../../src/main/java/nothing/xiaofan_delta/void_things/common/register/VTItems.java) |
| 8 种空XX材料 | 未注册, 收敛为 `empty_shard` |
| `empty_ingot_block` | 已注册 (普通方块, 未做玻璃质感) |
| `empty_ore` / `empty_ore_block` | 未注册; 贴图 `textures/block/ore/void_bedrock/empty_ore.png` 已存在 |
| `black_ingot` / `white_ingot` / 黑白锭块 | 未注册; 贴图与 `unconverted/{black,white}/0~2.png` 已就位 |
| `empty_hammer` / `empty_wrench` | 未注册; `textures/item/hammer.png`、`wrench.png` 已就位 |
| 工具 / 护甲 / 盾 | 未移植 |
| `advanced_fuser` | 未移植; [docs/TODO.md](../TODO.md) P3 的"组装机"应为其正式版 |
| 空矿世界生成 / 进度 / 标签 | 未移植 |

## 相关文档

- [docs/Agent.md](../Agent.md): Agent 阅读指引
- [docs/Main.md](../Main.md): 世界观与已完成进度
- [docs/TODO.md](../TODO.md): 开发待办
- [CONTRIBUTING.md](../../CONTRIBUTING.md): 强制规范
