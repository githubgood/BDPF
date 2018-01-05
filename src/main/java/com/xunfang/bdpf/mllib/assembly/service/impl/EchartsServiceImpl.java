package com.xunfang.bdpf.mllib.assembly.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xunfang.bdpf.mllib.assembly.entity.Columns;
import com.xunfang.bdpf.mllib.assembly.mapper.EchartsMapper;
import com.xunfang.bdpf.mllib.assembly.service.EchartsService;
/**
 * @ClassName EchartsService
 * @Description: Echarts实现接口层
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author 
 * @date 
 * @version V1.0
 */
@Service
@Transactional
public class EchartsServiceImpl implements EchartsService{

	
	@Autowired
	private EchartsMapper echartsMapper;
	/**
	  * 
	  * @Title: getColumnByTable
	  * @Description: 根据表名查询列名
	  * @return List<Columns>  返回类型
	  * @param String table 表名
	  * @throws
	 */
	@Override
	public List<Columns> getColumnByTable(String table) {
		List<Columns> columnLists = Lists.newArrayList();
		columnLists = echartsMapper.getColumnByTable(table);
		if(columnLists.size() > 0){
			for (Columns columns : columnLists) {
				if(columns.getRownum()!=null && !"".equals(columns.getRownum())){
				String str = columns.getRownum();
				char i =	(char) (Integer.parseInt(str) + 64);
				columns.setRownum(String.valueOf(i));
				}
			}
		}	
		return echartsMapper.getColumnByTable(table);
	}
	
	/**
	 * 
	  * @Title: getLine
	  * @Description: 查询图表统计列表数据(至折线（面积图）)
	  * @return  Map<String, Object> 返回类型
	  * @param String columnName 列名
	  * @throws
	 */
	@Override
	public Map<String, Object> getLine(String columnName) {
		Map<String, Object> data = Maps.newHashMap();
		//结果数据集合
		List<Long>  firstParame= Lists.newArrayList();
		List<Object> secondParame = Lists.newArrayList();
		
		// 图表数据
		data.put("firstParame", firstParame);
		data.put("secondParame", secondParame);	
		Map<String, Object> maps = Maps.newHashMap();	
		maps.put("columnName", columnName);
		
		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){			
			// 图表数据
			listData = echartsMapper.getLine(maps);
		}
	    //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				firstParame.add((Long) listData.get(i).get("firstParame"));
				secondParame.add((Object) listData.get(i).get("secondParame"));
			}
		}
		return data;
	}

	
	/**
	 * 
	  * @Title: getScatter
	  * @Description: 查询图表统计列表数据(散点图)
	  * @return  List<List<Object>> 返回类型
	  * @param String columnName 列名
	  * @throws
	 */
	@Override
	public List<List<Object>> getScatter(String columnName) {
		List<List<Object>> data = Lists.newArrayList();
		
		Map<String, Object> maps = Maps.newHashMap();
		maps.put("columnName", columnName);
		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getScatter(maps);
		}
	    //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				//结果数据集合
				List<Object> objList = Lists.newArrayList();	
				objList.add((Object) listData.get(i).get("secondParame"));
				objList.add((Object) listData.get(i).get("firstParame"));
				data.add(objList);
			}
		}
		return data;
	}
	
	
	/**
	 * 
	  * @Title: getKLine
	  * @Description: 查询图表统计列表数据(K线图)
	  * @return  List<List<Object>> 返回类型
	  * @param String columnName 列名
	  * @throws
	 */
	@Override
	public List<List<Object>> getKLine(String columnName) {
		List<List<Object>> data = Lists.newArrayList();		
		Map<String, Object> maps = Maps.newHashMap();		
		maps.put("columnNameA", columnName);
		maps.put("columnNameB", "age");
		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){
			listData = echartsMapper.getKLine(maps);
		}
	    //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				//结果数据集合
				List<Object> objList = Lists.newArrayList();	
				objList.add((Object) listData.get(i).get("firstParame"));
				objList.add((Object) listData.get(i).get("thirdParame"));
				objList.add((Object) listData.get(i).get("fifthParame"));
				objList.add((Object) listData.get(i).get("forthParame"));
				data.add(objList);
			}
		}
		return data;
	}
	
	
	/**
	 * 
	  * @Title: getBar
	  * @Description: 查询图表统计列表数据(柱状图)
	  * @return  Map<String, Object> 返回类型
	  * @param String columnName 列名
	  * @throws
	 */
	@Override
	public Map<String, Object> getBar(String columnName) {
		Map<String, Object> data = Maps.newHashMap();
		//结果数据集合
		List<Long>  firstParame= Lists.newArrayList();
		List<Object> secondParame = Lists.newArrayList();
		
		// 设置参数
		data.put("firstParame", firstParame);
		data.put("secondParame", secondParame);
				
		Map<String, Object> maps = Maps.newHashMap();		
		maps.put("columnName", columnName);
		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){			
			// 图表数据
			listData = echartsMapper.getBar(maps);
		}
	    //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				firstParame.add((Long) listData.get(i).get("firstParame"));
				secondParame.add((Object) listData.get(i).get("secondParame"));
			}
		}
		return data;
	}
	
	/**
	 * 
	  * @Title: getPie
	  * @Description: 查询图表统计列表数据(饼（圆环）图)
	  * @return  Map<String, Object> 返回类型
	  * @param String columnName 列名
	  * @throws
	 */
	@Override
	public List<Map<String, Object>> getPie(String columnName) {
		List<Map<String, Object>> data = Lists.newArrayList();
		Map<String, Object> maps = Maps.newHashMap();
		//设置查询参数
		maps.put("columnName", columnName);
		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getPie(maps);
		}
		 //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				Map<String,Object>  parame= Maps.newHashMap();
				parame.put("name",(Object) listData.get(i).get("secondParame"));
				parame.put("value",(Long) listData.get(i).get("firstParame"));
				data.add(parame);
			}
		}
		return data;
	}
	
	/**
	 * 
	  * @Title: getFunnel
	  * @Description: 查询图表统计列表数据(漏斗图 )
	  * @return  Map<String, Object> 返回类型
	  * @param String columnName 列名
	  * @throws
	 */
	@Override
	public List<Map<String, Object>> getFunnel(String columnName) {
		List<Map<String, Object>> data = Lists.newArrayList();
		Map<String, Object> maps = Maps.newHashMap();
		//设置查询参数
		maps.put("columnName", columnName);
		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getFunnel(maps);
		}
		 //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				Map<String,Object>  parame= Maps.newHashMap();
				parame.put("name",(Object) listData.get(i).get("secondParame"));
				parame.put("value",(Long) listData.get(i).get("firstParame"));
				data.add(parame);
			}
		}
		return data;
	}

	/**
	 * 
	  * @Title: getRectangle
	  * @Description: 查询图表统计列表数据(矩形树图 )
	  * @return  Map<String, Object> 返回类型
	  * @param String columnName 列名
	  * @throws
	 */
	@Override
	public List<Map<String, Object>> getRectangle(String columnName) {
		List<Map<String, Object>> data = Lists.newArrayList();
		Map<String, Object> maps = Maps.newHashMap();
		//设置查询参数
		maps.put("columnName", columnName);
		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getRectangle(maps);
		}
		 //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				Map<String,Object>  parame= Maps.newHashMap();
				parame.put("name",(Object) listData.get(i).get("secondParame"));
				parame.put("value",(Long) listData.get(i).get("firstParame"));
				data.add(parame);
			}
		}
		return data;
	}
	
		
	/**
	 * 
	  * @Title: getChord
	  * @Description: 查询图表统计列表数据(和弦图 )
	  * @return  Map<String, Object> 返回类型
	  * @param String columnName 列名
	  * @throws
	 */
	@Override
	public List<List<Object>> getChord(String columnName) {
		List<List<Object>> data = Lists.newArrayList();
		Map<String, Object> maps = Maps.newHashMap();
		//把数据从大到小分成4段
		Long count = echartsMapper.getCount();
		//设置查询参数
		maps.put("columnNameA", columnName);
		maps.put("columnNameB", count);
		maps.put("columnNameC", count*2);
		maps.put("columnNameD", count*3);

		// 图表数据
		List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getChord(maps);
		}
		 //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				List<Object>  parame= Lists.newArrayList();
				parame.add(listData.get(i).get("firstParame"));
				parame.add(listData.get(i).get("thirdParame"));
				parame.add(listData.get(i).get("forthParame"));
				parame.add(listData.get(i).get("fifthParame"));
				data.add(parame);
			}
		}
		return data;
	}
	
	
	/**
	 * 
	  * @Title: getMix
	  * @Description: 查询图表统计列表数据(混搭图 )
	  * @return  Map<String, Object> 返回类型
	  * @param String columnName 列名
	  * @throws
	 */
	@Override
	public List<Map<String, Object>> getMix(String columnName) {
		List<Map<String, Object>> data = Lists.newArrayList();
		Map<String, Object> maps = Maps.newHashMap();
		//设置查询参数
		maps.put("columnNameA", columnName);
		maps.put("columnNameB", "ca");

		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getMix(maps);
		}
		 //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				Map<String,Object>  parame= Maps.newHashMap();
				parame.put("max",(Object) listData.get(i).get("thirdParame"));
				parame.put("min",(Object) listData.get(i).get("forthParame"));
				parame.put("avg",(Object) listData.get(i).get("fifthParame"));
				parame.put("param",(Object) listData.get(i).get("firstParame"));
				data.add(parame);
			}
		}
		return data;
	}
	
	/**
	 * 
	  * @Title: getForce
	  * @Description: 查询图表统计列表数据(力导向布局图 )
	  * @return  Map<String, Object> 返回类型
	  * @param String columnName 列名
	  * @throws
	 */
	@Override
	public List<List<Object>> getForce(String columnName) {
		List<List<Object>> data = Lists.newArrayList();
		Map<String, Object> maps = Maps.newHashMap();
		Long count = echartsMapper.getCount();
		//设置查询参数
		maps.put("columnNameA", columnName);
		maps.put("columnNameB", count);
		maps.put("columnNameC", count*2);
		maps.put("columnNameD", count*3);

		// 图表数据
		List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getForce(maps);
		}
		 //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				List<Object>  parame= Lists.newArrayList();
				parame.add(listData.get(i).get("firstParame"));
				parame.add(listData.get(i).get("thirdParame"));
				parame.add(listData.get(i).get("forthParame"));
				parame.add(listData.get(i).get("fifthParame"));
				data.add(parame);
			}
		}
		return data;
	}
	
	/**
	 * 
	  * @Title: getRadar
	  * @Description: 查询图表统计列表数据(雷达图)
	  * @return  Map<String, Object> 返回类型
	  * @param String columnName 列名
	  * @throws
	 */
	@Override
	public List<Map<String, Object>> getRadar(String columnName) {
		List<Map<String, Object>> data = Lists.newArrayList();
		Map<String, Object> maps = Maps.newHashMap();
		//设置查询参数
		maps.put("columnName", columnName);

		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getRadar(maps);
		}
		 //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				Map<String,Object>  parame= Maps.newHashMap();
				parame.put("max",(Object) listData.get(i).get("thirdParame"));
				parame.put("min",(Object) listData.get(i).get("forthParame"));
				parame.put("avg",(Object) listData.get(i).get("fifthParame"));
				parame.put("count",(Object) listData.get(i).get("firstParame"));
				parame.put("sum",(Object) listData.get(i).get("secondParame"));
				data.add(parame);
			}
		}
		return data;
	}

	/**
	 * 
	  * @Title: getGauge
	  * @Description: 查询图表统计列表数据(仪表盘图)
	  * @return  Map<String, Object> 返回类型
	  * @param String columnName 列名
	  * @throws
	 */
	@Override
	public List<Map<String, Object>>  getGauge(String columnName) {
		List<Map<String, Object>> data = Lists.newArrayList();
		Map<String, Object> maps = Maps.newHashMap();
		//设置查询参数
		maps.put("columnName", columnName);

		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getGauge(maps);
		}
		 //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				Map<String,Object>  parame= new LinkedHashMap<String,Object>();
				parame.put("value",(Object) listData.get(i).get("firstParame"));
				parame.put("name","平均值占有率");
				data.add(parame);
			}
		}
		return data;
	}

	/**
	 * @Title: getVenn 
	 * @Description: 查询图表统计列表数据(韦恩图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @param String columnName 列名
	 * @throws
	 */
	@Override
	public List<Map<String, Object>> getVenn(String columnName) {
		List<Map<String, Object>> data = Lists.newArrayList();
		Map<String, Object> maps = Maps.newHashMap();
		//设置查询参数
		maps.put("columnName", columnName);

		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getVenn(maps);
		}
		 //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				Map<String,Object>  parame= new LinkedHashMap<String,Object>();
				parame.put("maxValue",(Object) listData.get(i).get("firstParame"));
				parame.put("minValue",(Object) listData.get(i).get("secondParame"));
				parame.put("avgValue",(Object) listData.get(i).get("thirdParame"));
				data.add(parame);
			}
		}
		return data;
	}

	
	/**
	 * @Title: getHeatmap 
	 * @Description: 查询图表统计列表数据(热力图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @param String columnName 列名
	 * @throws
	 */
	@Override
	public List<Map<String, Object>> getHeatmap(String columnName) {
		List<Map<String, Object>> data = Lists.newArrayList();
		Map<String, Object> maps = Maps.newHashMap();
		//设置查询参数
		maps.put("columnName", columnName);

		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getHeatmap(maps);
		}
		 //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				Map<String,Object>  parame= new LinkedHashMap<String,Object>();
				parame.put("minValue",(Object) listData.get(i).get("firstParame"));
				parame.put("maxValue",(Object) listData.get(i).get("secondParame"));
				parame.put("countValue",(Object) listData.get(i).get("thirdParame"));
				data.add(parame);
			}
		}
		return data;
	}
	
	
	/**
	 * @Title: getWordCloud 
	 * @Description: 查询图表统计列表数据(字符云图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @param String columnName 列名
	 * @throws
	 */
	public List<Map<String, Object>> getWordCloud(String columnName){
		List<Map<String, Object>> data = Lists.newArrayList();
		Map<String, Object> maps = Maps.newHashMap();
		//列名
		maps.put("columnName", columnName);

		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getWordCloud(maps);
		}
		 //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				Map<String,Object>  parame= new LinkedHashMap<String,Object>();
				parame.put("minValue",(Object) listData.get(i).get("minParame"));
				parame.put("maxValue",(Object) listData.get(i).get("maxParame"));
				parame.put("countValue",(Object) listData.get(i).get("countParame"));
				parame.put("bigAndValue",(Object) listData.get(i).get("bigAndParame"));
				parame.put("bigOrValue",(Object) listData.get(i).get("bigOrParame"));
				parame.put("bigXorValue",(Object) listData.get(i).get("bigXorParame"));
				parame.put("stdPopValue",(Object) listData.get(i).get("stdPopParame"));
				parame.put("stdDampValue",(Object) listData.get(i).get("stdDampParame"));
				parame.put("varSampValue",(Object) listData.get(i).get("varSampParame"));
				parame.put("varPopValue",(Object) listData.get(i).get("varPopParame"));
				parame.put("avgValue",(Object) listData.get(i).get("avgParame"));
				parame.put("sumValue",(Object) listData.get(i).get("sumParame"));
	
				data.add(parame);
			}
		}
		return data;
	
	}

	/**
	 * @Title: getTree 
	 * @Description: 查询图表统计列表数据(树图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @param String columnName 列名
	 * @throws
	 */
	@Override
	public List<Map<String, Object>> getTree(String columnName) {
		List<Map<String, Object>> data = Lists.newArrayList();
		Map<String, Object> maps = Maps.newHashMap();
		
		maps.put("columnNameA", columnName);
		maps.put("columnNameB", "type");
		maps.put("columnNameC", "parent_id");
		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getTree(maps);
		}
		Map<String,Object>  parames1= new LinkedHashMap<String,Object>();
		parames1.put("name", columnName);
		List<Map<String, Object>> list1=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list2=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list3=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list4=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list5=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list6=new ArrayList<Map<String, Object>>();

		Map<String,Object>  parame1 =null;
		Map<String,Object>  parame2 =null;
		Map<String,Object>  parame3 =null;
		Map<String,Object>  parame4 =null;
		Map<String,Object>  parame5 =null;
		Map<String,Object>  parame6 =null;
		Map<String,Object>  parame7 =null;
		Map<String,Object>  parame8 =null;
		Map<String,Object>  parame9 =null;
		Map<String,Object>  parame10 =null;

		 //给集合赋值
			if(listData.size()>0){
				for(int i=0;i<listData.size();i++){ 
					Object	objParamB = (Object) listData.get(i).get("secondParame");
					Object	objParamC = (Object) listData.get(i).get("thirdParame");
					Object objParamA = (Object) listData.get(i).get("firstParame");
					if(objParamC == null && objParamB.toString().equals("1")){
						parame1= new LinkedHashMap<String,Object>();
						parame1.put("name", objParamA);
						list1.add(parame1);
					}else if(objParamC != null && objParamC.toString().equals("1") && objParamB.toString().equals("1")){
						parame2= new LinkedHashMap<String,Object>();
						parame2.put("name", objParamA);
						list2.add(parame2);
					}
					if(objParamC == null && objParamB.toString().equals("2")){
						parame3= new LinkedHashMap<String,Object>();
						parame3.put("name", objParamA);
						list1.add(parame3);
					}else if(objParamC != null && objParamC.toString().equals("4") && objParamB.toString().equals("2")){
						parame4= new LinkedHashMap<String,Object>();
						parame4.put("name", objParamA);
						list3.add(parame4);
					}
					if(objParamC == null && objParamB.toString().equals("3")){
						parame5= new LinkedHashMap<String,Object>();
						parame5.put("name", objParamA);
						list1.add(parame5);
					}else if(objParamC != null && objParamC.toString().equals("19") && objParamB.toString().equals("3")){
						parame6= new LinkedHashMap<String,Object>();
						parame6.put("name", objParamA);
						list4.add(parame6);
					}
					if(objParamC == null && objParamB.toString().equals("4")){
						parame7= new LinkedHashMap<String,Object>();
						parame7.put("name", objParamA);
						list1.add(parame7);
					}else if(objParamC != null && objParamC.toString().equals("31") && objParamB.toString().equals("4")){
						parame8= new LinkedHashMap<String,Object>();
						parame8.put("name", objParamA);
						list5.add(parame8);
					}
					if(objParamC == null && objParamB.toString().equals("5")){
						parame9= new LinkedHashMap<String,Object>();
						parame9.put("name", objParamA);
						list1.add(parame9);
					}else if(objParamC != null && objParamC.toString().equals("49") && objParamB.toString().equals("5")){
						parame10= new LinkedHashMap<String,Object>();
						parame10.put("name", objParamA);
						list6.add(parame10);
					}
					
				}
				parame1.put("children", list2);
				parame3.put("children", list3);
				parame5.put("children", list4);
				parame7.put("children", list5);
				parame9.put("children", list6);
				parames1.put("children", list1);
				data.add(parames1);
			}	
		return data;
	}

	
	/**
	 * @Title: getMap 
	 * @Description: 查询图表统计列表数据(地图 ) 
	 * @return List<Map<String, Object>> 返回类型 
	 * @param String columnName 列名
	 * @throws
	 */
	@Override
	public Map<String, List<Map<String, Object>>> getMap(String columnName) {
		Map<String, List<Map<String, Object>>> datas = Maps.newHashMap();
		List<Map<String, Object>> dataCount = Lists.newArrayList();
		List<Map<String, Object>> dataMax = Lists.newArrayList();
		List<Map<String, Object>> dataMin = Lists.newArrayList();
		List<Map<String, Object>> dataAvg = Lists.newArrayList();

		Map<String, Object> maps = Maps.newHashMap();
		//设置查询参数
		maps.put("columnNameA", columnName);
		maps.put("columnNameB", "city_data");
		// 图表数据
		List<Map<String, Object>> listData=new ArrayList<Map<String, Object>>();
		if(columnName != null){		
			// 图表数据
			listData = echartsMapper.getMap(maps);
		}
		
		 //给集合赋值
		if(listData.size()>0){
			for(int i=0;i<listData.size();i++){ 
				Map<String,Object>  parameCount = new LinkedHashMap<String,Object>();
				Map<String,Object>  parameAvg = new LinkedHashMap<String,Object>();
				Map<String,Object>  parameMax = new LinkedHashMap<String,Object>();
				Map<String,Object>  parameMin = new LinkedHashMap<String,Object>();
				parameCount.put("name",(Object)listData.get(i).get("columnPram"));
				parameCount.put("value", (Object)listData.get(i).get("countPram"));
				parameAvg.put("name",(Object)listData.get(i).get("columnPram"));
				parameAvg.put("value", (Object)listData.get(i).get("avgPram"));
				parameMax.put("name",(Object)listData.get(i).get("columnPram"));
				parameMax.put("value", (Object)listData.get(i).get("maxPram"));
				parameMin.put("name",(Object)listData.get(i).get("columnPram"));
				parameMin.put("value", (Object)listData.get(i).get("minPram"));
				dataCount.add(parameCount);
				dataMax.add(parameMax);			
				dataMin.add(parameMin);
				dataAvg.add(parameAvg);
			}
		}
		datas.put("countP", dataCount);
		datas.put("avgP", dataAvg);
		datas.put("maxP", dataMax);
		datas.put("minP", dataMin);
		return datas;
	}
}
