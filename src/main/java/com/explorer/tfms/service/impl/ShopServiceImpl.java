package com.explorer.tfms.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;

import com.explorer.tfms.dao.FoodDao;
import com.explorer.tfms.dao.OrderDao;
import com.explorer.tfms.dao.ShopDao;
import com.explorer.tfms.dao.ShopLabelDao;
import com.explorer.tfms.domain.Food;
import com.explorer.tfms.domain.Order;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.domain.ShopLabel;
import com.explorer.tfms.service.ShopService;
import com.explorer.tfms.utils.DateUtils;
import com.explorer.tfms.utils.TFMSException;
@Service("shopService")
public class ShopServiceImpl implements ShopService{
	@Resource(name="shopDao")
	private ShopDao shopDao;
	@Resource(name="shopLabelDao")
	private ShopLabelDao shopLabelDao;
	@Resource(name="orderDao")
	private OrderDao orderDao;
	@Resource(name="foodDao")
	private FoodDao foodDao;
	
	/**
	 * 分页查询所有商铺
	 */
	public PageBean<Shop> findAllShops(int currentPage, int pageSize) {
		String hql = "FROM Shop";
		return this.shopDao.findAll(hql, currentPage, pageSize);
	}

	/**
	 * 保存Shop_ShopLabel对象
	 */
	private void saveShopShopLabel(Shop shop,Long shopLabelId){
		ShopLabel shopLabel = (ShopLabel)this.shopLabelDao.getEntity(shopLabelId);
		if(shopLabel==null){
			throw new TFMSException("添加的商铺标签不存在！");
		}
		this.shopDao.saveShopShopLabel(shop,shopLabel);
	}
	
	public List<Long> listShopLabelds(Long shopId){
		return this.shopDao.listShopShopLabelIds(shopId);
	}
	
	/**
	 * 保存商铺
	 */
	public void saveShop(Shop shop,Long[] shopLabelIds) {
		Shop s = getShopByName(shop.getName());
		if(s!=null){
			throw new TFMSException("商铺名称已经存在！");
		}
		shop.setIsrest("1"); // 添加商铺默认正在营业
		//保存商铺
		this.shopDao.saveEntity(shop);
		//添加商铺标签对象
		for(Long shopLabelId:shopLabelIds){
			this.saveShopShopLabel(shop,shopLabelId);
		}
	}

	/**
	 * 通过商铺名称得到商铺
	 */
	public Shop getShopByName(String name) {
		String hql = "FROM Shop s WHERE s.name=?";
		return (Shop)this.shopDao.getEntiyByHql(hql,new Object[]{name});
	}

	/**
	 * 通过Id得到店铺
	 */
	public Shop getShop(Long id) {
		return (Shop)this.shopDao.getEntity(id);
	}

	/**
	 * 更新方法
	 */
	public void updateShop(Shop shop,Long[] shopLabelIds) {
		//先对shop修改
		this.shopDao.updateEntity(shop); //这句代码放在前面
		//数据库中存在的Id集合
		List<Long> eshopLabelIds = this.shopDao.listShopShopLabelIds(shop.getId());
		//1、判断，如果eshopLabelIds中不存在shopLabelIds就要进行添加
		for(Long slId:shopLabelIds){
			if(!eshopLabelIds.contains(slId)){
				saveShopShopLabel(shop,slId);
			}
		}
		//2、进行删除
		for(Long eslId:eshopLabelIds){
			//数据库中存在的id 是否包含在 修改选择的Id中，不在就删除
			if(!ArrayUtils.contains(shopLabelIds,eslId)){
				this.shopDao.deleteShopShopLabel(shop.getId(),eslId);
			}
		}
	}

	/**
	 * 删除商铺
	 */
	public void deleteShop(Long id) {
		List<Order> orderList = this.orderDao.findShopOrders(id);
		if(orderList!=null){
			throw new TFMSException("改商铺下有订单，不能删除！");
		}
		List<Food> foodList = this.foodDao.findShopFoods(id);
		if(foodList!=null){
			throw new TFMSException("改商铺下有食品，不能删除！");
		}
		Shop shop = getShop(id);
		this.shopDao.deleteEntity(shop);
	}

	/**
	 * 查询所有发布的店家
	 */
	public List<Shop> listAllPublicShops() {
		String hql = "FROM Shop s WHERE s.state=?";
		return this.shopDao.listAllByArgs(hql,"1");
	}

	/**
	 * 是否推荐方法
	 */
	public void recommondShop(Long id, String type) {
		Shop shop = (Shop)this.shopDao.getEntity(id);
		shop.setIsrecommend(type);
		shop.setRecommendTime(DateUtils.formatDate(new Date(),"yyyy-MM-dd"));
		this.shopDao.updateEntity(shop);
	}
	
	/**
	 * 是否营业方法
	 */
	public void restShop(Long id, String type) {
		Shop shop = (Shop)this.shopDao.getEntity(id);
		shop.setIsrest(type);
		this.shopDao.updateEntity(shop);
	}
	
	/**
	 * 查询按时间查询的前六个商铺
	 * @date: 3-16 下午03:26:03
	 * @version: V1.0
	 *
	 */
	public List<Shop> findAllRecommendShops(){
		String hql = "FROM Shop s WHERE s.isrecommend=?";
		List<Shop> shopList = this.shopDao.listAllDESC(hql,"recommendTime","1");
		if(shopList.size()>6)shopList = shopList.subList(0,6);
		return shopList;
	}
	
	/**
	 * 通过标签查询所有商铺
	 * @param shopLabelId
	 * @date: 3-22 下午02:52:09
	 * @version: V1.0
	 *
	 */
	public List<Shop> listShoLabelShops(Long shopLabelId){
		return this.shopDao.listShoLabelShops(shopLabelId);
	}

	/**
	 * 通过名字获得商铺列表
	 */
	public List<Shop> findShopsByName(String name) {
		return this.shopDao.findShopsByName(name);
	}
	
	/**
	 * 找到没有分配责任人的所有商铺
	 */
	public List<Shop> listLeaveShops(){
		List<Shop> shops = new ArrayList<Shop>();
		
		//所有店铺列表
		List<Shop> shopList = this.shopDao.listAll();
		
		//已经分配责任人的商铺列表
		List<Shop> shopAdminList = this.shopDao.listAdminShops();
		
		//求他们的差集
		for(Shop shop:shopList){
			if(!shopAdminList.contains(shop)){
				shops.add(shop);
			}
		}
		
		return shops;
	}
	
	public List<Long> listAdminShops(Long adminId){
		return this.shopDao.listAdminShops(adminId);
	}
}
