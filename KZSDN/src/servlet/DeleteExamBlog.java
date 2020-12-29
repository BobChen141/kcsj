package servlet;

import bean.Blog;
import dao.CDeleteBlog1;
import dao.DeleteBlog1;
import dao.GetBlogs;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/deleteexamblog")
public class DeleteExamBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogid=req.getParameter("sdblogid");
        boolean f=new DeleteBlog1().deleteBlog(blogid);
        if(f)
        {
            boolean f1=new CDeleteBlog1().deleteBlog(blogid);
            ArrayList<Blog> mblogs=new ArrayList<>();
            mblogs=new GetBlogs().getBlog();
            req.getSession().setAttribute("manageblogs",mblogs);
            req.getRequestDispatcher("/Manage.jsp").forward(req,resp);
        }
    }
}
