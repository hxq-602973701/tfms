package com.explorer.tfms.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 美食实体类
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_foods")
public class Food implements java.io.Serializable{
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
	 * 美食现在价格
	 */
	private float newprice;
	/**
	 * 图片
	 */
	private String url;
	/**
	 * 是否推荐 0不推荐 1推荐
	 */
	private String isrecommend = "0";
	/**
	 * 推荐时间
	 */
	private String recommendTime;
	/**
	 * 是否下架 0代表下架 1代表上架
	 */
	private String isdown = "1";
	/**
	 * 所属类型
	 */
	private FoodType foodType;
	/**
	 * 所属商铺
	 */
	private Shop shop;
	/**
	 * 描述
	 */
	private String desription;
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getOldprice() {
		return oldprice;
	}
	public void setOldprice(float oldprice) {
		this.oldprice = oldprice;
	}
	
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
	
	public String getIsrecommend() {
		return isrecommend;
	}
	public void setIsrecommend(String isrecommend) {
		this.isrecommend = isrecommend;
	}
	public String getRecommendTime() {
		return recommendTime;
	}
	public void setRecommendTime(String recommendTime) {
		this.recommendTime = recommendTime;
	}
	public String getIsdown() {
		return isdown;
	}
	public void setIsdown(String isdown) {
		this.isdown = isdown;
	}
	@ManyToOne
	@JoinColumn(name="foodType_id")
	public FoodType getFoodType() {
		return foodType;
	}
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}
	@ManyToOne
	@JoinColumn(name="shop_id")
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public String getDesription() {
		return desription;
	}
	public void setDesription(String desription) {
		this.desription = desription;
	}
}
