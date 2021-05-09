DROP TABLE IF EXISTS `t_user_info`;

CREATE TABLE IF NOT EXISTS `t_user_info`(

   `user_id` int  NOT NULL,
   `user_name` VARCHAR(100)  NOT NULL,
   `user_password` VARCHAR(100)  NOT NULL,
   `id_card` VARCHAR(100)  NOT NULL COMMENT '身份证ID',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY ( `user_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_product_info`;

CREATE TABLE IF NOT EXISTS `t_product_info`(
   `product_id` int NOT NULL,
   `product_name` VARCHAR(100)  NOT NULL,
   `product_desc` VARCHAR(255)  NOT NULL,
   `product_price` decimal(10,2)  NOT NULL,
   `product_discount` decimal(10,2)   NOT NULL,
   `user_id` int  NOT NULL,
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, 
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY ( `product_id` )
   
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_order_info`;

CREATE TABLE IF NOT EXISTS `t_order_info`(
   `order_id` bigint   NOT NULL,
   `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
   `order_desc` VARCHAR(255)  NOT NULL,
   `user_id` int NOT NULL,
   `product_id`  int  NOT NULL,
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY ( `order_id` )
   
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
