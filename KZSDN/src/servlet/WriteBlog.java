package servlet;

import bean.Blog;
import dao.AddBlog;
import utils.GetID;
import utils.GetTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/writeblog")
public class WriteBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        Blog blog=new Blog();
        String id=GetID.main(null);
        blog.setId(id);
        String time=GetTime.getTime();
        blog.setTime(time);
        blog.setTitle(req.getParameter("ttle"));
        blog.setContent(req.getParameter("my-editormd-markdown-doc"));
        blog.setAuthor(req.getParameter("username"));
        blog.setLabel(req.getParameter("label"));
        blog.setCategory(req.getParameter("category"));
        blog.setOrigin(req.getParameter("origin"));
        blog.setCode(req.getParameter("my-editormd-html-code"));
        blog.setLabel2(req.getParameter("label2"));
        blog.setLabel3(req.getParameter("label3"));
        blog.setStatement(0);
        try {
            boolean f=new AddBlog().addBlog(blog);
            if(f) {
                req.getSession().setAttribute("blog", blog);
                req.getRequestDispatcher("/ReadBlog.jsp").forward(req,resp);
            }
            else
            {
                req.setAttribute("error","发布错误，请重新发布！");
                req.getRequestDispatcher("Blog/.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
