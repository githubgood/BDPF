package com.xunfang.bdpf.experiment.plan.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.experiment.course.entity.CourseExample;
import com.xunfang.bdpf.experiment.course.service.CourseService;
import com.xunfang.bdpf.experiment.plan.entity.Plan;
import com.xunfang.bdpf.experiment.plan.entity.PlanExample;
import com.xunfang.bdpf.experiment.plan.entity.PlanLine;
import com.xunfang.bdpf.experiment.plan.entity.PlanLineExample;
import com.xunfang.bdpf.experiment.plan.service.PlanService;
import com.xunfang.bdpf.experiment.task.entity.Task;
import com.xunfang.bdpf.experiment.task.entity.TaskExample;
import com.xunfang.bdpf.experiment.task.service.TaskService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;

/**
 * 
 * @ClassName PlanController
 * @Description: 课程教学计划控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月9日 下午5:07:13
 * @version V1.0
 */
@Controller
@RequestMapping(value = "plan")
public class PlanController {
	/**
	 * @Fields log : 输出日志
	 */
	private static Logger log = Logger.getLogger(PlanController.class);

	@Autowired
	//课程教学计划Service接口
	private PlanService planService;

	@Autowired
	//教学计划管理Service接口
	private CourseService courseService;
	
	@Autowired
	//任务发布管理Service接口
	private TaskService taskService;
	
    /**
     * 
      * @Title: exp
      * @Description: 教学计划管理跳转方法，进入查询页
      *  @param request
      *  @param session
      *  @return  String 返回类型
      * @throws
     */
	@RequestMapping(value = "")
	public String exp(HttpServletRequest request, HttpSession session) {
		return "experiment/plan/index";
	}

	/**
	 * 
	  * @Title: toAdd
	  * @Description: 课程教学计划管理跳转方法，进入添加页
	  *  @param model Model 课程教学计划model
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-add")
	public String toAdd(Model model) {
		//获取当前课程资源信息
		CourseExample example =new CourseExample();
		example.createCriteria().andFileTypeEqualTo("0");
		example.setOrderByClause("xh");
		model.addAttribute("course", courseService.selectByExample(example));
		return "experiment/plan/add";
	}

	/**
	 * 
	  * @Title: toEdit
	  * @Description: 课程教学计划管理跳转方法，进入修改页
	  *  @param id String 当前课程教学计划主键ID
	  *  @param model Model 课程教学计划model
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-edit/{id}")
	public String toEdit(@PathVariable String id, Model model) {
		CourseExample example =new CourseExample();
		example.createCriteria().andFileTypeEqualTo("0");
		model.addAttribute("course", courseService.selectByExample(example));
		model.addAttribute("plan", planService.selectByPrimaryKey(id));
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("teachingPlanId", id);
		StringBuffer sb=new StringBuffer();
		List<PlanLine> list=planService.queryPlanLineById(param);
		for(int i=0;i<list.size();i++){
			sb=sb.append(list.get(i).getCourseResourceId()+"-");
		}
		model.addAttribute("planLine", sb.toString());
		return "experiment/plan/edit";
	}
	
	/**
	 * 
	  * @Title: listCourseByPage
	  * @Description: 课程教学计划管理查询方法，带分页
	  *  @param keywords String 课程教学计划名称
	  *  @param pageNo String 从第几条开始取数据
	  *  @param pageSize String 每页显示多少条数据
	  *  @return  Page<Course> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public Page<Plan> listCourseByPage(String keywords, String pageNo, String pageSize,HttpSession session) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Plan> page = new Page<Plan>();
		List<Plan> list = new ArrayList<Plan>();
		if (StringUtils.isNotBlank(pageSize)) {
			max = Integer.parseInt(pageSize);
		}
		if (StringUtils.isNotBlank(pageNo)) {
			skip = max * (Integer.parseInt(pageNo) - 1);// mysql中记录从0开始
			page.setPageNo(Integer.parseInt(pageNo));
		} else {
			page.setPageNo(1);
		}
		//获取当前登录用户信息
//		User  user = (User)session.getAttribute("loginuser");
		String account = null;
//		if(user.getUserType() != 2){
//			account = user.getAccount();
//		}
		try {
			page.setTotalCount(planService.getPlanCount(keywords,account));
			list = planService.queryPlanByPage(keywords, skip, max,account);
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}

	/**
	 * 
	  * @Title: checkName
	  * @Description: 验证课程计划名称
	  *  @param name
	  *  @param id
	  *  @return  boolean 返回类型
	  * @throws
	 */
	@RequestMapping(value="checkName", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkName(String name, String id){
		boolean falg = true;
		if(id.equals("undefined")||"".equals(id)){//课程资源主键ID判空已经未定义判断
			id = "";
		}
		try {
			//根据课程资源主键ID和序号查询是否存在重复的课程资源序号
			PlanExample planExample = new PlanExample();
			planExample.createCriteria().andIdNotEqualTo(id).andNameEqualTo(name);
		   List<Plan> list= planService.selectByExample(planExample);
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
	  * @Title: save
	  * @Description: 课程教学计划管理保存方法
	  *  @param course Course 课程教学计划model
	  *  @param session HttpSession http缓存
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "save/{courseId}", method = RequestMethod.POST)
	@ResponseBody
	public Feedback save(@PathVariable String courseId, Plan plan, HttpSession session) {
		Feedback feedback = null;
		try {
			planService.save(plan, courseId);
			feedback = new Feedback(true, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "保存失败！");
		}
		return feedback;
	}

	/**
	 * 
	  * @Title: delete
	  * @Description: 课程教学计划管理删除方法
	  *  @param ids String 课程教学计划主键ID
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Feedback delete(String ids) {
		Feedback feedback = null;
		if (StringUtils.isBlank(ids)) {
			feedback = new Feedback(false, "删除失败!");
			return feedback;
		}
		try {
			String[] idArr = ids.split(",");
			TaskExample task=new TaskExample();
			PlanExample plan = new PlanExample();
			PlanLineExample planLine = new PlanLineExample();
			task.createCriteria().andTeachingPlanIdIn(Arrays.asList(idArr));
			plan.createCriteria().andIdIn(Arrays.asList(idArr));
			planLine.createCriteria().andTeachingPlanIdIn(Arrays.asList(idArr));
			//任务已发布或者完成不允许删除
			List<Task> list=taskService.selectByExample(task);
			if(list.size()>0){
				feedback = new Feedback(false, "请先删除对应的任务！");
				 return feedback;
			}
			planService.deleteByExample(plan, planLine);
			feedback = new Feedback(true, "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "删除失败");
		}
		return feedback;
	}

}
