package servlet;

import bean.Blog;
import bean.Comments;
import dao.GetBlog1;
import dao.GetComments;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/checkexamblog")
public class CheckExamBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Blog blog=new Blog();
        blog.setId(req.getParameter("scheck_id"));
        blog=new GetBlog1().getBlog(blog);
        ArrayList<Comments> comments=new ArrayList<>();
        comments=new GetComments().getComments(blog.getId());
        req.getSession().setAttribute("comments",comments);
        req.getSession().setAttribute("blog", blog);
        req.getRequestDispatcher("/ReadBlog.jsp").forward(req,resp);
    }
}
