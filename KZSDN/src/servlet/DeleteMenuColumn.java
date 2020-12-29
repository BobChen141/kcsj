package servlet;

import dao.DeleteMenu_Column;
import dao.GetMenu_Column;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/deletemenucolumn")
public class DeleteMenuColumn extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        String category=req.getParameter("deletecolumn");
        System.out.println(category);
        boolean f=new DeleteMenu_Column().deleteCategory(category);
        if(f)
        {
            ArrayList<String> allcolumns=new ArrayList<>();
            allcolumns=new GetMenu_Column().getColumn();
            req.getSession().setAttribute("allcolumns",allcolumns);
            req.getRequestDispatcher("/Manage.jsp").forward(req,resp);
        }
    }
}
