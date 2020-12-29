package servlet;

import bean.Blog;
import dao.GetBlog;
import dao.GetCategorys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/userbloglist")
public class UserBlogList extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("getusername");
        String checkstr="sb";
        ArrayList<String>categorys=new ArrayList<>();
        categorys=new GetCategorys().getCategory(username);
        ArrayList<Blog> blogs=new ArrayList<>();
        ArrayList<Blog> blogs2=new ArrayList<>();
        ArrayList<Blog> blogs3=new ArrayList<>();
        ArrayList<Blog> blogs4=new ArrayList<>();
        ArrayList<Blog> blogs5=new ArrayList<>();
        blogs=new GetBlog().getBlog(username);
        ArrayList<Blog> blog=new ArrayList<>();
        for(int i=0;i<5&&i<blogs.size();i++)
        {
            Blog b=blogs.get(i);
            blog.add(b);
        }
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
        req.getSession().setAttribute("blog",blog);
        req.getSession().setAttribute("blogs",blogs);
        req.getSession().setAttribute("blogs2",blogs2);
        req.getSession().setAttribute("blogs3",blogs3);
        req.getSession().setAttribute("blogs4",blogs4);
        req.getSession().setAttribute("blogs5",blogs5);
        req.getSession().setAttribute("checkstr",checkstr);
        int page=0;
        req.getSession().setAttribute("page",page);
        req.getSession().setAttribute("categorys",categorys);
        req.getRequestDispatcher("/BlogList1.jsp").forward(req,resp);
    }
}
