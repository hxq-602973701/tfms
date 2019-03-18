package com.explorer.tfms.dao.impl;
import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.AdminShopDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.Admin;
import com.explorer.tfms.domain.AdminShop;
import com.explorer.tfms.domain.Shop;
@Repository("adminShopDao")
public class AdminShopDaoImpl extends BaseDaoImpl<AdminShop> implements AdminShopDao{
	
	public void saveShop2Admin(Shop shop, Admin admin) {
		AdminShop adminShop = new AdminShop();
		adminShop.setAdmin(admin);
		adminShop.setShop(shop);
		this.saveEntity(adminShop);
	}
	
	public AdminShop getAdminShop(Long shopId,Long adminId){
		String hql = "FROM AdminShop a WHERE a.shop.id=? AND a.admin.id=?";
		return (AdminShop) this.getEntiyByHql(hql,new Object[]{shopId,adminId});
	}
	
	public void deleteShop2Admin(Long shopId,Long adminId){
		this.deleteEntity(getAdminShop(shopId, adminId));
	}

	public void deleteAllAdminShops(Long adminId) {
		String hql = "DELETE FROM AdminShop a WHERE a.admin.id=?";
		this.executeHQL(hql,new Object[]{adminId});
	}

}
