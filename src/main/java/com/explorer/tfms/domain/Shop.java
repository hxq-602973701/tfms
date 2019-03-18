package com.explorer.tfms.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 商铺
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_shops")
public class Shop implements java.io.Serializable{
	
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
	 * 是否推荐,1推荐 0不推荐
	 */
	private String isrecommend = "0";
	/**
	 * 推荐时间
	 */
	private String recommendTime;
	/**
	 * 商铺老板名称
	 */
	private String bossname;
	/**
	 * 是否禁用,0禁用，1启用
	 */
	private String state = "0";
	/**
	 * 是否休息0休息，1代表营业
	 */
	private String isrest = "1";
	/**
	 * 商铺老板联系方式
	 */
	private String bosstel;
	/**
	 * 商铺备注
	 */
	private String description;
	/**
	 * 图片url地址
	 */
	private String url;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBossname() {
		return bossname;
	}
	public void setBossname(String bossname) {
		this.bossname = bossname;
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
	public String getBosstel() {
		return bosstel;
	}
	public void setBosstel(String bosstel) {
		this.bosstel = bosstel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsrest() {
		return isrest;
	}
	public void setIsrest(String isrest) {
		this.isrest = isrest;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}