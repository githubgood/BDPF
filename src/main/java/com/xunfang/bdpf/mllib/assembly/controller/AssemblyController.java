package com.xunfang.bdpf.mllib.assembly.controller;

import java.util.*;

import javax.servlet.http.HttpSession;

import com.xunfang.utils.Feedback;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.AddSerialNumChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.Assembly;
import com.xunfang.bdpf.mllib.assembly.entity.AssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.ConvertAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.ConvertAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FeatureengineeringChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FillMissingValuesChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.FilterMappingChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.JoinAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.JoinChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.JoinChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.LibraryAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.LibraryAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.NormalizationChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.NormalizationChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.RandomSamplingAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.SplitAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.SplitAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.SqlAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StandardizationAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StandardizationChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StandardizationChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.StatisticalanalysisAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.StatisticalanalysisAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.TextAnalysisAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.TextAnalysisAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.entity.UnionAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.UnionChildAssembly;
import com.xunfang.bdpf.mllib.assembly.entity.UnionChildAssemblyExample;
import com.xunfang.bdpf.mllib.assembly.service.AddSerialNumAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.AddSerialNumChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.AssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.ConvertAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FeatureengineeringAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FeatureengineeringChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FillMissingValuesAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FillMissingValuesChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FilterMappingAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.FilterMappingChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.JoinAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.JoinChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.LibraryAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.NormalizationAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.NormalizationChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.RandomSamplingAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.SplitAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.SqlAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.StandardizationAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.StandardizationChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.StatisticalanalysisAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.TextAnalysisAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.UnionAssemblyService;
import com.xunfang.bdpf.mllib.assembly.service.UnionChildAssemblyService;
import com.xunfang.bdpf.mllib.assembly.vo.Classified;
import com.xunfang.bdpf.mllib.enums.FieldType;
import com.xunfang.bdpf.mllib.experiment.entity.Experiment;
import com.xunfang.bdpf.mllib.experiment.entity.ExperimentExample;
import com.xunfang.bdpf.mllib.experiment.entity.Table;
import com.xunfang.bdpf.mllib.experiment.entity.TableChild;
import com.xunfang.bdpf.mllib.experiment.service.DataSourceService;
import com.xunfang.bdpf.mllib.experiment.service.ExperimentService;
import com.xunfang.bdpf.mllib.experiment.vo.TableVo;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Identities;
import com.xunfang.utils.Tools;

/**
 * @ClassName AssemblyController
 * @Description: 组件库控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月26日 下午4:07:44
 * @version V1.0
 */
@Controller
@RequestMapping(value = "assembly")
public class AssemblyController {
	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(AssemblyController.class);
	
	@Autowired
	private ExperimentService  experimentService;

	@Autowired
	private LibraryAssemblyService libraryAssemblyService;
	
	@Autowired
	private AssemblyService assemblyService;
	
	@Autowired
	private DataSourceService dataSourceService;
	
	@Autowired
	private ConvertAssemblyService convertAssemblyService;
	
	@Autowired
	private NormalizationAssemblyService normalizationAssemblyService;
	@Autowired
	private NormalizationChildAssemblyService normalizationChildAssemblyService;
	
	@Autowired
	private SplitAssemblyService splitAssemblyService;
	
	@Autowired
	private StatisticalanalysisAssemblyService statisticalanalysisAssemblyService;
	
	@Autowired
	private SqlAssemblyService sqlAssemblyService;
	
	@Autowired
	private FilterMappingAssemblyService filterMappingAssemblyService;
	@Autowired
	private FilterMappingChildAssemblyService filterMappingChildAssemblyService;
	@Autowired
	private JoinAssemblyService joinAssemblyService;
	@Autowired
	private JoinChildAssemblyService joinChildAssemblyService;

	@Autowired
	private UnionAssemblyService unionAssemblyService;
	@Autowired
	private UnionChildAssemblyService unionChildAssemblyService;
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
	private FillMissingValuesChildAssemblyService fillMissingValuesChildAssemblyService;
	
	@Autowired
	private TextAnalysisAssemblyService textAnalysisAssemblyService;
	
	@Autowired
	private FeatureengineeringAssemblyService featureengineeringAssemblyService;

	@Autowired
	private FeatureengineeringChildAssemblyService featureengineeringChildAssemblyService;

	/**
	 * 
	  * @Title: exp
	  * @Description: 组件库跳转方法，进入查询页
	  *  @param request HttpServletRequest http请求
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String assembly(Model model, HttpSession session) {
		//获取当前登录用户信息
		User  user = (User)session.getAttribute("loginuser");
		if(user == null){
			return "";
		}
		//组件库信息
		LibraryAssemblyExample example = new LibraryAssemblyExample();
		List<LibraryAssembly> libraryAssemblies = libraryAssemblyService.selectByExample(example);
		if(libraryAssemblies.size()==0){
			libraryAssemblies=null;
		}
		
		//遍历组件库，获取父组件list
		Map<LibraryAssembly,List<LibraryAssembly>> libraryAssemblyMap = new HashMap<LibraryAssembly,List<LibraryAssembly>>();
		List<LibraryAssembly> fileList = new ArrayList<LibraryAssembly>();
		for (LibraryAssembly libraryAssembly : libraryAssemblies) {
			if(libraryAssembly.getParentId()==null){
				fileList.add(libraryAssembly);
			}
		}
		
		//遍历组件库，获取每个父组件下的组件list
		for (LibraryAssembly libraryAssembly : fileList) {
			List<LibraryAssembly> assemblieList = new ArrayList<LibraryAssembly>();
			for(LibraryAssembly libraryAssembly2 : libraryAssemblies){
				if(libraryAssembly.getId().equals(libraryAssembly2.getParentId())&&libraryAssemblyMap.get(libraryAssembly.getId())==null){
					assemblieList.add(libraryAssembly2);
				}
			}
			libraryAssemblyMap.put(libraryAssembly, assemblieList);
		}
		
		//获取实验信息
		ExperimentExample experimentExample = new ExperimentExample();
		experimentExample.createCriteria().andAccountEqualTo(user.getId());
		List<Experiment> experiments = experimentService.selectByExample(experimentExample);
		model.addAttribute("experiments", experiments);
		
		model.addAttribute("fileList", fileList);
		model.addAttribute("libraryAssemblyMap", libraryAssemblyMap);
		
		//自定条件
		TableVo vo = new TableVo();
		vo.setAccount(user.getId());
		//获取当前表字段信息
		List<Table> tables = dataSourceService.findTables(vo);
		model.addAttribute("tables", tables);
		model.addAttribute("typeDemo", FieldType.values());
		return "/mllib/assembly/index";
	}
	
	/**
	 * 
	  * @Title: list
	  * @Description: 组件管理管理查询方法，带分页
	  *  @param keywords String 班级管理名称
	  *  @param pageNo String 从第几条开始取数据
	  *  @param pageSize String 每页显示多少条数据
	  *  @return  Page<clas> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Map<String,Object> list(String expid,String id,HttpSession session) {
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
		if(expid == null){
			return map;
		}
		try {
			//实验信息
			Experiment experiment = experimentService.selectByPrimaryKey(expid);
			map.put("experiment", experiment);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}
	
	/**
	 * 
	  * @Title: assemblylist
	  * @Description: 组件管理查询方法，带分页
	  *  @param keywords String 班级管理名称
	  *  @param pageNo String 从第几条开始取数据
	  *  @param pageSize String 每页显示多少条数据
	  *  @return  Page<clas> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "assemblylist")
	@ResponseBody
	public Map<String,Object> assemblylist(String expid,HttpSession session) {
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
		if(expid == null){
			return map;
		}
		try {
			//组件列表信息
			AssemblyExample assemblyExample = new AssemblyExample();
			assemblyExample.createCriteria().andBdpfMllibExperimentIdEqualTo(expid).andAccountEqualTo(user.getId());
			assemblyExample.setOrderByClause("xh");
			List<Assembly> assemblies = assemblyService.selectByExample(assemblyExample);
			map.put("assemblies", assemblies);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 
	  * @Title: getUUID
	  * @Description: 获取唯一的UUID
	  *  @param session
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "getUUID")
	@ResponseBody
	public String getUUID(HttpSession session) {
		try {
			return Identities.uuid2();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return "";
	}

	/**
	 * 
	  * @Title: save
	  * @Description: 实验管理保存
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(Assembly assembly,HttpSession session) {
		//获取当前登录用户信息
		User  user = (User)session.getAttribute("loginuser");
		if(user == null || assembly.getId()==null || "".equals(assembly.getId())){
			return "0";
		}

		//实验信息
		Assembly sassemblyold = assemblyService.selectByPrimaryKey(assembly.getId());
		if(sassemblyold==null&&assembly.getId()!=null){
			Assembly assemblynew1 = new Assembly();
			assemblynew1.setId(assembly.getId());
			assemblynew1.setBdpfMllibExperimentId(assembly.getBdpfMllibExperimentId());
			assemblynew1.setBdpfMllibAssemblyLibraryId(assembly.getBdpfMllibAssemblyLibraryId());
			assemblynew1.setName(assembly.getName());
			assemblynew1.setIsAssembly(1);
			assemblynew1.setAccount(user.getId());
			assemblynew1.setXh(assemblyService.queryMaxXh(assembly.getBdpfMllibExperimentId()));
			assemblyService.insert(assemblynew1);
		}

		//更新实验表父组件ID
		sassemblyold = assemblyService.selectByPrimaryKey(assembly.getTid());
		if(sassemblyold!=null&&sassemblyold.getId()!=null){
			/**
			 * add 2017-12-14
			 * 机器学习组件后跟组件不能为数据源、预处理、特征工程、sql脚本，阻止恶意连接
			 * begin
			 */
			LibraryAssembly start = libraryAssemblyService.selectByPrimaryKey(assembly.getBdpfMllibAssemblyLibraryId());
			LibraryAssembly end = libraryAssemblyService.selectByPrimaryKey(sassemblyold.getBdpfMllibAssemblyLibraryId());
			Integer startType = start.getType();
			Integer endType = end.getType();
			if (startType == 5 && (endType == 1 || endType == 2 || endType == 3 || endType ==7))
				return "-1";
			/** end **/

			if(sassemblyold.getParentId()!=null){//已经存在的，在后面累加
				if(sassemblyold.getParentId().indexOf(assembly.getId())<0 && sassemblyold.getParentId().split(",").length < 2){
					sassemblyold.setParentId(sassemblyold.getParentId()+","+assembly.getId());
				}
			}else{
				sassemblyold.setParentId(assembly.getId());
			}
			/**
			 * add by ylh 2017-12-11
			 * begin
			 * 1.解决组件只能按照拖拽顺序案例运行问题,现按照连线顺序逻辑与组件拖拽顺序无关
			 * 2.先决条件：需要按顺序连线！即从数据源开始按照执行顺序依次连接
			 */
			String[] parentIds = sassemblyold.getParentId().split(",");
			switch (parentIds.length){
				case 1:
					Assembly o = assemblyService.selectByPrimaryKey(sassemblyold.getParentId());//父组件
					sassemblyold.setXh(o.getXh() + 1);
					break;
				case 2:
					Assembly o1 = assemblyService.selectByPrimaryKey(parentIds[0]);//父组件
					Assembly o2 = assemblyService.selectByPrimaryKey(parentIds[1]);//父组件
					Integer xh = o1.getXh() > o2.getXh() ? o1.getXh() : o2.getXh();
					sassemblyold.setXh(xh +1);
					break;
			}

			/** end **/
			assemblyService.updateByPrimaryKeySelective(sassemblyold);
		}
		
		//保存实验信息
		Experiment experiment = experimentService.selectByPrimaryKey(assembly.getBdpfMllibExperimentId());
		if(experiment!=null){
			if(!"".equals(assembly.getMainarr())){
				experiment.setMainarr(assembly.getMainarr());
			}
			if(!"".equals(assembly.getConnects())){
				experiment.setContent(assembly.getConnects());
			}
			experimentService.updateByPrimaryKeySelective(experiment);
		}
		return "1";
	}

	/**
	 * add ylh 2017-12-14
	 * 组件连线删除
	 * @param assembly
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "deleteLabel")
	@ResponseBody
	public Object deleteLabel(Assembly assembly,HttpSession session){
		User user = (User) session.getAttribute("loginuser");
		if (user == null || StringUtils.isBlank(user.getId()))
			return 0;
		if (StringUtils.isBlank(assembly.getId()) || StringUtils.isBlank(assembly.getTid()))
			return 1;//线条始端组件id为空或末端组件id为空
		Assembly endAssembly = assemblyService.selectByPrimaryKey(assembly.getTid());
		String[] parentIds = endAssembly.getParentId().split(",");
		/** 只考虑至多存在两个父ID的情形 **/
		switch(parentIds.length){
			case 1:
				endAssembly.setParentId(null);
				break;
			case 2:
				List<String> pIdList = new ArrayList<>();//数组转集合不支持部分集合方法
				pIdList.add(parentIds[0]);
				pIdList.add(parentIds[1]);
				if (pIdList.remove(assembly.getId()))
					endAssembly.setParentId(pIdList.get(0));
				break;
		}
		assemblyService.updateByPrimaryKey(endAssembly);

		//保存实验信息
		Experiment experiment = experimentService.selectByPrimaryKey(assembly.getBdpfMllibExperimentId());
		if(experiment!=null){
			if(!"".equals(assembly.getMainarr())){
				experiment.setMainarr(assembly.getMainarr());
			}
			if(!"".equals(assembly.getConnects())){
				experiment.setContent(assembly.getConnects());
			}
			experimentService.updateByPrimaryKeySelective(experiment);
		}
		return "success";
	}


	/**
	 * 
	  * @Title: del
	  * @Description: 删除单个组件
	  *  @param id String 组件主键ID
	  *  @param expid String 实验ID
	  *  @param sid String 组件ID
	  *  @param connects String 线条列表字符串
	  *  @param mainarr String 组件列表字符串
	  *  @param session HttpSession session缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "del")
	@ResponseBody
	public String del(String id,String expid,String sid,String connects,String mainarr,HttpSession session) {
		//删除组件对应的参数表信息
		//删除类型转换数据
		if ("5".equals(sid)){//随机采样
			randomSamplingAssemblyService.deleteByPrimaryKey(id);
		}else if("6".equals(sid)){
			//删除过滤与映射数据子表
			FilterMappingChildAssemblyExample o = new FilterMappingChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyFilterMappingIdEqualTo(id);
			filterMappingChildAssemblyService.deleteByExample(o);

			//删除过滤与映射数据主表
			filterMappingAssemblyService.deleteByPrimaryKey(id);
		}else if ("9".equals(sid)){//join
			JoinChildAssemblyExample o = new JoinChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyJoinIdEqualTo(id);
			joinChildAssemblyService.deleteByExample(o);

			joinAssemblyService.deleteByPrimaryKey(id);
		}else if("11".equals(sid)){
			ConvertAssemblyExample convertAssemblyExample = new ConvertAssemblyExample();
			convertAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
			convertAssemblyService.deleteByExample(convertAssemblyExample);
		//删除拆分数据
		}else if ("12".equals(sid)){//增加序号列
			AddSerialNumChildAssemblyExample o = new AddSerialNumChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyAddSerialNumIdEqualTo(id);
			addSerialNumChildAssemblyService.deleteByExample(o);

			addSerialNumAssemblyService.deleteByPrimaryKey(id);
		}else if("13".equals(sid)){
			splitAssemblyService.deleteByPrimaryKey(id);
		}else if ("14".equals(sid)){//缺失值填充
			FillMissingValuesChildAssemblyExample o = new FillMissingValuesChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyFillMissingValuesIdEqualTo(id);
			fillMissingValuesChildAssemblyService.deleteByExample(o);

			fillMissingValuesAssemblyService.deleteByPrimaryKey(id);
		}else if("15".equals(sid)){
			//删除归一化数据子表
			NormalizationChildAssemblyExample normalizationChildAssemblyExample = new NormalizationChildAssemblyExample();
			normalizationChildAssemblyExample.createCriteria().andBdpfMllibAssemblyNormalizationIdEqualTo(id);
			normalizationChildAssemblyService.deleteByExample(normalizationChildAssemblyExample);
		
			//删除归一化数据主表
			normalizationAssemblyService.deleteByPrimaryKey(id);
		}else if ("16".equals(sid)){//标准化
			StandardizationChildAssemblyExample scae = new StandardizationChildAssemblyExample();
			scae.createCriteria().andBdpfMllibAssemblyStandardizationIdEqualTo(id);
			standardizationChildAssemblyService.deleteByExample(scae);

			standardizationAssemblyService.deleteByPrimaryKey(id);
		}else if ("17".equals(sid)){//union
			UnionChildAssemblyExample o = new UnionChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyUnionIdEqualTo(id);
			unionChildAssemblyService.deleteByExample(o);

			unionAssemblyService.deleteByPrimaryKey(id);
		}else if ("24".equals(sid) || "25".equals(sid) || "28".equals(sid)){//特征离散 PCA 过滤式特征选择
			FeatureengineeringChildAssemblyExample o = new FeatureengineeringChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyFeatureengineeringIdEqualTo(id);
			featureengineeringChildAssemblyService.deleteByExample(o);

			featureengineeringAssemblyService.deleteByPrimaryKey(id);
		}else if (Integer.parseInt(sid) > 31 && Integer.parseInt(sid) < 49){//统计分析
			StatisticalanalysisAssemblyExample o = new StatisticalanalysisAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
			statisticalanalysisAssemblyService.deleteByExample(o);

		}
		//删除组件表信息
		Assembly  sassemblyold = assemblyService.selectByPrimaryKey(id);
		if(sassemblyold!=null){
			assemblyService.deleteByPrimaryKey(id);
		}
		//更新实验表中的组件信息
		Experiment experiment = experimentService.selectByPrimaryKey(expid);
		if(experiment!=null){
			experiment.setContent(connects);
			experiment.setMainarr(mainarr);
			experimentService.updateByPrimaryKeySelective(experiment);
		}
		return experiment.getContent();
	}
	
	/**
	 * 
	  * @Title: dels
	  * @Description: 删除实验
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "dels")
	@ResponseBody
	public String dels(String expid,HttpSession session) {
		//获取所有的组件信息
		AssemblyExample assemblyExample = new AssemblyExample();
		assemblyExample.createCriteria().andBdpfMllibExperimentIdEqualTo(expid);
		List<Assembly> assemblies = assemblyService.selectByExample(assemblyExample);
		int result = 0;
		for (Assembly assembly : assemblies) {
			result ++;
			//删除组件对应的参数表信息
			//删除类型转换数据
			if ("5".equals(assembly.getBdpfMllibAssemblyLibraryId())){//随机采样
				randomSamplingAssemblyService.deleteByPrimaryKey(assembly.getId());
			}else if("6".equals(assembly.getBdpfMllibAssemblyLibraryId())){
				//删除过滤与映射数据子表
				FilterMappingChildAssemblyExample o = new FilterMappingChildAssemblyExample();
				o.createCriteria().andBdpfMllibAssemblyFilterMappingIdEqualTo(assembly.getId());
				filterMappingChildAssemblyService.deleteByExample(o);

				//删除过滤与映射数据主表
				filterMappingAssemblyService.deleteByPrimaryKey(assembly.getId());
			}else if ("9".equals(assembly.getBdpfMllibAssemblyLibraryId())){//join
				JoinChildAssemblyExample o = new JoinChildAssemblyExample();
				o.createCriteria().andBdpfMllibAssemblyJoinIdEqualTo(assembly.getId());
				joinChildAssemblyService.deleteByExample(o);

				joinAssemblyService.deleteByPrimaryKey(assembly.getId());
			}else if("11".equals(assembly.getBdpfMllibAssemblyLibraryId())){
				ConvertAssemblyExample convertAssemblyExample = new ConvertAssemblyExample();
				convertAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(assembly.getId());
				convertAssemblyService.deleteByExample(convertAssemblyExample);
			//删除拆分数据
			}else if ("12".equals(assembly.getBdpfMllibAssemblyLibraryId())){//增加序号列
				AddSerialNumChildAssemblyExample o = new AddSerialNumChildAssemblyExample();
				o.createCriteria().andBdpfMllibAssemblyAddSerialNumIdEqualTo(assembly.getId());
				addSerialNumChildAssemblyService.deleteByExample(o);

				addSerialNumAssemblyService.deleteByPrimaryKey(assembly.getId());
			}else if("13".equals(assembly.getBdpfMllibAssemblyLibraryId())){
				splitAssemblyService.deleteByPrimaryKey(assembly.getId());
			//删除归一化数据
			}else if ("14".equals(assembly.getBdpfMllibAssemblyLibraryId())){//缺失值填充
				FillMissingValuesChildAssemblyExample o = new FillMissingValuesChildAssemblyExample();
				o.createCriteria().andBdpfMllibAssemblyFillMissingValuesIdEqualTo(assembly.getId());
				fillMissingValuesChildAssemblyService.deleteByExample(o);

				fillMissingValuesAssemblyService.deleteByPrimaryKey(assembly.getId());
			}else if("15".equals(assembly.getBdpfMllibAssemblyLibraryId())){
				//删除归一化数据子表
				NormalizationChildAssemblyExample normalizationChildAssemblyExample = new NormalizationChildAssemblyExample();
				normalizationChildAssemblyExample.createCriteria().andBdpfMllibAssemblyNormalizationIdEqualTo(assembly.getId());
				normalizationChildAssemblyService.deleteByExample(normalizationChildAssemblyExample);
			
				//删除归一化数据主表
				normalizationAssemblyService.deleteByPrimaryKey(assembly.getId());
			}else if ("16".equals(assembly.getBdpfMllibAssemblyLibraryId())){//标准化
				StandardizationChildAssemblyExample scae = new StandardizationChildAssemblyExample();
				scae.createCriteria().andBdpfMllibAssemblyStandardizationIdEqualTo(assembly.getId());
				standardizationChildAssemblyService.deleteByExample(scae);

				standardizationAssemblyService.deleteByPrimaryKey(assembly.getId());
			}else if ("17".equals(assembly.getBdpfMllibAssemblyLibraryId())){//union
				UnionChildAssemblyExample o = new UnionChildAssemblyExample();
				o.createCriteria().andBdpfMllibAssemblyUnionIdEqualTo(assembly.getId());
				unionChildAssemblyService.deleteByExample(o);

				unionAssemblyService.deleteByPrimaryKey(assembly.getId());
			}else if ("24".equals(assembly.getBdpfMllibAssemblyLibraryId()) ||
					  "25".equals(assembly.getBdpfMllibAssemblyLibraryId()) ||
					  "28".equals(assembly.getBdpfMllibAssemblyLibraryId())){//特征离散 PCA 过滤式特征选择
				FeatureengineeringChildAssemblyExample o = new FeatureengineeringChildAssemblyExample();
				o.createCriteria().andBdpfMllibAssemblyFeatureengineeringIdEqualTo(assembly.getId());
				featureengineeringChildAssemblyService.deleteByExample(o);

				featureengineeringAssemblyService.deleteByPrimaryKey(assembly.getId());
			}else if (Integer.parseInt(assembly.getBdpfMllibAssemblyLibraryId()) > 31 && Integer.parseInt(assembly.getBdpfMllibAssemblyLibraryId()) < 49){//统计分析
				StatisticalanalysisAssemblyExample o = new StatisticalanalysisAssemblyExample();
				o.createCriteria().andBdpfMllibAssemblyIdEqualTo(assembly.getId());
				statisticalanalysisAssemblyService.deleteByExample(o);

			}

		}

		//删除组件表信息
		assemblyExample.createCriteria().andBdpfMllibExperimentIdEqualTo(expid);
		assemblyService.deleteByExample(assemblyExample);

		//如果所有参数和组件都处理完后，删除实验表数据
		if(result==assemblies.size()){
			//删除实验表信息
			result = experimentService.deleteByPrimaryKey(expid);
		}
		
		return result+"";
	}

	/**
	 * 
	  * @Title: data_show
	  * @Description: 数据查看
	  *  @param expid
	  *  @param session
	  *  @return  Map<String,Object> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "data_show")
	@ResponseBody
	public Map<String,Object> data_show(String expid,String sid,String id,HttpSession session) {
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
			if("3".equals(sid)){//读取数据库
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds!=null){
					map.put("tableChilds", tableChilds);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("5".equals(sid)){//随机采样
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds!=null){
					map.put("tableChilds", tableChilds);
				}
				List<RandomSamplingAssembly> randomSamplingAssemblies = (List<RandomSamplingAssembly>)session.getAttribute("randomSamplingAssemblies");
				if(randomSamplingAssemblies!=null){
					map.put("randomSamplingAssemblies", randomSamplingAssemblies);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("6".equals(sid)){//过滤与映射
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds!=null){
					map.put("tableChilds", tableChilds);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("9".equals(sid)){//join
				List<JoinChildAssembly> joinChildAssemblies = (List<JoinChildAssembly>)session.getAttribute("joinChildAssemblies");
				if(joinChildAssemblies!=null){
					map.put("joinChildAssemblies", joinChildAssemblies);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("11".equals(sid)){//类型转换
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds!=null){
					map.put("tableChilds", tableChilds);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("12".equals(sid)){//增加序号列
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds!=null){
					map.put("tableChilds", tableChilds);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("13".equals(sid)){//拆分
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds==null){
					tableChilds = (List<TableChild>)session.getAttribute("table"+assembly.getParentId());
				}
				map.put("tableChilds", tableChilds);
				//训练数据
				List<LabeledPoint> traininglabeledPoints = (List<LabeledPoint>)session.getAttribute("trainingList");
				if(traininglabeledPoints!=null){
					List<List<Object>> trainingList = Tools.lablePointToList(traininglabeledPoints);
					map.put("trainingList", trainingList);
				}
				//测试数据
				List<LabeledPoint> testlabeledPoints = (List<LabeledPoint>)session.getAttribute("testList");
				if(testlabeledPoints!=null){
					List<List<Object>> testList = Tools.lablePointToList(testlabeledPoints);
					map.put("testList", testList);
				}
			}else if("14".equals(sid)){//缺失值填充
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds==null){
					tableChilds = (List<TableChild>)session.getAttribute("tableChilds");
				}
				map.put("tableChilds", tableChilds);
				List<FillMissingValuesAssembly> fillMissingValuesAssemblies = (List<FillMissingValuesAssembly>)session.getAttribute("fillMissingValuesAssemblies");
				if(fillMissingValuesAssemblies!=null){
					map.put("fillMissingValuesAssemblies", fillMissingValuesAssemblies);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}

			}else if("15".equals(sid)){//归一化
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds!=null){
					map.put("tableChilds", tableChilds);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("16".equals(sid)){//标准化
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds!=null){
					map.put("tableChilds", tableChilds);
				}
				List<StandardizationChildAssembly> standardizationChildAssemblies = (List<StandardizationChildAssembly>)session.getAttribute("standardizationChildAssemblies");
				if(standardizationChildAssemblies!=null){
					map.put("standardizationChildAssemblies", standardizationChildAssemblies);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("17".equals(sid)){//union
				List<UnionChildAssembly> unionChildAssemblies = (List<UnionChildAssembly>)session.getAttribute("unionChildAssemblies");
				if(unionChildAssemblies!=null){
					List<UnionChildAssembly> unionChildAssemblies2 = new ArrayList<>();
					for (UnionChildAssembly e : unionChildAssemblies){
						if (e.getOperationType() == 0)
							unionChildAssemblies2.add(e);
					}
					map.put("unionChildAssemblies", unionChildAssemblies2);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("20".equals(sid)){//特征尺寸变换
				//结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				List<String>  columnNames =(List<String>)session.getAttribute("columnNames"+sid);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
				if(columnNames!=null){
					map.put("columnNames"+sid, columnNames);
				}
			}else if("21".equals(sid)){//二值化
				//结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				List<String>  columnNames =(List<String>)session.getAttribute("columnNames"+sid);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
				if(columnNames!=null){
					map.put("columnNames"+sid, columnNames);
				}
			}else if("23".equals(sid)){//特征尺寸变换
				//结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				List<String>  columnNames =(List<String>)session.getAttribute("columnNames"+sid);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
				if(columnNames!=null){
					map.put("columnNames"+sid, columnNames);
				}
			}else if("24".equals(sid)){//特征离散
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds!=null){
					map.put("tableChilds", tableChilds);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("25".equals(sid)){//pca
				List<double[]> pcaList = (List<double[]>)session.getAttribute("pcaList");
				if(pcaList!=null){
					map.put("pcaList", pcaList);
				}

				//通过表名和列查询对应的结果集
				List<double[]> resultList = (List<double[]>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("26".equals(sid)){//特征重要性
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds!=null){
					map.put("tableChilds", tableChilds);
				}
				//通过表名和列查询对应的结果集
				double[] result = (double[])session.getAttribute(id);
				if(result!=null){
					map.put("resultList", result);
				}
			}else if("28".equals(sid)){//过滤式特征选择
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds!=null){
					map.put("tableChilds", tableChilds);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if(Integer.parseInt(sid)>=32&&Integer.parseInt(sid)<=48){//统计分析【32-48】
				List<StatisticalanalysisAssembly> statisticalanalysisAssemblies = (List<StatisticalanalysisAssembly>)session.getAttribute("statisticalanalysisAssemblies"+id);
				if(statisticalanalysisAssemblies!=null){
					map.put("statisticalanalysisAssemblies", statisticalanalysisAssemblies);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("66".equals(sid)){//频繁项集挖掘算法
				Map<List<String>,Long> resultMap = (Map<List<String>,Long>)session.getAttribute(id);
				if(resultMap!=null){
					map.put("resultMap", resultMap);
				}
			}else if("68".equals(sid)){//推荐预测
				List<Map<Object,Object>> outlist = (List<Map<Object,Object>>)session.getAttribute("outlist"+id);
				if(outlist!=null){
					map.put("outlist", outlist);
				}
			}else if("69".equals(sid)){//分类预测
				List<Map<Object,Object>> outlist = (List<Map<Object,Object>>)session.getAttribute("outlist"+id);
				if(outlist!=null){
					map.put("outlist", outlist);
				}
			}else if("70".equals(sid)){//回归预测
				List<Map<Object,Object>> outlist = (List<Map<Object,Object>>)session.getAttribute("outlist"+id);
				if(outlist!=null){
					map.put("outlist", outlist);
				}
			}else if("71".equals(sid)){//聚类预测
				List<Integer> outlist = (List<Integer>)session.getAttribute("outlist"+id);
				if(outlist!=null){
					map.put("outlist", outlist);
				}
			}else if("73".equals(sid)){//推荐评估
				double mse = (double)session.getAttribute(id);
				map.put("mse", mse);
			}else if("75".equals(sid)){//分类评估
				//评估参数
				List<Classified> classifieds = (List<Classified>)session.getAttribute("classifieds");
				if(classifieds!=null){
					map.put("classifieds", classifieds);
				}
				//AUC	
				String auc = (String)session.getAttribute("auc");
				map.put("auc", auc);
			}else if("77".equals(sid)){//回归评估
				Map<String, Double> metricsMap = (Map<String, Double>)session.getAttribute("metricsMap");
				if(metricsMap!=null){
					map.put("metricsMap", metricsMap);
				}
			}else if("79".equals(sid)){//聚类评估
				String WSSSE = (String)session.getAttribute(id);
				map.put("wsse", WSSSE);
			}else if("81".equals(sid)){//文档主题生成模型
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("84".equals(sid)){//停用词过滤
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds!=null){
					map.put("tableChilds", tableChilds);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("86".equals(sid)){//词频统计
				List<TextAnalysisAssembly> textAnalysisAssemblies = (List<TextAnalysisAssembly>)session.getAttribute("textAnalysisAssemblies");
				if(textAnalysisAssemblies!=null){
					map.put("textAnalysisAssemblies", textAnalysisAssemblies);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("87".equals(sid)){//Split Word
				List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("table"+id);
				if(tableChilds!=null){
					map.put("tableChilds", tableChilds);
				}
				//通过表名和列查询对应的结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
			}else if("91".equals(sid)){//SQL脚本
				//结果集
				List<LinkedHashMap<String, Object>> resultList = (List<LinkedHashMap<String, Object>>)session.getAttribute(id);
				if(resultList!=null){
					map.put("resultList", resultList);
				}
				if(resultList!=null){
					//字段名称列表
					List<TableChild> tableChilds = (List<TableChild>)session.getAttribute("columnNamelist");
					if(tableChilds!=null){
						map.put("tableChilds", tableChilds);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}
	
	/**
	 * 
	  * @Title: listAssembly
	  * @Description: 获取流程列表
	  *  @param expid
	  *  @param session
	  *  @return  Map<String,Object> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "runFlow")
	@ResponseBody
	public Object runFlow(String id,String assemblyId,String expId,String parentId,String name,HttpSession session) {
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
			Map<String,Object> parms = new HashMap<String,Object>();
			//根据实验ID、帐号、父组件ID获取当前组件信息
			String[] parentIdArr = parentId.split(",");
			AssemblyExample assemblyExample = new AssemblyExample();
			assemblyExample.createCriteria().andBdpfMllibExperimentIdEqualTo(expId).andAccountEqualTo(user.getId()).andIdIn(Arrays.asList(parentIdArr));
			List<Assembly> assemblies = assemblyService.selectByExample(assemblyExample);
			if(assemblies.size()>0){
				for (int i = 0; i < parentIdArr.length; i++) {
					for (Assembly assembly : assemblies) {
						if(parentIdArr[i].equals(assembly.getId())){
							parms.put(parentIdArr[i], assembly);
						}
					}
				}
			}
			
			parms.put("id", id);
			parms.put("assemblyId", Integer.parseInt(assemblyId));
			parms.put("expId", expId);
			parms.put("parentId", parentId);
			parms.put("name", name);
			parms.put("session", session);
			parms.put("account", user.getId());
			parms.put("count", 10);
			
			//将组件相关的参数放入缓存中
			queryDatas(parms);
			
			String result = assemblyService.runFlow(parms);
			map.put("result", result);
			map.put("sourceId", parentId+"_"+assemblyId);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			return new Feedback(false,e.getMessage());
		}
		return new Feedback(true,"流程运行完成！",map);
	}
	
	@RequestMapping(value = "stop")
	@ResponseBody
	public String stop(HttpSession session){
		JavaSparkContext sc = (JavaSparkContext)session.getAttribute("sc");
		if(sc!=null){
			sc.stop();
		}
		return "";
	}
	
	/**
	 * 
	  * @Title: queryDatas
	  * @Description: 根据不同的组件获取对应的参数
	  *  @param parms Map<String,Object> 相关查询条件
	  *  @return  Map<String,Object> 返回类型
	  * @throws
	 */
	public void queryDatas(Map<String,Object> parms){
		String id = (String)parms.get("id");
		String expId = (String)parms.get("expId");
		int assemblyId = Integer.parseInt(parms.get("assemblyId")+"");
		String name = (String)parms.get("name");
		String account = (String)parms.get("account");
		HttpSession session = (HttpSession) parms.get("session");
		if(assemblyId==3){//读数据库
			//所有字段
			List<TableChild> tableChilds = dataSourceService.queryTableChild(name,account);
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
			//表名
			session.removeAttribute("tableName");
			session.setAttribute("tableName", name);
		}else if(assemblyId==5) {//随机采样
			//随机采样信息
			RandomSamplingAssembly randomSamplingAssembly = randomSamplingAssemblyService.selectByPrimaryKey(id);
			/** 流程参数校验 2017-12-15**/
			if (randomSamplingAssembly == null)
				throw new RuntimeException("请设置随机采样组件信息");

			session.removeAttribute("randomSamplingAssembly");
			session.setAttribute("randomSamplingAssembly", randomSamplingAssembly);

			String parentId = (String) parms.get("parentId");
			List<TableChild> tableChilds  =(List<TableChild>) session.getAttribute("table"+parentId);
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);

		}else if(assemblyId==6){//过滤与映射
			//过滤与映射信息
			FilterMappingAssembly filterMappingAssembly = filterMappingAssemblyService.selectByPrimaryKey(id);
			/** 流程参数校验 2017-12-15**/
			if (filterMappingAssembly == null)
				throw new RuntimeException("请设置过滤与映射组件信息");
			session.removeAttribute("filterMappingAssembly");
			session.setAttribute("filterMappingAssembly", filterMappingAssembly);
			
			//过滤与映射字段信息
			FilterMappingChildAssemblyExample filterMappingChildAssemblyExample = new FilterMappingChildAssemblyExample();
			filterMappingChildAssemblyExample.createCriteria().andBdpfMllibAssemblyFilterMappingIdEqualTo(id);
			filterMappingChildAssemblyExample.setOrderByClause("xh");
			List<FilterMappingChildAssembly> filterMappingChildAssemblies = filterMappingChildAssemblyService.selectByExample(filterMappingChildAssemblyExample);
			/** 流程参数校验 2017-12-15**/
			if (CollectionUtils.isEmpty(filterMappingChildAssemblies))
				throw new RuntimeException("请设置过滤与映射组件字段信息");
			//封装结果到统一的list中
			List<TableChild> tableChilds = new ArrayList<TableChild>();
			for (FilterMappingChildAssembly filterMappingChildAssembly : filterMappingChildAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(filterMappingChildAssembly.getName());
				tableChild.setType(filterMappingChildAssembly.getDataType());
				tableChilds.add(tableChild);
			}
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==9){//join
			//join信息
			JoinAssembly joinAssembly = joinAssemblyService.selectByPrimaryKey(id);
			/** 流程参数校验 2017-12-15**/
			if (joinAssembly == null)
				throw new RuntimeException("请设置join组件信息");
			session.removeAttribute("joinAssembly");
			session.setAttribute("joinAssembly", joinAssembly);

			Assembly leftSource = assemblyService.selectByPrimaryKey(joinAssembly.getBdpfMllibAssemblyLeftId());
			Assembly RightSource = assemblyService.selectByPrimaryKey(joinAssembly.getBdpfMllibAssemblyRightId());
			/** 流程参数校验 2017-12-15**/
			if (leftSource == null || RightSource == null)
				throw new RuntimeException("join组件需要连接两个数据源");
			session.removeAttribute("leftTableName");
			session.setAttribute("leftTableName", leftSource.getName());
			session.removeAttribute("rightTableName");
			session.setAttribute("rightTableName", RightSource.getName());
			
			//join字段信息
			JoinChildAssemblyExample o = new JoinChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyJoinIdEqualTo(id);
			List<JoinChildAssembly> joinChildAssemblies = joinChildAssemblyService.selectByExample(o);
			if (CollectionUtils.isEmpty(joinChildAssemblies))
				throw new RuntimeException("请设置join组件字段信息");
			session.removeAttribute("joinChildAssemblies");
			session.setAttribute("joinChildAssemblies", joinChildAssemblies);

			//封装结果到统一的list中
			List<TableChild> tableChilds = new ArrayList<TableChild>();
			for (JoinChildAssembly e : joinChildAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(e.getColumnIn());
				tableChild.setType(e.getDataType());
				tableChilds.add(tableChild);
			}
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==11){//类型转换
			ConvertAssemblyExample convertAssemblyExample = new ConvertAssemblyExample();
			convertAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
			convertAssemblyExample.setOrderByClause("xh");
			List<ConvertAssembly> convertAssemblies = convertAssemblyService.selectByExample(convertAssemblyExample);
			/** 流程参数校验 2017-12-15**/
			if (CollectionUtils.isEmpty(convertAssemblies))
				throw  new RuntimeException("请设置类型转换组件信息");

			//封装结果到统一的list中
			List<TableChild> tableChilds = new ArrayList<TableChild>();
			for (ConvertAssembly filterMappingChildAssembly : convertAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(filterMappingChildAssembly.getName());
				tableChild.setType(filterMappingChildAssembly.getDataType());
				tableChild.setXh(filterMappingChildAssembly.getConvertType());
				tableChilds.add(tableChild);
			}
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==12){//增加序号列
			//增加序号列信息
			AddSerialNumAssembly addSerialNumAssembly = addSerialNumAssemblyService.selectByPrimaryKey(id);
			/** 流程参数校验 2017-12-15**/
			if(addSerialNumAssembly == null)
				throw new RuntimeException("请设置增加序号列组件信息");
			session.removeAttribute("addSerialNumAssembly");
			session.setAttribute("addSerialNumAssembly", addSerialNumAssembly);

			//增加序号列字段信息
			AddSerialNumChildAssemblyExample o = new AddSerialNumChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyAddSerialNumIdEqualTo(id);
			List<AddSerialNumChildAssembly> addSerialNumChildAssemblies = addSerialNumChildAssemblyService.selectByExample(o);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(addSerialNumChildAssemblies))
				throw new RuntimeException("请设置增加序号列组件字段信息");
			session.removeAttribute("addSerialNumChildAssemblies");
			session.setAttribute("addSerialNumChildAssemblies", addSerialNumChildAssemblies);
			
			//封装结果到统一的list中
			List<TableChild> tableChilds = new ArrayList<>();
			for (AddSerialNumChildAssembly e : addSerialNumChildAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(e.getColumnName());
				tableChild.setType(e.getDataType());
				tableChilds.add(tableChild);
			}
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==13){//拆分
			//查询拆分相关参数
			SplitAssemblyExample splitAssemblyExample = new SplitAssemblyExample();
			splitAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
			List<SplitAssembly> splitAssemblies = splitAssemblyService.selectByExample(splitAssemblyExample);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(splitAssemblies))
				throw new RuntimeException("请设置拆分组件信息");
			if(splitAssemblies.size()>0){
				session.removeAttribute("splitAssemblie");
				session.setAttribute("splitAssemblie", splitAssemblies.get(0));
			}
		}else if(assemblyId==14){//缺失值填充
			//缺失值填充信息
			FillMissingValuesAssembly fillMissingValuesAssembly = fillMissingValuesAssemblyService.selectByPrimaryKey(id);
			/** 流程参数校验 2017-12-15**/
			if(fillMissingValuesAssembly == null)
				throw new RuntimeException("请设置缺失值填充组件信息");
			session.removeAttribute("fillMissingValuesAssembly");
			session.setAttribute("fillMissingValuesAssembly", fillMissingValuesAssembly);

			//缺失值填充字段信息
			FillMissingValuesChildAssemblyExample o = new FillMissingValuesChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyFillMissingValuesIdEqualTo(id);
			List<FillMissingValuesChildAssembly> fillMissingValuesChildAssemblies = fillMissingValuesChildAssemblyService.selectByExample(o);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(fillMissingValuesChildAssemblies))
				throw  new RuntimeException("请设置缺失值填充组件字段信息");
			session.removeAttribute("fillMissingValuesChildAssemblies");
			session.setAttribute("fillMissingValuesChildAssemblies", fillMissingValuesChildAssemblies);
			
			//封装结果到统一的list中
			List<TableChild> tableChilds = new ArrayList<>();
			for (FillMissingValuesChildAssembly e : fillMissingValuesChildAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(e.getColumnName());
				tableChild.setType(e.getDataType());
				tableChilds.add(tableChild);
			}
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==15){//归一化
			//归一化字段信息
			List<NormalizationChildAssembly> normalizationChildAssemblies = normalizationChildAssemblyService.queryNormalizationChild(id);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(normalizationChildAssemblies))
				throw  new RuntimeException("请设置归一化组件信息");
			//封装结果到统一的list中
			List<TableChild> tableChilds = new ArrayList<TableChild>();
			for (NormalizationChildAssembly filterMappingChildAssembly : normalizationChildAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(filterMappingChildAssembly.getName());
				tableChild.setType(filterMappingChildAssembly.getDataType());
				tableChilds.add(tableChild);
			}
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==16){//标准化
			StandardizationAssembly standardizationAssembly = standardizationAssemblyService.selectByPrimaryKey(id);
			/** 流程参数校验 2017-12-15**/
			if(standardizationAssembly == null)
				throw  new RuntimeException("请设置标准化组件信息");
			session.removeAttribute("standardizationAssembly");
			session.setAttribute("standardizationAssembly", standardizationAssembly);

			StandardizationChildAssemblyExample o = new StandardizationChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyStandardizationIdEqualTo(id);
			List<StandardizationChildAssembly> standardizationChildAssemblies = standardizationChildAssemblyService.selectByExample(o);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(standardizationChildAssemblies))
				throw  new RuntimeException("请设置标准化组件字段信息");
			session.removeAttribute("standardizationChildAssemblies");
			session.setAttribute("standardizationChildAssemblies", standardizationChildAssemblies);

			List<TableChild> tableChilds = new ArrayList<TableChild>();
			for (StandardizationChildAssembly e : standardizationChildAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(e.getColumnName());
				tableChild.setType(e.getDataType());
				tableChilds.add(tableChild);
			}
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==17){//union
			//union信息
			UnionAssembly unionAssembly = unionAssemblyService.selectByPrimaryKey(id);
			/** 流程参数校验 2017-12-15**/
			if(unionAssembly == null)
				throw  new RuntimeException("请设置union组件信息");
			session.removeAttribute("unionAssembly");
			session.setAttribute("unionAssembly", unionAssembly);
			//
			Assembly leftSource = assemblyService.selectByPrimaryKey(unionAssembly.getBdpfMllibAssemblyIdLeft());
			Assembly RightSource = assemblyService.selectByPrimaryKey(unionAssembly.getBdpfMllibAssemblyIdRight());
			/** 流程参数校验 2017-12-15**/
			if(leftSource == null || RightSource == null)
				throw  new RuntimeException("union组件需要连接两个数据源信息");
			session.removeAttribute("leftTableName");
			session.setAttribute("leftTableName", leftSource.getName());
			session.removeAttribute("rightTableName");
			session.setAttribute("rightTableName", RightSource.getName());

			//union字段信息
			UnionChildAssemblyExample o = new UnionChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyUnionIdEqualTo(id);
			List<UnionChildAssembly> unionChildAssemblies = unionChildAssemblyService.selectByExample(o);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(unionChildAssemblies))
				throw  new RuntimeException("请设置union组件字段信息");
			session.removeAttribute("unionChildAssemblies");
			session.setAttribute("unionChildAssemblies", unionChildAssemblies);

			//封装结果到统一的list中
			List<TableChild> tableChilds = new ArrayList<TableChild>();
			for (UnionChildAssembly e : unionChildAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(e.getColumnIn());
				tableChild.setType(e.getDataType());
				tableChilds.add(tableChild);
			}
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==20){//特征尺寸变换
			//特征尺寸变换主表信息
			List<FeatureengineeringAssembly> featureengineeringAssemblys = featureengineeringAssemblyService.queryFeatureengineeringAssembly(id);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(featureengineeringAssemblys))
				throw  new RuntimeException("请设置特征尺寸变换组件信息");
			session.removeAttribute("featureengineeringAssemblys");
			session.setAttribute("featureengineeringAssemblys", featureengineeringAssemblys);

            FeatureengineeringChildAssemblyExample o = new FeatureengineeringChildAssemblyExample();
            o.createCriteria().andBdpfMllibAssemblyFeatureengineeringIdEqualTo(id);
            List<FeatureengineeringChildAssembly> featureengineeringChildAssemblies = featureengineeringChildAssemblyService.selectByExample(o);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(featureengineeringChildAssemblies))
				throw  new RuntimeException("请设置特征尺寸变换组件字段信息");
            List<TableChild> tableChilds = new ArrayList<TableChild>();
            for (FeatureengineeringChildAssembly e : featureengineeringChildAssemblies) {
                TableChild tableChild = new TableChild();
                tableChild.setName(e.getName());
                tableChild.setType(e.getDataType());
                tableChilds.add(tableChild);
            }
            session.removeAttribute("table"+id);
            session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==21){//二值化
			//二值化主表信息
			List<FeatureengineeringAssembly> featureengineeringAssemblys = featureengineeringAssemblyService.queryFeatureengineeringAssembly(id);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(featureengineeringAssemblys))
				throw  new RuntimeException("请设置二值化组件信息");
			session.removeAttribute("featureengineeringAssemblys");
			session.setAttribute("featureengineeringAssemblys", featureengineeringAssemblys);

            FeatureengineeringChildAssemblyExample o = new FeatureengineeringChildAssemblyExample();
            o.createCriteria().andBdpfMllibAssemblyFeatureengineeringIdEqualTo(id);
            List<FeatureengineeringChildAssembly> featureengineeringChildAssemblies = featureengineeringChildAssemblyService.selectByExample(o);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(featureengineeringChildAssemblies))
				throw  new RuntimeException("请设置二值化组件字段信息");
            List<TableChild> tableChilds = new ArrayList<TableChild>();
            for (FeatureengineeringChildAssembly e : featureengineeringChildAssemblies) {
                TableChild tableChild = new TableChild();
                tableChild.setName(e.getName());
                tableChild.setType(e.getDataType());
                tableChilds.add(tableChild);
            }
            session.removeAttribute("table"+id);
            session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==23){//one-hot编码
			//one-hot编码主表信息
			List<FeatureengineeringAssembly> featureengineeringAssemblys = featureengineeringAssemblyService.queryFeatureengineeringAssembly(id);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(featureengineeringAssemblys))
				throw  new RuntimeException("请设置one-hot编码组件信息");
			session.removeAttribute("featureengineeringAssemblys");
			session.setAttribute("featureengineeringAssemblys", featureengineeringAssemblys);

            FeatureengineeringChildAssemblyExample o = new FeatureengineeringChildAssemblyExample();
            o.createCriteria().andBdpfMllibAssemblyFeatureengineeringIdEqualTo(id);
            List<FeatureengineeringChildAssembly> featureengineeringChildAssemblies = featureengineeringChildAssemblyService.selectByExample(o);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(featureengineeringChildAssemblies))
				throw  new RuntimeException("请设置one-hot编码组件字段信息");
            List<TableChild> tableChilds = new ArrayList<TableChild>();
            for (FeatureengineeringChildAssembly e : featureengineeringChildAssemblies) {
                TableChild tableChild = new TableChild();
                tableChild.setName(e.getName());
                tableChild.setType(e.getDataType());
                tableChilds.add(tableChild);
            }
            session.removeAttribute("table"+id);
            session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==24){//特征离散
			FeatureengineeringAssembly featureengineeringAssembly = featureengineeringAssemblyService.selectByPrimaryKey(id);
			/** 流程参数校验 2017-12-15**/
			if(featureengineeringAssembly == null)
				throw  new RuntimeException("请设置特征离散组件信息");
			session.removeAttribute("featureengineeringAssembly");
			session.setAttribute("featureengineeringAssembly", featureengineeringAssembly);
			FeatureengineeringChildAssemblyExample o = new FeatureengineeringChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyFeatureengineeringIdEqualTo(id);
			List<FeatureengineeringChildAssembly> featureengineeringChildAssemblies = featureengineeringChildAssemblyService.selectByExample(o);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(featureengineeringChildAssemblies))
				throw  new RuntimeException("请设置特征离散组件字段信息");
			session.removeAttribute("featureengineeringChildAssemblies");
			session.setAttribute("featureengineeringChildAssemblies", featureengineeringChildAssemblies);

			List<TableChild> tableChilds = new ArrayList<TableChild>();
			for (FeatureengineeringChildAssembly e : featureengineeringChildAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(e.getName());
				tableChild.setType(e.getDataType());
				tableChilds.add(tableChild);
			}
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		} else if(assemblyId==25){//PCA
			FeatureengineeringAssembly featureengineeringAssembly = featureengineeringAssemblyService.selectByPrimaryKey(id);
			/** 流程参数校验 2017-12-15**/
			if(featureengineeringAssembly == null)
				throw  new RuntimeException("请设置主成分分析组件信息");
			session.removeAttribute("featureengineeringAssembly");
			session.setAttribute("featureengineeringAssembly", featureengineeringAssembly);

			String parentId = (String) parms.get("parentId");
			List<TableChild> tableChilds  =(List<TableChild>) session.getAttribute("table"+parentId);
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		} else if(assemblyId==26){//特征重要性
			String parentId = (String) parms.get("parentId");
			List<TableChild> tableChilds  =(List<TableChild>) session.getAttribute("table"+parentId);
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		} else if(assemblyId==28){//过滤式特征选择
			FeatureengineeringAssembly featureengineeringAssembly = featureengineeringAssemblyService.selectByPrimaryKey(id);
			/** 流程参数校验 2017-12-15**/
			if(featureengineeringAssembly == null)
				throw  new RuntimeException("请设置过滤式特征选择组件信息");
			session.removeAttribute("featureengineeringAssembly");
			session.setAttribute("featureengineeringAssembly", featureengineeringAssembly);
			FeatureengineeringChildAssemblyExample o = new FeatureengineeringChildAssemblyExample();
			o.createCriteria().andBdpfMllibAssemblyFeatureengineeringIdEqualTo(id);
			List<FeatureengineeringChildAssembly> featureengineeringChildAssemblies = featureengineeringChildAssemblyService.selectByExample(o);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(featureengineeringChildAssemblies))
				throw  new RuntimeException("请设置过滤式特征选择组件字段信息");
			//封装结果到统一的list中
			List<TableChild> tableChilds = new ArrayList<TableChild>();
			for (FeatureengineeringChildAssembly filterMappingChildAssembly : featureengineeringChildAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(filterMappingChildAssembly.getName());
				tableChild.setType(filterMappingChildAssembly.getDataType());
				tableChilds.add(tableChild);
			}
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId>=32&&assemblyId<=48){//统计分析【32-48】
			StatisticalanalysisAssemblyExample  statisticalanalysisAssemblyExample = new StatisticalanalysisAssemblyExample();
			statisticalanalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
			List<StatisticalanalysisAssembly> statisticalanalysisAssemblies = statisticalanalysisAssemblyService.selectByExample(statisticalanalysisAssemblyExample);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(statisticalanalysisAssemblies))
				throw  new RuntimeException("请设置统计分析组件信息");
			//封装结果到统一的list中
			List<TableChild> tableChilds = new ArrayList<TableChild>();
			for (StatisticalanalysisAssembly filterMappingChildAssembly : statisticalanalysisAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(filterMappingChildAssembly.getCharacteristicColumn());
				tableChild.setType(filterMappingChildAssembly.getCharacteristicType());
				tableChilds.add(tableChild);
			}
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==84||assemblyId==87){//停用词过滤/Split Word
			TextAnalysisAssemblyExample textAnalysisAssemblyExample = new TextAnalysisAssemblyExample();
			textAnalysisAssemblyExample.createCriteria().andBdpfMllibAssemblyIdEqualTo(id);
			List<TextAnalysisAssembly> textAnalysisAssemblies = textAnalysisAssemblyService.selectByExample(textAnalysisAssemblyExample);
			/** 流程参数校验 2017-12-15**/
			if(CollectionUtils.isEmpty(textAnalysisAssemblies))
				throw  new RuntimeException("请设置文本分析组件信息");
			//封装结果到统一的list中
			List<TableChild> tableChilds = new ArrayList<TableChild>();
			for (TextAnalysisAssembly filterMappingChildAssembly : textAnalysisAssemblies) {
				TableChild tableChild = new TableChild();
				tableChild.setName(filterMappingChildAssembly.getFilterColumn());
				tableChild.setType(filterMappingChildAssembly.getFilterType());
				tableChilds.add(tableChild);
			}
			session.removeAttribute("table"+id);
			session.setAttribute("table"+id, tableChilds);
		}else if(assemblyId==91){//SQL脚本
			//获取所有的数据源
			AssemblyExample assemblyExample = new AssemblyExample();
			assemblyExample.createCriteria().andAccountEqualTo(account).andBdpfMllibExperimentIdEqualTo(expId).andBdpfMllibAssemblyLibraryIdEqualTo("3");
			List<Assembly> assemblies = assemblyService.selectByExample(assemblyExample);
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
			SqlAssembly sqlAssembly = sqlAssemblyService.selectByPrimaryKey(id);
			/** 流程参数校验 2017-12-15**/
			if(sqlAssembly == null)
				throw  new RuntimeException("请设置SQL脚本组件信息");
			if(sqlAssembly!=null){
				session.removeAttribute("sqlAssembly");
				session.setAttribute("sqlAssembly", sqlAssembly);
			}
		}
	}
	
}