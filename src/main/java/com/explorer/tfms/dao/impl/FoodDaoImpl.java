package com.explorer.tfms.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.FoodDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.Food;
@Repository("foodDao")
public class FoodDaoImpl extends BaseDaoImpl<Food> implements FoodDao{
	public Food getFoodByName(String name,Long shopId){
		String hql = "FROM Food f WHERE f.name=? AND f.shop.id=?";
		return (Food) this.getEntiyByHql(hql,new Object[]{name,shopId});
	}
	
	/**
	 * 通过名称查询所有食物
	 * @date: 3-22 下午11:54:34
	 * @version: V1.0
	 *
	 */
	public List<Food> findFoodsByName(String name){
		String hql = "FROM Food f WHERE f.name like ?";
		return this.listAllByArgs(hql,new Object[]{"%"+name+"%"});
	}
	
	public List<Food> findShopFoods(Long shopId){
		String hql = "FROM Food f WHERE f.shop.id=?";
		return this.listAllByArgs(hql,new Object[]{shopId});
	}
}