package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tomodifyc")
public class Tomodifyc extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        String category=req.getParameter("musername");
        String username=req.getParameter("username7");
        String id=req.getParameter("getid");
        req.setAttribute("id",id);
        req.setAttribute("item",category);
        req.setAttribute("username7",username);

        req.getRequestDispatcher("/ModifyCategory.jsp").forward(req, resp);
    }
}
