package com.explorer.tfms.service;
import java.util.List;
import com.explorer.tfms.domain.Activity;
import com.explorer.tfms.domain.PageBean;
public interface ActivityService{
	
	public PageBean<Activity> findAllActivitys(int currentPage,int pageSize);
	
	public PageBean<Activity> findSuccessActivitys(int currentPage,int pageSize);

	public List<Activity> list6SuccessActivitys();
	
	public void saveActivity(Activity activity);
	
	public void updateActivity(Activity activity);
	
	public Activity getActivity(Long id);
	
	public void deleteActivity(Long id);
	
	public Activity getActivityByTitle(String title);
}
