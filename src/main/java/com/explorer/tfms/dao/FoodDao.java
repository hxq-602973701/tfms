package com.explorer.tfms.dao;
import java.util.List;

import com.explorer.tfms.dao.base.BaseDao;
import com.explorer.tfms.domain.Food;
public interface FoodDao extends BaseDao<Food>{
	public Food getFoodByName(String name,Long shopId);
	
	public List<Food> findFoodsByName(String name);
	
	public List<Food> findShopFoods(Long shopId);
}