package com.explorer.tfms.service.impl;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.explorer.tfms.dao.NoticeDao;
import com.explorer.tfms.domain.Notice;
import com.explorer.tfms.domain.PageBean;
import com.explorer.tfms.service.NoticeService;
import com.explorer.tfms.utils.DateUtils;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	
	@Resource(name="noticeDao")
	private NoticeDao noticeDao;
	
	/**
	 * 查询所有公告
	 * @param currentPage 
	 * @param pageSize
	 * @date: 3-23 下午02:17:36
	 * @version: V1.0
	 *
	 */
	public PageBean<Notice> findAllNotices(int currentPage,int pageSize){
		String hql = "FROM Notice";
		return this.noticeDao.findAll(hql, currentPage, pageSize);
	}
	
	/**
	 * 查询首页显示的公告 最多6条
	 */
	public List<Notice> list6Notices(){
		String hql = "FROM Notice n WHERE n.state=?";
		List<Notice> noticeList = this.noticeDao.listAllDESC(hql,"createTime","1");
		if(noticeList.size()>6) noticeList = noticeList.subList(0,6);
		return noticeList;
	}

	/**
	 * 显示所有发表的公告
	 * @date: 3-23 下午02:24:03
	 * @version: V1.0
	 *
	 */
	public PageBean<Notice> listAllNotices(int currentPage,int pageSize){
		String hql = "FROM Notice n WHERE n.state=? ORDER BY createTime DESC";
		return this.noticeDao.findAll(hql, currentPage, pageSize,new Object[]{"1"});
	}
	
	/**
	 * 保存公告
	 * @version: V1.0
	 *
	 */
	public void saveNotice(Notice notice){
		notice.setCreateTime(DateUtils.formatDate(new Date(),"yyyy-MM-dd hh:MM:ss"));
		this.noticeDao.saveEntity(notice);
	}
	
	/**
	 * 修改公告信息
	 */
	public void updateNotice(Notice notice){
		this.noticeDao.updateEntity(notice);
	}
	
	/** 
	 * 通过id得到公告对象
	 */
	public Notice getNotice(Long id){
		return (Notice)this.noticeDao.getEntity(id);
	}
	
	/**
	 * 通过Id删除公告信息
	 */
	public void deleteNotice(Long id){
		Notice notice = this.getNotice(id);
		this.noticeDao.deleteEntity(notice);
	}
}
