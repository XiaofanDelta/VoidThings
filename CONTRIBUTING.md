# Void Things 代码规范

## 第一章 总则

**第一条** 本规范适用于 `Void Things` (`VT`) 项目的所有开发人员与协作者.

**第二条** 项目主理人 (Maintainer) 拥有版本迭代的最终决策权和规范解释权.

**第三条** 本规范基于 CMI、CMC 项目的开源协作协议制定, 结合 VT 项目 (Minecraft 1.20.1 / Forge 47.4.10 / Java 17) 的实际开发习惯做出调整.

---

## 第二章 版本管理

**第四条** `VT` 版本号由大、中、小版本号组成, 格式为 `<major>.<minor>.<patch>`.

**第五条** `mod_version` 是版本号的唯一来源, 必须在 `gradle.properties` 中声明; 其他文件不得重复硬编码版本号.

**第六条** `build.gradle` 的 `version` 直接读取 `mod_version`, 更新版本时只需修改 `gradle.properties` 一处.

**第七条** 热修复版本在版本号后追加 `-hf`, 例: `1.0.1-hf`.

**第八条** 最新版本的版本号必须与 `UpdateLogs.md` 中的版本标题保持一致; 发布前必须同步更新更新日志.

**第九条** 发布须使用 Git Tag 标记节点 (见第九章), **严禁** 将编译或运行产物 (`build/`、`run/`、`run-data/`、`src/generated/`) 提交进仓库.

---

## 第三章 命名规范

**第十条** 命名规则:

1. 类名采用大驼峰式 (**PascalCase**), 例: `VoidThings`、`VoidBedrockBlock`、`DarknessEchoEffect`.
2. 方法 / 变量采用小驼峰式 (**camelCase**), 例: `spawnDripParticles`、`loadResource`.
3. 常量 (`static final`) 采用全大写蛇形命名 (**SNAKE_CASE**), 例: `MODID`、`REGISTRATE`、`EMPTY_INGOT_BLOCK`.
4. 包名全部小写, 用 `.` 分隔, 例: `nothing.xiaofan_delta.void_things.common.register`.
5. **严格禁止** 使用 `var` 声明变量与常量, 必须写明具体类型.
6. 注册类统一使用 `VT` 前缀, 例: `VTBlocks`、`VTItems`、`VTFluids`、`VTEffects`.
7. 注册 id 使用蛇形命名 (**snake_case**), 例: `empty_ingot_block`、`darkness_echo`, 并统一使用 `void_things:` 命名空间.

**第十一条** `final` 只用于声明不应被重新赋值的引用; 会在逻辑中重新赋值的局部变量不得加 `final`.

**第十二条** 全局工具方法与常量统一挂载在 `VoidThings` 下, 例: `VoidThings.MODID`、`VoidThings.REGISTRATE`、`VoidThings.loadResource`.

**第十三条** 类名后缀遵循职责约定:

- 方块 / 物品 / 效果: `*Block`、`*Item`、`*Effect`
- 事件监听: `*Events`、`*Handler`
- 数据生成: `*Provider`、`*Language`、`*Model`
- 注册入口: `VT*`

---

## 第四章 代码格式规范

**第十四条** Java 源文件使用 **Tab** 缩进, 一个 Tab 显示宽度为 **4 个字符**; 禁止用空格代替 Tab.

**第十五条** 续行统一缩进 **2 个 Tab**; 换行后的右括号与语句起始位置对齐:

```java
public static SmithingTemplateItem createSimple(...) {
	return new SmithingTemplateItem(
			appliesTo,
			ingredients,
			title
	);
}
```

**第十六条** 所有 `if` / `else` / `for` / `while` / `do` / `try` 语句必须使用大括号 `{}`, 即使只有一行代码, **禁止** 单行省略大括号或单行 `return`:

```java
if (a == b) {
	return; // √
}

if (a == b) return; // ×
```

**第十七条** 鼓励使用卫语句提前返回, 减少嵌套层级:

```java
if (player == null) {
	return;
}

if (level.isClientSide()) {
	return;
}
```

**第十八条** 连续两级以上的链式调用 **必须** 换行, 每级独占一行; 单个 getter 调用 (如 `.get()`) 可保持单行:

```java
static {
	BLACK_BLOCK = VoidThings.REGISTRATE.block("black_block", Block::new)
			.properties((properties) -> properties
					.mapColor(MapColor.COLOR_BLACK)
					.strength(3.0F, 300.0F))
			.item()
			.tab(VTCreativeTabs.getTabKey())
			.build()
			.register();
}
```

**第十九条** Lambda 表达式规范:

1. 参数必须加小括号 `()`, 即使只有一个参数.
2. `->` 两侧各保留一个空格.
3. 单表达式可省略大括号; Lambda 体包含多条语句时必须换行并加大括号.

```java
(properties) -> properties.stacksTo(64) // √

(entity, amplifier) -> {
	if (entity.level().isClientSide()) {
		return; // √
	}
}

p -> properties.stacksTo(4) // ×
```

**第二十条** 除 `char` 类型外, 一律使用双引号 `""`; 禁止用单引号表示字符串.

**第二十一条** 十六进制数必须以 `0x` 开头, 字母使用大写, 颜色值写满 6 位, 例: `0x2B103D`.

**第二十二条** 方法 / 构造器参数需要换行时, 必须每个参数独占一行; 要么全部写在一行:

```java
public static void example(int a, int b, int c) {
}

public static void example(
		int a,
		int b,
		int c
) {
}
```

**第二十三条** 数组 / 集合 / 可变参数初始化需要换行时, 每个元素独占一行; 仅含两三个简单元素的短数组允许单行.

```java
List<ResourceLocation> icons = List.of(
		baseSlotIcon,
		additionSlotIcon,
		resultSlotIcon
);
```

**第二十四条** 二元运算符、赋值号、`->` 两侧各保留一个空格; 逗号、分号后保留一个空格.

**第二十五条** 代码与注释中尽量少使用全角符号; 面向玩家的文案 (lang、叙事文本) 例外.

---

## 第五章 注册与架构规范

**第二十六条** 注册类按模块划分, 每个模块一个注册类 (`VTBlocks`、`VTItems`、`VTFluids`、`VTEffects`、`VTMusicDisc`、`VTBlockEntities`、`VTCreativeTabs`), 并在 `VoidThings` 构造器中显式调用其 `register()`.

**第二十七条** 注册统一使用 `Registrate` + `Nebula Libs`, 不得再使用 `DeferredRegister`.

**第二十八条** 注册类结构固定:

1. 声明为 `public final class`, 并提供私有构造器禁止实例化.
2. 所有注册字段集中在类顶部声明, 统一在 `static {}` 块中赋值.
3. 提供 `public static void register()` 方法, 内部调用 `VoidThings.registerLog(...)` 输出注册日志.

```java
public final class VTBlocks {
	public static final BlockEntry<Block> BLACK_BLOCK;

	static {
		BLACK_BLOCK = VoidThings.REGISTRATE.block("black_block", Block::new)
				.properties((properties) -> properties
						.mapColor(MapColor.COLOR_BLACK)
						.strength(3.0F, 300.0F))
				.item()
				.tab(VTCreativeTabs.getTabKey())
				.build()
				.register();
	}

	private VTBlocks() {
	}

	public static void register() {
		VoidThings.registerLog("方块");
	}
}
```

**第二十九条** 资源路径必须通过 `VoidThings.loadResource` / `VoidThings.loadGui` 获取, 禁止手写 `"void_things:path"` 字符串或者`new ResourceLocation("void_things:path")`; 若写别的模组的资源路径, 使用`ResourceLocation.fromNamespaceAndPath("modid", "path")`或`ResourceLocation.tryParse("modid:path")`

**第三十条** `Nebula Libs` 已提供的轮子 (`BasicItem`、`BasicBlock`、`BasicBlockEntity`、`BasicRecordItem` 等) 必须优先复用, 不要重复造轮子.

**第三十一条** 客户端专用代码放在 `client` 包下, 并通过 `@Mod.EventBusSubscriber(value = Dist.CLIENT)` 限制加载; 服务端逻辑不得依赖客户端类.

**第三十二条** 事件监听优先使用 `@Mod.EventBusSubscriber`; 需要实例状态时可在主类中显式注册到对应事件总线.

**第三十三条** 事件总线不得混用:

- 加载 / 注册类事件使用 `Bus.MOD`
- 游戏内事件使用 `Bus.FORGE`

---

## 第六章 注释规范

**第三十四条** 主要类 (`VoidThings`、数据生成器、工具类) 与公开方法必须有 Javadoc 注释, 说明作用、参数与返回值.

**第三十五条** 复杂逻辑须分步注释, 按执行顺序标注步骤:

```java
// 1. 校验玩家与目标方块
// 2. 扣除交互物品
// 3. 触发方块转换
```

**第三十六条** 同一文件包含多类逻辑时, 须用注释分节.

**第三十七条** 注释使用中文, 专有名词 (类名、注册 id、Mod 名) 保留英文; 禁止无意义的注释 (内容与代码重复且不提供额外信息).

**第三十八条** 临时待办使用 `// TODO: <具体内容>` 标记, 必须写明待解决的问题, 不得留空.

---

## 第七章 语言文件与叙事规范

**第三十九条** **严禁** 在 Java 代码或注册链中硬编码面向玩家的语言文本, 包括但不限于:

- 使用 `.lang("Music Disc")` 直接写死英文名
- 使用 `Component.literal("...")` 承载面向玩家的字面量文本

若检查有例外或待修复清单写在 [docs/TODO.md](./docs/TODO.md) 内, 之后统一修改.

**第四十条** 语言文本统一由 datagen 生成: 在 `datagen/language/type` 下通过 `LanguageGenerate.addItem` / `addBlock` / `addEffect` / `addRaw` 声明, 由 `VTChineseLanguageProvider` 输出 `zh_cn.json`, 由 Registrate 输出 `en_us.json`.

**第四十一条** datagen 生成的 `.json` 会在构建时一并打入模组 jar 文件, 因此 **不属于硬编码**; 判断标准是文本是否来自源码中的字符串字面量, 而不是文件是否位于 jar 内.

**第四十二条** `.defaultLang()` 由注册 id 自动派生, 不属于硬编码, 仅可作为兜底; 正式文本仍必须在 datagen 中声明.

**第四十三条** 面向玩家的文本必须通过 `Component.translatable("<key>")` 引用, 翻译键遵循以下格式:

- 物品: `item.void_things.<id>`
- 方块: `block.void_things.<id>`
- 效果: `effect.void_things.<id>`
- 其他 (标签页、JEI、流体等): `itemGroup.void_things.*`、`jei.void_things.*`、`fluid_type.void_things.*`

**第四十四条** 叙事文案 (物品描述、进度提示、遗迹日志等) 建议遵循 **短、留白、叙事化** 原则: 不写大段说明文, 用场景代替指令, 用画面代替数据. 世界观与主线流程参见 [docs/Main.md](./docs/Main.md).

**第四十五条** 文本颜色与语义对应, 使用 `§` 格式化代码, 并以 `§r` 结束 (防止某处应用此文本时错误应用颜色):

| 颜色 | 语义 |
|---|---|
| `§a` 绿 | 正面、建议 |
| `§c` 红 | 警告、危险 |
| `§e` 黄 | 强调数值 |
| `§7` 灰 | 提示、参考 |
| `§k` 混淆 | 错误 |

**第四十六条** 面向玩家的文案可以使用全角符号与中文标点; 代码与注释仍遵循第二十五条.

---

## 第八章 开发模式 (Dev)

**第四十七条** 所有注册日志必须通过 `VoidThings.registerLog(String)` 统一输出, 禁止在注册类中直接调用 `LOGGER.info` 打印注册信息.

**第四十八条** 调试输出使用 `LOGGER.debug`, 禁止使用 `LOGGER.info` 打印仅供开发排查的临时信息.

**第四十九条** 建议在 `lib` 包下集中维护开发期开关, 至少包含注册日志与通用调试两项; 开关按本地开发需要开启, 不得提交开启状态.

**第五十条** 正式发布前必须确认所有开发期开关关闭, 且代码中无残留临时调试输出.

---

## 第九章 Git 提交规范

**第五十一条** 提交信息遵循 Conventional Commits 规范, 格式为:

`<类型>(<可选作用域>): <简短描述>`

示例: `feat(effect): 添加暗影残响白天清除逻辑`

**第五十二条** 提交类型从以下列表中选择:

- `feat`: 新功能. 如新增物品、方块、效果、机制.
- `fix`: 修复 Bug. 如崩溃、贴图丢失、配方冲突.
- `docs`: 文档修改. 仅限 `.md` 说明文件.
- `refactor`: 重构. 既不是新功能也不是修 Bug, 仅优化代码结构.
- `perf`: 性能优化. 如减少每 tick 计算、优化数据加载.
- `style`: 代码格式调整. 不影响逻辑, 如缩进、空格.
- `chore`: 杂项. 如修改 `.gitignore`、`build.gradle` 或拉取配置.
- `build`: 构建系统或依赖修改. 如升级 Forge 版本、引入前置模组.
- `test`: 测试相关.

**第五十三条** 作用域 (Scope) 为选填项, 推荐填写以精确定位修改位置:

- 模块层级: `(item)`、`(block)`、`(effect)`、`(fluid)`、`(recipe)`、`(jei)`、`(register)`
- 资源层级: `(texture)`、`(model)`、`(lang)`、`(datagen)`
- 配置与构建: `(config)`、`(gradle)`
- 文档: `(docs)`

**第五十四条** 简短描述 (Description) 的书写原则:

1. 使用祈使句 / 动词开头, 写"添加"、"修复", 不写"我添加了"、"修复了".
2. 结尾不加句号.
3. 尽量控制在 50 个字符以内.
4. **严格禁止** 情绪化或无意义的描述 (如 `emm`、`差不多做好了`、`先提交`).

**第五十五条** 一次提交只做一件事; 功能、重构、格式化不得混在同一次提交中.

**第五十六条** 发布与 Tag 规范:

1. 发布新版本时, 使用 Git Tag 标记发布节点, 并在代码托管平台的 Releases 页面发布构建产物.
2. **严禁** 将 `build/`、`run/`、`run-data/`、`src/generated/` 等编译或运行产物提交进仓库.

```bash
git tag -a v1.0.0 -m "Release v1.0.0: 加入暗影残响"
git push origin v1.0.0
```

---

## 第十章 更新日志规范

**第五十七条** 每次完成一个 `feature` 或修复重要 Bug, 必须在 `UpdateLogs.md` 中记录, 并采用中英双语.

**第五十八条** 更新日志按版本倒序排列; 最新版本的标题必须与 `gradle.properties` 的 `mod_version` 一致, 历史版本标题保留不改.

**第五十九条** 条目格式:

- 每条以 ` - ` 开头 (短横线前后各一个空格).
- 条目以动词开头, 描述玩家可感知的变化.
- 英文更新日志和中文更新日志要同步更新.

```markdown
# Void Things 模组更新日志
## 1.0.0
 - 新增 `void_things:water` 流体、液块与水桶
 - 新增 `暗影残响` 效果
 - 新增 `void_things:egg` 落入原版水源的污染交互
 - 迁移数据生成, 统一生成方块状态/模型/战利品表/语言文件

# Void Things Mod Update Logs
## 1.0.0
 - Added `void_things:water` fluid, liquid block and bucket
 - Added `Darkness Echo` effect
 - Added `void_things:egg` contamination when dropped into vanilla water
 - Migrated data generation for blockstates/models/loot/lang
```

**第六十条** 纯文档修改也须在更新日志中记录.

---

## 第十一章 代码管理与知识产权

**第六十一条** 知识产权条款:

1. 贡献者保留其提交代码的著作权.
2. 项目核心团队拥有代码架构的最终优化权.
3. 贡献的代码与资源默认按项目许可证 (`GNU LGPLv3`) 授权.
4. 禁止提交来源不明或未获授权的代码、贴图、音频等资源; 引用第三方资源必须注明来源与许可证.

**第六十二条** 规范解释权归主理人所有; 未尽事宜按 CMC 项目协作协议执行.
