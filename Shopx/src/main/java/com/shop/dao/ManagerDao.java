package com.shop.dao;

import com.shop.pojo.Goods;
import com.shop.pojo.Order;
import com.shop.pojo.User;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理员类，提供对：用户，商品信息，订单信息的修改，查询
 */

public class ManagerDao {
    Connection conn = null;
    QueryRunner query = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * 查询所有用户
     *
     * @return
     */
    public List<User> selectAllUser() {
        String sql = "select * from user_TB ";

        conn = DBUtil.openConnection();

        query = new QueryRunner();

        List<User> list = new ArrayList<User>();
        try {
            list = query.query(conn, sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关流
            DbUtils.closeQuietly(conn);
        }

        return list;
    }

    /**
     * 查询所有订单
     *
     * @return
     */
    public List<Order> selectAllOrder() {
        List<Order> lists = new ArrayList<Order>();
        List<User> ulist = new ArrayList<User>();
        //先查询所有用户名selectAllUser()，再遍历这个用户集合
        ulist = this.selectAllUser();
        //不断传入用户名，得到Order类，存入集合
        for (User u : ulist
        ) {
            List<Order> list = new ArrayList<Order>();//每个用户的订单list
            UserDao dao = new UserDao();
            list = dao.getOrders(u.getU_name());//返回每个用户的订单集合
            //合并集合到总lists
            lists.addAll(list);
        }
        String sql = "select * from order_TB ";

        conn = DBUtil.openConnection();

        query = new QueryRunner();


        try {
            lists = query.query(conn, sql, new BeanListHandler<Order>(Order.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关流
            DbUtils.closeQuietly(conn);
        }

        return lists;
    }

    /**
     * 查询所有商品
     *
     * @return
     */
    public List<Goods> selectAllGood() {
        String sql = "select * from goods_TB";

        conn = DBUtil.openConnection();

        query = new QueryRunner();

        List<Goods> list = new ArrayList<Goods>();
        try {
            list = query.query(conn, sql, new BeanListHandler<Goods>(Goods.class));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关流
            DbUtils.closeQuietly(conn);
        }

        return list;
    }

    /**
     * 据用户名删除用户
     *
     * @param name 用户名
     * @return
     */
    public int delUser(String name) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "delete  from user_tb  where u_name =?";

            ps = conn.prepareStatement(sql);
            ps.setString(1, name);

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
     * 根据oid删除订单
     *
     * @param oid
     * @return
     */
    public int delOrders(int oid) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "delete  from order_tb  where oid =?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, oid);

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
     * 更新用户类
     *
     * @param u    新的用户类
     * @param name 要更新的用户用户名
     * @return
     */
    public int updateUser(User u, String name) {
        int count = 0;
        conn = DBUtil.openConnection();
        try {
            String sql = "update user_TB set u_name =?  , u_phone=?  , u_address=?  ,u_pwd=?  ,u_money=?  ,u_role=?  where u_name=? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getU_name());
            ps.setString(2, u.getU_phone());
            ps.setString(3, u.getU_address());
            ps.setString(4, u.getU_pwd());
            ps.setFloat(5, u.getU_money());
            ps.setInt(6, u.getU_role());
            ps.setString(7, name);

            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return count;
    }

    /**
     * 更新商品
     *
     * @param gid   商品id
     * @param name  商品名
     * @param price 商品价格
     * @return
     */
    public int updateGood(int gid, String name, float price) {
        int count = 0;
        conn = DBUtil.openConnection();
        try {
            String sql = "update goods_TB set g_describe =?  , g_price=?  where  g_id=? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setFloat(2, price);
            ps.setInt(3, gid);
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return count;
    }
}
