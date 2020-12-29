package servlet;

import bean.Blog;
import dao.GetBlog1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modifyblog")
public class ModifyBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Blog blog=new Blog();
        blog.setId(req.getParameter("modify_id"));
        blog=new GetBlog1().getBlog(blog);
        req.getSession().setAttribute("mblog",blog);
        req.getRequestDispatcher("/ModifyBlog.jsp").forward(req,resp);
    }
}
