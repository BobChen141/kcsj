package servlet;

import bean.User;
import dao.DeleteUser1;
import dao.GetUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/deleteuser")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("Text/html;charset=utf-8");
        String username=req.getParameter("dusername");
        System.out.println(username);
        boolean f=new DeleteUser1().deleteUser(username);
        if(f)
        {
            ArrayList<User> users=new ArrayList<>();
            users=new GetUsers().getUser();
            req.getSession().setAttribute("users",users);
            req.getRequestDispatcher("/Manage.jsp").forward(req,resp);
        }
    }
}
