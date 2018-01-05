package com.xunfang.bdpf.mllib.assembly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.Assembly;
import com.xunfang.bdpf.mllib.assembly.entity.AssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.TextAnalysisAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.TextAnalysisAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.service.AssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.TextAnalysisAssemblyService;
import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
import com.xunfang.bdpf.mllib.experiment.service.DataSourceService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Identities;

/**
 * 
 * @ClassName TextAnalysisController
 * @Description: 文本分析控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年11月7日 上午10:34:29
 * @version V1.0
 */
@Controller
@RequestMapping(value = "textanalysis")
public class TextAnalysisController {
	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(TextAnalysisController.class);

	@Autowired
	private DataSourceService dataSourceService;

	@Autowired
	private TextAnalysisAssemblyService textAnalysisAssemblyService;

	@Autowired
	private AssemblyService assemblyService;

	/**
	 * 
	  * @Title: model_84_list
	  * @Description: 停用词过滤查询
	  *  @param expid
	  *  @param session
	  *  @return  Map<String,Object> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_84_list")
	@ResponseBody
	public Map<String,Object> model_84_list(String expid,String id,HttpSession session) {
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
			AssemblyExample assemblyExample = new AssemblyExample();
			assemblyExample.createCriteria().andAccountEqualTo(user.getId()).andBdpfMllibExperimentIdEqualTo(expid).andParentIdIsNull();
			List<Assembly> assemblies = assemblyService.selectByExample(assemblyExample);
			String tableName = "";
			if(assemblies.size()==1){//说明有一个数据源
				tableName = assemblies.get(0).getName();
			}else if(assemblies.size()>1){//说明有多个数据源
				//TODO暂不处理
			}

			if("".equals(tableName)){
				return map;
			}

			//所有字段
			List<TableChild> tableChilds = dataSourceService.queryTableChild(tableName,user.getId());
			AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
			if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
				if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
					TableChild tableChild = new TableChild();
					tableChild.setName(addSerialNumAssembly.getSerialNum());
					tableChild.setType("Int");
					tableChilds.add(tableChild);
				}
			}
			map.put("tableChilds", tableChilds);
			//已选字段
			TextAnalysisAssemblyExample textAnalysisAssemblyExample = new TextAnalysisAssemblyExample();
			textAnalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
			List<TextAnalysisAssembly> textAnalysisAssemblies = textAnalysisAssemblyService.selectByExample(textAnalysisAssemblyExample);
			List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
			for (TextAnalysisAssembly e : textAnalysisAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(e.getFilterColumn());
				tableChild.setType(e.getFilterType());
				tableChildList.add(tableChild);
			}
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
			map.put("textAnalysisAssemblies", textAnalysisAssemblies);
			session.setAttribute("textAnalysisAssemblies", textAnalysisAssemblies);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}
	
	/**
	 * 
	  * @Title: model_84_save
	  * @Description: 停用词过滤保存
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_84_save")
	@ResponseBody
	public Map<String,Object> model_84_save(String id,String[] conn,String convertType,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(id==null||"".equals(id)){
			return map;
		}
		//删除
		TextAnalysisAssemblyExample textAnalysisAssemblyExample = new TextAnalysisAssemblyExample();
		textAnalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
		textAnalysisAssemblyService.deleteByExample(textAnalysisAssemblyExample);
		
		List<TextAnalysisAssembly> textAnalysisAssemblies = new ArrayList<TextAnalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			TextAnalysisAssembly textAnalysisAssembly = new TextAnalysisAssembly();
			textAnalysisAssembly.setId(Identities.uuid2());
			textAnalysisAssembly.setBdpfMllibAssemblyId(id);
			textAnalysisAssembly.setFilterColumn(conn[i].split(";")[0]);
			textAnalysisAssembly.setFilterType(conn[i].split(";")[1]);
			textAnalysisAssembly.setXh(i+1);
			textAnalysisAssemblies.add(textAnalysisAssembly);
		}
		
		int result = 0;

		if(textAnalysisAssemblies.size()>0){
			result = textAnalysisAssemblyService.batchInsertTextAnalysisAssembly(textAnalysisAssemblies);
		}
		
		map.put("result", result);
		map.put("id", id);
		
		return map;
	}
	
	/**
	 * 
	  * @Title: model_87_list
	  * @Description: Split Word查询
	  *  @param expid
	  *  @param session
	  *  @return  Map<String,Object> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_87_list")
	@ResponseBody
	public Map<String,Object> model_87_list(String expid,String id,HttpSession session) {
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
			AssemblyExample assemblyExample = new AssemblyExample();
			assemblyExample.createCriteria().andAccountEqualTo(user.getId()).andBdpfMllibExperimentIdEqualTo(expid).andParentIdIsNull();
			List<Assembly> assemblies = assemblyService.selectByExample(assemblyExample);
			String tableName = "";
			if(assemblies.size()==1){//说明有一个数据源
				tableName = assemblies.get(0).getName();
			}else if(assemblies.size()>1){//说明有多个数据源
				//TODO暂不处理
			}

			if("".equals(tableName)){
				return map;
			}

			//所有字段
			List<TableChild> tableChilds = dataSourceService.queryTableChild(tableName,user.getId());
			AddSerialNumAssembly addSerialNumAssembly = (AddSerialNumAssembly)session.getAttribute("addSerialNumAssembly");
			if(addSerialNumAssembly!=null&&addSerialNumAssembly.getSerialNum()!=null){
				if(!tableChilds.contains(addSerialNumAssembly.getSerialNum())){
					TableChild tableChild = new TableChild();
					tableChild.setName(addSerialNumAssembly.getSerialNum());
					tableChild.setType("Int");
					tableChilds.add(tableChild);
				}
			}
			map.put("tableChilds", tableChilds);
			//已选字段
			TextAnalysisAssemblyExample textAnalysisAssemblyExample = new TextAnalysisAssemblyExample();
			textAnalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
			List<TextAnalysisAssembly> textAnalysisAssemblies = textAnalysisAssemblyService.selectByExample(textAnalysisAssemblyExample);
			map.put("textAnalysisAssemblies", textAnalysisAssemblies);
			
			List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
			for (TextAnalysisAssembly e : textAnalysisAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(e.getFilterColumn());
				tableChild.setType(e.getFilterType());
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
	  * @Title: model_87_save
	  * @Description: Split Word保存
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_87_save")
	@ResponseBody
	public Map<String,Object> model_87_save(String id,String[] conn,String convertType,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(id==null||"".equals(id)){
			return map;
		}
		//删除
		TextAnalysisAssemblyExample textAnalysisAssemblyExample = new TextAnalysisAssemblyExample();
		textAnalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
		textAnalysisAssemblyService.deleteByExample(textAnalysisAssemblyExample);
		
		List<TextAnalysisAssembly> textAnalysisAssemblies = new ArrayList<TextAnalysisAssembly>();
		for (int i = 0; i < conn.length; i++) {
			TextAnalysisAssembly textAnalysisAssembly = new TextAnalysisAssembly();
			textAnalysisAssembly.setId(Identities.uuid2());
			textAnalysisAssembly.setBdpfMllibAssemblyId(id);
			textAnalysisAssembly.setFilterColumn(conn[i].split(";")[0]);
			textAnalysisAssembly.setFilterType(conn[i].split(";")[1]);
			textAnalysisAssembly.setXh(i+1);
			textAnalysisAssemblies.add(textAnalysisAssembly);
		}
		
		int result = 0;

		if(textAnalysisAssemblies.size()>0){
			result = textAnalysisAssemblyService.batchInsertTextAnalysisAssembly(textAnalysisAssemblies);
		}
		
		map.put("result", result);
		map.put("id", id);
		
		return map;
	}
}
