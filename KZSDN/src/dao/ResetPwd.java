package dao;

import bean.Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class ResetPwd {
    public boolean resetPwd(Message msg) throws Exception{
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
        String sql ="update admin set password = '"+msg.getPassword()+"' where username='"+msg.getUsername()+"'";
        PreparedStatement statement=connection.prepareStatement(sql);
        int update=statement.executeUpdate();
        if(update==1)
            flag=true;
    return  flag;
    }
}
