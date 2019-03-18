package com.explorer.tfms.dao.impl;
import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.NoticeDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.Notice;
@Repository("noticeDao")
public class NoticeDaoImpl extends BaseDaoImpl<Notice> implements NoticeDao{

}
