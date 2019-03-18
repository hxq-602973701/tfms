package com.explorer.tfms.utils;
import java.util.UUID;

public class GenOrderNumber {
	
	public static String genOrderNum(){
		String num = UUID.randomUUID().toString();
		return num.replace("-","");
	}
	
	public static void main(String[] args) {
		System.out.println(GenOrderNumber.genOrderNum());
	}
}
