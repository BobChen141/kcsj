package dao;

import bean.Blog;
import bean.User;

import java.sql.*;

public class GetBlog1 {
    public Blog getBlog(Blog blog) {
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
            String sql = "select * from blog where id = ?";
            PreparedStatement presta = conn.prepareStatement(sql);
            presta.setString(1, blog.getId());
            res = presta.executeQuery();
            if (res.next()) {
                blog.setTitle(res.getString("title"));
                blog.setOrigin(res.getString("origin"));
                blog.setTime(res.getString("sendtime"));
                blog.setCategory(res.getString("category"));
                blog.setLabel(res.getString("label"));
                blog.setAuthor(res.getString("author"));
                blog.setContent(res.getString("content"));
                blog.setCode(res.getString("code"));
                blog.setLabel2(res.getString("label2"));
                blog.setLabel3(res.getString("label3"));
                blog.setStatement(res.getInt("statement"));
                blog.setFavorite(res.getInt("favorite"));
                return blog;
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
