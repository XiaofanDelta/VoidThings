# Void Things 代码规范

## 第一章 总则
**第一条** 本规范适用于 `Void Things`(`VT`) 项目的开发人员及协作者.

**第二条** 项目主理人 (Maintainer) 拥有版本迭代的最终决策权和规范解释权.

**第三条** 本规范基于 CMI、CMC 项目的开源协作协议制定, 结合 VT 项目实际开发习惯做出调整.

---

## 第二章 命名规范

**第四条** 命名规则：
1. 类名采用大驼峰式 (**PascalCase**), 例: `VoidBedrockBlock`, `DarknessEchoEffect`.

2. 方法 / 变量采用小驼峰式 (**camelCase**), 例: `spawnDripParticles`, `addRaw`.

3. 常量 (`static final`) 采用全大写蛇形命名 (**SNAKE_CASE**), 例：`MAX_STAGE`、`PI`.

4. **严格禁止** 使用 `var` 声明变量与常量.

---

## 第三章 注册与架构规范

**第五条** 注册时须按模块分类建立独立的注册类 (如 `VTBlocks`、`VTItems`、`VTFluids`).

**第六条** `Nebula Libs`提供了许多轮子, 不要重复造轮子.

---

## 第四章 代码格式规范

**第七条** 调用链式方法时 **强烈建议** 换行, 以提高可读性:
```java
public static final BlockEntry<Block> BLACK_BLOCK = REGISTRATE.block("black_block", Block::new)
        .initialProperties(() -> BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BLACK)
                .strength(5.0F))
        .item()
        .model(ItemModelGen.generated("block/material/black_block"))
        .build()
        .register();
```

**第八条** 所有 `if` / `for` / `while` 语句必须使用大括号 `{}`, 即使只有一行代码, **禁止** 单行省略大括号:
``` java
if (a == b) {
    return // √
}

if (a == b) return // ×
```


**第九条** 箭头函数 `->` 前后保持一个空格, 前面传参时需要加括号, 且 Lambda 体如果不是单行表达式须换行并加大括号.
```java
a -> a.xxx() // ×

(a) -> a.xxx() // √
```

**第十条** 除`char`(`Charater`)类型值外, 其他地方需要使用双引号`""`.

---

## 第五章 注释规范

**第十一条** 主要方法, 复杂的工具类等需要有 Javadoc 注释 (`/** ... */`), 说明该方法的作用、参数含义及返回值.

**第十二条** 在写复杂的逻辑时, 须分步写注释:
```java
// 1. xxx
// 2. yyy
// 3. zzz
```

---

## 第六章 语言文件与叙事规范

**第十三条** **严禁** 在 Java 代码中直接 **硬编码** lang, 需要给自定义空间.

**第十四条** 叙事文案(如物品描述、遗迹日志、进度提示)建议遵循**短、留白、叙事化**原则.

---

## 第七章 更新日志规范

**第十五条** 每次完成一个`feature`或修复重要 Bug, 需在 `UpdateLogs.md` 中记录中英双语更新日志:
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