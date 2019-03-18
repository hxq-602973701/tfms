package com.explorer.tfms.web.listener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.explorer.tfms.web.controller.TFMSSessionContext;
/**
 * 实现购物车功能
 * @author Administrator
 */
public class ShopHttpSessionListener implements HttpSessionListener {
	public void sessionCreated(HttpSessionEvent se) {
		// 创建一个购物车，并且把购物车存储到session中
		//System.out.println("创建一个购物车...");
		//se.getSession().setAttribute("order",new Order());
		
	}
	public void sessionDestroyed(HttpSessionEvent se) {
		//se.getSession().removeAttribute("order");
		//se.getSession().getServletContext();
		TFMSSessionContext.removeSession(se.getSession());
		/*Map<String,HttpSession> maps = TFMSSessionContext.getSc();
		Set<String> set = maps.keySet();
		for(String sessionId : set){
			if(sessionId.contains("order_")){
				TFMSSessionContext.removeSession(sessionId);
			}
		}*/
	}

}
