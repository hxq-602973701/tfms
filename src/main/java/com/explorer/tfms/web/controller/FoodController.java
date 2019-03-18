package com.explorer.tfms.web.controller;
import java.io.IOException;
import java.util.List;
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
import com.explorer.tfms.domain.Food;
import com.explorer.tfms.domain.FoodType;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.service.FoodService;
import com.explorer.tfms.service.FoodTypeService;
import com.explorer.tfms.service.ShopService;
import com.explorer.tfms.utils.JSONUtils;
import com.explorer.tfms.utils.UploadUtils;
import com.explorer.tfms.web.dto.AjaxObj;
import com.explorer.tfms.web.dto.FoodDto;
@Controller("foodController")
@RequestMapping("/food/")
public class FoodController {
	@Resource(name="foodService")
	private FoodService foodService;
	@Resource(name="shopService")
	private ShopService shopService;
	@Resource(name="foodTypeService")
	private FoodTypeService foodTypeService;
	
	/**
	 * 
	 * @date: 3-15 下午05:50:51
	 * @version: V1.0
	 *
	 */
	@RequestMapping("list")
	public String list(Model model,PageBean<Food> pageBean){
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<Food> datas = foodService.findAllFoods(currentPage, pageSize);
		model.addAttribute("datas",datas);
		return "food/foodList";
	}
	
	/**
	 * 添加页面
	 * @date: 3-15 下午06:40:44
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model){
		List<Shop> shopList = this.shopService.listAllPublicShops();
		List<FoodType> foodTypeList = this.foodTypeService.listPublicFoodTypes();
		model.addAttribute("foodDto",new FoodDto());
		model.addAttribute("shopList",shopList);
		model.addAttribute("foodTypeList",foodTypeList);
		return "food/addFood";
	}
	
	/**
	 * 添加方法
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@Validated FoodDto foodDto,BindingResult br,Model model){
		if(br.hasErrors()){
			List<Shop> shopList = this.shopService.listAllPublicShops();
			List<FoodType> foodTypeList = this.foodTypeService.listPublicFoodTypes();
			model.addAttribute("shopList",shopList);
			model.addAttribute("foodTypeList",foodTypeList);
			return "food/addFood";
		}
		this.foodService.saveFood(foodDto.getFood(),foodDto.getShopId(),foodDto.getFoodTypeId());
		return "redirect:/food/list";
	}
	
	@RequestMapping(value="{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		Food food = this.foodService.getFood(id);
		List<Shop> shopList = this.shopService.listAllPublicShops();
		List<FoodType> foodTypeList = this.foodTypeService.listPublicFoodTypes();
		model.addAttribute("shopList",shopList);
		model.addAttribute("foodTypeList",foodTypeList);
		model.addAttribute("foodDto",new FoodDto(food,food.getFoodType().getId(),food.getShop().getId()));
		return "food/updateFood";
	}
	
	@RequestMapping(value="{id}/update",method=RequestMethod.POST)
	public String update(@PathVariable Long id,@Validated FoodDto foodDto,BindingResult br,Model model){
		if(br.hasErrors()){
			List<Shop> shopList = this.shopService.listAllPublicShops();
			List<FoodType> foodTypeList = this.foodTypeService.listPublicFoodTypes();
			model.addAttribute("shopList",shopList);
			model.addAttribute("foodTypeList",foodTypeList);
			return "food/updateFood";
		}
		this.foodService.updateFood(foodDto.getFood(),foodDto.getShopId(),foodDto.getFoodTypeId());
		return "redirect:/food/list";
	}
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Long id){
		this.foodService.deleteFood(id);
		return "redirect:/food/list";
	}
	
	/**
	 * 推荐商品
	 * @date: 3-16 下午04:20:19
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/{type}/recommend",method=RequestMethod.GET)
	public String recommend(@PathVariable Long id,@PathVariable String type){
		this.foodService.recommondFood(id,type);
		return "redirect:/food/list";
	}
	
	@RequestMapping(value="upload",method=RequestMethod.POST)//返回的是json格式，而uploadify只能接受字符串
	public void upload(MultipartFile file,HttpServletResponse response) throws IOException{
		//获取新的文件名称
		String filename = UploadUtils.getInstance().saveImageFileToPath(file.getOriginalFilename(),file.getInputStream(),UploadUtils.FOOD);
		response.setContentType("text/html;charset=utf-8");
		AjaxObj ajaxObj = new AjaxObj();
		ajaxObj.setObj(filename);
		response.getWriter().write(JSONUtils.getInstance().obj2Json(ajaxObj));
	}
}