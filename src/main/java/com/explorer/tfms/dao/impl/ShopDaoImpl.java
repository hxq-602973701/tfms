package com.explorer.tfms.dao.impl;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.ShopDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.domain.ShopLabel;
import com.explorer.tfms.domain.Shop_ShopLabel;
@Repository("shopDao")
public class ShopDaoImpl extends BaseDaoImpl<Shop> implements ShopDao{
	/**
	 * 通过商铺标签获得商铺
	 * @param shopId  商铺Id
	 * @date: 3-11 下午12:58:30
	 * @version: V1.0
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<Long> listShopShopLabelIds(Long shopId) {
		String hql = "SELECT s.shopLabel.id FROM Shop_ShopLabel s WHERE s.shop.id=?";
		return this.getSession().createQuery(hql).setParameter(0,shopId).list();
	}
	
	/**
	 * 通过商店标签获得商店列表
	 * @param shopLabelId  商店标签
	 * @date: 3-22 下午02:46:30
	 * @version: V1.0
	 *
	 */
	public List<Shop> listShoLabelShops(Long shopLabelId){
		String hql = "SELECT s.shop FROM Shop_ShopLabel s WHERE s.shopLabel.id=?";
		return this.listAllByArgs(hql,new Object[]{shopLabelId});
	}
	
	/**
	 * 通过商铺和商铺标签id获得Shop_ShopLabel对象
	 * @param shopId  商铺Id
	 * @param shopLabelId  商铺标签Id
	 * @date: 3-11 下午01:15:51
	 * @version: V1.0
	 *
	 */
	public Shop_ShopLabel loadShopShopLabel(Long shopId,Long shopLabelId){
		String hql = "FROM Shop_ShopLabel s WHERE s.shop.id=? AND s.shopLabel.id=?";
		return (Shop_ShopLabel)this.getSession().createQuery(hql)
				   .setParameter(0,shopId)
				   .setParameter(1,shopLabelId)
				   .uniqueResult();
	}
	
	/**
	 * 保存商铺商铺标签对象
	 * @param shop  商铺
	 * @param shopLabel 商铺标签
	 * @date: 3-11 下午01:16:44
	 * @version: V1.0
	 *
	 */
	public void saveShopShopLabel(Shop shop,ShopLabel shopLabel){
		Shop_ShopLabel s = this.loadShopShopLabel(shop.getId(),shopLabel.getId());
		if(s!=null) {
			return;
		}
		s = new Shop_ShopLabel();
		s.setShop(shop);
		s.setShopLabel(shopLabel);
		this.getSession().save(s);
	}
	
	public void deleteShopShopLabel(Long shopId,Long shopLabelId){
		String hql = "DELETE FROM Shop_ShopLabel s WHERE s.shop.id=? AND s.shopLabel.id=?";
		Shop_ShopLabel s = this.loadShopShopLabel(shopId,shopLabelId);
		if(s==null) return;
		this.getSession().createQuery(hql)
			.setParameter(0,shopId)
			.setParameter(1,shopLabelId)
			.executeUpdate();
	}
	
	/**
	 * 通过商铺标签查询商铺
	 * @param shoplabelId  商铺标签Id
	 * @date: 3-11 下午01:18:22
	 * @version: V1.0
	 *
	 */
	public List<Shop> listShopsByShopLabel(Long shoplabelId){
		String hql = "SELECT s.shop FROM Shop_ShopLabel s WHERE s.shopLabel.id=?";
		return this.listAllByArgs(hql,new Object[]{shoplabelId});
	}
	
	/**
	 * 通过商铺名称模糊查询
	 */
	public List<Shop> findShopsByName(String name){
		String hql = "FROM Shop s WHERE s.name like ?";
		return this.listAllByArgs(hql,new Object[]{'%' + name + '%' });
	}
	
	/**
	 * 查询那些已经配分配责任
	 */
	public List<Shop> listAdminShops(){
		String hql = "SELECT a.shop FROM AdminShop a";
		return this.listAllByArgs(hql);
	}

	@SuppressWarnings("unchecked")
	public List<Long> listAdminShops(Long adminId){
		String hql = "SELECT a.shop.id FROM AdminShop a WHERE a.admin.id=?";
		return this.getSession().createQuery(hql).setParameter(0,adminId).list();
	}
}