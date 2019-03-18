package com.explorer.tfms.service.impl;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.explorer.tfms.dao.CommentDao;
import com.explorer.tfms.dao.ShopDao;
import com.explorer.tfms.dao.UserDao;
import com.explorer.tfms.domain.Comment;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.domain.User;
import com.explorer.tfms.service.CommentService;
import com.explorer.tfms.utils.DateUtils;
import com.explorer.tfms.utils.StringUtils;
@Service("commentService")
public class CommentServiceImpl implements CommentService{
	@Resource(name="commentDao")
	private CommentDao commentDao;
	@Resource(name="userDao")
	private UserDao userDao;
	@Resource(name="shopDao")
	private ShopDao shopDao;
	
	public PageBean<Comment> findShopComemnts(Long shopId,int currentPage){
		String hql = "FROM Comment c WHERE c.shop.id=?";
		return this.commentDao.findAll(hql,currentPage,new Object[]{shopId});
	}

	public void saveComment(String content, Long userId, Long shopId) {
		Comment comment = new Comment();
		User user = (User)this.userDao.getEntity(userId);
		Shop shop = (Shop)this.shopDao.getEntity(shopId);
		comment.setShop(shop);
		comment.setUser(user);
		comment.setContent(content);
		comment.setReplyTime(DateUtils.formatDate(new Date(),"yyyy-MM-dd hh:MM:ss"));
		this.commentDao.saveEntity(comment);
	}
	
	/**
	 * 分页搜索查询
	 * @param search
	 * @param currentPage
	 * @param pageSize
	 * @return:
	 * @throws: 
	 * @date: 3-31 下午07:20:04
	 * @version: V1.0
	 *
	 */
	public PageBean<Comment> findShopComemnts(String search,int currentPage,int pageSize){
		String hql = null;
		PageBean<Comment> pageBean = null;
		if(StringUtils.getInstance().isEmpty(search)){
			hql = "FROM Comment";
			pageBean = this.commentDao.findAll(hql,currentPage,pageSize);
		}else{
			hql = "FROM Comment c WHERE c.shop.name like ?";
			pageBean = this.commentDao.findAll(hql,currentPage,pageSize,new Object[]{"%"+search+"%"});
		}
		return pageBean;
	}
	
	public Comment getComment(Long id){
		return (Comment)this.commentDao.getEntity(id);
	}
	
	public void deleteComment(Long id){
		Comment comment = getComment(id);
		this.commentDao.deleteEntity(comment);
	}
}
