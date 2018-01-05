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
import com.xunfang.bdpf.mllib.assembly.entity.Assembly;
import com.xunfang.bdpf.mllib.assembly.entity.AssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.service.AssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FeatureengineeringAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FeatureengineeringChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.LibraryAssemblyService;
import com.xunfang.bdpf.mllib.assembly.vo.FeatureEngineeringAssemblyVo;
import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
import com.xunfang.bdpf.mllib.experiment.service.DataSourceService;
import com.xunfang.bdpf.mllib.experiment.service.ExperimentService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Identities;

/**
 * 
 * @ClassName FeatureEngineeringController
 * @Description: 特征工程控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月9日 下午5:18:42
 * @version V1.0
 */
@Controller
@RequestMapping(value = "featureengineering")
public class FeatureEngineeringController {
	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(FeatureEngineeringController.class);
	
	@Autowired
	private ExperimentService  experimentService;

	@Autowired
	private LibraryAssemblyService libraryAssemblyService;
	
	@Autowired
	private AssemblyService assemblyService;
	@Autowired
	private DataSourceService dataSourceService;
	@Autowired
	private FeatureengineeringAssemblyService featureengineeringAssemblyService;

	@Autowired
	private FeatureengineeringChildAssemblyService featureengineeringChildAssemblyService;


	/**
	 * 过滤式特征选择查询
	 * @param session
	 * @param id
	 * @param expid
	 * @return
	 */
	@RequestMapping(value = "model_28_list")
	@ResponseBody
	public Object model_28_list(HttpSession session,String id,String expid){
		Map<String,Object> map = new HashMap<>();
		User user = (User) session.getAttribute("loginuser");
		if (user == null)
			return map;
		if (StringUtils.isEmpty(id))
			return new Feedback(false,"缺少必要参数组件id");

		try{
			//findTableChilds(map,user,expid);
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

			FeatureengineeringAssembly fa = featureengineeringAssemblyService.selectByPrimaryKey(id);
			map.put("featureengineeringAssembly",fa == null ? new FeatureengineeringAssembly() : fa);

			List<FeatureengineeringChildAssembly> featureengineeringChildAssemblies = null;
			if (null != fa){
				FeatureengineeringChildAssemblyExample fcae = new FeatureengineeringChildAssemblyExample();
				fcae.createCriteria().andBdpfMllibAssemblyFeatureengineeringIdEqualTo(fa.getId());
				featureengineeringChildAssemblies = featureengineeringChildAssemblyService.selectByExample(fcae);


				List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
				for (FeatureengineeringChildAssembly e : featureengineeringChildAssemblies) {
					TableChild tableChild = new TableChild();
					tableChild.setName(e.getName());
					tableChild.setType(e.getDataType());
					tableChildList.add(tableChild);
				}
				map.put("featureengineeringChildAssemblies",featureengineeringChildAssemblies);
				session.setAttribute("featureengineeringAssembly", fa);
				//将当前选中的字段存到缓存中
				session.removeAttribute("table"+id);
				session.setAttribute("table"+id, tableChildList);
			}
		}catch (Exception e){
			e.printStackTrace();
			log.error("过滤式特征选择查询失败"+e.getMessage(),e);
		}
		return map;
	}

	@RequestMapping(value = "model_28_save")
	@ResponseBody
	public Object model_28_save(HttpSession session,FeatureEngineeringAssemblyVo feav){
		User user = (User)session.getAttribute("loginuser");
		Map<String,Object> map = new HashMap<>();
		if (null == user)
			return new Feedback(false,"请重新登录",map);
		if (StringUtils.isBlank(feav.getBdpfMllibAssemblyId()))
			return new Feedback(false,"缺少参数bdpfMllibAssemblyId",map);
		FeatureengineeringAssembly fa = new FeatureengineeringAssembly();

		try {
			fa.setId(feav.getId());
			fa.setBdpfMllibAssemblyId(feav.getBdpfMllibAssemblyId());
			map.put("featureengineeringAssembly",fa);
			if (StringUtils.isBlank(fa.getId())) {
				fa.setId(feav.getBdpfMllibAssemblyId());
				featureengineeringAssemblyService.insertSelective(fa);
				map.put("featureengineeringAssembly",fa);
			}
			feav.setId(fa.getId());
			Feedback feedback = featureengineeringChildAssemblyService.save(feav);
			map.put("featureengineeringChildAssemblies",feedback.getData());

			return new Feedback(true,"过滤式特征选择设置成功",map);
		}catch(Exception e){
			e.printStackTrace();
			log.error("过滤式特征选择保存异常"+e.getMessage(),e);
			return new Feedback(false,"过滤式特征选择信息设置异常",map);
		}
	}

	/**
	 * 特征重要性查询
	 * @param session
	 * @param id
	 * @param expid
	 * @return
	 */
	@RequestMapping(value = "model_26_list")
	@ResponseBody
	public Object model_26_list(HttpSession session,String id,String expid){
		Map<String,Object> map = new HashMap<>();
		User user = (User) session.getAttribute("loginuser");
		if (user == null)
			return map;
		if (StringUtils.isEmpty(id))
			return new Feedback(false,"缺少必要参数组件id");
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

			FeatureengineeringAssembly fa = featureengineeringAssemblyService.selectByPrimaryKey(id);
			map.put("featureengineeringAssembly",fa == null ? new FeatureengineeringAssembly() : fa);


			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}catch (Exception e){
			e.printStackTrace();
			log.error("特征重要性查询失败"+e.getMessage(),e);
		}

		return map;
	}

	@RequestMapping(value = "model_26_save")
	@ResponseBody
	public Object model_26_save(HttpSession session,FeatureEngineeringAssemblyVo feav){
		return "";
	}

	/**
	 * 主成分分析查询
	 * @param session
	 * @param id
	 * @param expid
	 * @return
	 */
	@RequestMapping(value = "model_25_list")
	@ResponseBody
	public Object model_25_list(HttpSession session,String id,String expid){
		Map<String,Object> map = new HashMap<>();
		User user = (User) session.getAttribute("loginuser");
		if (user == null)
			return map;
		if (StringUtils.isEmpty(id))
			return new Feedback(false,"缺少必要参数组件id");
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

			FeatureengineeringAssembly fa = featureengineeringAssemblyService.selectByPrimaryKey(id);
			map.put("featureengineeringAssembly",fa == null ? new FeatureengineeringAssembly() : fa);

			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}catch (Exception e){
			e.printStackTrace();
			log.error("主成分分析查询失败"+e.getMessage(),e);
		}

		return map;
	}

	@RequestMapping(value = "model_25_save")
	@ResponseBody
	public Object model_25_save(HttpSession session,FeatureEngineeringAssemblyVo feav){
		User user = (User)session.getAttribute("loginuser");
		Map<String,Object> map = new HashMap<>();
		if (null == user)
			return new Feedback(false,"请重新登录",map);
		if (StringUtils.isBlank(feav.getBdpfMllibAssemblyId()))
			return new Feedback(false,"缺少参数bdpfMllibAssemblyId",map);
		FeatureengineeringAssembly fa = new FeatureengineeringAssembly();

		try {
			fa.setId(feav.getId());
			fa.setBdpfMllibAssemblyId(feav.getBdpfMllibAssemblyId());
			fa.setK(feav.getK());

			if (StringUtils.isBlank(fa.getId())) {
				fa.setId(feav.getBdpfMllibAssemblyId());
				featureengineeringAssemblyService.insertSelective(fa);
				map.put("featureengineeringAssembly",fa);
			}else {
				featureengineeringAssemblyService.updateByPrimaryKeySelective(fa);
				map.put("featureengineeringAssembly",fa);
			}

			return new Feedback(true,"主成分分析信息设置成功",map);
		}catch(Exception e){
			e.printStackTrace();
			log.error("主成分分析信息保存异常"+e.getMessage(),e);
			return new Feedback(false,"主成分分析信息设置异常",map);
		}
	}

	/**
	 * 特征离散查询
	 * @param session
	 * @param expid
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "model_24_list")
	@ResponseBody
	public Object model_24_list(HttpSession session,String expid,String id){
		Map<String,Object> map = new HashMap<>();
		User user = (User) session.getAttribute("loginuser");
		if (user == null)
			return map;
		if (StringUtils.isEmpty(id))
			return new Feedback(false,"缺少必要参数组件id");

		try{
			//findTableChilds(map,user,expid);
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

			FeatureengineeringAssembly fa = featureengineeringAssemblyService.selectByPrimaryKey(id);
			map.put("featureengineeringAssembly",fa == null ? new FeatureengineeringAssembly() : fa);

			List<FeatureengineeringChildAssembly> featureengineeringChildAssemblies = null;
			if (null != fa){
				FeatureengineeringChildAssemblyExample fcae = new FeatureengineeringChildAssemblyExample();
				fcae.createCriteria().andBdpfMllibAssemblyFeatureengineeringIdEqualTo(fa.getId());
				featureengineeringChildAssemblies = featureengineeringChildAssemblyService.selectByExample(fcae);

				List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
				for (FeatureengineeringChildAssembly e : featureengineeringChildAssemblies) {
					TableChild tableChild = new TableChild();
					tableChild.setName(e.getName());
					tableChild.setType(e.getDataType());
					tableChildList.add(tableChild);
				}
				map.put("featureengineeringChildAssemblies",featureengineeringChildAssemblies);
				session.setAttribute("featureengineeringAssembly", fa);
				//将当前选中的字段存到缓存中
				session.removeAttribute("table"+id);
				session.setAttribute("table"+id, tableChildList);
			}

		}catch (Exception e){
			e.printStackTrace();
			log.error("特征离散查询失败"+e.getMessage(),e);
		}
		return map;
	}

	@RequestMapping(value = "model_24_save")
	@ResponseBody
	public Object model_24_save(HttpSession session, FeatureEngineeringAssemblyVo feav){
		User user = (User)session.getAttribute("loginuser");
		Map<String,Object> map = new HashMap<>();
		if (null == user)
			return new Feedback(false,"请重新登录",map);
		if (StringUtils.isBlank(feav.getBdpfMllibAssemblyId()))
			return new Feedback(false,"缺少参数bdpfMllibAssemblyId",map);
		FeatureengineeringAssembly fa = new FeatureengineeringAssembly();

		try {
			fa.setId(feav.getId());
			fa.setBdpfMllibAssemblyId(feav.getBdpfMllibAssemblyId());
			fa.setEquidistantDispersion(feav.getEquidistantDispersion());
			fa.setEquidistantDispersionInterval(feav.getEquidistantDispersionInterval());

			if (StringUtils.isBlank(fa.getId())) {
				fa.setId(feav.getBdpfMllibAssemblyId());
				featureengineeringAssemblyService.insertSelective(fa);
				map.put("featureengineeringAssembly",fa);
			}else {
				featureengineeringAssemblyService.updateByPrimaryKeySelective(fa);
				map.put("featureengineeringAssembly",fa);
				Feedback feedback = featureengineeringChildAssemblyService.save(feav);
				map.put("featureengineeringChildAssemblies",feedback.getData());
			}

			return new Feedback(true,"特征离散信息设置成功",map);
		}catch(Exception e){
			e.printStackTrace();
			log.error("特征离散信息保存异常"+e.getMessage(),e);
			return new Feedback(false,"特征离散信息设置异常",map);
		}
	}

	/**
	 * 字段查询
	 * @param map
	 * @param user
	 * @param expid
	 */
	private void findTableChilds(Map map,User user,String expid){

		AssemblyExample ae = new AssemblyExample() ;

		ae.createCriteria()
				.andAccountEqualTo(user.getId())
				.andBdpfMllibExperimentIdEqualTo(expid)
				.andBdpfMllibAssemblyLibraryIdEqualTo("3");//数据源
		List<Assembly> datasources = assemblyService.selectByExample(ae);
		List<TableChild> tableChilds = new ArrayList<>();
//		if (CollectionUtils.isEmpty(datasources))
//			map.put("tableChilds",tableChilds);
		if (datasources.size() == 2){
			Assembly e1 = datasources.get(0);
			Assembly e2 = datasources.get(1);
			if (e1.getXh() < e2.getXh()){//左表默认取最早拖拽进来的数据源
				tableChilds = dataSourceService.queryTableChild(e1.getName(),user.getId());
			}else{
				tableChilds = dataSourceService.queryTableChild(e2.getName(),user.getId());
			}
			map.put("tableChilds",tableChilds);
		}else if (datasources.size() == 1){
			tableChilds = dataSourceService.queryTableChild(datasources.get(0).getName(),user.getId());
			map.put("tableChilds",tableChilds);
		}else {
			map.put("tableChilds",tableChilds);
		}
	}
	
	/**
	 * 
	 * @Title: model_20_list 
	 * @Description: 特征尺寸变换列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_20_list")
	@ResponseBody
	public Map<String, Object> model_20_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}
		try {// 所有字段
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			Assembly assembly2 = new Assembly();
			if(assembly!=null&&assembly.getParentId()!=null&&assembly.getParentId().split(",").length==1){//说明有一个数据源
				assembly2 = assemblyService.selectByPrimaryKey(assembly.getParentId());
			}else{//说明有多个数据源
				
			}			
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

//			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("tableChilds");
//			map.put("tableChilds", tableChilds);
			// 已选字段
			List<FeatureengineeringAssembly> featureengineeringAssemblys = featureengineeringAssemblyService.queryFeatureengineeringAssembly(id);
			List<FeatureengineeringChildAssembly> featureengineeringChildAssemblys = new ArrayList<FeatureengineeringChildAssembly>();
			if(featureengineeringAssemblys != null && featureengineeringAssemblys.size()>0){
				for (FeatureengineeringAssembly featureengineeringAssembly : featureengineeringAssemblys) {
					FeatureengineeringChildAssembly featureengineeringChildAssembly = new FeatureengineeringChildAssembly();
					featureengineeringChildAssembly.setBdpfMllibAssemblyFeatureengineeringId(featureengineeringAssembly.getId());
					featureengineeringChildAssembly = featureengineeringChildAssemblyService.queryByMainTableId(featureengineeringChildAssembly);
					featureengineeringChildAssemblys.add(featureengineeringChildAssembly);
				}
			}
			List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
			for (FeatureengineeringChildAssembly e : featureengineeringChildAssemblys) {
				TableChild tableChild = new TableChild();
				tableChild.setName(e.getName());
				tableChild.setType(e.getDataType());
				tableChildList.add(tableChild);
			}
			map.put("featureengineeringAssemblys", featureengineeringAssemblys);
			map.put("featureengineeringChildAssemblys",featureengineeringChildAssemblys);
			session.setAttribute("featureengineeringAssemblys", featureengineeringAssemblys);
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);

//			map.put("featureengineeringAssemblys", featureengineeringAssemblys);
//			map.put("featureengineeringChildAssemblys", featureengineeringChildAssemblys);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_20_save 
	 * @Description: 特征尺寸变换列表数据保存 
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_20_save")
	@ResponseBody
	public Map<String, Object> model_20_save(String id, String[] conn, String convertType,String proportionalityCoefficient, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		//删除
		if(conn != null && conn.length>0){
			List<FeatureengineeringAssembly> featureengineeringAssemblys = featureengineeringAssemblyService.queryFeatureengineeringAssembly(id);
			if(featureengineeringAssemblys != null && featureengineeringAssemblys.size()>0){
				for (FeatureengineeringAssembly featureengineeringAssembly : featureengineeringAssemblys) {
					FeatureengineeringChildAssembly featureengineeringChildAssembly = new FeatureengineeringChildAssembly();
					featureengineeringChildAssembly.setBdpfMllibAssemblyFeatureengineeringId(featureengineeringAssembly.getId());
					featureengineeringChildAssemblyService.deleteByMainTableId(featureengineeringChildAssembly);
				}
			}
			
			FeatureengineeringAssemblyExample featureengineeringAssemblyExample = new FeatureengineeringAssemblyExample();
			featureengineeringAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
			featureengineeringAssemblyService.deleteByExample(featureengineeringAssemblyExample);
		}
		int result = 0;
		for (int i = 0; i < conn.length; i++) {
			FeatureengineeringChildAssembly featureengineeringChildAssembly = new FeatureengineeringChildAssembly();
			FeatureengineeringAssembly featureengineeringAssembly = new FeatureengineeringAssembly();
			featureengineeringAssembly.setId(Identities.uuid2());
			featureengineeringAssembly.setBdpfMllibAssemblyId(id);
			featureengineeringAssembly.setName(conn[i].split(";")[0]);
			featureengineeringAssembly.setProportionalityCoefficient(proportionalityCoefficient);
			featureengineeringAssembly.setEquidistantDispersion("");
			featureengineeringAssembly.setEquidistantDispersionInterval("");
			featureengineeringAssembly.setK("");
			featureengineeringChildAssembly.setId(Identities.uuid2());
			featureengineeringChildAssembly.setBdpfMllibAssemblyFeatureengineeringId(featureengineeringAssembly.getId());
			featureengineeringChildAssembly.setName(conn[i].split(";")[0]);
			featureengineeringChildAssembly.setDataType(conn[i].split(";")[2]);
			featureengineeringChildAssembly.setXh(Integer.parseInt(conn[i].split(";")[1]));
			int resu1 = featureengineeringChildAssemblyService.insert(featureengineeringChildAssembly);
			int resu = featureengineeringAssemblyService.insert(featureengineeringAssembly);
			result = (result + resu + resu1)/2;
		}
		map.put("result", result);
		map.put("id", id);

		return map;
	}
	
	/**
	 * 
	 * @Title: model_20_save_coefficient 
	 * @Description: 特征尺寸变换列表数据保存 
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_20_save_coefficient")
	@ResponseBody
	public Map<String, Object> model_20_save_coefficient(String id, String proportionalityCoefficient, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = 0;
		if(proportionalityCoefficient != null){
			if(Integer.parseInt(proportionalityCoefficient) < 0){
				map.put("msg", "特征尺寸必须为正整数！");
				return map;
			}
			List<FeatureengineeringAssembly> featureengineeringAssemblys = featureengineeringAssemblyService.queryFeatureengineeringAssembly(id);
			if( featureengineeringAssemblys != null && featureengineeringAssemblys.size()>0){
				FeatureengineeringAssembly featureengineeringAssembly = new FeatureengineeringAssembly();
				featureengineeringAssembly.setBdpfMllibAssemblyId(id);
				featureengineeringAssembly.setProportionalityCoefficient(proportionalityCoefficient);
				result = featureengineeringAssemblyService.updateBybdpfMllibAssemblyId(featureengineeringAssembly);
			}else{
				map.put("msg", "列名不能为空，请先添加列名！");
			}
		}
		map.put("result", result);
		map.put("id", id);
		return map;
	}
	
	
	/**
	 * 
	 * @Title: model_21_list 
	 * @Description: 二值化列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_21_list")
	@ResponseBody
	public Map<String, Object> model_21_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}
		try {// 所有字段
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			Assembly assembly2 = new Assembly();
			if(assembly!=null&&assembly.getParentId()!=null&&assembly.getParentId().split(",").length==1){//说明有一个数据源
				assembly2 = assemblyService.selectByPrimaryKey(assembly.getParentId());
			}else{//说明有多个数据源
				
			}			
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
//			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("tableChilds");
//			map.put("tableChilds", tableChilds);
			// 已选字段
			List<FeatureengineeringAssembly> featureengineeringAssemblys = featureengineeringAssemblyService.queryFeatureengineeringAssembly(id);
			List<FeatureengineeringChildAssembly> featureengineeringChildAssemblys = new ArrayList<FeatureengineeringChildAssembly>();
			if(featureengineeringAssemblys != null && featureengineeringAssemblys.size()>0){
				for (FeatureengineeringAssembly featureengineeringAssembly : featureengineeringAssemblys) {
					FeatureengineeringChildAssembly featureengineeringChildAssembly = new FeatureengineeringChildAssembly();
					featureengineeringChildAssembly.setBdpfMllibAssemblyFeatureengineeringId(featureengineeringAssembly.getId());
					featureengineeringChildAssembly = featureengineeringChildAssemblyService.queryByMainTableId(featureengineeringChildAssembly);
					featureengineeringChildAssemblys.add(featureengineeringChildAssembly);
				}
			}


			List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
			for (FeatureengineeringChildAssembly e : featureengineeringChildAssemblys) {
				TableChild tableChild = new TableChild();
				tableChild.setName(e.getName());
				tableChild.setType(e.getDataType());
				tableChildList.add(tableChild);
			}
			map.put("featureengineeringChildAssemblys",featureengineeringChildAssemblys);
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
			//map.put("featureengineeringChildAssemblys", featureengineeringChildAssemblys);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_21_save 
	 * @Description: 二值化列表数据保存 
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_21_save")
	@ResponseBody
	public Map<String, Object> model_21_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		//删除
		if(conn != null && conn.length>0){
			List<FeatureengineeringAssembly> featureengineeringAssemblys = featureengineeringAssemblyService.queryFeatureengineeringAssembly(id);
			if(featureengineeringAssemblys != null && featureengineeringAssemblys.size()>0){
				for (FeatureengineeringAssembly featureengineeringAssembly : featureengineeringAssemblys) {
					FeatureengineeringChildAssembly featureengineeringChildAssembly = new FeatureengineeringChildAssembly();
					featureengineeringChildAssembly.setBdpfMllibAssemblyFeatureengineeringId(featureengineeringAssembly.getId());
					featureengineeringChildAssemblyService.deleteByMainTableId(featureengineeringChildAssembly);
				}
			}
			
			FeatureengineeringAssemblyExample featureengineeringAssemblyExample = new FeatureengineeringAssemblyExample();
			featureengineeringAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
			featureengineeringAssemblyService.deleteByExample(featureengineeringAssemblyExample);
		}
		int result = 0;
		for (int i = 0; i < conn.length; i++) {
			FeatureengineeringChildAssembly featureengineeringChildAssembly = new FeatureengineeringChildAssembly();
			FeatureengineeringAssembly featureengineeringAssembly = new FeatureengineeringAssembly();
			featureengineeringAssembly.setId(Identities.uuid2());
			featureengineeringAssembly.setBdpfMllibAssemblyId(id);
			featureengineeringAssembly.setName(conn[i].split(";")[0]);
			featureengineeringAssembly.setProportionalityCoefficient("");
			featureengineeringAssembly.setEquidistantDispersion("");
			featureengineeringAssembly.setEquidistantDispersionInterval("");
			featureengineeringAssembly.setK("");
			featureengineeringChildAssembly.setId(Identities.uuid2());
			featureengineeringChildAssembly.setBdpfMllibAssemblyFeatureengineeringId(featureengineeringAssembly.getId());
			featureengineeringChildAssembly.setName(conn[i].split(";")[0]);
			featureengineeringChildAssembly.setDataType(conn[i].split(";")[2]);
			featureengineeringChildAssembly.setXh(Integer.parseInt(conn[i].split(";")[1]));
			int resu1 = featureengineeringChildAssemblyService.insert(featureengineeringChildAssembly);
			int resu = featureengineeringAssemblyService.insert(featureengineeringAssembly);
			result = (result + resu + resu1)/2;
		}
		map.put("result", result);
		map.put("id", id);

		return map;
	}
	
	
	/**
	 * 
	 * @Title: model_23_list 
	 * @Description: one-hot编码列表
	 * @param expid 组件主键ID
	 * @param id 组件ID
	 * @param session 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_23_list")
	@ResponseBody
	public Map<String, Object> model_23_list(String expid, String id, String name, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取当前登录用户信息
		User user = (User) session.getAttribute("loginuser");
		if (user == null) {
			return map;
		}
		try {// 所有字段
			Assembly assembly = assemblyService.selectByPrimaryKey(id);
			Assembly assembly2 = new Assembly();
			if(assembly!=null&&assembly.getParentId()!=null&&assembly.getParentId().split(",").length==1){//说明有一个数据源
				assembly2 = assemblyService.selectByPrimaryKey(assembly.getParentId());
			}else{//说明有多个数据源
				
			}			
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
//			List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("tableChilds");
//			map.put("tableChilds", tableChilds);
			// 已选字段
			List<FeatureengineeringAssembly> featureengineeringAssemblys = featureengineeringAssemblyService.queryFeatureengineeringAssembly(id);
			List<FeatureengineeringChildAssembly> featureengineeringChildAssemblys = new ArrayList<FeatureengineeringChildAssembly>();
			if(featureengineeringAssemblys != null && featureengineeringAssemblys.size()>0){
				for (FeatureengineeringAssembly featureengineeringAssembly : featureengineeringAssemblys) {
					FeatureengineeringChildAssembly featureengineeringChildAssembly = new FeatureengineeringChildAssembly();
					featureengineeringChildAssembly.setBdpfMllibAssemblyFeatureengineeringId(featureengineeringAssembly.getId());
					featureengineeringChildAssembly = featureengineeringChildAssemblyService.queryByMainTableId(featureengineeringChildAssembly);
					featureengineeringChildAssemblys.add(featureengineeringChildAssembly);
				}
			}

			List<TableChild> tableChildList  = new ArrayList<TableChild>();//存放当前字段list
			for (FeatureengineeringChildAssembly e : featureengineeringChildAssemblys) {
				TableChild tableChild = new TableChild();
				tableChild.setName(e.getName());
				tableChild.setType(e.getDataType());
				tableChildList.add(tableChild);
			}
			map.put("featureengineeringChildAssemblys",featureengineeringChildAssemblys);
			//将当前选中的字段存到缓存中
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChildList);
			//map.put("featureengineeringChildAssemblys", featureengineeringChildAssemblys);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: model_23_save 
	 * @Description: one-hot编码列表数据保存 
	 * @param String id 
	 * @param String convertType 
	 * @param conn 列名的数组
	 * @param session  HttpSession http缓存 
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "model_23_save")
	@ResponseBody
	public Map<String, Object> model_23_save(String id, String[] conn, String convertType, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		//删除
		if(conn != null && conn.length>0){
			List<FeatureengineeringAssembly> featureengineeringAssemblys = featureengineeringAssemblyService.queryFeatureengineeringAssembly(id);
			if(featureengineeringAssemblys != null && featureengineeringAssemblys.size()>0){
				for (FeatureengineeringAssembly featureengineeringAssembly : featureengineeringAssemblys) {
					FeatureengineeringChildAssembly featureengineeringChildAssembly = new FeatureengineeringChildAssembly();
					featureengineeringChildAssembly.setBdpfMllibAssemblyFeatureengineeringId(featureengineeringAssembly.getId());
					featureengineeringChildAssemblyService.deleteByMainTableId(featureengineeringChildAssembly);
				}
			}
			
			FeatureengineeringAssemblyExample featureengineeringAssemblyExample = new FeatureengineeringAssemblyExample();
			featureengineeringAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
			featureengineeringAssemblyService.deleteByExample(featureengineeringAssemblyExample);
		}
		int result = 0;
		for (int i = 0; i < conn.length; i++) {
			FeatureengineeringChildAssembly featureengineeringChildAssembly = new FeatureengineeringChildAssembly();
			FeatureengineeringAssembly featureengineeringAssembly = new FeatureengineeringAssembly();
			featureengineeringAssembly.setId(Identities.uuid2());
			featureengineeringAssembly.setBdpfMllibAssemblyId(id);
			featureengineeringAssembly.setName(conn[i].split(";")[0]);
			featureengineeringAssembly.setProportionalityCoefficient("");
			featureengineeringAssembly.setEquidistantDispersion("");
			featureengineeringAssembly.setEquidistantDispersionInterval("");
			featureengineeringAssembly.setK("");
			featureengineeringChildAssembly.setId(Identities.uuid2());
			featureengineeringChildAssembly.setBdpfMllibAssemblyFeatureengineeringId(featureengineeringAssembly.getId());
			featureengineeringChildAssembly.setName(conn[i].split(";")[0]);
			featureengineeringChildAssembly.setDataType(conn[i].split(";")[2]);
			featureengineeringChildAssembly.setXh(Integer.parseInt(conn[i].split(";")[1]));
			int resu1 = featureengineeringChildAssemblyService.insert(featureengineeringChildAssembly);
			int resu = featureengineeringAssemblyService.insert(featureengineeringAssembly);
			result = (result + resu + resu1)/2;
		}
		map.put("result", result);
		map.put("id", id);

		return map;
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

}