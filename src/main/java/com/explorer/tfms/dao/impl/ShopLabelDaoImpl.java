package com.explorer.tfms.dao.impl;

import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.ShopLabelDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.ShopLabel;
@Repository("shopLabelDao")
public class ShopLabelDaoImpl extends BaseDaoImpl<ShopLabel> implements ShopLabelDao{
}
