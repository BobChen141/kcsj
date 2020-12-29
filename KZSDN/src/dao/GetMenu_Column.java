package dao;

import bean.Blog;

import java.sql.*;
import java.util.ArrayList;

public class GetMenu_Column {
    public ArrayList getColumn(){
        Connection conn = null;
        ResultSet res = null;
        try {
            ArrayList<String> columns=new ArrayList<>();
            boolean f=false;
            String user = "root";
            String password = "dpprz.777";
            String Driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/cksdn?serverTimezone=GMT";
            //注册驱动
            Class.forName(Driver);
            //获取连接
            conn = DriverManager.getConnection(url, user, password);
            String sql="select * from menu_columns ";
            PreparedStatement presta = conn.prepareStatement(sql);
            res=presta.executeQuery();

            while(res.next()) {
                String s=res.getString("category");
                columns.add(s);
            }
            return columns;
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
