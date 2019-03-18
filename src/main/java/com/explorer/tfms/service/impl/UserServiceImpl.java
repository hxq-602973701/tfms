package com.explorer.tfms.service.impl;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.explorer.tfms.dao.CommentDao;
import com.explorer.tfms.dao.OrderDao;
import com.explorer.tfms.dao.UserDao;
import com.explorer.tfms.domain.Comment;
import com.explorer.tfms.domain.Order;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.User;
import com.explorer.tfms.service.UserService;
import com.explorer.tfms.utils.DateUtils;
import com.explorer.tfms.utils.MD5Utils;
import com.explorer.tfms.utils.TFMSException;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource(name="userDao")
	private UserDao userDao;
	@Resource(name="commentDao")
	private CommentDao commentDao;
	@Resource(name="orderDao")
	private OrderDao orderDao;
	
	public PageBean<User> findAllUsers(int currentPage,int pageSize){
		String hql = "FROM User";
		return this.userDao.findAll(hql, currentPage, pageSize);
	}
	
	/**
	 * 通过用户名称获得用户对象
	 */
	public User getUser(String username) {
		String hql = "FROM User u WHERE u.username=?";
		return (User)this.userDao.getEntiyByHql(hql,new Object[]{username});
	}

	/**
	 * 通过Id获得用户
	 */
	public User getUser(Long id) {
		return (User) this.userDao.getEntity(id);
	}

	/**
	 * 添加用户
	 */
	public void saveUser(User user){
		user.setPassword(MD5Utils.getInstance().genMD5Pwd(user.getPassword()));
		user.setRegDate(DateUtils.formatDate(new Date(),"yyyy-MM-dd"));
		this.userDao.saveEntity(user);
	}

	public void deleteUser(Long id) {
		List<Comment> commentList = this.commentDao.findUserComments(id);
		if(commentList!=null){
			throw new TFMSException("该用户发表了评论，不能删除！");
		}
		List<Order> orderList = this.orderDao.findUserOrders(id);
		if(orderList!=null){
			throw new TFMSException("该用户以在改系统下过单，不能删除！");
		}
		User user = this.getUser(id);
		this.userDao.deleteEntity(user);
	}
	
	public void updateUser(User user){
		this.userDao.updateEntity(user);
	}
}
