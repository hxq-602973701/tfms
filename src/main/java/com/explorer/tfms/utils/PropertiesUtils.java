package com.explorer.tfms.utils;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * 类
 * @author Administrator
 */
public class PropertiesUtils {
	private static PropertiesUtils utils = null;
	private static Map<String, Properties> props = null;
	
	private PropertiesUtils(){
		
	}
	
	public static PropertiesUtils getInstance(){
		if(utils == null){
			utils = new PropertiesUtils();
			props = new HashMap<String,Properties>();
		}
		return utils;
	}
	
	/**
	 * 通过文件名获得properties对象
	 * @param name :prperties文件名称
	 * @date: 3-13 下午06:25:26
	 * @version: V1.0
	 *
	 */
	public Properties load(String name) {
		if(props.get(name)!=null) {
			return props.get(name);
		} else {
			Properties prop = new Properties();
			try {
				prop.load(new InputStreamReader(PropertiesUtils.class.getResourceAsStream("/"+name+".properties"),"UTF-8"));
				props.put(name, prop);
				return prop;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 测试方法
	 * @param args:
	 * @throws: 
	 * @date: 3-13 下午06:27:33
	 * @version: V1.0
	 *
	public static void main(String[] args) {
		Properties prop = PropertiesUtils.getInstance().load("system");
		System.out.println(prop.get("tfms.project.realpath"));
	} */
}
