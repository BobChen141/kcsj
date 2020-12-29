package dao;

import java.sql.*;

public class CheckMenu {
    public boolean checkMenu(String blogid) {
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
            String sql = "select * from menu where blogid = ?";
            PreparedStatement presta = conn.prepareStatement(sql);
            presta.setString(1, blogid);
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
