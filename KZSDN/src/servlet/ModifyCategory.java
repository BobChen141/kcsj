package servlet;

import dao.GetCategorys;
import dao.ResetCategory;
import dao.ResetColumn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/modifycategory")
public class ModifyCategory extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        String username=req.getParameter("username7");
        String category=req.getParameter("musername");
        String str=req.getParameter("category_id1");
        String id=req.getParameter("getid");
        System.out.println(username+" "+category+" "+str);
        try {
            boolean f1=new ResetColumn().resetColumn(username,category,str,id);
            boolean f=new ResetCategory().resetBlog(username,category,str);

            System.out.println(f1+" "+f);
                ArrayList<String> categorys = new ArrayList<>();
                categorys = new GetCategorys().getCategory(username);
                req.getSession().setAttribute("columns", categorys);
                req.getRequestDispatcher("/Columns.jsp").forward(req, resp);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
