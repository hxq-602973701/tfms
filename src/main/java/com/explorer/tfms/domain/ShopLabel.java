package com.explorer.tfms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 食品类型实体类
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_shopLabels")
public class ShopLabel implements java.io.Serializable{
	private Long id;
	/**
	 * 食品类型名称
	 */
	private String name;
	/**
	 * 是否启用，0为不启用，1为启用
	 */
	private String state = "0";
	/**
	 * 食品类型描述
	 */
	private String description;
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotEmpty(message="商铺标签名称不能为空！")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotEmpty(message="商铺标签状态不能为空！")
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
}
