package com.shop.pojo;

import java.io.Serializable;

public class ShopCart implements Serializable {

    /**
     * 添加商品进购物车的类
     */
    private static final long serialVersionUID = 5850355515390716818L;
    private int goodsId;//商品的id
    private int goodsCount;//商品的数量
    private String name;//购买者的用户名

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public ShopCart(int goodsId, int goodsCount, String name) {
        super();
        this.goodsId = goodsId;
        this.goodsCount = goodsCount;
        this.name = name;
    }

    public ShopCart() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "ShopCart [goodsId=" + goodsId + ", goodsCount=" + goodsCount
                + ", name=" + name + "]";
    }


}
