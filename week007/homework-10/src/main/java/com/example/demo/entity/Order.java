package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class Order {

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
