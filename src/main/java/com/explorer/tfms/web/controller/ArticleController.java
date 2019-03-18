package com.explorer.tfms.web.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.explorer.tfms.domain.Article;
import com.explorer.tfms.service.ArticleService;
import com.explorer.tfms.service.ChannelService;
import com.explorer.tfms.web.dto.ArticleDto;

@Controller("articleController")
@RequestMapping("/article/")
public class ArticleController {
	
	@Resource(name="articleService")
	private ArticleService articleService;
	@Resource(name="channelService")
	private ChannelService channelService;
	
	/**
	 * 文章列表
	 * @date: 3-23 下午02:11:31
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(Model model){
		List<Article> articleList = this.articleService.findAllArticles();
		model.addAttribute("articleList",articleList);
		return "article/articleList";
	}
	
	/**
	 * 文章添加页面
	 * @date: 3-23 下午02:30:40
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("channelList",this.channelService.listSuccessAll());
		model.addAttribute("articleDto",new ArticleDto());
		return "article/addArticle";
	}
	
	/**
	 * 文章添加方法
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(Model model,@Validated ArticleDto articleDto,BindingResult br){
		if(br.hasErrors()){
			model.addAttribute("channelList",this.channelService.listSuccessAll());
			return "article/addArticle";
		}
		this.articleService.saveArticle(articleDto.getArticle(),articleDto.getTitle());
		return "redirect:/article/list";
	}
	
	/**
	 * 文章修改页面
	 * @date: 3-23 下午02:30:40
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		Article c = articleService.getArticle(id);
		model.addAttribute("channelList",this.channelService.listSuccessAll());
		model.addAttribute("articleDto",new ArticleDto(c,c.getTitle().getId()));
		return "article/updateArticle";
	}
	
	/**
	 * 文章修改方法
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.POST)
	public String update(Model model,@Validated ArticleDto articleDto,BindingResult br){
		if(br.hasErrors()){
			model.addAttribute("channelList",this.channelService.listSuccessAll());
			return "article/updateArticle";
		}
		this.articleService.updateArticle(articleDto.getArticle(),articleDto.getTitle());
		return "redirect:/article/list";
	}
	
	/**
	 * 删除文章信息
	 */
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Long id){
		this.articleService.deleteArticle(id);
		return "redirect:/article/list";
	}
}
