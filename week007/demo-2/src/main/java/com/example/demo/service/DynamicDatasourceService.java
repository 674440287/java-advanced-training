package com.example.demo.service;

import com.example.demo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicDatasourceService {

    @Autowired
    @Qualifier("master")
    DataSource masterDataSource;

    @Autowired
    @Qualifier("slave")
    DataSource slaveDataSource;


    public boolean create(String first_name, String last_name) throws SQLException {
        Connection conn = masterDataSource.getConnection();

        PreparedStatement statement = conn.prepareStatement("INSERT INTO customers(first_name, last_name) VALUES (?,?)");
        statement.setObject(1, first_name);
        statement.setObject(2, last_name);
        return statement.execute();
    }


    public List<Customer> query(String first_name) throws SQLException {
        Connection conn = slaveDataSource.getConnection();
        List<Customer> customers = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement("SELECT id, first_name, last_name FROM customers WHERE first_name = ?");
        statement.setString(1, first_name);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            customers.add(Customer.builder()
                    .id(resultSet.getInt("id"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .build());
        }
        return customers;
    }

}
