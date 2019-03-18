package com.explorer.tfms.service;

import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.User;

public interface UserService {
	public User getUser(String username);
	
	public PageBean<User> findAllUsers(int currentPage,int pageSize);
	
	public User getUser(Long id);
	
	public void saveUser(User user);
	
	public void deleteUser(Long id);
	
	public void updateUser(User user);
}
