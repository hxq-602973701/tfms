package com.explorer.tfms.service;
import java.util.List;
import com.explorer.tfms.domain.Food;
import com.explorer.tfms.domain.PageBean;

public interface FoodService {
	
	public PageBean<Food> findAllFoods(int currentPage,int pageSize);

	public void saveFood(Food food, Long shopId, Long foodTypeId);

	public Food getFood(Long id);

	public void updateFood(Food food, Long shopId, Long foodTypeId);

	public void deleteFood(Long id);

	public void recommondFood(Long id, String type);
	
	public List<Food> listRecommendFoods();
	
	public List<Food> listAllFoodsByType(Long foodTypeId,Long shopId);
	
	public List<Food> findFoodsByName(String name);
}
