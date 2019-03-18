package com.explorer.tfms.web.controller;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.User;
import com.explorer.tfms.service.UserService;
@Controller("userController")
@RequestMapping("/user/")
public class UserController {
	
	@Resource(name="userService")
	private UserService userService;
	
	/**
	 * 用户列表
	 * @date: 3-28 下午04:07:36
	 * @version: V1.0
	 *
	 */
	@RequestMapping("list")
	public String list(Model model,PageBean<User> pageBean){
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<User> datas = this.userService.findAllUsers(currentPage, pageSize);
		model.addAttribute("datas",datas);
		return "user/userList";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Long id,int currentPage){
		this.userService.deleteUser(id);
		return "redirect:/user/list?currentPage="+currentPage;
	}
}
