package com.shop.pojo;

import java.io.Serializable;
import java.util.Date;

public class Evaluate implements Serializable {

    /**
     * 评价表
     */
    private static final long serialVersionUID = 489833047832291077L;
    private String econtent;//评价内容
    private Date etime;//评价时间
    private String name;//评价人的名字


    @Override
    public String toString() {
        return "Evaluate [econtent=" + econtent + ", etime=" + etime
                + ", name=" + name + "]";
    }

    public Evaluate(String econtent, Date etime, String name) {
        super();
        this.econtent = econtent;
        this.etime = etime;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Evaluate() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getEcontent() {
        return econtent;
    }

    public void setEcontent(String econtent) {
        this.econtent = econtent;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
