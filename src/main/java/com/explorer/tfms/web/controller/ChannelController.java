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
import com.explorer.tfms.domain.Channel;
import com.explorer.tfms.service.ChannelService;

@Controller("channelController")
@RequestMapping("/channel/")
public class ChannelController {
	
	@Resource(name="channelService")
	private ChannelService channelService;
	
	/**
	 * 模块列表
	 * @date: 3-24 上午01:35:14
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(Model model){
		List<Channel> channelList = this.channelService.listAll();
		model.addAttribute("channelList",channelList);
		return "channel/channelList";
	}
	
	/**
	 * 添加页面
	 * @date: 3-9 下午03:37:47
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("channel",new Channel());
		return "channel/addChannel";
	}
	
	/**
	 * 添加方法
	 * @date: 3-9 下午04:04:41
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(Model model,@Validated Channel channel,BindingResult br){
		if(br.hasErrors()){
			return "channel/addChannel";
		}
		this.channelService.saveChannel(channel);
		return "redirect:/channel/list";
	}
	
	/**
	 * 修改页面
	 * @date: 3-10 上午12:23:39
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable Long id,Model model){
		model.addAttribute("channel",this.channelService.getChannel(id));
		return "channel/updateChannel";
	}
	/**
	 * 修改方法
	 * @date: 3-10 上午12:30:16
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/update",method=RequestMethod.POST)
	public String update(@Validated Channel channel,BindingResult br){
		if(br.hasErrors()){
			return "channel/updateChannel";
		}
		this.channelService.updateChannel(channel);
		return "redirect:/channel/list";
	}
	/**
	 * 删除方法
	 * @date: 3-10 上午12:35:00
	 * @version: V1.0
	 *
	 */
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Long id){
		this.channelService.deleteChannel(id);
		return "redirect:/channel/list";
	}
}
