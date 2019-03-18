package com.explorer.tfms.web.controller;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.ShopLabel;
import com.explorer.tfms.service.ShopLabelService;
@Controller("shopLabelController")
@RequestMapping("/shopLabel/")
public class ShopLabelController {
	@Resource(name="shopLabelService")
	private ShopLabelService shopLabelService;
	/**
	 * 列表页面
	 * @return:
	 * @throws: 
	 * @date: 3-9 上午11:54:45
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(Model model,PageBean<ShopLabel> pageBean){
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<ShopLabel> datas = this.shopLabelService.findAllShopLabels(currentPage,pageSize);
		model.addAttribute("datas",datas);
		return "shopLabel/shopLabelList";
	}
	/**
	 * 添加页面
	 * @date: 3-9 下午03:37:47
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("shopLabel",new ShopLabel());
		return "shopLabel/addShopLabel";
	}
	
	/**
	 * 添加方法
	 * @date: 3-9 下午04:04:41
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(Model model,@Validated ShopLabel shopLabel,BindingResult br){
		if(br.hasErrors()){
			return "shopLabel/addShopLabel";
		}
		this.shopLabelService.saveShopLabel(shopLabel);
		return "redirect:/shopLabel/list";
	}
	
	/**
	 * 修改页面
	 * @date: 3-10 上午12:23:39
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		model.addAttribute("shopLabel",this.shopLabelService.getShopLabel(id));
		return "shopLabel/updateShopLabel";
	}
	/**
	 * 修改方法
	 * @date: 3-10 上午12:30:16
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.POST)
	public String update(@Validated ShopLabel shopLabel,BindingResult br){
		if(br.hasErrors()){
			return "shopLabel/updateShopLabel";
		}
		this.shopLabelService.updateShopLabel(shopLabel);
		return "redirect:/shopLabel/list";
	}
	/**
	 * 删除方法
	 * @date: 3-10 上午12:35:00
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Long id){
		this.shopLabelService.deleteShopLabel(id);
		return "redirect:/foodType/list";
	}
}
