package servlet;

import bean.Message;
import dao.GetEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/regetpwd")
public class RegetpwdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        //先数据库查询用户邮箱了，然后发验证码给该邮箱再跳转到修改密码。
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String syzm=null;
        String gyzm=req.getParameter("YZM");
        Message msg=new Message();
        msg.setUsername(username);

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if ("EmailCode".equals(cookie.getName())) {
                syzm = cookie.getValue();
                break;
            }
        }
            if(syzm.equals(gyzm)) {
                req.setAttribute("username", username);
                //req.setAttribute("password",password);
                req.setAttribute("tishi1", "验证码正确！");
                //resp.getWriter().write("\"<script>alert(\\\"注册成功\\\");</script>\"");
                req.getRequestDispatcher("/ResetPassword.jsp").forward(req, resp);
            }
            else{
                req.setAttribute("yzmerror","验证码错误！");
                req.getRequestDispatcher("/RegetPassword.jsp").forward(req,resp);
            }
    }
}
