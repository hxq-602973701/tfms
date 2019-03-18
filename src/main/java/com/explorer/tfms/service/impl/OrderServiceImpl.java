package com.explorer.tfms.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.explorer.tfms.dao.FoodItemDao;
import com.explorer.tfms.dao.OrderDao;
import com.explorer.tfms.domain.FoodItem;
import com.explorer.tfms.domain.Order;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.domain.User;
import com.explorer.tfms.service.OrderService;
import com.explorer.tfms.utils.DateUtils;
import com.explorer.tfms.utils.GenOrderNumber;
import com.explorer.tfms.utils.StringUtils;
import com.explorer.tfms.utils.TFMSException;
@Service("orderService")
public class OrderServiceImpl implements OrderService{
	
	@Resource(name="orderDao")
	private OrderDao orderDao;
	@Resource(name="foodItemDao")
	private FoodItemDao foodItemDao;
	
	/**
	 * 创建订单
	 */
	public void saveOrder(Order order,Set<FoodItem> foods,User user,Shop shop){
		order.setUser(user);
		order.setState("0");
		order.setShop(shop);
		order.setOrderDate(DateUtils.formatDate(new Date(),"yyyy-MM-dd"));
		order.setDate(DateUtils.formatDate(new Date(),"yyyy-MM-dd hh:MM:ss"));
		order.setOrdernum(GenOrderNumber.genOrderNum());
		this.orderDao.saveEntity(order);
		for(FoodItem f:foods){
			f.setOrder(order);
			this.foodItemDao.saveEntity(f);
		}
	}
	
	/**
	 * 新生成订单列表
	 * @date: 3-29 下午07:25:03
	 * @version: V1.0
	 *
	 */
	public List<Order> listnewOrders(){
		String hql = "FROM Order o WHERE o.state=?";
		return this.orderDao.listAllByArgs(hql,new Object[]{"0"});
	}
	
	public List<Order> listnewOrders(List<Long> shopIds) {
		String ids = StringUtils.getInstance().list2String(shopIds);
		String hql = "FROM Order o WHERE o.state=? AND o.shop.id IN ("+ids+")";
		return this.orderDao.listAllByArgs(hql,new Object[]{"0"});
	}
	
	/**
	 * 接受的订单列表
	 * @date: 3-29 下午07:25:03
	 * @version: V1.0
	 *
	 */
	public PageBean<Order> findAcceptOrders(int currentPage,int pageSize){
		String hql = "FROM Order o WHERE o.state=?";
		return this.orderDao.findAll(hql, currentPage, pageSize,new Object[]{"1"});
	}
	
	public PageBean<Order> findAcceptOrders(int currentPage, int pageSize,
			List<Long> shopIds) {
		String ids = StringUtils.getInstance().list2String(shopIds);
		String hql = "FROM Order o WHERE o.state=? AND o.shop.id IN ("+ids+")";
		return this.orderDao.findAll(hql, currentPage, pageSize,new Object[]{"1"});
	}
	/**
	 * 取消的订单列表
	 * @date: 3-29 下午07:25:03
	 * @version: V1.0
	 *
	 */
	public PageBean<Order> findFailOrders(int currentPage,int pageSize){
		String hql = "FROM Order o WHERE o.state=?";
		return this.orderDao.findAll(hql, currentPage, pageSize,new Object[]{"2"});
	}
	
	/**
	 * 成功订单列表
	 * @date: 3-29 下午07:25:03
	 * @version: V1.0
	 *
	 */
	public PageBean<Order> findSuccessOrders(int currentPage,int pageSize,String search){
		String hql = null;
		PageBean<Order> pageBean = new PageBean<Order>();
		if(StringUtils.getInstance().isEmpty(search)){
			hql = "FROM Order o WHERE o.state=?";
			pageBean = this.orderDao.findAll(hql, currentPage, pageSize,new Object[]{"3"});
		}else{
			hql = "FROM Order o WHERE o.state=? AND o.shop.name like ?";
			pageBean = this.orderDao.findAll(hql, currentPage, pageSize,new Object[]{"3","%"+search+"%"});
		}
		return pageBean;
	}
	
	public List<Order> findShopOrders(String search,String sdate,String edate){
		String hql = "FROM Order o WHERE o.state=? AND o.orderDate>=? AND o.orderDate<=? AND o.shop.name=?";
		return this.orderDao.listAllByArgs(hql,new Object[]{"3",sdate,edate,search});
	}

	public Order getOrder(Long id) {
		return (Order)this.orderDao.getEntity(id);
	}
	
	/**
	 * 接受订单：取消和完成的订单不能接受
	 */
	public void acceptOrder(Long orderId){
		Order order = getOrder(orderId);
		if("2".equals(order.getState()) || "3".equals(order.getState())){
			throw new TFMSException("非法操作！");
		}
		order.setState("1");
		this.orderDao.updateEntity(order);
	}
	
	/**
	 * 取消订单：完成的订单不能取消
	 */
	public void canceltOrder(Long orderId){
		Order order = getOrder(orderId);
		if("3".equals(order.getState())){
			throw new TFMSException("非法操作！");
		}
		order.setState("2");
		this.orderDao.updateEntity(order);
	}
	
	/**
	 * 恢复订单：只有取消的订单才有回复功能
	 */
	public void replaceOrder(Long orderId) {
		Order order = getOrder(orderId);
		if("2".equals(order.getState())){
			order.setState("0");
		}else{
			throw new TFMSException("非法操作！");
		}
		this.orderDao.updateEntity(order);
	}
	
	/**
	 * 完成订单
	 */
	public void finishOrder(Long orderId){
		Order order = getOrder(orderId);
		order.setState("3");
		this.orderDao.updateEntity(order);
	}
	
	/**
	 * 删除订单
	 * @date: 3-30 上午01:53:12
	 * @version: V1.0
	 *
	 */
	public void deleteOrder(Long orderId){
		List<FoodItem> foodItems = this.foodItemDao.findOrderFoodItems(orderId);
		for(FoodItem foodItem:foodItems){
			this.foodItemDao.deleteEntity(foodItem);
		}
		Order order = getOrder(orderId);
		this.orderDao.deleteEntity(order);
	}
	
	/**
	 * 通过用户Id查询所有订单
	 */
	public PageBean<Order> findUserOrders(int currentPage,int pageSize,Long userId){
		if(userId!=null){
			String hql = "FROM Order o WHERE o.user.id=? AND o.state!=?";
			return this.orderDao.findAll(hql, currentPage, pageSize,new Object[]{userId,"2"});
		}
		return null;
	}
}
