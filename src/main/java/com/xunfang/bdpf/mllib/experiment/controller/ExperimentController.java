package com.xunfang.bdpf.mllib.experiment.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xunfang.bdpf.mllib.assembly.entity.*;
import com.xunfang.bdpf.mllib.assembly.service.*;
import com.xunfang.bdpf.mllib.experiment.service.DataSourceService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.mllib.experiment.entity.Experiment;
import com.xunfang.bdpf.mllib.experiment.entity.ExperimentExample;
import com.xunfang.bdpf.mllib.experiment.service.ExperimentService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Identities;


/**
 * 
 * @ClassName: DataSourceController
 * @Description: 实验模块控制器
 * @author: wjp
 * @date: 2017年11月11日 下午15:02:33
 */
@Controller
@RequestMapping(value = "experiment")
public class ExperimentController {
@Autowired
private ExperimentService experimentService;
	@Autowired
	private AssemblyService assemblyService;

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
private static Logger log = Logger.getLogger(ExperimentController.class);
/**
 * 
  * @Title: save
  * @Description: 实验保存
  *  @param session HttpSession http缓存
  *  @return  Feedback 返回类型
  * @throws
 */
@RequestMapping(value = "save")
@ResponseBody
public Feedback save(@RequestParam Map<String,String> file,
		Experiment resource,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
	request.setCharacterEncoding("utf-8");
	
	Feedback feedback = new Feedback(false, "添加失败！");
	try {
			User user=(User) session.getAttribute("loginuser");
			ExperimentExample example = new ExperimentExample();
			  example.createCriteria().andNameEqualTo(file.get("name")); 
			  List<Experiment> list= experimentService.selectByExample(example);
			 if(user == null){
		    		return feedback;
			}else if(list.size()>0){
				feedback.setMessage("实验名已存在！");
		    		return feedback;
			}
	
			if(file!=null&&!StringUtils.isBlank(file.get("name"))&&!StringUtils.isBlank(file.get("description"))){
				resource.setId(Identities.uuid2());
				resource.setBdpfMllibFileId("1");
				resource.setCreateDate(new Date());
				resource.setName(file.get("name"));
				resource.setAccount(user.getId());
				resource.setDescription(file.get("description"));
				if(experimentService.insert(resource)>0){
					feedback.setMessage("添加成功！");
					feedback.setSuccessful(true);
					feedback.setData(resource);
				};	
			}else{
				feedback.setMessage("添加的信息不能为空！");
			}
	} catch (Exception e) {
		e.printStackTrace();
		log.error(e.getMessage(), e);
 }
	return feedback;
}
/**
 * 
  * @Title: checkTitle
  * @Description: 验证实验名称
  *  @param
  *  @return  boolean 返回类型
  * @throws
 */
@RequestMapping(value="checkName")
@ResponseBody
public boolean checkTitle(@RequestParam(value = "name")String title){
 	boolean falg = true;
	try {
		//验证案例名称或者文件名是否有重复
		ExperimentExample example = new ExperimentExample();
			  example.createCriteria().andNameEqualTo(title); 
	   List<Experiment> list= experimentService.selectByExample(example);
	   if(list.size()==0){
			falg = true;
		}else{
			falg = false;
		}
	} catch (Exception e) {
		e.printStackTrace();
		log.error(e.getMessage(), e);
	}
	return falg;
}
/**
 * 
  * @Title: list
  * @Description: 查询所有的实验
  *  @return  List<ResourceAssembly>
  * @throws
 */
@RequestMapping(value="experimentlist")
@ResponseBody
public List<Experiment> list(HttpSession session){
	User user=(User) session.getAttribute("loginuser");
	if(user==null){
		return null;
	}
	ExperimentExample example = new ExperimentExample();
	example.createCriteria().andAccountEqualTo(user.getId());
	
	return experimentService.selectByExample(example);
}

/**
 * 
  * @Title: resourceDel
  * @Description: 删除实验
  * *  @param id案例主键
  *  @return  Feedback
  * @throws 
 */
@RequestMapping(value="experimentDel")
@ResponseBody
public Feedback resourceDel(@RequestParam(value = "id")String id){
	if (StringUtils.isBlank(id)) {//课程资源主键ID判空
		return new Feedback(false,"没有删除的资源！");
	}
	ExperimentExample resource=new ExperimentExample();
	resource.createCriteria().andIdEqualTo(id);
	Experiment experiment=experimentService.selectByPrimaryKey(id);
	Feedback feed=new Feedback(false,"删除失败！");
	if(experiment!=null){
				if(experimentService.deleteByPrimaryKey(id)>0){
					 feed.setMessage("删除成功");
					 feed.setSuccessful(true);
				 }
	}else{
		 feed.setMessage("删除的信息不存在！");
	}	
	return feed;
}
/**
 * 
  * @Title: resourceDel
  * @Description: 查询实验
  * *  @param id案例主键
  *  @return  Experiment
  * @throws 
 */
@RequestMapping(value="searchById")
@ResponseBody
public Experiment searchById(@RequestParam(value = "id")String id){
	if (StringUtils.isBlank(id)) {//课程资源主键ID判空
		return new Experiment();
	}
	ExperimentExample resource=new ExperimentExample();
	resource.createCriteria().andIdEqualTo(id);
	Experiment experiment=experimentService.selectByPrimaryKey(id);
	return experiment==null?new Experiment():experiment ;
}

	/**
	 * 清空当前用户实验内容信息
	 * @param session
	 * @return
	 */
@RequestMapping(value = "updateExperiment")
@ResponseBody
public Object experimentUpdate(HttpSession session){
	User user = (User) session.getAttribute("loginuser");
	if (user == null || org.apache.commons.lang.StringUtils.isBlank(user.getId()))
		return false;

	ExperimentExample ee = new ExperimentExample();
	ee.createCriteria().andAccountEqualTo(user.getId());
	List<Experiment> expList = experimentService.selectByExample(ee);

	for (Experiment e : expList){

		if (org.apache.commons.lang.StringUtils.isNotBlank(e.getContent())
				&&
			org.apache.commons.lang.StringUtils.isNotBlank(e.getMainarr())){
			//获取所有的组件信息
			AssemblyExample assemblyExample = new AssemblyExample();
			assemblyExample.createCriteria().andBdpfMllibExperimentIdEqualTo(e.getId());
			List<Assembly> assemblies = assemblyService.selectByExample(assemblyExample);

			for (Assembly assembly : assemblies) {
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
			assemblyExample.createCriteria().andBdpfMllibExperimentIdEqualTo(e.getId());
			assemblyService.deleteByExample(assemblyExample);
		}

		e.setMainarr(null);
		e.setContent(null);
		experimentService.updateByPrimaryKey(e);
	}

	return true;
}




}
