package com.explorer.tfms.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 管理员的权限控制
 * 每个商铺的负责人只有一个
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_adminshops")
public class AdminShop implements java.io.Serializable{
	private Long id;
	private Admin admin;
	private Shop shop;
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name="adminId")
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	@ManyToOne
	@JoinColumn(name="shopId")
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
