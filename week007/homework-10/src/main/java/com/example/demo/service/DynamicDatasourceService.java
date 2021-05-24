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

    public boolean create(String first_name, String last_name) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement statement = conn.prepareStatement("INSERT INTO t_order_0(user_id,status) values (?,?)");
        statement.setObject(1, first_name);
        statement.setObject(2, last_name);
        return statement.execute();
    }


    public List<Order> query(String first_name) throws SQLException {
        Connection conn = dataSource.getConnection();
        List<Order> orders = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement("SELECT user_id, status FROM t_order_0 WHERE user_id = ?");
        statement.setString(1, first_name);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            orders.add(Order.builder()
                    .userId(resultSet.getLong("user_id"))
                    .status(resultSet.getString("status"))
                    .build());
        }
        return orders;
    }

}
