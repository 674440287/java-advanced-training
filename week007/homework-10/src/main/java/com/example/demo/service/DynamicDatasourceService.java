package com.example.demo.service;

import com.example.demo.entity.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicDatasourceService {

    @Resource
    private DataSource dataSource;

    public boolean create(String user_id, String status) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement statement = conn.prepareStatement("INSERT INTO t_order_0(user_id,status) values (?,?)");
        statement.setObject(1, user_id);
        statement.setObject(2, status);
        return statement.execute();
    }


    public List<Order> query(String user_id) throws SQLException {
        Connection conn = dataSource.getConnection();
        List<Order> orders = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement("SELECT order_id, user_id, status FROM t_order_0 WHERE user_id = ?");
        statement.setString(1, user_id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            orders.add(Order.builder()
                    .orderId(resultSet.getLong("order_id"))
                    .userId(resultSet.getLong("user_id"))
                    .status(resultSet.getString("status"))
                    .build());
        }
        return orders;
    }

}
