# mysql
## 设置mysql 可以 从任意ip访问
grant all privileges  on *.* to root@'%';

# 访问 sharding proxy 
mysql -h127.0.0.1 -P3307 -uroot -proot -A


# 插入数据
insert into sharding_db.t_order values(1,'1'), (2,'2'), (3,'3');

select * from sharding_db.t_order;
