package com.explorer.tfms.service;
import java.util.List;
import java.util.Set;
import com.explorer.tfms.domain.FoodItem;
import com.explorer.tfms.domain.Order;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.domain.User;

public interface OrderService {
	
	public void saveOrder(Order order,Set<FoodItem> foods,User user,Shop shop);
	
	public List<Order> listnewOrders();
	
	public PageBean<Order> findAcceptOrders(int currentPage,int pageSize);
	
	public PageBean<Order> findFailOrders(int currentPage,int pageSize);
	
	public PageBean<Order> findSuccessOrders(int currentPage,int pageSize,String search);
	
	public List<Order> findShopOrders(String search,String sdate,String edate);
	
	public Order getOrder(Long id);
	
	public void acceptOrder(Long orderId);
	
	/**
	 * 取消订单
	 */
	public void canceltOrder(Long orderId);
	
	/**
	 * 完成订单
	 */
	public void finishOrder(Long orderId);
	
	/**
	 * 删除订单
	 * @date: 3-30 上午01:53:12
	 * @version: V1.0
	 *
	 */
	public void deleteOrder(Long orderId);
	
	public PageBean<Order> findUserOrders(int currentPage,int pageSize,Long userId);

	public void replaceOrder(Long orderId);

	public List<Order> listnewOrders(List<Long> shopIds);

	public PageBean<Order> findAcceptOrders(int currentPage, int pageSize,
			List<Long> shopIds);

}