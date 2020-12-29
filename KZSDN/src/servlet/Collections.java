package servlet;

import bean.Blog;
import dao.GetBlog1;
import dao.GetCollections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/collections")
public class Collections extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Blog> cblogs=new ArrayList<>();
        String username=req.getParameter("username1");
        cblogs=new GetCollections().getCollections(username);
        for(int i=0;i<cblogs.size();i++)
        {
           Blog b=new Blog();
           b.setId(cblogs.get(i).getId());
           b=new GetBlog1().getBlog(b);
           cblogs.set(i,b);
        }
        req.getSession().setAttribute("cblogs",cblogs);
        req.getRequestDispatcher("/CollectionList.jsp").forward(req, resp);
    }
}
