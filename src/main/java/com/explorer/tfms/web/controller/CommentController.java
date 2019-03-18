package com.explorer.tfms.web.controller;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.explorer.tfms.domain.Comment;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.service.CommentService;
@Controller("commentController")
@RequestMapping("/comment/")
public class CommentController {
	@Resource(name="commentService")
	private CommentService commentService;
	
	@RequestMapping("list")
	public String list(Model model,PageBean<Comment> pageBean,String search){
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<Comment> datas = this.commentService.findShopComemnts(search, currentPage, pageSize);
		model.addAttribute("datas",datas);
		model.addAttribute("search",search);
		return "comment/commentList";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Long id,int currentPage){
		this.commentService.deleteComment(id);
		return "redirect:/comment/list?currentPage="+currentPage;
	}
}
