package servlet;

import bean.Blog;
import dao.GetBlog;
import dao.ResetBlog;
import utils.GetTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/mblog")
public class Mblog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        Blog blog=new Blog();
        blog.setId(req.getParameter("id"));
        blog.setTitle(req.getParameter("ttle"));
        blog.setContent(req.getParameter("my-editormd-markdown-doc"));
        blog.setCode(req.getParameter("my-editormd-html-code"));
        blog.setLabel(req.getParameter("label"));
        blog.setCategory(req.getParameter("category"));
        blog.setOrigin(req.getParameter("origin"));
        blog.setLabel2(req.getParameter("label2"));
        blog.setLabel3(req.getParameter("label3"));
        blog.setStatement(0);
        String time=GetTime.getTime();
        blog.setTime(time);
        try {
            boolean f=new ResetBlog().resetBlog(blog);
            if(f)
            {
                String username=(String)req.getSession().getAttribute("username");
                ArrayList<Blog> blogs=new ArrayList<>();
                ArrayList<Blog> blogs2=new ArrayList<>();
                ArrayList<Blog> blogs3=new ArrayList<>();
                ArrayList<Blog> blogs4=new ArrayList<>();
                ArrayList<Blog> blogs5=new ArrayList<>();
                blogs=new GetBlog().getBlog(username);
                for(int i=0;i<blogs.size();i++)
                {
                    if(blogs.get(i).getCategory().equals("算法"))
                    {
                        Blog bb=new Blog();
                        bb=blogs.get(i);
                        blogs2.add(bb);
                    }
                    else
                    {
                        Blog bb=new Blog();
                        bb=blogs.get(i);
                        blogs3.add(bb);
                    }
                    if(blogs.get(i).getOrigin().equals("原创")){
                        Blog bb=new Blog();
                        bb=blogs.get(i);
                        blogs4.add(bb);
                    }
                    else
                    {
                        Blog bb=new Blog();
                        bb=blogs.get(i);
                        blogs5.add(bb);
                    }
                }
                req.getSession().setAttribute("blogs",blogs);
                req.getSession().setAttribute("blogs2",blogs2);
                req.getSession().setAttribute("blogs3",blogs3);
                req.getSession().setAttribute("blogs4",blogs4);
                req.getSession().setAttribute("blogs5",blogs5);
                req.getRequestDispatcher("/BlogList.jsp").forward(req,resp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
