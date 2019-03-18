package com.explorer.tfms.web.controller;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import com.explorer.tfms.domain.Activity;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.service.ActivityService;
import com.explorer.tfms.utils.JSONUtils;
import com.explorer.tfms.utils.UploadUtils;
import com.explorer.tfms.web.dto.AjaxObj;

@Controller("activityController")
@RequestMapping("/activity/")
public class ActivityController {
	
	@Resource(name="activityService")
	private ActivityService activityService;
	/**
	 * 活动列表
	 * @date: 3-23 下午02:11:31
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(Model model,PageBean<Activity> pageBean){
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<Activity> datas = this.activityService.findAllActivitys(currentPage, pageSize);
		model.addAttribute("datas",datas);
		return "activity/activityList";
	}
	
	/**
	 * 活动添加页面
	 * @date: 3-23 下午02:30:40
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("activity",new Activity());
		return "activity/addActivity";
	}
	
	/**
	 * 活动添加方法
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(Model model,@Validated Activity activity,BindingResult br){
		if(br.hasErrors()){
			return "activity/addActivity";
		}
		this.activityService.saveActivity(activity);
		return "redirect:/activity/list";
	}
	
	/**
	 * 活动修改页面
	 * @date: 3-23 下午02:30:40
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		model.addAttribute("activity",activityService.getActivity(id));
		return "activity/updateActivity";
	}
	
	/**
	 * 活动修改方法
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.POST)
	public String update(Model model,@Validated Activity activity,BindingResult br){
		if(br.hasErrors()){
			return "activity/updateActivity";
		}
		this.activityService.updateActivity(activity);
		return "redirect:/activity/list";
	}
	
	/**
	 * 删除活动信息
	 */
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Long id){
		this.activityService.deleteActivity(id);
		return "redirect:/activity/list";
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)//返回的是json格式，而uploadify只能接受字符串
	public void upload(MultipartFile file,HttpServletResponse response) throws IOException{
		//获取新的文件名称
		String filename = UploadUtils.getInstance().saveImageFileToPath(file.getOriginalFilename(),file.getInputStream(),UploadUtils.ACTIVITY);
		response.setContentType("text/html;charset=utf-8");
		AjaxObj ajaxObj = new AjaxObj();
		ajaxObj.setObj(filename);
		response.getWriter().write(JSONUtils.getInstance().obj2Json(ajaxObj));
	}
}
