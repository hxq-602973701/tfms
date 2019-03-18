package com.explorer.tfms.web.dto;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import com.explorer.tfms.domain.Food;
/**
 * 美食Dto
 * @author Administrator
 */
public class FoodDto {
	private Long id;
	/**
	 * 美食名称
	 */
	private String name;
	/**
	 * 美食原来价格
	 */
	private float oldprice;
	/**
	 * 美食现在折扣
	 */
	private float newprice;
	/**
	 * 图片
	 */
	private String url;
	/**
	 * 是否推荐，默认不推荐
	 */
	private String isrecommend = "0";
	/**
	 * 是否下架
	 */
	private String isdown;
	/**
	 * 所属类型
	 */
	private Long foodTypeId;
	/**
	 * 所属商铺
	 */
	private Long shopId;
	/**
	 * 描述
	 */
	private String desription;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotEmpty(message="商品名称不能为空！")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotNull(message="商品的原价格不能为空！")
	public float getOldprice() {
		return oldprice;
	}
	public void setOldprice(float oldprice) {
		this.oldprice = oldprice;
	}
	@NotNull(message="商品折扣不能为空！")
	public float getNewprice() {
		return newprice;
	}
	public void setNewprice(float newprice) {
		this.newprice = newprice;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@NotEmpty(message="商品是否推荐不能为空！")
	public String getIsrecommend() {
		return isrecommend;
	}
	public void setIsrecommend(String isrecommend) {
		this.isrecommend = isrecommend;
	}
	@NotEmpty(message="商品是否上架不能为空！")
	public String getIsdown() {
		return isdown;
	}
	public void setIsdown(String isdown) {
		this.isdown = isdown;
	}
	@NotNull(message="商品类型不能为空！")
	public Long getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(Long foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	@NotNull(message="商品所属商店不能为空！")
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getDesription() {
		return desription;
	}
	public void setDesription(String desription) {
		this.desription = desription;
	}
	public FoodDto(){}
	
	public Food getFood(){
		Food f = new Food();
		f.setNewprice(newprice);
		f.setIsdown(isdown);
		f.setIsrecommend(isrecommend);
		f.setName(name);
		f.setOldprice(oldprice);
		f.setUrl(url);
		f.setId(id);
		f.setDesription(desription);
		return f;
	}
	
	public FoodDto(Food food){
		this.setNewprice(food.getNewprice());
		this.setId(food.getId());
		this.setIsdown(food.getIsdown());
		this.setIsrecommend(food.getIsrecommend());
		this.setName(food.getName());
		this.setOldprice(food.getOldprice());
		this.setUrl(food.getUrl());
		this.setDesription(food.getDesription());
	}
	
	public FoodDto(Food food,Long foodTypeId,Long shopId){
		this.setFoodTypeId(foodTypeId);
		this.setNewprice(food.getNewprice());
		this.setId(food.getId());
		this.setIsdown(food.getIsdown());
		this.setIsrecommend(food.getIsrecommend());
		this.setName(food.getName());
		this.setOldprice(food.getOldprice());
		this.setUrl(food.getUrl());
		this.setShopId(shopId);
		this.setDesription(food.getDesription());
	}
}
