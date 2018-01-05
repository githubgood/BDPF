package com.xunfang.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName JsonUtils
 * @Description: JSON转为工具类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年5月26日 上午9:24:05
 * @version V1.0
 */
public class JsonUtils {
	
	/**
	 * 
	  * Object转为JSON字符串
	  *
	  * @Title: getJsonFromObject
	  * @Description: Object转为JSON字符串
	  * @param @param obj
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String getJsonFromObject(Object obj) {
		if(obj == null) return "";
		if(obj instanceof List || obj.getClass().isArray()) {
			JSONArray array = JSONArray.fromObject(obj);
			return array.toString();
		}else  {
			JSONObject jsonObjectFromMap = JSONObject.fromObject(obj);
			return jsonObjectFromMap.toString();
		}
	}
	
	/**
	 * 
	  * 将一个对象打印输出JSON字符串
	  *
	  * @Title: printWriterJson
	  * @Description: 将一个对象打印输出JSON字符串
	  * @param @param response
	  * @param @param object    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public static void printWriterJson(HttpServletResponse response, Object object) {
		String str = getJsonFromObject(object);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(str);  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }
	}
	
	/**
	 * 
	  * 将多个对象打印输出JSON字符串
	  *
	  * @Title: printWriterJson
	  * @Description: 将多个对象打印输出JSON字符串
	  * @param @param response
	  * @param @param object    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public static void printWriterJson(HttpServletResponse response,Object...args) {
		List<Object> keys = new ArrayList<Object>();
		List<Object> values = new ArrayList<Object>();
		Map<Object, Object> map = new LinkedHashMap<Object, Object>();
		for(int i=0;i<args.length;i++) {
			if(i%2==0) {
				keys.add(args[i]);
				continue;
			}
			values.add(args[i]);
		}
		if(keys.size() > 0) {
			for(int i=0;i<keys.size();i++) {
				map.put(keys.get(i), values.get(i));
			}
		}
		String str = getJsonFromObject(map);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(str);  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }
	}
	
	/**
	 * 
	  * 将Map对象打印输出JSON字符串
	  *
	  * @Title: printWriterJson
	  * @Description: 将Map对象打印输出JSON字符串
	  * @param @param response
	  * @param @param object    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public static void printWriterJson(HttpServletResponse response, Map<String, Object> map) {
		String jsonStr = JSONObject.fromObject(map).toString();
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(jsonStr);  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }
	}
}