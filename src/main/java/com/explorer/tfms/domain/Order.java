package com.explorer.tfms.domain;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 订单实体类(也就是购物车实体类)
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_orders")
public class Order implements java.io.Serializable{
	private Long id;
	/**
	 * 订单号（UUID自动生成）
	 */
	private String ordernum;
	/**
	 * 收餐人姓名
	 */
	private String name;
	/**
	 * 收餐人手机号
	 */
	private String phone;
	/**
	 * 总价格
	 */
	private float total;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 下单日期
	 */
	private String date;
	/**
	 * 下单时间
	 */
	private String orderDate;
	/**
	 * 订单地址
	 */
	private String address;
	/**
	 * 订单状态 0，系统正在处理，1接收的订单，2，取消的订单 3，完成的订单
	 */
	private String state;
	/**
	 * 订单支付形式1，在线支付，0代表货到付款
	 */
	private String payType;
	/**
	 * 购物车中要存储商品(购物项)
	 */
	private Set<FoodItem> foodItems=new HashSet<FoodItem>();
	
	private User user;
	
	private Shop shop;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@OneToMany(mappedBy="order")
	//@Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE}) //级联添加
	public Set<FoodItem> getFoodItems() {
		return foodItems;
	}
	public void setFoodItems(Set<FoodItem> foodItems) {
		this.foodItems = foodItems;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="shopId")
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
}
