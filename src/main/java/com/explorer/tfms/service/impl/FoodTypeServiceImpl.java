package com.explorer.tfms.service.impl;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.explorer.tfms.dao.FoodTypeDao;
import com.explorer.tfms.domain.FoodType;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.service.FoodTypeService;
import com.explorer.tfms.utils.TFMSException;
/**
 * 食品种类的业务逻辑
 * @author Administrator
 */
@Service("foodTypeService")
public class FoodTypeServiceImpl implements FoodTypeService{
	@Resource(name="foodTypeDao")
	private FoodTypeDao foodTypeDao;
	
	/**
	 * 分页查询所有食物类型列表
	 * @param currentPage  当前页
	 * @param pageSize   每页显示的条数
	 * @return:
	 * @date: 3-9 下午01:26:00
	 * @version: V1.0
	 *
	 */
	public PageBean<FoodType> findAllFoodTypes(int currentPage,int pageSize){
		String hql = "FROM FoodType";
		return this.foodTypeDao.findAll(hql,currentPage,pageSize);
	}
	
	public FoodType getFoodTypeByName(String name){
		String hql = "FROM FoodType ft WHERE ft.name=?";
		return (FoodType)this.foodTypeDao.getEntiyByHql(hql,new Object[]{name});
	}
	
	/**
	 * 添加食品种类方法
	 */
	public void saveFoodType(FoodType foodType) {
		FoodType ft = getFoodTypeByName(foodType.getName());
		if(ft!=null){
			throw new TFMSException("食物类型名称已经存在！");
		}
		this.foodTypeDao.saveEntity(foodType);
	}

	/**
	 * 通过id得到食品类型对象
	 */
	public FoodType getFoodType(Long id) {
		return (FoodType)this.foodTypeDao.getEntity(id);
	}

	/**
	 * 修改方法
	 */
	public void updateFoodType(FoodType foodType) {
		this.foodTypeDao.updateEntity(foodType);
	}

	/**
	 * 删除方法
	 */
	public void deleteFoodType(Long id){
		//TODO 判断商品类型下所有商店
		FoodType foodType = getFoodType(id);
		this.foodTypeDao.deleteEntity(foodType);
	}
	
	/**
	 * 查询所有启用的商品类型
	 * @date: 3-15 下午06:46:20
	 * @version: V1.0
	 *
	 */
	public List<FoodType> listPublicFoodTypes(){
		String hql = "FROM FoodType ft WHERE ft.state=?";
		return this.foodTypeDao.listAllByArgs(hql,"1");
	}
}
