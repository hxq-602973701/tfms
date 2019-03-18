package com.explorer.tfms.service.impl;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.explorer.tfms.dao.ArticleDao;
import com.explorer.tfms.dao.ChannelDao;
import com.explorer.tfms.domain.Article;
import com.explorer.tfms.domain.Channel;
import com.explorer.tfms.service.ArticleService;
import com.explorer.tfms.utils.DateUtils;
import com.explorer.tfms.utils.TFMSException;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService{
	
	@Resource(name="articleDao")
	private ArticleDao articleDao;
	@Resource(name="channelDao")
	private ChannelDao channelDao;
	
	/**
	 * 查询所有文章
	 * @param currentPage 
	 * @param pageSize
	 * @date: 3-23 下午02:17:36
	 * @version: V1.0
	 *
	 */
	public List<Article> findAllArticles(){
		return this.articleDao.listAll();
	}

	
	/**
	 * 保存文章
	 * @version: V1.0
	 *
	 */
	public void saveArticle(Article article,Long title){
		Article a = this.getArticleByTitle(title);
		if(a!=null){
			throw new TFMSException("这篇文章已经存在！");
		}
		Channel c = (Channel)this.channelDao.getEntity(title);
		article.setTitle(c);
		article.setCreateTime(DateUtils.formatDate(new Date(),"yyyy-MM-dd hh:MM:ss"));
		this.articleDao.saveEntity(article);
	}
	
	/**
	 * 修改公告信息
	 */
	public void updateArticle(Article article,Long title){
		Channel c = (Channel)this.channelDao.getEntity(title);
		article.setTitle(c);
		this.articleDao.updateEntity(article);
	}
	
	/** 
	 * 通过id得到公告对象
	 */
	public Article getArticle(Long id){
		return (Article)this.articleDao.getEntity(id);
	}
	
	/**
	 * 通过Id删除公告信息
	 */
	public void deleteArticle(Long id){
		Article article = this.getArticle(id);
		this.articleDao.deleteEntity(article);
	}
	
	/**
	 * 通过标题获得文章
	 * @param titleId
	 * @date: 3-24 上午02:49:59
	 * @version: V1.0
	 *
	 */
	public Article getArticleByTitle(Long titleId){
		return this.articleDao.getArticleByTitle(titleId);
	}
}
