package com.explorer.tfms.web.dto;

public class OrderTotalDto {
	/**
	 * 店铺名称
	 */
	private String name;
	/**
	 * 店铺老板姓名
	 */
	private String bossname;
	/**
	 * 店铺老板联系方式
	 */
	private String bossphone;
	/**
	 * 时间内的订单数量
	 */
	private long num;
	/**
	 * 订单总价格
	 */
	private float total;
	
	public OrderTotalDto(){}
	
	
	public OrderTotalDto(String name, String bossname, String bossphone,
			long num, float total) {
		super();
		this.name = name;
		this.bossname = bossname;
		this.bossphone = bossphone;
		this.num = num;
		this.total = total;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}

	public String getBossname() {
		return bossname;
	}

	public void setBossname(String bossname) {
		this.bossname = bossname;
	}

	public String getBossphone() {
		return bossphone;
	}

	public void setBossphone(String bossphone) {
		this.bossphone = bossphone;
	}
}
