package com.xunfang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @ClassName DateUtil
 * @Description: Date工具类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年5月26日 上午9:35:43
 * @version V1.0
 */
public class DateUtil {

	/**
	 * 
	  * 字符串转为Data
	  *
	  * @Title: getDate
	  * @Description: 字符串转为Data
	  * @param @param date
	  * @param @param pattern
	  * @param @return    设定文件
	  * @return Date    返回类型
	  * @throws
	 */
	public static Date getDate(String date, String pattern){
		Date result = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			result = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	  * Data转为字符串
	  *
	  * @Title: getDate
	  * @Description: Data转为字符串
	  * @param @param date
	  * @param @param pattern
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String getDate(Date date, String pattern){
		String result = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		result = sdf.format(date);
		return result;
	}
}