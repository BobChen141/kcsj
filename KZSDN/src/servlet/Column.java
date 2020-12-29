package servlet;

import bean.Category;
import dao.GetCategorys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/column")
public class Column extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        String username=req.getParameter("username2");
        ArrayList<Category>categorys=new ArrayList<>();
        categorys=new GetCategorys().getCategory(username);
        for(int i=0;i<categorys.size();i++)
        {
            Category c=categorys.get(i);
        }
        req.getSession().setAttribute("columns",categorys);
        req.getRequestDispatcher("/Columns.jsp").forward(req, resp);
    }
}
