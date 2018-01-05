package com.xunfang.bdpf.mllib.assembly.service;

import java.util.List;
import java.util.Map;

import com.xunfang.bdpf.mllib.assembly.entity.Columns;

/**
 * @ClassName EchartsService
 * @Description: Echarts实现接口层 Copyright: Copyright (c) 2017
 *               Company:深圳市讯方技术股份有限公司
 *
 * @author
 * @date
 * @version V1.0
 */
public interface EchartsService {
	/**
	 * 
	 * @Title: getColumnByTable 
	 * @Description: 根据表名查询列名
	 * @return List<Columns> 返回类型
	 * @throws
	 */
	List<Columns> getColumnByTable(String table);

	/**
	 * 
	 * @Title: getLine 
	 * @Description: 查询图表统计列表数据(至折线（面积图）) 
	 * @return Map<String, Object>返回类型 
	 * @throws
	 */
	Map<String, Object> getLine(String columnName);

	/**
	 * 
	 * @Title: getScatter 
	 * @Description: 查询图表统计列表数据(散点图) 
	 * @return List<List<Object>>返回类型 
	 * @throws
	 */
	List<List<Object>> getScatter(String columnName);

	/**
	 * 
	 * @Title: getKLine 
	 * @Description: 查询图表统计列表数据(K线图) 
	 * @return List<List<Object>>返回类型 
	 * @throws
	 */
	List<List<Object>> getKLine(String columnName);

	/**
	 * 
	 * @Title: getBar 
	 * @Description: 查询图表统计列表数据(柱状图) 
	 * @return Map<String, Object> 返回类型 
	 * @throws
	 */
	Map<String, Object> getBar(String columnName);

	/**
	 * 
	 * @Title: getPie 
	 * @Description: 查询图表统计列表数据(饼（圆环）图) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @throws
	 */
	List<Map<String, Object>> getPie(String columnName);

	/**
	 * 
	 * @Title: getFunnel 
	 * @Description: 查询图表统计列表数据(漏斗图 ) 
	 * @return Map<String, Object> 返回类型 
	 * @throws
	 */
	List<Map<String, Object>> getFunnel(String columnName);

	/**
	 * 
	 * @Title: getRectangle 
	 * @Description: 查询图表统计列表数据(矩形树图) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @throws
	 */
	List<Map<String, Object>> getRectangle(String columnName);

	/**
	 * 
	 * @Title: getChord 
	 * @Description: 查询图表统计列表数据(和弦图) 
	 * @return List<List<Object>> 返回类型 
	 * @throws
	 */
	List<List<Object>> getChord(String columnName);

	/**
	 * 
	 * @Title: getMix 
	 * @Description: 查询图表统计列表数据(混搭图) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @throws
	 */
	List<Map<String, Object>> getMix(String columnName);

	/**
	 * 
	 * @Title: getForce 
	 * @Description: 查询图表统计列表数据(力导向布局图 ) 
	 * @return List<List<Object>> 返回类型 
	 * @throws
	 */
	List<List<Object>> getForce(String columnName);

	/**
	 * 
	 * @Title: getRadar 
	 * @Description: 查询图表统计列表数据(雷达图  ) 
	 * @return List<Map<String, Object>>  返回类型 
	 * @throws
	 */
	List<Map<String, Object>> getRadar(String columnName);

	/**
	 * 
	 * @Title: getGauge 
	 * @Description: 查询图表统计列表数据(仪表盘图) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @throws
	 */
	List<Map<String, Object>>  getGauge(String columnName);
	
	/**
	 * @Title: getVenn 
	 * @Description: 查询图表统计列表数据(韦恩图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @throws
	 */
	List<Map<String, Object>> getVenn(String columnName);
	
	/**
	 * @Title: getHeatmap 
	 * @Description: 查询图表统计列表数据(热力图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @throws
	 */
	List<Map<String, Object>> getHeatmap(String columnName);
	
	/**
	 * @Title: getWordCloud 
	 * @Description: 查询图表统计列表数据(字符云图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @throws
	 */
	List<Map<String, Object>> getWordCloud(String columnName);
	
	/**
	 * @Title: getWordCloud 
	 * @Description: 查询图表统计列表数据(树图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @throws
	 */
	List<Map<String, Object>> getTree(String columnName);
	
	/**
	 * @Title: getMap 
	 * @Description: 查询图表统计列表数据(地图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @throws
	 */
	Map<String,List<Map<String, Object>>> getMap(String columnName);
}
