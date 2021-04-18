package com.demo.jdbc;

import com.demo.jdbc.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLTest {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";

    private final Connection conn;

    public MySQLTest() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        this.conn= DriverManager.getConnection(DB_URL,USER,PASS);
    }

    public void close() throws SQLException {
        if(conn!=null){
            conn.close();
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySQLTest mySQLTest = new MySQLTest();
        // 单个创建
        mySQLTest.create("firstName1","lastName1");

        // 批量创建
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().firstName("firstName2").lastName("lastName2").build());
        customers.add(Customer.builder().firstName("firstName3").lastName("lastName3").build());
        mySQLTest.createBatch(customers);

        System.out.println(mySQLTest.query("firstName1"));
        // 关闭链接
        mySQLTest.close();
    }

    /**
     * 单条更新
     * @param first_name
     * @param last_name
     * @throws SQLException
     */
    public void create(String first_name, String last_name) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO customers(first_name, last_name) VALUES (?,?)");
        statement.setObject(1, first_name);
        statement.setObject(2, last_name);
        statement.execute();
    }

    /**
     * 批量更新
     * @param customers
     * @throws SQLException
     */
    public void createBatch(List<Customer> customers) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO customers(first_name, last_name) VALUES (?,?)");
        for (Customer customer:customers){
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.addBatch();
        }
        statement.executeBatch();
    }

    public List<Customer> query(String first_name) throws SQLException {
        List<Customer> customers = new ArrayList<>();

        //3.2.1 查
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
