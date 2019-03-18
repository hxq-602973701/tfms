package com.explorer.tfms.service;

import java.util.List;

import com.explorer.tfms.domain.FoodItem;
import com.explorer.tfms.domain.Order;

public interface FoodItemService {
	public Order addFoodItems(FoodItem foodItem,Order order);
	
	public Order addNum(Long foodId,Order order);
	
	public Order minuNum(Long foodId,Order order);
	
	public Order deleteFoodItem2Order(Long foodId,Order order);
	
	public List<FoodItem> findOrderFoodItems(Long orderId);
	
	public FoodItem getFoodItem(Long foodItemId);
}
