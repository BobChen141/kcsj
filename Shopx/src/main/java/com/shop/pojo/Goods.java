package com.shop.pojo;

import java.io.Serializable;

public class Goods implements Serializable{

	/**
	 * 商品类
	 */
	private static final long serialVersionUID = -4298565889470230608L;
	private int g_id;//商品id
	private float g_price;//价格
	private String g_picture;//图片
	private String g_describe;//描述
	private int g_category;//商品类别
	
	private int count;//数量，此处的变量是为了在展示页面显示该商品数量的
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public float getG_price() {
		return g_price;
	}
	public void setG_price(float g_price) {
		this.g_price = g_price;
	}
	public String getG_picture() {
		return g_picture;
	}
	public void setG_picture(String g_picture) {
		this.g_picture = g_picture;
	}
	public String getG_describe() {
		return g_describe;
	}
	public void setG_describe(String g_describe) {
		this.g_describe = g_describe;
	}
	public int getG_category() {
		return g_category;
	}
	public void setG_category(int g_category) {
		this.g_category = g_category;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//用来展示商品
	public Goods(int g_id, float g_price, String g_picture,
			String g_describe, int g_category) {
		super();
		this.g_id = g_id;
		this.g_price = g_price;
		this.g_picture = g_picture;
		this.g_describe = g_describe;
		this.g_category = g_category;
	}
	//用来在购物车展示商品
	public Goods(int g_id, float g_price, String g_picture,
			String g_describe, int g_category, int count) {
		super();
		this.g_id = g_id;
		this.g_price = g_price;
		this.g_picture = g_picture;
		this.g_describe = g_describe;
		this.g_category = g_category;
		this.count = count;
	}
	
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	//商品展示
	@Override
	public String toString() {
		return "Goods [g_id=" + g_id + ", g_price=" + g_price +
				", g_picture=" + g_picture + ", g_describe="
				+ g_describe + ", g_category=" + g_category + ", count="
				+ count + "]";
	}
	
	
}
