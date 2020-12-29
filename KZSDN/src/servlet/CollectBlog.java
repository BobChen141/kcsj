package servlet;

import bean.Blog;
import dao.AddCollection;
import dao.CheckCollect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/collectblog")
public class CollectBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Blog b=new Blog();
        b=(Blog)req.getSession().getAttribute("blog");
        String blogid=b.getId();
        String username=req.getParameter("username");
        try {
            boolean flag=new CheckCollect().checkCollect(username,blogid);
            if(flag==false) {
                boolean f = new AddCollection().addCollection(username, blogid);
                if (f) {

                }
            }
            else
            {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // req.getRequestDispatcher("/Menu.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogid=req.getParameter("collect_id");
        String username=req.getParameter("username");
        System.out.println(username+blogid);
        System.out.println("hhhhhh");
        try {
            boolean flag=new CheckCollect().checkCollect(username,blogid);
            if(flag==false) {
                boolean f = new AddCollection().addCollection(username, blogid);
                if (f) {

                }
            }
            else
            {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // req.getRequestDispatcher("/Menu.jsp").forward(req,resp);
    }
}
