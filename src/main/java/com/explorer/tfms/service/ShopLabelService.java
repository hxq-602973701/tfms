package com.explorer.tfms.service;

import java.util.List;

import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.ShopLabel;

public interface ShopLabelService {
	
	public PageBean<ShopLabel> findAllShopLabels(int currentPage,int pageSize);
	
	public List<ShopLabel> listActiveShopLabels();
	
	public ShopLabel getShopLabelByName(String name);
	
	public void saveShopLabel(ShopLabel shopLabel);
	
	public ShopLabel getShopLabel(Long id);
	
	public void updateShopLabel(ShopLabel shopLabel);
	
	public void deleteShopLabel(Long id);
}
