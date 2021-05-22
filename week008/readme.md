# mysql
grant all privileges  on *.* to root@'%';

mysql -h127.0.0.1 -P3307 -uroot -proot -A

insert into sharding_db.t_order values(1,'1');
