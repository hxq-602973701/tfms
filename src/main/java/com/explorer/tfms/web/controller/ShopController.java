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
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.domain.ShopLabel;
import com.explorer.tfms.service.ShopLabelService;
import com.explorer.tfms.service.ShopService;
import com.explorer.tfms.utils.JSONUtils;
import com.explorer.tfms.utils.UploadUtils;
import com.explorer.tfms.web.dto.AjaxObj;
import com.explorer.tfms.web.dto.ShopDto;
/**
 * 商店控制层方法
 * @author Administrator
 */
@Controller("shopController")
@RequestMapping("/shop/")
public class ShopController {
	@Resource(name="shopService")
	private ShopService shopService;
	@Resource(name="shopLabelService")
	private ShopLabelService shopLabelService;
	/**
	 * 列表
	 */
	@RequestMapping("list")
	public String list(Model model,PageBean<Shop> pageBean){
		int currentPage = pageBean==null?1:pageBean.getCurrentPage();
		int pageSize = pageBean==null?10:pageBean.getPageSize();
		PageBean<Shop> datas = this.shopService.findAllShops(currentPage,pageSize);
		model.addAttribute("datas",datas);
		return "shop/shopList";
	}
	
	/**
	 * 添加页面
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model){
		List<ShopLabel> shopLabels = this.shopLabelService.listActiveShopLabels();
		model.addAttribute("shopLabels",shopLabels);
		model.addAttribute("shopDto",new ShopDto());
		return "shop/addShop";
	}
	
	/**
	 * 添加方法
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@Validated ShopDto shopDto,BindingResult br,Model model){
		if(br.hasErrors()){
			List<ShopLabel> shopLabels = this.shopLabelService.listActiveShopLabels();
			model.addAttribute("shopLabels",shopLabels);
			return "shop/addShop";
		}
		this.shopService.saveShop(shopDto.getShop(),shopDto.getShopLabelIds());
		return "redirect:/shop/list";
	}
	
	/**
	 * 修改页面
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		List<ShopLabel> shopLabels = this.shopLabelService.listActiveShopLabels();
		model.addAttribute("shopLabels",shopLabels);
		model.addAttribute("shopDto",new ShopDto(shopService.getShop(id),shopService.listShopLabelds(id)));
		return "shop/updateShop";
	}
	
	/**
	 * 修改方法
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.POST)
	public String update(@PathVariable Long id,@Validated ShopDto shopDto,BindingResult br,Model model){
		if(br.hasErrors()){
			List<ShopLabel> shopLabels = this.shopLabelService.listActiveShopLabels();
			model.addAttribute("shopLabels",shopLabels);
			return "shop/updateShop";
		}
		this.shopService.updateShop(shopDto.getShop(),shopDto.getShopLabelIds());
		return "redirect:/shop/list";
	}
	
	/**
	 * 是否设为推荐店铺
	 * @param id
	 * @param type
	 * @date: 3-16 下午02:55:46
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/{type}/recommend",method=RequestMethod.GET)
	public String recommend(@PathVariable Long id,@PathVariable String type){
		this.shopService.recommondShop(id,type);
		return "redirect:/shop/list";
	}
	
	/**
	 * 商铺是否营业
	 * @param id
	 * @param type
	 * @date: 3-16 下午02:55:46
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/{type}/rest",method=RequestMethod.GET)
	public String rest(@PathVariable Long id,@PathVariable String type){
		this.shopService.restShop(id,type);
		return "redirect:/shop/list";
	}
	
	/**
	 * 删除方法
	 */
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Long id){
		this.shopService.deleteShop(id);
		return "redirect:/shop/list";
	}
	
	/**
	 * 文件上传
	 * @return:
	 * @throws: 
	 * @date: 3-14 下午09:27:18
	 * @version: V1.0
	 *
	 * @return 
	@RequestMapping("/upload")//返回的是json格式，而uploadify只能接受字符串
	public @ResponseBody AjaxObj upload(MultipartFile shopImage){
System.out.println(shopImage.getOriginalFilename());		
		return new AjaxObj();
	}
	 */
	
	@RequestMapping(value="upload",method=RequestMethod.POST)//返回的是json格式，而uploadify只能接受字符串
	public void upload(MultipartFile file,HttpServletResponse response) throws IOException{
		//获取新的文件名称
		String filename = UploadUtils.getInstance().saveImageFileToPath(file.getOriginalFilename(),file.getInputStream(),UploadUtils.SHOP);
		response.setContentType("text/html;charset=utf-8");
		AjaxObj ajaxObj = new AjaxObj();
		ajaxObj.setObj(filename);
		response.getWriter().write(JSONUtils.getInstance().obj2Json(ajaxObj));
	}
}
