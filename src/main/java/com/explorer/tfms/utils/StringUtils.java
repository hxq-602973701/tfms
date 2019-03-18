package com.explorer.tfms.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	private static StringUtils stringUtils = null;
	
	private StringUtils(){}
	
	public static StringUtils getInstance(){
		if(stringUtils==null){
			stringUtils = new StringUtils();
		}
		return stringUtils;
	}
	
	/**
	 * 移除字符串的所有空格
	 * @date: 3-22 下午05:03:52
	 * @version: V1.0
	 *
	 */
	public String removeBlank(String str){
		String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
	}
	
	public boolean isEmpty(String str){
		if(str==null || "".equals(str)){
			return true;
		}
		return false;
	}
	
	public String list2String(List<Long> ids){
		String strs = ids.toString();
		strs = strs.substring(1,strs.length()-1);
		return strs;
	}
	
	public static void main(String[] args) {
		List<Long> id = new ArrayList<Long>();
		id.add(1L);
		id.add(2L);
		id.add(3L);
		id.add(4L);
		System.out.println(StringUtils.getInstance().list2String(id));
	}
}
