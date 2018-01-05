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

import com.xunfang.bdpf.mllib.assembly.entity.Assembly;
import com.xunfang.bdpf.mllib.assembly.entity.AssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.SqlAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.SqlAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.service.AssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.LibraryAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.SqlAssemblyService;
import com.xunfang.bdpf.mllib.experiment.entity.Experiment;
import com.xunfang.bdpf.mllib.experiment.entity.Table;
import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
import com.xunfang.bdpf.mllib.experiment.service.DataSourceService;
import com.xunfang.bdpf.mllib.experiment.service.ExperimentService;
import com.xunfang.bdpf.mllib.experiment.vo.TableChildVo;
import com.xunfang.bdpf.mllib.experiment.vo.TableVo;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Feedback;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @ClassName SourceTargetController
 * @Description: 源/目标控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月9日 下午5:18:42
 * @version V1.0
 */
@Controller
@RequestMapping(value = "sourcetarget")
public class SourceTargetController {
	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(SourceTargetController.class);
	
	@Autowired
	private ExperimentService  experimentService;

	@Autowired
	private LibraryAssemblyService libraryAssemblyService;
	
	@Autowired
	private AssemblyService assemblyService;
	
	@Autowired
	private DataSourceService dataSourceService;
	
	@Autowired
	private SqlAssemblyService sqlAssemblyService;
	
	/**
	 * 
	  * @Title: model_3_list
	  * @Description: 读数据库表查询
	  *  @param expid
	  *  @param session
	  *  @return  Map<String,Object> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_3_list")
	@ResponseBody
	public Map<String,Object> model_3_list(String expid,String id,HttpSession session) {
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
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			if(assembly==null){
				return map;
			}
			//所有字段
			List<TableChild> tableChilds = dataSourceService.queryTableChild(assembly.getName(),user.getId());
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
			//表名
			session.removeAttribute("tableName");
			session.setAttribute("tableName", assembly.getName());
			map.put("assembly", assembly);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}
	/**
	 * 用户表名信息初始化接口
	 * @param session
	 * @return 
	 */
	@RequestMapping(value="initTables")
	@ResponseBody
	public Object initTables(HttpSession session){
		log.info("- - - - - - - SourceTargetController initTables in - - - - - -");
		List<Table> tables = new ArrayList<>();
		try{
			User user = (User)session.getAttribute("loginuser");
			if(user == null) return tables; 
			TableVo vo = new TableVo();
			vo.setAccount(user.getId());
			tables = dataSourceService.findTables(vo);
			return tables;
		}catch(Exception e){
			log.error("- - - - - - - SourceTargetController initTables error - - - - - -"+e.getMessage());
			e.printStackTrace();
			return tables;
		}
		
	}
	@RequestMapping(value="tableData")
	@ResponseBody
	public Object findTableData(TableVo vo,HttpSession session){
		log.info("- - - - - - - SourceTargetController findTableData in - - - - - -");
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			User user = (User)session.getAttribute("loginuser");
			if(user == null){
				return new Feedback(false,"未登录或登录超时");
			}
			List<TableChildVo> tcvList = new ArrayList<TableChildVo>();
			//如果默认为“读数据库”,
			if(!"读数据库".equals(vo.getName())){
				vo.setAccount(user.getId());
				tcvList = dataSourceService.findTableData(vo,session);
				if(tcvList==null||tcvList.size()==0){
					return new Feedback(true,"表不存在！",map);
				}
			}else{
				return new Feedback(false,"请填写数据库表名！");
			}
			
			//更新组件库表数据
			Assembly assembly = assemblyService.selectByPrimaryKey(vo.getId());
			
		    //更新实验表数据
			Experiment experiment = experimentService.selectByPrimaryKey(vo.getPath());
			if(experiment != null&&assembly != null&&!assembly.getName().equals(vo.getName())){
				JSONArray jsarray = JSONArray.fromObject(experiment.getMainarr());
				for(int index = 0; index < jsarray.size(); index++){
					JSONObject json=new JSONObject(); 
					json=jsarray.getJSONObject(index);
					if(json.getString("text").equals(assembly.getName())){
						json.remove("text");
						json.accumulate("text", vo.getName());
					}
				}
				String mainarr = jsarray.toString();
				experiment.setMainarr(mainarr);
				assembly.setName(vo.getName());
				experimentService.updateByPrimaryKeySelective(experiment);
				assemblyService.updateByPrimaryKeySelective(assembly);
			}
			map.put("assembly", assembly);
			map.put("experiment", experiment);
			map.put("tcvList", tcvList);
			
			return new Feedback(true,"表信息获取成功",map);
		}catch(Exception e){
			log.error("- - - - - - - SourceTargetController findTableData error - - - - - -"+e.getMessage());
			e.printStackTrace();
			return new Feedback(false,"表信息获取失败",map);
		}
	}
	
	/**
	 * 
	  * @Title: model_91_list
	  * @Description: SQL脚本查询
	  *  @param expid
	  *  @param session
	  *  @return  Map<String,Object> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_91_list")
	@ResponseBody
	public Map<String,Object> model_91_list(String expid,String id,HttpSession session) {
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
			//获取所有的数据源
			AssemblyExample assemblyExample = new AssemblyExample();
			assemblyExample.createCriteria().andAccountEqualTo(user.getId()).andBdpfMllibExperimentIdEqualTo(expid).andBdpfMllibAssemblyLibraryIdEqualTo("3");
			List<Assembly> assemblies = assemblyService.selectByExample(assemblyExample);
			map.put("assemblies", assemblies);
			//所有字段
			List<TableChild> tableChilds;
			if(assemblies!=null&&assemblies.size()==1){//一个数据源
				tableChilds = dataSourceService.queryTableChild(assemblies.get(0).getName(),account);
				session.removeAttribute("table"+id);
				session.setAttribute("table"+id, tableChilds);
			}else{//多个数据源
				for (Assembly assembly : assemblies) {
					tableChilds = dataSourceService.queryTableChild(assembly.getName(),account);
					session.removeAttribute("table"+assembly.getId());
					session.setAttribute("table"+assembly.getId(), tableChilds);
				}
			}
			
			
			SqlAssembly sqlAssembly  = sqlAssemblyService.selectByPrimaryKey(id);
			map.put("sqlAssembly", sqlAssembly);
			//SQL脚本存入缓存中
			session.removeAttribute("sqlAssembly");
			session.setAttribute("sqlAssembly", sqlAssembly);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}
	
	/**
	 * 
	  * @Title: model_91_save
	  * @Description: SQL脚本保存
	  * @param id String 组件主键ID
	  * @param sqlScript String 脚本内容
	  * @param session HttpSession http缓存
	  * @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "model_91_save")
	@ResponseBody
	public Map<String,Object> model_91_save(String id,String sqlScript,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(id==null||"".equals(id)){
			return map;
		}
		int result = 0;
		SqlAssembly sqlAssembly = sqlAssemblyService.selectByPrimaryKey(id);
		if(sqlAssembly==null){
			sqlAssembly = new SqlAssembly();
			sqlAssembly.setId(id);
			sqlAssembly.setBdpfMllibAssemblyId(id);
			//sql脚本内容
			sqlAssembly.setSqlScript(sqlScript);
			result = sqlAssemblyService.insertSelective(sqlAssembly);
		}else{
			//sql脚本内容
			sqlAssembly.setSqlScript(sqlScript);
			SqlAssemblyExample sqlAssemblyExample = new SqlAssemblyExample();
			sqlAssemblyExample.createCriteria().andIdEqualTo(id);
			result = sqlAssemblyService.updateByExampleSelective(sqlAssembly, sqlAssemblyExample);
		}
		
		map.put("result", result);
		map.put("id", id);
		
		return map;
	}
}