package com.example.demo;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 大批量写入的优化
 * PreparedStatement 减少 SQL 解析
 * Multiple Values/Add Batch 减少交互
 * Load Data，直接导入
 * 索引和约束问题
 */
public class MySQLInsert {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";

    private final Connection conn;

    public MySQLInsert() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        this.conn= DriverManager.getConnection(DB_URL,USER,PASS);
    }

    public void close() throws SQLException {
        if(conn!=null){
            conn.close();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySQLInsert mySQLInsert = new MySQLInsert();
        Snowflake snowFlake = new Snowflake(2, 3);
        long start;
        int count = 100000;

        List<Order> orders = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            orders.add(Order.builder()
                    .orderId(snowFlake.nextId()).orderSn("23412341412341234").orderDesc("qwerwnisndfa")
                    .productId(123).userId(2323)
                    .totalAmount(new BigDecimal("1.2"))
                    .payAmount(new BigDecimal("1.2"))
                    .build());
        }
        System.out.println("单个创建");
        start = System.currentTimeMillis();
        for (Order order:orders){
            mySQLInsert.statementCreate(order);
        }
        System.out.println("statementCreate");
        System.out.println(System.currentTimeMillis()-start);
        mySQLInsert.delete();

        start = System.currentTimeMillis();
        for (Order order:orders){
            mySQLInsert.preparedStatementCreate(order);
        }
        System.out.println("preparedStatementCreate");
        System.out.println(System.currentTimeMillis()-start);
        mySQLInsert.delete();

        System.out.println("批量创建");
        start = System.currentTimeMillis();
        mySQLInsert.createBatch(orders);
        System.out.println("createBatch");
        System.out.println(System.currentTimeMillis()-start);
        mySQLInsert.delete();

        // 关闭链接
        mySQLInsert.close();
    }

    public boolean statementCreate(Order order) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = String.format("INSERT INTO t_order_info (order_id, order_sn, order_desc, total_amount, pay_amount, user_id, product_id) VALUES (%s, %s, '%s', %s, %s, %s, %s)",
                order.getOrderId(),order.getOrderSn(),order.getOrderDesc(),order.getTotalAmount(), order.getPayAmount(),order.getUserId(), order.getProductId());
        return statement.execute(sql);
    }


    public boolean preparedStatementCreate(Order order) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO t_order_info (order_id, order_sn, order_desc, total_amount, pay_amount, user_id, product_id)  VALUES (?,?,?,?,?,?,?)");
        statement.setLong(1, order.getOrderId());
        statement.setString(2, order.getOrderSn());
        statement.setString(3, order.getOrderDesc());
        statement.setBigDecimal(4, order.getTotalAmount());
        statement.setBigDecimal(5, order.getPayAmount());
        statement.setInt(6,order.getProductId());
        statement.setInt(7,order.getUserId());
        return statement.execute();
    }

    public void createBatch(List<Order> orders) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO t_order_info (order_id, order_sn, order_desc, total_amount, pay_amount, user_id, product_id)  VALUES (?,?,?,?,?,?,?)");
        for (Order order:orders){
            statement.setLong(1, order.getOrderId());
            statement.setString(2, order.getOrderSn());
            statement.setString(3, order.getOrderDesc());
            statement.setBigDecimal(4, order.getTotalAmount());
            statement.setBigDecimal(5, order.getPayAmount());
            statement.setInt(6,order.getProductId());
            statement.setInt(7,order.getUserId());
            statement.addBatch();
        }
        statement.executeBatch();
        statement.clearBatch(); //清空批处理
    }

    public boolean delete() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("truncate table t_order_info;");
        return statement.execute();
    }

}
