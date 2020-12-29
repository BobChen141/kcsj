package dao;

import bean.Message;

import java.sql.*;

public class GetEmail {
    public String getEmail(Message msg){
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
            String sql="select * from admin where username = ?";
            PreparedStatement presta = conn.prepareStatement(sql);
            presta.setString(1, msg.getUsername());
            res=presta.executeQuery();
            if(res.next()) {
                String email= res.getString("email");
                return email;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
