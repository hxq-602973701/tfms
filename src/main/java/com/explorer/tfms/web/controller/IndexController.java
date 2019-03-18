package com.explorer.tfms.web.controller;

import com.explorer.tfms.domain.*;
import com.explorer.tfms.service.*;
import com.explorer.tfms.token.Token;
import com.explorer.tfms.utils.JSONUtils;
import com.explorer.tfms.utils.MD5Utils;
import com.explorer.tfms.utils.StringUtils;
import com.explorer.tfms.web.dto.AjaxObj;
import com.explorer.tfms.web.dto.FoodType2Foods;
import com.explorer.tfms.web.dto.ShopDto;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 首页的控制层
 * @author Administrator
 */
@Controller("indexController")
public class IndexController {
	@Resource(name="shopService")
	private ShopService shopService;
	@Resource(name="foodService")
	private FoodService foodService;
	@Resource(name="foodTypeService")
	private FoodTypeService foodTypeService;
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="noticeService")
	private NoticeService noticeService;
	@Resource(name="articleService")
	private ArticleService articleService;
	@Resource(name="activityService")
	private ActivityService activityService;
	@Resource(name="orderService")
	private OrderService orderService;
	@Resource(name="commentService")
	private CommentService commentService;
	@Resource(name="foodItemService")
	private FoodItemService foodItemService;
	
	@RequestMapping({"/","/index"})
	public String index(){
		return "index/index";
	}
	
	/**
	 * 通过Id显示商店对象
	 * @param id
	 * @date: 3-15 上午11:17:18
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/{id}/show")
	public String show(@PathVariable Long id,Long foodId,Model model,HttpSession session){
		Shop shop = this.shopService.getShop(id);
		model.addAttribute("shopDto",new ShopDto(shop));
		if(foodId!=null){
			model.addAttribute("foodId",foodId);
		}
		//创建购物车
		System.out.println("为"+shop.getName()+"创建购物车");
		if(session.getAttribute("order_"+id)==null){
			session.setAttribute("order_"+id,new Order());
			TFMSSessionContext.addSession(session);
		}
		//TFMSSessionContext.addSession(session);
		return "index/shopShow";
	}
	/*
	@RequestMapping(value="{id}/{foodId}/show",method=RequestMethod.GET)
	public String show(@PathVariable Long id,@PathVariable Long foodId,Model model){
		Shop shop = this.shopService.getShop(id);
		model.addAttribute("shopDto",new ShopDto(shop));
		model.addAttribute("foodId",foodId);
		return "index/shopShow";
	}*/
	
	/**
	 * ajax请求
	 */
	
	
	/**
	 * ajax请求
	 * @date: 3-17 下午01:32:44
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/showShopFoods",method=RequestMethod.GET)
	public @ResponseBody List<FoodType2Foods> showShopFoods(Long shopId){
		List<FoodType2Foods> foodType2FoodsList = new ArrayList<FoodType2Foods>();
		List<FoodType> foodTypeList = this.foodTypeService.listPublicFoodTypes();
		for(FoodType ft:foodTypeList){
			FoodType2Foods foodType2Foods = new FoodType2Foods();
			List<Food> foodList = this.foodService.listAllFoodsByType(ft.getId(),shopId);
			foodType2Foods.setName(ft.getName());
			foodType2Foods.setFoods(foodList);
			foodType2FoodsList.add(foodType2Foods);
		}
		return foodType2FoodsList;
	}
	
	/**
	 * 下订单页面
	 * @date: 3-19 下午11:05:02
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/forder",method=RequestMethod.GET)
	@Token(save=true)
	public String order(HttpSession session,Long shopId,Model model){
		//判断用户是否在线
		User u = (User)session.getAttribute("user");
		if(u==null){//跳转登陆页面
			return "index/login";
		}
		Order order = (Order)session.getAttribute("order_"+shopId);
		float total = 0;
		for(FoodItem foodItem:order.getFoodItems()){
			total = total + foodItem.getPrice()*foodItem.getNumber(); 
		}
		session.setAttribute("orders",order.getFoodItems());
		model.addAttribute("total",total);
		model.addAttribute("shopId",shopId);
		return "index/orderShow";
	}
	
	/**
	 * 下订单(发送到系统处理)
	 */
	@RequestMapping(value="/forder",method=RequestMethod.POST)
	@Token(remove=true)
	public String order(Long shopId,Order order,HttpSession session,Model model,HttpServletRequest request){
		Order order1 = (Order)session.getAttribute("order_"+shopId);
		Shop shop = this.shopService.getShop(shopId);
		User user = (User)session.getAttribute("user");
		if(user==null){
			return "index/login";
		}
		String returnresult = null;
		if("0".equals(order.getPayType())){
			this.orderService.saveOrder(order,order1.getFoodItems(),user,shop);
			returnresult =  "index/order_success";
			session.removeAttribute("order_"+shopId);
		}else if("1".equals(order.getPayType())){//跳转在线支付
			returnresult =  "index/onlinePay";
		}
		model.addAttribute("order",order);
		return returnresult;
	}
	
	
	
	/**
	 * 用户登陆页面
	 * @date: 3-20 下午11:17:48
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "index/login";
	}
	
	/**
	 * 用户登录方法
	 * @date: 3-21 下午01:10:00
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(User user,HttpSession session,String kaptcha,Model model){
		AjaxObj ao = new AjaxObj(0);
		String kaptchaInSession = (String)session.getAttribute("kaptcha");
		if(kaptcha!=null && kaptchaInSession.equalsIgnoreCase(kaptcha)){
			//1、通过用户名获得用户对象
			User u = this.userService.getUser(user.getUsername());
			if(u==null){//改用户名不存在
				ao.setMsg("用户名不存在！");
			}else{
				boolean flag = MD5Utils.getInstance().isCheckPassword(user.getPassword(),u.getPassword());
				if(flag){//登陆成功
					session.setAttribute("user",u);
					ao.setResult(1);
					return "redirect:/index";
				}else{
					ao.setMsg("用户输入密码错误！");
				}
			}
		}else{
			ao.setMsg("输入验证码错误！");
		}
		model.addAttribute("ajaxObj",ao);
		model.addAttribute("username",user.getUsername());
		model.addAttribute("password",user.getPassword());
		return "index/login";
	}
	
	/**
	 * 用户快速登录方法
	 * @date: 3-21 下午01:10:00
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/quickLogin",method=RequestMethod.POST)
	public @ResponseBody AjaxObj quickLogin(User user,HttpSession session,String kaptcha,Model model){
		AjaxObj ao = new AjaxObj(0);
		String kaptchaInSession = (String)session.getAttribute("kaptcha");
		if(kaptcha!=null && kaptchaInSession.equalsIgnoreCase(kaptcha)){
			if(user.getUsername()!=null){
				//1、通过用户名获得用户对象
				User u = this.userService.getUser(user.getUsername());
				if(u==null){//改用户名不存在
					ao.setMsg("用户名不存在！");
				}else{
					if(u.getPassword()!=null){
						boolean flag = MD5Utils.getInstance().isCheckPassword(user.getPassword(),u.getPassword());
						if(flag){//登陆成功
							session.setAttribute("user",u);
							ao.setResult(1);
						}else{
							ao.setMsg("用户输入密码错误！");
						}
					}else{
						ao.setMsg("请输入用户密码！");
					}
				}
			}else{
				ao.setMsg("请输入用户名！");
			}
		}else{
			ao.setMsg("输入验证码错误！");
		}
		model.addAttribute("ajaxObj",ao);
		model.addAttribute("username",user.getUsername());
		model.addAttribute("password",user.getPassword());
		return ao;
	}
	
	/**
	 * 退出登陆
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "redirect:/index";
	}
	
	/**
	 * 用户注册页面
	 * @date: 3-20 下午11:17:48
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(){
		return "index/register";
	}
	
	/**
	 * 
	 * @date: 3-21 下午08:38:56
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(User user,Model model,HttpSession session){
		User u = this.userService.getUser(user.getUsername());
		if(u!=null){
			model.addAttribute("user",user);
		}
		session.setAttribute("user",user);
		this.userService.saveUser(user);
		return "index/regSuccess";
	}
	/**
	 * 通过种类查询商家
	 * @date: 3-22 下午01:58:47
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/{id}/shop",method=RequestMethod.GET)
	public String category(@PathVariable Long id,Model model){
		List<Shop> shopList = this.shopService.listShoLabelShops(id);
		model.addAttribute("shopList",shopList);
		return "index/categoryShops";
	}
	
	
	/**
	 * 通过搜索查询
	 */
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String search(int type,String keyword,Model model){
		if(!"".equals(keyword) && keyword!=null){
			String scontent = StringUtils.getInstance().removeBlank(keyword);
			if(type==1){//美食
				//商店名称和食品集合
				Map<String,List<Food>> maps = null;
				List<Food> foodList = null;
				List<Food> foods = this.foodService.findFoodsByName(scontent);
				if(foods!=null && foods.size()!=0){
					maps = new HashMap<String,List<Food>>();
					for(Food food1:foods){
						foodList = new ArrayList<Food>();
						for(Food food2:foods){//将店铺名称相同放到同一个集合中
							if(food1.getShop().equals(food2.getShop())){
								foodList.add(food2);
							}
						}
						maps.put(food1.getShop().getName(),foodList);
					}
					model.addAttribute("shopFoods",maps);
				}
				return "index/shop_foods";
			}else if(type==2){//商铺
				List<Shop> shopList = this.shopService.findShopsByName(scontent);
				model.addAttribute("shopList",shopList);
				return "index/categoryShops";
			}
		}
		return "index/index";
	}
	
	/**
	 * 公告列表
	 */
	@RequestMapping(value="/noticeList",method=RequestMethod.GET)
	public String noticeList(Model model,PageBean<Notice> pageBean){
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<Notice> datas = this.noticeService.listAllNotices(currentPage, pageSize);
		model.addAttribute("datas",datas);
		return "index/noticeList";
	}
	
	/**
	 * 公告详情列表
	 */
	@RequestMapping(value="/{id}/noticeShow",method=RequestMethod.GET)
	public String noticeShow(@PathVariable Long id,Model model){
		Notice notice = this.noticeService.getNotice(id);
		model.addAttribute("notice",notice);
		return "index/noticeShow";
	}
	
	/**
	 * 显示文章细节
	 * @param id:值得是文章标题Id
	 */
	@RequestMapping(value="/{id}/articleShow",method=RequestMethod.GET)
	public String articleShow(@PathVariable Long id,Model model){
		Article article = this.articleService.getArticleByTitle(id);
		model.addAttribute("article",article);
		return "index/articleShow";
	}
	
	/**
	 * 活动列表
	 */
	@RequestMapping(value="/activityList",method=RequestMethod.GET)
	public String activityList(Model model,PageBean<Notice> pageBean){
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<Activity> datas = this.activityService.findSuccessActivitys(currentPage,pageSize);
		model.addAttribute("datas",datas);
		return "index/activityList";
	}
	
	/**
	 * 活动详情列表
	 */
	@RequestMapping(value="/{id}/activityShow",method=RequestMethod.GET)
	public String activityShow(@PathVariable Long id,Model model){
		Activity activity = this.activityService.getActivity(id);
		model.addAttribute("activity",activity);
		return "index/activityShow";
	}
	
	/**
	 * 会员中心
	 */
	@RequestMapping(value="/userInfo",method=RequestMethod.GET)
	public String userInfo(Model model,HttpSession session){
		User u = (User)session.getAttribute("user");
		if(u==null){//跳转登陆页面
			return "index/login";
		}
		User user = this.userService.getUser(u.getId());
		model.addAttribute("userInfo",user);
		return "index/userInfo";
	}
	
	@RequestMapping(value="/userInfo",method=RequestMethod.POST)
	public String userInfo(Model model,HttpSession session,User user){
		User u = (User)session.getAttribute("user");
		if(u==null){//跳转登陆页面
			return "index/login";
		}
		User user1 = this.userService.getUser(user.getId());
		user1.setSex(user.getSex());
		user1.setName(user.getName());
		user1.setPhone(user.getPhone());
		this.userService.updateUser(user1);
		return "redirect:/userInfo";
	}
	/**
	 * 修改密码
	 * @date: 3-30 下午12:23:12
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/updatePassword",method=RequestMethod.GET)
	public String updatePassword(Model model,HttpSession session){
		User u = (User)session.getAttribute("user");
		if(u==null){//跳转登陆页面
			return "index/login";
		}
		User user = this.userService.getUser(u.getId());
		model.addAttribute("userInfo",user);
		return "index/user_editpassword";
	}
	
	/**
	 * 修改密码
	 * @date: 3-30 下午12:23:12
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public String updatePassword(Model model,HttpSession session,User user){
		User u = (User)session.getAttribute("user");
		if(u==null){//跳转登陆页面
			return "index/login";
		}
		u.setPassword(MD5Utils.getInstance().genMD5Pwd(user.getPassword()));
		this.userService.updateUser(u);
		return "redirect:/updatePassword";
	}
	/**
	 * 核对密码
	 * @throws IOException 
	 */
	@RequestMapping("/checkPassword")
	public void checkPassword(String pwd,HttpServletResponse response,HttpSession session) throws IOException{
		boolean flag = false;
		User u = (User)session.getAttribute("user");
		response.setContentType("text/html;charset=utf-8");
		if(MD5Utils.getInstance().genMD5Pwd(pwd).equals(u.getPassword())){
			flag = true;
		}
		response.getWriter().print(flag);
	}
	
	/**
	 * 订单列表
	 */
	@RequestMapping(value="/orderList",method=RequestMethod.GET)
	public String orderList(Model model,PageBean<Notice> pageBean,HttpSession session){
		//判断用户是否在线
		User u = (User)session.getAttribute("user");
		if(u==null){//跳转登陆页面
			return "index/login";
		}
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<Order> datas = this.orderService.findUserOrders(currentPage,pageSize,u.getId());
		model.addAttribute("datas",datas);
		return "index/userOrderList";
	}
	
	/**
	 * 取消订单
	 */
	@RequestMapping("/cancelOrder")
	public @ResponseBody AjaxObj cancelOrder(Long orderId,HttpSession session){
		AjaxObj ajax = null;
		//判断用户是否在线
		User u = (User)session.getAttribute("user");
		if(u==null){//跳转登陆页面
			ajax = new AjaxObj(2);//用户不存在
			return ajax;
		}
		Order order = this.orderService.getOrder(orderId);
		if("1".equals(order.getState()) ){
			ajax = new AjaxObj(0); //订单被接受
			return ajax;
		}else if("3".equals(order.getState())){
			ajax = new AjaxObj(3); //订单已经确认收货了
			return ajax;
		}
		//获取新的文件名称
		this.orderService.canceltOrder(orderId);
		ajax = new AjaxObj(1);
		return ajax;
	}
	
	/**
	 * 确认收货
	 */
	@RequestMapping("/finishOrder")
	public @ResponseBody AjaxObj finishOrder(Long orderId,HttpSession session){
		AjaxObj ajax = null;
		//判断用户是否在线
		User u = (User)session.getAttribute("user");
		if(u==null){//跳转登陆页面
			ajax = new AjaxObj(2);//用户不存在
			return ajax;
		}
		Order order = this.orderService.getOrder(orderId);
		if("1".equals(order.getState())){ //非法操作
			this.orderService.canceltOrder(orderId);
			ajax = new AjaxObj(0);
			return ajax;
		}
		ajax = new AjaxObj(1);
		return ajax;
	}
	
	/**
	 * 评论列表
	 * @throws IOException 
	 */
	@RequestMapping(value="/showComments",method=RequestMethod.GET)
	public void showComments(Long shopId,PageBean<Comment> pageBean,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		PageBean<Comment> datas = this.commentService.findShopComemnts(shopId,currentPage);
		response.getWriter().write(JSONUtils.getInstance().obj2Json(datas));
	}
	
	/**
	 * 添加评论
	 */
	@RequestMapping(value="/addComments",method=RequestMethod.POST)
	public String addComments(String content,Long shopId,HttpServletResponse response,HttpSession session) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		User u = (User)session.getAttribute("user");
		if(u==null){//跳转登陆页面
			return "index/login";
		}
		this.commentService.saveComment(content,u.getId(),shopId);
		AjaxObj ajax = new AjaxObj();
		ajax.setResult(1);
		response.getWriter().write(JSONUtils.getInstance().obj2Json(ajax));
		return null;
	}
	
	@RequestMapping(value="orderInfo")
	public String orderDetail(Long orderId,HttpServletResponse response,HttpSession session) throws IOException{
		User u = (User)session.getAttribute("user");
		if(u==null){//跳转登陆页面
			return "index/login";
		}
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
		jsonObj.put("total",order.getTotal());
		jsonObj.put("remark",order.getRemark());
		jsonObj.put("address",order.getAddress());
		jsonObj.put("payType",order.getPayType());
		response.getWriter().write(jsonObj.toString());
		return null;
	}
}
