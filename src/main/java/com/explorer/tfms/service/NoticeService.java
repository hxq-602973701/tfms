package com.explorer.tfms.service;
import java.util.List;
import com.explorer.tfms.domain.Notice;
import com.explorer.tfms.domain.PageBean;

public interface NoticeService {
	
	public PageBean<Notice> findAllNotices(int currentPage,int pageSize);
	
	public List<Notice> list6Notices();

	public PageBean<Notice> listAllNotices(int currentPage,int pageSize);
	
	public void saveNotice(Notice notice);
	
	public void updateNotice(Notice notice);
	
	public Notice getNotice(Long id);
	
	public void deleteNotice(Long id);
}
