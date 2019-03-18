package com.explorer.tfms.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.explorer.tfms.utils.StringUtils;
import com.explorer.tfms.web.controller.TFMSSessionContext;
/**
 * 验证用户是否在线
 * @author Administrator
 *
 */
public class CheckIsOnline extends HandlerInterceptorAdapter{
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		String sid = request.getParameter("sid");
		if(!StringUtils.getInstance().isEmpty(sid)){
			//当sid有值，就表示是uploadify上传文件此时就应该去原来session
			session = TFMSSessionContext.getSession(sid);
		}
		if(session.getAttribute("admin")==null) {
			response.sendRedirect(request.getContextPath()+"/admin/alogin");
		} 
		return super.preHandle(request, response, handler);
	}
}
