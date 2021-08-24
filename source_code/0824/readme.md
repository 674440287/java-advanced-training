# 启动步骤
1. 下载 ShardingSphere-Proxy 的最新发行版。
2. 如果使用 docker，可以执行 docker pull shardingsphere/shardingsphere-proxy 获取镜像。详细信息请参考Docker镜像。
3. 修改 conf/server.yaml和以 config- 前缀开头的文件，如：conf/config-xxx.yaml 文件，进行分片规则、读写分离规则配置。


# 使用 MySQL
1. 将 MySQL 的 JDBC 驱动程序复制至目录 ext-lib/
2. 使用任何 MySQL 的客户端连接。如: mysql -u root -h 127.0.0.1 -P 3307



# 使用自定义分片算法
1. 当用户需要使用自定义的分片算法类时，无法再通过简单的行表达式在 YAML 文件进行配置。可通过以下方式配置使用自定义分片算法。
2. 实现 ShardingAlgorithm 接口定义的算法实现类。
3. 将上述 Java 文件打包成 jar 包。
4. 将上述 jar 包拷贝至 ShardingSphere-Proxy 解压后的 ext-lib/ 目录。
5. 将上述自定义算法实现类的 Java 文件引用配置在 YAML 文件中，具体可参考配置规则。


# 注意事项
1. ShardingSphere-Proxy 默认使用 3307 端口，可以通过启动脚本追加参数作为启动端口号。如: bin/start.sh 3308
2. ShardingSphere-Proxy 使用 conf/server.yaml 配置注册中心、认证信息以及公用属性。
3. ShardingSphere-Proxy 支持多逻辑数据源，每个以 config- 前缀命名的 YAML 配置文件，即为一个逻辑数据源。


# 数据源配置
配置项说明
```yaml
schemaName: # 逻辑数据源名称

dataSources: # 数据源配置，可配置多个 <data-source-name>
  <data-source-name>: # 与 ShardingSphere-JDBC 配置不同，无需配置数据库连接池
    url: #数据库 URL 连接
    username: # 数据库用户名
    password: # 数据库密码
    connectionTimeoutMilliseconds: # 连接超时毫秒数
    idleTimeoutMilliseconds: # 空闲连接回收超时毫秒数
    maxLifetimeMilliseconds: # 连接最大存活时间毫秒数
    maxPoolSize: 50 # 最大连接数
    minPoolSize: 1  # 最小连接数     

rules: # 与 ShardingSphere-JDBC 配置一致
  # ...
```

# 权限配置
用于执行登录 Sharding Proxy 的权限验证。 配置用户名、密码、可访问的数据库后，必须使用正确的用户名、密码才可登录。
```yaml
rules:
  - !AUTHORITY
    users:
      - root@localhost:root  # <username>@<hostname>:<password>
      - sharding@:sharding
    provider:
      type: NATIVE
```

hostname 为 % 或空字符串，则代表不限制 host。

provider 的 type 必须显式指定，具体实现可以参考 5.11 Proxy


# YAML 语法说明
!! 表示实例化该类

! 表示自定义别名

- 表示可以包含一个或多个

[] 表示数组，可以与减号相互替换使用
