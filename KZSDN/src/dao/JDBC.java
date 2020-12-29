package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
    public static void main(String[] args)  {
        String url = "jdbc:mysql://127.0.0.1:3306/cksdn?serverTimezone=GMT";
        String user = "root";
        String password = "dpprz.777";
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //建立和数据库的连接
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println(conn);
            conn.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
