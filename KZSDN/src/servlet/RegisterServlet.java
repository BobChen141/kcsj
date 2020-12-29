package servlet;

import dao.AddColumn;
import dao.AddUser;
import dao.CheckRegister;
import bean.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String email=req.getParameter("email");
        String syzm=null;
        String gyzm=req.getParameter("YZM");
        //String useremail=req.getParameter("useremail");
        System.out.println("username="+username);
        System.out.println("password="+password);
        //查询数据先判断是否存在该用户名，若存在则提示否则新建一条信息
        Message msg=new Message();
        msg.setUsername(username);
        msg.setPassword(password);
        msg.setEmail(email);
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if ("EmailCode".equals(cookie.getName())) {
                   syzm = cookie.getValue();
                break;
            }
        }
        boolean f1=new CheckRegister().checkRegister(msg);
        if(f1)
        {//用户名已存在
           // System.out.println("www");
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("error","用户名已被使用，请重新输入！");
            req.getRequestDispatcher("/Register.jsp").forward(req,resp);
        }
        else{//直接注册
            //System.out.println("zzz");
            if(syzm.equals(gyzm)) {
                try {
                    boolean f2 = new AddUser().addUser(msg);
                    if (f2) {//注册成功
                        req.setAttribute("username", username);
                        //req.setAttribute("password",password);
                        req.setAttribute("tishi1", "注册成功！");
                        new AddColumn().addcolumn(username,"算法","111");
                        new AddColumn().addcolumn(username,"笔记","222");
                        //resp.getWriter().write("\"<script>alert(\\\"注册成功\\\");</script>\"");
                        req.getRequestDispatcher("/index.jsp").forward(req, resp);
                    } else {//注册失败
                        req.setAttribute("username", username);
                        // req.setAttribute("password",password);
                        req.setAttribute("tishi2", "注册失败！");

                        req.getRequestDispatcher("/Register.jsp").forward(req, resp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                req.setAttribute("yzmerror","验证码错误");
                req.getRequestDispatcher("Register.jsp").forward(req,resp);
            }
        }
    }
}
