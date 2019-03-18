package com.explorer.tfms.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.explorer.tfms.web.controller.TFMSSessionContext;

public class TFMSSessionListener implements HttpSessionListener{

	/**
	 * 创建session
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		/*这里创建session消耗性能，应该放在登陆成功后移除session
		TFMSSessionContext.addSession(event.getSession());
		System.out.println("将session放到map集合");*/
	}

	/**
	 * 移除session
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		TFMSSessionContext.removeSession(event.getSession());
		System.out.println("将session移除map集合");
	}

}
