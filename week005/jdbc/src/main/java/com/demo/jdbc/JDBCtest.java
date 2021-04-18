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
            update("ma1", "ma");
            updateRollBack("ma2", "ma");
            List<String> persons = new ArrayList<>();
            persons.add("batchFirstName");
            persons.add("batchLastName");
            persons.add("batchTwoName");
            persons.add("batchTwoName");
            delete();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            statement.close();
            conn.close();
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
