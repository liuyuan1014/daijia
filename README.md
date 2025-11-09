# 工业园区UAV飞行任务调度平台

## 📋 项目简介

工业园区UAV飞行任务调度平台是一套完整的无人机任务管理系统，从原代驾平台演进而来。平台支持**运营商**发布无人机飞行任务，**飞手**接单并操控无人机完成飞行任务。

核心场景：
- 🏢 **运营商端**：创建飞行任务，管理无人机资源，查看实时飞行轨迹
- 👨‍✈️ **飞手端**：接收任务邀约，操控无人机执行任务，上传任务数据
- 📊 **管理后台**：系统监控，数据统计，风险评估，财务结算

---

## 🏗️ 系统架构

### 技术栈
- **后端框架**：Spring Boot 3.0.5 + Spring Cloud 2022.0.2
- **数据存储**：MySQL 8.0 + MongoDB + Elasticsearch
- **消息队列**：RabbitMQ
- **服务注册/发现**：Nacos
- **API网关**：Spring Cloud Gateway
- **缓存**：Redis + Redisson
- **分布式事务**：Seata
- **业务规则引擎**：Drools
- **对象存储**：MinIO
- **日志**：ELK（Elasticsearch + Logstash + Kibana）
- **构建工具**：Maven

### 项目结构
```
uav_platform/
├── common/                     # 通用模块
│   ├── common-util/           # 工具类库
│   ├── common-log/            # 日志模块
│   ├── service-util/          # 微服务工具
│   ├── rabbit-util/           # RabbitMQ工具
│   └── spring-security/       # 安全认证
├── model/                     # 数据模型
├── service/                   # 微服务模块
│   ├── service-uav-pilot/     # 飞手服务（核心）
│   ├── service-order/         # 订单服务
│   ├── service-coupon/        # 优惠券服务
│   ├── service-dispatch/      # 调度服务
│   ├── service-customer/      # 用户服务
│   ├── service-payment/       # 支付服务
│   ├── service-map/           # 地图服务
│   ├── service-system/        # 系统服务
│   ├── service-rules/         # 规则引擎服务
│   └── service-mq/            # 消息队列服务
├── web/                       # Web应用模块
│   ├── web-uav-pilot/         # 飞手App后端
│   ├── web-customer/          # 运营商App后端
│   └── web-mgr/               # 管理后台
├── service-client/            # 服务调用客户端（Feign）
├── server-gateway/            # API网关
└── 资料/                      # 配置资料
```

---

## 🔑 核心业务流程

### 1. 任务发布与接单
```
运营商创建飞行任务 
  ↓
系统分析任务参数（区域、时间、风险等级）
  ↓
规则引擎(Drools)评估合适的飞手
  ↓
通过RabbitMQ发送任务推送
  ↓
飞手收到邀约 → 接单/拒单
```

### 2. 飞行任务执行
```
飞手接单
  ↓
获取实时地图数据（service-map）
  ↓
开始飞行操控 → 实时轨迹上报
  ↓
任务执行中的状态变更（via MQ）
  ↓
任务完成 → 生成订单（service-order）
```

### 3. 订单结算与支付
```
任务完成 → 生成订单
  ↓
Seata分布式事务保证
  ↓
调用微信支付接口（service-payment）
  ↓
更新账户余额
  ↓
生成财务报表
```

---

## 🚀 技术亮点

### 1. 高并发处理
- **限流**：Alibaba Sentinel配置服务限流策略
- **缓存策略**：Redis多级缓存，减少数据库查询
- **数据库优化**：MySQL分表、索引优化、批量操作
- **消息队列削峰**：RabbitMQ异步处理任务推送、订单生成

### 2. 分布式架构
- **微服务拆分**：按业务域独立部署10+个微服务
- **服务治理**：Nacos配置中心统一管理，自动化服务发现
- **网关统一入口**：Gateway路由、限流、认证
- **链路追踪**：支持分布式链路追踪

### 3. 分布式锁与一致性
- **Redisson分布式锁**：确保飞手接单的唯一性
- **Seata分布式事务**：订单与支付操作的强一致性
- **乐观锁**：库存扣减场景的并发控制

### 4. 业务规则引擎
- **Drools规则引擎**：评估飞手匹配度、任务风险等级
- **动态配置规则**：无需重启即可变更业务规则

### 5. 实时性能
- **WebSocket实时推送**：飞手位置实时上报
- **Elasticsearch全文搜索**：任务快速查询
- **MongoDB文档存储**：灵活的轨迹数据存储

---

## 📊 核心模块说明

### service-uav-pilot（飞手服务）
- 飞手认证、信息管理
- 技能等级管理、保险管理
- 历史订单查询、评价系统

### service-order（订单服务）
- 订单创建、状态管理
- 订单金额计算、佣金结算
- 订单查询、统计分析

### service-dispatch（调度服务）
- 任务分配算法（距离、评分、在线状态）
- 任务超时处理
- 任务重新分配

### service-payment（支付服务）
- 微信支付集成
- 支付回调处理
- 退款、对账

### service-rules（规则引擎）
- Drools规则配置
- 飞手匹配规则
- 风险评估规则
- 价格计算规则

---

## 🔐 系统设计亮点

### 安全性
- Spring Security + JWT认证
- 敏感数据加密存储
- 接口访问权限控制
- SQL注入防护、XSS防护

### 可扩展性
- 完全的微服务架构
- Feign声明式服务调用
- 支持服务熔断降级
- 支持灰度发布

### 监控告警
- 实时日志收集（ELK）
- 系统性能监控
- 业务指标监控
- 告警规则配置

---

## 🛠️ 开发指南

### 环境要求
- JDK 17+
- Maven 3.8+
- MySQL 8.0
- Redis 6.0+
- RabbitMQ 3.8+
- Nacos 2.0+

### 启动流程
1. 启动中间件（MySQL、Redis、RabbitMQ、Nacos）
2. 启动server-gateway网关
3. 启动各个微服务模块
4. 启动web应用（web-uav-pilot、web-customer、web-mgr）

### 配置说明
所有服务配置通过Nacos配置中心管理，配置文件位于`资料/DEFAULT_GROUP/`目录。

---

## 📝 项目演进

本项目从成熟的**代驾平台**演进而来，通过以下改造成为无人机调度平台：

- ✅ 核心业务流程复用（发布任务 → 接单 → 执行 → 结算）
- ✅ 角色变更（司机→飞手，乘客→运营商）
- ✅ 技术栈保持一致（Spring Cloud微服务体系）
- ✅ 80%代码逻辑复用

---

## 📄 许可证

该项目为教学用途
