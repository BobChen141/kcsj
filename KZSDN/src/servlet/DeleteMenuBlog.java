package servlet;

import dao.DeleteMenu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deletemenublog")
public class DeleteMenuBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogid=req.getParameter("syblogid1");
        boolean f=new DeleteMenu().deleteBlog(blogid);
        if(f)
        {
            req.getRequestDispatcher("/Manage.jsp").forward(req,resp);
        }
    }
}
