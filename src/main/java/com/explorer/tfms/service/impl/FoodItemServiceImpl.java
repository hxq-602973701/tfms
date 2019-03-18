package com.explorer.tfms.service.impl;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.explorer.tfms.dao.FoodItemDao;
import com.explorer.tfms.domain.FoodItem;
import com.explorer.tfms.domain.Order;
import com.explorer.tfms.service.FoodItemService;
@Service("foodItemService")
public class FoodItemServiceImpl implements FoodItemService{
	@Resource(name="foodItemDao")
	private FoodItemDao foodItemDao;
	
	/**
	 * 把购物项添加到购物车中，并且判断重复
	 */
	public Order addFoodItems(FoodItem foodItem,Order order){
		boolean isHas = false;
		Set<FoodItem> foodItems = order.getFoodItems();
		for(FoodItem fi : foodItems){
			if(fi.getFood().getId().equals(foodItem.getFood().getId())){
				fi.setNumber(fi.getNumber()+1);
				isHas = true;
				break;
			}
		}
		if(!isHas){
			foodItems.add(foodItem);
		}
		return order;
	}
	
	/**
	 * 在购物车中添加
	 */
	public Order addNum(Long foodId,Order order){
		Set<FoodItem> foodItems = order.getFoodItems();
		for(FoodItem fi : foodItems){
			if(fi.getFood().getId().equals(foodId)){
				fi.setNumber(fi.getNumber()+1);
				break;
			}
		}
		return order;
	}
	
	/**
	 * 在购物车中减少数量
	 */
	public Order minuNum(Long foodId,Order order){
		Set<FoodItem> foodItems = order.getFoodItems();
		for(FoodItem fi : foodItems){
			if(fi.getFood().getId().equals(foodId)){
				fi.setNumber(fi.getNumber()-1);
				break;
			}
		}
		return order;
	}
	
	/**
	 * 在购物车中删除购物项
	 */
	public Order deleteFoodItem2Order(Long foodId,Order order){
		Set<FoodItem> foodItems = order.getFoodItems();
		for(FoodItem fi : foodItems){
			if(fi.getFood().getId().equals(foodId)){
				foodItems.remove(fi);
				break;
			}
		}
		return order;
	}

	public List<FoodItem> findOrderFoodItems(Long orderId) {
		return this.foodItemDao.findOrderFoodItems(orderId);
	}
	
	public FoodItem getFoodItem(Long foodItemId){
		return (FoodItem) this.foodItemDao.getEntity(foodItemId);
	}
}
