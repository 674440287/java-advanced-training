# ShardingSphere 源码结构
ShardingSphere当前master为5.0.0-beta版本，跟之前alpha和4.x源码结构差别非常大。
一级目录
shardingsphere % tree -L 1
├── shardingsphere-agent            1星，对接APM的，不用管
├── shardingsphere-db-protocol   2星，Proxy使用的数据库协议，不用管
├── shardingsphere-distribution     1星，打包发布用的，不用管
├── shardingsphere-distsql-parser 2星，新加的创建数据源和规则，不用管
├── shardingsphere-features          5星重点，常用功能在这里
├── shardingsphere-governance     3星，治理，写zk，并且给UI用的
├── shardingsphere-infra               5星重点，引擎内核
├── shardingsphere-jdbc               4星重点，JDBC入口
├── shardingsphere-proxy             4星重点，Proxy入口
├── shardingsphere-scaling           2星，数据迁移的，不用管
├── shardingsphere-sql-parser      2星，Antlr3 SQL解析的，不用管
├── shardingsphere-test               1星，主要是SQL测试和集成测试，不用管
├── shardingsphere-transaction     3星，主要是XA和Seata集成，不用管

二级目录
shardingsphere-infra % tree -L 1
├── shardingsphere-infra-authority   2星，proxy权限配置
├── shardingsphere-infra-binder      1星，sql解析结果转换
├── shardingsphere-infra-common   4星，重要的基本对象和工具
├── shardingsphere-infra-context     2星，封装的上下文集合
├── shardingsphere-infra-datetime   1星，时间服务
├── shardingsphere-infra-executor    5星重点，引擎-执行引擎
├── shardingsphere-infra-merge       5星重点，引擎-归并引擎
├── shardingsphere-infra-optimize    2星，引擎-优化引擎，新加的
├── shardingsphere-infra-parser       5星重点，引擎-解析引擎
├── shardingsphere-infra-rewrite      5星重点，引擎-改写引擎
├── shardingsphere-infra-route        5星重点，引擎-路由引擎

shardingsphere-features % tree -L 1
├── shardingsphere-db-discovery             3星，基于MGR的主从切换，新加的
├── shardingsphere-encrypt                     2星，加密解密
├── shardingsphere-readwrite-splitting     5星重点，读写分离
├── shardingsphere-shadow                    2星，影子库
├── shardingsphere-sharding                   5星重点，分库分表功能

三级目录
shardingsphere-sharding % tree -L 1
├── shardingsphere-sharding-api         3星，api和spi
├── shardingsphere-sharding-core       5星重点，分库分表功能细节
├── shardingsphere-sharding-distsql    2星，新加的
├── shardingsphere-sharding-spring     3星，spring入口，集成spring和spring boot
