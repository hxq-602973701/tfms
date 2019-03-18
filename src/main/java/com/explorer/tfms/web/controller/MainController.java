package com.explorer.tfms.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 
 * @author Administrator
 */
@Controller("mainController")
@RequestMapping("/")
public class MainController {
	/**
	 * 左侧页面
	 * @return:
	 * @throws: 
	 * @date: 3-9 上午12:55:34
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="left",method=RequestMethod.GET)
	public String left(){
		return "left";
	}
	
	/**
	 * 右侧页面，这个页面需要要系统的一些数据
	 * @return:
	 * @throws: 
	 * @date: 3-9 上午12:57:41
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="right",method=RequestMethod.GET)
	public String right(){
		return "right";
	}
	
	/**
	 * 顶部页面
	 * @return:
	 * @throws: 
	 * @date: 3-9 上午01:00:02
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="top",method=RequestMethod.GET)
	public String top(){
		return "top";
	}
	
	/**
	 * 后台管理页面主页
	 * @return:
	 * @throws: 
	 * @date: 3-9 上午01:02:15
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="main",method=RequestMethod.GET)
	public String main(){
		return "main";
	}
}
