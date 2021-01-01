package com.shop.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    static Properties prop;


    static {

        prop = new Properties();

        InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");

        try {
            prop.load(is);

            String driver = prop.getProperty("sqlserver_driver");

            Class.forName(driver);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //打开连接的方法
    public static Connection openConnection() {

        Connection conn = null;

        String url = prop.getProperty("sqlserver_url");
        String user = prop.getProperty("sqlserver_user");
        String pwd = prop.getProperty("sqlserver_pwd");


        try {
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
}
