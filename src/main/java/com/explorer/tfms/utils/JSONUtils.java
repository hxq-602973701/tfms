package com.explorer.tfms.utils;
import java.io.IOException;
import java.io.StringWriter;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * JSON生成工具
 * @author Administrator
 */
public class JSONUtils {
	private static JSONUtils jsonUtils = null;
	private static JsonFactory jf = null;
	private static ObjectMapper mapper = null;
	
	private JSONUtils(){
	}
	
	public static JSONUtils getInstance(){
		if(jsonUtils==null){
			jsonUtils = new JSONUtils();
		}
		return jsonUtils;
	}
	
	public static JsonFactory getFactory(){
		if(jf==null){
			jf = new JsonFactory();
		}
		return jf;
	}
	
	public static ObjectMapper getMapper(){
		if(mapper == null){
			mapper = new ObjectMapper();
		}
		return mapper;
	}
	
	/**
	 * 对象转换为JSON
	 * @return:
	 * @throws: 
	 * @date: 3-14 下午11:39:50
	 * @version: V1.0
	 *
	 */
	public String obj2Json(Object obj){
		//json生成器
		JsonGenerator jg = null;
		try {
			jf = getFactory();
			mapper = getMapper();
			StringWriter out = new StringWriter();
			jg = jf.createGenerator(out);
			mapper.writeValue(jg,obj);
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(jg!=null){
				try {
					jg.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 将JSON转换成对象
	 * @param json
	 * @param clz
	 * @return:
	 * @throws: 
	 * @date: 3-14 下午11:47:24
	 * @version: V1.0
	 *
	 */
	public Object json2obj(String json,Class<?> clz) {
		try {
			mapper = getMapper();
			return mapper.readValue(json,clz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
