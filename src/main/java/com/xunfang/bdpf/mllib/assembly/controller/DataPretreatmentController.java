package com.xunfang.bdpf.mllib.assembly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.Assembly;
import com.xunfang.bdpf.mllib.assembly.entity.AssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.ConvertAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.ConvertAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.JoinAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.JoinChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.JoinChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.NormalizationAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.NormalizationAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.NormalizationChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.NormalizationChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.RandomSamplingAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.SplitAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.SplitAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.SqlAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StandardizationAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StandardizationChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StandardizationChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.UnionAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.UnionChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.UnionChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.service.AddSerialNumAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.AddSerialNumChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.AssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.ConvertAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FillMissingValuesAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FillMissingValuesChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FilterMappingAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FilterMappingChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FlowBussiness;
import com.xunfang.bdpf.mllib.assembly.service.JoinAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.JoinChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.NormalizationAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.NormalizationChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.RandomSamplingAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.SplitAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.SqlAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.StandardizationAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.StandardizationChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.UnionAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.UnionChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.vo.AddSerialNumAssemblyVo;
import com.xunfang.bdpf.mllib.assembly.vo.FillMissingValuesAssemblyVo;
import com.xunfang.bdpf.mllib.assembly.vo.JoinAssemblyVo;
import com.xunfang.bdpf.mllib.assembly.vo.StandardizationAssemblyVo;
import com.xunfang.bdpf.mllib.assembly.vo.UnionAssemblyVo;
import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
import com.xunfang.bdpf.mllib.experiment.service.DataSourceService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Identities;
import com.xunfang.utils.Tools;

/**
 * 
 * @ClassName DataPretreatmentController
 * @Description: 数据预处理控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月9日 下午5:18:42
 * @version V1.0
 */
@Controller
@RequestMapping(value = "datapretreatment")
public class DataPretreatmentController {
	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(DataPretreatmentController.class);
	
	//类型转换服务接口类
	@Autowired
	private ConvertAssemblyService convertAssemblyService;
	
	@Autowired
	private AssemblyService assemblyService;

	//
	@Autowired
	private DataSourceService dataSourceService;
	
	@Autowired
	private NormalizationAssemblyService  normalizationAssemblyService;
	@Autowired
	private NormalizationChildAssemblyService normalizationChildAssemblyService;
	
	@Autowired
	private SplitAssemblyService splitAssemblyService;

	@Autowired
	private RandomSamplingAssemblyService randomSamplingAssemblyService;

	@Autowired
	private AddSerialNumAssemblyService addSerialNumAssemblyService;
	@Autowired
	private AddSerialNumChildAssemblyService addSerialNumChildAssemblyService;

	@Autowired
	private StandardizationAssemblyService standardizationAssemblyService;
	@Autowired
	private StandardizationChildAssemblyService standardizationChildAssemblyService;

	@Autowired
	private FillMissingValuesAssemblyService fillMissingValuesAssemblyService;
	@Autowired
	private FillMissingValuesChildAssemblyService fillMissingValuesChildAssemblyService ;
	
	@Autowired
	private JoinAssemblyService joinAssemblyService;
	@Autowired
	private JoinChildAssemblyService joinChildAssemblyService;
	
	@Autowired
	private UnionAssemblyService unionAssemblyService;
	@Autowired
	private UnionChildAssemblyService unionChildAssemblyService;
	
	@Autowired
	private FilterMappingAssemblyService filterMappingAssemblyService;
	@Autowired
	private FilterMappingChildAssemblyService filterMappingChildAssemblyService;
	
	@Autowired
	private SqlAssemblyService sqlAssemblyService;
	
	/**
	 * 
	  * @Title: model_6_list
	  * @Description: 过滤与映射查询
	  *  @param expid
	  *  @param session
	  *  @return  Map<String,Object> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_6_list")
	@ResponseBody
	public Map<String,Object> model_6_list(String expid,String id,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		//获取当前登录用户信息
		User  user = (User)session.getAttribute("loginuser");
		if(user == null){
			return map;
		}
		String account = null;
		if(user.getUserType() != 2){
			account = user.getAccount();
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
			//如果父组件是增加序列号12，这里默认增加一列
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
			//如果父组件ID是SQL脚本 91，特殊处理
			}else if(assemblyParent!=null&&"91".equals(assemblyParent.getBdpfMllibAssemblyLibraryId())){
				SqlAssembly sqlAssembly = sqlAssemblyService.selectByPrimaryKey(assemblyParent.getId());
				if(sqlAssembly!=null&&sqlAssembly.getSqlScript()!=null){
					tableChilds = FlowBussiness.queryColumnValues(sqlAssembly.getSqlScript());
				}else{
					tableChilds = (List<TableChild>)session.getAttribute("columnNamelist");
				}
			}
			map.put("tableChilds", tableChilds);
			
			//过滤与映射信息
			FilterMappingAssembly filterMappingAssembly = filterMappingAssemblyService.selectByPrimaryKey(id);
			map.put("filterMappingAssembly", filterMappingAssembly);
			
			//过滤与映射字段信息
			List<FilterMappingChildAssembly> filterMappingChildAssemblies = null;
			if(filterMappingAssembly!=null){
				FilterMappingChildAssemblyExample filterMappingChildAssemblyExample = new FilterMappingChildAssemblyExample();
				filterMappingChildAssemblyExample.createCriteria().andBdpfMllibAssemblyFilterMappingIdEqualTo(id);
				filterMappingChildAssemblyExample.setOrderByClause("xh");
				filterMappingChildAssemblies = filterMappingChildAssemblyService.selectByExample(filterMappingChildAssemblyExample);
			}
			map.put("filterMappingChildAssemblies", filterMappingChildAssemblies);
			
			List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
			for (FilterMappingChildAssembly filterMappingChildAssembly : filterMappingChildAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(filterMappingChildAssembly.getName());
				tableChild.setType(filterMappingChildAssembly.getDataType());
				tableChildList.add(tableChild);
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
	  * @Title: model_6_save
	  * @Description: 过滤与映射保存
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_6_save")
	@ResponseBody
	public Map<String,Object> model_6_save(String id,String[] conn,String filtrationCondition,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(id==null||"".equals(id)){
			return map;
		}
		//删除
		FilterMappingChildAssemblyExample filterMappingChildAssemblyExample = new FilterMappingChildAssemblyExample();
		filterMappingChildAssemblyExample.createCriteria().andBdpfMllibAssemblyFilterMappingIdEqualTo(id);
		filterMappingChildAssemblyService.deleteByExample(filterMappingChildAssemblyExample);
		
		if(filtrationCondition!=null){
			FilterMappingAssemblyExample filterMappingAssemblyExample = new FilterMappingAssemblyExample();
			filterMappingAssemblyExample.createCriteria().andIdEqualTo(id);
			filterMappingAssemblyService.deleteByExample(filterMappingAssemblyExample);
		}
		
		//保存操作
		FilterMappingAssembly filterMappingAssembly = filterMappingAssemblyService.selectByPrimaryKey(id);
		if(filterMappingAssembly==null){
			filterMappingAssembly = new FilterMappingAssembly();
			filterMappingAssembly.setId(id);
			filterMappingAssembly.setBdpfMllibAssemblyId(id);
			filterMappingAssembly.setFiltrationCondition(filtrationCondition);
			filterMappingAssemblyService.insertSelective(filterMappingAssembly);
		}else{
			FilterMappingAssemblyExample filterMappingAssemblyExample = new FilterMappingAssemblyExample();
			filterMappingAssemblyExample.createCriteria().andIdEqualTo(id);
			filterMappingAssembly.setFiltrationCondition(filtrationCondition);
			filterMappingAssemblyService.updateByExampleSelective(filterMappingAssembly, filterMappingAssemblyExample);
		}
		
		List<FilterMappingChildAssembly> filterMappingChildAssemblies = new ArrayList<FilterMappingChildAssembly>();
		for (int i = 0; i < conn.length; i++) {
			FilterMappingChildAssembly filterMappingChildAssembly = new FilterMappingChildAssembly();
			filterMappingChildAssembly.setId(Identities.uuid2());
			filterMappingChildAssembly.setBdpfMllibAssemblyFilterMappingId(id);
			filterMappingChildAssembly.setName(conn[i].split(";")[0]);
			filterMappingChildAssembly.setDataType(conn[i].split(";")[1]);
			filterMappingChildAssembly.setXh(i+1);
			filterMappingChildAssemblies.add(filterMappingChildAssembly);
		}
		
		int result = 0;
		
		if(filterMappingChildAssemblies.size()>0){
			result = filterMappingChildAssemblyService.batchInsertFilterMappingChildAssembly(filterMappingChildAssemblies);
		}
		
		map.put("result", result);
		map.put("id", id);
		
		return map;
	}
	
	/**
	 * 
	  * @Title: model_11_list
	  * @Description: 类型转换查询
	  *  @param expid
	  *  @param session
	  *  @return  Map<String,Object> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_11_list")
	@ResponseBody
	public Map<String,Object> model_11_list(String expid,String id,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		//获取当前登录用户信息
		User  user = (User)session.getAttribute("loginuser");
		if(user == null){
			return map;
		}
		String account = null;
		if(user.getUserType() != 2){
			account = user.getAccount();
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
			
			//已选字段
			List<ConvertAssembly> convertAssemblies = convertAssemblyService.queryConvertAssembly(id);
			List<ConvertAssembly> doubleList = new ArrayList<ConvertAssembly>();//转为double类型字段List
			List<ConvertAssembly> intList  = new ArrayList<ConvertAssembly>();//转为int类型字段List
			List<ConvertAssembly> stringList  = new ArrayList<ConvertAssembly>();//转为string类型字段List
			List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
			for (ConvertAssembly convertAssembly : convertAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(convertAssembly.getName());
				tableChild.setType(convertAssembly.getDataType());
				tableChild.setXh(convertAssembly.getConvertType());
				tableChildList.add(tableChild);
				if(convertAssembly.getConvertType()==0){//double类型
					doubleList.add(convertAssembly);
				}else if(convertAssembly.getConvertType()==1){//int类型
					intList.add(convertAssembly);
				}else{//sring类型
					stringList.add(convertAssembly);
				}
			}
			map.put("doubleList", doubleList);
			map.put("intList", intList);
			map.put("stringList", stringList);
			map.put("convertAssemblies", convertAssemblies);
			
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
	  * @Title: model_11_save
	  * @Description: 类型转换保存
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_11_save")
	@ResponseBody
	public Map<String,Object> model_11_save(String id,String[] conn,String convertType,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(id==null||"".equals(id)){
			return map;
		}
		//删除
		ConvertAssemblyExample convertAssemblyExample = new ConvertAssemblyExample();
		convertAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
		convertAssemblyService.deleteByExample(convertAssemblyExample);
		
		List<ConvertAssembly> convertAssemblies = new ArrayList<ConvertAssembly>();
		for (int i = 0; i < conn.length; i++) {
			ConvertAssembly convertAssembly = new ConvertAssembly();
			convertAssembly.setId(Identities.uuid2());
			convertAssembly.setBdpfMllibAssemblyId(id);
			convertAssembly.setName(conn[i].split(";")[0]);
			if("0".equals(convertType)){//string转double
				convertAssembly.setDataType("Double");
			}else if("1".equals(convertType)){//string转int
				convertAssembly.setDataType("Int");
			}else{//转string
				convertAssembly.setDataType("String");
			}
			convertAssembly.setXh(i+1);
			convertAssembly.setConvertType(Tools.stringToInt(convertType));
			convertAssemblies.add(convertAssembly);
		}
		
		int result = 0;

		if(convertAssemblies.size()>0){
			result = convertAssemblyService.batchInsertConvertAssembly(convertAssemblies);
		}
		
		map.put("result", result);
		map.put("id", id);
		
		return map;
	}
	
	/**
	 * 
	  * @Title: model_13_list
	  * @Description: 拆分查询
	  *  @param expid
	  *  @param session
	  *  @return  Map<String,Object> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_13_list")
	@ResponseBody
	public Map<String,Object> model_13_list(String id,String expid,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		//获取当前登录用户信息
		User  user = (User)session.getAttribute("loginuser");
		if(user == null){
			return map;
		}
		String account = null;
		if(user.getUserType() != 2){
			account = user.getAccount();
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
			map.put("table"+id, tableChilds);
			
			//拆分表信息
			SplitAssembly splitAssembly = splitAssemblyService.selectByPrimaryKey(id);
			map.put("splitAssembly", splitAssembly);
			session.removeAttribute("splitAssemblie");
			session.setAttribute("splitAssemblie", splitAssembly);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}
	
	/**
	 * 
	  * @Title: model_13_save
	  * @Description: 拆分保存
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_13_save")
	@ResponseBody
	public Map<String,Object> model_13_save(String id,String columnName,String columnValue,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(id==null||"".equals(id)){
			return map;
		}
		SplitAssembly splitAssembly = splitAssemblyService.selectByPrimaryKey(id);
		if(splitAssembly == null){//新增
			splitAssembly = new SplitAssembly();
		}
		if(columnValue!=null&&!"".equals(columnValue)){
			if("resolutionMode".equals(columnName)){
				splitAssembly.setResolutionMode(Tools.stringToInt(columnValue));//拆分方式
			}else if("segmentationRatio".equals(columnName)){
				splitAssembly.setSegmentationRatio(Tools.stringToDouble(columnValue));//切分比例
			}else if("random".equals(columnName)){
				splitAssembly.setRandom(Tools.stringToInt(columnValue));//随机种子数
			}else if("coreNumber".equals(columnName)){
				splitAssembly.setCoreNumber(Tools.stringToInt(columnValue));//计算核心数
			}else if("memory".equals(columnName)){
				splitAssembly.setMemory(Tools.stringToInt(columnValue));//每个核心内存
			}
		}
		int result = 0;
		if(splitAssembly.getId()==null||"".equals(splitAssembly.getId())){
			splitAssembly.setId(id);
			splitAssembly.setBdpfMllibAssemblyId(id);
			result = splitAssemblyService.insertSelective(splitAssembly);
		}else{
			SplitAssemblyExample splitAssemblyExample = new SplitAssemblyExample();
			splitAssemblyExample.createCriteria().andIdEqualTo(id);
			result = splitAssemblyService.updateByExampleSelective(splitAssembly, splitAssemblyExample);
		}
		map.put("result", result);
		map.put("id", id);
		
		return map;
	}
	
	/**
	 * 
	  * @Title: model_15_list
	  * @Description: 归一化查询
	  *  @param expid
	  *  @param session
	  *  @return  Map<String,Object> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_15_list")
	@ResponseBody
	public Map<String,Object> model_15_list(String expid,String id,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		//获取当前登录用户信息
		User  user = (User)session.getAttribute("loginuser");
		if(user == null){
			return map;
		}
		String account = null;
		if(user.getUserType() != 2){
			account = user.getAccount();
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
			
			//归一化信息
			NormalizationAssembly normalizationAssembly = normalizationAssemblyService.selectByPrimaryKey(id);
			map.put("normalizationAssembly", normalizationAssembly);
			
			//归一化字段信息
			List<NormalizationChildAssembly> normalizationChildAssemblies = null;
			if(normalizationAssembly!=null){
				NormalizationChildAssemblyExample normalizationChildAssemblyExample = new NormalizationChildAssemblyExample();
				normalizationChildAssemblyExample.createCriteria().andBdpfMllibAssemblyNormalizationIdEqualTo(id);
				normalizationChildAssemblyExample.setOrderByClause("xh");
				normalizationChildAssemblies = normalizationChildAssemblyService.selectByExample(normalizationChildAssemblyExample);
			}
			map.put("normalizationChildAssemblies", normalizationChildAssemblies);
			//遍历当前选中的字段信息
			List<TableChild> tableChildList = new ArrayList<TableChild>();
			if(normalizationChildAssemblies!=null){
				for (NormalizationChildAssembly normalizationChildAssembly : normalizationChildAssemblies) {
					TableChild tableChild = new TableChild();
					tableChild.setName(normalizationChildAssembly.getName());
					tableChild.setType(normalizationChildAssembly.getDataType());
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
	  * @Title: model_15_save
	  * @Description: 归一化保存
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_15_save")
	@ResponseBody
	public Map<String,Object> model_15_save(String id,String[] conn,String coreNumber,String memory,String state,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(id==null||"".equals(id)){
			return map;
		}
		//删除
		if(conn.length>0||"0".equals(state)){
			NormalizationChildAssemblyExample normalizationChildAssemblyExample = new NormalizationChildAssemblyExample();
			normalizationChildAssemblyExample.createCriteria().andBdpfMllibAssemblyNormalizationIdEqualTo(id);
			normalizationChildAssemblyService.deleteByExample(normalizationChildAssemblyExample);
		}
		
		if(coreNumber!=null||memory!=null){
			NormalizationAssemblyExample normalizationAssemblyExample = new NormalizationAssemblyExample();
			normalizationAssemblyExample.createCriteria().andIdEqualTo(id);
			normalizationAssemblyService.deleteByExample(normalizationAssemblyExample);
		}

		//保存操作
		if(coreNumber!=null||memory!=null){
			NormalizationAssembly normalizationAssembly = new NormalizationAssembly();
			normalizationAssembly.setId(id);
			normalizationAssembly.setBdpfMllibAssemblyId(id);
			normalizationAssembly.setCoreNumber(Tools.stringToInt(coreNumber));
			normalizationAssembly.setMemory(Tools.stringToInt(memory));
			normalizationAssemblyService.insertSelective(normalizationAssembly);
		}

		List<NormalizationChildAssembly> normalizationChildAssemblys = new ArrayList<NormalizationChildAssembly>();
		for (int i = 0; i < conn.length; i++) {
			NormalizationChildAssembly normalizationChildAssembly = new NormalizationChildAssembly();
			normalizationChildAssembly.setId(Identities.uuid2());
			normalizationChildAssembly.setBdpfMllibAssemblyNormalizationId(id);
			normalizationChildAssembly.setName(conn[i].split(";")[0]);
			normalizationChildAssembly.setDataType(conn[i].split(";")[1]);
			normalizationChildAssembly.setXh(i+1);
			normalizationChildAssemblys.add(normalizationChildAssembly);
		}

		int result = 0;

		if(normalizationChildAssemblys.size()>0){
			result = normalizationChildAssemblyService.batchInsertNormalizationChildAssembly(normalizationChildAssemblys);
		}

		map.put("result", result);
		map.put("id", id);

		return map;
	}

	/**
	 * 随机采样查询
	 * @param expid 实验id
	 * @param id 随机采样表主键
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "model_5_list")
	@ResponseBody
	public Object model_5_list(String expid,String id,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		User user = (User)session.getAttribute("loginuser");
		if (user == null)
			return map;
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
			//获取父组件选中字段信息
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);//

			RandomSamplingAssembly rsa = randomSamplingAssemblyService.selectByPrimaryKey(id);
			map.put("randomSamplingAssembly",rsa == null ? new RandomSamplingAssembly() : rsa);
			
			session.removeAttribute("randomSamplingAssembly");
			session.setAttribute("randomSamplingAssembly", rsa);


		}catch (Exception e){
			e.printStackTrace();
			log.error("随机采样查询异常"+e.getMessage(),e);
		}

		return map;
	}

	/**
	 * 随机采样编辑
	 * @param session
	 * @param o
	 * @return
	 */
	@RequestMapping(value = "model_5_save")
	@ResponseBody()
	public Object model_5_save(HttpSession session, RandomSamplingAssembly o){
		User user = (User) session.getAttribute("loginuser");
		Map<String,Object> map = new HashMap<>();
		if (null ==user)
			return new Feedback(false,"请重新登录",map);
		if (StringUtils.isBlank(o.getBdpfMllibAssemblyId()))
			return new Feedback(false,"缺少参数bdpfMllibAssemblyId",map);

		try {
			if (StringUtils.isEmpty(o.getId())) {
				o.setId(o.getBdpfMllibAssemblyId());
				randomSamplingAssemblyService.insertSelective(o);
				map.put("randomSamplingAssembly",o);
				//return new Feedback(true, "随机采样组件初始化完成", o);
			}else{
				randomSamplingAssemblyService.updateByPrimaryKey(o);
				map.put("randomSamplingAssembly",o);
//				return new Feedback(true,"随机采样参数设置完成",o);
			}
			return new Feedback(true,"随机采样参数设置完成",map);
		}catch(Exception e){
			e.printStackTrace();
			log.error("随机采样参数设置异常"+e.getMessage(),e);
			return new Feedback(false,"随机采样参数设置异常",map);
		}
	}



	/**
	 * 增加序号列查询
	 * @param expid 实验id
	 * @param id 增加序号列主键
	 * @param name 表名
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "model_12_list")
	@ResponseBody
	public Object model_12_list(String expid,String id,String name,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		User user = (User) session.getAttribute("loginuser");
		if (user == null)
			return map;
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
			
			//findTableChilds(map,assemblyService,user,expid);

			AddSerialNumAssembly asna = addSerialNumAssemblyService.selectByPrimaryKey(id);
			map.put("addSerialNumAssembly",asna == null ? new AddSerialNumAssembly() : asna);

			List<AddSerialNumChildAssembly> addSerialNumChildAssemblies = null;//子表信息
			if (null != asna){
				AddSerialNumChildAssemblyExample asnae = new AddSerialNumChildAssemblyExample();
				asnae.createCriteria().andBdpfMllibAssemblyAddSerialNumIdEqualTo(asna.getId());
				addSerialNumChildAssemblies = addSerialNumChildAssemblyService.selectByExample(asnae);

				List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
				for (AddSerialNumChildAssembly addSerialNumChildAssembly : addSerialNumChildAssemblies) {
					TableChild tableChild = new TableChild();
					tableChild.setName(addSerialNumChildAssembly.getColumnName());
					tableChild.setType(addSerialNumChildAssembly.getDataType());
					tableChildList.add(tableChild);
				}
				map.put("addSerialNumChildAssemblies",addSerialNumChildAssemblies);
				session.setAttribute("addSerialNumAssembly", asna);
				//将当前选中的字段存到缓存中
				session.removeAttribute("table"+id);
				session.setAttribute("table"+id, tableChildList);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("增加序号列查询失败"+e.getMessage(),e);
		}
		return map;
	}

	/**
	 * 增加序号列编辑
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="model_12_save")
	@ResponseBody
	public Object model_12_save(HttpSession session, AddSerialNumAssemblyVo vo){
		User user = (User) session.getAttribute("loginuser");
		Map<String,Object> map = new HashMap<>();
		if (null == user)
			return new Feedback(false,"请重新登录",map);
		if (StringUtils.isBlank(vo.getBdpfMllibAssemblyId()))
			return new Feedback(false,"缺少参数bdpfMllibAssemblyId",map);
		AddSerialNumAssembly asna = new AddSerialNumAssembly();

		try{
			asna.setId(vo.getId());
			asna.setBdpfMllibAssemblyId(vo.getBdpfMllibAssemblyId());
			asna.setCoreNumber(vo.getCoreNumber());
			asna.setMemory(vo.getMemory());
			asna.setSerialNum(vo.getSerialNum());
			if (StringUtils.isBlank(asna.getId())){
				asna.setId(vo.getBdpfMllibAssemblyId());
				addSerialNumAssemblyService.insertSelective(asna);
				map.put("addSerialNumAssembly",asna);
			}else {
				addSerialNumAssemblyService.updateByPrimaryKeySelective(asna);
				map.put("addSerialNumAssembly",asna);
				Feedback feedback = addSerialNumChildAssemblyService.save(vo);
				map.put("addSerialNumChildAssemblies",feedback.getData());
			}
			return new Feedback(true,"增加序号列信息设置完成",map);
		}catch(Exception e){
			e.printStackTrace();
			log.error("增加序号列信息设置异常"+e.getMessage(),e);
			return new Feedback(false,"增加序号列信息设置异常",map);
		}
	}

	/**
	 * 缺失值填充查询
	 * @param expid 实验id
	 * @param id 缺失值表主键
	 * @param name 表名
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "model_14_list")
	@ResponseBody
	public Object model_14_list(String expid,String id,String name,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		User user = (User) session.getAttribute("loginuser");
		if (user == null)
			return map;
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

			findTableChilds(map,assemblyService,user,expid);
			FillMissingValuesAssembly fmva = fillMissingValuesAssemblyService.selectByPrimaryKey(id);
			map.put("fillMissingValuesAssembly",fmva == null ? new FillMissingValuesAssembly() : fmva);

			List<FillMissingValuesChildAssembly> fmvcaList = null;//子表信息
			if(null != fmva){
				FillMissingValuesChildAssemblyExample fmvcae = new FillMissingValuesChildAssemblyExample();
				fmvcae.createCriteria().andBdpfMllibAssemblyFillMissingValuesIdEqualTo(fmva.getId());
				fmvcaList = fillMissingValuesChildAssemblyService.selectByExample(fmvcae);

				List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
				for (FillMissingValuesChildAssembly e : fmvcaList) {
					TableChild tableChild = new TableChild();
					tableChild.setName(e.getColumnName());
					tableChild.setType(e.getDataType());
					tableChildList.add(tableChild);
				}
				map.put("FillMissingValuesChildAssemblies",fmvcaList);
				session.setAttribute("fillMissingValuesAssembly", fmva);
				//将当前选中的字段存到缓存中
				session.removeAttribute("table"+id);
				session.setAttribute("table"+id, tableChildList);
			}

		}catch(Exception e){
			e.printStackTrace();
			log.error("缺失值填充查询异常"+e.getMessage(),e);
		}
		return map;
	}


	/**
	 * 缺失值填充编辑
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "model_14_save")
	@ResponseBody
	public Object model_14_save(HttpSession session, FillMissingValuesAssemblyVo vo){
		User user = (User) session.getAttribute("loginuser");
		Map<String,Object> map = new HashMap<>();
		if (null == user)
			return new Feedback(false,"请重新登录",map);
		if (StringUtils.isBlank(vo.getBdpfMllibAssemblyId()))
			return new Feedback(false,"缺少参数bdpfMllibAssemblyId",map);
		FillMissingValuesAssembly fmva = new FillMissingValuesAssembly();

		try{
			fmva.setId(vo.getId());
			fmva.setBdpfMllibAssemblyId(vo.getBdpfMllibAssemblyId());
			fmva.setOriginValue(vo.getOriginValue());
			fmva.setReplaceValue(vo.getReplaceValue());
			if (StringUtils.isBlank(fmva.getId())){
				fmva.setId(vo.getBdpfMllibAssemblyId());
				fillMissingValuesAssemblyService.insertSelective(fmva);
				map.put("fillMissingValuesAssembly",fmva);
			}else {
				fillMissingValuesAssemblyService.updateByPrimaryKeySelective(fmva);
				map.put("fillMissingValuesAssembly",fmva);
				Feedback feedback = fillMissingValuesChildAssemblyService.save(vo);
				map.put("FillMissingValuesChildAssemblies",feedback.getData());
			}
			return new Feedback(true,"缺失值填充信息设置完成",map);
		}catch(Exception e){
			e.printStackTrace();
			log.error("缺失值填充信息设置异常"+e.getMessage(),e);
			return new Feedback(false,"缺失值填充信息设置异常",map);
		}
	}

	private void findTableChilds (Map map,AssemblyService as,User user,String expid){
		AssemblyExample ae = new AssemblyExample();

		ae.createCriteria()
				.andBdpfMllibAssemblyLibraryIdEqualTo("3")//固定
				.andBdpfMllibExperimentIdEqualTo(expid)
				.andAccountEqualTo(user.getId());
		List<Assembly> dataSources = assemblyService.selectByExample(ae);
		List<TableChild> tableChildsLeft = new ArrayList<>();
		List<TableChild> tableChildsRight = new ArrayList<>();
		List<TableChild> tableChilds= new ArrayList<>();
		if (CollectionUtils.isEmpty(dataSources)){
			map.put("tableChildsLeft",tableChildsLeft);
			map.put("tableChildsRight",tableChildsRight);
			map.put("tableChilds",tableChilds);
		}else if (dataSources.size() > 2) {
			map.put("tableChildsLeft",tableChildsLeft);
			map.put("tableChildsRight",tableChildsRight);
			map.put("tableChilds",tableChilds);
		}else if (dataSources.size() == 2){
			Assembly e1 = dataSources.get(0);
			Assembly e2 = dataSources.get(1);
			if (e1.getXh() < e2.getXh()){//左表默认取最早拖拽进来的数据源
				tableChildsLeft = dataSourceService.queryTableChild(e1.getName(),user.getId());//左表所有字段信息
				tableChildsRight = dataSourceService.queryTableChild(e2.getName(),user.getId());
				map.put("tableChildsRight",tableChildsRight);
				map.put("tableChildsLeft",tableChildsLeft);
				map.put("tableChilds",tableChildsLeft);
			}else{
				tableChildsLeft = dataSourceService.queryTableChild(e2.getName(),user.getId());//左表所有字段信息
				tableChildsRight = dataSourceService.queryTableChild(e1.getName(),user.getId());
				map.put("tableChildsRight",tableChildsRight);
				map.put("tableChildsLeft",tableChildsLeft);
				map.put("tableChilds",tableChildsLeft);
			}
		}else{
			tableChilds = dataSourceService.queryTableChild(dataSources.get(0).getName(),user.getId());
			map.put("tableChildsRight",tableChildsRight);
			map.put("tableChildsLeft",tableChilds);
			map.put("tableChilds",tableChilds);
		}
	}
	/**
	 * 标准化查询
	 * @param expid 实验id
	 * @param id 标准化表主键
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "model_16_list")
	@ResponseBody
	public Object model_16_list(String expid,String id,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		User user = (User) session.getAttribute("loginuser");
		if (user == null)
			return map;
		if (StringUtils.isEmpty(id))
			return new Feedback(false,"缺少必要参数组件id");
		try{
			//findTableChilds(map,assemblyService,user,expid);
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

			StandardizationAssembly sa = standardizationAssemblyService.selectByPrimaryKey(id);
			map.put("standardizationAssembly",sa == null ? new AddSerialNumAssembly() : sa);

			List<StandardizationChildAssembly> standardizationChildAssemblies = null;
			if (null != sa){
				StandardizationChildAssemblyExample scae = new StandardizationChildAssemblyExample();
				scae.createCriteria().andBdpfMllibAssemblyStandardizationIdEqualTo(sa.getId());
				standardizationChildAssemblies = standardizationChildAssemblyService.selectByExample(scae);

				List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
				for (StandardizationChildAssembly e : standardizationChildAssemblies) {
					TableChild tableChild = new TableChild();
					tableChild.setName(e.getColumnName());
					tableChild.setType(e.getDataType());
					tableChildList.add(tableChild);
				}
				map.put("standardizationChildAssemblies",standardizationChildAssemblies);
				session.setAttribute("standardizationAssembly", sa);
				//将当前选中的字段存到缓存中
				session.removeAttribute("table"+id);
				session.setAttribute("table"+id, tableChildList);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("标准化查询失败"+e.getMessage(),e);
		}
		return map;
	}

	/**
	 * 标准化编辑
	 * @param session
	 * @param savo
	 * @return
	 */
	@RequestMapping(value = "model_16_save")
	@ResponseBody
	public Object model_16_save(HttpSession session, StandardizationAssemblyVo savo){
		User user = (User)session.getAttribute("loginuser");
		Map<String,Object> map = new HashMap<>();
		if (null == user)
			return new Feedback(false,"请重新登录",map);
		if (StringUtils.isBlank(savo.getBdpfMllibAssemblyId()))
			return new Feedback(false,"缺少参数bdpfMllibAssemblyId",map);
		StandardizationAssembly sa = new StandardizationAssembly();

		try {
			sa.setId(savo.getId());
			sa.setBdpfMllibAssemblyId(savo.getBdpfMllibAssemblyId());
			sa.setCoreNumber(savo.getCoreNumber());
			sa.setMemory(savo.getMemory());

			if (StringUtils.isBlank(sa.getId())) {
				sa.setId(savo.getBdpfMllibAssemblyId());
				standardizationAssemblyService.insertSelective(sa);
				map.put("standardizationAssembly",sa);
			}else {
				standardizationAssemblyService.updateByPrimaryKeySelective(sa);
				map.put("standardizationAssembly",sa);
				Feedback feedback = standardizationChildAssemblyService.save(savo);
				map.put("standardizationChildAssemblies",feedback.getData());
			}

			return new Feedback(true,"标准化组件信息设置成功",map);
		}catch(Exception e){
			e.printStackTrace();
			log.error("标准化组件信息保存异常"+e.getMessage(),e);
			return new Feedback(false,"标准化组件信息设置异常",map);
		}
	}

	/**
	 * JOIN查询
	 * @param expid 实验id
	 * @param id join表主键
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "model_9_list")
	@ResponseBody
	public Object model_9_list(String expid,String id,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		User user = (User) session.getAttribute("loginuser");
		if (user == null)
			return map;
		try{
			//findTableChilds(map,assemblyService,user,expid);
			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			String[] parentId = assembly.getParentId().split(",");
			String parentId_1,parentId_2 ;
			List<TableChild> tableChildsLeft = null;
			List<TableChild> tableChildsRight = null;
			if (parentId.length < 2){//一个数据源
				parentId_1 = parentId[0];//左
				tableChildsLeft = (List<TableChild>)session.getAttribute("table"+parentId_1);
			}else{//两个的
				parentId_1 = parentId[0];//左
				parentId_2 = parentId[1];//右
				//获取父组件选中字段信息
				tableChildsLeft = (List<TableChild>)session.getAttribute("table"+parentId_1);
				tableChildsRight = (List<TableChild>)session.getAttribute("table"+parentId_2);
			}

			map.put("tableChildsLeft",tableChildsLeft);
			map.put("tableChildsRight",tableChildsRight);

			JoinAssembly ja = joinAssemblyService.selectByPrimaryKey(id);
			map.put("joinAssembly",ja == null ? new JoinAssembly() :ja);

			List<JoinChildAssembly> jcaListLeft = null;//左表子表列表信息
			List<JoinChildAssembly> jcaListRight = null;
			if (null != ja){
				JoinChildAssemblyExample jcae = new JoinChildAssemblyExample();
				jcae.createCriteria()
						.andBdpfMllibAssemblyJoinIdEqualTo(ja.getId())
						.andOperationTypeEqualTo(1);
				JoinChildAssemblyExample jcae2 = new JoinChildAssemblyExample();
				jcae2.createCriteria()
						.andBdpfMllibAssemblyJoinIdEqualTo(ja.getId())
						.andOperationTypeEqualTo(3);
				jcaListLeft = joinChildAssemblyService.selectByExample(jcae);
				jcaListRight = joinChildAssemblyService.selectByExample(jcae2);

				JoinChildAssemblyExample o = new JoinChildAssemblyExample();
				o.createCriteria().andBdpfMllibAssemblyJoinIdEqualTo(id);
				List<JoinChildAssembly> joinChildAssemblies = joinChildAssemblyService.selectByExample(o);
				List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
				for (JoinChildAssembly e : joinChildAssemblies) {
					TableChild tableChild = new TableChild();
					tableChild.setName(e.getColumnIn());
					tableChild.setType(e.getDataType());
					tableChildList.add(tableChild);
				}
				map.put("joinChildAssemblies",joinChildAssemblies);
				session.setAttribute("joinAssembly", ja);
				//将当前选中的字段存到缓存中
				session.removeAttribute("table"+id);
				session.setAttribute("table"+id, tableChildList);
			}

			map.put("joinChildAssembliesLeft",jcaListLeft);
			map.put("joinChildAssembliesRight",jcaListRight);
		}catch(Exception e){
			e.printStackTrace();
			log.error("join查询异常"+e.getMessage(),e);
		}
		return map;
	}

	/**
	 * join组件编辑
	 * @param session
	 * @param javo
	 * @param bdpfMllibAssemblyId 组件id
	 * @return
	 */
	@RequestMapping(value = "model_9_save")
	@ResponseBody
	public Object model_9_save(HttpSession session, String expid,JoinAssemblyVo javo,String bdpfMllibAssemblyId){
		User user = (User)session.getAttribute("loginuser");
		Map<String,Object> map = new HashMap<>();
		if (null == user)
			return new Feedback(false,"请重新登录",map);
		if (StringUtils.isBlank(bdpfMllibAssemblyId))
			return new Feedback(false,"缺少参数bdpfMllibAssemblyId",map);
		JoinAssembly ja = new JoinAssembly();

		try {
			AssemblyExample ae = new AssemblyExample();
			ae.createCriteria()
					.andBdpfMllibAssemblyLibraryIdEqualTo("3")//固定
					.andBdpfMllibExperimentIdEqualTo(expid)
					.andAccountEqualTo(user.getId());
			List<Assembly> dataSources = assemblyService.selectByExample(ae);
			if (dataSources.size() > 2){
				return new Feedback(false,"join组件输入数据源数超过两个",dataSources);
			}else if (dataSources.size() == 2){
				Assembly e1 = dataSources.get(0);
				Assembly e2 = dataSources.get(1);
				if (e1.getXh() < e2.getXh()){//序号小的为左表
					ja.setBdpfMllibAssemblyLeftId(e1.getId());
					ja.setBdpfMllibAssemblyRightId(e2.getId());
				}else {
					ja.setBdpfMllibAssemblyLeftId(e2.getId());
					ja.setBdpfMllibAssemblyRightId(e1.getId());
				}
			}else if (dataSources.size() == 1){//左表
				ja.setBdpfMllibAssemblyLeftId(dataSources.get(0).getId());
			}
			ja.setId(javo.getId());
			ja.setJoinType(javo.getJoinType());
			ja.setJoinCondition(javo.getJoinCondition());
			if (StringUtils.isBlank(ja.getId())) {
				ja.setId(bdpfMllibAssemblyId);
				joinAssemblyService.insertSelective(ja);
				map.put("joinAssembly",ja);
			}else {
				joinAssemblyService.updateByPrimaryKeySelective(ja);
				map.put("joinAssembly",ja);
				Feedback feedback = joinChildAssemblyService.save(javo);
				map.put("joinChildAssemblies",feedback.getData());
			}

			return new Feedback(true,"join组件信息设置成功",map);
		}catch(Exception e){
			e.printStackTrace();
			log.error("join组件信息保存异常"+e.getMessage(),e);
			return new Feedback(false,"join组件信息设置异常",map);
		}
	}

	/**
	 * UNION查询
	 * @param expid 实验id
	 * @param id union表主键
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "model_17_list")
	@ResponseBody
	public Object model_17_list(String expid,String id,HttpSession session){
		Map<String,Object> map = new HashMap<>();
		User user = (User) session.getAttribute("loginuser");
		if (user == null)
			return map;
		try{
			//findTableChilds(map,assemblyService,user,expid);

			//获取当前组件信息
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly == null){
				return map;
			}
			String[] parentId = assembly.getParentId().split(",");
			String parentId_1,parentId_2 ;
			List<TableChild> tableChildsLeft = null;
			List<TableChild> tableChildsRight = null;
			if (parentId.length < 2){//一个数据源
				parentId_1 = parentId[0];//左
				tableChildsLeft = (List<TableChild>)session.getAttribute("table"+parentId_1);
			}else{//两个的
				parentId_1 = parentId[0];//左
				parentId_2 = parentId[1];//右
				//获取父组件选中字段信息
				tableChildsLeft = (List<TableChild>)session.getAttribute("table"+parentId_1);
				tableChildsRight = (List<TableChild>)session.getAttribute("table"+parentId_2);
			}

			map.put("tableChildsLeft",tableChildsLeft);
			map.put("tableChildsRight",tableChildsRight);

			UnionAssembly ua = unionAssemblyService.selectByPrimaryKey(id);
			map.put("unionAssembly",ua == null ? new UnionAssembly() :ua);

			List<UnionChildAssembly> ucaListLeft = null;//左表子表列表信息
			List<UnionChildAssembly> ucaListRight = null;
			if (null != ua){
				UnionChildAssemblyExample ucae = new UnionChildAssemblyExample();
				ucae.createCriteria()
						.andBdpfMllibAssemblyUnionIdEqualTo(ua.getId())
						.andOperationTypeEqualTo(0);
				UnionChildAssemblyExample ucae2 = new UnionChildAssemblyExample();
				ucae2.createCriteria()
						.andBdpfMllibAssemblyUnionIdEqualTo(ua.getId())
						.andOperationTypeEqualTo(1);
				ucaListLeft = unionChildAssemblyService.selectByExample(ucae);
				ucaListRight = unionChildAssemblyService.selectByExample(ucae2);

				UnionChildAssemblyExample o = new UnionChildAssemblyExample();
				o.createCriteria().andBdpfMllibAssemblyUnionIdEqualTo(id);
				List<UnionChildAssembly> unionChildAssemblies = unionChildAssemblyService.selectByExample(o);
				List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
				for (UnionChildAssembly e : unionChildAssemblies) {
					TableChild tableChild = new TableChild();
					tableChild.setName(e.getColumnIn());
					tableChild.setType(e.getDataType());
					tableChildList.add(tableChild);
				}
				map.put("unionChildAssemblies",unionChildAssemblies);
				session.setAttribute("UnionAssembly", ua);
				//将当前选中的字段存到缓存中
				session.removeAttribute("table"+id);
				session.setAttribute("table"+id, tableChildList);
			}

			map.put("unionChildAssembliesLeft",ucaListLeft);
			map.put("unionChildAssembliesRight",ucaListRight);
		}catch(Exception e){
			e.printStackTrace();
			log.error("union查询异常"+e.getMessage(),e);
		}
		return map;
	}

	/**
	 * UNION 组件编辑
	 * @param session
	 * @param uavo
	 * @param bdpfMllibAssemblyId 组件id
	 * @return
	 */
	@RequestMapping(value = "model_17_save")
	@ResponseBody
	public Object model_17_save(HttpSession session,String expid, UnionAssemblyVo uavo,String bdpfMllibAssemblyId){
		User user = (User)session.getAttribute("loginuser");
		Map<String,Object> map = new HashMap<>();
		if (null == user)
			return new Feedback(false,"请重新登录",map);
		if (StringUtils.isBlank(bdpfMllibAssemblyId))
			return new Feedback(false,"缺少参数bdpfMllibAssemblyId",map);
		UnionAssembly ua = new UnionAssembly();

		try {
			AssemblyExample ae = new AssemblyExample();
			ae.createCriteria()
					.andBdpfMllibAssemblyLibraryIdEqualTo("3")//固定
					.andBdpfMllibExperimentIdEqualTo(expid)
					.andAccountEqualTo(user.getId());
			List<Assembly> dataSources = assemblyService.selectByExample(ae);
			if (dataSources.size() > 2){
				return new Feedback(false,"union组件输入数据源数超过两个",dataSources);
			}else if (dataSources.size() == 2){
				Assembly e1 = dataSources.get(0);
				Assembly e2 = dataSources.get(1);
				if (e1.getXh() < e2.getXh()){//序号小的为左表
					ua.setBdpfMllibAssemblyIdLeft(e1.getId());
					ua.setBdpfMllibAssemblyIdRight(e2.getId());
				}else {
					ua.setBdpfMllibAssemblyIdLeft(e2.getId());
					ua.setBdpfMllibAssemblyIdRight(e1.getId());
				}
			}else if (dataSources.size() == 1){//左表
				ua.setBdpfMllibAssemblyIdLeft(dataSources.get(0).getId());
			}
			ua.setId(uavo.getId());
			ua.setDeduplication(uavo.getDeduplication());
			ua.setWhereConditionLeft(uavo.getWhereConditionLeft());
			ua.setWhereConditionRight(uavo.getWhereConditionRight());
			if (StringUtils.isBlank(ua.getId())) {
				ua.setId(bdpfMllibAssemblyId);
				unionAssemblyService.insertSelective(ua);
				map.put("unionAssembly",ua);
			}else {
				unionAssemblyService.updateByPrimaryKeySelective(ua);
				map.put("unionAssembly",ua);
				Feedback feedback = unionChildAssemblyService.save(uavo);
				map.put("unionChildAssemblies",feedback.getData());
			}

			return new Feedback(true,"union组件信息设置成功",map);
		}catch(Exception e){
			e.printStackTrace();
			log.error("union组件信息保存异常"+e.getMessage(),e);
			return new Feedback(false,"union组件信息设置异常",map);
		}
	}


}