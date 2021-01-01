package com.shop.pojo;

import java.util.Date;

/**
 * 订单类
 *
 * @author ChenBo
 */
public class Order {
    private int oid;//订单id
    private Date otime;//订单支付时间
    private int gid;//商品信息
    private int counts;//购买数量
    private float money;//金额

    private String u_name;//购买者姓名
    private String gname;//商品名

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Date getOtime() {
        return otime;
    }

    public void setOtime(Date otime) {
        this.otime = otime;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public Order(Date otime, int gid, int counts, float money) {
        super();
        this.otime = otime;
        this.gid = gid;
        this.counts = counts;
        this.money = money;
    }


    public Order(Date otime, int counts, float money, String gname) {
        super();
        this.otime = otime;
        this.counts = counts;
        this.money = money;
        this.gname = gname;
    }

    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "Order [otime=" + otime + ", gid=" + gid + ", counts=" + counts + ", money=" + money + ", gname=" + gname
                + "]";
    }


}
