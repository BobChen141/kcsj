package servlet;

import bean.Blog;
import dao.GetBlog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/showcategory")
public class ShowCategory extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        String str=req.getParameter("clickcategory");
        String checkstr=str;
        String username=(String)req.getSession().getAttribute("username");
        ArrayList<Blog> blogs=new ArrayList<>();
        ArrayList<Blog> blogss=new ArrayList<>();
        blogs=new GetBlog().getBlog(username);
        ArrayList<Blog> blog=new ArrayList<>();
        int page =(int)req.getSession().getAttribute("page");
        if(str.equals("sb"))
        {
            page=0;
            for(int i=0;i<5&&i<blogs.size();i++)
            {

                Blog b=blogs.get(i);
                blog.add(b);
            }
        }
        req.getSession().setAttribute("page",page);
        req.getSession().setAttribute("blogss",blog);
        req.getSession().setAttribute("checkstr",checkstr);
        req.getRequestDispatcher("/BlogList.jsp").forward(req,resp);
    }
}
