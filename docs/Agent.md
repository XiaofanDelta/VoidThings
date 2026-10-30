# Agent 阅读指引

本文件面向新对话中的 AI / Agent, 说明开始工作前应阅读的文档、项目约束, 以及调试工具的存放位置.

## 阅读顺序

1. [CONTRIBUTING.md](../CONTRIBUTING.md): 强制规范. 代码格式、命名、注册架构、语言文本、Git 提交与更新日志要求均以此为准.
2. [docs/Main.md](./Main.md): 世界观、主线流程、核心机制与当前开发进度. 这里只记录已完成进度.
3. [docs/TODO.md](./TODO.md): 开发待办与已知问题. 开始新任务前先确认是否已有相关条目.
4. [UpdateLogs.md](../UpdateLogs.md): 版本历史. 每次功能或文档改动后按规范追加中英双语记录.
5. [README.md](../README.md)、[build.gradle](../build.gradle)、[gradle.properties](../gradle.properties): 构建环境、依赖与版本号来源.

## 项目速览

- Minecraft 1.20.1 / Forge 47.4.10 / Java 17.
- mod id: `void_things`; 包名: `nothing.xiaofan_delta.void_things`.
- 注册统一使用 `Registrate` + `Nebula Libs`, 禁止 `DeferredRegister`.
- 面向玩家的语言文本必须走 Datagen, 禁止在 Java 中硬编码; 现有待修复项见 [docs/TODO.md](./TODO.md).
- 版本号唯一来源是 [gradle.properties](../gradle.properties) 的 `mod_version`.

## 开始工作前

1. 先读 [CONTRIBUTING.md](../CONTRIBUTING.md), 确认本次改动涉及的格式、命名与提交规范.
2. 查看 [docs/TODO.md](./TODO.md), 优先处理其中已有条目; 不要重复创建相同任务.
3. 检查工作区已有的未提交修改, 不要覆盖或回滚他人的改动.
4. 修改语言文本后运行 `gradlew runData`, 并检查 `src/generated/resources/assets/void_things/lang/` 下的输出.
5. 完成后更新 [UpdateLogs.md](../UpdateLogs.md), 并在 [docs/TODO.md](./TODO.md) 中勾选对应条目.

## 调试工具目录

仓库根目录下的 `.dsh-tools/` 是 Agent 的临时工作目录, 已加入 [.gitignore](../.gitignore), 不会进入版本库:

- 可以放入: 调试脚本 (`.ps1`、`.py`、`.sh`)、运行日志、Blockbench 模型源文件 (`.bbmodel`)、临时导出文件.
- 不要放入: 生产代码、正式资源、需要提交的文档或构建产物.
- 现有文件: `gradle-sync-fix.ps1`、`sync.log`、`textures.bbmodel`.
- 该目录不参与 Gradle 构建; 清理时确认脚本没有被其他流程引用.

## 常用命令

```bash
./gradlew build      # 构建
./gradlew runData    # 生成数据 (lang / model / loot 等)
./gradlew runClient  # 启动客户端
./gradlew runServer  # 启动服务端
```

构建产物位于 `build/`, 运行目录为 `run/` 与 `run-data/`, 均已被 Git 忽略.
