package servlet;

import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.ResetDat;

@WebServlet("/resetData")
public class ResetData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        User user=new User();
        user.setTouxiang(req.getParameter("touxiang"));
        user.setUsername(req.getParameter("username"));
        user.setName(req.getParameter("name"));
        user.setGender(req.getParameter("gender"));
        user.setBirth(req.getParameter("birth"));
        user.setPhone(req.getParameter("phone"));
        user.setIntroduction(req.getParameter("introduction"));
        try {
            boolean f=new ResetDat().resetDat(user);
            if(f){
                req.getSession().setAttribute("user",user);
                req.getRequestDispatcher("/Menu.jsp").forward(req,resp);
            }
            else{
                req.getSession().setAttribute("user",user);
            req.setAttribute("error","修改失败，请重新修改！");
            req.getRequestDispatcher("/ResetData.jsp").forward(req,resp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
