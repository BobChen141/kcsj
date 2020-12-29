package servlet;

import bean.User;
import dao.SearchUser1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/searchuser")
public class SearchUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        String user=req.getParameter("user");
        ArrayList<User>users =new SearchUser1().searchUser(user);
        req.getSession().setAttribute("users",users);
        req.getRequestDispatcher("/Menu1.jsp").forward(req, resp);
    }
}
