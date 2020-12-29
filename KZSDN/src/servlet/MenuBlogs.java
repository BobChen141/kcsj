package servlet;

import dao.AddMenuBlog;
import dao.CheckMenu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/menublogs")
public class MenuBlogs extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogid=req.getParameter("syblogid");
        boolean f=new CheckMenu().checkMenu(blogid);
        if(!f)
        {
            try {
                boolean f1=new AddMenuBlog().addBlog(blogid);
                if(f1) {
                    req.getRequestDispatcher("/Manage.jsp").forward(req, resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
