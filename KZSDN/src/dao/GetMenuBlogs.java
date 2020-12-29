package dao;

import bean.Blog;

import java.sql.*;
import java.util.ArrayList;

public class GetMenuBlogs {
    public ArrayList getBlog(){
        Connection conn = null;
        ResultSet res = null;
        try {
            ArrayList<Blog> blog=new ArrayList<>();
            boolean f=false;
            String user = "root";
            String password = "dpprz.777";
            String Driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/cksdn?serverTimezone=GMT";
            //注册驱动
            Class.forName(Driver);
            //获取连接
            conn = DriverManager.getConnection(url, user, password);
            String sql="select * from menu ";
            PreparedStatement presta = conn.prepareStatement(sql);
            res=presta.executeQuery();

            while(res.next()) {
                Blog b=new Blog();
                b.setId(res.getString("blogid"));
                blog.add(b);
            }
            return blog;
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
