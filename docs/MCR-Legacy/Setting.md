# MCR 遗留工程设定参考 (Xiaofan's More Item 4.6)

本文件归档旧 MCreator 工程 `more_item` 中**可参考的设定**: 主题、物品与方块数值、配方、标签、进度、GUI 布局与文本.

本文件只描述"旧工程做过什么", 不代表 `void_things` 当前设计. 是否沿用、如何改写, 见 [README.md](./README.md) 与本文末尾的移植取舍.

## 一、来源与环境

| 项 | 值 |
|---|---|
| 原工程路径 | `C:\Users\fzh\MCreatorWorkspaces\more_item` |
| MCreator 版本 | `202500113416` (MCreator 2025.1) |
| 生成器 | `forge-1.20.1` |
| modid / 名称 / 版本 | `more_item` / `Xiaofan's More Item` / `4.6` |
| 包名 | `net.mcreator.moreitem` |
| 元素总数 | 89: 物品 23, 工具 7, 护甲 1, 方块 4, GUI 1, 配方 39, 进度 13, 创造标签页 1 |
| 已注册物品总数 | 38 (含 4 个方块物品与 4 件护甲) |
| 未包含 | 无 procedure, 无生物, 无维度, 无自定义声音, 无自定义粒子, 无自定义配方类型 |
| 许可 | 工作区标记为 `Not specified`; 作者与 `void_things` 相同 |

原工程目录结构:

```text
more_item/
├── more_item.mcreator          # 工作区索引: 元素表 / 标签 / 语言映射 / 标签页顺序
├── elements/                   # 每个元素一份 *.mod.json 定义 (数值的权威来源)
├── src/main/java/...           # MCreator 生成的 Java
├── src/main/resources/...      # 生成的资源: 模型 / 方块状态 / 战利品表 / 配方 / 进度 / 标签 / 贴图
└── build.gradle                # MCreator 生成
```

## 二、主题与叙事设定

### 2.1 空系列命名: `§k` 乱码占位

空系列物品的名字全部是"乱码占位 + 后缀", 表达"材质不可名状、无法被描述":

- 英文: `§eIngot made of §k______` / `§e§k______§r§eHammer`
- 中文: `§e§k______§r§e锭` / `§e§k______§r§e锤`

`§k` 是原版混淆格式, 与 [docs/Main.md](../Main.md) 中"错误 = 混淆"的颜色语义一致, 属于该系列的身份标识. 移植时必须保留.

### 2.2 统一属性

- 所有"空系列"物品 (含工具、护甲、盾、黑/白锭、黑曜石破坏者) 都带 `fireResistant()`.
- 例外: `iron_plate`、`gold_plate`、`precision_core` 不抗火.
- 所有空系列工具/护甲使用统一修复标签 `more_item:empty_items` (盾牌与护甲同).

### 2.3 WIP 标记

下列元素的文本带 `(WIP!)`, 表示当时只有贴图和名字, 没有玩法:

- `black_ingot` / `white_ingot` (黑色锭 / 白色锭, `rarity = EPIC`, 堆叠 16)
- `white_block` (白色块)
- `advanced_fuser` (高级融合器; 方块名与 GUI 标题都是 `WIP!`)
- 另: `precision_core` 为 `UNCOMMON`, 不抗火

### 2.4 空矿的由来

空矿不是普通的矿石: 它的世界生成目标方块是**玻璃 / 染色玻璃 / 染色玻璃板 / 沙**, 即"世界里的玻璃与沙被空侵蚀". 对应进度描述 `Like glasses.` / `像玻璃`, 方块本身也是玻璃质感、可含水.

### 2.5 空系列工具的语气

工具进度描述统一使用 "by the power of air!" / "用空气之力..." 的戏谑口吻, 例如:

- `Break stones by the power of air!` / `用空气之力挖掉石头!`
- `Air armors?` / `空气盔甲?`
- `So Fast!But where are my obsidians?` / `太快了!可是我黑曜石呢?`

### 2.6 单只靴子的玩笑

`empty_boot` 是"单只靴子", 堆叠 2; 配方用 2 只单靴合成 1 双 `empty_armor_boots`.

## 三、物品设定

### 3.1 基础材料

| 注册 id | 堆叠 | 稀有度 | 抗火 | 特殊行为 |
|---|---|---|---|---|
| `empty_ingot` | 64 | COMMON | 是 | 无 |
| `empty_nugget` | 64 | COMMON | 是 | 无 |
| `empty_stick` | 64 | COMMON | 是 | 无 |
| `empty_plate` | 64 | COMMON | 是 | 无 |
| `empty_parts` | 16 | COMMON | 是 | 无 |
| `empty_ball` | 16 | COMMON | 是 | 无 |
| `empty_amethyst_shard` | 64 | COMMON | 是 | 无 |
| `empty_diamond` | 64 | COMMON | 是 | 无 |
| `empty_dust` | 64 | COMMON | 是 | 无 |
| `empty_emerald` | 64 | COMMON | 是 | 无 |
| `empty_lapis_lazuli` | 64 | COMMON | 是 | 无 |
| `empty_pottery_sherd` | 64 | COMMON | 是 | 无 |
| `empty_quartz` | 64 | COMMON | 是 | 无 |
| `empty_ore` | 4 | COMMON | 是 | 耐久 64, 作合成材料时磨损 1 点, 耗尽后消失; 不可修复 |

### 3.2 金属板与功能物品

| 注册 id | 堆叠 | 稀有度 | 抗火 | 特殊行为 |
|---|---|---|---|---|
| `iron_plate` | 32 | COMMON | 否 | 无 |
| `gold_plate` | 32 | COMMON | 否 | 无 |
| `black_ingot` | 16 | EPIC | 是 | WIP |
| `white_ingot` | 16 | EPIC | 是 | WIP |
| `precision_core` | 64 | UNCOMMON | 否 | 无 |
| `empty_hammer` | 1 | COMMON | 是 | 合成后返回自身 (不消耗) |
| `empty_wrench` | 1 | COMMON | 是 | 合成后返回自身 (不消耗) |
| `empty_hammer_head` | 1 | COMMON | 是 | 合成后返回 `empty_ingot` |
| `empty_boot` | 2 | COMMON | 是 | 单只靴子 |

### 3.3 黑曜石破坏者

| 项 | 值 |
|---|---|
| 注册 id | `obsidian_breaker` |
| 名称 | `§8Obsidian Breaker` / `§8黑曜石破坏者` |
| 耐久 | 128000 |
| 附魔能力 | 2 |
| 目标方块 | 黑曜石、哭泣的黑曜石、重生锚 |
| 目标方块挖掘速度 | 10000; 其他方块为 1 |
| 攻击 | 伤害修正 -1, 攻速修正 -4 |
| 修复材料 | `empty_pickaxe`、`minecraft:obsidian`、`#more_item:empty_items` |
| 挖掘掉落等级 | NETHERITE (但生成代码未实现 `Tier`/`isCorrectToolForDrops`, 实际**挖黑曜石不掉落**) |
| Tooltip | `§2只能破坏黑曜石、哭泣的黑曜石、重生锚` / `§4注意：黑曜石不会掉落` |

元素定义中的 `specialInformation` 引用 `{item_tips.more_item.obsidian_breaker.tips1/tips2}`, 但语言文件里实际是 `item.more_item.obsidian_breaker.description_0/1`, 生成代码也读后者, 属于旧工程的不一致点.

## 四、工具与武器设定

| 注册 id | 类型 | 耐久 | 挖掘速度 | 攻击加成 | 挖掘等级 | 附魔 | 修复材料 |
|---|---|---|---|---|---|---|---|
| `empty_pickaxe` | Pickaxe | 10000 | 20 | +8 | 4 (下界合金级) | 100 | `#empty_items` |
| `empty_axe` | Axe | 2500 | 30 | +18 | 4 | 100 | `#empty_items` |
| `empty_shovel` | Shovel | 10000 | 30 | +8 | 4 | 100 | `#empty_items` |
| `empty_hoe` | Hoe | 10000 | 20 | +9 | 4 | 100 | `#empty_items` |
| `empty_sword` | Sword | 10000 | 40 | +46 | 4 | 100 | `#empty_items` |
| `empty_shield` | Shield | 100000 | - | - | - | 100 | `#empty_items` |
| `obsidian_breaker` | Item (Special) | 128000 | 仅目标方块 10000 | -1 | 4 | 2 | 见 3.3 |

补充说明:

- 全部工具/盾牌抗火, 修复材料都是 `more_item:empty_items` 标签.
- MCreator 的 `attackSpeed` 字段是**最终攻速**: 元素里 10 / 20 / 50 会生成攻速修正 6 / 16 / 46, 即 10 / 20 / 50 次每秒.
- 由此实际数值约为: 镐 9 伤 / 10 攻速, 斧 19 伤 / 20 攻速, 铲 9 伤 / 10 攻速, 锄 9 伤 / 10 攻速, 剑 49 伤 / 50 攻速.
- 这套数值接近"作弊级", 迁移时必须重平衡, 不要直接照搬.
- 盾牌在客户端注册了 `blocking` 物品属性 (`ItemProperties`), 模型使用 `empty_shield_0.json` 作为举起状态.

## 五、护甲设定

| 项 | 值 |
|---|---|
| 元素 | `empty_armor` (四个部位: `empty_armor_helmet` / `_chestplate` / `_leggings` / `_boots`) |
| 防御值 | 头盔 10, 胸甲 30, 护腿 20, 靴子 10 |
| 韧性 | 10 |
| 击退抗性 | 1.0 |
| 附魔能力 | 100 |
| 耐久基数 | 100 (按部位 ×13 / ×15 / ×16 / ×11) |
| 装备音效 | `item.armor.equip_chain` |
| 修复材料 | `#more_item:empty_items` |
| 材质 | `assets/more_item/textures/models/armor/empty_armor_layer_1.png`、`_layer_2.png` |
| 抗火 | 四个部位都是 |

防御总值 70、韧性 10、击退抗性满值, 同样是极端数值. 全套不含额外效果, 纯属性护甲.

## 六、方块设定

| 注册 id | 硬度 / 抗性 | 音效 | 亮度 | 工具 | 特殊行为 |
|---|---|---|---|---|---|
| `empty_ingot_block` | 10 / 10 | GLASS | 0 | 镐, 钻石级 | 玻璃质感 (`noOcclusion` + 空 `getVisualShape` + 连体剔除), 可含水, 被活塞破坏 (`DESTROY`), 生物寻路视为可通行 |
| `empty_ore_block` | 10 / 10 | GLASS | 0 | 镐, 钻石级 | 同上, 掉落 `empty_ore`; 参与世界生成 |
| `white_block` | 2 / 100 | METAL | 2 | 镐, 铁级 | 自发光渲染, 方块速度 ×2, 信标光柱白色, 推动时只被推 (`PUSH_ONLY`), 抗凋灵/末影龙 (标签) |
| `advanced_fuser` | 1 / 20 | METAL | 0 | 镐, 铁级 | 水平朝向 `FACING`, 6 格容器 + GUI, 光照遮挡 15, 被推动时阻止 (`BLOCK`) |

掉落表:

| 方块 | 掉落 |
|---|---|
| `empty_ingot_block` | 9 × `empty_ingot` (受爆炸衰减, 不爆出) |
| `empty_ore_block` | 1 × `empty_ore` (需在爆炸中存活) |
| `white_block` | 自身 |
| `advanced_fuser` | 自身 (容器内容物在破坏时掉落) |

变体方块状态: `empty_ingot_block` 与 `empty_ore_block` 都有 `WATERLOGGED` 布尔状态和 six-face 模型 (`_top` / `_side` / `_bottom` 三张贴图); `advanced_fuser` 只有 `FACING`.

## 七、世界生成

仅 `empty_ore_block` 参与生成:

| 项 | 值 |
|---|---|
| 特征类型 | `minecraft:ore` |
| 团簇大小 | 4 |
| 目标方块 | `minecraft:glass`、`#forge:stained_glass_panes`、`#forge:stained_glass`、`#minecraft:sand` |
| 放置数量 | 每区块 10 次尝试 |
| 高度范围 | 均匀分布, -64 ~ 256 |
| 生物群系 | 所有 (`minecraft:biome` 放置修饰) |

即"空矿"只在玻璃与沙子所在位置生成, 不会替换石头. 与 `docs/Main.md` 的"虚空侵蚀"设定方向一致, 但实现方式是直接替换方块.

## 八、配方体系

### 8.1 全部配方

未标命名空间的 id 属于 `more_item`.

| 配方文件 | 类型 | 输入 | 产出 |
|---|---|---|---|
| `advanced_fuser_recipe` | 有序 | `aba` / `cdc` / `aea`: a=铁块, b=`precision_core`, c=玻璃, d=高炉, e=钻石块 | 高级融合器 ×1 |
| `precision_core_recipe` | 有序 | ` a ` / `bcb` / ` a `: a=`#forge:plates/gold`, b=`#forge:plates/iron`, c=钻石 | 精密核心 ×1 |
| `empty_ore_recipe` | 有序 | `aaa` / `aba` / `aaa`: a=黑曜石, b=玻璃 | 空矿方块 ×1 |
| `empty_ore_to_block` | 有序 | `aa` / `aa`: a=空矿 | 空矿方块 ×1 |
| `empty_ore_to_ingot` | 有序 | `aaa` / `aba` / `ccc`: a=黑曜石, b=空矿, c=岩浆块 | 空锭 ×1 |
| `empty_ore_to_parts` | 有序 | `aaa` / `aba` / `ccc`: a=哭泣的黑曜石, b=空矿, c=岩浆桶 | 空组件 ×1 |
| `empty_parts_recipe` | 无序 | 空陶片 + 空紫水晶碎片 + 空球 + 空钻石 + 空粉 + 空绿宝石 + 空青金石 + 空石英 + 空锭 | 空组件 ×12 |
| `empty_ingot_recipe` | 有序 | ` a ` / `aba` / ` a `: a=空板, b=铁锭 | 空锭 ×4 |
| `empty_ingot_block_recipe` | 有序 | `aaa` / `aba` / `aaa`: a=空锭, b=空组件 | 空锭方块 ×1 |
| `empty_ingot_block_recipe_dismantle` | 无序 | 空锭方块 | 空锭 ×9 |
| `empty_ingot_to_nugget` | 无序 | 空锭 | 空粒 ×9 |
| `empty_nugget_to_ingot` | 有序 | `aaa` / `aaa` / `aaa`: a=空粒 | 空锭 ×1 |
| `empty_stick_recipe` | 有序 | `a` / `a`: a=空锭 | 空木棍 ×4 |
| `empty_plate_recipe` | 无序 | 空锤 + 空锭 | 空板 ×5 (锤子不消耗) |
| `empty_pottery_sherd_recipe` | 有序 | ` a ` / `aaa` / ` a `: a=空板 | 空陶片 ×5 |
| `empty_quartz_recipe` | 有序 | ` a ` / `aba` / ` a `: a=空板, b=石英 | 空石英 ×4 |
| `empty_lapis_lazuli_recipe` | 有序 | ` a ` / `aba` / ` a `: a=空板, b=青金石 | 空青金石 ×4 |
| `empty_diamond_recipe` | 无序 | 钻石 + 空石英 ×4 | 空钻石 ×4 |
| `empty_emerald_recipe` | 无序 | 绿宝石 + 空石英 ×4 | 空绿宝石 ×4 |
| `empty_amethyst_shard_recipe` | 无序 | 紫水晶碎片 + 空石英 ×4 | 空紫水晶碎片 ×4 |
| `empty_ball_recipe` | 有序 | `abc` / `bbb` / `dbe`: a=雪球, b=空板, c=岩浆膏, d=黏液球, e=火焰弹 | 空球 ×4 |
| `empty_dust_recipe` | 有序 | `abc` / `bbb` / `dbe`: a=火药, b=空板, c=红石, d=糖, e=荧石粉 | 空粉 ×4 |
| `empty_hammer_head_recipe` | 有序 | `abc` / `dbc`: a=空组件, b=空锭, c=空锭方块, d=空粒 | 空锤头 ×1 |
| `empty_hammer_recipe` | 有序 | `  a` / ` b ` / `b  `: a=空锤头, b=空木棍 | 空锤 ×1 |
| `empty_wrench_recipe` | 有序 | `  a` / ` b ` / `b  `: a=空锭方块, b=空木棍 | 空扳手 ×1 |
| `empty_pickaxe_recipe` | 有序 | `aba` / ` c ` / ` c `: a=空锭, b=空组件, c=空木棍 | 空镐 ×1 |
| `empty_axe_recipe` | 有序 | `ab` / `ac` / ` c`: a=空锭, b=空组件, c=空木棍 | 空斧 ×1 |
| `empty_shovel_recipe` | 有序 | `a` / `b` / `b`: a=空组件, b=空木棍 | 空铲 ×1 |
| `empty_hoe_recipe` | 有序 | `ab` / ` c` / ` c`: a=空锭, b=空组件, c=空木棍 | 空锄 ×1 |
| `empty_sword_recipe` | 有序 | `a` / `b` / `c`: a=空组件, b=空锭, c=空木棍 | 空剑 ×1 |
| `empty_helmet_recipe` | 有序 | `aba` / `a a`: a=空锭, b=空组件 | 空头盔 ×1 |
| `empty_chestplate_recipe` | 有序 | `a a` / `aaa` / `aba`: a=空锭, b=空组件 | 空胸甲 ×1 |
| `empty_leggings_recipe` | 有序 | `aba` / `a a` / `a a`: a=空锭, b=空组件 | 空护腿 ×1 |
| `empty_boots_recipe` | 有序 | `aa`: a=空单靴 | 空靴子 ×1 |
| `empty_boot_recipe` | 有序 | `a` / `b`: a=空锭, b=空组件 | 单只空靴 ×1 |
| `empty_shield_recipe` | 有序 | `aba` / `aaa` / ` a `: a=空锭, b=空组件 | 空盾 ×1 |
| `iron_plate_recipe` | 无序 | 空锤 + 铁锭 | 铁板 ×1 |
| `gold_plate_recipe` | 无序 | 空锤 + 金锭 | 金板 ×1 |
| `obsidian_breaker_recipe` | 有序 | `aba` / ` c ` / ` c `: a=黑锭, b=黑曜石, c=空木棍 | 黑曜石破坏者 ×1 |

### 8.2 设计规律

1. **转化公式**: 4 空板 / 4 空石英 + 1 个原版材料 → 4 个"空版本", 表达"掏空原版材料, 只留下外形".
2. **拆解与合成互逆**: 空锭 ↔ 空粒 (1:9), 空锭方块 ↔ 空锭 (1:9), 空矿方块 ↔ 空矿 (1:4, 与黑曜石+玻璃配方重复, 两套并存).
3. **工具不收损**: 空锤、空扳手作合成材料时返回自身; 空锤头返回空锭.
4. **空矿双路线**: 一种用黑曜石+玻璃直接合成空矿方块; 另一种是挖世界里的空矿, 分别加工成空锭 (岩浆块) 或空组件 (岩浆桶).
5. **黑白科技入口**: 精密核心 (金板+铁板+钻石) → 高级融合器 (铁块+玻璃+高炉+钻石块) → 黑曜石破坏者 (黑锭+黑曜石).

## 九、标签体系

| 标签 | 内容 |
|---|---|
| `more_item:empty_items` | 9 种空材料: 空锭, 空紫水晶碎片, 空球, 空钻石, 空粉, 空绿宝石, 空青金石, 空陶片, 空石英 |
| `more_item:empty_tools` | 6 工具 + 4 件护甲 |
| `more_item:empty_blocks` | 空锭方块, 空矿方块 |
| `more_item:empty_blocks_items` | 上述两方块的物品形式 |
| `more_item:empty_things` | 空木棍, 空板, `#empty_items`, `#empty_tools`, `#empty_blocks_items`, 空锤头, 空矿 |
| `forge:ingots` | 空锭, 黑锭, 白锭 |
| `forge:nuggets` | 空粒 |
| `forge:ores` | 空矿 |
| `forge:dusts` | 空粉 |
| `forge:rods` / `forge:sticks` | 空木棍 |
| `forge:plates` / `forge:sheets` | 铁板, 金板, 空板 |
| `forge:plates/iron` / `forge:plates/gold` | 对应板 |
| `forge:sheets/iron` / `forge:sheets/gold` | 对应板 |
| `forge:hammers` | 空锤 |
| `forge:wrenches` | 空锤, 空扳手 |
| `forge:tools` | `#forge:wrenches` |
| `forge:storage_blocks` | 空矿方块, 空锭方块 |
| `forge:wrench_pickup` | 空矿方块, 空锭方块 |
| `minecraft:pickaxes` | 空镐, 黑曜石破坏者 |
| `minecraft:axes` / `minecraft:shovels` / `minecraft:hoes` / `minecraft:swords` | 对应空工具 |
| `minecraft:mineable/pickaxe` | 4 个方块 |
| `minecraft:needs_iron_tool` | 高级融合器, 白块 |
| `minecraft:needs_diamond_tool` | 空锭方块, 空矿方块 |
| `minecraft:beacon_base_blocks` | `#empty_blocks`, 白块 |
| `minecraft:wither_immune` / `minecraft:dragon_immune` | `#empty_blocks` (白块也在 dragon_immune) |

可疑点: 空锤同时被放进 `forge:hammers` 与 `forge:wrenches`; `forge:tools` 只指向 `forge:wrenches`, 语义混乱. `forge:wrench_pickup` 用在空锭方块与空矿方块上, 含义也不明确.

## 十、进度树

```text
start_of_more_item (challenge, 进入主世界触发, 奖励解锁 3 个空矿配方)
├── get_empty_ore (goal)
│   ├── get_empty_ingot (goal)
│   └── get_empty_part (goal)
│       ├── get_empty_pickaxe / get_empty_axe / get_empty_hoe
│       ├── get_empty_shovel / get_empty_sword / get_empty_shield
│       └── get_empty_armors (challenge, 四件护甲分别一个 criteria)
├── get_empty_ore_block (goal)
└── get_obsidian_breaker (goal)
```

| 进度 | 触发条件 | frame |
|---|---|---|
| `start_of_more_item` | `changed_dimension` 到 `minecraft:overworld` | challenge |
| `get_empty_ore` | 获得空矿 (1~64) | goal |
| `get_empty_ingot` | 获得空锭 (1~64) | goal |
| `get_empty_part` | 获得空组件 (1~64) | goal |
| `get_empty_ore_block` | 获得空矿方块 (1~64) | goal |
| `get_empty_armors` | 分别获得 4 件护甲, 每件一个 criteria | challenge |
| `get_empty_pickaxe` 等 6 个 | 获得对应工具 (1) | goal |
| `get_obsidian_breaker` | 获得黑曜石破坏者 (1) | goal |

已知问题:

- `get_empty_shield` 的英文标题误写成 `§e§k______§r§eShovel` (中文正常).
- `get_empty_pickaxe` 的英文标题是 `Empty Pickaxe`, 与其余 `§k______` 风格不一致.
- 根节点图标为 `empty_wrench`, 背景为 `more_item:textures/screens/more_item_ground.png`.

## 十一、高级融合器 GUI

### 11.1 尺寸与槽位

- 背景贴图: `assets/more_item/textures/screens/advanced_fuser_gui.png`, 180 × 166.
- 6 个槽位 (菜单实际坐标, 相对背景左上):

| 槽位 | 坐标 (x, y) | 约束 |
|---|---|---|
| 0 | 10, 19 | 只允许 `black_ingot` |
| 1 | 10, 63 | 只允许 `white_ingot` |
| 2 | 46, 41 | 任意物品 |
| 3 | 80, 32 | 任意物品 |
| 4 | 98, 50 | 任意物品 |
| 5 | 154, 41 | 输出, 禁止放入 |

- 玩家背包: 3 × 9 (y=84 起), 快捷栏 y=142.
- GUI 叠加贴图 (`AdvancedFuserGuiScreen#renderBg`):

| 贴图 | 位置 | 尺寸 | 说明 |
|---|---|---|---|
| `advanced_fuser_gui_step1.png` | 8, 33 | 64 × 32 | 黑白锭 → 融合的示意图 |
| `advanced_fuser_gui_step2.png` | 60, 20 | 72 × 72 | 中部装饰 |
| `advanced_fuser_gui_step3.png` | 119, 41 | 32 × 16 | 输出箭头 |
| `black_ingot_slot.png` | 10, 19 | 16 × 16 | 黑锭槽图标 |
| `white_ingot_slot.png` | 10, 63 | 16 × 16 | 白锭槽图标 |
| `advanced_fuser_gui_step4.png` | 未使用 | 32 × 16 | 进度条底图, 代码从未绘制 |

### 11.2 容器行为

- `AdvancedFuserBlockEntity` 继承 `RandomizableContainerBlockEntity`, 6 格, 支持从任意面接入 `IItemHandler`.
- 自动化可插入槽 0~4, 只能从槽 5 抽出; **但自动化不检查黑白锭限制**, 该限制只写在菜单的 `mayPlace` 里.
- 破坏方块时调用 `Containers.dropContents` 掉落全部内容.
- 菜单关闭时把内容物塞回玩家背包.

### 11.3 缺失的逻辑

融合器**没有任何处理逻辑**: 没有 `blockTick`, 也没有配方匹配、进度同步、状态方块. 当前等价于"带主题 GUI 的 6 格箱子". 进度条贴图 `step4` 从未使用, 说明原计划有 4 阶段进度展示.

## 十二、文本设定

来源: `assets/more_item/lang/en_us.json` 与 `zh_cn.json`. 以 `en_us` 为准, 中文为对照.

| 键 | en_us | zh_cn |
|---|---|---|
| `item.more_item.empty_ingot` | `§eIngot made of §k______` | `§e§k______§r§e锭` |
| `item.more_item.empty_nugget` | `§eNugget made of §k______` | `§e§k______§r§e粒` |
| `item.more_item.empty_stick` | `§eStick/Rod made of §k______` | `§e§k______§r§e棍` |
| `item.more_item.empty_plate` | `§ePlate/Sheet made of §k______` | `§e§k______§r§e板` |
| `item.more_item.empty_parts` | `§eParts made of §k______` | `§e§k______§r§e组件` |
| `item.more_item.empty_ball` | `§eBall made of §k______` | `§e§k______§r§e球` |
| `item.more_item.empty_amethyst_shard` | `§eAmethyst Shard made of §k______` | `§e紫§k______§r§e水晶碎片` |
| `item.more_item.empty_diamond` | `§eDiamond made of §k______` | `§e§k______§r§e钻石` |
| `item.more_item.empty_dust` | `§eDust made of §k______` | `§e§k______§r§e粉` |
| `item.more_item.empty_emerald` | `§eEmerald made of §k______` | `§e绿§k______§r§e宝石` |
| `item.more_item.empty_lapis_lazuli` | `§eLapis Lazuli made of §k______` | `§e青§k______§r§e金石` |
| `item.more_item.empty_pottery_sherd` | `§ePottery Sherd made of §k______` | `§e§k______§r§e陶片` |
| `item.more_item.empty_quartz` | `§eQuartz made of §k______` | `§e§k______§r§e石英` |
| `item.more_item.empty_ore` | `§e§k______§r§eOre` | `§e§k______§r§e矿` |
| `item.more_item.empty_pickaxe` | `§ePickaxe made of §k______` | `§e§k______§r§e镐` |
| `item.more_item.empty_axe` | `§eAxe made of §k______` | `§e§k______§r§e斧` |
| `item.more_item.empty_shovel` | `§eShovel/Spade made of §k______` | `§e§k______§r§e铲` |
| `item.more_item.empty_hoe` | `§eHoe made of §k______` | `§e§k______§r§e锄` |
| `item.more_item.empty_sword` | `§eSword made of §k______` | `§e§k______§r§e剑` |
| `item.more_item.empty_shield` | `§eShield made of §r______` | `§e§k______§r§e盾牌` |
| `item.more_item.empty_armor_helmet` | `§eHelmet made of §k______` | `§e§k______§r§e头盔` |
| `item.more_item.empty_armor_chestplate` | `§eChestplate made of §k______` | `§e§k______§r§e胸甲` |
| `item.more_item.empty_armor_leggings` | `§eLegging made of §k______` | `§e§k______§r§e护腿` |
| `item.more_item.empty_armor_boots` | `§eBoots made of §k______` | `§e§k______§r§e靴子` |
| `item.more_item.empty_hammer` | `§e§k______§r§eHammer` | `§e§k______§r§e锤` |
| `item.more_item.empty_hammer_head` | `§e§k______§r§eHammer Head` | `§e§k______§r§e锤头` |
| `item.more_item.empty_wrench` | `§e§k______§r§eWrench` | `§e§k______§r§e扳手` |
| `item.more_item.empty_boot` | `§eSingle Boot made of §k______` | `§e单只§k______§r§e靴子` |
| `item.more_item.iron_plate` | `Iron Plate` | `铁板` |
| `item.more_item.gold_plate` | `Gold Plate` | `金板` |
| `item.more_item.black_ingot` | `Black Ingot(WIP!)` | `黑色锭(WIP!)` |
| `item.more_item.white_ingot` | `White Ingot(WIP!)` | `白色锭(WIP!)` |
| `item.more_item.precision_core` | `Precision Core` | `精密核心` |
| `item.more_item.obsidian_breaker` | `§8Obsidian Breaker` | `§8黑曜石破坏者` |
| `item.more_item.obsidian_breaker.description_0` | `§2Can only break Obsidian, Crying Obsidian, Respawn Anchor` | `§2只能破坏黑曜石、哭泣的黑曜石、重生锚` |
| `item.more_item.obsidian_breaker.description_1` | `§4Note: Obsidian will not drop` | `§4注意：黑曜石不会掉落` |
| `block.more_item.empty_ingot_block` | `§e§k______§r§eIngot Block` | `§e§k______§r§e锭方块` |
| `block.more_item.empty_ore_block` | `§e§k______§r§eOre Block` | `§e§k______§r§e矿方块` |
| `block.more_item.white_block` | `White Block(WIP!)` | `白色块(WIP!)` |
| `block.more_item.advanced_fuser` | `Advanced Fuser(WIP!)` | `高级融合器(WIP!)` |
| `gui.more_item.advanced_fuser_gui.label_advanced_fusertitle` | `Advanced Fuser(WIP!)` | `高级融合机(WIP!)` |
| `item_group.more_item.more_item` | `More Items` | `更多物品` |
| `advancements.start_of_more_item.title` | `Xiaofan's More Item` | `Xiaofan的更多物品` |
| `advancements.start_of_more_item.descr` | `The start of Xiaofan's More Item mod.` | `Xiaofan的更多物品模组的开头` |
| `advancements.get_empty_ore.title` | `§e§k______§r§eOre...Right?` | `§e§k______§r§e矿，对吧?` |
| `advancements.get_empty_ore.descr` | `§eEmm...Isn't it Raw §k______§r§e?` | `嗯...这是粗§e§k______§r§e吗?` |
| `advancements.get_empty_ore_block.title` | `§e§k______§r§eBlock?!` | `§e§k______§r§e块?!` |
| `advancements.get_empty_ore_block.descr` | `Like glasses.` | `像玻璃` |
| `advancements.get_empty_ingot.title` | `§e§k______§r§eIngot!` | `§e§k______§r§e锭!` |
| `advancements.get_empty_ingot.descr` | `null + null = ?` | `null + null = ?` |
| `advancements.get_empty_part.title` | `§e§k______§r§ePart for crafting.` | `§e§k______§r§e组件!` |
| `advancements.get_empty_part.descr` | `Part A` | `Part A` |
| `advancements.get_empty_armors.title` | `§e§k______§r§eArmors!` | `§e§k______§r§e制成的盔甲!` |
| `advancements.get_empty_armors.descr` | `Air armors?` | `空气盔甲?` |
| `advancements.get_empty_pickaxe.title` | `Empty Pickaxe` | `§e§k______§r§e镐` |
| `advancements.get_empty_pickaxe.descr` | `Break stones by the power of air!` | `用空气之力挖掉石头!` |
| `advancements.get_empty_axe.title` | `§e§k______§r§eAxe` | `§e§k______§r§e斧` |
| `advancements.get_empty_axe.descr` | `Break woods by the power of air!` | `用空气之力砍伐木头!` |
| `advancements.get_empty_hoe.title` | `§e§k______§r§eHoe` | `§e§k______§r§e锄` |
| `advancements.get_empty_hoe.descr` | `Break wheats by the power of air!` | `用空气之力挖掉小麦!` |
| `advancements.get_empty_shovel.title` | `§e§k______§r§eShovel` | `§e§k______§r§e铲` |
| `advancements.get_empty_shovel.descr` | `Break dirts by the power of air!` | `用空气之力挖掉泥土!` |
| `advancements.get_empty_sword.title` | `§e§k______§r§eSword` | `§e§k______§r§e剑` |
| `advancements.get_empty_sword.descr` | `Break...Attack by the power of air!` | `用空气之力挖...攻击!` |
| `advancements.get_empty_shield.title` | `§e§k______§r§eShovel` (原文如此) | `§e§k______§r§e盾` |
| `advancements.get_empty_shield.descr` | `Break...Defend by the power of air!` | `用空气之力挖...防御!` |
| `advancements.get_obsidian_breaker.title` | `Obsidian Breaker!` | `黑曜石破坏者` |
| `advancements.get_obsidian_breaker.descr` | `So Fast!But where are my obsidians?` | `太快了!可是我黑曜石呢?` |

注意:

- 大多数中文条目以 `§r` 收尾, 但 `empty_shield` 的英文写成 `§eShield made of §r______` (缺少 `§k`), 属于笔误.
- 文本中半角 `?` `!` 与全角 `，` 混用, 迁移到 Datagen 时需统一.

## 十三、移植取舍建议

| 设定 | 建议 | 理由 |
|---|---|---|
| `§k______` 乱码命名 | 保留 | 系列身份标识, 与主线"不可名状"一致 |
| 全部空系列抗火 | 保留 | 统一的"不属于此世"属性 |
| 工具/护甲极端数值 | 重平衡 | 当前数值接近作弊级, 不适合正式流程 |
| `(WIP!)` 文本 | 移除 | 玩法完成后不再需要 |
| 8 种空XX材料 | 可合并 | `void_things` 已用 `empty_shard` 收敛, 需要时再按配方区分 |
| 空矿替换玻璃/沙的世界生成 | 改设定 | 建议与 `void_things:water` 侵蚀或主线遗迹绑定, 不要直接替换玻璃/沙 |
| 空锭方块掉 9 锭 | 保留 | 方块 ↔ 材料的拆解关系清晰 |
| 空矿方块双配方 (黑曜石+玻璃 / 4 空矿) | 保留其一 | 避免同一产出两条路线 |
| 空锤/空扳手不消耗 | 保留, 但需明确规则 | 合成不消耗工具是空系列的核心手感 |
| `empty_ore` 耐久当消耗品 | 重做 | 与 1.20.1 配方剩余物机制耦合, 建议改为普通材料或带耐久的工具材料 |
| `forge:hammers` / `forge:wrenches` 混放 | 修正 | 锤子不应出现在 `forge:wrenches`; `forge:tools` 应指向正确的工具标签 |
| 进度树 | 复用框架, 重写文本 | 进度结构 (矿 → 锭/组件 → 工具/护甲) 可沿用 |
| 高级融合器 GUI | 复用布局, 重写逻辑 | 槽位约束与贴图可参考, 处理逻辑需要从零实现 |
| 空系列护甲 | 保留属性方向, 下调数值 | 高防御 + 高韧性 + 满击退抗性是"空"的质感 |
| 修复标签 `empty_items` | 保留 | 统一修复材料的做法值得延续 |
