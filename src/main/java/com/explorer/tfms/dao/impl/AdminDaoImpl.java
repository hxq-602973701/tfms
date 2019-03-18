package com.explorer.tfms.dao.impl;
import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.AdminDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.Admin;
@Repository("adminDao")
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{
	
}
