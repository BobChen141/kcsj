package com.shop.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.dao.GoodsDao;
import com.shop.dao.UserDao;
import com.shop.pojo.Goods;
import com.shop.pojo.Order;

/**
 * 商品操作Servlet
 *
 * @author ChenBo
 */
@WebServlet(urlPatterns = "/GoodsServlet")
public class GoodsServlet extends HttpServlet {
    //用来存放某一订单的信息
    private static Map<Float, Goods> map = null;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor of the object.
     */
    public GoodsServlet() {
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

        //判断功能
        String action = request.getParameter("action");

        if (action.equals("showgoods")) {//展示商品，showgoods

            //从视图层得到商品的类名
            int category = Integer.parseInt(request.getParameter("category"));
            GoodsDao dao = new GoodsDao();
            List<Goods> list = dao.selectAllGoods(category);
            request.getSession().setAttribute("list", "");
            request.getSession().setAttribute("list", list);
            response.sendRedirect("View/Goods/Goods.jsp");

        } else if (action.equals("addgoods")) {//添加商品到购物车,addgoods
            //进行ajax相应
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");
            HttpSession session = request.getSession();
            String msg = "";
            //判断是否已经登录
            if ((session.getAttribute("username")) == null) {
                //没登录显示提示信息
                msg = "选择商品前需要登录！";
            } else {
                //获得商品id
                int g_id = Integer.parseInt(request.getParameter("g_id"));
                //获得用户名
                String name = (String) session.getAttribute("username");
                //获得数量,初始数量设置为一个
                int counts = 1;
                GoodsDao dao = new GoodsDao();
                int count = dao.addShopCart(name, g_id, counts);
                if (count > 0) {
                    //提示添加成功！
                    msg = "添加成功！";
                } else {
                    //提示添加失败！
                    msg = "添加失败！";
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), msg);

        } else if (action.equals("showsc")) {//展示购物车,showsc
            //进行ajax相应
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("username");
            //将数量数据取出来
            GoodsDao dao = new GoodsDao();
            List<Goods> list = dao.showAllSc(name);
            //将list转为json，并且传递给客户端
            //把list转换为json
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), list);

        } else if (action.equals("checkinsc")) {//从购物车按钮判断是否登录,checkinsc
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
        } else if (action.equals("checkname")) {//通过AJAX得到用户名,checkname
            //进行ajax相应
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");
            //获得用户名
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("username");
            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), name);
        } else if (action.equals("counts")) {//结算,counts
            String[] gids = request.getParameterValues("ckbox");
            //将gid和用户名传入得到单个的对象，再装到集合里去
            //获得用户名
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("username");
            //重新得到一个map，更新内容
            map = new HashMap<Float, Goods>();
            GoodsDao dao = new GoodsDao();

            float sums = 0;
            for (String gid : gids) {
                Goods g = dao.showScGoods(gid, name);
                int count = g.getCount();
                float price = g.getG_price();


                sums = price * count;
                map.put(sums, g);
            }
//			 for(Float key:map.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
//				 Goods value = map.get(key);
//				  System.out.println("key:"+key+" vlaue:"+value.toString());
//			}
            request.getSession().setAttribute("countslist", map);
            request.getSession().setAttribute("sums", sums);
            response.sendRedirect("/Shopx/View/Goods/Accounts.jsp");

        } else if (action.equals("topay")) {//跳转到支付页面,topay
            float pay = Float.parseFloat(request.getParameter("sums"));
            request.getSession().setAttribute("pay", pay);
            request.getSession().setAttribute("paymsg", "");
            response.sendRedirect("/Shopx/View/Goods/Pay.jsp");
        } else if (action.equals("pay")) {//支付,pay
            //获取用户名
            //获得用户名
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("username");
            //获得页面所输入的密码
            String pwd = request.getParameter("pwd");

            //获取数据库中的密码
            UserDao dao = new UserDao();
            String truePed = dao.checkUser(name);
            //判断密码是否正确
            if (truePed.equals(pwd)) {
                //如果正确则进行下一层判断
                //从数据库中获得该用户余额
                GoodsDao gdao = new GoodsDao();
                //获得该订单余额
                float money = Float.parseFloat(request.getParameter("money"));

                boolean isok = gdao.getMoney(name, money);
                //判断余额是否充足，如果充足则减去余额，并在提示页面显示支付成功
                if (isok) {
                    //显示支付成功
                    request.getSession().setAttribute("msg", "支付成功!");
                    //跳转到提示页面
                    response.sendRedirect("View/User/Msg.jsp");
                    //如果成功，则调用插入信息到订单表方法
                    //1、先得到map
                    for (Float key : map.keySet()) {//keySet获取map集合key的集合  然后在遍历key即可
                        Goods value = map.get(key);
                        Date otime = new Date();
                        int gid = value.getG_id();
                        int counts = value.getCount();
                        float mon = key;
                        Order order = new Order(otime, gid, counts, money);
                        gdao.insertToOrder(order, name);
                        //并且删除购物车表里的信息
                        gdao.deleteSc(name, gid);
                    }


                } else {
                    //判断余额是否充足，如果余额过少则在提示页面显示
                    request.getSession().setAttribute("msg", "余额不足！");
                    //跳转到提示页面
                    response.sendRedirect("View/User/Msg.jsp");
                }
            } else {
                //如果不正确则跳转到提示页面，提示密码错误
                request.getSession().setAttribute("paymsg", "密码错误,请重新输入");
                //跳转到提示页面
                response.sendRedirect("/Shopx/View/Goods/Pay.jsp");
            }
        } else if (action.equals("deleteSc")) {//删除,deleteSc
            //设置编码
            request.setCharacterEncoding("utf-8");
            //获得用户名
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("username");
            //获得gid
            int gid = Integer.parseInt(request.getParameter("gid"));
            //调用删除方法
            GoodsDao dao = new GoodsDao();
            dao.deleteSc(name, gid);
            response.sendRedirect("View/Goods/ShopCart.html");
        } else if (action.equals("changCounts")) {//删除,changCounts
            //进行ajax相应
            //设置响应的数据格式为json
            response.setContentType("application/json;charset=utf-8");
            //获得用户名
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("username");
            //获得ajax从页面传入的值
            String btnname = request.getParameter("bn");
            int gid = Integer.parseInt(request.getParameter("g"));
            int counts = Integer.parseInt(request.getParameter("c"));
            GoodsDao dao = new GoodsDao();
            String msg = dao.changeCount(btnname, name, gid, counts);

            ObjectMapper mapper = new ObjectMapper();
            //并且传递给客户端
            mapper.writeValue(response.getWriter(), msg);
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
