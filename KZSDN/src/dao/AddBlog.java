package dao;

import bean.Blog;
import bean.Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class AddBlog {
    public boolean addBlog(Blog blog) throws Exception{

        boolean flag=false;
        //连接数据库
        Properties info=new Properties();
        String user = "root";
        String password = "dpprz.777";
        String Driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/cksdn?serverTimezone=GMT";
        //注册驱动
        Class.forName(Driver);
        //获取连接
        Connection connection= DriverManager.getConnection(url,user,password);
        //
        String sql="INSERT INTO blog(title,content,sendtime,author,label,category,id,origin,code,label2,label3,statement) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,blog.getTitle());
        statement.setString(2,blog.getContent());
        statement.setString(3,blog.getTime());
        statement.setString(4,blog.getAuthor());
        statement.setString(5,blog.getLabel());
        statement.setString(6,blog.getCategory());
        statement.setString(7,blog.getId());
        statement.setString(8,blog.getOrigin());
        statement.setString(9,blog.getCode());
        statement.setString(10,blog.getLabel2());
        statement.setString(11,blog.getLabel3());
        statement.setInt(12,blog.getStatement());
        //statement.setString(2,msg.getEmail());

        //执行sql命令
        int update=statement.executeUpdate();
        if(update==1)
            flag=true;
        return flag;
    }
}
