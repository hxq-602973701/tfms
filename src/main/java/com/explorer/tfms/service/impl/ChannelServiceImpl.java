package com.explorer.tfms.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.explorer.tfms.dao.ArticleDao;
import com.explorer.tfms.dao.ChannelDao;
import com.explorer.tfms.domain.Article;
import com.explorer.tfms.domain.Channel;
import com.explorer.tfms.service.ChannelService;
import com.explorer.tfms.utils.TFMSException;

@Service("channelService")
public class ChannelServiceImpl implements ChannelService{
	
	@Resource(name="channelDao")
	private ChannelDao channelDao;
	@Resource(name="articleDao")
	private ArticleDao articleDao;
	
	/**
	 * 不分页查询所有
	 * @date: 3-24 上午01:36:49
	 * @version: V1.0
	 *
	 */
	public List<Channel> listAll(){
		return this.channelDao.listAll();
	}
	
	public List<Channel> listSuccessAll(){
		String hql = "FROM Channel c WHERE c.state=?";
		return this.channelDao.listAllByArgs(hql,"1");
	}
	
	/**
	 * 保存模块
	 * @date: 3-24 上午01:40:56
	 * @version: V1.0
	 *
	 */
	public void saveChannel(Channel channel){
		this.channelDao.saveEntity(channel);
	}
	
	/**
	 * 跟新模块
	 * @date: 3-24 上午01:40:43
	 * @version: V1.0
	 *
	 */
	public void updateChannel(Channel channel){
		this.channelDao.updateEntity(channel);
	}
	
	/**
	 * 删除模块
	 * @date: 3-24 上午01:40:28
	 * @version: V1.0
	 *
	 */
	public void deleteChannel(Long id){
		Article a = this.articleDao.getArticleByTitle(id);
		if(a!=null){
			throw new TFMSException("该模块存在文章，不能删除！");
		}
		Channel channel = this.getChannel(id);
		this.channelDao.deleteEntity(channel);
	}
	
	/**
	 * 通过Id得到模块对象
	 * @date: 3-24 上午01:40:03
	 * @version: V1.0
	 *
	 */
	public Channel getChannel(Long id){
		return (Channel) this.channelDao.getEntity(id);
	}
}
