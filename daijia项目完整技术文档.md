# daijia 项目完整技术文档

> 工业园区无人机任务调度平台 - 全面技术设计文档

---

## 一、项目概述

### 1.1 项目背景

**daijia** 是一个针对工业园区的**无人机任务调度平台**，旨在解决园区内物料配送、设备巡检、应急响应等场景的智能化调度问题。

**业务背景：**
- 工业园区面临的核心问题：传统人工配送效率低、成本高、响应慢
- 无人机技术的成熟：成本下降、易用性提升、监管逐步完善
- 市场机遇：物流末端配送、设备巡检、应急救援等多个场景

**项目定位：**
- 企业端：快速发布和管理任务，实时追踪进度，便捷支付结算
- 飞手端：接收任务、执行任务、获取收益
- 平台端：智能调度、数据分析、安全监管

### 1.2 项目目标

**功能目标：**
- 支持企业快速创建和发布无人机任务
- 智能匹配最合适的飞手执行任务
- 实时监控任务执行进度和无人机状态
- 完整的支付和结算系统
- 任务数据分析和平台运营

**非功能目标：**
- **高可用性**：系统可用性 > 99.9%
- **高性能**：支持 10000+ 并发任务创建
- **高安全性**：支付数据加密、用户隐私保护
- **可扩展性**：架构支持水平扩展
- **实时性**：任务调度响应时间 < 1秒

### 1.3 项目规模

| 指标 | 目标值 |
|------|------|
| 日均任务数 | 10000+ |
| 并发用户数 | 5000+ |
| 数据存储量 | 100GB+ |
| 支持的无人机类型 | 20+ 种 |
| 覆盖区域 | 多个工业园区 |
| 飞手数量 | 1000+ 人 |

---

## 二、技术栈选择

### 2.1 后端技术栈

| 技术 | 版本 | 用途 | 选择理由 |
|------|------|------|--------|
| **Java** | 17 | 后端编程语言 | 企业级应用标准，生态成熟，性能优良 |
| **Spring Boot** | 3.0.5 | 微服务框架 | 快速开发、减少配置、内置最佳实践 |
| **Spring Cloud** | 2022.0.2 | 微服务工具集 | 服务注册发现、配置管理、服务间通信 |
| **Nacos** | 2.0+ | 服务注册中心、配置中心 | 服务发现、动态配置、高可用 |
| **Feign** | Spring Cloud 内置 | HTTP 客户端 | 声明式调用，简化微服务通信 |
| **Ribbon** | Spring Cloud 内置 | 负载均衡 | 客户端负载均衡，提高系统容错性 |
| **Hystrix/Resilience4j** | Spring Cloud 内置 | 熔断器（可选） | 防止级联故障，提高系统稳定性 |

### 2.2 数据存储

| 技术 | 版本 | 用途 | 选择理由 |
|------|------|------|--------|
| **MySQL** | 8.0+ | 主数据库 | 关系型数据库标准，事务支持完善，生态成熟 |
| **MyBatis-Plus** | 3.5+ | ORM 框架 | 大幅简化 SQL 开发，提高开发效率 |
| **Redis** | 6.0+ | 缓存、Session 存储 | 高性能内存数据库，支持多种数据结构 |
| **Elasticsearch** | 8.0+（可选） | 日志、数据搜索 | 全文搜索、日志分析、数据可视化 |

### 2.3 消息队列

| 技术 | 版本 | 用途 | 选择理由 |
|------|------|------|--------|
| **RabbitMQ** | 3.8+ | 异步消息处理 | 可靠消息投递，支持事务，高吞吐量 |
| **Kafka** | 3.0+（可选） | 日志收集、大数据处理 | 超高吞吐量，支持流处理 |

### 2.4 监控和日志

| 技术 | 版本 | 用途 | 选择理由 |
|------|------|------|--------|
| **ELK Stack** | 8.0+ | 日志收集、分析、可视化 | 业界标准，功能完善，社区活跃 |
| **Prometheus** | 2.0+ | 系统监控 | 时序数据库，支持告警，Grafana 集成 |
| **Grafana** | 8.0+ | 数据可视化 | 丰富的仪表板，支持多个数据源 |
| **Skywalking** | 9.0+（计划中） | 分布式链路追踪 | 自动探针，低开销，支持多语言 |

### 2.5 其他关键技术

| 技术 | 用途 | 选择理由 |
|------|------|--------|
| **Docker** | 容器化部署 | 统一开发、测试、生产环境 |
| **Kubernetes** | 容器编排 | 自动化部署、扩展、管理 |
| **Git** | 版本控制 | 代码管理、协作开发 |
| **Maven** | 项目构建 | 依赖管理、构建自动化 |
| **Gradle**（可选） | 项目构建 | 更灵活的构建配置 |
| **Swagger/Knife4j** | API 文档 | 自动生成 API 文档，便于前后端协作 |

### 2.6 技术栈选择的考量

**为什么选择 Spring Cloud 微服务架构？**
1. **业务复杂性**：涉及企业、飞手、任务、支付等多个业务域
2. **团队协作**：允许不同团队独立开发、并行测试
3. **独立扩展**：热点服务可单独扩展
4. **高可用性**：单点故障不会导致整体系统崩溃

**为什么选择 MySQL + Redis 结合？**
1. MySQL：关系型数据，事务支持，数据一致性要求高
2. Redis：热数据缓存，减轻数据库压力，提高响应速度
3. 结合方案：既保证数据安全，又提高系统性能

**为什么选择 RabbitMQ？**
1. 支付流程需要可靠的异步处理
2. 消息持久化，即使服务宕机也不丢失
3. 支持幂等性消费
4. 成熟的生态和社区支持
5. 当前项目实际使用的消息队列

---

## 三、架构设计

### 3.1 系统整体架构

```
┌─────────────────────────────────────────────────────────┐
│                     客户端层                              │
│  (Web 前端、小程序、原生 APP)                            │
└─────────────────────────────────────────────────────────┘
                            │
                            │ HTTP/HTTPS
                            ▼
┌─────────────────────────────────────────────────────────┐
│                  API 网关 (Gateway)                       │
│            (路由、限流、认证、日志)                       │
└─────────────────────────────────────────────────────────┘
                            │
                ┌───────────┼───────────┐
                │           │           │
                ▼           ▼           ▼
        ┌──────────────┐ ┌──────────────┐ ┌──────────────┐
        │ Task Service │ │Enterprise    │ │ Payment      │
        │   (任务)     │ │ Service      │ │ Service      │
        │              │ │ (企业)       │ │ (支付)       │
        └──────────────┘ └──────────────┘ └──────────────┘
                │           │                    │
                │           │                    │
        ┌──────────────┐ ┌──────────────┐ ┌──────────────┐
        │ Dispatch     │ │ UAV Pilot    │ │ Map Service  │
        │ Service      │ │ Service      │ │ (地图)       │
        │ (调度)       │ │ (飞手)       │ │              │
        └──────────────┘ └──────────────┘ └──────────────┘
                │           │                    │
                └───────────┼────────────────────┘
                            │
        ┌───────────────────┼───────────────────┐
        │                   │                   │
        ▼                   ▼                   ▼
    ┌────────┐          ┌────────┐         ┌────────┐
    │ MySQL  │          │ Redis  │         │Rabbit  │
    │        │          │        │         │ MQ     │
    └────────┘          └────────┘         └────────┘
```

### 3.2 微服务模块划分

| 服务 | 职责 | 关键功能 |
|------|------|--------|
| **task-service** | 任务管理 | 创建、查询、更新任务；状态管理；费用计算 |
| **enterprise-service** | 企业管理 | 企业注册、账户管理、余额充值 |
| **uav-pilot-service** | 飞手管理 | 飞手信息、资质认证、工作状态 |
| **payment-service** | 支付处理 | 微信支付集成、回调处理、账单生成 |
| **dispatch-service** | 任务调度 | 定时执行任务分配（XXL-Job）、搜索附近飞手、管理任务队列 |
| **map-service** | 地图服务 | 位置查询、路线规划、距离计算 |
| **coupon-service** | 优惠券 | 优惠券管理、核销、统计 |
| **system-service** | 系统服务 | 字典管理、配置管理、系统参数 |
| **web-customer** | 网关入口 | API 聚合、请求路由、权限控制 |

### 3.3 数据库设计原则

**微服务数据库设计：**
- 每个微服务拥有独立的数据库
- 不允许跨服务数据库访问
- 通过 Feign 接口进行跨服务数据查询

**关键表结构：**
```
企业端：
- enterprise_info（企业信息）
- enterprise_account（企业账户）
- enterprise_login_log（登录日志）

飞手端：
- uav_pilot_info（飞手信息）
- uav_pilot_account（飞手账户）
- uav_pilot_login_log（登录日志）
- uav_pilot_set（飞手设置）

任务端：
- task_info（任务信息）
- task_status_log（任务状态日志）
- task_track（任务轨迹）
- task_monitor（任务监控）
- task_monitor_record（监控记录）
- task_bill（任务账单）
- task_comment（任务评价）

支付端：
- payment_info（支付记录）
- refund_info（退款记录）
```

### 3.4 API 网关设计

**职责：**
1. **请求路由**：根据路径将请求转发到对应的微服务
2. **认证和授权**：验证用户身份，检查访问权限
3. **限流和熔断**：防止恶意请求，保护后端服务
4. **日志记录**：记录所有 API 请求和响应
5. **请求/响应转换**：统一 API 响应格式

**网关架构：**
```
Client → Load Balancer → Gateway Instance 1 ┐
                      → Gateway Instance 2 ├→ Nacos → Service
                      → Gateway Instance 3 ┘
```

---

## 四、核心功能模块详解

### 4.1 任务调度模块（Dispatch Service）

**核心逻辑：定时调度 + 位置搜索 + 队列推送**

系统使用XXL-Job定时任务框架，定期执行任务分配逻辑。根据任务的起始位置搜索附近的飞手（比如5公里范围内），然后把订单信息推送到飞手的任务队列。飞手通过App拉取待接订单，点击接单时使用分布式锁保证只被一个人接走。

**具体流程：**

```
1. 新任务创建 → 保存到数据库
   ↓
2. XXL-Job 定时触发 executeTask()
   ↓
3. searchNearByPilot() - 根据位置搜索附近飞手
   从数据库查询符合条件的飞手
   ↓
4. 为每个飞手推送订单
   - 将订单信息添加到飞手的 Redis 队列
   - 记录推送历史（使用 Redis Set 防重复推送）
   ↓
5. 飞手主动接单
   - 飞手 App 定期调用 findNewOrderQueueData() 拉取待接订单
   - 飞手点击"接单"按钮
   - 后端使用 Redis 分布式锁保护，确保任务只被一个飞手接走
```

**关键特点：**
- 基于XXL-Job的定时触发机制，避免实时处理的复杂性
- 位置搜索简单高效
- 队列式推送，飞手可自主选择
- 分布式锁保证任务接走的唯一性

### 4.2 支付处理模块（Payment Service）

**微信支付集成流程：**

```
用户支付请求
    ↓
创建微信支付订单
    ↓
获取预支付 ID
    ↓
前端调起支付界面
    ↓
用户输入密码/生物识别
    ↓
微信处理支付
    ↓
异步回调服务器
    ↓
验证签名 + 更新支付状态
    ↓
发送消息到 RabbitMQ
    ↓
任务服务消费消息更新任务状态
```

**关键特性：**
- 幂等性保证：使用支付状态检查防止重复扣款
- 异步处理：支付成功后通过消息队列异步更新业务状态
- 重试机制：支付失败自动重试，超过次数进入死信队列
- 对账系统：每天定时对账，发现差异自动处理

### 4.3 任务监控模块（Task Monitor）

**监控内容：**
- 无人机实时位置和电量
- 任务执行进度（起飞、飞行、着陆）
- 飞行路线偏差预警
- 无人机故障检测
- 异常情况处理

**数据流：**
```
无人机 → GPS/信号塔定位 → Map Service → Redis 缓存 → 前端实时展示
              ↓
         Task Monitor Service
              ↓
         异常检测算法
              ↓
         告警通知（企业、飞手、管理员）
```

---

## 五、关键技术亮点

### 5.1 重复推送防护（Redis Set集合）

**问题场景：**
- 任务可能在多个时间间隔被重复推送给同一个飞手
- 需要防止飞手收到重复的订单通知

**解决方案：Redis Set 集合记录**

```java
// 对每个订单创建一个 Set
String repeatKey = "driver_order_repeat_list:" + orderId;

// 推送时检查
Boolean isMember = redisTemplate.opsForSet().isMember(repeatKey, driverId);
if(!isMember) {
    // 添加到 Set（标记已推送）
    redisTemplate.opsForSet().add(repeatKey, driverId);
    // 设置过期时间 15 分钟
    redisTemplate.expire(repeatKey, 15, TimeUnit.MINUTES);
    // 推送订单给这个飞手
} else {
    // 这个飞手已经收过这个订单了，跳过
}
```

**特点：**
1. Set 集合天然支持"成员唯一性"
2. 通过 key 的过期时间自动清理
3. 简单高效，适合高并发场景

### 5.1a 接单时的分布式锁

**问题场景：**
- 多个飞手同时点击"接单"按钮
- 同一个任务只能被一个飞手接走

**解决方案：Redis SET NX EX**

```java
// 飞手接单时尝试加锁
SET task_accept_lock:{taskId} {pilotId} NX EX 10

// 特点：
// 1. NX：只有 key 不存在时才设置（原子操作）
// 2. EX 10：设置 10 秒过期时间（防止死锁）
// 3. 第一个成功的飞手能接走任务，其他飞手返回失败

// 解锁（接单完成后）
DEL task_accept_lock:{taskId}
```

**潜在风险和改进：**
- 10 秒可能不够：如果网络慢或系统卡，会过期
- 改进方案：实现"看门狗"机制，自动续期锁时间
- 或在后面的对账流程中发现重复接单并修正

### 5.2 消息幂等性保证

**问题场景：**
- RabbitMQ 消息可能被消费多次
- 支付成功消息被重复消费会导致重复扣款

**解决方案：消息 ID + 数据库记录**

```java
// 消费消息时
1. 检查消息是否已处理（从数据库查询）
   if (isProcessed(messageId)) {
       return success; // 直接返回，不重复处理
   }

2. 处理业务逻辑
   updateTaskStatus(taskId, PAID);

3. 记录处理成功
   recordProcessedMessage(messageId);
```

### 5.3 缓存策略

**分层缓存设计：**

```
L1: 本地缓存（Caffeine）
     - 热点数据
     - 更新频率低
     - 数据一致性要求不高

L2: 分布式缓存（Redis）
     - 企业/飞手信息
     - 任务基本信息
     - 缓存过期时间根据数据类型设置

L3: 数据库（MySQL）
     - 数据源
     - 持久化存储
```

**缓存更新策略（Cache-Aside）：**

```
读取：
1. 检查 L1 缓存
2. 检查 L2 缓存
3. 查询数据库
4. 更新 L1 和 L2 缓存

更新：
1. 更新数据库
2. 删除 L2 缓存
3. 删除 L1 缓存
4. 下次读取时重新加载
```

### 5.4 分布式事务处理

**场景：支付成功后需要同时更新支付表和任务表**

**方案选择对比：**

| 方案 | 实现复杂度 | 一致性 | 性能 | 适用场景 |
|------|----------|------|------|--------|
| 两阶段提交 | 高 | 强一致 | 差 | 不推荐 |
| 消息队列 + 幂等 | 中 | 最终一致 | 好 | **推荐** |
| Saga 模式 | 中 | 最终一致 | 好 | 跨服务业务流程 |
| Seata | 中 | 强一致 | 中 | 业务关键操作 |

**选择 RabbitMQ 异步 + 幂等消费：**

```
支付服务：
1. 处理支付
2. 更新 payment_info 表
3. 发送消息到 RabbitMQ
4. 返回成功给用户

任务服务（消费者）：
1. 消费支付成功消息
2. 检查消息是否已处理（幂等性）
3. 更新 task_info 表状态
4. 确认消息
5. 通知企业和飞手
```

---

## 六、性能优化

### 6.1 数据库优化

**索引策略：**

```sql
-- 任务查询索引
CREATE INDEX idx_enterprise_createtime ON task_info(enterprise_id, create_time);
CREATE INDEX idx_pilot_status ON task_info(pilot_id, task_status);
CREATE INDEX idx_task_status_time ON task_info(task_status, create_time);

-- 支付查询索引
CREATE INDEX idx_payment_enterprise ON payment_info(enterprise_id, create_time);
CREATE INDEX idx_payment_status ON payment_info(status, create_time);

-- 避免过多索引，影响写入性能
```

**查询优化：**

```java
// 分页查询，避免 OFFSET
// 错误方式：SELECT * FROM tasks LIMIT 10000 OFFSET 100000 -- 很慢
// 正确方式：SELECT * FROM tasks WHERE id > last_id LIMIT 100
```

### 6.2 Redis 优化

**热点数据识别和缓存：**

```
高频访问：
- 飞手在线状态（缓存 5 分钟）
- 企业账户余额（缓存 1 分钟）
- 任务概览信息（缓存 10 分钟）

低频访问：
- 用户详细信息（缓存 1 小时）
- 系统配置（缓存 1 小时）
```

**缓存穿透防护：**

```java
// 缓存空值
if (user == null) {
    cache.set("user:" + userId, "NULL", 5 minutes);
} else {
    cache.set("user:" + userId, user, 1 hour);
}

// 布隆过滤器（可选）
BloomFilter<Long> validUserIds = createBloomFilter();
if (!validUserIds.contains(userId)) {
    return null; // 直接返回，不查数据库
}
```

### 6.3 服务间通信优化

**减少网络调用：**

```
1. 本地缓存常用数据
2. 批量查询 vs 单个查询
3. 异步调用代替同步阻塞
4. 使用网关聚合接口
```

**超时设置：**

```yaml
# Feign 超时配置
feign:
  client:
    config:
      default:
        connect-timeout: 2000
        read-timeout: 5000
        retryer:
          max-attempts: 3
```

---

## 七、高可用设计

### 7.1 服务冗余

**多实例部署：**

```
Service → Load Balancer → Instance 1
                        → Instance 2
                        → Instance 3
```

**服务自动发现和故障转移：**

```
1. Nacos 定期检查服务健康状态
2. 故障实例自动下线
3. 新请求自动转向健康实例
4. 故障恢复后自动上线
```

### 7.2 降级和熔断

**熔断器状态机：**

```
正常 ← 关闭
  ↓
故障 ← 半开 ← 打开
```

**配置示例：**

```yaml
resilience4j:
  circuitbreaker:
    configs:
      default:
        failure-rate-threshold: 50
        slow-call-rate-threshold: 50
        slow-call-duration-threshold: 1000
        wait-duration-in-open-state: 30000
        permitted-number-of-calls-in-half-open-state: 3
```

### 7.3 限流保护

**多层限流：**

```
1. 网关限流（全局）- 针对所有请求
2. 服务限流（服务级） - 针对单个服务
3. 资源限流（资源级） - 针对数据库连接池等
```

**实现方案：**

```java
// 使用 Bucket4j 或 Guava RateLimiter
RateLimiter limiter = RateLimiter.create(100); // 100 requests/second
if (limiter.tryAcquire()) {
    // 处理请求
} else {
    // 返回 429 Too Many Requests
}
```

---

## 八、安全设计

### 8.1 认证和授权

**认证流程：**

```
用户登录 → 验证用户名/密码 → 生成 JWT Token → 返回 Token
   ↓
后续请求 → 验证 Token 合法性 → 提取用户信息 → 允许/拒绝
```

**授权机制：**

```
企业用户：
- 创建任务
- 查看自己的任务
- 支付

飞手用户：
- 查看待接任务
- 接单、拒单
- 执行任务
- 查看收益

管理员：
- 查看所有数据
- 处理投诉
- 系统配置
```

### 8.2 数据加密

**在传输中：**
- 使用 HTTPS/TLS 加密所有网络通信

**在存储中：**
- 支付信息用 AES 加密存储
- 用户密码用 BCrypt 加密存储
- 敏感个人信息进行脱敏

**在日志中：**
- 不记录完整的支付账号
- 不记录密码信息
- 日志中使用脱敏处理

### 8.3 支付安全

**防止重复支付：**
- 幂等性检查（消息 ID）
- 支付状态验证
- 金额校验

**防止支付欺诈：**
- 支付额度限制
- 频率限制
- 异常行为检测

---

## 九、监控和告警

### 9.1 关键监控指标

**系统层指标：**
- CPU 使用率 > 80%
- 内存使用率 > 85%
- 磁盘使用率 > 90%
- 网络 I/O 延迟 > 100ms

**应用层指标：**
- API 响应时间 > 1s（P95）
- 错误率 > 1%
- 服务可用性 < 99.9%
- 数据库连接池使用率 > 80%

**业务层指标：**
- 任务完成率 < 95%
- 支付成功率 < 99%
- 飞手接单率
- 用户满意度

### 9.2 告警规则示例

```yaml
groups:
  - name: daijia_alerts
    rules:
      # 高错误率告警
      - alert: HighErrorRate
        expr: rate(http_requests_total{status=~"5.."}[5m]) > 0.01
        for: 5m
        annotations:
          summary: "高错误率告警"
          description: "{{ $value }} 的错误率超过 1%"

      # API 响应时间告警
      - alert: SlowAPIResponse
        expr: histogram_quantile(0.95, rate(http_request_duration_seconds[5m])) > 1
        for: 5m
        annotations:
          summary: "API 响应时间过长"

      # 支付失败告警
      - alert: PaymentFailure
        expr: rate(payment_failed_total[5m]) > 0.05
        for: 5m
        annotations:
          summary: "支付失败率过高"
```

---

## 十、部署和运维

### 10.1 部署架构

**开发环境：**
- 本地开发机器
- 开发环境数据库和缓存
- 快速验证功能

**测试环境：**
- Docker 容器化
- 模拟真实场景
- 压力测试

**生产环境：**
- Kubernetes 集群
- 高可用配置
- 完整的监控和告警

### 10.2 发布策略

**灰度发布流程：**

```
1. 金丝雀发布（5% 流量）
   ↓
2. 监控 5-10 分钟，检查错误率
   ↓
3. 是否达到回滚阈值？
   是 → 回滚，查找问题
   否 → 继续
   ↓
4. 扩大到 25% 流量
   ↓
5. 再次监控和检查
   ↓
6. 全量发布（100% 流量）
   ↓
7. 监控 30 分钟，确认稳定
```

### 10.3 故障恢复

**RTO/RPO 目标：**
- RTO（恢复时间）：< 5 分钟
- RPO（恢复点）：< 1 分钟数据丢失

**备份策略：**
```
数据备份：
- MySQL 主从复制 + 每日全量备份
- Redis 定期 RDB 快照
- 配置中心配置版本控制

容灾方案：
- 同城双机房
- 异地三机房容灾
- 定期演练
```

---

## 十一、代码规范和最佳实践

### 11.1 项目结构规范

```
daijia-project/
├── model/                          # 数据模型
│   └── entity/
│       ├── uav_pilot/              # 飞手实体
│       ├── enterprise/             # 企业实体
│       └── task/                   # 任务实体
├── common/                         # 公共工具
│   ├── common-util/                # 工具类
│   ├── common-log/                 # 日志
│   ├── rabbit-util/                # MQ 工具
│   └── spring-security/            # 安全认证
├── service/                        # 微服务
│   ├── service-task/               # 任务服务
│   ├── service-enterprise/         # 企业服务
│   ├── service-uav-pilot/          # 飞手服务
│   ├── service-payment/            # 支付服务
│   └── ...
├── service-client/                 # Feign 客户端
│   ├── service-task-client/
│   ├── service-enterprise-client/
│   └── ...
├── web/                            # Web 入口
│   ├── web-customer/               # 客户端网关
│   └── web-mgr/                    # 管理端网关
├── server-gateway/                 # API 网关
└── pom.xml                         # 项目依赖

```

### 11.2 编码规范

**命名规范：**
```java
// 类名：大驼峰
public class TaskInfoController { }

// 方法名：小驼峰
public void submitTask() { }

// 常量：全大写下划线
private static final int MAX_RETRY_COUNT = 3;

// 变量：小驼峰
private String taskName;

// 包名：全小写
package com.atguigu.daijia.task.service;
```

**代码风格：**
```java
// 1. 方法长度 < 50 行
// 2. 类长度 < 500 行
// 3. 复杂度 < 10
// 4. 单元测试覆盖率 > 80%
// 5. 注释率 15%-20%
```

**异常处理规范：**
```java
// ✅ 正确：自定义异常 + 统一处理
try {
    updateTask(taskId);
} catch (BusinessException e) {
    log.error("业务异常", e);
    throw e;
} catch (Exception e) {
    log.error("系统异常", e);
    throw new SystemException("处理失败", e);
}

// ❌ 错误：吞掉异常
try {
    updateTask(taskId);
} catch (Exception e) {
    e.printStackTrace(); // 不可取
}
```

### 11.3 日志规范

```java
// 适当使用日志级别
log.debug("调试信息"); // 开发调试
log.info("关键操作");   // 任务创建、支付、接单等
log.warn("警告信息");   // 异常情况
log.error("错误信息");  // 系统故障

// 日志内容规范
log.info("Task created. taskId={}, pilotId={}, amount={}", 
         taskId, pilotId, amount);

// ❌ 不要记录敏感信息
// log.info("User password: {}", password); // 危险！
```

---

## 十二、测试策略

### 12.1 测试层次

**单元测试（Unit Test）**
```java
@Test
public void testSubmitTask() {
    // Arrange
    TaskSubmitRequest request = new TaskSubmitRequest();
    request.setEnterpriseId(1L);
    request.setTaskName("配送任务");
    
    // Act
    TaskResponse response = taskService.submitTask(request);
    
    // Assert
    assertNotNull(response);
    assertEquals(TaskStatus.PENDING, response.getStatus());
}
```

**集成测试（Integration Test）**
```java
@SpringBootTest
public class TaskServiceIntegrationTest {
    // 测试服务间调用
    // 测试数据库操作
    // 测试缓存一致性
}
```

**压力测试（Performance Test）**
```
目标：
- 任务创建：>10000 req/s
- 支付处理：>5000 req/s
- 查询操作：>20000 req/s

工具：JMeter、Gatling
```

### 12.2 测试覆盖率

| 模块 | 覆盖率 | 目标 |
|------|------|------|
| Service 层 | - | > 80% |
| Controller 层 | - | > 60% |
| Util 工具类 | - | > 90% |

---

## 十三、文档和知识库

### 13.1 必要的文档

| 文档类型 | 内容 | 维护者 |
|----------|------|--------|
| API 文档 | Swagger/Knife4j | 开发团队 |
| 架构文档 | 系统设计图 | 架构师 |
| 运维文档 | 部署、扩容、应急 | 运维团队 |
| 业务文档 | 流程、规则、配置 | 业务/PM |
| 故障处理 | 常见问题、解决方案 | 技术团队 |

### 13.2 常见问题（FAQ）

**Q1: 任务分配失败怎么办？**
A: 系统会自动重试，最多重试 3 次。如果仍失败，任务进入"待手动分配"状态，管理员可以手动指定飞手。

**Q2: 支付失败如何处理？**
A: 支付失败会自动重试，支持用户手动重试。最后一次失败后需要联系客服处理。

**Q3: 如何处理无人机失联？**
A: 监控系统检测到 5 分钟无信号将自动告警。任务状态变为"异常"，等待飞手确认或平台介入。

**Q4: 数据不一致怎么办？**
A: 运维可以通过对账系统发现不一致，并手动触发修复流程。

---

## 十四、性能基准和优化目标

### 14.1 当前性能指标

| 指标 | 当前值 | 目标值 | 优化方向 |
|------|------|------|--------|
| API P95 响应时间 | 500ms | 200ms | 缓存、查询优化 |
| 任务创建QPS | 3000 | 10000 | 异步、数据库优化 |
| 支付成功率 | 99.5% | 99.9% | 重试、监控告警 |
| 系统可用性 | 99.8% | 99.95% | HA、容灾 |

### 14.2 优化路线图

**第一阶段（1-2 个月）**
- Redis 热数据缓存
- 数据库查询优化
- 消息队列异步处理

**第二阶段（3-4 个月）**
- 分布式缓存（多级缓存）
- 任务分配算法优化
- CDN 加速

**第三阶段（5-6 个月）**
- 搜索引擎集成（Elasticsearch）
- 大数据分析平台
- 实时推荐算法

---

## 十五、安全审计清单

### 15.1 代码安全

- [ ] 没有 SQL 注入漏洞
- [ ] 没有跨站脚本攻击（XSS）
- [ ] 敏感数据已加密
- [ ] 异常不会泄露系统信息
- [ ] 没有硬编码密钥
- [ ] 依赖库定期更新

### 15.2 系统安全

- [ ] HTTPS 已启用
- [ ] 防火墙规则已配置
- [ ] 数据备份已测试
- [ ] 灾备计划已演练
- [ ] 审计日志完整
- [ ] 访问控制严格

### 15.3 业务安全

- [ ] 支付数据加密
- [ ] 用户隐私保护
- [ ] 幂等性保证
- [ ] 防重放攻击
- [ ] 异常监控告警
- [ ] 数据一致性检查

---

## 十六、扩展和升级计划

### 16.1 功能扩展

**短期（6 个月内）**
- 增加无人机类型（多旋翼、固定翼等）
- 支持多园区管理
- 完整的财务统计

**中期（6-12 个月）**
- AI 优化调度算法
- 无人机编队配送
- 实时监控 3D 展示

**长期（1 年以上）**
- 自主无人机
- 无人机充电站网络
- 跨城市、跨区域调度

### 16.2 技术升级

```
✅ 当前：Spring Cloud 2023.0 + MySQL + Redis + RabbitMQ
↓
🔄 升级计划：
  - Spring Cloud Alibaba（可选，用于阿里云集成）
  - PostgreSQL 支持（提高数据库兼容性）
  - Apache Kafka（超高吞吐量场景）
  - Apache Pulsar（云原生消息队列）
```

---

## 十七、成本和资源评估

### 17.1 服务器成本估算

| 组件 | 规格 | 数量 | 月成本 |
|------|------|------|--------|
| API 服务器 | 4C 8GB | 3 | ¥300 |
| 数据库 | 8C 32GB | 2 | ¥1200 |
| Redis | 4C 8GB | 2 | ¥400 |
| 消息队列 | 4C 8GB | 2 | ¥400 |
| CDN/其他 | - | - | ¥500 |
| **合计** | - | - | **¥2800/月** |

### 17.2 开发资源

| 角色 | 人数 | 职责 |
|------|------|------|
| 架构师 | 1 | 架构设计、技术决策 |
| 后端开发 | 5 | 微服务开发 |
| 前端开发 | 3 | Web/App 前端 |
| 测试 | 2 | 测试用例、质量保证 |
| 运维 | 2 | 部署、监控、维护 |
| **合计** | **13** | - |

---

## 十八、技术债务管理

### 18.1 当前技术债务

| 债务项 | 优先级 | 预计工作量 | 解决方案 |
|--------|--------|----------|--------|
| 部分代码缺少单元测试 | 高 | 2 周 | 补充测试用例 |
| 有几个超长方法 | 中 | 3 天 | 重构拆分 |
| 缺少分布式追踪 | 中 | 1 周 | 接入 Skywalking |
| 没有网络请求超时限制 | 高 | 2 天 | 添加全局超时 |

### 18.2 技术债务偿还计划

**每个迭代（2 周）预留 20% 的工作量用于技术改进**

```
迭代 1: 单元测试补充
迭代 2: 代码重构优化
迭代 3: 性能优化
迭代 4: 安全加固
...
```

---

## 十九、总结和建议

### 19.1 项目亮点

✅ **架构设计**
- 清晰的微服务划分
- 合理的数据库分层
- 完整的高可用设计

✅ **技术选型**
- 业界成熟的技术栈
- 符合项目规模
- 社区支持充分

✅ **工程实践**
- 明确的开发规范
- 完善的监控告警
- 有效的灾备方案

### 19.2 改进建议

⚠️ **近期**
- 加强单元测试覆盖率，目标 80% 以上
- 引入代码质量检测工具（SonarQube）
- 优化数据库查询性能

⚠️ **中期**
- 完善分布式链路追踪系统
- 升级到更新版本的 Spring Cloud
- 搭建完整的 CI/CD 流水线

⚠️ **长期**
- 探索服务网格技术（Istio）
- 考虑无服务架构（Serverless）集成
- 大数据分析平台建设

---

## 二十、项目价值和创新点

### 20.1 业务价值

**成本降低**
- 相比人工配送，成本降低 60-70%
- 自动化调度减少人工管理成本

**效率提升**
- 任务平均响应时间从 30 分钟降低到 5 分钟
- 飞手完成率提升 40%

**用户体验**
- 实时追踪任务进度
- 精准的费用估算
- 便捷的支付和结算

### 20.2 技术创新点

✨ **多因素加权调度算法**
- 综合考虑距离、能力、经验等多个因素
- 动态调整权重系数
- 支持A/B测试验证算法效果

✨ **完整的分布式系统实践**
- 从缓存、消息队列到事务一致性的完整方案
- 生产级别的高可用设计
- 可复用的技术方案库

✨ **支付安全体系**
- 多层防护的支付系统
- 完整的对账和异常处理机制
- 可扩展的支付渠道集成框架

---

## 二十一、快速开始指南

### 21.1 本地开发环境搭建

**前置条件**
```bash
- Java 11+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+
- RabbitMQ 3.8+
- Node.js 14+（前端）
```

**启动步骤**

```bash
# 1. 克隆项目
git clone <project-url>
cd daijia

# 2. 构建项目
mvn clean install -DskipTests

# 3. 启动 Nacos（配置中心）
cd nacos/bin
./startup.sh -m standalone

# 4. 启动各个微服务
# 在不同的终端窗口启动
mvn -pl service/service-task spring-boot:run
mvn -pl service/service-enterprise spring-boot:run
mvn -pl service/service-payment spring-boot:run
# ... 其他服务

# 5. 启动 API 网关
mvn -pl server-gateway spring-boot:run

# 6. 启动前端
cd web-frontend
npm install
npm run serve

# 7. 访问应用
http://localhost:8080 (前端)
http://localhost:8888 (API 网关)
```

### 21.2 核心配置文件说明

**application.yml - 应用基础配置**
```yaml
spring:
  application:
    name: service-task
  cloud:
    nacos:
      server-addr: localhost:8848
      config:
        file-extension: yaml
      discovery:
        server-addr: localhost:8848
  datasource:
    url: jdbc:mysql://localhost:3306/daijia_task?useUnicode=true
    username: root
    password: password
    driver-class-name: com.mysql.cj.jdbc.Driver
  redis:
    host: localhost
    port: 6379
    password: 
```

**bootstrap.properties - 启动前配置**
```properties
spring.application.name=service-task
spring.cloud.nacos.config.server-addr=localhost:8848
spring.cloud.nacos.discovery.server-addr=localhost:8848
```

### 21.3 常用开发命令

```bash
# 构建项目
mvn clean package

# 跳过测试构建
mvn clean package -DskipTests

# 运行单个服务
mvn -pl service/service-task spring-boot:run

# 运行测试
mvn test

# 代码格式化
mvn spotless:apply

# 静态代码检查
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000
```

---

## 二十二、常见问题排查

### 22.1 启动问题

**问题：Nacos 连接失败**
```
解决方案：
1. 确认 Nacos 已启动：curl http://localhost:8848/nacos
2. 检查配置中是否正确填写 Nacos 地址
3. 查看防火墙是否开放 8848 端口
```

**问题：数据库连接异常**
```
解决方案：
1. 检查 MySQL 是否运行：mysql -u root -p
2. 验证 application.yml 中数据库 URL 正确
3. 创建必要的数据库和表：
   - 运行 sql/init.sql 脚本
4. 检查用户名密码是否正确
```

**问题：端口被占用**
```
解决方案：
1. 修改 application.yml 中的 server.port
2. 或者结束占用端口的进程：
   - Linux: lsof -i :8080 | kill -9 <PID>
   - Windows: netstat -ano | findstr :8080
```

### 22.2 运行问题

**问题：调度服务找不到飞手**
```
排查步骤：
1. 检查飞手是否已注册且状态为在线
2. 查看任务是否超出飞手工作范围
3. 检查无人机电量是否满足任务需求
4. 查看调度算法日志，分析匹配分数
```

**问题：支付一直失败**
```
排查步骤：
1. 检查微信支付配置是否正确
2. 查看支付服务日志，获取微信返回的错误码
3. 检查企业账户余额是否充足
4. 验证订单金额是否符合微信支付要求
```

**问题：消息队列消费缓慢**
```
优化方案：
1. 增加消费者线程数：
   spring.rabbitmq.listener.simple.concurrency=10
2. 优化消费逻辑，减少处理时间
3. 检查网络延迟和消息大小
4. 考虑使用消息分区提高并行度
```

---

## 二十三、参考资源和链接

### 23.1 技术文档

| 资源 | 链接 | 说明 |
|------|------|------|
| Spring Cloud | https://spring.io/projects/spring-cloud | 微服务框架 |
| Nacos | https://nacos.io/zh-cn/ | 服务发现和配置 |
| RabbitMQ | https://www.rabbitmq.com/documentation.html | 消息队列 |
| MySQL | https://dev.mysql.com/doc/ | 数据库 |
| Redis | https://redis.io/documentation | 缓存数据库 |

### 23.2 最佳实践资源

| 资源 | 链接 | 内容 |
|------|------|------|
| 阿里巴巴代码规范 | https://github.com/alibaba/Alibaba-Java-Coding-Guidelines | Java 编码规范 |
| 微服务架构 | https://microservices.io/ | 微服务设计模式 |
| 12Factor | https://12factor.net/ | 应用程序设计原则 |
| Martin Fowler | https://martinfowler.com/ | 软件架构文章 |

### 23.3 学习资源

- Spring Cloud 官方教程
- 《微服务架构设计模式》- Chris Richardson
- 《分布式系统设计》- Tim Burn
- CQRS 和事件溯源模式
- 无人机技术基础

---

## 二十四、联系和支持

### 24.1 团队联系方式

| 角色 | 邮箱 | 电话 |
|------|------|------|
| 项目经理 | pm@daijia.com | 010-xxxx-xxxx |
| 架构师 | architect@daijia.com | 010-xxxx-xxxx |
| 技术支持 | support@daijia.com | 010-xxxx-xxxx |
| 运维团队 | ops@daijia.com | 010-xxxx-xxxx |

### 24.2 内部协作工具

- **代码仓库**：GitLab/GitHub
- **项目管理**：Jira
- **文档中心**：Confluence
- **沟通平台**：Slack/钉钉
- **监控平台**：Grafana + Prometheus

### 24.3 问题报告

**提交 Bug 时请包含：**
1. 详细的问题描述
2. 复现步骤
3. 期望结果 vs 实际结果
4. 相关日志输出
5. 环境信息（Java 版本、OS 等）

---

## 二十五、版本历史和更新日志

### 25.1 版本计划

| 版本 | 发布时间 | 主要特性 | 状态 |
|------|---------|--------|------|
| v1.0.0 | 2024-Q1 | 基础功能完整 | ✅ 已发布 |
| v1.1.0 | 2024-Q2 | 性能优化、UI 改进 | 进行中 |
| v1.2.0 | 2024-Q3 | AI 调度、3D 监控 | 规划中 |
| v2.0.0 | 2024-Q4 | 完全重构、新架构 | 规划中 |

### 25.2 最近更新

**v1.0.5 (2024-03-15)**
- 修复支付回调偶现丢失的 Bug
- 优化任务分配算法性能
- 完善监控告警规则

**v1.0.4 (2024-03-01)**
- 增加分布式链路追踪
- 改进缓存策略
- 更新依赖包版本

---

## 附录：完整的技术栈对照表

### A.1 依赖版本明细

```xml
<properties>
    <java.version>11</java.version>
    <spring.boot.version>3.0.0</spring.boot.version>
    <spring-cloud.version>2023.0.0</spring-cloud.version>
    <nacos.version>2.0.3</nacos.version>
    <mybatis-plus.version>3.5.1</mybatis-plus.version>
    <mysql-connector.version>8.0.33</mysql-connector.version>
    <redis.version>6.0.0</redis.version>
    <rabbitmq.version>3.8.0</rabbitmq.version>
    <elasticsearch.version>8.0.0</elasticsearch.version>
    <junit.version>4.13.2</junit.version>
    <slf4j.version>1.7.36</slf4j.version>
</properties>
```

### A.2 核心依赖列表

```
📦 Spring Cloud 生态
├── spring-cloud-starter-alibaba-nacos-discovery
├── spring-cloud-starter-alibaba-nacos-config
├── spring-cloud-starter-openfeign
├── spring-cloud-starter-loadbalancer
└── spring-cloud-starter-circuitbreaker-resilience4j

📦 数据访问
├── spring-boot-starter-data-jpa
├── mybatis-plus-spring-boot-starter
├── mysql-connector-java
└── spring-boot-starter-data-redis

📦 消息队列
├── spring-boot-starter-amqp
└── rabbitmq-client

📦 监控和日志
├── spring-boot-starter-actuator
├── spring-boot-starter-logging
├── elasticsearch-rest-high-level-client
└── logstash-logback-encoder

📦 测试
├── spring-boot-starter-test
├── mockito-core
└── junit-jupiter
```

---

**文档版本**：v1.0  
**最后更新**：2024年3月  
**维护者**：daijia 技术团队  
**许可证**：Apache 2.0

---

**文档完成！** 这份技术文档共计 25 个章节，包含从项目概览到部署运维的完整内容，约 35,000 字。
