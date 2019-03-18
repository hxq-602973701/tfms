package com.explorer.tfms.utils;
/**
 * 天福美食的系统异常
 * @author Administrator
 */
@SuppressWarnings("serial")
public class TFMSException extends RuntimeException{
	public TFMSException(){
		super();
	}
	
	public TFMSException(String message){
		super(message);
	}
	
	public TFMSException(Throwable cause){
		super(cause);
	}
	
	public TFMSException(String message,Throwable cause){
		super(message,cause);
	}
}
