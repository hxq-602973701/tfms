package com.explorer.tfms.web.controller;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.explorer.tfms.domain.Admin;
import com.explorer.tfms.domain.FoodItem;
import com.explorer.tfms.domain.Order;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.service.FoodItemService;
import com.explorer.tfms.service.OrderService;
import com.explorer.tfms.service.ShopService;
import com.explorer.tfms.utils.TFMSException;
import com.explorer.tfms.web.dto.OrderTotalDto;
@Controller("orderController")
@RequestMapping("/order/")
public class OrderController {
	
	@Resource(name="orderService")
	private OrderService orderService;
	@Resource(name="foodItemService")
	private FoodItemService foodItemService;
	@Resource(name="shopService")
	private ShopService shopService;
	
	/**
	 * 新的订单列表
	 * @date: 3-29 下午07:19:52
	 * @version: V1.0
	 *
	 */
	@RequestMapping("newOrderList")
	public String newOrderList(Model model){
		//model.addAttribute("orderList",this.orderService.listnewOrders());
		return "order/newOrderList";
	}
	
	@RequestMapping("newAddOrderList")
	public void newAddOrderList(Model model,HttpServletResponse response,HttpSession session) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		Admin admin = (Admin)session.getAttribute("admin");
		List<Long> shopIds = this.shopService.listAdminShops(admin.getId());
		JSONArray jsonArray = new JSONArray();
		//具体店铺的订单
		List<Order> orderList = this.orderService.listnewOrders(shopIds);
		for(Order order:orderList){
			JSONObject json = new JSONObject();
			json.put("id",order.getId());
			json.put("shopname",order.getShop().getName());
			json.put("ordernum",order.getOrdernum());
			json.put("name",order.getName());
			json.put("phone",order.getPhone());
			json.put("state",order.getState());
			json.put("date",order.getDate());
			json.put("total",order.getTotal());
			jsonArray.add(json);
		}
		response.getWriter().write(jsonArray.toString());
	}
	
	/**
	 * 接受的订单列表
	 */
	@RequestMapping("acceptOrderList")
	public String acceptOrderList(Model model,PageBean<Order> pageBean,HttpSession session){
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		//具体店铺的订单
		Admin admin = (Admin)session.getAttribute("admin");
		List<Long> shopIds = this.shopService.listAdminShops(admin.getId());
		PageBean<Order> datas = this.orderService.findAcceptOrders(currentPage,pageSize,shopIds);
		model.addAttribute("datas",datas);
		return "order/acceptOrderList";
	}
	
	/**
	 * 被取消的订单列表
	 */
	@RequestMapping("failOrderList")
	public String failOrderList(Model model,PageBean<Order> pageBean){
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<Order> datas = this.orderService.findFailOrders(currentPage,pageSize);
		model.addAttribute("datas",datas);
		return "order/failOrderList";
	}
	
	/**
	 * 已完成的订单列表
	 */
	@RequestMapping("successOrderList")
	public String successOrderList(Model model,PageBean<Order> pageBean,String search){
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<Order> datas = this.orderService.findSuccessOrders(currentPage,pageSize,search);
		model.addAttribute("datas",datas);
		model.addAttribute("search",search);
		return "order/successOrderList";
	}
	
	/**
	 * 订单统计
	 * @date: 9-2 下午05:47:14
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="orderTotal",method=RequestMethod.GET)
	public String orderTotal(Model model,String sdate,String edate,String search){	
		Shop shop = this.shopService.getShopByName(search);
		if(shop==null){
			throw new TFMSException("这个店铺不存在！");
		}
		List<Order> orderList = this.orderService.findShopOrders(shop.getName(),sdate,edate);
		int ordernum = orderList.size();
		float ordertotal = 0f;
		for(Order o:orderList){
			ordertotal = ordertotal + o.getTotal();
		}
		model.addAttribute("orderTotalDto",new OrderTotalDto(shop.getName(),
								shop.getBossname(),shop.getBosstel(),ordernum,ordertotal));
		model.addAttribute("sdate",sdate);
		model.addAttribute("edate",edate);
		return "order/orderTotal";
	}
	
	@RequestMapping(value="orderDetail")
	public void orderDetail(Long orderId,HttpServletResponse response) throws IOException{
		Order order = this.orderService.getOrder(orderId);
		List<FoodItem> foodItems = this.foodItemService.findOrderFoodItems(orderId);
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("ordernum",order.getOrdernum());
		jsonObj.put("name",order.getName());
		jsonObj.put("phone",order.getPhone());
		jsonObj.put("bookdate",order.getDate());
		jsonObj.put("sname",order.getShop().getName());
		JSONArray jsonArray = new JSONArray();
		for(FoodItem foodItem : foodItems){
			JSONObject json = new JSONObject();
			json.put("foodname",foodItem.getName());
			json.put("shopname",foodItem.getFood().getShop().getName());
			json.put("foodnum",foodItem.getNumber());
			json.put("price",foodItem.getPrice());
			json.put("tprice",(foodItem.getPrice()*foodItem.getNumber()));
			jsonArray.add(json);
		}
		jsonObj.put("foodItems",jsonArray);
		jsonObj.put("remark",order.getRemark());
		jsonObj.put("address",order.getAddress());
		jsonObj.put("total",order.getTotal());
		jsonObj.put("payType",order.getPayType());
		response.getWriter().write(jsonObj.toString());
	}
	
	/**
	 * 处理订单
	 */
	@RequestMapping(value="{orderId}/acceptOrder")
	public String acceptOrder(@PathVariable Long orderId){
		//获取新的文件名称
		this.orderService.acceptOrder(orderId);
		return "redirect:/order/acceptOrderList";
	}
	
	/**
	 * 取消订单
	 */
	@RequestMapping(value="{orderId}/cancelOrder")
	public String cancelOrder(@PathVariable Long orderId){
		//获取新的文件名称
		this.orderService.canceltOrder(orderId);
		return "redirect:/order/failOrderList";
	}
	
	/**
	 * 完成订单
	 */
	@RequestMapping(value="{orderId}/finishOrder")
	public String finishOrder(@PathVariable Long orderId){
		//获取新的文件名称
		this.orderService.finishOrder(orderId);
		return "redirect:/order/acceptOrderList";
	}
	
	/**
	 * 完成订单
	 */
	@RequestMapping(value="{orderId}/deleteOrder")
	public String deleteOrder(@PathVariable Long orderId){
		//获取新的文件名称
		this.orderService.finishOrder(orderId);
		return "redirect:/order/failOrderList";
	}
	
	/**
	 * 恢复订单
	 */
	@RequestMapping(value="{orderId}/replaceOrder")
	public String replaceOrder(@PathVariable Long orderId){
		//获取新的文件名称
		this.orderService.replaceOrder(orderId);
		return "redirect:/order/failOrderList";
	}
}
