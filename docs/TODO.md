# TODO

本文件记录 `Void Things` 的开发待办与已知问题. 完成一项后勾选, 并在 `UpdateLogs.md` 中按规范记录中英双语日志.

## P0 黑白化方块 (下一项)

- [ ] 注册 `unconverted_black_ingot_block` / `unconverted_white_ingot_block`, 以及最终方块 `black_ingot_block` / `white_ingot_block`.
- [ ] 实现 `stage` 状态机: `0 -> 1 -> 2 -> 最终方块`; 0-2 使用已就绪的黑化/白化纹理.
- [ ] 滴水石锥判定: `THICKNESS=TIP`、`TIP_DIRECTION=DOWN`, 上方必须是 `void_things:water` 水源.
- [ ] 使用方块 scheduled tick 推进阶段; `onPlace` / `neighborChanged` / `randomTick` 只负责启动.
- [ ] 黑化来源为金块, 白化来源为铁块, 来源方块可配置.
- [ ] 补齐黑/白锭物品、方块物品、掉落表、配方、模型、中英文 lang.
- [ ] 转化音效、粒子、配置项 (`stage` 间隔、最大检测距离等).
- [ ] 验证: 单格污染、区块重载继续、条件消失后停止、第 3 阶段只替换目标方块.

## P1 DP 系统 (按决定放到最后, 暂不开始)

- [ ] `VoidPlayerData` Capability + Provider + `RegisterCapabilitiesEvent`.
- [ ] `PlayerEvent.Clone` 的 `reviveCaps` / `invalidateCaps` 数据复制.
- [ ] `SyncDPPacket` + `SimpleChannel` 服务端到客户端同步.
- [ ] `/void_things dp set <value>` 调试命令 (必须实现).
- [ ] 玩家接触/浸泡 `void_things:water` 时增加 DP.
- [ ] DP 满 100 睡觉进入梦魇维度 `void_things:dark_world`.
- [ ] 梦魇死亡惊醒: DP 降至 90, 重生后施加 `darkness_echo`.
- [ ] 封印黑暗喷泉: DP 降至 20, 清除 `darkness_echo`, 保留 1 血 1 饱食度.
- [ ] `darkness_echo` 期间无法睡觉.
- [ ] DP HUD (后置, 不阻塞核心逻辑).

## P2 维度与世界

- [ ] 纯黑维度 `void_things:strange_place`: 紫方块平台、方块树、展示架上的蛋、返回传送门.
- [ ] 荒芜维度 `void_things:ruins`: 遗迹、无之碎片、Oneshot 灯泡.
- [ ] 梦魇维度 `void_things:dark_world`: 与 DP 睡眠/惊醒流程联动.
- [ ] 主世界/末地穿梭时 2% 拉入 `strange_place` 的逻辑.
- [ ] 维度之间的返回与坐标安全屋 `(66, 66, 66)`.

## P3 机器与终局

- [ ] 组装机与黑白方块科技链.
- [ ] 控制台, 以及正常显示 "OK" 的交互.
- [ ] 传送至 `ruins` 的多方块结构.
- [ ] Oneshot 破碎灯泡: 点亮后降低 DP.
- [ ] 点亮纯黑维度、树后隐藏道路、黑系列终极物品.
- [ ] 终局剧情与隐藏真相内容.

## 收尾与验证

- [ ] 生成 1.0.0 完整 jar (`gradlew build`), 确认 `mods.toml` 版本为 1.0.0.
- [ ] 文档同步: `docs/Main.md` 的水描述改为当前流动水实现; `docs/Agent.md` 中已过时的 lang 待修复说明同步更新.

## 已确认非待办

- Datagen 的语言 provider 使用 Java 字符串生成 `en_us.json` / `zh_cn.json`, 属于数据生成, 不算运行时硬编码 lang.
- `void_things:water` 不加入 `minecraft:water` 标签: 避免触发原版溺水、游泳、珊瑚等判定, 这是刻意设计.
- 水使用 `ForgeFlowingFluid` 流动实现, 不是静态水; 旧文档中的“静态水”描述需要按上条收尾项同步.
