package com.xunfang.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @ClassName PropertiesUtil
 * @Description: 读取配置文件类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年5月26日 上午9:06:49
 * @version V1.0
 */
public class PropertiesUtil {

	//定义配置类
	private static Properties properties = null;
	
	//定义配置文件名称+扩展名
	private static final String PARAM_NAME = "application.properties"; 
	
	/**
	* @Fields ENDPOINT_SERVICE_IDENTITY : 身份验证服务地址
	*/
	public static String ENDPOINT_SERVICE_IDENTITY = "http://172.0.0.0:5000/v3";
	
	
	/**
	* @Fields ENDPOINT_SERVICE_IDENTITY_ATTRIBUTE : 身份验证服务key
	*/
	private static String ENDPOINT_SERVICE_IDENTITY_ATTRIBUTE = "ENDPOINT_SERVICE.IDENTITY";
	
	
	/**
	* @Fields IDENTITY_AUTH_DOMAIN : 域名（缺失值：default）
	*/
	public static String IDENTITY_AUTH_DOMAIN = "default";
	
	
	/**
	* @Fields IDENTITY_AUTH_DOMAIN_ATTRIBUTE : 域名key
	*/
	private static String IDENTITY_AUTH_DOMAIN_ATTRIBUTE = "IDENTITY_AUTH.DOMAIN";
	
	//静态块，当配置类为空时，自动加载load()方法
	static{
		if(properties == null){
			try {
				load();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	  * 加载并读取配置文件
	  *
	  * @Title: load
	  * @Description: 加载并读取配置文件
	  * @param @throws FileNotFoundException
	  * @param @throws IOException    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	private static void load() throws FileNotFoundException, IOException{
//		String path = PropertiesUtil.class.getResource("/").getPath();
//		properties = new Properties();
//		properties.load(new FileInputStream(path + PARAM_NAME));
		
		//update jm 2016-09-23 在server2008中获取配置文件路径方法
		InputStream path = PropertiesUtil.class.getClassLoader().getResourceAsStream(PARAM_NAME);
		properties = new Properties();
		properties.load(path);
		
		ENDPOINT_SERVICE_IDENTITY = getValue(ENDPOINT_SERVICE_IDENTITY_ATTRIBUTE);
		IDENTITY_AUTH_DOMAIN = getValue(IDENTITY_AUTH_DOMAIN_ATTRIBUTE);
	}
	
	/**
	 * 
	  * 根据配置文件的key获取value
	  *
	  * @Title: getValue
	  * @Description: 根据配置文件的key获取value
	  * @param @param key
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	public static String getValue(String key){
		return properties.getProperty(key);
	}
}