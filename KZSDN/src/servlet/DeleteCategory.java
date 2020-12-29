package servlet;

import dao.CheckColum;
import dao.DeleteColumn;
import dao.GetCategorys;
import dao.ResetCategory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/deletecategory")
public class DeleteCategory extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        String username=req.getParameter("username1");
        String category=req.getParameter("dusername");
            boolean f = new DeleteColumn().deleteColumn(username, category);
            if(f)
            {
                String str="";
                try {
                    boolean f1=new ResetCategory().resetBlog(username,category,str);
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
}
