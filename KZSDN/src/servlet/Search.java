package servlet;

import bean.Blog;
import dao.GetBlog1;
import dao.SearchBlog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/search")
public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        String text=req.getParameter("searchtext");
        ArrayList<Blog>menublogs=new ArrayList<>();
        menublogs=new SearchBlog().searchBlog(text);
        req.setAttribute("tcontent",text);
        req.getSession().setAttribute("searchblogs",menublogs);
        req.getRequestDispatcher("/Menu3.jsp").forward(req,resp);
    }
}
