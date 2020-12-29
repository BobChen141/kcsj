package dao;

import bean.Blog;
import bean.Comments;

import java.sql.*;
import java.util.ArrayList;

public class GetComments {
    public ArrayList getComments(String blogid){
        Connection conn = null;
        ResultSet res = null;
        try {
            ArrayList<Comments> comments=new ArrayList<>();
            boolean f=false;
            String user = "root";
            String password = "dpprz.777";
            String Driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/cksdn?serverTimezone=GMT";
            //注册驱动
            Class.forName(Driver);
            //获取连接
            conn = DriverManager.getConnection(url, user, password);
            String sql="select * from comment where blogid = ?";
            PreparedStatement presta = conn.prepareStatement(sql);
            presta.setString(1, blogid);
            res=presta.executeQuery();

            while(res.next()) {
                Comments cmt=new Comments();
                cmt.setComment(res.getString("comment"));
                cmt.setUsername(res.getString("user"));
                cmt.setTime(res.getString("sendtime"));
                cmt.setCommentid(res.getString("commentid"));
                cmt.setRate(res.getInt("rate"));
                cmt.setSuperid(res.getString("superid"));
                cmt.setSupername(res.getString("supername"));
                cmt.setBlogid(blogid);
                comments.add(cmt);
            }
            return comments;
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
