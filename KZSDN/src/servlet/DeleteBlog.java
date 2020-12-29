package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.CDeleteBlog1;
import dao.DeleteBlog1;

@WebServlet("/deleteblog")
public class DeleteBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogid=req.getParameter("delete_id");
        boolean f=new DeleteBlog1().deleteBlog(blogid);
        boolean f1=new CDeleteBlog1().deleteBlog(blogid);
        if(f){
            req.getRequestDispatcher("/Menu.jsp").forward(req,resp);
        }
    }
}
