package dao;

import bean.Blog;
import bean.Message;

import java.sql.*;
import java.util.ArrayList;

public class GetBlog {
    public ArrayList getBlog(String username){
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
            String sql="select * from blog where author = ?";
            PreparedStatement presta = conn.prepareStatement(sql);
            presta.setString(1, username);
            res=presta.executeQuery();

            while(res.next()) {
               Blog b=new Blog();
               b.setTitle(res.getString("title"));
               b.setContent(res.getString("content"));
               b.setTime(res.getString("sendtime"));
               b.setAuthor(username);
               b.setLabel(res.getString("label"));
               b.setCategory(res.getString("category"));
               b.setOrigin(res.getString("origin"));
               b.setId(res.getString("id"));
               b.setLabel2(res.getString("label2"));
               b.setLabel3(res.getString("label3"));
               b.setAuthor(res.getString("author"));
               b.setStatement(res.getInt("statement"));
               if(b.getStatement()==1)
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
