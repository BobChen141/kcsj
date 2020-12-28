package com.shop.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.shop.pojo.Evaluate;
import com.shop.pojo.Order;
import com.shop.pojo.User;

/**
 * 用户操作类
 *
 * @author ChenBo
 */
public class UserDao {

    Connection conn = null;

    PreparedStatement ps = null;

    QueryRunner query = null;

    ResultSet rs = null;


    /**
     * 验证用户登录
     * 根据用户名查询密码
     * 返回一个字符串
     *
     * @param name
     * @return
     */
    public String checkUser(String name) {
        String pwd = null;

        try {

            conn = DBUtil.openConnection();

            String sql = "select * from user_TB where u_name = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, name);

            rs = ps.executeQuery();
            while (rs.next()) {
                pwd = rs.getString("u_pwd");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return pwd;
    }

    /**
     * 验证是否有此用户
     *
     * @param name
     * @return
     */
    public int checklogin(String name) {
        int count = 0;
        try {

            conn = DBUtil.openConnection();

            String sql = "select * from user_TB where u_name = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, name);

            rs = ps.executeQuery();
            if (rs.next()) {
                count = 1;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return count;
    }

    /**
     * 查询用户角色
     * 根据用户名查询,返回用户角色
     *
     * @param name
     * @return
     */
    public int checkRole(String name) {
        int count = 0;
        try {

            conn = DBUtil.openConnection();

            String sql = "select u_role from user_TB where u_name = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, name);

            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("u_role");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return count;
    }

    /**
     * 判断是否已有该用户名
     *
     * @param name
     * @return
     */
    public int checkName(String name) {
        int count = 0;
        try {

            conn = DBUtil.openConnection();

            String sql = "select * from user_TB where u_name = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, name);

            rs = ps.executeQuery();
            if (rs.next()) {
                count = 1;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return count;
    }


    /**
     * 注册
     * 获得用户bean，返回修改行数
     *
     * @param u
     * @return
     */
    public int Reg(User u) {
        int count = 0;

        try {
            conn = DBUtil.openConnection();

            String sql = "insert into user_TB(u_name,u_pwd,u_phone,u_address,u_role) values(?,?,?,?,0)";

            ps = conn.prepareStatement(sql);

            ps.setString(1, u.getU_name());
            ps.setString(2, u.getU_pwd());
            ps.setString(3, u.getU_phone());
            ps.setString(4, u.getU_address());

            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }


        return count;
    }

    /**
     * 忘记密码
     * 传入用户名和电话号码，查询后，修改该用户的密码为传入的密码
     *
     * @param name
     * @param phone
     * @param pwd
     * @return
     */
    public int forgetPwd(String name, String phone, String pwd) {
        int count = 0;


        try {
            conn = DBUtil.openConnection();

            String sql = "update user_tb set u_pwd =? where u_id=(select u_id from user_TB where u_name=? and u_phone=?) ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, pwd);
            ps.setString(2, name);
            ps.setString(3, phone);

            count = ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return count;
    }


    /**
     * 忘记会员名
     * 传入用户名和电话号码，查询后，修改该用户的密码为传入的密码
     *
     * @param name
     * @param phone
     * @param pwd
     * @return
     */
    public int forgetName(String name, String phone, String pwd) {
        int count = 0;


        try {
            conn = DBUtil.openConnection();

            String sql = "update user_tb set u_name =? where u_id=(select u_id from user_TB where u_pwd=? and u_phone=?) ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, pwd);
            ps.setString(3, phone);

            count = ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return count;
    }

    /**
     * 展示评论的方法
     *
     * @return
     */
    public List<Evaluate> getEval() {
        List<Evaluate> list = new ArrayList<Evaluate>();
        try {
            conn = DBUtil.openConnection();

            String sql = "select e.* ,u.u_name  from evaluate_TB e,user_TB u where e.u_id = u.u_id order by e.e_id desc";


            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                String econtent = rs.getString("e_content");//评价内容
                //同时引用date，使用全限名
                java.util.Date etime = rs.getDate("e_time");//评价时间
                String name = rs.getString("u_name");//评价人的用户id
                //ps.setDate(4, new Date(ee.getHiredate().getTime()));
                Evaluate e = new Evaluate(econtent, etime, name);
                list.add(e);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }

        return list;
    }

    /**
     * 写评论
     * 根据传入的Evaluate
     *
     * @param e
     * @return
     */
    public int writeEval(Evaluate e) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();

            String sql = "insert into evaluate_TB values(?,?,(select  u_id from user_tb where u_name=?))";

            ps = conn.prepareStatement(sql);

            ps.setString(1, e.getEcontent());
            ps.setDate(2, new Date(e.getEtime().getTime()));
            ps.setString(3, e.getName());

            count = ps.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }

        return count;
    }

    /**
     * 根据传入的用户名得到订单
     *
     * @param name
     * @return
     */
    public List<Order> getOrders(String name) {
        List<Order> list = new ArrayList<Order>();
        //日期格式化
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            conn = DBUtil.openConnection();
            String sql = "select o.*,g.g_describe,g.g_price ,u.u_name from order_TB o, user_TB u ,goods_TB g where o.uid=u.u_id and u.u_name=? and o.gid=g.g_id order by o.otime desc";

            ps = conn.prepareStatement(sql);
            ps.setString(1, name);

            rs = ps.executeQuery();

            while (rs.next()) {

                java.util.Date otime = rs.getDate("otime");//订单支付时间
                String gname = rs.getString("g_describe");
                int counts = rs.getInt("counts");//购买数量
                float price = rs.getFloat("g_price");//价格
                String u_name = rs.getString("u_name");//购买者姓名
                int oid = rs.getInt("oid");//订单id
                float money = 0;
                money = counts * price;
                Order order = new Order(otime, counts, money, gname);
                order.setU_name(u_name);
                order.setOid(oid);
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return list;
    }


    /**
     * 根据传入的用户名，得到用户信息
     *
     * @param u_name
     * @return
     */
    public User getUser(String u_name) {
        User u = null;
        try {
            conn = DBUtil.openConnection();

            String sql = "select * from user_TB where u_name =?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, u_name);
            rs = ps.executeQuery();

            while (rs.next()) {
                String u_phone = rs.getString("u_phone");
                String u_address = rs.getString("u_address");
                float u_money = rs.getFloat("u_money");
                u = new User(u_name, u_phone, u_address, u_money);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return u;
    }


    /**
     * 根据传入的用户类，更新一个类
     * 传入的类装的是新的信息，name里装的是要修改的用户名
     *
     * @param u
     * @param name
     * @return
     */
    public int updateUser(User u, String name) {
        int count = 0;
        conn = DBUtil.openConnection();
        try {
            String sql = "update user_TB set u_name =?  , u_phone=?, u_address=? where u_name=?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getU_name());
            ps.setString(2, u.getU_phone());
            ps.setString(3, u.getU_address());
            ps.setString(4, name);

            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return count;
    }
}
