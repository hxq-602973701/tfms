package com.explorer.tfms.web.dto;
import java.util.List;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import com.explorer.tfms.domain.Shop;

public class ShopDto {
	private Long id;
	/**
	 * 商铺名称
	 */
	private String name;
	/**
	 * 商铺地址
	 */
	private String address;
	/**
	 * 是否推荐，默认不推荐
	 */
	private String isrecommend = "0";
	/**
	 * 商铺老板名称
	 */
	private String bossname;
	/**
	 * 商铺老板联系方式
	 */
	private String bosstel;
	/**
	 * 是否禁用
	 */
	private String state;
	/**
	 * url
	 */
	private String url;
	/**
	 * 商铺备注
	 */
	private String description;
	/**
	 * 是否休息，默认正在营业
	 */
	private String isrest = "1";
	
	private Long[] shopLabelIds;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotEmpty(message="商铺名称不能为空！")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotEmpty(message="商铺地址不能为空！")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@NotEmpty(message="负责人姓名不能为空！")
	public String getBossname() {
		return bossname;
	}
	public void setBossname(String bossname) {
		this.bossname = bossname;
	}
	@NotEmpty(message="是否推荐条目不能为空！")
	public String getIsrecommend() {
		return isrecommend;
	}
	public void setIsrecommend(String isrecommend) {
		this.isrecommend = isrecommend;
	}
	@Pattern(message="手机号的格式不正确！",regexp ="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$")
	public String getBosstel() {
		return bosstel;
	}
	public void setBosstel(String bosstel) {
		this.bosstel = bosstel;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@NotEmpty(message="商铺状态不能为空！")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsrest() {
		return isrest;
	}
	public void setIsrest(String isrest) {
		this.isrest = isrest;
	}
	@NotEmpty(message="商铺标签条目不能为空！")
	public Long[] getShopLabelIds() {
		return shopLabelIds;
	}
	public void setShopLabelIds(Long[] shopLabelIds) {
		this.shopLabelIds = shopLabelIds;
	}
	public ShopDto(){
		
	}
	
	public Shop getShop(){
		Shop shop = new Shop();
		shop.setAddress(address);
		shop.setBossname(bossname);
		shop.setBosstel(bosstel);
		shop.setState(state);
		shop.setDescription(description);
		shop.setId(id);
		shop.setIsrecommend(isrecommend);
		shop.setName(name);
		shop.setUrl(url);
		shop.setIsrest(isrest);
		return shop;
	}
	
	public ShopDto(Shop shop){
		this.setAddress(shop.getAddress());
		this.setBossname(shop.getBossname());
		this.setBosstel(shop.getBosstel());
		this.setState(shop.getState());
		this.setDescription(shop.getDescription());
		this.setId(shop.getId());
		this.setIsrecommend(shop.getIsrecommend());
		this.setName(shop.getName());
		this.setUrl(shop.getUrl());
		this.setIsrest(shop.getIsrest());
	}

	public ShopDto(Shop shop,Long[] shopLabelIds){
		this.setAddress(shop.getAddress());
		this.setBossname(shop.getBossname());
		this.setBosstel(shop.getBosstel());
		this.setState(shop.getState());
		this.setDescription(shop.getDescription());
		this.setId(shop.getId());
		this.setIsrecommend(shop.getIsrecommend());
		this.setName(shop.getName());
		this.setShopLabelIds(shopLabelIds);
		this.setUrl(shop.getUrl());
		this.setIsrest(shop.getIsrest());
	}
	
	public ShopDto(Shop shop,List<Long> shopLabelIds){
		this.setAddress(shop.getAddress());
		this.setBossname(shop.getBossname());
		this.setBosstel(shop.getBosstel());
		this.setState(shop.getState());
		this.setDescription(shop.getDescription());
		this.setId(shop.getId());
		this.setIsrecommend(shop.getIsrecommend());
		this.setName(shop.getName());
		this.setShopLabelIds(list2Array(shopLabelIds));
		this.setUrl(shop.getUrl());
		this.setIsrest(shop.getIsrest());
	}
	
	private Long[] list2Array(List<Long> datas) {
		Long[] nums = new Long[datas.size()];
		for(int i=0;i<datas.size();i++) {
			nums[i] = datas.get(i);
		}
		return nums;
	}
}
