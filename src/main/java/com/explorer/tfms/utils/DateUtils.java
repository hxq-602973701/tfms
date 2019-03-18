package com.explorer.tfms.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期工具类
 * @author Administrator
 */
public class DateUtils {
	/**
	 * 时间格式化
	 * @param date
	 * @param format "yyyy-MM-dd hh:MM:ss"
	 * 			年：yyyy，月：YY，日：dd
	 * 			时：hh，分：MM，秒：ss
	 * @return:
	 * @throws: 
	 * @date: 3-9 上午11:41:42
	 * @version: V1.0
	 *
	 */
	public static String formatDate(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
