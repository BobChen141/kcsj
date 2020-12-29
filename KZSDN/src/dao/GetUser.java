package dao;

import bean.User;

import java.sql.*;

public class GetUser {
    public User getUser(User user1) {
        Connection conn = null;
        ResultSet res = null;
        try {
            boolean f = false;
            String user = "root";
            String password = "dpprz.777";
            String Driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/cksdn?serverTimezone=GMT";
            //注册驱动
            Class.forName(Driver);
            //获取连接
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select * from admin where username = ?";
            PreparedStatement presta = conn.prepareStatement(sql);
            presta.setString(1, user1.getUsername());
            res = presta.executeQuery();
            if (res.next()) {
                user1.setEmail(res.getString("email"));
                user1.setPassword(res.getString("password"));
                user1.setBirth(res.getString("birth"));
                user1.setGender(res.getString("gender"));
                user1.setName(res.getString("name"));
                user1.setPhone(res.getString("phone"));
                user1.setIntroduction(res.getString("introduction"));
                user1.setTouxiang(res.getString("touxiang"));
                return user1;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user1;
    }
}
