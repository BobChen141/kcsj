package dao;

import bean.Comments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class AddCollection {
    public boolean addCollection(String username,String blogid) throws Exception{

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
        String sql="INSERT INTO collection(username,blogid) VALUES(?,?)";

        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,username);
        statement.setString(2,blogid);

        //statement.setString(2,msg.getEmail());
        //执行sql命令
        int update=statement.executeUpdate();
        if(update==1)
            flag=true;
        return flag;
    }
}
