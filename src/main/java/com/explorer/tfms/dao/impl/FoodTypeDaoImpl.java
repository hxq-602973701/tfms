package com.explorer.tfms.dao.impl;
import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.FoodTypeDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.FoodType;
/**
 * 食品类型数据库操作类
 * @author Administrator
 */
@Repository("foodTypeDao")
public class FoodTypeDaoImpl extends BaseDaoImpl<FoodType> implements FoodTypeDao{

}
