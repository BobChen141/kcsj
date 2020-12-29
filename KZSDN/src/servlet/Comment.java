package servlet;

import bean.Comments;
import dao.AddComment;
import utils.GetID;
import utils.GetTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/comment")
public class Comment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        Comments cmt=new Comments();
        cmt.setBlogid(req.getParameter("blog_id"));
        cmt.setTime(GetTime.getTime());
        cmt.setUsername(req.getParameter("articleauthor"));
        cmt.setComment(req.getParameter("remarkEditor"));
        String id=GetID.main(null);
        cmt.setCommentid(id);
        cmt.setRate(0);
        try {
            boolean f=new AddComment().addcomment(cmt);
            System.out.println(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
