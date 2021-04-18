package com.demo.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//启动H2testAppliction 可创建H2数据库
public class JDBCtest {

    private static Connection conn = null;
    private static PreparedStatement statement = null;

    public static void main(String[] args) throws SQLException {
        try {
            //1. 获得数据库连接
            conn = DriverManager.getConnection("jdbc:h2:~/test");
            //3.操作数据库，实现增删改查
            statement = null;
            add("ma", "bo");
            query();
            update("ma1", "ma");
            preQuery("ma");
            updateRollBack("ma2", "ma");
            preQuery("ma");
            List<String> persons = new ArrayList<>();
            persons.add("batchFirstName");
            persons.add("batchLastName");
            persons.add("batchTwoName");
            persons.add("batchTwoName");
            addBatch(persons);
            query();
            delete();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            statement.close();
            conn.close();
        }

    }

    public static void add(String first_name, String last_name) throws SQLException {
        //3.1 增
        statement = conn.prepareStatement("INSERT INTO customers(first_name, last_name) VALUES (?,?)");
        statement.setObject(1, first_name);
        statement.setObject(2, last_name);
        statement.execute();
    }

    public static void addBatch(List<String> persons) throws SQLException {
        //批量新增
        statement = conn.prepareStatement("INSERT INTO customers(first_name, last_name) VALUES (?,?)");
        statement.setString(1, persons.get(0));
        statement.setString(2, persons.get(1));
        statement.addBatch();
        statement.setString(1, persons.get(2));
        statement.setString(2, persons.get(3));
        statement.addBatch();
        statement.executeBatch();
    }

    public static void query() throws SQLException {
        //3.2 查
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, first_name, last_name FROM customers");
        System.out.println("查询结果：");
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            System.out.print("id: " + id);
            System.out.print(", first_name: " + firstName);
            System.out.println(", last_name: " + lastName);


        }
        statement.close();
    }

    public static void preQuery(String first_name) throws SQLException {
        //3.2.1 查
        statement = conn.prepareStatement("SELECT id, first_name, last_name FROM customers WHERE first_name = ?");
        statement.setString(1, first_name);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            System.out.print("id: " + id);
            System.out.print(", first_name: " + firstName);
            System.out.println(", last_name: " + lastName);
        }
    }

    public static void delete() throws SQLException {
        //3.3 删除
        statement = conn.prepareStatement("delete from customers");
        statement.execute();
        System.out.println("清空customers表");
    }

    public static void update(String last_name, String first_name) throws SQLException {
        //3.4 修改
        System.out.println("更新last_name to " + last_name);
        statement = conn.prepareStatement("update customers set last_name = ? where first_name= ?");
        statement.setObject(1, last_name);
        statement.setObject(2, first_name);
        statement.execute();
    }

    public static void updateRollBack(String last_name, String first_name) throws SQLException {
        //3.4 修改
        try {
            System.out.println("更新last_name to " + last_name);
            statement = conn.prepareStatement("update customers set last_name = ? where first_name= ?");
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
