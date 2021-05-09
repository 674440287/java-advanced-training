package com.example.demo;



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
        // 单个创建
        mySQLInsert.create("firstName1","lastName1");

        // 批量创建
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().firstName("firstName2").lastName("lastName2").build());
        customers.add(Customer.builder().firstName("firstName3").lastName("lastName3").build());
        mySQLInsert.createBatch(customers);

        // 查询
        System.out.println(mySQLInsert.query("firstName1"));
        // 更新
        mySQLInsert.update("firstName1", "lastName1000");

        // 查询
        System.out.println(mySQLInsert.query("firstName1"));

        // 更新 回滚
        mySQLInsert.updateRollBack("firstName1", "lastName10000");

        // 查询
        System.out.println(mySQLInsert.query("firstName1"));

        // 清空
//        mySQLTest.delete();

        // 关闭链接
        mySQLInsert.close();
    }

    /**
     * 单条插入
     */
    public void create(String first_name, String last_name) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO customers(first_name, last_name) VALUES (?,?)");
        statement.setObject(1, first_name);
        statement.setObject(2, last_name);
        statement.execute();
    }

    /**
     * 批量插入
     * @param customers
     */
    public void createBatch(List<Customer> customers) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO customers(first_name, last_name) VALUES (?,?)");
        for (Customer customer:customers){
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.addBatch();
        }
        statement.executeBatch();
        statement.clearBatch(); //清空批处理
    }

    public List<Customer> query(String first_name) throws SQLException {
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

    public boolean delete() throws SQLException {
        PreparedStatement statement = conn.prepareStatement("truncate table customers");
        return statement.execute();
    }

    public void update(String first_name, String last_name) throws SQLException {
        System.out.println("更新last_name to " + last_name);

        PreparedStatement statement = conn.prepareStatement("update customers set last_name = ? where first_name= ?");
        statement.setObject(1, last_name);
        statement.setObject(2, first_name);
        statement.execute();
    }

    public void updateRollBack(String first_name, String last_name) throws SQLException {
        try {
            conn.setAutoCommit(false); //JDBC中默认是true，我们改成false，然后在后面手动提交
            System.out.println("更新last_name to " + last_name);
            PreparedStatement statement = conn.prepareStatement("update customers set last_name = ? where first_name= ?");
            statement.setObject(1, last_name);
            statement.setObject(2, first_name);
            int i = 1 / 0;
            statement.execute();
            conn.commit();
        } catch (Exception throwables) {
            System.out.println("更新失败");
            conn.rollback();
        }
    }

}
