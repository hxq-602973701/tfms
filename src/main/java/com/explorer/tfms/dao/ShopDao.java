package com.explorer.tfms.dao;

import java.util.List;

import com.explorer.tfms.dao.base.BaseDao;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.domain.ShopLabel;
import com.explorer.tfms.domain.Shop_ShopLabel;

public interface ShopDao extends BaseDao<Shop>{
	/**
	 * 通过商铺标签获得商铺
	 * @param shopId  商铺Id
	 * @date: 3-11 下午12:58:30
	 * @version: V1.0
	 *
	 */
	public List<Long> listShopShopLabelIds(Long shopId);
	
	public List<Shop> listShoLabelShops(Long shopLabelId);
	
	/**
	 * 通过商铺和商铺标签id获得Shop_ShopLabel对象
	 * @param shopId  商铺Id
	 * @param shopLabelId  商铺标签Id
	 * @date: 3-11 下午01:15:51
	 * @version: V1.0
	 *
	 */
	public Shop_ShopLabel loadShopShopLabel(Long shopId,Long shopLabelId);
	
	/**
	 * 保存商铺商铺标签对象
	 * @param shop  商铺
	 * @param shopLabel 商铺标签
	 * @date: 3-11 下午01:16:44
	 * @version: V1.0
	 *
	 */
	public void saveShopShopLabel(Shop shop,ShopLabel shopLabel);
	
	public void deleteShopShopLabel(Long shopId,Long shopLabelId);
	
	/**
	 * 通过商铺标签查询商铺
	 * @param shoplabelId  商铺标签Id
	 * @date: 3-11 下午01:18:22
	 * @version: V1.0
	 *
	 */
	public List<Shop> listShopsByShopLabel(Long shoplabelId);
	
	public List<Shop> findShopsByName(String name);
	
	public List<Shop> listAdminShops();
	
	public List<Long> listAdminShops(Long adminId);
}
