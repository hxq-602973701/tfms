package com.explorer.tfms.web.dto;
import java.util.List;
import com.explorer.tfms.domain.Food;
public class FoodType2Foods {
	/**
	 * 返回结果，1代表成功，0代表失败
	 */
	private String result;
	private String name;
	private List<Food> foods;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Food> getFoods() {
		return foods;
	}
	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
	
	public FoodType2Foods(String name,List<Food> foods){
		super();
		this.setResult(result);
		this.setFoods(foods);
		this.setName(name);
	}
	
	public FoodType2Foods(){
		result = "1";
	}
}
