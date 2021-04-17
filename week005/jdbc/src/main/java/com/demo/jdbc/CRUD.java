package com.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUD {

    // MySQL 8.0 以及以上版本 - JDBC 驱动名及数据库 URL
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASS = "root";

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Connection conn = null;
        PreparedStatement ps1 = null;

        try{
            //加载驱动类
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            conn.setAutoCommit(false); //JDBC中默认是true，我们改成false，然后在后面手动提交

            ps1 = conn.prepareStatement("select * from t_ds_monitor_signal where id = 30000 for update;");
            ps1.execute();
            System.out.println("get lock");
            try {
                Thread.sleep(100000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

            conn.commit();//提交事务
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();

        }finally{
            try{
                if(ps1!=null)
                    ps1.close();
                if(conn!=null)
                    conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}