package servlet;

import bean.Blog;
import dao.SearchDate;
import utils.StringToDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/showdate")
public class ShowDate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date=req.getParameter("searchdate");
        String username=(String)req.getSession().getAttribute("username");
        ArrayList<Blog>blogs=new SearchDate().getBlog(username,date);
        System.out.println(blogs.size());
        System.out.println(date);
        String checkstr="sb";
        req.getSession().setAttribute("checkstr",checkstr);
        int page=0;
        req.getSession().setAttribute("blogss",blogs);
        req.getSession().setAttribute("page",page);
        req.getRequestDispatcher("/BlogList.jsp").forward(req,resp);
    }
}
