package com.explorer.tfms.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.FoodItemDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.FoodItem;
@Repository("foodItemDao")
public class FoodItemDaoImpl extends BaseDaoImpl<FoodItem> implements FoodItemDao{
	
	public List<FoodItem> findOrderFoodItems(Long orderId){
		String hql = "FROM FoodItem f WHERE f.order.id=?";
		return this.listAllByArgs(hql,new Object[]{orderId});
	}
}
