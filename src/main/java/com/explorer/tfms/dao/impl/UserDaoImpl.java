package com.explorer.tfms.dao.impl;
import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.UserDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

}
