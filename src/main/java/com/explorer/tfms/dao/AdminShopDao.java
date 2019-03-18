package com.explorer.tfms.dao;

import com.explorer.tfms.dao.base.BaseDao;
import com.explorer.tfms.domain.Admin;
import com.explorer.tfms.domain.AdminShop;
import com.explorer.tfms.domain.Shop;

public interface AdminShopDao extends BaseDao<AdminShop>{
	public void saveShop2Admin(Shop shop,Admin admin);
	
	public AdminShop getAdminShop(Long shopId,Long adminId);
	
	public void deleteShop2Admin(Long shopId,Long adminId);

	public void deleteAllAdminShops(Long adminId);
}
