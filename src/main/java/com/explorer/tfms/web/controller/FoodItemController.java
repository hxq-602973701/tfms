package com.explorer.tfms.web.controller;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.explorer.tfms.domain.Food;
import com.explorer.tfms.domain.FoodItem;
import com.explorer.tfms.domain.Order;
import com.explorer.tfms.service.FoodItemService;
import com.explorer.tfms.service.FoodService;
import com.explorer.tfms.utils.JSONUtils;

/**
 * 食物项控制层
 * @author Administrator
 */
@Controller("foodItemController")
public class FoodItemController{
	@Resource(name="foodService")
	private FoodService foodService;
	@Resource(name="foodItemService")
	private FoodItemService foodItemService;
	
	/**
	 * 添加购物项到购物车
	 * @param session
	 * @param foodId
	 * @param response
	 * @throws IOException:
	 * @date: 3-19 上午01:36:06
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/addFood2Car",method=RequestMethod.GET)
	public void addFood2Car(HttpSession session,@RequestParam("foodId") Long foodId,@RequestParam("shopId") Long shopId,HttpServletResponse response) throws IOException{
System.out.println("添加商品到购物车");
		response.setContentType("text/html;charset=utf-8");
		Food food = this.foodService.getFood(foodId);
		FoodItem foodItem = new FoodItem();
		//foodItem.setId(food.getId());
		foodItem.setName(food.getName());
		foodItem.setPrice(food.getNewprice());
		foodItem.setNumber(1);
		foodItem.setFood(food);
		//拿到session中的购物车
		Order order = (Order)session.getAttribute("order_"+shopId);
		if(order==null){
System.out.println("为"+food.getShop().getName()+"创建购物车");
			order = new Order();
			session.setAttribute("order_"+shopId,order);
			TFMSSessionContext.addSession(session);
		}
		//把购物项添加到购物车
		//order.getFoodItems().add(foodItem);
		
		//把购物车返回
		session.setAttribute("order_"+shopId,foodItemService.addFoodItems(foodItem,order));
		
		response.getWriter().write(JSONUtils.getInstance().obj2Json(order.getFoodItems()));
	}
	
	/**
	 * 读取购物车
	 * @date: 3-19 上午01:35:54
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/readCar",method=RequestMethod.GET)
	public void readCar(HttpSession session,Long shopId,HttpServletResponse response) throws IOException{
System.out.println("读取购物车");		
		response.setContentType("text/html;charset=utf-8");
		//拿到session中的购物车
		Order order = (Order)session.getAttribute("order_"+shopId);
		//把购物车返回
		session.setAttribute("order_"+shopId,order);
		response.getWriter().write(JSONUtils.getInstance().obj2Json(order.getFoodItems()));
	}
	
	/**
	 * 添加
	 */
	@RequestMapping(value="/addCar",method=RequestMethod.GET)
	public void addCar(HttpSession session,Long shopId,HttpServletResponse response,Long foodId) throws IOException{
System.out.println("添加商品数量");
		response.setContentType("text/html;charset=utf-8");
		//拿到session中的购物车
		Order order = (Order)session.getAttribute("order_"+shopId);
		//把购物车返回
		session.setAttribute("order_"+shopId,this.foodItemService.addNum(foodId,order));
		
		response.getWriter().write(JSONUtils.getInstance().obj2Json(order.getFoodItems()));
	}
	
	/**
	 * 减少
	 */
	@RequestMapping(value="/minusCar",method=RequestMethod.GET)
	public void minusCar(HttpSession session,Long shopId,HttpServletResponse response,Long foodId) throws IOException{
System.out.println("减少商品数量");
		response.setContentType("text/html;charset=utf-8");
		//拿到session中的购物车
		Order order = (Order)session.getAttribute("order_"+shopId);
		//把购物车返回
		session.setAttribute("order_"+shopId,this.foodItemService.minuNum(foodId,order));
		
		response.getWriter().write(JSONUtils.getInstance().obj2Json(order.getFoodItems()));
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteCar",method=RequestMethod.GET)
	public void deleteCar(HttpSession session,Long shopId,HttpServletResponse response,Long foodId) throws IOException{
System.out.println("从购物车删除商品");		
		response.setContentType("text/html;charset=utf-8");
		//拿到session中的购物车
		Order order = (Order)session.getAttribute("order_"+shopId);
		//把购物车返回
		session.setAttribute("order_"+shopId,this.foodItemService.deleteFoodItem2Order(foodId,order));
		
		response.getWriter().write(JSONUtils.getInstance().obj2Json(order.getFoodItems()));
	}
}
