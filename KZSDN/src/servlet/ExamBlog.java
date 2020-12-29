package servlet;

import bean.Blog;
import dao.GetBlogs;
import dao.ModifyExam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/examblog")
public class ExamBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogid=req.getParameter("scheck_id2");
        Blog blog=new Blog();
        blog.setId(blogid);
        blog.setStatement(1);
        try {
            boolean f=new ModifyExam().resetBlog(blog);
            if(f)
            {
                ArrayList<Blog> mblogs=new ArrayList<>();
                mblogs=new GetBlogs().getBlog();
                req.getSession().setAttribute("manageblogs",mblogs);
                req.getRequestDispatcher("/Manage.jsp").forward(req,resp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
