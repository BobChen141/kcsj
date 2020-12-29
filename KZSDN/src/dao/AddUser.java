package dao;

import bean.Message;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddUser {
    public boolean addUser(Message msg) throws Exception{

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
        Connection connection=DriverManager.getConnection(url,user,password);
        //
        String sql="INSERT INTO admin(username,password,email) VALUES(?,?,?)";

        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,msg.getUsername());
        statement.setString(2,msg.getPassword());
        statement.setString(3,msg.getEmail());
        //statement.setString(2,msg.getEmail());

        //执行sql命令
        int update=statement.executeUpdate();
        if(update==1)
            flag=true;
        return flag;
    }
}
