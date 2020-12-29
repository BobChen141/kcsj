package dao;

import bean.Blog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class AddFavorite {
    public boolean addFavorite(Blog blog) throws ClassNotFoundException, SQLException {
        boolean flag=false;
        Properties info=new Properties();
        String user = "root";
        String password = "dpprz.777";
        String Driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/cksdn?serverTimezone=GMT";
        //注册驱动
        Class.forName(Driver);
        //获取连接
        Connection connection= DriverManager.getConnection(url,user,password);
        String sql ="update blog set favorite = '"+blog.getFavorite()+"' where id='"+blog.getId()+"'";
        PreparedStatement statement=connection.prepareStatement(sql);
        int update=statement.executeUpdate();
        if(update==1)
            flag=true;
        return flag;
    }
}
