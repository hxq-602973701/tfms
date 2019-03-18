package com.explorer.tfms.service.impl;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.explorer.tfms.dao.FoodDao;
import com.explorer.tfms.dao.FoodTypeDao;
import com.explorer.tfms.dao.ShopDao;
import com.explorer.tfms.domain.Food;
import com.explorer.tfms.domain.FoodType;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.service.FoodService;
import com.explorer.tfms.utils.DateUtils;
import com.explorer.tfms.utils.TFMSException;
@Service("foodService")
public class FoodServiceImpl implements FoodService{
	@Resource(name="foodDao")
	private FoodDao foodDao;
	@Resource(name="foodTypeDao")
	private FoodTypeDao foodTypeDao;
	@Resource(name="shopDao")
	private ShopDao shopDao;
	
	/**
	 * 分页查询所有商品
	 * @param currentPage  当前页
	 * @param pageSize   每页显示的条数
	 * @date: 3-15 下午05:53:58
	 * @version: V1.0
	 *
	 */
	public PageBean<Food> findAllFoods(int currentPage,int pageSize){
		String hql = "FROM Food";
		return this.foodDao.findAll(hql,currentPage,pageSize);
	}

	/**
	 * 保存商品
	 */
	public void saveFood(Food food, Long shopId, Long foodTypeId) {
		Food f = this.foodDao.getFoodByName(food.getName(),shopId);
		if(f!=null){
			throw new TFMSException("改商铺的这个食物名称已经存在！");
		}
		Shop shop = (Shop)this.shopDao.getEntity(shopId);
		FoodType foodType = (FoodType)this.foodTypeDao.getEntity(foodTypeId);
		food.setShop(shop);
		food.setFoodType(foodType);
		this.foodDao.saveEntity(food);
	}
	
	/**
	 * 通过Id得到food
	 * @param id
	 * @return:
	 * @throws: 
	 * @date: 3-15 下午11:10:40
	 * @version: V1.0
	 *
	 */
	public Food getFood(Long id){
		return (Food)this.foodDao.getEntity(id);
	}

	/**
	 * 修改方法
	 */
	public void updateFood(Food food, Long shopId, Long foodTypeId){
		Shop shop = (Shop)this.shopDao.getEntity(shopId);
		FoodType foodType = (FoodType)this.foodTypeDao.getEntity(foodTypeId);
		food.setShop(shop);
		food.setFoodType(foodType);
		this.foodDao.updateEntity(food);
	}

	/**
	 * 是否为推荐商品
	 */
	public void recommondFood(Long id, String type) {
		Food food = (Food)this.foodDao.getEntity(id);
		food.setIsrecommend(type);
		food.setRecommendTime(DateUtils.formatDate(new Date(),"yyyy-MM-dd"));
		this.foodDao.updateEntity(food);
	}
	
	/**
	 * 通过id删除对象
	 */
	public void deleteFood(Long id) {
		//TODO 
		Food food = (Food)this.foodDao.getEntity(id);
		this.foodDao.deleteEntity(food);
	}
	
	/**
	 * 查询所有推荐商品
	 */
	public List<Food> listRecommendFoods(){
		String hql = "FROM Food f WHERE f.isrecommend=?";
		List<Food> foodList = this.foodDao.listAllDESC(hql,"recommendTime","1");
		if(foodList.size()>6)foodList = foodList.subList(0,6);
		return foodList;
	}
	
	/**
	 * 通过商品种类获得商铺商品
	 */
	public List<Food> listAllFoodsByType(Long foodTypeId,Long shopId){
		String hql = "FROM Food f WHERE f.foodType.id=? AND f.shop.id=?";
		return this.foodDao.listAllByArgs(hql,new Object[]{foodTypeId,shopId});
	}

	/**
	 * 通过名称获得食物列表
	 */
	public List<Food> findFoodsByName(String name) {
		return this.foodDao.findFoodsByName(name);
	}
}
