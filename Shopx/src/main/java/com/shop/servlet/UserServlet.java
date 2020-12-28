package com.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.dao.ManagerDao;
import com.shop.dao.UserDao;
import com.shop.pojo.Evaluate;
import com.shop.pojo.Goods;
import com.shop.pojo.Order;
import com.shop.pojo.User;

/**
 * 进行用户相关操作的servlet
 *
 * @author ChenBo
 */
@WebServlet(urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public UserServlet() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * The doGet method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doPost(request, response);
    }

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //判断是哪个页面
        String action = request.getParameter("action");

        if (action.equals("login")) {//登录，login
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");

            String name = request.getParameter("username");
            String pwd = request.getParameter("pwd");

            UserDao dao = new UserDao();
            //先验证是否有此用户
            int num = dao.checklogin(name);
            int count = 0;
            if (num != 0) {//说明有此用户
                String npwd = dao.checkUser(name);
                if (npwd.equals(pwd)) {

                    //登录成功，跳转到首页
                    //跳转前先进行角色判断
                    int role = dao.checkRole(name);
                    if (role == 1) {//1，是管理员
                        count = 3;
                        request.getSession().setAttribute("m_username", name);//存在管理员域中
                    } else if (role == 0) {//普通用户
                        count = 1;
                        request.getSession().setAttribute("username", name);
                    }

                } else {
                    count = 0;//密码错误
                }
            } else {//无此用户
                count = 2;
            }
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), count);
        } else if (action.equals("reg")) {//注册,reg
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");
            //获得页面数据
            String name = request.getParameter("username");
            String phone = request.getParameter("phone");
            String addr = request.getParameter("addr");
            String pwd1 = request.getParameter("pwd1");
            User u = new User(name, phone, pwd1, addr);

            UserDao dao = new UserDao();
            //在注册前先判断是否已有该用户名
            int checkname = dao.checkName(name);

            int count = 0;//用来记录是否用户表里插入数据
            ObjectMapper mapper = new ObjectMapper();
            //如果有此用户名checkname>0
            //有此用户，不注册，返回注册失败count=0
            if (checkname > 0) {
                count = 0;
            } else {//没有此用户，允许注册
                count = dao.Reg(u);

                //并且传递给客户端
                if (count > 0) {
                    request.getSession().setAttribute("username", name);
                }
            }
            mapper.writeValue(response.getWriter(), count);
        } else if (action.equals("exit")) {//退出，exit
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");
            //清除session的用户名
            request.getSession().removeAttribute("username");
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), "已登出！");
        } else if (action.equals("lostname")) {//忘记名字,lostname

            String name = request.getParameter("username");
            String phone = request.getParameter("phone");
            String pwd = request.getParameter("pwd");
            UserDao dao = new UserDao();
            int count = dao.forgetName(name, phone, pwd);
            if (count > 0) {
                //修改用户名成功！
                //移除登录状态
                request.getSession().setAttribute("username", "");
            } else {
            }
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), count);
        } else if (action.equals("lostpwd")) {//忘记密码,lostpwd
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");
            String name = request.getParameter("username");
            String phone = request.getParameter("phone");
            String pwd = request.getParameter("pwd1");

            UserDao dao = new UserDao();
            int count = dao.forgetPwd(name, phone, pwd);
            if (count > 0) {
                //修改成功！
                //移除seesion的用户名，改为未登录状态
                request.getSession().setAttribute("username", "");
            } else {
                //修改失败
            }
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), count);
        } else if (action.equals("eval")) {//展示评价,eval
            //设置编码
            request.setCharacterEncoding("utf-8");
            UserDao dao = new UserDao();
            List<Evaluate> list = new ArrayList<Evaluate>();
            list = dao.getEval();
            request.getSession().setAttribute("evalist", list);
            //跳转到评价页面
            response.sendRedirect("View/User/Evaluate.jsp");

        } else if (action.equals("writeeval")) {//写评价,writeeval
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");
            HttpSession session = request.getSession();
            int count = 0;
            //判断是否登录，如果没登录就不能评论
            if ((session.getAttribute("username")) == null) {
                count = -1;
            } else {
                //获得系统时间
                Date time = new Date();
                //获得评论内容
                String text = request.getParameter("text");
                System.out.println("text" + text);
                String name = (String) session.getAttribute("username");
                Evaluate e = new Evaluate(text, time, name);
                UserDao dao = new UserDao();
                count = dao.writeEval(e);
            }
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), count);
        } else if (action.equals("checklogin")) {//检查普通用户的登录状态,checklogin
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");

            //判断session里是不是有值
            String msg = "";
//	        Map<String,Object> map = new HashMap<String,Object>();
            if ((request.getSession().getAttribute("username")) == null) {
                //没登录,传入null
//				map.put("loginflag",null);
                msg = "0";
            } else {
                //已登录,传入名字
//				map.put("loginflag",(session.getAttribute("username")));
                msg = (String) request.getSession().getAttribute("username");
            }
            //将map转为json，并且传递给客户端
            //将map转为json
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), msg);
        } else if (action.equals("showOrder")) {//展示订单信息，检查登录状态,showOrder
            //进行ajax相应
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");

            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("username");
            UserDao dao = new UserDao();
            List<Order> list = dao.getOrders(name);
            //将list转为json，并且传递给客户端
            //把list转换为json
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), list);
        } else if (action.equals("user")) {//展示用户信息，user
            //设置编码
            response.setContentType("application/json;charset=utf-8");
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("username");
            UserDao dao = new UserDao();
            User u = dao.getUser(name);
            //把list转换为json
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), u);
        } else if (action.equals("updateUser")) {//展示用户信息，updateUser
            //设置编码
            response.setContentType("application/json;charset=utf-8");
            HttpSession session = request.getSession();
            String nowname = (String) session.getAttribute("username");
            String name = request.getParameter("username");
            String phone = request.getParameter("phone");
            String addr = request.getParameter("addr");

            User u = new User();
            u.setU_name(name);
            u.setU_address(addr);
            u.setU_phone(phone);

            System.out.println(u.toString());

            UserDao dao = new UserDao();
            int count = dao.updateUser(u, nowname);
            System.out.println(count);
            if (count == 0) {
            } else {

                //修改成功，使登录用户改为当前用户
                request.getSession().setAttribute("username", name);
            }
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), count);
        } else if (action.equals("checkinel")) {//从发表评论按钮判断是否登录,checkinel
            //进行ajax相应
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");
            HttpSession session = request.getSession();
            int login = 0;
            //判断是否已经登录
            if ((session.getAttribute("username")) == null) {
                //没登录显示提示信息
                login = 0;
            } else {
                //如果已经登录，则跳转到显示页面
                login = 1;
            }
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), login);
        } else if (action.equals("userlist")) {//展示所有用户信息，userlist
            //设置编码
            response.setContentType("application/json;charset=utf-8");
            ManagerDao dao = new ManagerDao();
            List<User> list = dao.selectAllUser();
            //把list转换为json
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), list);
        } else if (action.equals("goodslist")) {//展示所有商品信息，goodslist
            //设置编码
            response.setContentType("application/json;charset=utf-8");
            ManagerDao dao = new ManagerDao();
            List<Goods> list = dao.selectAllGood();
            //把list转换为json
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), list);
        } else if (action.equals("orderlist")) {//展示所有订单信息，orderlist
            //设置编码
            response.setContentType("application/json;charset=utf-8");
            ManagerDao dao = new ManagerDao();
            List<Order> list = dao.selectAllOrder();
            //把list转换为json
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), list);
        } else if (action.equals("countsofman")) {//显示在线人数
            //设置编码
            response.setContentType("application/json;charset=utf-8");
            ServletContext context = request.getServletContext();
            Integer countsofman = (Integer) context.getAttribute("countsofman");
            if (countsofman == null) {
                countsofman = 0;
            }
            //把list转换为json
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), countsofman);
        }
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
    }

}
