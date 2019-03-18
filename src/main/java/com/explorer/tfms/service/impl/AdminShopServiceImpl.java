package com.explorer.tfms.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import com.explorer.tfms.dao.AdminDao;
import com.explorer.tfms.dao.AdminShopDao;
import com.explorer.tfms.dao.ShopDao;
import com.explorer.tfms.domain.Admin;
import com.explorer.tfms.domain.Shop;
import com.explorer.tfms.service.AdminShopService;
@Service("adminShopService")
public class AdminShopServiceImpl implements AdminShopService{
	
	@Resource(name="adminDao")
	private AdminDao adminDao;
	@Resource(name="adminShopDao")
	private AdminShopDao adminShopDao;
	@Resource(name="shopDao")
	private ShopDao shopDao;
	
	public void saveShop2Admin(Long[] shopIds,Long adminId){
		Admin admin = (Admin) this.adminDao.getEntity(adminId);
		//该管理员有拥有的商铺
		List<Long> ownshopIds = this.shopDao.listAdminShops(adminId);
		//上传的所有商铺中有，本省拥有的商铺中没有就添加
		for(Long id:shopIds){
			if(!ownshopIds.contains(id)){//本身Id不包含上传Id
				Shop shop = (Shop)this.shopDao.getEntity(id);
				this.adminShopDao.saveShop2Admin(shop,admin);
			}
		}
		
		//上传Id没有,本身Id有，删除
		for(Long id:ownshopIds){
			if(!ArrayUtils.contains(shopIds,id)){
				this.adminShopDao.deleteShop2Admin(id,adminId);
			}
		}
	}

	public void deleteAllAdminShops(Long adminId) {
		this.adminShopDao.deleteAllAdminShops(adminId);
	}
}