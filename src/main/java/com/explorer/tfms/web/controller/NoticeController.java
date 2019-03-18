package com.explorer.tfms.web.controller;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.explorer.tfms.domain.Notice;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.service.NoticeService;

@Controller("noticeController")
@RequestMapping("/notice/")
public class NoticeController {
	
	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	/**
	 * 公告列表
	 * @date: 3-23 下午02:11:31
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(Model model,PageBean<Notice> pageBean){
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<Notice> datas = this.noticeService.findAllNotices(currentPage, pageSize);
		model.addAttribute("datas",datas);
		return "notice/noticeList";
	}
	
	/**
	 * 公告添加页面
	 * @date: 3-23 下午02:30:40
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("notice",new Notice());
		return "notice/addNotice";
	}
	
	/**
	 * 公告添加方法
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(Model model,@Validated Notice notice,BindingResult br){
		if(br.hasErrors()){
			return "notice/addNotice";
		}
		this.noticeService.saveNotice(notice);
		return "redirect:/notice/list";
	}
	
	/**
	 * 公告修改页面
	 * @date: 3-23 下午02:30:40
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		model.addAttribute("notice",this.noticeService.getNotice(id));
		return "notice/updateNotice";
	}
	
	/**
	 * 公告修改方法
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.POST)
	public String update(Model model,@Validated Notice notice,BindingResult br){
		if(br.hasErrors()){
			return "notice/updateNotice";
		}
		this.noticeService.updateNotice(notice);
		return "redirect:/notice/list";
	}
	
	/**
	 * 删除公告信息
	 */
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Long id){
		this.noticeService.deleteNotice(id);
		return "redirect:/notice/list";
	}
}
