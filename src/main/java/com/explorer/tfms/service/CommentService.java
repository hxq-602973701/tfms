package com.explorer.tfms.service;
import com.explorer.tfms.domain.Comment;
import com.explorer.tfms.domain.PageBean;
public interface CommentService {
	public PageBean<Comment> findShopComemnts(Long shopId,int currentPage);

	public void saveComment(String content, Long userId, Long shopId);
	
	public PageBean<Comment> findShopComemnts(String search,int currentPage,int pageSize);
	
	public Comment getComment(Long id);
	
	public void deleteComment(Long id);
}
