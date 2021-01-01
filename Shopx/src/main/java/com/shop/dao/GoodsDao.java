package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.shop.pojo.Goods;
import com.shop.pojo.Order;

/**
 * 商品表操作类
 *
 * @author ChenBo
 */
public class GoodsDao {


    Connection conn = null;
    QueryRunner query = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**
     * @param ca 传入商品类别
     * @return 返回商品集合
     */
    public List<Goods> selectAllGoods(int ca) {
        String sql = "select * from goods_TB where g_category = " + ca;

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
     * 添加商品到购物车
     *
     * @param name   用户名
     * @param gid    商品gid
     * @param counts 购买数量
     * @return 影响行数
     */
    public int addShopCart(String name, int gid, int counts) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            PreparedStatement ps = conn.prepareStatement("{call dbo.insertToSc(?, ?,?)}"); //调用存储过程
            ps.setInt(1, gid);
            ps.setString(2, name);
            ps.setInt(3, counts);
            count = ps.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //关流
            DbUtils.closeQuietly(conn);
        }
        return count;
    }

    /**
     * 展示购物车
     *
     * @param name 用户名
     * @return 商品集合
     */
    public List<Goods> showAllSc(String name) {
        List<Goods> list = new ArrayList<Goods>();
        try {
            conn = DBUtil.openConnection();

            String sql = "select g.*,sc.goods_count "
                    + "from user_tb u,"
                    + "goods_TB g,"
                    + "shopping_cart sc "
                    + "where u.u_name =?"
                    + " and  u.u_id = sc.u_id "
                    + "and g.g_id =  sc.goods_id";

            ps = conn.prepareStatement(sql);

            ps.setString(1, name);

            rs = ps.executeQuery();

            while (rs.next()) {

                int g_id = rs.getInt("g_id");

                float g_price = rs.getFloat("g_price");


                String g_picture = rs.getString("g_picture");

                String g_describe = rs.getString("g_describe");

                int g_category = rs.getInt("g_category");

                int count = rs.getInt("goods_count");

                Goods g = new Goods(g_id, g_price, g_picture, g_describe, g_category, count);

                list.add(g);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }

        return list;
    }

    /**
     * @param gid   商品gid
     * @param uname 用户名
     * @return 商品
     */
    public Goods showScGoods(String gid, String uname) {
        Goods g = null;
        try {
            conn = DBUtil.openConnection();

            String sql = "select g.*, s.goods_count from \r\n" +
                    "goods_TB g,\r\n" +
                    "shopping_cart s,\r\n" +
                    "user_TB u\r\n" +
                    "where\r\n" +
                    "g.g_id = s.goods_id\r\n" +
                    "and \r\n" +
                    "u.u_id = s.u_id\r\n" +
                    "and \r\n" +
                    "u.u_name=?\r\n" +
                    "and\r\n" +
                    "g.g_id=?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, uname);
            ps.setInt(2, Integer.parseInt(gid));

            rs = ps.executeQuery();

            while (rs.next()) {

                int g_id = rs.getInt("g_id");

                float g_price = rs.getFloat("g_price");


                String g_picture = rs.getString("g_picture");

                String g_describe = rs.getString("g_describe");

                int g_category = rs.getInt("g_category");

                int count = rs.getInt("goods_count");

                g = new Goods(g_id, g_price, g_picture, g_describe, g_category, count);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return g;
    }

    /**
     * @param name 用户名
     * @param mon  余额
     * @return 余额充足返回true, 余额不足返回false
     */
    public boolean getMoney(String name, float mon) {
        int count = 0;
        boolean flag = true;
        float money = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "Select u_money from user_TB where u_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);

            rs = ps.executeQuery();
            if (rs.next()) {
                money = rs.getFloat("u_money");
            }
            //判断余额是否充足，如果充足则减去余额，
            if (money >= mon) {
                //最终的要该成的金额
                float lastm = money - mon;
                count = this.updateMoney(name, lastm);
                //判断影响行数是否大于0
                if (count > 0) {
                    flag = true;
                } else {
                    flag = false;
                }
            } else {
                //余额不足则返回false
                flag = false;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return flag;
    }


    /**
     * 更改余额
     *
     * @param name  用户名
     * @param lastm 要更新的钱
     * @return 影响行数
     */
    public int updateMoney(String name, float lastm) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "update user_TB set u_money=? where u_name = ?";
            ps = conn.prepareStatement(sql);
            ps.setFloat(1, lastm);
            ps.setString(2, name);
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
     * @param o    订单类
     * @param name 用户名
     * @return 影响行数
     */
    public int insertToOrder(Order o, String name) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "insert into  order_TB values((select u_id from user_TB where u_name=?),?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDate(2, new java.sql.Date(o.getOtime().getTime()));
            ps.setInt(3, o.getGid());
            ps.setInt(4, o.getCounts());
            ps.setFloat(5, o.getMoney());

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
     * 删除购物车表数据
     *
     * @param name 用户名
     * @param gid  商品gid
     * @return 返回影响行数
     */
    public int deleteSc(String name, int gid) {
        int count = 0;
        try {
            conn = DBUtil.openConnection();
            String sql = "delete  from shopping_cart  where u_id =(select u_id from user_TB where u_name=?)  and goods_id=? ";

            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, gid);

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
     * 根据传入的用户名和商品id得到具体数据
     * 点击购物车里的图片，修改数量
     * 根据传入的字符串，判断是进行加还是减
     * add为加，minus为减
     * 当只有一件商品时，不能减
     *
     * @param btnname 按钮名字
     * @param user    用户名字
     * @param gid     商品id
     * @param counts  商品购买的数量
     * @return
     */
    public String changeCount(String btnname, String user, int gid, int counts) {
        int count = 0;
        String msg = "";
        conn = DBUtil.openConnection();
        //先判断是进行什么操作
        if (btnname.equals("add")) {//增加
            String sql = "update shopping_cart set goods_count = goods_count+1 where goods_id = ? and u_id = (select u_id from user_TB where u_name = ?)";

            try {
                ps = conn.prepareStatement(sql);

                ps.setInt(1, gid);
                ps.setString(2, user);
                //获得到影响的行数
                count = ps.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                DbUtils.closeQuietly(conn);
            }


        } else if (btnname.equals("minus")) {//减少，至少要保留一个商品
            if (counts == 1) {//当数量为1个时，直接返回"至少保留一个"的提示信息
                msg = "至少保留一个";
            } else {
                //执行减一个的操作
                String sql = "update shopping_cart set goods_count = goods_count-1 where goods_id = ? and u_id = (select u_id from user_TB where u_name = ?)";

                try {
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, gid);
                    ps.setString(2, user);
                    count = ps.executeUpdate();

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        //如果msg里是“至少保留一个”，则保持原样
        if (msg.equals("至少保留一个")) {
            msg = "至少保留一个";
        } else {
            if (count == 0) {//未进行修改
                msg = "修改失败";
            } else {
                msg = "修改成功";
            }
        }
        return msg;
    }


}
