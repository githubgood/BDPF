package com.xunfang.bdpf.mllib.assembly.mapper;

import java.util.List;
import java.util.Map;

import com.xunfang.bdpf.mllib.assembly.entity.Columns;

/**
 * 
 * @ClassName EchartsMapper
 * @Description: Echarts实现接口层 Copyright: Copyright (c) 2017
 *               Company:深圳市讯方技术股份有限公司
 * @author
 * @date
 * @version V1.0
 */
public interface EchartsMapper {
	/**
	 * 
	 * @Title: getColumnByTable @Description: 根据表名查询列名 
	 * @return List<Columns>
	 * 返回类型 @throws
	 */
	List<Columns> getColumnByTable(String table);

	/**
	 * 
	 * @Title: getLine 
	 * @Description: 查询图表统计列表数据(至折线（面积图）) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getLine(Map<String, Object> maps);
	
	
	/**
	 * 
	 * @Title: getScatter 
	 * @Description: 查询图表统计列表数据(散点图) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getScatter(Map<String, Object> maps);


	/**
	 * 
	 * @Title: getKLine 
	 * @Description: 查询图表统计列表数据(K线图) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getKLine(Map<String, Object> maps);

	/**
	 * 
	 * @Title: getBar 
	 * @Description: 查询图表统计列表数据(柱状图) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getBar(Map<String, Object> maps);

	/**
	 * 
	 * @Title: getPie 
	 * @Description: 查询图表统计列表数据(饼（圆环）图) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getPie(Map<String, Object> maps);

	/**
	 * 
	 * @Title: getFunnel 
	 * @Description: 查询图表统计列表数据(漏斗图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getFunnel(Map<String, Object> maps);

	/**
	 * 
	 * @Title: getRectangle 
	 * @Description: 查询图表统计列表数据(矩形树图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getRectangle(Map<String, Object> maps);

	/**
	 * 
	 * @Title: getChord 
	 * @Description: 查询图表统计列表数据(和弦图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getChord(Map<String, Object> maps);

	/**
	 * 
	 * @Title: getCount 
	 * @Description: 获取表中数据条数
	 * @return Long 返回类型 
	 */
	Long getCount();

	/**
	 * 
	 * @Title: getMix 
	 * @Description: 查询图表统计列表数据(混搭图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getMix(Map<String, Object> maps);

	/**
	 * 
	 * @Title: getForce 
	 * @Description: 查询图表统计列表数据(力导向布局图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getForce(Map<String, Object> maps);

	/**
	 * 
	 * @Title: getRadar 
	 * @Description: 查询图表统计列表数据(雷达图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getRadar(Map<String, Object> maps);

	/**
	 * @Title: getGauge 
	 * @Description: 查询图表统计列表数据(仪表盘图) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getGauge(Map<String, Object> maps);
	
	/**
	 * @Title: getVenn 
	 * @Description: 查询图表统计列表数据(韦恩图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getVenn(Map<String, Object> maps);
	
	/**
	 * @Title: getHeatmap 
	 * @Description: 查询图表统计列表数据(热力图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getHeatmap(Map<String, Object> maps);
	
	/**
	 * @Title: getWordCloud 
	 * @Description: 查询图表统计列表数据(字符云图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getWordCloud(Map<String, Object> maps);
	
	/**
	 * @Title: getTree 
	 * @Description: 查询图表统计列表数据(树图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getTree(Map<String, Object> maps);
	
	/**
	 * @Title: getMap 
	 * @Description: 查询图表统计列表数据(地图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 */
	List<Map<String, Object>> getMap(Map<String, Object> maps);
}
