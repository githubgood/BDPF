package com.xunfang.bdpf.experiment.check.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunfang.bdpf.experiment.clas.entity.ClasExample;
import com.xunfang.bdpf.experiment.clas.service.ClasService;
import com.xunfang.bdpf.experiment.grade.entity.Grade;
import com.xunfang.bdpf.experiment.grade.service.GradeService;
import com.xunfang.bdpf.experiment.task.entity.TaskExample;
import com.xunfang.bdpf.experiment.task.service.TaskService;
import com.xunfang.bdpf.system.user.entity.User;
import com.xunfang.utils.Feedback;
import com.xunfang.utils.Page;
import com.xunfang.utils.PropertiesUtil;

/**
 * 
 * @ClassName CheckController
 * @Description: 实验批改控制器
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author weiweiqi
 * @date 2017年6月7日 上午10:27:48
 * @version V1.0
 */
@Controller
@RequestMapping(value = "check")
public class CheckController {
	/**
	 * @Fields log : 输出日志
	 * 
	 */
	private static Logger log = Logger.getLogger(CheckController.class);

	@Autowired
	//班级Service接口
	private ClasService clasService;
	@Autowired
	//课程资源管理Service接口
	private GradeService gradeService;
	
	@Autowired
	//课程资源管理Service接口
	private TaskService taskService;

	/**
	 * 
	  * @Title: exp
	  * @Description: 课程资源管理跳转方法，进入查询页
	  *  @param request HttpServletRequest http请求
	  *  @param session HttpSession http缓存
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "")
	public String check(Model model, HttpSession session) {
		//获取所有的任务信息
		String account = null;
		User user = (User)session.getAttribute("loginuser");
		if(user != null){
			if(user.getUserType()!=2){
				account = user.getAccount();
			}
		    model.addAttribute("clas", clasService.queryClassUser(account));
			model.addAttribute("task", taskService.selectTaskByAccount(account));
			return "experiment/check/index";
		}else{
			return "experiment/check/index";
		}
		
	}



    /**
     * 
      * @Title: toRerson
      * @Description: 担任批改界面
      *  @param taskId
      *  @param courseResourceId
      *  @param classId
      *  @param model
      *  @return  String 返回类型
      * @throws
     */
	@RequestMapping(value = "to-person/{taskId}/{courseResourceId}/{classId}")
	public String toRerson( @PathVariable String taskId,@PathVariable String courseResourceId,@PathVariable String classId, Model model) {
		//根据当前数据保存到界面
		model.addAttribute("taskId", taskId);
		model.addAttribute("courseResourceId", courseResourceId);
		model.addAttribute("classId", classId);
		return "experiment/check/person";
	}

	/**
	 * 
	  * @Title: toGroup
	  * @Description: 分组批改界面
	  *  @param taskId
	  *  @param courseResourceId
	  *  @param classId
	  *  @param model
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-group/{taskId}/{courseResourceId}/{classId}")
	public String toGroup( @PathVariable String taskId,@PathVariable String courseResourceId,@PathVariable String classId, Model model) {
		//根据当前数据保存到界面
		model.addAttribute("taskId", taskId);
		model.addAttribute("courseResourceId", courseResourceId);
		model.addAttribute("classId", classId);
		return "experiment/check/group";
	}
	
	/**
	 * 
	  * @Title: toDetail
	  * @Description: 跳转详情界面
	  *  @return  String 返回类型
	  * @throws
	 */
	@RequestMapping(value = "to-detail")
	public String toDetail() {
		return "experiment/check/detail";
	}
	
	/**
	 * 
	  * @Title: listCourseByPage
	  * @Description: 课程资源管理查询方法，带分页
	  *  @param keywords String 课程资源名称
	  *  @param pageNo String 从第几条开始取数据
	  *  @param pageSize String 每页显示多少条数据
	  *  @return  Page<Course> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "listDetail")
	@ResponseBody
	public Page<Grade> queryListDetail(String taskId,String classId,String courseResourceId, String pageNo, String pageSize,HttpSession session) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Grade> page = new Page<Grade>();//Page类
		List<Grade> list = new ArrayList<Grade>();//课程资源列表
		User user=(User) session.getAttribute("loginuser");
		if(user==null){
			return page;
		}
		String account = null;
		if(user.getUserType()!=2){
			account = user.getAccount();
		}
		if (StringUtils.isNotBlank(pageSize)) {
			max = Integer.parseInt(pageSize);
		}
		if (StringUtils.isNotBlank(pageNo)) {
			skip = max * (Integer.parseInt(pageNo) - 1);// mysql中记录从0开始
			page.setPageNo(Integer.parseInt(pageNo));
		} else {
			page.setPageNo(1);
		}
		try {
			page.setTotalCount(gradeService.queryListCount(taskId,classId,courseResourceId,account).size());//根据查询条件获取课程资源总条数
			list = gradeService.queryListDetail(taskId,classId,courseResourceId,account,skip, max);//根据查询条件查询实验
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public Page<Grade> queryCheckByPage(String taskId,String classId,String title, String pageNo, String pageSize,HttpSession session) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Grade> page = new Page<Grade>();//Page类
		List<Grade> list = new ArrayList<Grade>();//课程资源列表
		User user=(User) session.getAttribute("loginuser");
		if(user==null){
			return page;
		}
		String account = null;
		if(user.getUserType()!=2){
			account = user.getAccount();
		}
		if (StringUtils.isNotBlank(pageSize)) {
			max = Integer.parseInt(pageSize);
		}
		if (StringUtils.isNotBlank(pageNo)) {
			skip = max * (Integer.parseInt(pageNo) - 1);// mysql中记录从0开始
			page.setPageNo(Integer.parseInt(pageNo));
		} else {
			page.setPageNo(1);
		}
		try {
			page.setTotalCount(gradeService.getCheckCount(taskId,classId,title,account).size());//根据查询条件获取课程资源总条数
			list = gradeService.queryCheckByPage(taskId,classId,title,account,skip, max);//根据查询条件查询实验
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}
	
	/**
	 * 
	  * @Title: queryPersonListByPage
	  * @Description: 查询单人实验
	  *  @param taskId
	  *  @param classId
	  *  @param title
	  *  @param pageNo
	  *  @param pageSize
	  *  @return  Page<Grade> 返回类型
	  * @throws
	 */
	@RequestMapping(value = "personList")
	@ResponseBody
	public Page<Grade> queryPersonListByPage(String taskId,String courseResourceId,String classId,String taskStatus, String pageNo, String pageSize,HttpSession session) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Grade> page = new Page<Grade>();//Page类
		List<Grade> list = new ArrayList<Grade>();//课程资源列表
		User user=(User) session.getAttribute("loginuser");
		if(user==null){
			return page;
		}
		String account = null;
		if(user.getUserType()!=2){
			account = user.getAccount();
		}
		if (StringUtils.isNotBlank(pageSize)) {
			max = Integer.parseInt(pageSize);
		}
		if (StringUtils.isNotBlank(pageNo)) {
			skip = max * (Integer.parseInt(pageNo) - 1);// mysql中记录从0开始
			page.setPageNo(Integer.parseInt(pageNo));
		} else {
			page.setPageNo(1);
		}
		try {
			page.setTotalCount(gradeService.getPersonListCount(taskId,courseResourceId,classId,taskStatus,account).size());//根据查询条件获取课程资源总条数
			list = gradeService.queryPersonListByPage(taskId,courseResourceId,classId,taskStatus,account, skip, max);//根据查询条件查询实验
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}
	
	@RequestMapping(value = "groupList")
	@ResponseBody
	public Page<Grade> queryGroupListByPage(String taskId,String courseResourceId,String classId,String taskStatus, String pageNo, String pageSize,HttpSession session) {
		int skip = Integer.parseInt(PropertiesUtil.getValue("page.pageNo"));//从第几条开始取数据
		int max = Integer.parseInt(PropertiesUtil.getValue("page.pageSize"));//每页显示多少条数据
		Page<Grade> page = new Page<Grade>();//Page类
		List<Grade> list = new ArrayList<Grade>();//课程资源列表
		User user=(User) session.getAttribute("loginuser");
		if(user==null){
			return page;
		}
		String account = null;
		if(user.getUserType()!=2){
			account = user.getAccount();
		}
		if (StringUtils.isNotBlank(pageSize)) {
			max = Integer.parseInt(pageSize);
		}
		if (StringUtils.isNotBlank(pageNo)) {
			skip = max * (Integer.parseInt(pageNo) - 1);// mysql中记录从0开始
			page.setPageNo(Integer.parseInt(pageNo));
		} else {
			page.setPageNo(1);
		}
		try {
			page.setTotalCount(gradeService.getGroupListCount(taskId,courseResourceId,classId,taskStatus,account).size());//根据查询条件获取课程资源总条数
			list = gradeService.queryGroupListByPage(taskId,courseResourceId,classId,taskStatus,account, skip, max);//根据查询条件查询实验
			page.setResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return page;
	}
	
	/**
	 * 
	  * @Title: personGrade
	  * @Description: 数据保存
	  *  @param id
	  *  @param score
	  *  @param comment
	  *  @param session
	  *  @return  Feedback 返回类型
	  * @throws
	 */
	@RequestMapping(value = "personGrade")
	@ResponseBody
	public Feedback personGrade(String id,int score,String comment, HttpSession session) {
		Feedback feedback = null;
		try {
			Grade grade=new Grade();
			grade.setId(id);
			grade.setScore(score);
			grade.setComment(comment);
			grade.setTaskStatus(1);
			gradeService.save(grade);//保存
			feedback = new Feedback(true, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "保存失败！");
		}
		return feedback;
	}
	
	@RequestMapping(value = "groupGrade")
	@ResponseBody
	public Feedback groupGrade(String groupId,int score,String comment, HttpSession session) {
		Feedback feedback = null;
		try {
			Grade grade=new Grade();
			grade.setGroupId(groupId);
			grade.setScore(score);
			grade.setComment(comment);
			grade.setTaskStatus(1);
			gradeService.groupGrade(grade);//保存
			feedback = new Feedback(true, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			feedback = new Feedback(false, "保存失败！");
		}
		return feedback;
	}
}