package dao;

import bean.Message;

import java.sql.*;

public class CheckCollect {
    public boolean checkCollect(String username,String blogid) {
        Connection conn = null;
        ResultSet res = null;
        try {
            boolean f=false;
            String user = "root";
            String password = "dpprz.777";
            String Driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/cksdn?serverTimezone=GMT";
            //注册驱动
            Class.forName(Driver);
            //获取连接
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select * from collection where username = ? and blogid = ?";
            PreparedStatement presta = conn.prepareStatement(sql);
            presta.setString(1, username);
            presta.setString(2, blogid);
            res = presta.executeQuery();

            // System.out.println(res.next()+"hhh");
            return res.next();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
