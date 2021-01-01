package com.shop.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.dao.ManagerDao;
import com.shop.dao.UserDao;
import com.shop.pojo.Goods;
import com.shop.pojo.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/MServlet")
public class MServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
//        request.setCharacterEncoding("utf-8");
        //判断是哪个页面
        String action = request.getParameter("action");
        if (action.equals("delUser")) {//删除用户
            //设置编码
            request.setCharacterEncoding("utf-8");
            //获得用户名
            String name = request.getParameter("u_name");
            //调用删除方法
            ManagerDao dao = new ManagerDao();
            //删除用户
            dao.delUser(name);
            response.sendRedirect("View/Manager/UserList.html");
        } else if (action.equals("delorders")) {//删除订单
            //设置编码
            request.setCharacterEncoding("utf-8");
            //获得oid
            String oid_ = (request.getParameter("oid"));
            int oid = Integer.parseInt(oid_);
            //调用删除方法
            ManagerDao dao = new ManagerDao();
            //删除订单
            dao.delOrders(oid);
            response.sendRedirect("View/Manager/OrderList.html");
        } else if (action.equals("checklogin")) {//检查管理员登录状态,checklogin
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");

            //判断session里是不是有值
            HttpSession session = request.getSession();
            String msg = "";
//	        Map<String,Object> map = new HashMap<String,Object>();
            if ((session.getAttribute("m_username")) == null) {
                //没登录,传入null
//				map.put("loginflag",null);
                msg = "0";
            } else {
                //已登录,传入名字
//				map.put("loginflag",(session.getAttribute("username")));
                msg = (String) session.getAttribute("m_username");
            }
            //将map转为json，并且传递给客户端
            //将map转为json
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), msg);
        } else if (action.equals("exit")) {//退出，exit
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");
            //清除session的用户名
            request.getSession().removeAttribute("m_username");
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), "已登出！");
        } else if (action.equals("user")) {//展示用户信息
            //设置编码
            response.setContentType("application/json;charset=utf-8");
            String name = request.getParameter("name");
            ManagerDao dao = new ManagerDao();
            List<User> list = dao.selectAllUser();
            User user = new User();
            for (User u : list) {
                if (u.getU_name().equals(name)) {
                    user.setU_name(name);
                    user.setU_pwd(u.getU_pwd());
                    user.setU_phone(u.getU_phone());
                    user.setU_address(u.getU_address());
                    user.setU_money(u.getU_money());
                    user.setU_role(u.getU_role());
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), user);

        } else if (action.equals("updateUser")) {//修改用户信息，updateUser
            //设置编码
            response.setContentType("application/json;charset=utf-8");

            String name = request.getParameter("username");
            String oldname = request.getParameter("oldname");
            String phone = request.getParameter("phone");
            String addr = request.getParameter("addr");
            String pwd = request.getParameter("pwd");
            String role_ = request.getParameter("role");
            String money_ = request.getParameter("money");

            float money = Float.parseFloat(money_);
            int role = Integer.parseInt(role_);

            User u = new User();
            u.setU_name(name);
            u.setU_pwd(pwd);
            u.setU_phone(phone);
            u.setU_address(addr);
            u.setU_money(money);
            u.setU_role(role);

            System.out.println("接收到的要进行修改的值：" + u.toString());

            ManagerDao dao = new ManagerDao();
            int count = dao.updateUser(u, oldname);
            System.out.println(count);

            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), count);
        } else if (action.equals("visited")) {//显示访问量
            //设置编码
            response.setContentType("application/json;charset=utf-8");
            ServletContext context = request.getServletContext();
            Integer visited = (Integer) context.getAttribute("visited");
            if (visited == null) {
                visited = 0;
            }
            //把list转换为json
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), visited);
        } else if (action.equals("goods")) {//展示商品信息
            //设置编码
            response.setContentType("application/json;charset=utf-8");
            Integer gid = Integer.parseInt(request.getParameter("gid"));
            ManagerDao dao = new ManagerDao();
            List<Goods> list = dao.selectAllGood();
            Goods good = new Goods();
            for (Goods g : list) {
                if (g.getG_id() == gid) {
                    good.setG_id(g.getG_id());
                    good.setG_price(g.getG_price());
                    good.setG_picture(g.getG_picture());
                    good.setG_describe(g.getG_describe());
                    good.setG_category(g.getG_category());

                }
            }

            System.out.println(good.toString());
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), good);

        } else if (action.equals("updateGoods")) {//修改商品信息，updateGoods
            //设置编码
            response.setContentType("application/json;charset=utf-8");

            //'gid':gid,'name':name,"price":price

            String gid_ = request.getParameter("gid");
            String name = request.getParameter("name");
            String price_ = request.getParameter("price");
            Float price = Float.parseFloat(price_);

            int gid = Integer.parseInt(gid_);

            ManagerDao dao = new ManagerDao();
            int count = dao.updateGood(gid, name, price);
            System.out.println(count);

            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), count);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
