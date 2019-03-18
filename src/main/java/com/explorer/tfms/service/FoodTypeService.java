package com.explorer.tfms.service;
import java.util.List;

import com.explorer.tfms.domain.FoodType;
import com.explorer.tfms.domain.PageBean;
public interface FoodTypeService{

	public PageBean<FoodType> findAllFoodTypes(int currentPage,int pageSize);
	
	public void saveFoodType(FoodType foodType);
	
	public FoodType getFoodTypeByName(String name);
	
	public FoodType getFoodType(Long id);
	
	public void updateFoodType(FoodType foodType);
	
	public void deleteFoodType(Long id);
	
	public List<FoodType> listPublicFoodTypes();
}
