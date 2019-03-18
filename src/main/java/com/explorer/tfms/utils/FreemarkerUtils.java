package com.explorer.tfms.utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import org.apache.commons.io.output.FileWriterWithEncoding;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * FreeMarker工具类 
 * @author Administrator
 */
public class FreemarkerUtils {
	private static FreemarkerUtils freemarkerUtils;
	private static Configuration config;
	
	private FreemarkerUtils(){
		
	}
	/**
	 * 创建FreemarkerUtils对象
	 * @param fpath: ftl文件的路径
	 * @date: 3-13 下午02:29:20
	 * @version: V1.0
	 *
	 */
	public static FreemarkerUtils getInstance(String fpath){
		if(freemarkerUtils==null){
			try {
				config = new Configuration(Configuration.VERSION_2_3_23);
				config.setClassForTemplateLoading(FreemarkerUtils.class,fpath);
				config.setDefaultEncoding("urf-8");
				freemarkerUtils = new FreemarkerUtils();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return freemarkerUtils;
	}
	
	/**
	 * 生成Template（模板）对象
	 * @param fname  模板文件路径
	 * @date: 3-13 下午02:31:28
	 * @version: V1.0
	 *
	 */
	private Template getTemplate(String fname) {
		try {
			return config.getTemplate(fname,"utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 输出到控制台
	 * @param maps：输出内容
	 * @param fname：模板文件路径
	 * @date: 3-13 下午02:30:45
	 * @version: V1.0
	 *
	 */
	public void printToConsole(Map<String,Object> maps,String fname){
		try {
			Template template = getTemplate(fname);
			template.process(maps, new PrintWriter(System.out));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param maps：输出内容
	 * @param fname：模板文件路径
	 * @param outpath:输出文件路径
	 * @throws: 
	 * @date: 3-13 下午02:37:59
	 * @version: V1.0
	 *
	 */
	public void printToLocalFile(Map<String,Object> maps,String fname,String outpath){
		try {
			Template template = getTemplate(fname);
			template.process(maps,new FileWriterWithEncoding(outpath, "utf-8"));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
