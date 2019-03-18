package com.explorer.tfms.service;

public interface AdminShopService {
	public void saveShop2Admin(Long[] shopIds,Long adminId);

	public void deleteAllAdminShops(Long adminId);
}
