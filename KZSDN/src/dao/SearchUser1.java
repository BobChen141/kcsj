package dao;

import bean.Blog;
import bean.User;

import java.sql.*;
import java.util.ArrayList;

public class SearchUser1 {
    public ArrayList searchUser(String text){
        Connection conn = null;
        ResultSet res = null;
        try {
            ArrayList<User>users=new ArrayList<>();
            boolean f=false;
            String user = "root";
            String password = "dpprz.777";
            String Driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/cksdn?serverTimezone=GMT";
            //注册驱动
            Class.forName(Driver);
            //获取连接
            conn = DriverManager.getConnection(url, user, password);
            String sql="select * from admin where username like ?";
            PreparedStatement presta = conn.prepareStatement(sql);
            presta.setString(1,"%"+text+"%");
            res=presta.executeQuery();
            if(res.next()) {
                User user1=new User();
                user1.setEmail(res.getString("email"));
                //user1.setPassword(res.getString("password"));
                user1.setBirth(res.getString("birth"));
                user1.setGender(res.getString("gender"));
                user1.setName(res.getString("name"));
                user1.setPhone(res.getString("phone"));
                user1.setIntroduction(res.getString("introduction"));
                user1.setTouxiang(res.getString("touxiang"));
                user1.setUsername(res.getString("username"));
                users.add(user1);
            }
            return users;
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
