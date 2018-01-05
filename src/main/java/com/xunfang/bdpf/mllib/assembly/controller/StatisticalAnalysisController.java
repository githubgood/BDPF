package com.xunfang.bdpf.mllib.assembly.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.Assembly;
import com.xunfang.bdpf.mllib.assembly.entity.StatisticalanalysisAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StatisticalanalysisAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.service.AssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.EchartsService;
import com.xunfang.bdpf.mllib.assembly.service.StatisticalanalysisAssemblyService;
import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
import com.xunfang.bdpf.mllib.experiment.service.DataSourceService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Identities;

/**
 * 
 * @ClassName StatisticalAnalysisController
 * @Description: 统计分析控制器 Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月9日 下午5:18:42
 * @version V1.0
 */
@Controller
@RequestMapping(value = "statisticalanalysis")
public class StatisticalAnalysisController {
	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(StatisticalAnalysisController.class);

	@Autowired
	private DataSourceService dataSourceService;

	@Autowired
	private StatisticalanalysisAssemblyService statisticalanalysisAssemblyService;

	@Autowired
	private EchartsService echartsService;
	
	@Autowired
	private AssemblyService assemblyService;

	/**
	 * 
	 * @Title: model_32_list 
	 * @Description: 至折线（面积）图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_32_list")
	@ResponseBody
	public Map<String, Object> model_32_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try{
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_32_save 
	 * @Description: 至折线（面积）图列表数据保存 
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_32_save")
	@ResponseBody
	public Map<String, Object> model_32_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}

	
	/**
	 * 
	 * @Title: model_33_list 
	 * @Description: 散点(气泡)图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_33_list")
	@ResponseBody
	public Map<String, Object> model_33_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_33_save 
	 * @Description: 散点（气泡）图列表数据保存 
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_33_save")
	@ResponseBody
	public Map<String, Object> model_33_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}

	/**
	 * 
	 * @Title: model_34_list 
	 * @Description: K线图列表 
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_34_list")
	@ResponseBody
	public Map<String, Object> model_34_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}


	/**
	 * 
	 * @Title: model_34_save 
	 * @Description: K线图列表数据保存
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_34_save")
	@ResponseBody
	public Map<String, Object> model_34_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}


	/**
	 * 
	 * @Title: model_35_list 
	 * @Description: 饼（圆环）图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_35_list")
	@ResponseBody
	public Map<String, Object> model_35_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}


	/**
	 * 
	 * @Title: model_35_save 
	 * @Description: 饼（圆环）图列表数据保存
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_35_save")
	@ResponseBody
	public Map<String, Object> model_35_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}
	/**
	 * 
	 * @Title: model_36_list 
	 * @Description: 雷达（面积）图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_36_list")
	@ResponseBody
	public Map<String, Object> model_36_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_36_save 
	 * @Description: 雷达（面积）图列表数据保存
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_36_save")
	@ResponseBody
	public Map<String, Object> model_36_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}

	/**
	 * 
	 * @Title: model_37_list 
	 * @Description:  和弦图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_37_list")
	@ResponseBody
	public Map<String, Object> model_37_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_37_save 
	 * @Description: 和弦图列表数据保存
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_37_save")
	@ResponseBody
	public Map<String, Object> model_37_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}

	/**
	 * 
	 * @Title: model_38_list 
	 * @Description:  力导向布局图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_38_list")
	@ResponseBody
	public Map<String, Object> model_38_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_38_save 
	 * @Description: 力导向布局图列表数据保存
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_38_save")
	@ResponseBody
	public Map<String, Object> model_38_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}

	/**
	 * 
	 * @Title: model_39_list 
	 * @Description:  图柱状(条形)图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_39_list")
	@ResponseBody
	public Map<String, Object> model_39_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_39_save 
	 * @Description: 图柱状(条形)图列表数据保存
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_39_save")
	@ResponseBody
	public Map<String, Object> model_39_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}

	/**
	 * 
	 * @Title: model_40_list 
	 * @Description:  仪表盘图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_40_list")
	@ResponseBody
	public Map<String, Object> model_40_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_40_save 
	 * @Description: 仪表盘图列表数据保存 
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_40_save")
	@ResponseBody
	public Map<String, Object> model_40_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}


	/**
	 * 
	 * @Title: model_41_list 
	 * @Description:  漏斗图列表列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_41_list")
	@ResponseBody
	public Map<String, Object> model_41_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}


	/**
	 * 
	 * @Title: model_41_save 
	 * @Description: 漏斗图列表数据保存
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_41_save")
	@ResponseBody
	public Map<String, Object> model_41_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}

	/**
	 * 
	 * @Title: model_42_list 
	 * @Description:  热力图列表列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_42_list")
	@ResponseBody
	public Map<String, Object> model_42_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_42_save 
	 * @Description: 热力图列表数据保存
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_42_save")
	@ResponseBody
	public Map<String, Object> model_42_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}
	
	/**
	 * 
	 * @Title: model_43_list 
	 * @Description: 地图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_43_list")
	@ResponseBody
	public Map<String, Object> model_43_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_43_save 
	 * @Description: 地图列表数据保存 
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_43_save")
	@ResponseBody
	public Map<String, Object> model_43_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}

	
	/**
	 * 
	 * @Title: model_44_list 
	 * @Description:  韦恩图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_44_list")
	@ResponseBody
	public Map<String, Object> model_44_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}


	/**
	 * 
	 * @Title: model_44_save
	 * @Description: 韦恩图列表数据保存 
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_44_save")
	@ResponseBody
	public Map<String, Object> model_44_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}
	/**
	 * 
	 * @Title: model_45_list 
	 * @Description:  矩形树图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_45_list")
	@ResponseBody
	public Map<String, Object> model_45_list(String expid, String id, String name, HttpSession session) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 获取当前登录用户信息
			User user = (User) session.getAttribute("loginuser");
			if (user == null) {
				return map;
			}

			try {//获取当前组件信息
				Assembly assembly = assemblyService.selectByPrimaryKey(id);
				if(assembly == null){
					return map;
				}
				//获取父组件选中字段信息
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
				
				//获取父组件信息
				Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
				//如果父组件是增加序列号，这里默认增加一列
				if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
					//如果有增加序列号信息。则在原来的基础上累加
					AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
					if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
						if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
							TableChild tableChild = new TableChild();
							tableChild.setName(addSerialNumAssembly.getSerialNum());
							tableChild.setType("Int");
							tableChilds.add(tableChild);
						}
					}
				}
				map.put("tableChilds", tableChilds);
				
				// 已选字段
				List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
				map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
				
				//遍历当前选中的字段信息
				List<TableChild> tableChildList = new ArrayList<TableChild>();
				if(statisticalanalysisAssemblys!=null){
					for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
						TableChild tableChild = new TableChild();
						tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
						tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
						tableChildList.add(tableChild);
					}
				}
				
				//将当前选中的字段存到缓存中
				session.removeAttribute("table"+id);
				session.setAttribute("table"+id, tableChildList);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
			}
			return map;
		}

	

	/**
	 * 
	 * @Title: model_45_save
	 * @Description: 矩形树图列表数据保存 
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_45_save")
	@ResponseBody
	public Map<String, Object> model_45_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}

	/**
	 * 
	 * @Title: model_46_list 
	 * @Description: 树图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_46_list")
	@ResponseBody
	public Map<String, Object> model_46_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_46_save 
	 * @Description: 树图列表数据保存 
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_46_save")
	@ResponseBody
	public Map<String, Object> model_46_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}

	/**
	 * 
	 * @Title: model_47_list 
	 * @Description:  混搭图列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_47_list")
	@ResponseBody
	public Map<String, Object> model_47_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}


	/**
	 * 
	 * @Title: model_47_save
	 * @Description: 混搭图列表数据保存 
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_47_save")
	@ResponseBody
	public Map<String, Object> model_47_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}
	/**
	 * 
	 * @Title: model_48_list 
	 * @Description:  字符云图
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_48_list")
	@ResponseBody
	public Map<String, Object> model_48_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}

		try {//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			//获取父组件选中字段信息
			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
			
			//获取父组件信息
			Assembly assemblyParent = assemblyService.selectByPrimaryKey(assembly.getParentId());
			//如果父组件是增加序列号，这里默认增加一列
			if(assemblyParent!=null&&"12".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				//如果有增加序列号信息。则在原来的基础上累加
				AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
				if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
					if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
						TableChild tableChild = new TableChild();
						tableChild.setName(addSerialNumAssembly.getSerialNum());
						tableChild.setType("Int");
						tableChilds.add(tableChild);
					}
				}
			}
			map.put("tableChilds", tableChilds);
			
			// 已选字段
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = statisticalanalysisAssemblyService.queryStatisticalanalysisAssembly(id);
			map.put("statisticalanalysisAssemblys", statisticalanalysisAssemblys);
			
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(statisticalanalysisAssemblys!=null){
				for (StatisticalanalysisAssembly statisticalanalysisAssembly : statisticalanalysisAssemblys) {
					TableChild tableChild = new TableChild();
					tableChild.setName(statisticalanalysisAssembly.getCharacteristicColumn());
					tableChild.setType(statisticalanalysisAssembly.getCharacteristicType());
					tableChildList.add(tableChild);
				}
			}
			
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_48_save
	 * @Description: 字符云图列表数据保存
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_48_save")
	@ResponseBody
	public Map<String, Object> model_48_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 删除
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id)
				.andCharacteristicTypeEqualTo(convertType);
		statisticalanalysisAssemblyService.deleteByExample(statisticalanalysisAssemblyExample);

		List<StatisticalanalysisAssembly> statisticalanalysisAssemblys = new ArrayList<StatisticalanalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			StatisticalanalysisAssembly statisticalanalysisAssembly = new StatisticalanalysisAssembly();
			statisticalanalysisAssembly.setId(Identities.uuid2());
			statisticalanalysisAssembly.setBdpfMllibAssemblyId(id);
			statisticalanalysisAssembly.setCharacteristicColumn(conn[i].split(";")[0]);
			statisticalanalysisAssembly.setAssemblyLibraryId(conn[i].split(";")[1]);
			statisticalanalysisAssembly.setCharacteristicType(convertType);
			statisticalanalysisAssemblys.add(statisticalanalysisAssembly);
		}

		int result = statisticalanalysisAssemblyService
				.batchInsertStatisticalanalysisAssembly(statisticalanalysisAssemblys);

		map.put("result", result);
		map.put("id", id);

		return map;
	}
	/**
	 * 
	 * @Title: getLine 
	 * @Description: 查询图表统计列表数据(至折线（面积图）) 
	 * @param columnName String 列名 
	 * @return Map<String, Object> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "line")
	public Map<String, Object> getLine(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		if(null == resultList){
			return data;
		}
		
		List<Object> obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		
		//去重
		List objCount = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					objCount.add(str.get(columnName));
				}
			}	
		}
		//排序
		Collections.sort(objCount);
		
		for (int  i = 0 ; i < objCount.size() ; i++) {
			for(int  j  =  objCount.size() -1; j  >  i; j-- ){
				if(objCount.get(j).equals(objCount.get(i)))  {       
					objCount.remove(j);       
				}  
			}
		}
		
		//求个数
		List<Object> ints = new ArrayList<Object>();					
		if(objCount != null && objCount.size()>0 && obj != null && obj.size()>0){
			for (int j = 0; j < objCount.size(); j++) {
				int count = Collections.frequency(obj, objCount.get(j));
				ints.add(count);
			}						
		}	
		
		//拼接页面需要的格式
		Map<String,Object> maps = new LinkedHashMap<String,Object>();
		maps.put("secondParame", objCount);
		maps.put("firstParame", ints);
		
		return maps;
	}


	/**
	 * 
	 * @Title: getLines 
	 * @Description: 查询图表统计列表数据(至折线（面积图）) 
	 * @param columnArray  列名的数组
	 * @return List<Map<String, Object>> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "getLines")
	public List<LinkedHashMap<String, Object>> getLines(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<LinkedHashMap<String,List<Object>>> countDatas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<LinkedHashMap<String,List<Object>>> countDatass = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<LinkedHashMap<String, Object>> barsParams= new ArrayList<LinkedHashMap<String, Object>>();

		
		if(null == resultList){
			return barsParams;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			List<Object> obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			lists.put(columnName,obj);
			datas.add(lists);
		}
		
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			//排序
			Collections.sort(obj);
			lists.put(columnName,obj);
			datas.add(lists);
			countDatas.add(lists);
		}
				
		
		//去重复
		for (String columnName : columnArray) {
			for (LinkedHashMap<String,List<Object>> str : countDatas) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					List<Object> objs = str.get(columnName);
					for (int  i = 0 ; i < objs.size() ; i++) {
						for(int  j  =  objs.size() -1; j  >  i; j-- ){
							if(objs.get(j).equals(objs.get(i)))  {       
								objs.remove(j);       
					        }  
						}
					}
				}
			}	
		}
		
		//遍历每个列里面去重后的数据出现的次数
		for (String columnName : columnArray) {	
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			for (int  i = 0 ; i < countDatas.size() ; i++) {
				List<Object> objs = new ArrayList<Object>();
				objs = countDatas.get(i).get(columnName);				
				List<Object> objs2 = new ArrayList<Object>();
				objs2 = datas.get(i).get(columnName);
				if(objs != null && objs.size()>0 && objs2 != null && objs2.size()>0){
					List<Object> ints = new ArrayList<Object>();					
					for (int j = 0; j < objs.size(); j++) {
						int count = Collections.frequency(objs2, objs.get(j));
						ints.add(count);
					}						
					lists.put(columnName+"Count", ints);															
				}				
			}		
			countDatass.add(lists);
		}	
		
		//拼接格式
		for (String columnName : columnArray) {
			for (int  i = 0 ; i < countDatas.size() ; i++) {
				List<Object> objs = new ArrayList<Object>();
				objs = countDatas.get(i).get(columnName);	
				if(objs != null && objs.size()>0){
					for (int  j = 0 ; j < countDatass.size() ; j++) {
						List<Object> objs2 = new ArrayList<Object>();
						objs2 = countDatass.get(j).get(columnName+"Count");
						if(objs2 != null && objs2.size()>0){
							Map<String,Object> maps = new LinkedHashMap<String,Object>();
							maps.put("secondParame", objs);
							maps.put("firstParame", objs2);
							barsParams.add((LinkedHashMap<String, Object>) maps);
						}
					}
				}				
			}
		}	
		return barsParams;
	}

	/**
	 * 
	 * @Title: queryColumn 
	 * @Description: 根据id查询出数据库存放的字段名称
	 * @param String id 
	 * @return List<StatisticalanalysisAssembly> 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "queryColumn")
	public List<StatisticalanalysisAssembly> queryColumn(String id,String sid,HttpSession session) {
		//通过表名和列查询对应的结果集
		StatisticalanalysisAssemblyExample statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
		statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
		List<StatisticalanalysisAssembly> stLists = statisticalanalysisAssemblyService.selectByExample(statisticalanalysisAssemblyExample);
		return stLists;
	}

	/**
	 * 
	 * @Title: getScatter 
	 * @Description: 查询图表统计列表数据(散点图) 
	 * @param columnName String 列名 
	 * @return List<List<Object>> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "scatter")
	public List<List<Object>> getScatter(String columnName,String id,HttpSession session) {
			// 图表数据
			List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
			List<List<Object>> data = new ArrayList<List<Object>>();
			if(null == resultList){
				return data;
			}
			
			List<Object> obj = new ArrayList<Object>();
			if(null != columnName){
				for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
					if(str.get(columnName)!=null){//匹配选择的字段
						obj.add(str.get(columnName));
					}
				}	
			}
			
			//去重
			List objCount = new ArrayList<Object>();
			if(null != columnName){
				for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
					if(str.get(columnName)!=null){//匹配选择的字段
						objCount.add(str.get(columnName));
					}
				}	
			}
			//排序
			Collections.sort(objCount);
			
			for (int  i = 0 ; i < objCount.size() ; i++) {
				for(int  j  =  objCount.size() -1; j  >  i; j-- ){
					if(objCount.get(j).equals(objCount.get(i)))  {       
						objCount.remove(j);       
					}  
				}
			}
			
			//求个数
			if(objCount != null && objCount.size()>0 && obj != null && obj.size()>0){
				for (int j = 0; j < objCount.size(); j++) {
					List<Object> ints = new ArrayList<Object>();					
					int count = Collections.frequency(obj, objCount.get(j));
					ints.add(objCount.get(j));
					ints.add(count);
					data.add(ints);
				}						
			}
			
			
			
			return data;	
	}

	/**
	 * 
	 * @Title: getScatters 
	 * @Description: 查询图表统计列表数据(散点图) 
	 * @param columnArray  列名的数组
	 * @return List<List<List<Object>>> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "getScatters")
	public List<List<List<Object>>> getScatters(String[] columnArray,String id,HttpSession session) {
			// 图表数据
			List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
			List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
			List<LinkedHashMap<String,List<Object>>> countDatas = new ArrayList<LinkedHashMap<String,List<Object>>>();
			List<LinkedHashMap<String,List<Object>>> countDatass = new ArrayList<LinkedHashMap<String,List<Object>>>();
			List<List<List<Object>>> barsParams= new ArrayList<List<List<Object>>>();

			
			if(null == resultList){
				return barsParams;
			}
			//获取所有的每列的数据，组成一个list集合
			for (String columnName : columnArray) {//遍历选择的字段
				LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
				List<Object> obj = new ArrayList<Object>();
				for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
					if(str.get(columnName)!=null){//匹配选择的字段
						obj.add(str.get(columnName));
					}
				}	
				lists.put(columnName,obj);
				datas.add(lists);
			}
			
			//获取所有的每列的数据，组成一个list集合
			for (String columnName : columnArray) {//遍历选择的字段
				LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
				List obj = new ArrayList<Object>();
				for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
					if(str.get(columnName)!=null){//匹配选择的字段
						obj.add(str.get(columnName));
					}
				}	
				//排序
				Collections.sort(obj);
				lists.put(columnName,obj);
				datas.add(lists);
				countDatas.add(lists);
			}
								
			//去重复
			for (String columnName : columnArray) {
				for (LinkedHashMap<String,List<Object>> str : countDatas) {//遍历数据集
					if(str.get(columnName)!=null){//匹配选择的字段
						List<Object> objs = str.get(columnName);
						for (int  i = 0 ; i < objs.size() ; i++) {
							for(int  j  =  objs.size() -1; j  >  i; j-- ){
								if(objs.get(j).equals(objs.get(i)))  {       
									objs.remove(j);       
						        }  
							}
						}
					}
				}	
			}
			
			//遍历每个列里面去重后的数据出现的次数
			for (String columnName : columnArray) {	
				List<List<Object>> lists= new ArrayList<List<Object>>();
				for (int  i = 0 ; i < countDatas.size() ; i++) {
					List<Object> objs = new ArrayList<Object>();
					objs = countDatas.get(i).get(columnName);				
					List<Object> objs2 = new ArrayList<Object>();
					objs2 = datas.get(i).get(columnName);
					if(objs != null && objs.size()>0 && objs2 != null && objs2.size()>0){
						for (int j = 0; j < objs.size(); j++) {
							List<Object> ints = new ArrayList<Object>();					
							int count = Collections.frequency(objs2, objs.get(j));
							ints.add(objs.get(j));
							ints.add(count);
							lists.add(ints);
						}						
																					
					}				
				}		
				barsParams.add(lists);
			}	
			return barsParams;
		}

	/**
	 * 
	 * @Title: getKLine 
	 * @Description: 查询图表统计列表数据(K线图) 
	 * @param columnName String 列名 
	 * @return List<List<Object>> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "kline")
	public List<List<Object>> getKLine(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<List<Object>> data = new ArrayList<List<Object>>();
		if(null == resultList){
			return data;
		}
		
		List<Object> obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}	
		if(null != obj && obj.size()>0){
			int sizes =obj.size();
			int cou =sizes % 10;
			int cou1 = (sizes-cou)/10;
			if(obj.size()<10){
				List list1= new ArrayList<Object>();
				List list2= new ArrayList<Object>();
				double sum = 0;
				for (int j = 0; j < obj.size(); j++) {
					double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
					sum += num;
					list1.add(obj.get(j));
				}
				list2.add(sum);
				list2.add(Collections.max(list1));
				list2.add(Collections.min(list1));
				list2.add(Math.ceil(sum/obj.size()));
				data.add(list2);
			}else{	
				if(cou == 0){
					for (int i = 0; i < cou1; i++) {
						List list1= new ArrayList<Object>();
						List list2= new ArrayList<Object>();
						double sum = 0;
						for (int j = 10*i; j < 10*(i+1); j++) {
							double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
							sum += num;
							list1.add(obj.get(j));
						}
						list2.add(sum);
						list2.add(Collections.max(list1));
						list2.add(Collections.min(list1));
						list2.add(Math.ceil(sum/10));
						data.add(list2);
					}
				}else{
					for (int i = 0; i < cou1; i++) {
						List list1= new ArrayList<Object>();
						List list2= new ArrayList<Object>();
						double sum = 0;
						for (int j = 10*i; j < 10*(i+1); j++) {
							double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
							sum += num;
							list1.add(obj.get(j));
						}
						list2.add(sum);
						list2.add(Collections.max(list1));
						list2.add(Collections.min(list1));
						list2.add(Math.ceil(sum/10));
						data.add(list2);
					}
					
					List list1= new ArrayList<Object>();
					List list2= new ArrayList<Object>();
					double sum = 0;
					for (int j = cou1*10; j < cou1*10+cou; j++) {
						double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
						sum += num;
						list1.add(obj.get(j));
					}
					list2.add(sum);
					list2.add(Collections.max(list1));
					list2.add(Collections.min(list1));
					list2.add(Math.ceil(sum/cou));
					data.add(list2);
					
				}		
			}	
		}
		return data;
	}

	/**
	 * 
	 * @Title: getKLines 
	 * @Description: 查询图表统计列表数据(K线图) 
	 * @param columnArray  列名的数组
	 * @return List<List<List<Object>>>  返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "klines")
	public List<List<List<List<Object>>>> getKLines(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<List<List<Object>>> data = new ArrayList<List<List<Object>>>();
		List<List<List<List<Object>>>> datas = new ArrayList<List<List<List<Object>>>>();

		List<List<Object>> datas2 = new ArrayList<List<Object>>();

		if(null == resultList){
			return datas;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			datas2.add(obj);			
		}
		if(null != datas2 && datas2.size()>0){
			for (List<Object> obj : datas2) {
				if(null != obj && obj.size()>0){
					int sizes =obj.size();
					int cou =sizes % 10;
					int cou1 = (sizes-cou)/10;
					if(obj.size()<10){
						List list1= new ArrayList<Object>();
						List list2= new ArrayList<Object>();
						double sum = 0;
						for (int j = 0; j < obj.size(); j++) {
							double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
							sum += num;
							list1.add(obj.get(j));
						}
						list2.add(sum);
						list2.add(Collections.max(list1));
						list2.add(Collections.min(list1));
						list2.add(Math.ceil(sum/obj.size()));
						data.add(list2);
					}else{	
						if(cou == 0){
							for (int i = 0; i < cou1; i++) {
								List list1= new ArrayList<Object>();
								List list2= new ArrayList<Object>();
								double sum = 0;
								for (int j = 10*i; j < 10*(i+1); j++) {
									double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
									sum += num;
									list1.add(obj.get(j));
								}
								list2.add(sum);
								list2.add(Collections.max(list1));
								list2.add(Collections.min(list1));
								list2.add(Math.ceil(sum/10));
								data.add(list2);
							}
						}else{
							for (int i = 0; i < cou1; i++) {
								List list1= new ArrayList<Object>();
								List list2= new ArrayList<Object>();
								double sum = 0;
								for (int j = 10*i; j < 10*(i+1); j++) {
									double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
									sum += num;
									list1.add(obj.get(j));
								}
								list2.add(sum);
								list2.add(Collections.max(list1));
								list2.add(Collections.min(list1));
								list2.add(Math.ceil(sum/10));
								data.add(list2);
							}
							
							List list1= new ArrayList<Object>();
							List list2= new ArrayList<Object>();
							double sum = 0;
							for (int j = cou1*10; j < cou1*10+cou; j++) {
								double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
								sum += num;
								list1.add(obj.get(j));
							}
							list2.add(sum);
							list2.add(Collections.max(list1));
							list2.add(Collections.min(list1));
							list2.add(Math.ceil(sum/cou));
							data.add(list2);
							
						}		
					}	
				}
		}	
	}
	if(null != data && data.size()>0){
		int sizeParam =data.size()/datas2.size();
		for (int i = 0; i < datas2.size(); i++) {
			List<List<List<Object>>> listA = new ArrayList<List<List<Object>>>();
			for (int j = i*sizeParam; j < (i+1)*sizeParam; j++) {
				listA.add(data.get(j));
			}
			datas.add(listA);
		}
	}
	return datas;
	
}

	/**
	 * 
	 * @Title: getBar 
	 * @Description: 查询图表统计列表数据(柱状图) 
	 * @param columnName String 列名 
	 * @return Map<String, Object> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "bar")
	public Map<String, Object> getBar(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		if(null == resultList){
			return data;
		}
		
		List<Object> obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		
		//去重
		List objCount = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					objCount.add(str.get(columnName));
				}
			}	
		}
		//排序
		Collections.sort(objCount);
		
		for (int  i = 0 ; i < objCount.size() ; i++) {
			for(int  j  =  objCount.size() -1; j  >  i; j-- ){
				if(objCount.get(j).equals(objCount.get(i)))  {       
					objCount.remove(j);       
				}  
			}
		}
		
		//求个数
		List<Object> ints = new ArrayList<Object>();					
		if(objCount != null && objCount.size()>0 && obj != null && obj.size()>0){
			for (int j = 0; j < objCount.size(); j++) {
				int count = Collections.frequency(obj, objCount.get(j));
				ints.add(count);
			}						
		}	
		
		//拼接页面需要的格式
		Map<String,Object> maps = new LinkedHashMap<String,Object>();
		maps.put("secondParame", objCount);
		maps.put("firstParame", ints);
		
		return maps;
	}

	/**
	 * 
	 * @Title: getBars 
	 * @Description: 查询图表统计列表数据(柱状图) 
	 * @param columnArray  列名的数组
	 * @return  List<Map<String, Object>> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "getBars")
	public List<LinkedHashMap<String, Object>>  getBars(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<LinkedHashMap<String,List<Object>>> countDatas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<LinkedHashMap<String,List<Object>>> countDatass = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<LinkedHashMap<String, Object>> barsParams= new ArrayList<LinkedHashMap<String, Object>>();

		
		if(null == resultList){
			return barsParams;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			List<Object> obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			lists.put(columnName,obj);
			datas.add(lists);
		}
		
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			//排序
			Collections.sort(obj);
			lists.put(columnName,obj);
			datas.add(lists);
			countDatas.add(lists);
		}
				
		
		//去重复
		for (String columnName : columnArray) {
			for (LinkedHashMap<String,List<Object>> str : countDatas) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					List<Object> objs = str.get(columnName);
					for (int  i = 0 ; i < objs.size() ; i++) {
						for(int  j  =  objs.size() -1; j  >  i; j-- ){
							if(objs.get(j).equals(objs.get(i)))  {       
								objs.remove(j);       
					        }  
						}
					}
				}
			}	
		}
		
		//遍历每个列里面去重后的数据出现的次数
		for (String columnName : columnArray) {	
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			for (int  i = 0 ; i < countDatas.size() ; i++) {
				List<Object> objs = new ArrayList<Object>();
				objs = countDatas.get(i).get(columnName);				
				List<Object> objs2 = new ArrayList<Object>();
				objs2 = datas.get(i).get(columnName);
				if(objs != null && objs.size()>0 && objs2 != null && objs2.size()>0){
					List<Object> ints = new ArrayList<Object>();					
					for (int j = 0; j < objs.size(); j++) {
						int count = Collections.frequency(objs2, objs.get(j));
						ints.add(count);
					}						
					lists.put(columnName+"Count", ints);															
				}				
			}		
			countDatass.add(lists);
		}	
		
		//拼接格式
		for (String columnName : columnArray) {
			for (int  i = 0 ; i < countDatas.size() ; i++) {
				List<Object> objs = new ArrayList<Object>();
				objs = countDatas.get(i).get(columnName);	
				if(objs != null && objs.size()>0){
					for (int  j = 0 ; j < countDatass.size() ; j++) {
						List<Object> objs2 = new ArrayList<Object>();
						objs2 = countDatass.get(j).get(columnName+"Count");
						if(objs2 != null && objs2.size()>0){
							Map<String,Object> maps = new LinkedHashMap<String,Object>();
							maps.put("secondParame", objs);
							maps.put("firstParame", objs2);
							barsParams.add((LinkedHashMap<String, Object>) maps);
						}
					}
				}				
			}
		}	
		return barsParams;
	}


	/**
	 * 
	 * @Title: getPie 
	 * @Description: 查询图表统计列表数据(饼（圆环）图) 
	 * @param columnName String 列名 
	 * @return List<Map<String, Object>> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "pie")
	public List<Map<String, Object>> getPie(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if(null == resultList){
			return data;
		}
		
		List<Object> obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		
		//去重
		List objCount = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					objCount.add(str.get(columnName));
				}
			}	
		}
		//排序
		Collections.sort(objCount);
		
		for (int  i = 0 ; i < objCount.size() ; i++) {
			for(int  j  =  objCount.size() -1; j  >  i; j-- ){
				if(objCount.get(j).equals(objCount.get(i)))  {       
					objCount.remove(j);       
				}  
			}
		}
		
		//求个数
		if(objCount != null && objCount.size()>0 && obj != null && obj.size()>0){
			for (int j = 0; j < objCount.size(); j++) {
				Map<String, Object> ints = new LinkedHashMap<String, Object>();					
				int count = Collections.frequency(obj, objCount.get(j));
				ints.put("value",count);
				ints.put("name",objCount.get(j));
				data.add(ints);
			}						
		}	
		return data;	
}


	/**
	 * 
	 * @Title: getPies 
	 * @Description: 查询图表统计列表数据(饼（圆环）图) 
	 * @param columnArray  列名的数组
	 * @return List<List<Map<String, Object>>> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "pies")
	public List<List<Map<String, Object>>> getPies(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<LinkedHashMap<String,List<Object>>> countDatas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<LinkedHashMap<String,List<Object>>> countDatass = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<List<Map<String, Object>>> barsParams= new ArrayList<List<Map<String, Object>>>();

		
		if(null == resultList){
			return barsParams;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			List<Object> obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			lists.put(columnName,obj);
			datas.add(lists);
		}
		
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			//排序
			Collections.sort(obj);
			lists.put(columnName,obj);
			datas.add(lists);
			countDatas.add(lists);
		}
							
		//去重复
		for (String columnName : columnArray) {
			for (LinkedHashMap<String,List<Object>> str : countDatas) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					List<Object> objs = str.get(columnName);
					for (int  i = 0 ; i < objs.size() ; i++) {
						for(int  j  =  objs.size() -1; j  >  i; j-- ){
							if(objs.get(j).equals(objs.get(i)))  {       
								objs.remove(j);       
					        }  
						}
					}
				}
			}	
		}
		
		//遍历每个列里面去重后的数据出现的次数
		for (String columnName : columnArray) {	
			List<Map<String, Object>> lists= new ArrayList<Map<String, Object>>();
			for (int  i = 0 ; i < countDatas.size() ; i++) {
				List<Object> objs = new ArrayList<Object>();
				objs = countDatas.get(i).get(columnName);				
				List<Object> objs2 = new ArrayList<Object>();
				objs2 = datas.get(i).get(columnName);
				if(objs != null && objs.size()>0 && objs2 != null && objs2.size()>0){
					for (int j = 0; j < objs.size(); j++) {
						Map<String, Object> ints = new LinkedHashMap<String, Object>();					
						int count = Collections.frequency(objs2, objs.get(j));
						ints.put("value",count);
						ints.put("name",objs.get(j));
						lists.add(ints);
					}						
																				
				}				
			}		
			barsParams.add(lists);
		}	
		return barsParams;
	}

	/**
	 * 
	 * @Title: getFunnel 
	 * @Description: 查询图表统计列表数据(漏斗图 ) 
	 * @param columnName String 列名 
	 * @return Map<String, Object> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "funnel")
	public List<Map<String, Object>> getFunnel(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if(null == resultList){
			return data;
		}
		
		List<Object> obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		
		//去重
		List objCount = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					objCount.add(str.get(columnName));
				}
			}	
		}
		//排序
		Collections.sort(objCount);
		
		for (int  i = 0 ; i < objCount.size() ; i++) {
			for(int  j  =  objCount.size() -1; j  >  i; j-- ){
				if(objCount.get(j).equals(objCount.get(i)))  {       
					objCount.remove(j);       
				}  
			}
		}
		
		//求个数
		if(objCount != null && objCount.size()>0 && obj != null && obj.size()>0){
			for (int j = 0; j < objCount.size(); j++) {
				Map<String, Object> ints = new LinkedHashMap<String, Object>();					
				int count = Collections.frequency(obj, objCount.get(j));
				ints.put("value",count);
				ints.put("name",objCount.get(j));
				data.add(ints);
			}						
		}	
		return data;	
}

	/**
	 * 
	 * @Title: getFunnels 
	 * @Description: 查询图表统计列表数据(漏斗图 ) 
	 * @param columnArray  列名的数组
	 * @return List<List<Map<String, Object>>> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "funnels")
	public List<List<Map<String, Object>>> getFunnels(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<LinkedHashMap<String,List<Object>>> countDatas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<LinkedHashMap<String,List<Object>>> countDatass = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<List<Map<String, Object>>> barsParams= new ArrayList<List<Map<String, Object>>>();

		
		if(null == resultList){
			return barsParams;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			List<Object> obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			lists.put(columnName,obj);
			datas.add(lists);
		}
		
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			//排序
			Collections.sort(obj);
			lists.put(columnName,obj);
			datas.add(lists);
			countDatas.add(lists);
		}
							
		//去重复
		for (String columnName : columnArray) {
			for (LinkedHashMap<String,List<Object>> str : countDatas) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					List<Object> objs = str.get(columnName);
					for (int  i = 0 ; i < objs.size() ; i++) {
						for(int  j  =  objs.size() -1; j  >  i; j-- ){
							if(objs.get(j).equals(objs.get(i)))  {       
								objs.remove(j);       
					        }  
						}
					}
				}
			}	
		}
		
		//遍历每个列里面去重后的数据出现的次数
		for (String columnName : columnArray) {	
			List<Map<String, Object>> lists= new ArrayList<Map<String, Object>>();
			for (int  i = 0 ; i < countDatas.size() ; i++) {
				List<Object> objs = new ArrayList<Object>();
				objs = countDatas.get(i).get(columnName);				
				List<Object> objs2 = new ArrayList<Object>();
				objs2 = datas.get(i).get(columnName);
				if(objs != null && objs.size()>0 && objs2 != null && objs2.size()>0){
					for (int j = 0; j < objs.size(); j++) {
						Map<String, Object> ints = new LinkedHashMap<String, Object>();					
						int count = Collections.frequency(objs2, objs.get(j));
						ints.put("value",count);
						ints.put("name",objs.get(j));
						lists.add(ints);
					}						
																				
				}				
			}		
			barsParams.add(lists);
		}	
		return barsParams;
	}

	/**
	 * 
	 * @Title: getRectangle 
	 * @Description: 查询图表统计列表数据(矩形树图 ) 
	 * @param columnName String 列名 
	 * @return List<Map<String, Object>> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "rectangle")
	public List<Map<String, Object>> getRectangle(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if(null == resultList){
			return data;
		}
		
		List<Object> obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		
		//去重
		List objCount = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					objCount.add(str.get(columnName));
				}
			}	
		}
		//排序
		Collections.sort(objCount);
		
		for (int  i = 0 ; i < objCount.size() ; i++) {
			for(int  j  =  objCount.size() -1; j  >  i; j-- ){
				if(objCount.get(j).equals(objCount.get(i)))  {       
					objCount.remove(j);       
				}  
			}
		}
		
		//求个数
		if(objCount != null && objCount.size()>0 && obj != null && obj.size()>0){
			for (int j = 0; j < objCount.size(); j++) {
				Map<String, Object> ints = new LinkedHashMap<String, Object>();					
				int count = Collections.frequency(obj, objCount.get(j));
				ints.put("value",count);
				ints.put("name",objCount.get(j));
				data.add(ints);
			}						
		}	
		return data;	
}

	/**
	 * 
	 * @Title: getRectangles 
	 * @Description: 查询图表统计列表数据(矩形树图 ) 
	 * @param columnArray  列名的数组
	 * @return List<List<Map<String, Object>>> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "rectangles")
	public List<List<Map<String, Object>>> getRectangles(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<LinkedHashMap<String,List<Object>>> countDatas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<LinkedHashMap<String,List<Object>>> countDatass = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<List<Map<String, Object>>> barsParams= new ArrayList<List<Map<String, Object>>>();

		
		if(null == resultList){
			return barsParams;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			List<Object> obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			lists.put(columnName,obj);
			datas.add(lists);
		}
		
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			LinkedHashMap<String,List<Object>> lists= new LinkedHashMap<String,List<Object>>();
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			//排序
			Collections.sort(obj);
			lists.put(columnName,obj);
			datas.add(lists);
			countDatas.add(lists);
		}
							
		//去重复
		for (String columnName : columnArray) {
			for (LinkedHashMap<String,List<Object>> str : countDatas) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					List<Object> objs = str.get(columnName);
					for (int  i = 0 ; i < objs.size() ; i++) {
						for(int  j  =  objs.size() -1; j  >  i; j-- ){
							if(objs.get(j).equals(objs.get(i)))  {       
								objs.remove(j);       
					        }  
						}
					}
				}
			}	
		}
		
		//遍历每个列里面去重后的数据出现的次数
		for (String columnName : columnArray) {	
			List<Map<String, Object>> lists= new ArrayList<Map<String, Object>>();
			for (int  i = 0 ; i < countDatas.size() ; i++) {
				List<Object> objs = new ArrayList<Object>();
				objs = countDatas.get(i).get(columnName);				
				List<Object> objs2 = new ArrayList<Object>();
				objs2 = datas.get(i).get(columnName);
				if(objs != null && objs.size()>0 && objs2 != null && objs2.size()>0){
					for (int j = 0; j < objs.size(); j++) {
						Map<String, Object> ints = new LinkedHashMap<String, Object>();					
						int count = Collections.frequency(objs2, objs.get(j));
						ints.put("value",count);
						ints.put("name",objs.get(j));
						lists.add(ints);
					}						
																				
				}				
			}		
			barsParams.add(lists);
		}	
		return barsParams;
	}


	/**
	 * 
	 * @Title: getChord 
	 * @Description: 查询图表统计列表数据(和弦图) 
	 * @param columnName String 列名 
	 * @return List<List<Object>> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "chord")
	public List<List<Object>> getChord(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<List<Object>> data = new ArrayList<List<Object>>();
		if(null == resultList){
			return data;
		}
		
		List obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		List<Object> listA= new ArrayList<Object>();
		List<Object> listB= new ArrayList<Object>();
		List<Object> listC= new ArrayList<Object>();
		List<Object> listD= new ArrayList<Object>();

		
		if(null != obj && obj.size()>0){
			double sum1 = 0;
			double sum2 = 0;
			double sum3 = 0;
			double sum4 = 0;

			int sizes =obj.size();
			int cou =sizes % 4;
			int cou1 = (sizes-cou)/4;
			List list1= new ArrayList<Object>();
			for (int i = 0; i < cou1; i++) {
				double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
				sum1 += num;
				list1.add(obj.get(i));
			}
			listA.add(cou1);
			listA.add(Collections.max(list1));
			listA.add(Collections.min(list1));
			listA.add(Math.ceil(sum1/cou1));
			
			
			List list2= new ArrayList<Object>();
			for (int i = cou1; i < cou1*2; i++) {
				double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
				sum2 += num;
				list2.add(obj.get(i));
			}
			listB.add(cou1);
			listB.add(Collections.max(list2));
			listB.add(Collections.min(list2));
			listB.add(Math.ceil(sum2/cou1));
			
			List list3= new ArrayList<Object>();
			for (int i = cou1*2; i < cou1*3; i++) {
				double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
				sum3 += num;
				list3.add(obj.get(i));
			}
			listC.add(cou1);
			listC.add(Collections.max(list3));
			listC.add(Collections.min(list3));
			listC.add(Math.ceil(sum3/cou1));
			
			List list4= new ArrayList<Object>();
			for (int i = cou1*3; i < cou1*4+cou; i++) {
				double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
				sum4 += num;
				list4.add(obj.get(i));
			}
			listD.add(cou1);
			listD.add(Collections.max(list4));
			listD.add(Collections.min(list4));
			listD.add(Math.ceil(sum4/(cou1+cou)));
			
			data.add(listA);
			data.add(listB);
			data.add(listC);
			data.add(listD);

			
		}	
		return data;
	}

	/**
	 * 
	 * @Title: getChords 
	 * @Description: 查询图表统计列表数据(和弦图) 
	 * @param columnArray  列名的数组
	 * @return List<List<List<Object>>>  返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "chords")
	public List<List<List<Object>>> getChords(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<List<List<Object>>> data = new ArrayList<List<List<Object>>>();
		List<List<Object>> datas1 = new ArrayList<List<Object>>();
		List<List<Object>> datas2 = new ArrayList<List<Object>>();

		if(null == resultList){
			return data;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			datas2.add(obj);			
		}
		
		if(null != datas2 && datas2.size()>0){
				for (List<Object> obj : datas2) {
					if(null != obj && obj.size()>0){
						List<Object> listA= new ArrayList<Object>();
						List<Object> listB= new ArrayList<Object>();
						List<Object> listC= new ArrayList<Object>();
						List<Object> listD= new ArrayList<Object>();
	
						
						if(null != obj && obj.size()>0){
							double sum1 = 0;
							double sum2 = 0;
							double sum3 = 0;
							double sum4 = 0;
	
							int sizes =obj.size();
							int cou =sizes % 4;
							int cou1 = (sizes-cou)/4;
							List list1= new ArrayList<Object>();
							for (int i = 0; i < cou1; i++) {
								double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
								sum1 += num;
								list1.add(obj.get(i));
							}
							listA.add(cou1);
							listA.add(Collections.max(list1));
							listA.add(Collections.min(list1));
							listA.add(Math.ceil(sum1/cou1));
							
							
							List list2= new ArrayList<Object>();
							for (int i = cou1; i < cou1*2; i++) {
								double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
								sum2 += num;
								list2.add(obj.get(i));
							}
							listB.add(cou1);
							listB.add(Collections.max(list2));
							listB.add(Collections.min(list2));
							listB.add(Math.ceil(sum2/cou1));
							
							List list3= new ArrayList<Object>();
							for (int i = cou1*2; i < cou1*3; i++) {
								double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
								sum3 += num;
								list3.add(obj.get(i));
							}
							listC.add(cou1);
							listC.add(Collections.max(list3));
							listC.add(Collections.min(list3));
							listC.add(Math.ceil(sum3/cou1));
							
							List list4= new ArrayList<Object>();
							for (int i = cou1*3; i < cou1*4+cou; i++) {
								double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
								sum4 += num;
								list4.add(obj.get(i));
							}
							listD.add(cou1);
							listD.add(Collections.max(list4));
							listD.add(Collections.min(list4));
							listD.add(Math.ceil(sum4/(cou1+cou)));
							
							datas1.add(listA);
							datas1.add(listB);
							datas1.add(listC);
							datas1.add(listD);
					}	
				}
			}	
		}
		if(null != datas1 && datas1.size()>0){
			for (int i = 0; i < datas1.size()/4; i++) {
				List<List<Object>> datas3 = new ArrayList<List<Object>>();
				datas3.add(datas1.get(i));
				datas3.add(datas1.get(i+1));
				datas3.add(datas1.get(i+2));
				datas3.add(datas1.get(i+3));
				data.add(datas3);
			}
		}
		
		return data;
}

	/**
	 * 
	 * @Title: getMix 
	 * @Description: 查询图表统计列表数据(混搭图) 
	 * @param columnName String 列名 
	 * @return List<Map<String, Object>> 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "mix")
	public List<Map<String, Object>> getMix(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if(null == resultList){
			return data;
		}
		
		List<Object> obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		
		int number =1;
		if(null != obj && obj.size()>0){
			int sizes =obj.size();
			int cou =sizes % 10;
			int cou1 = (sizes-cou)/10;
			if(obj.size()<10){
				List list1= new ArrayList<Object>();
				Map<String,Object> list2= new LinkedHashMap<String,Object>();
				double sum = 0;
				for (int j = 0; j < obj.size(); j++) {
					double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
					sum += num;
					list1.add(obj.get(j));
				}
				list2.put("param",number++);
				list2.put("max",Collections.max(list1));
				list2.put("min",Collections.min(list1));
				list2.put("avg",Math.ceil(sum/10));
				data.add(list2);
			}else{	
				if(cou == 0){
					for (int i = 0; i < cou1; i++) {
						List list1= new ArrayList<Object>();
						Map<String,Object> list2= new LinkedHashMap<String,Object>();
						double sum = 0;
						for (int j = 10*i; j < 10*(i+1); j++) {
							double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
							sum += num;
							list1.add(obj.get(j));
						}
						list2.put("param",number++);
						list2.put("max",Collections.max(list1));
						list2.put("min",Collections.min(list1));
						list2.put("avg",Math.ceil(sum/10));
						data.add(list2);
					}
				}else{
					for (int i = 0; i < cou1; i++) {
						List list1= new ArrayList<Object>();
						Map<String,Object> list2= new LinkedHashMap<String,Object>();
						double sum = 0;
						for (int j = 10*i; j < 10*(i+1); j++) {
							double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
							sum += num;
							list1.add(obj.get(j));
						}
						list2.put("param",number++);
						list2.put("max",Collections.max(list1));
						list2.put("min",Collections.min(list1));
						list2.put("avg",Math.ceil(sum/10));
						data.add(list2);
					}
					
					List list1= new ArrayList<Object>();
					Map<String,Object> list2= new LinkedHashMap<String,Object>();
					double sum = 0;
					for (int j = cou1*10; j < cou1*10+cou; j++) {
						double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
						sum += num;
						list1.add(obj.get(j));
					}
					list2.put("param",cou1+1);
					list2.put("max",Collections.max(list1));
					list2.put("min",Collections.min(list1));
					list2.put("avg",Math.ceil(sum/cou));
					data.add(list2);				
				}		
			}	
		}	
		return data;	
	}

	/**
	 * 
	 * @Title: getMixs 
	 * @Description: 查询图表统计列表数据(混搭图) 
	 * @param columnArray  列名的数组
	 * @return List<List<Map<String, Object>>>  返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "mixs")
	public List<List<Map<String, Object>>> getMixs(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		List<List<Map<String, Object>>> datas = new ArrayList<List<Map<String, Object>>>();

		List<List<Object>> datas2 = new ArrayList<List<Object>>();

		if(null == resultList){
			return datas;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			datas2.add(obj);			
		}
		if(null != datas2 && datas2.size()>0){
			for (List<Object> obj : datas2) {		
				int number =1;
				if(null != obj && obj.size()>0){
					int sizes =obj.size();
					int cou =sizes % 10;
					int cou1 = (sizes-cou)/10;
					if(obj.size()<10){
						List list1= new ArrayList<Object>();
						Map<String,Object> list2= new LinkedHashMap<String,Object>();
						double sum = 0;
						for (int j = 0; j < obj.size(); j++) {
							double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
							sum += num;
							list1.add(obj.get(j));
						}
						list2.put("param",number++);
						list2.put("max",Collections.max(list1));
						list2.put("min",Collections.min(list1));
						list2.put("avg",Math.ceil(sum/10));
						data.add(list2);
					}else{	
						if(cou == 0){
							for (int i = 0; i < cou1; i++) {
								List list1= new ArrayList<Object>();
								Map<String,Object> list2= new LinkedHashMap<String,Object>();
								double sum = 0;
								for (int j = 10*i; j < 10*(i+1); j++) {
									double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
									sum += num;
									list1.add(obj.get(j));
								}
								list2.put("param",number++);
								list2.put("max",Collections.max(list1));
								list2.put("min",Collections.min(list1));
								list2.put("avg",Math.ceil(sum/10));
								data.add(list2);
							}
						}else{
							for (int i = 0; i < cou1; i++) {
								List list1= new ArrayList<Object>();
								Map<String,Object> list2= new LinkedHashMap<String,Object>();
								double sum = 0;
								for (int j = 10*i; j < 10*(i+1); j++) {
									double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
									sum += num;
									list1.add(obj.get(j));
								}
								list2.put("param",number++);
								list2.put("max",Collections.max(list1));
								list2.put("min",Collections.min(list1));
								list2.put("avg",Math.ceil(sum/10));
								data.add(list2);
							}
							
							List list1= new ArrayList<Object>();
							Map<String,Object> list2= new LinkedHashMap<String,Object>();
							double sum = 0;
							for (int j = cou1*10; j < cou1*10+cou; j++) {
								double num =Double.parseDouble(obj.get(j)==null?"":obj.get(j).toString());
								sum += num;
								list1.add(obj.get(j));
							}
							list2.put("param",cou1+1);
							list2.put("max",Collections.max(list1));
							list2.put("min",Collections.min(list1));
							list2.put("avg",Math.ceil(sum/cou));
							data.add(list2);	
						}		
					}	
				}	
			}	
		}
		if(null != data && data.size()>0){
			int sizeParam =data.size()/datas2.size();
			for (int i = 0; i < datas2.size(); i++) {
				List<Map<String, Object>> listA = new ArrayList<Map<String, Object>>();
				for (int j = i*sizeParam; j < (i+1)*sizeParam; j++) {
					listA.add(data.get(j));
				}
				datas.add(listA);
			}
		}
		System.out.println(data.toString());
		return datas;
	}

	/**
	 * 
	 * @Title: getForce 
	 * @Description: 查询图表统计列表数据(力导向布局图) 
	 * @param columnName String 列名 
	 * @return Map<String, Object> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "force")
	public List<List<Object>> getForce(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<List<Object>> data = new ArrayList<List<Object>>();
		if(null == resultList){
			return data;
		}
		
		List obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		List<Object> listA= new ArrayList<Object>();
		List<Object> listB= new ArrayList<Object>();
		List<Object> listC= new ArrayList<Object>();
		List<Object> listD= new ArrayList<Object>();

		
		if(null != obj && obj.size()>0){
			double sum1 = 0;
			double sum2 = 0;
			double sum3 = 0;
			double sum4 = 0;

			int sizes =obj.size();
			int cou =sizes % 4;
			int cou1 = (sizes-cou)/4;
			List list1= new ArrayList<Object>();
			for (int i = 0; i < cou1; i++) {
				double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
				sum1 += num;
				list1.add(obj.get(i));
			}
			listA.add(cou1);
			listA.add(Collections.max(list1));
			listA.add(Collections.min(list1));
			listA.add(Math.ceil(sum1/cou1));
			
			
			List list2= new ArrayList<Object>();
			for (int i = cou1; i < cou1*2; i++) {
				double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
				sum2 += num;
				list2.add(obj.get(i));
			}
			listB.add(cou1);
			listB.add(Collections.max(list2));
			listB.add(Collections.min(list2));
			listB.add(Math.ceil(sum2/cou1));
			
			List list3= new ArrayList<Object>();
			for (int i = cou1*2; i < cou1*3; i++) {
				double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
				sum3 += num;
				list3.add(obj.get(i));
			}
			listC.add(cou1);
			listC.add(Collections.max(list3));
			listC.add(Collections.min(list3));
			listC.add(Math.ceil(sum3/cou1));
			
			List list4= new ArrayList<Object>();
			for (int i = cou1*3; i < cou1*4+cou; i++) {
				double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
				sum4 += num;
				list4.add(obj.get(i));
			}
			listD.add(cou1);
			listD.add(Collections.max(list4));
			listD.add(Collections.min(list4));
			listD.add(Math.ceil(sum4/(cou1+cou)));
			
			data.add(listA);
			data.add(listB);
			data.add(listC);
			data.add(listD);

			
		}	
		return data;
	}


	/**
	 * 
	 * @Title: getForces 
	 * @Description: 查询图表统计列表数据(力导向布局图) 
	 * @param columnArray  列名的数组
	 * @return List<List<Map<String, Object>>>  返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "forces")
	public List<List<List<Object>>> getForces(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<List<List<Object>>> data = new ArrayList<List<List<Object>>>();
		List<List<Object>> datas1 = new ArrayList<List<Object>>();
		List<List<Object>> datas2 = new ArrayList<List<Object>>();

		if(null == resultList){
			return data;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			datas2.add(obj);			
		}
		
		if(null != datas2 && datas2.size()>0){
				for (List<Object> obj : datas2) {
					if(null != obj && obj.size()>0){
						List<Object> listA= new ArrayList<Object>();
						List<Object> listB= new ArrayList<Object>();
						List<Object> listC= new ArrayList<Object>();
						List<Object> listD= new ArrayList<Object>();
	
						
						if(null != obj && obj.size()>0){
							double sum1 = 0;
							double sum2 = 0;
							double sum3 = 0;
							double sum4 = 0;
	
							int sizes =obj.size();
							int cou =sizes % 4;
							int cou1 = (sizes-cou)/4;
							List list1= new ArrayList<Object>();
							for (int i = 0; i < cou1; i++) {
								double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
								sum1 += num;
								list1.add(obj.get(i));
							}
							listA.add(cou1);
							listA.add(Collections.max(list1));
							listA.add(Collections.min(list1));
							listA.add(Math.ceil(sum1/cou1));
							
							
							List list2= new ArrayList<Object>();
							for (int i = cou1; i < cou1*2; i++) {
								double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
								sum2 += num;
								list2.add(obj.get(i));
							}
							listB.add(cou1);
							listB.add(Collections.max(list2));
							listB.add(Collections.min(list2));
							listB.add(Math.ceil(sum2/cou1));
							
							List list3= new ArrayList<Object>();
							for (int i = cou1*2; i < cou1*3; i++) {
								double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
								sum3 += num;
								list3.add(obj.get(i));
							}
							listC.add(cou1);
							listC.add(Collections.max(list3));
							listC.add(Collections.min(list3));
							listC.add(Math.ceil(sum3/cou1));
							
							List list4= new ArrayList<Object>();
							for (int i = cou1*3; i < cou1*4+cou; i++) {
								double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
								sum4 += num;
								list4.add(obj.get(i));
							}
							listD.add(cou1);
							listD.add(Collections.max(list4));
							listD.add(Collections.min(list4));
							listD.add(Math.ceil(sum4/(cou1+cou)));
							
							datas1.add(listA);
							datas1.add(listB);
							datas1.add(listC);
							datas1.add(listD);
					}	
				}
			}	
		}
		if(null != datas1 && datas1.size()>0){
			for (int i = 0; i < datas1.size()/4; i++) {
				List<List<Object>> datas3 = new ArrayList<List<Object>>();
				datas3.add(datas1.get(i));
				datas3.add(datas1.get(i+1));
				datas3.add(datas1.get(i+2));
				datas3.add(datas1.get(i+3));
				data.add(datas3);
			}
		}	
		return data;
}
	/**
	 * 
	 * @Title: getRadar 
	 * @Description: 查询图表统计列表数据(雷达图) 
	 * @param columnName String 列名 
	 * @return Map<String, Object> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "radar")
	public List<Map<String, Object>> getRadar(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if(null == resultList){
			return data;
		}
		
		List obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != obj && obj.size()>0){
			double sum = 0;
			for (int i = 0; i < obj.size(); i++) {
				double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
				sum += num;
			}
			map.put("max", Collections.max(obj));
			map.put("min", Collections.min(obj));
			map.put("sum",sum);
			map.put("count",obj.size());
			map.put("avg",Math.ceil(sum/obj.size()));
			data.add(map);
		}	
		return data;
	}

	/**
	 * 
	 * @Title: getRadars 
	 * @Description: 查询图表统计列表数据(雷达图) 
	 * @param columnArray  列名的数组
	 * @return List<List<Map<String, Object>>>  返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "radars")
	public List<Map<String, Object>>  getRadars(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();	
		if(null == resultList){
			return data;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			Map<String, Object> map = new HashMap<String, Object>();
			if(null != obj && obj.size()>0){
				double sum = 0;
				for (int i = 0; i < obj.size(); i++) {
					double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
					sum += num;
				}
				map.put("max", Collections.max(obj));
				map.put("min", Collections.min(obj));
				map.put("sum",sum);
				map.put("count",obj.size());
				map.put("avg",Math.ceil(sum/obj.size()));
				data.add(map);
			}	
		}

		return data;
	}

	
	/**
	 * 
	 * @Title: getGauge 
	 * @Description: 查询图表统计列表数据(仪表盘图) 
	 * @param columnName String 列名 
	 * @return Map<String, Object> 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "gauge")
	public List<Map<String, Object>> getGauge(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if(null == resultList){
			return data;
		}
		
		List obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != obj && obj.size()>0){
			double sum = 0;
			for (int i = 0; i < obj.size(); i++) {
				double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
				sum += num;
			}
			@SuppressWarnings("unchecked")
			double max = Double.parseDouble(Collections.max(obj)==null?"":Collections.max(obj).toString());
			DecimalFormat  df = new DecimalFormat("######0.00");   
			map.put("para",df.format(Math.ceil(sum/obj.size())/max));		
			data.add(map);
		}	
		return data;
	}


	/**
	 * 
	 * @Title: getGauges 
	 * @Description: 查询图表统计列表数据(仪表盘图) 
	 * @param columnArray  列名的数组
	 * @return List<List<Map<String, Object>>>  返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "gauges")
	public List<List<Map<String, Object>>> getGauges(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<List<Map<String, Object>>>  barsParams= new ArrayList<List<Map<String, Object>>>();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		
		if(null == resultList){
			return barsParams;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			Map<String, Object> map = new HashMap<String, Object>();
			if(null != obj && obj.size()>0){
				double sum = 0;
				for (int i = 0; i < obj.size(); i++) {
					double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
					sum += num;
				}
				@SuppressWarnings("unchecked")
				double max = Double.parseDouble(Collections.max(obj)==null?"":Collections.max(obj).toString());
				DecimalFormat  df = new DecimalFormat("######0.00");   
				map.put("para",df.format(Math.ceil(sum/obj.size())/max));		
				data.add(map);
			}	
			barsParams.add(data);
		}
		return barsParams;
	}

	/**
	 * 
	 * @Title: getVenn 
	 * @Description: 查询图表统计列表数据(韦恩图) 
	 * @param columnName String 列名 
	 * @return Map<String, Object> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "venn")
	public List<Map<String, Object>> getVenn(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if(null == resultList){
			return data;
		}
		
		List obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != obj && obj.size()>0){
			double sum = 0;
			for (int i = 0; i < obj.size(); i++) {
				double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
				sum += num;
			}
			map.put("avgValue",Math.ceil(sum/obj.size()));
			map.put("maxValue", Collections.max(obj));
			map.put("minValue", Collections.min(obj));
			data.add(map);
		}	
		return data;
	}

	/**
	 * 
	 * @Title: getVenns 
	 * @Description: 查询图表统计列表数据(韦恩图) 
	 * @param columnArray  列名的数组
	 * @return List<List<Map<String, Object>>>  返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "venns")
	public List<List<Map<String, Object>>> getVenns(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<List<Map<String, Object>>>  barsParams= new ArrayList<List<Map<String, Object>>>();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		
		if(null == resultList){
			return barsParams;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			Map<String, Object> map = new HashMap<String, Object>();
			if(null != obj && obj.size()>0){
				double sum = 0;
				for (int i = 0; i < obj.size(); i++) {
					double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
					sum += num;
				}
				map.put("avgValue",Math.ceil(sum/obj.size()));
				map.put("maxValue", Collections.max(obj));
				map.put("minValue", Collections.min(obj));
				data.add(map);
			}	
			barsParams.add(data);
		}

		return barsParams;
	}

	/**
	 * 
	 * @Title: getHeatmap 
	 * @Description: 查询图表统计列表数据(热力图 ) 
	 * @param columnName String 列名 
	 * @return Map<String, Object> 返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "heatmap")
	public List<Map<String, Object>> getHeatmap(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if(null == resultList){
			return data;
		}
		
		List obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != obj && obj.size()>0){
			map.put("countValue", obj.size());
			map.put("maxValue", Collections.max(obj));
			map.put("minValue", Collections.min(obj));
			data.add(map);
		}	
		return data;
	}

	/**
	 * 
	 * @Title: getHeatmaps 
	 * @Description: 查询图表统计列表数据(热力图) 
	 * @param columnArray  列名的数组
	 * @return List<List<Map<String, Object>>>  返回类型
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "heatmaps")
	public List<List<Map<String, Object>>> getHeatmaps(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<List<Map<String, Object>>>  barsParams= new ArrayList<List<Map<String, Object>>>();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		
		if(null == resultList){
			return barsParams;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			Map<String, Object> map = new HashMap<String, Object>();
			if(null != obj && obj.size()>0){
				map.put("countValue", obj.size());
				map.put("maxValue", Collections.max(obj));
				map.put("minValue", Collections.min(obj));
				data.add(map);
			}	
			barsParams.add(data);
		}

		return barsParams;
	}

	/**
	 * 
	 * @Title: getWordCloud 
	 * @Description: 查询图表统计列表数据(字符云图 ) 
	 * @param columnName String 列名 
	 * @return Map<String, Object> 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "wordCloud")
	public List<Map<String, Object>> getWordCloud(String columnName,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		if(null == resultList){
			return data;
		}
		
		List obj = new ArrayList<Object>();
		if(null != columnName){
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != obj && obj.size()>0){
			double sum = 0;
			for (int i = 0; i < obj.size(); i++) {
				double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
				sum += num;
			}
			map.put("avgValue",Math.ceil(sum/obj.size()));
			double maxP=Double.parseDouble(Collections.max(obj).toString());
			double minP=Double.parseDouble(Collections.min(obj).toString());
			map.put("maxValue", Collections.max(obj));
			map.put("minValue", Collections.min(obj));
			map.put("sumValue", sum);
			map.put("countValue", obj.size());
			map.put("hypotValue",Math.ceil(Math.hypot(maxP, minP)));   
			map.put("cbrtValue",Math.ceil(Math.cbrt(maxP)));     
			map.put("sinValue",Math.ceil(Math.sin(maxP)));
			map.put("cosValue",Math.ceil(Math.cos(maxP)));  
			map.put("tanValue",Math.ceil(Math.tan(maxP)));
			map.put("asinValue",Math.ceil(Math.asin(maxP)));
			map.put("acosValue",Math.ceil(Math.acos(maxP)));
			map.put("atanValue",Math.ceil(Math.atan(maxP))); 
			map.put("toDegreesValue",Math.ceil(Math.toDegrees(maxP)));
			map.put("toRadiansValue",Math.ceil(Math.toRadians(maxP)));
			
			map.put("avgcbrtValue",Math.ceil(Math.cbrt(Math.ceil(sum/obj.size()))));     
			map.put("avgsinValue",Math.ceil(Math.sin(Math.ceil(sum/obj.size()))));
			map.put("avgcosValue",Math.ceil(Math.cos(Math.ceil(sum/obj.size()))));  
			map.put("avgtanValue",Math.ceil(Math.tan(Math.ceil(sum/obj.size()))));
			map.put("avgasinValue",Math.ceil(Math.asin(Math.ceil(sum/obj.size()))));
			map.put("avgacosValue",Math.ceil(Math.acos(Math.ceil(sum/obj.size()))));
			map.put("avgatanValue",Math.ceil(Math.atan(Math.ceil(sum/obj.size())))); 
			map.put("avgtoDegreesValue",Math.ceil(Math.toDegrees(Math.ceil(sum/obj.size()))));
			map.put("avgtoRadiansValue",Math.ceil(Math.toRadians(Math.ceil(sum/obj.size()))));
			data.add(map);
		}	
		return data;
	}

	/**
	 * 
	 * @Title: getWordClouds 
	 * @Description: 查询图表统计列表数据(字符云图) 
	 * @param columnArray  列名的数组
	 * @return List<List<Map<String, Object>>>  返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "wordClouds")
	public List<Map<String, Object>> getWordCloudsgetHeatmaps(String[] columnArray,String id,HttpSession session) {
		// 图表数据
		List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
		List<LinkedHashMap<String,List<Object>>> datas = new ArrayList<LinkedHashMap<String,List<Object>>>();
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		
		if(null == resultList){
			return data;
		}
		//获取所有的每列的数据，组成一个list集合
		for (String columnName : columnArray) {//遍历选择的字段
			List obj = new ArrayList<Object>();
			for (LinkedHashMap<String, Object> str : resultList) {//遍历数据集
				if(str.get(columnName)!=null){//匹配选择的字段
					obj.add(str.get(columnName));
				}
			}	
			Map<String, Object> map = new HashMap<String, Object>();
			if(null != obj && obj.size()>0){
				double sum = 0;
				for (int i = 0; i < obj.size(); i++) {
					double num =Double.parseDouble(obj.get(i)==null?"":obj.get(i).toString());
					sum += num;
				}
				map.put("avgValue",Math.ceil(sum/obj.size()));
				double maxP=Double.parseDouble(Collections.max(obj).toString());
				double minP=Double.parseDouble(Collections.min(obj).toString());
				map.put("maxValue", Collections.max(obj));
				map.put("minValue", Collections.min(obj));
				map.put("sumValue", sum);
				map.put("countValue", obj.size());
				map.put("hypotValue",Math.ceil(Math.hypot(maxP, minP)));   
				map.put("cbrtValue",Math.ceil(Math.cbrt(maxP)));     
				map.put("sinValue",Math.ceil(Math.sin(maxP)));
				map.put("cosValue",Math.ceil(Math.cos(maxP)));  
				map.put("tanValue",Math.ceil(Math.tan(maxP)));
				map.put("asinValue",Math.ceil(Math.asin(maxP)));
				map.put("acosValue",Math.ceil(Math.acos(maxP)));
				map.put("atanValue",Math.ceil(Math.atan(maxP))); 
				map.put("toDegreesValue",Math.ceil(Math.toDegrees(maxP)));
				map.put("toRadiansValue",Math.ceil(Math.toRadians(maxP)));
				map.put("avgcbrtValue",Math.ceil(Math.cbrt(Math.ceil(sum/obj.size()))));     
				map.put("avgsinValue",Math.ceil(Math.sin(Math.ceil(sum/obj.size()))));
				map.put("avgcosValue",Math.ceil(Math.cos(Math.ceil(sum/obj.size()))));  
				map.put("avgtanValue",Math.ceil(Math.tan(Math.ceil(sum/obj.size()))));
				map.put("avgasinValue",Math.ceil(Math.asin(Math.ceil(sum/obj.size()))));
				map.put("avgacosValue",Math.ceil(Math.acos(Math.ceil(sum/obj.size()))));
				map.put("avgatanValue",Math.ceil(Math.atan(Math.ceil(sum/obj.size())))); 
				map.put("avgtoDegreesValue",Math.ceil(Math.toDegrees(Math.ceil(sum/obj.size()))));
				map.put("avgtoRadiansValue",Math.ceil(Math.toRadians(Math.ceil(sum/obj.size()))));
				data.add(map);
			}	
		}

		return data;
	}

	/**
	 * 
	 * @Title: getTree 
	 * @Description: 查询图表统计列表数据(树图 ) 
	 * @param columnName String 列名 
	 * @return Map<String, Object> 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "tree")
	public List<Map<String, Object>> getTree(String columnName) {
		// 图表数据
		List<Map<String, Object>> data = Lists.newArrayList();
		data = echartsService.getTree(columnName);
		return data;
	}


	/**
	 * 
	 * @Title: getTrees 
	 * @Description: 查询图表统计列表数据(树图) 
	 * @param columnArray  列名的数组
	 * @return List<List<Map<String, Object>>>  返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "trees")
	public List<List<Map<String, Object>>> getTrees(@RequestParam(value = "columnArray[]") String[] columnArray) {
		// 图表数据
		List<List<Map<String, Object>>> datas = Lists.newArrayList();
		for (String columnName : columnArray) {
			List<Map<String, Object>> data = echartsService.getTree(columnName);
			datas.add(data);
		}
		return datas;
	}

	/**
	 * 
	 * @Title: getMap 
	 * @Description: 查询图表统计列表数据(地图 ) 
	 * @param columnName String 列名 
	 * @return Map<String, Object> 返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "map")
	public Map<String, List<Map<String, Object>>> getMap(String columnName) {
		// 图表数据
		Map<String, List<Map<String, Object>>> data = Maps.newHashMap();
		data = echartsService.getMap(columnName);
		return data;
	}

	/**
	 * 
	 * @Title: getMaps 
	 * @Description: 查询图表统计列表数据(地图) 
	 * @param columnArray  列名的数组
	 * @return List<List<Map<String, Object>>>  返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "maps")
	public List<Map<String, List<Map<String, Object>>>> getMaps(
			@RequestParam(value = "columnArray[]") String[] columnArray) {
		// 图表数据
		List<Map<String, List<Map<String, Object>>>> datas = Lists.newArrayList();
		for (String columnName : columnArray) {
			Map<String, List<Map<String, Object>>> data = echartsService.getMap(columnName);
			datas.add(data);
		}
		return datas;
	}
}
