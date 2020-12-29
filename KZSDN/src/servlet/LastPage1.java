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

@WebServlet("/lastpage1")
public class LastPage1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        ArrayList<Blog> blogs=new GetBlog().getBlog(username);
        ArrayList<Blog> blog=new ArrayList<>();
        int page=(int)req.getSession().getAttribute("page");
        if(page==0)
        {
            req.getRequestDispatcher("/BlogList1.jsp").forward(req,resp);
        }
        else
        {
            page--;
            for(int j=0,i=page*5;i<page*5+5&&i<blogs.size();j++,i++)
            {
                Blog b=blogs.get(i);
                blog.add(b);
            }
            req.getSession().setAttribute("blogss",blog);
            req.getSession().setAttribute("page",page);
            req.getRequestDispatcher("/BlogList1.jsp").forward(req,resp);
        }
    }
}
