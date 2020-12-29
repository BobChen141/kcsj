package servlet;

import dao.CDeleteBlog1;
import dao.DeleteBlog1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cdeleteblog")
public class CDeleteBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogid=req.getParameter("delete_id");
        boolean f=new CDeleteBlog1().deleteBlog(blogid);
        if(f){
            req.getRequestDispatcher("/Menu.jsp").forward(req,resp);
        }
    }
}
