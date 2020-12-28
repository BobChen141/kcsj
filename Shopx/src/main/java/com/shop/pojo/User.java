package com.shop.pojo;

import java.io.Serializable;

public class User implements Serializable {

    /**
     * 用户类
     */
    private static final long serialVersionUID = -3034203599506817489L;
    private String u_name;
    private String u_phone;
    private String u_pwd;
    private String u_address;
    private float u_money;
    private int u_role;

    public int getU_role() {
        return u_role;
    }

    public void setU_role(int u_role) {
        this.u_role = u_role;
    }

    public float getU_money() {
        return u_money;
    }

    public void setU_money(float u_money) {
        this.u_money = u_money;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_pwd() {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

    public String getU_address() {
        return u_address;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    //用来注册
    public User(String u_name, String u_phone, String u_pwd, String u_address) {
        super();
        this.u_name = u_name;
        this.u_phone = u_phone;
        this.u_pwd = u_pwd;
        this.u_address = u_address;
    }

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "User{" +
                "u_name='" + u_name + '\'' +
                ", u_phone='" + u_phone + '\'' +
                ", u_pwd='" + u_pwd + '\'' +
                ", u_address='" + u_address + '\'' +
                ", u_money=" + u_money +
                ", u_role=" + u_role +
                '}';
    }


    //用来展示
    public User(String u_name, String u_phone, String u_address, float u_money) {
        super();
        this.u_name = u_name;
        this.u_phone = u_phone;
        this.u_address = u_address;
        this.u_money = u_money;
    }


}
