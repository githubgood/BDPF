package com.xunfang.bdpf.mllib.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;

import com.xunfang.utils.PropertiesUtil;

/**
 * 
 * @ClassName AlgorithmFactory
 * @Description: Algorithm service factory 算法工厂类，
 * 				包括:回归算法、分类算法、聚类算法、关联规则算法、推荐算法、预测评估等一系列算法
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author houjingru
 * @date 2017年9月18日 下午9:10:36
 * @version V1.0
 */
public class AlgorithmFactory {

	private final static Log log = LogFactory.getLog(AlgorithmFactory.class);

/***********************JavaSparkContext对象 相关操作（创建/关闭）*****************************/
	
	/**
	 * 
	  * @Title: init
	  * @Description: 初始化环境配置
	  *  @param appName 自定义算法名称
	  *  @param master int 线程数
	  *  @return  JavaSparkContext 返回类型
	  * @throws
	 */
	public static JavaSparkContext initJavaSparkEnv(String appName,int master){
		//System.setProperty("hadoop.home.dir", PropertiesUtil.getValue("HADOOP_PATH"));
		//System.setProperty("hadoop.home.dir", "D:/hadoop-2.6.5");
		SparkConf sparkConf = new SparkConf()
				.setAppName(appName)
				.setMaster("local["+master+"]")
				.set("spark.driver.allowMultipleContexts","true")
				.set("spark.port.maxRetries","128");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        return jsc;
	}
	/**
	 * 
	  * @Title: stop
	  * @Description: 关闭JavaSparkContext对象
	  *  @param jsc  void 返回类型
	  * @throws
	 */
	public static void stopJavaSparkContext(JavaSparkContext jsc){
		jsc.stop();
	}
/****************************SparkContext对象 相关操作（创建/关闭）******************************/	
	
	/**
	 * @Title:intiSparkEnv
	 * @Description: 初始化环境配置
	 * @param appName 算法名称
	 * @param master int 线程数
	 * @return SparkContext 返回类型为SparkContext对象
	 */
	public static SparkContext initSparkEnv(String appName,int master){
		System.setProperty("hadoop.home.dir", "D:/hadoop-2.6.5");
		SparkConf conf = new SparkConf()
				.setAppName(appName)
				.setMaster("local["+master+"]")
				.set("spark.driver.allowMultipleContexts","true")
				.set("spark.port.maxRetries","128");
		SparkContext sc = new SparkContext(conf);
		return sc;
	}
	/**
	 * @Title: stop
	 * @Description: 关闭SparkContext对象
	 * 	@param sc void 返回类型
	 * @throws
	 */
	public static void stopSparkContext(SparkContext sc){
		sc.stop();
	}
}
