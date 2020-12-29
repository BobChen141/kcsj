package dao;

import java.sql.*;

public class DeleteColumn {
    public boolean deleteColumn(String username,String category)
    {
        boolean f=false;
        Connection conn = null;
        ResultSet res = null;
        try {
            String user = "root";
            String password = "dpprz.777";
            String Driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/cksdn?serverTimezone=GMT";
            //注册驱动
            Class.forName(Driver);
            //获取连接
            conn = DriverManager.getConnection(url, user, password);
            String sql="delete from `column` where username= ? and category= ? ";
            PreparedStatement presta = conn.prepareStatement(sql);
            presta.setString(1, username);
            presta.setString(2,category);
            int update=presta.executeUpdate();
            if(update==1)
                f=true;
            return f;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return f;
    }
}
