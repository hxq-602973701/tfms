package com.explorer.tfms.web.dto;

import com.explorer.tfms.domain.FoodItem;

/**
 * 购物项
 * @author Administrator
 */
public class FoodItemDto{
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
	private Long foodId;
	/**
	 * 此订单项所属订单编号
	 */
	private Long orderId;
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
	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public FoodItem getFoodItem(){
		FoodItem foodItem = new FoodItem();
		foodItem.setId(id);
		foodItem.setName(name);
		foodItem.setNumber(number);
		foodItem.setPrice(price);
		return foodItem;
	}
	
	public FoodItemDto(){
	}
	
	public FoodItemDto(FoodItem foodItem){
		this.setId(foodItem.getId());
		this.setName(foodItem.getName());
		this.setNumber(foodItem.getNumber());
		this.setPrice(foodItem.getPrice());
	}
	
	public FoodItemDto(FoodItem foodItem,Long foodId,Long orderId){
		this.setId(foodItem.getId());
		this.setName(foodItem.getName());
		this.setNumber(foodItem.getNumber());
		this.setPrice(foodItem.getPrice());
		this.setFoodId(foodId);
		this.setOrderId(orderId);
	}
}
