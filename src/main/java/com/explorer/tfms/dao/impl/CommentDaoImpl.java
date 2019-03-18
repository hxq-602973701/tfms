package com.explorer.tfms.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.CommentDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.Comment;
@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao{
	
	public List<Comment> findUserComments(Long userId){
		String hql = "FROM Comment c WHERE c.user.id=?";
		return this.listAllByArgs(hql,new Object[]{userId});
	}
}
