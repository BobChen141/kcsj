package servlet;

import bean.User;
import dao.GetUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkuser")
public class CheckUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=new User();
        user.setUsername(req.getParameter("check_id"));
        user=new GetUser().getUser(user);
        req.getSession().setAttribute("user1",user);
        req.getRequestDispatcher("/UserImformation.jsp").forward(req, resp);
    }
}
