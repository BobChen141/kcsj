package servlet;

import dao.AddColumn;
import dao.CheckColum;
import dao.GetCategorys;
import utils.GetID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/addcategory")
public class AddCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        String username=req.getParameter("username");
        String name=req.getParameter("category_id");
        try {
            System.out.println(username);
            boolean f1=new CheckColum().checkColumn(username,name);
            if(!f1) {
                String id= GetID.main(null);
                boolean f = new AddColumn().addcolumn(username, name,id);
                if (f) {
                    ArrayList<String> categorys = new ArrayList<>();
                    categorys = new GetCategorys().getCategory(username);
                    req.getSession().setAttribute("columns", categorys);
                    req.getRequestDispatcher("/Columns.jsp").forward(req, resp);
                }
            }
            else
            {
                ArrayList<String> categorys = new ArrayList<>();
                categorys = new GetCategorys().getCategory(username);
                req.getSession().setAttribute("columns", categorys);
                req.getRequestDispatcher("/Columns.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
