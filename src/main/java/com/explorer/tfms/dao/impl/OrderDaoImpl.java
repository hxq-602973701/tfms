package com.explorer.tfms.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.OrderDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.Order;
@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao{

	public List<Order> findUserOrders(Long userId){
		String hql = "FROM Order o WHERE o.user.id=?";
		return this.listAllByArgs(hql,new Object[]{userId});
	}

	public List<Order> findShopOrders(Long shopId){
		String hql = "FROM Order o WHERE o.shop.id=?";
		return this.listAllByArgs(hql,new Object[]{shopId});
	}
} 