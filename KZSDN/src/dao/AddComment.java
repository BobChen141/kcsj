package dao;

import bean.Blog;
import bean.Comments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class AddComment {
    public boolean addcomment(Comments cmt) throws Exception{

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
        String sql="INSERT INTO comment(comment,user,blogid,sendtime,rate,commentid) VALUES(?,?,?,?,?,?)";

        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,cmt.getComment());
        statement.setString(2,cmt.getUsername());
        statement.setString(3,cmt.getBlogid());
        statement.setString(4,cmt.getTime());
        statement.setInt(5,cmt.getRate());
        statement.setString(6,cmt.getCommentid());
        //statement.setString(2,msg.getEmail());
        //执行sql命令
        int update=statement.executeUpdate();
        if(update==1)
            flag=true;
        return flag;
    }
}
