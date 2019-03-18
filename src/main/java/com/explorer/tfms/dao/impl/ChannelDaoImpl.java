package com.explorer.tfms.dao.impl;
import org.springframework.stereotype.Repository;
import com.explorer.tfms.dao.ChannelDao;
import com.explorer.tfms.dao.base.impl.BaseDaoImpl;
import com.explorer.tfms.domain.Channel;
@Repository("channelDao")
public class ChannelDaoImpl extends BaseDaoImpl<Channel> implements ChannelDao{

}
