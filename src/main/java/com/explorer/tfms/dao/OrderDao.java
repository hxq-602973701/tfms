package com.explorer.tfms.dao;
import java.util.List;

import com.explorer.tfms.dao.base.BaseDao;
import com.explorer.tfms.domain.Order;

public interface OrderDao extends BaseDao<Order>{
	public List<Order> findUserOrders(Long userId);
	
	public List<Order> findShopOrders(Long shopId);
}
