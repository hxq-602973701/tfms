package com.explorer.tfms.dao.impl;
import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.ActivityDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.Activity;
@Repository("activityDao")
public class ActivityDaoImpl extends BaseDaoImpl<Activity> implements ActivityDao{
	
}
