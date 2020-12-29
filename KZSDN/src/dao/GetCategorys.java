package dao;

import bean.Blog;
import bean.Category;

import java.sql.*;
import java.util.ArrayList;

public class GetCategorys {
    public ArrayList getCategory(String username){
        Connection conn = null;
        ResultSet res = null;
        try {
            ArrayList<Category> columns=new ArrayList<>();
            boolean f=false;
            String user = "root";
            String password = "dpprz.777";
            String Driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/cksdn?serverTimezone=GMT";
            //注册驱动
            Class.forName(Driver);
            //获取连接
            conn = DriverManager.getConnection(url, user, password);
            String sql="select * from `column` where username = ?";
            PreparedStatement presta = conn.prepareStatement(sql);
            presta.setString(1, username);
            res=presta.executeQuery();

            while(res.next()) {
                Category c=new Category();
                c.setCategory(res.getString("category"));
                c.setId(res.getString("id"));
                c.setUsername(res.getString("username"));
                columns.add(c);
            }
            return columns;
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
