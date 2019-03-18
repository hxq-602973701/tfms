package com.explorer.tfms.service;

import java.util.List;

import com.explorer.tfms.domain.Admin;

public interface AdminService {
	
	public List<Admin> listAllAdmins();
	
	public Admin getAdmin(Long id);
	
	public Admin getAdmin(String username);
	
	public void saveAdmin(Admin admin);
	
	public void updateAdmin(Admin admin);
	
	public void deleteAdmin(Long id);
	
	public void initPwd(Long id);
}
