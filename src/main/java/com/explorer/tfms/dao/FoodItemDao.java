package com.explorer.tfms.dao;

import java.util.List;

import com.explorer.tfms.dao.base.BaseDao;
import com.explorer.tfms.domain.FoodItem;

public interface FoodItemDao extends BaseDao<FoodItem>{
	public List<FoodItem> findOrderFoodItems(Long orderId);
}
