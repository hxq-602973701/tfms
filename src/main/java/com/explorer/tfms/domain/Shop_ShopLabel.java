package com.explorer.tfms.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 商品和商品种类的中间表
 * @author Administrator
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_shoplabel_shops")
public class Shop_ShopLabel implements java.io.Serializable{
	private Long id;
	private ShopLabel shopLabel;
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
	@JoinColumn(name="shopLabel_id")
	public ShopLabel getShopLabel() {
		return shopLabel;
	}
	public void setShopLabel(ShopLabel shopLabel) {
		this.shopLabel = shopLabel;
	}
	@ManyToOne
	@JoinColumn(name="shop_id")
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
