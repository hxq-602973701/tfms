package com.explorer.tfms.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 购物项
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_foodItems")
public class FoodItem implements java.io.Serializable{
	private Long id;
	/**
	 * 购买商品名称
	 */
	private String name;
	/**
	 * 购买商品价格
	 */
	private float price;
	/**
	 * 购买商品数量
	 */
	private int number;
	/**
	 * 所属商品
	 */
	private Food food;
	/**
	 * 此订单项所属订单编号
	 */
	private Order order;
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@ManyToOne
	@JoinColumn(name="foodId")
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	@ManyToOne
	@JoinColumn(name="orderId")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
