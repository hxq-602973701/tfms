package com.explorer.tfms.service.impl;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.explorer.tfms.dao.ActivityDao;
import com.explorer.tfms.domain.Activity;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.service.ActivityService;
import com.explorer.tfms.utils.DateUtils;
import com.explorer.tfms.utils.TFMSException;
/**
 * 活动实体类管理
 * @author Administrator
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService{
	@Resource(name="activityDao")
	private ActivityDao activityDao;
	
	/**
	 * 查询所有活动
	 * @param currentPage 
	 * @param pageSize
	 * @date: 3-23 下午02:17:36
	 * @version: V1.0
	 *
	 */
	public PageBean<Activity> findAllActivitys(int currentPage,int pageSize){
		String hql = "FROM Activity";
		return this.activityDao.findAll(hql, currentPage, pageSize);
	}
	
	/**
	 * 查询所有发布活动
	 * @param currentPage 
	 * @param pageSize
	 * @date: 3-23 下午02:17:36
	 * @version: V1.0
	 *
	 */
	public PageBean<Activity> findSuccessActivitys(int currentPage,int pageSize){
		String hql = "FROM Activity a WHERE a.state=?";
		return this.activityDao.findAll(hql, currentPage, pageSize,new Object[]{"1"});
	}

	/**
	 * 显示所有发布的公告
	 */
	public List<Activity> list6SuccessActivitys(){
		String hql = "FROM Activity a WHERE a.state=?";
		List<Activity> aList = this.activityDao.listAllDESC(hql, "createTime","1");
		if(aList.size()>6)aList = aList.subList(0,6);
		return aList;
	}
	
	/**
	 * 保存活动
	 * @version: V1.0
	 *
	 */
	public void saveActivity(Activity activity){
		Activity a = this.getActivityByTitle(activity.getTitle());
		if(a!=null){
			throw new TFMSException("这篇活动已经存在！");
		}
		activity.setCreateTime(DateUtils.formatDate(new Date(),"yyyy-MM-dd hh:MM:ss"));
		this.activityDao.saveEntity(activity);
	}
	
	/**
	 * 修改公告信息
	 */
	public void updateActivity(Activity activity){
		this.activityDao.updateEntity(activity);
	}
	
	/** 
	 * 通过id得到公告对象
	 */
	public Activity getActivity(Long id){
		return (Activity)this.activityDao.getEntity(id);
	}
	
	/**
	 * 通过Id删除公告信息
	 */
	public void deleteActivity(Long id){
		Activity Activity = this.getActivity(id);
		this.activityDao.deleteEntity(Activity);
	}
	
	/**
	 * 通过标题获得活动
	 * @param titleId
	 * @date: 3-24 上午02:49:59
	 * @version: V1.0
	 *
	 */
	public Activity getActivityByTitle(String title){
		String hql = "FROM Activity a WHERE a.title=?";
		return (Activity) this.activityDao.getEntiyByHql(hql, new Object[]{title});
	}
}
