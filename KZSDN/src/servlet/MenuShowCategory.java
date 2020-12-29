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

@WebServlet("/menushowcategory")
public class MenuShowCategory extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        String str=req.getParameter("clickcategory");
        String checkstr=str;
        req.getSession().setAttribute("checkstr",checkstr);
        req.getRequestDispatcher("/Menu2.jsp").forward(req,resp);

    }
}
