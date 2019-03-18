package com.explorer.tfms.service;
import java.util.List;

import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.Shop;

public interface ShopService {
	
	public PageBean<Shop> findAllShops(int currentPage,int pageSize);
	
	public void saveShop(Shop shop,Long[] shopLabelIds);
	
	public List<Long> listShopLabelds(Long shopId);
	
	public Shop getShopByName(String name);
	
	public Shop getShop(Long id);
	
	public void updateShop(Shop shop,Long[] shopLabelIds);
	
	public void deleteShop(Long id);
	
	public List<Shop> listAllPublicShops();

	public void recommondShop(Long id, String type);
	
	public void restShop(Long id, String type);
	
	public List<Shop> findAllRecommendShops();
	
	public List<Shop> listShoLabelShops(Long shopLabelId);
	
	public List<Shop> findShopsByName(String name);
	
	public List<Shop> listLeaveShops();
	
	public List<Long> listAdminShops(Long adminId);
}
