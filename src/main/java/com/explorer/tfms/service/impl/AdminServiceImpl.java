package com.explorer.tfms.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.explorer.tfms.dao.AdminDao;
import com.explorer.tfms.domain.Admin;
import com.explorer.tfms.service.AdminService;
import com.explorer.tfms.utils.MD5Utils;
import com.explorer.tfms.utils.TFMSException;
@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Resource(name="adminDao")
	private AdminDao adminDao;
	
	public List<Admin> listAllAdmins(){
		return this.adminDao.listAll();
	}
	
	public Admin getAdmin(Long id){
		return (Admin)this.adminDao.getEntity(id);
	}
	
	public Admin getAdmin(String username){
		String hql = "FROM Admin a WHERE a.username=?";
		return (Admin)this.adminDao.getEntiyByHql(hql,new Object[]{username});
	}
	
	public void saveAdmin(Admin admin){
		Admin a = getAdmin(admin.getUsername());
		if(a!=null){
			throw new TFMSException("用户名已经存在");
		}
		admin.setPassword(MD5Utils.getInstance().genMD5Pwd("123456"));
		this.adminDao.saveEntity(admin);
	}
	
	public void updateAdmin(Admin admin){
		this.adminDao.updateEntity(admin);
	}
	
	public void deleteAdmin(Long id){
		Admin admin = getAdmin(id);
		this.adminDao.deleteEntity(admin);
	}
	
	public void initPwd(Long id){
		Admin admin = getAdmin(id);
		admin.setPassword(MD5Utils.getInstance().genMD5Pwd("123456"));
		this.adminDao.updateEntity(admin);
	}
	
}
