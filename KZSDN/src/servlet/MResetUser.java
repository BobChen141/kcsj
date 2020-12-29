package servlet;

import bean.User;
import dao.GetUsers;
import dao.MResetDat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/mresetuser")
public class MResetUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setIntroduction(req.getParameter("introduction"));
        try {
            boolean f=new MResetDat().resetDat(user);
            if(f)
            {
                ArrayList<User> users=new ArrayList<>();
                users=new GetUsers().getUser();
                req.getSession().setAttribute("users",users);
                req.getRequestDispatcher("/Manage.jsp").forward(req,resp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
