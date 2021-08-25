# 二进制运行
git clone https://github.com/apache/shardingsphere-ui.git；   
运行 mvn clean install -Prelease；            
获取安装包 /shardingsphere-ui/shardingsphere-ui-distribution/target/apache-shardingsphere-${latest.release.version}-shardingsphere-ui-bin.tar.gz；       
解压缩后运行bin/start.sh；         
访问http://localhost:8088/。                      


# 配置
ShardingSphere-UI 的配置文件为 conf/application.properties, 它由两部分组成。

程序监听端口；
登录身份验证信息。

```
server.port=8088

user.admin.username=admin
user.admin.password=admin
```


# 注册中心配置
首先需要添加并激活注册中心。可以添加多个注册中心，但只能有一个处于激活状态，后面的运行状态功能都是针对当前已激活的注册中心进行操作。 目前提供 Zookeeper 和 etcd 的支持，后续会添加第三方注册中心的支持。

点击 + 按钮可以添加新注册中心。

通过配置扩展配置中心来使用其它配置中心管理配置。

支持编辑、激活和删除注册中心操作。


# 规则配置
添加激活注册中心后，可以获取当前注册中心中所有数据源的相关配置，包括数据分片，读写分离、Properties 配置等。

可以通过 YAML 格式对相关配置信息进行修改。

点击 + 按钮可以添加新的数据源和分片规则。


# 运行状态
添加激活注册中心后，可以查看当前注册中心所有运行实例信息。     

可以通过操作按钮对运行实例进行熔断与恢复操作。      

可以查看所有从库信息，并进行从库禁用与恢复操作。          
