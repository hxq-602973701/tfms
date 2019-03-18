package com.explorer.tfms.service;
import java.util.List;
import com.explorer.tfms.domain.Channel;

public interface ChannelService{
	
	public List<Channel> listAll();
	
	public List<Channel> listSuccessAll();
	
	public void saveChannel(Channel channel);
	
	public void updateChannel(Channel channel);
	
	public void deleteChannel(Long id);
	
	public Channel getChannel(Long id);
}
