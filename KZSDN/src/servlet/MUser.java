package servlet;

import bean.User;
import dao.GetUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/muser")
public class MUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String username=req.getParameter("musername");
       User user=new User();
       user.setUsername(username);
       user=new GetUser().getUser(user);
       req.getSession().setAttribute("user1",user);
        req.getRequestDispatcher("/ManageUser.jsp").forward(req,resp);
    }
}
