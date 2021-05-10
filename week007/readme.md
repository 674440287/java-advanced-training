2.（必做）按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
```

# 10000 id

单个创建
statementCreate
39594 ms
preparedStatementCreate
41393 ms
批量创建
createBatch
27747 ms

# 100000 id
单个创建
statementCreate
399708 ms
preparedStatementCreate
400264 ms
批量创建
createBatch
279611 ms
```
9.（必做）读写分离 - 动态切换数据源版本 1.0
homework-9
10.（必做）读写分离 - 数据库框架版本 2.0
homework-10
