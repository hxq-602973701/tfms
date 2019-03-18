package com.explorer.tfms.service.impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.explorer.tfms.dao.ShopLabelDao;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.domain.ShopLabel;
import com.explorer.tfms.service.ShopLabelService;
import com.explorer.tfms.utils.TFMSException;
@Service("shopLabelService")
public class ShopLabelServiceImpl implements ShopLabelService{
	@Resource(name="shopLabelDao")
	private ShopLabelDao shopLabelDao;
	
	public PageBean<ShopLabel> findAllShopLabels(int currentPage,int pageSize){
		String hql = "FROM ShopLabel";
		return this.shopLabelDao.findAll(hql, currentPage, pageSize);
	}
	
	public List<ShopLabel> listActiveShopLabels(){
		String hql = "FROM ShopLabel sl WHERE sl.state=?";
		return this.shopLabelDao.listAllByArgs(hql,"1");
	}
	
	public ShopLabel getShopLabelByName(String name){
		String hql = "FROM ShopLabel sl WHERE sl.name=?";
		return (ShopLabel)this.shopLabelDao.getEntiyByHql(hql,new Object[]{name});
	}
	
	public void saveShopLabel(ShopLabel shopLabel){
		ShopLabel sl = getShopLabelByName(shopLabel.getName());
		if(sl!=null){
			throw new TFMSException("商铺的标签名称不能重复！");
		}
		this.shopLabelDao.saveEntity(shopLabel);
	}
	
	public ShopLabel getShopLabel(Long id){
		return (ShopLabel)this.shopLabelDao.getEntity(id);
	}
	
	public void updateShopLabel(ShopLabel shopLabel){
		this.shopLabelDao.updateEntity(shopLabel);
	}
	
	public void deleteShopLabel(Long id){
		//TODO 查询商铺标签下面是否含有商铺
		ShopLabel sl = getShopLabel(id);
		this.shopLabelDao.deleteEntity(sl);
	}
}
