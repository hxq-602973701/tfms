package com.explorer.tfms.dao;

import java.util.List;

import com.explorer.tfms.dao.base.BaseDao;
import com.explorer.tfms.domain.Comment;

public interface CommentDao extends BaseDao<Comment>{
	public List<Comment> findUserComments(Long userId);
}
