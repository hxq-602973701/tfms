package com.explorer.tfms.web.controller;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class TFMSSessionContext {
	private static final Map<String,HttpSession> sc = new HashMap<String,HttpSession>();
	
	private TFMSSessionContext(){}
	
	public static void addSession(HttpSession session){
		sc.put(session.getId(),session);
	}
	
	public static void removeSession(HttpSession session){
		sc.remove(session.getId());
	}
	
	public static void removeSession(String sessionId){
		sc.remove(sessionId);
	}
	
	public static HttpSession getSession(String sessionId){
		return sc.get(sessionId);
	}

	public static Map<String, HttpSession> getSc() {
		return sc;
	}
}
