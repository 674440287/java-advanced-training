package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class Order {
//CREATE TABLE IF NOT EXISTS `t_order_info`(
//   `order_id` int   NOT NULL,
//   `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
//  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
//  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
//   `order_desc` VARCHAR(255)  NOT NULL,
//   `user_id` int NOT NULL,
//   `product_id`  int  NOT NULL,
//   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
//    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
//   PRIMARY KEY ( `order_id` )
//
//)ENGINE=InnoDB DEFAULT CHARSET=utf8;

    private int orderId;
    private String orderSn;
    private BigDecimal totalAmount;
    private BigDecimal payAmount;
    private String orderDesc;
    private int userId;
    private int productId;
    private long createTime;
    private long updateTime;

}
