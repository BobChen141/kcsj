package dao;
import bean.Message;
import bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
public class ResetDat {
    public boolean resetDat(User user1) throws ClassNotFoundException, SQLException {
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
        String sql ="update admin set name = '"+user1.getName()+"', gender='"+user1.getGender()+"',touxiang='"+user1.getTouxiang()+"', birth='"+user1.getBirth()+"', phone='"+user1.getPhone()+"', introduction='"+user1.getIntroduction()+"' where username='"+user1.getUsername()+"'";
        PreparedStatement statement=connection.prepareStatement(sql);
        int update=statement.executeUpdate();
        if(update==1)
            flag=true;
        return flag;
    }
}
