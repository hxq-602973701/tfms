package com.explorer.tfms.web.controller;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.explorer.tfms.domain.FoodType;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.service.FoodTypeService;
@Controller("foodTypeController")
@RequestMapping("/foodType/")
public class FoodTypeController {
	@Resource(name="foodTypeService")
	private FoodTypeService foodTypeService;
	/**
	 * 列表页面
	 * @date: 3-9 上午11:54:45
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(Model model,PageBean<FoodType> pageBean){		
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<FoodType> datas = this.foodTypeService.findAllFoodTypes(currentPage,pageSize);
		model.addAttribute("datas",datas);
		return "foodType/foodTypeList";
	}
	/**
	 * 添加页面
	 * @date: 3-9 下午03:37:47
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("foodType",new FoodType());
		return "foodType/addFoodType";
	}
	
	/**
	 * 添加方法
	 * @date: 3-9 下午04:04:41
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(Model model,@Validated FoodType foodType,BindingResult br){
		if(br.hasErrors()){
			return "foodType/addFoodType";
		}
		this.foodTypeService.saveFoodType(foodType);
		return "redirect:/foodType/list";
	}
	
	/**
	 * 修改页面
	 * @date: 3-10 上午12:23:39
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		model.addAttribute("foodType",this.foodTypeService.getFoodType(id));
		return "foodType/updateFoodType";
	}
	/**
	 * 修改方法
	 * @date: 3-10 上午12:30:16
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.POST)
	public String update(@Validated FoodType foodType,BindingResult br){
		if(br.hasErrors()){
			return "foodType/updateFoodType";
		}
		this.foodTypeService.updateFoodType(foodType);
		return "redirect:/foodType/list";
	}
	/**
	 * 删除方法
	 * @date: 3-10 上午12:35:00
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Long id){
		this.foodTypeService.deleteFoodType(id);
		return "redirect:/foodType/list";
	}
}
